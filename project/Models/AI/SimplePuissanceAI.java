package project.Models.AI;

import project.Models.AbstractPlateau;
import project.Models.Exception.InvalidColumException;
import project.Models.PlateauPuissance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimplePuissanceAI extends AbstractPuissaceAI {


    /**
     * Instantie l'IA
     * @param jeu le plateau sur lequel l'ia joue
     */
    public SimplePuissanceAI(AbstractPlateau jeu) {
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

        if ((gagnerParRotation(isRotationActive,plateauPuissance)==2) && (plateauPuissance.getNbRestantRotation()[1] > 0)) return new int[]{-1};

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
        for (int i = 7; i > 0; i--) {
            List<Integer> listeCoup = pointParCoup.get(i);

            for (int coup : listeCoup){

                PlateauPuissance copie = creerCopieTerrain(plateauPuissance);
                try{
                    if (i != 7){
                        copie.jouerCoup(new int[]{coup,2});

                        if ((gagnerParRotation(isRotationActive,copie) != 1)){
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
            System.out.println("problem"); //todo ahhhhhh
            int randInt = ThreadLocalRandom.current().nextInt(0,listeCoupPossible.size());
            int coup = listeCoupPossible.get(randInt);
            plateauPuissance.jouerCoup(new int[]{coup,2});
            return new int[]{coup};
        } catch (InvalidColumException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test s'il est possible de gagner grace à une rotation.
     * @param isRotationActive True si la rotation est active, false sinon
     * @param terrain Le terrain a verifié
     * @return Le numero du gagnant s'il existe, 0 sinon
     */
    int gagnerParRotation(boolean isRotationActive, PlateauPuissance terrain){
        if ( isRotationActive ){
            int[] rotationRestantes = terrain.getNbRestantRotation();

            PlateauPuissance copie = creerCopieTerrain(terrain);
            copie.tournerSensHoraire();
            int res = copie.checkWin();

            if (res == 2 && rotationRestantes[1] > 0){
                terrain.tournerSensHoraire();
                return 2;
            } else if (res==1 && rotationRestantes[0] > 0){
                return 1;
            }
            copie = creerCopieTerrain(terrain);
            copie.tournerSensHoraire();
            res = copie.checkWin();

            if (res == 2 && rotationRestantes[1] > 0){
                terrain.tournerSensAntiHoraire();
                return 2;
            } else if (res == 1 && rotationRestantes[0] > 0) {
                return 1;
            }
        }
        return 0;
    }


}
