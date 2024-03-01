package project.controleur;

/**
 * game board
 */
public class Heap {
    private final int[][] jeu;

    /**
     * @param heapNumber how many number of heap to create
     */
    public Heap(int heapNumber) {
        this.jeu = new int[heapNumber][];
        for (int i = 0; i < heapNumber; i++) {
            this.jeu[i] = new int[ 2*i +1 ];
        }
    }

    /**
     * remove matches n from the m heap
     * @param n number of matches to subtract
     * @param m which heap to subtract the matches
     */
    public void removeMatches(int n, int m) {
        this.jeu[m - 1] = new int[this.jeu[m - 1].length - n];
    }

    /**
     * @return the current state of the game
     */
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

    /**
     * @return the number of heap
     */
    public int getNumberOfheap(){
        return jeu.length;
    }

    /**
     * @param index the heap index number
     * @return the number of matches in the heap
     */
    public int getNumberOfMatchesInHeap(int index) {
        return jeu[index-1].length;
    }

    /**
     * @param index the heap index number
     * @return if the heap is empty
     */
    public boolean heapIsEmpty(int index){
        return jeu[index-1].length == 0;
    }

    /**
     * @return if there is no matches left
     */
    public boolean isEmpty(){
        for (int[] row: jeu) {
            if (row.length > 0) {return false;}
        }
        return true;
    }

}
