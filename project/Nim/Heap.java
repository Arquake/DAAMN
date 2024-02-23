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

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    /**
     *
     * @param n number of matches to subtract
     * @param m which heap to subtract the matches
     */
<<<<<<< Updated upstream
    public void removeMatches(int n, int m){
        this.jeu[m-1] = new int[this.jeu[m-1].length - n];
=======
    public void removeMatches(int n, int m) {
        this.jeu[m - 1] = new int[this.jeu[m - 1].length - n];
>>>>>>> Stashed changes
    }


    @Override
    public String toString() {

        String res = "";
        int gameLength = this.jeu.length;

        for (int i = 0; i < gameLength; i++) {
            res += Integer.toString(i+1) + "\t:\t"+"\t".repeat(gameLength-i);
            for (int j = 0; j < this.jeu[i].length; j++) {
                res += "|\t";
            }
            res += "\n";
        }
        return res;
    }

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    public int getNumberOfheap(){
        return jeu.length;
    }

    public int getNumberOfMatchesInHeap(int index) {
        return jeu[index-1].length;
    }

    public boolean heapIsEmpty(int index){
        return jeu[index-1].length == 0;
    }

    public boolean isEmpty(){
        for (int[] row: jeu) {
            if (row.length > 0) {return false;}
        }
        return true;
    }

}
