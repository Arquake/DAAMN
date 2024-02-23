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


    public void removeMatches(){

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
