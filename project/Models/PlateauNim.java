package project.Models;

import project.Models.Exception.*;

/**
 * game board
 * plateau de jeu
 */
public class PlateauNim extends AbstractPlateau {
    /**
     * Tableau d'entier contenant le nombre d'allumette dans chaque tas
     */
    private final int[] jeu;
    private final int maxMatches;

    /**
     * makes the actual plateau
     * stores the maximum number of matches that can be removed
     * @param heapNumber number of heaps to create
     *                   nombre de tas à créer
     * @param maxMatches maximum number of matches that can be removed
     *                   nombre maximum d'allumettes qui peuvent être retirées
     */
    public PlateauNim(int heapNumber, int maxMatches) {
        this.jeu = new int[heapNumber];
        for (int i = 0; i < heapNumber; i++) {
            this.jeu[i] = i*2+1;
        }
        this.maxMatches = maxMatches;
    }

    /**
     * plays each turn of the game
     * @param target {Heap;number of matches} which heap to subtract the matches from
     *               {Tas;nombre d'allumettes} quel tas soustraire les allumettes
     *               {1;2} remove 2 matches from heap 1
     * @throws HeapNumberException if the heap number is invalid
     * @throws MatchesNumberException if the number of matches to remove is bigger than the maximum allowed
     * @throws CoupException if the number of matches to remove is invalid or the heap is empty
     * @throws NotEnoughMatchesException if there are not enough matches in the heap
     */
    public void jouerCoup(int[] target) throws HeapNumberException, MatchesNumberException , CoupException, NotEnoughMatchesException {
        // Check if the target heap number is valid
        if (target[0] > jeu.length || target[0] < 1) {
            throw new HeapNumberException();
        }

        // Check if the heap is empty
        if (jeu[target[0] - 1] == 0) {
            throw new CoupException();
        }

        // Check if the number of matches to remove is greater than 0
        if (target[1] < 1) {
            throw new CoupException();
        }

        // Check if there are enough matches in the heap
        if (target[1] > jeu[target[0] - 1]) {
            throw new NotEnoughMatchesException();
        }

        // Check if the number of matches to remove exceeds the maximum allowed
        if (maxMatches > 0 && target[1] > maxMatches) {
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
                    i + 1).append("\t:\t"
            ).append(
                    "\t".repeat(this.jeu.length - i)
            ).append(
                    "|\t".repeat(this.jeu[i])
            ).append("\n");
        }

        return res.toString();
    }

    /**
     * checks if there are no matches left
     * @return true if there is no matches left
     *        vrai s'il ne reste plus d'allumettes
     */
    public boolean boardCompleted(){
        for (int row: jeu) {
            if (row > 0) {return false;}
        }
        return true;
    }

    /**
     * return the board
     * @return the game board
     */
    public int[] getPlateau() {
        return jeu;
    }


    /**
     * return if the game have constraints
     * @return true if there is constraints | false otherwise
     */
    public boolean hasConstraints() {
        return maxMatches > 0;
    }

    /**
     * return the maximum number of matches
     * @return the max number of matches
     */
    public int getMaxMatches() {
        return maxMatches;
    }
}
