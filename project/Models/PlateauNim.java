package project.Models;

import project.Models.Exception.EmptyHeapException;
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
    private int maxMatches = 0;

    /**
     * @param heapNumber number of heaps to create
     *                   nombre de tas à créer
     */
    public PlateauNim(int heapNumber, int maxMatches) {
        this.jeu = new int[heapNumber];
        for (int i = 0; i < heapNumber; i++) {
            this.jeu[i] = i*2+1;
        }
        this.maxMatches = maxMatches;
    }

    /**
     * @param target {Heap;number of matches} which heap to subtract the matches from
     *               {Tas;nombre d'allumettes} quel tas soustraire les allumettes
     */
    public void jouerCoup(int[] target) throws HeapNumberException,MatchesNumberException, EmptyHeapException{
        // Check if the target heap number is valid
        if (target[0] > jeu.length || target[0] < 1) {
            throw new HeapNumberException();
        }

        // Check if the number of matches to remove is valid
        if (target[1] < 1) {
            throw new EmptyHeapException();
        }
        if (jeu[target[0] - 1] == 0) {
            throw new EmptyHeapException();
        }

        // Check if there are enough matches in the heap
        if (target[1] > jeu[target[0] - 1]) {
            throw new MatchesNumberException();
        }

        // Check if the number of matches to remove exceeds the maximum allowed
        if (maxMatches != 0 && target[1] > maxMatches) {
            throw new MatchesNumberException();
        }

        // If all checks pass, remove the specified number of matches from the heap
        jeu[target[0] - 1] -= target[1];
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
