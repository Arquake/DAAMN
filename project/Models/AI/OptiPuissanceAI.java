package project.Models.AI;

import project.Models.AbstractPlateau;
import project.Models.Exception.InvalidColumException;
import project.Models.PlateauPuissance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OptiPuissanceAI extends AbstractPuissaceAI {


    /**
     * Instantie l'IA
     * @param jeu le plateau sur lequel l'ia joue
     */
    public OptiPuissanceAI(AbstractPlateau jeu) {
        super(jeu);
    }

    /**
     * Trouve le coup le plus approprié pour ammener l'IA a la victoire et le joue.
     * @return Le coup joué
     */
    @Override
    public int[] makeMove() {
        PlateauPuissance plateauPuissance = (PlateauPuissance) jeu;
        boolean isRotationActive = plateauPuissance.rotationActive();

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
            if (possibilites[0] <= possibilites[1]) {
                switch (possibilites[1]) { // IA
                    case 4 -> pointParCoup.get(7).add(coup);
                    case 3 -> pointParCoup.get(5).add(coup);
                    case 2 -> pointParCoup.get(3).add(coup);
                    default -> pointParCoup.get(1).add(coup);
                }
            } else {
                switch (possibilites[0]){ // Joueur
                    case 4 -> pointParCoup.get(6).add(coup);
                    case 3 -> pointParCoup.get(4).add(coup);
                    case 2 -> pointParCoup.get(2).add(coup);
                    default -> pointParCoup.get(1).add(coup);
                }
            }
        }

        //Test des coups ayant le meilleur score
        for (int score = 7; score > 0; score--) {
            List<Integer> listeCoup = pointParCoup.get(score);

            while(!listeCoup.isEmpty()){
                int randInt = ThreadLocalRandom.current().nextInt(0,listeCoup.size());
                int coup = listeCoup.get(randInt);
                listeCoup.remove(randInt);
                try{
                    if (score == 5 || score == 3) {
                        if (estPrometteur(coup, terrain)) {
                            ((PlateauPuissance) jeu).jouerCoup(new int[]{coup, 2});
                            return new int[]{coup};
                        }
                    } else {
                        ((PlateauPuissance) jeu).jouerCoup(new int[]{coup, 2});
                        return new int[]{coup};
                    }
                }
                catch (InvalidColumException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //Si rien ne marche
        try {
            int randInt = ThreadLocalRandom.current().nextInt(0,listeCoupPossible.size());
            int coup = listeCoupPossible.get(randInt);
            plateauPuissance.jouerCoup(new int[]{coup,2});
            return new int[]{coup};
        } catch (InvalidColumException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Determine si une position est prometeuse, c'est a dire si elle peut aboutir a une ligne de 4 jeton.
     * @param columnCoup colonne sur laquelle la position est joué
     * @param terrain le plateau sur lequel on joue
     * @return true s'il est possible d'aligné 4 jetons depuis cette position, false sinon
     */
    private boolean estPrometteur(int columnCoup, int[][] terrain) {
        int ligneCoup = getLigne(columnCoup);

        int[] alignableColumn = nombreAlignableColumn(columnCoup,ligneCoup);
        int[] alignableLigne = nombreAlignableLigne(columnCoup, ligneCoup);
        int[] alignableDiag = nombreAlignableDiagonalHGBD(columnCoup, ligneCoup);
        int[] alignableDiag2 = nombreAlignableDiagonalBGHD(columnCoup, ligneCoup);

        String quiEstPrometteur = "";

        int max = Math.max(Math.max(alignableDiag[1], alignableLigne[1]),alignableColumn[1]);

        if (alignableDiag[1] == max){
            quiEstPrometteur += "D";
        }
        if (alignableLigne[1] == max){
            quiEstPrometteur += "L";
        }
        if (alignableColumn[1] == max){
            quiEstPrometteur += "C";
        }
        if (alignableDiag2[1] == max){
            quiEstPrometteur += "X";
        }

        int taille = terrain.length-1;

        int minColumn = Math.max(columnCoup-3,0);
        int maxColumn = Math.min(columnCoup+3,taille);
        int minLigne = Math.max(ligneCoup-3,0);
        int maxLigne = Math.min(ligneCoup+3,taille);

        int dansLaLigne = 1;
        int dansLaColonne = 1;
        int dansLaDiag1 = 1;
        int dansLaDiag2 = 1;

            if (quiEstPrometteur.contains("L")) {
                for (int i = 1; i <= 3; i++) {
                    // droite +
                    if (!(columnCoup + i > maxColumn)) {
                        if (terrain[ligneCoup][columnCoup + i] != 1) {
                            dansLaLigne++;
                        } else {
                            break;
                        }
                    }
                }
                for (int i = 1; i <= 3; i++) {
                    //gauche -
                    if (!(columnCoup - i < minColumn)) {
                        if (terrain[ligneCoup][columnCoup - i] != 1)  {
                            dansLaLigne++;
                        } else {
                            break;
                        }
                    }
                }
            }

            if (quiEstPrometteur.contains("C")) {
                for (int i = 1; i <= 3; i++) {

                    //bas +
                    if (!(ligneCoup + i > maxLigne)) {
                        if (terrain[ligneCoup + i][columnCoup] != 1) {
                            dansLaColonne++;
                        } else {
                            break;
                        }
                    }
                }
                for (int i = 1; i <= 3; i++) {
                    //haut -
                    if (!(ligneCoup - i < minLigne)) {
                        if (terrain[ligneCoup - i][columnCoup] != 1) {
                            dansLaColonne++;
                        } else {
                            break;
                        }
                    }
                }

            }
            if (quiEstPrometteur.contains("D")) {
                for (int i = 1; i <= 3; i++) {
                    //bas droite + +
                    if (!(ligneCoup + i > maxLigne || columnCoup + i > maxColumn)) {
                        if (terrain[ligneCoup + i][columnCoup + i] != 1) {
                            dansLaDiag1++;
                        } else {
                            break;
                        }
                    }
                }
                for (int i = 1; i <= 3; i++) {
                    //haut gauche - -
                    if (!(ligneCoup - i < minLigne || columnCoup - i < maxLigne)) {
                        if (terrain[ligneCoup - i][columnCoup - i] != 1) {
                            dansLaDiag1++;
                        } else {
                            break;
                        }
                    }
                }
            }

            if (quiEstPrometteur.contains("X")){
                for (int i = 1; i <= 3; i++) {
                    //haut droit - +
                    if (!(ligneCoup - i < minLigne || columnCoup + i > maxLigne)) {
                        if (terrain[ligneCoup - i][columnCoup + i] != 1) {
                            dansLaDiag2++;
                        } else {
                            break;
                        }
                    }
                }
                for (int i = 1; i <= 3; i++) {
                    //bas gauche + -
                    if (!(ligneCoup + i > maxLigne || columnCoup - i < minLigne)) {
                        if (terrain[ligneCoup + i][columnCoup - i] != 1) {
                            dansLaDiag2++;
                        } else {
                            break;
                        }
                    }
                }
            }
        return (   dansLaLigne > 4    // 4 alignable en dansLaLigne ?
                || dansLaColonne > 4  // 4 alignable en dansLaColonne ?
                || dansLaDiag1 > 4    // 4 alignable en diagonale \ ?
                || dansLaDiag2 > 4    // 4 alignable en diagonale / ?
        );
    }
}
