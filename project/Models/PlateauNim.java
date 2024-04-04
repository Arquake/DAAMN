package project.Models;

import project.Models.Exception.HeapNumberException;
import project.Models.Exception.MatchesNumberException;

/**
 * game board
 * plateau de jeu
 */
public class PlateauNim extends AbstractPlateau {
    /**
     * Tableau d'entier contenant le nombre d'allumette dans chaque tas
     */
    private final int[] jeu;

    /**
     * @param heapNumber number of heaps to create
     *                   nombre de tas à créer
     */
    public PlateauNim(int heapNumber) {
        this.jeu = new int[heapNumber];
        for (int i = 0; i < heapNumber; i++) {
            this.jeu[i] = i*2+1;
        }
    }

    /**
     * @param target {Heap;number of matches} which heap to subtract the matches from
     *               {Tas;nombre d'allumettes} quel tas soustraire les allumettes
     */
    public void jouerCoup(int[] target) throws MatchesNumberException, HeapNumberException {
        if ( target[0] > jeu.length ) {throw new HeapNumberException();}
        if ( jeu[target[0] - 1] < target[1] || target[1] < 1 ) {throw new MatchesNumberException();}
        jeu[target[0]-1] -= target[1];
    }

    /**
     * @return the current state of the game
     *         l'état actuel du jeu
     */
    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        int gameLength = this.jeu.length;

        for (int i = 0; i < gameLength; i++) {
            res.append(
                    Integer.toString(i + 1)).append("\t:\t"
            ).append(
                    "\t".repeat(this.jeu.length - i)
            ).append(
                    "|\t".repeat(this.jeu[i])
            ).append("\n");
        }

        return res.toString();
    }

    /**
     * @return true if there is no matches left
     *        vrai s'il ne reste plus d'allumettes
     */
    public boolean boardCompleted(){
        for (int row: jeu) {
            if (row > 0) {return false;}
        }
        return true;
    }
}
