package project.modele;

import java.util.Scanner;

/**
 * game board
 */
public class Plateau {
    private final int[] jeu;

    /**
     * @param heapNumber number of heaps to create
     */
    public Plateau(int heapNumber) {
        this.jeu = new int[heapNumber];
        for (int i = 0; i < heapNumber; i++) {
            this.jeu[i] = i*2+1;
        }
    }

    /**
     * @param target {Heap;number of matches} which heap to subtract the matches from
     */
    public void removeMatches(int[] target) {
        if ( target[0] > jeu.length || jeu[target[0] - 1] > target[1] ) {throw new RuntimeException("The number of the Heap or the matches was greater than expected");}
        jeu[target[0]] = jeu[target[0]] - target[1];
    }

    /**
     * @return the current state of the game
     */
    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        int gameLength = this.jeu.length;

        for (int i = 0; i < gameLength; i++) {
            res.append(Integer.toString(i+1) + "\t:\t"+"\t".repeat(this.jeu.length - i)+"|\t".repeat(this.jeu[i]) + "\n");
        }
        return res.toString();
    }

    /**
     * @return if there is no matches left
     */
    public boolean isEmpty(){
        for (int row: jeu) {
            if (row > 0) {return false;}
        }
        return true;
    }
}
