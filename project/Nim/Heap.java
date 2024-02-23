package project.Nim;

import java.util.Arrays;

public class Heap {
    int[][] jeu;

    /**
     * @author Nicolas
     * @param heapNumber how many number of heap to create
     */
    public Heap(int heapNumber) {
        this.jeu = new int[heapNumber][];
        for (int i = 0; i < heapNumber; i++) {
            this.jeu[i] = new int[ 2*i +1 ];
        }
    }


    public void removeMatches(int heap, int matches){
        //it makes a new row with the length of the old row minus the number of matches
        //i had to add heap-1 because the array start at 0
        //it throws an exception if the number of matches is greater than the number of matches in the heap
        int heapLength = this.jeu[heap-1].length;
        if (heapLength < matches){
            throw new IllegalArgumentException("You can't remove more matches than there are in the heap");
        }
        else {
            this.jeu[heap - 1] = new int[this.jeu[heap - 1].length - matches];
        }
    }


    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < this.jeu.length; i++) {
            res += Integer.toString(i+1) + "\t:\t";
            for (int j = 0; j < this.jeu[i].length; j++) {
                res += "|\t";
            }
            res += "\n";
        }
        return res;
    }


}
