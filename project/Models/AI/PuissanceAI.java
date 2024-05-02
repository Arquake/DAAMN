package project.Models.AI;

import project.Models.AbstractPlateau;
import project.Models.Exception.InvalidColumException;
import project.Models.PlateauPuissance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PuissanceAI extends AbstractAI {


    /**
     * instantiate the AI
     *
     * @param jeu Le plateau sur lequel on joue.
     */
    public PuissanceAI(AbstractPlateau jeu) {
        super(jeu);
        System.out.println(jeu); //todo
    }

    @Override
    public void makeMove() {
        //c'mon, do something
        // L'IA joue en 2
        /* Deroulé :
        Check rotation voir si ca win
        oui : win
        non : suite

        Check si cet endroit est viable pour elle et pour l'adversaire
        (viable = où il est possible d'aligner 4 jetons)
        viable :
        non-viable IA viable

        7 si l’IA peut aligner 4 sinon
        6 si l'adversaire peut aligner 4 sinon
        5 si l’IA peut aligner 3 sinon
        4 si l'adversaire peut aligner 3 sinon
        3 si l’IA peut aligner 2 sinon
        2 si l'adversaire peut aligner 2
        1 sinon
         */

        /** pour les coups de 1 à 7, on recup ceux qui sont jouable
         * on créer un nouveau plateau avec le coup jouer
         * on vérifie la win
         * on stock dans une liste/container le couple (score de sortie, coup)
         * on
         */
        PlateauPuissance plateauPuissance = (PlateauPuissance) jeu;
        boolean isRotationActive = plateauPuissance.rotationActive();

        if (gagnerParRotation(isRotationActive,plateauPuissance)==2) return;

        int[][] terrain = plateauPuissance.getTerrain();
        List<Integer> listeCoupPossible = new ArrayList<>();
        for (int i = 0; i < terrain.length; i++) {
            if (terrain[0][i] == 0) listeCoupPossible.add(i);
        }

        //setup hashMap
        HashMap<Integer,List<Integer>> pointParCoup = new HashMap<>();
        for (int i = 1; i < 8; i++) {
            pointParCoup.put(i,new ArrayList<>());
        }

        //Rempli la hasMap
        for (int coup : listeCoupPossible){
            int[] possibilites = nombreAlignable(coup,getLigne(coup));

            //Attribution des scores
            switch (possibilites[1]){ // IA
                case 4 -> pointParCoup.get(7).add(coup);
                case 3 -> pointParCoup.get(5).add(coup);
                case 2 -> pointParCoup.get(3).add(coup);
                default -> pointParCoup.get(1).add(coup);
            }
            switch (possibilites[0]){ // Joueur
                case 4 -> pointParCoup.get(6).add(coup);
                case 3 -> pointParCoup.get(4).add(coup);
                case 2 -> pointParCoup.get(2).add(coup);
                default -> pointParCoup.get(1).add(coup);
            }
        }

        //Test des coups ayant le meilleur score
        for (int i = 7; i > 0; i--) {
            List<Integer> listeCoup = pointParCoup.get(i);

            for (int coup : listeCoup){
                System.out.println("Coup jouer : " + coup); //TODO

                PlateauPuissance copie = creerCopieTerrain(plateauPuissance);
                try{
                    copie.jouerCoup(new int[]{coup,2});

                    if (gagnerParRotation(isRotationActive,copie) != 1){
                        ((PlateauPuissance) jeu).jouerCoup(new int[]{coup, 2});
                        System.out.println("==JEU==\n"+jeu);//TODO
                        return;
                    }
                }
                catch (InvalidColumException e) {
                    System.out.println("---------------------------------------");//TODO
                    throw new RuntimeException(e);
                }
            }
        }

        //Si rien ne marche
        try {
            int randInt = ThreadLocalRandom.current().nextInt(0,listeCoupPossible.size());
            plateauPuissance.jouerCoup(new int[]{listeCoupPossible.get(randInt),2});
        } catch (InvalidColumException e) {
            throw new RuntimeException(e);
        }

    }

    private int getLigne(int column) {
        int[][] terrain = ((PlateauPuissance) jeu).getTerrain();
        for (int ligne = 1; ligne < terrain.length; ligne++) {
            if (terrain[ligne][column] != 0){
                return ligne-1;
            }
        }
        return 0;
    }

    private int gagnerParRotation(boolean isRotationActive, PlateauPuissance terrain){
        if ( isRotationActive ){
            PlateauPuissance copie = creerCopieTerrain(terrain);
            copie.tournerSensHoraire();
            int res = copie.checkWin();

            if (res == 2){
                System.out.println("L'IA l'emporte a la rotation en sens horaire");
                terrain.tournerSensHoraire();
                return 2;
            } else if (res==1){
                return 1;
            }
            copie = creerCopieTerrain(terrain);
            copie.tournerSensHoraire();
            res = copie.checkWin();

            if (res == 2){
                System.out.println("L'IA l'emporte a la rotation en sens anti-horaire");
                terrain.tournerSensAntiHoraire();
                return 2;
            } else if (res == 1) {
                return 1;
            }
        }
        return 0;
    }

    private int[] nombreAlignable(int columnCoup, int ligneCoup){
        int[] column = nombreAligneableColumn(columnCoup,ligneCoup);
        int[] ligne = nombreAligneableLigne(columnCoup,ligneCoup);
        int[] diag = nombreAligneableDiagonal(columnCoup,ligneCoup);

        int[] max = new int[2];
        max[0] = Math.max(Math.max(column[0], ligne[0]),diag[0]);
        max[1] = Math.max(Math.max(column[1], ligne[1]),diag[1]);

        return max;
    }

    private int[] nombreAligneableColumn(int columnCoup, int ligneCoup){
        PlateauPuissance plateauPuissance = (PlateauPuissance) jeu;
        int longeurTerrain = plateauPuissance.getTerrain().length;

        //setup interval de verification
        int minIntervalDeCheck = columnCoup-3;
        int maxIntervalDeCheck = columnCoup+3;

        //Rogner l'interval de verification s'il depasse du plateau
        if (maxIntervalDeCheck > longeurTerrain) maxIntervalDeCheck = longeurTerrain;
        if (minIntervalDeCheck < 0) minIntervalDeCheck = 0;

        //Compter le nombre de jeton aligneable
        int[] maxParJoueurs = new int[2];
        for (int player = 1; player < 3; player++) {
            int maxAlignable = 1;
            int[][] terrain = plateauPuissance.getTerrain();
            for (int i = columnCoup + 1; i < maxIntervalDeCheck; i++) {
                if (terrain[ligneCoup][i] == 0 || terrain[ligneCoup][i] == player) maxAlignable++;
                else break;
            }
            for (int i = columnCoup - 1; i > minIntervalDeCheck; i--) {
                if (terrain[ligneCoup][i] == 0 || terrain[ligneCoup][i] == player) maxAlignable++;
                else break;
            }

            maxAlignable = Math.min(maxAlignable, 4);
            maxParJoueurs[player-1] = maxAlignable;
        }
        return maxParJoueurs;
    }

    private int[] nombreAligneableLigne(int columnCoup, int ligneCoup){
        PlateauPuissance plateauPuissance = (PlateauPuissance) jeu;
        int hauteurTerrain = plateauPuissance.getTerrain().length;

        //setup interval de verification
        int minIntervalDeCheck = ligneCoup-3;
        int maxIntervalDeCheck = ligneCoup+3;

        //Rogner l'interval de verification s'il depasse du plateau
        if (maxIntervalDeCheck > hauteurTerrain) maxIntervalDeCheck = hauteurTerrain;
        if (minIntervalDeCheck < 0) minIntervalDeCheck = 0;

        //Compter le nombre de jeton aligneable
        int[] maxParJoueurs = new int[2];
        for (int player = 1; player < 3; player++) {
            int maxAlignable = 1;
            int[][] terrain = plateauPuissance.getTerrain();
            for (int i = ligneCoup + 1; i < maxIntervalDeCheck; i++) {
                if (terrain[i][columnCoup] == 0 || terrain[i][columnCoup] == player) maxAlignable++;
                else break;
            }
            for (int i = ligneCoup - 1; i > minIntervalDeCheck; i--) {
                if (terrain[i][columnCoup] == 0 || terrain[i][columnCoup] == player) maxAlignable++;
                else break;
            }

            maxAlignable = Math.min(maxAlignable, 4);
            maxParJoueurs[player-1] = maxAlignable;
        }
        return maxParJoueurs;
    }

    private int[] nombreAligneableDiagonal(int columnCoup, int ligneCoup) {
        PlateauPuissance plateauPuissance = (PlateauPuissance) jeu;
        int coteTerrain = plateauPuissance.getTerrain().length;

        //setup interval de verification
        int minIntervalDeCheckLigne = ligneCoup-3;
        int maxIntervalDeCheckLigne = ligneCoup+3;
        int minIntervalDeCheckColumn = columnCoup-3;
        int maxIntervalDeCheckColumn = columnCoup+3;

        //Rogner l'interval de verification s'il depasse du plateau
        if (maxIntervalDeCheckLigne > coteTerrain) maxIntervalDeCheckLigne = coteTerrain;
        if (minIntervalDeCheckLigne < 0) minIntervalDeCheckLigne = 0;
        if (maxIntervalDeCheckColumn > coteTerrain) maxIntervalDeCheckColumn = coteTerrain;
        if (minIntervalDeCheckColumn < 0) minIntervalDeCheckColumn = 0;

        //Compter le nombre de jeton aligneable
        int[] maxParJoueurs = new int[2];
        for (int player = 1; player < 3; player++) {

            int[] maxAlignable = new int[]{1,1,1,1};
            int[][] terrain = plateauPuissance.getTerrain();

            for (int i = 1; i <= maxIntervalDeCheckLigne; i++) {
                if ((columnCoup+i < maxIntervalDeCheckColumn)){
                    //System.out.println("C = " + (columnCoup+i) + " | L = " + (ligneCoup+i));
                    if (terrain[ligneCoup+i][columnCoup+i] == 0 || terrain[ligneCoup+i][columnCoup+i] == player) maxAlignable[0]++;
                    else break;
                } else break;
            }

            for (int i = 1; ligneCoup-i >= minIntervalDeCheckLigne; i++) {
                if ((columnCoup-i > minIntervalDeCheckColumn)) {
                    if (terrain[ligneCoup - i][columnCoup - i] == 0 || terrain[ligneCoup - i][columnCoup - i] == player)
                        maxAlignable[1]++;
                    else break;
                }else break;
            }

            for (int i = 1; i <= maxIntervalDeCheckLigne; i++) {
                if (! (columnCoup-i <= minIntervalDeCheckColumn)) {
                    if (terrain[ligneCoup + i][columnCoup - i] == 0 || terrain[ligneCoup + i][columnCoup - i] == player)
                        maxAlignable[2]++;
                    else break;
                }else break;
            }

            for (int i = 1; ligneCoup-i >= minIntervalDeCheckLigne; i++) {
                if (! (columnCoup+i >= maxIntervalDeCheckColumn)){
                    if (terrain[ligneCoup-i][columnCoup+i] == 0 || terrain[ligneCoup-i][columnCoup+i] == player) maxAlignable[3]++;
                    else break;
                }else break;
            }
            int maxAlignableUnit = Math.max(Math.max(maxAlignable[0], maxAlignable[1]),Math.max(maxAlignable[2],maxAlignable[3]));
            maxParJoueurs[player-1] = Math.min(maxAlignableUnit,4);
        }
        return maxParJoueurs;
    }

    private PlateauPuissance creerCopieTerrain(PlateauPuissance base){
        PlateauPuissance copie = new PlateauPuissance();

        int len = base.getTerrain().length;

        int[][] terrainCopie = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                terrainCopie[i][j] = base.getTerrain()[i][j];
            }
        }

        copie.setTerrain(terrainCopie);
        return copie;
    }


}
