package project.Models.modeleNim;

import project.Models.AbstractPlateau;
import project.Models.Exception.heapNumberException;
import project.Models.Exception.matchesNumberException;

/**
 * game board
 */
public class Plateau extends AbstractPlateau {
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
    public void jouerCoup(int[] target) throws matchesNumberException, heapNumberException {
        if ( target[0] > jeu.length ) {throw new heapNumberException();}
        if ( jeu[target[0] - 1] < target[1] ) {throw new matchesNumberException();}
        jeu[target[0]-1] -= target[1];
    }

    /**
     * @return the current state of the game
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
     */
    public boolean boardCompleted(){
        for (int row: jeu) {
            if (row > 0) {return false;}
        }
        return true;
    }
}
