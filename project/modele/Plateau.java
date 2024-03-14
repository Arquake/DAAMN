package project.modele;

/**
 * game board
 */
public class Plateau {
    private final int[][] jeu;

    /**
     * @param heapNumber number of heaps to create
     */
    public Plateau(int heapNumber) {
        this.jeu = new int[heapNumber][];
        for (int i = 0; i < heapNumber; i++) {
            this.jeu[i] = new int[ 2*i +1 ];
        }
    }

    /**
     * remove matches n from the m heap
     * @param n number of matches to subtract
     * @param m which heap to subtract the matches from
     */
    public void removeMatches(int n, int m) {
        this.jeu[m - 1] = new int[this.jeu[m - 1].length - n];
    }

    /**
     * @return the current state of the game
     */
    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        int gameLength = this.jeu.length;

        for (int i = 0; i < gameLength; i++) {
            // ancienne version : Integer.toString(i+1) + "\t:\t"+"\t".repeat(gameLength-i);
            res.append("\t".repeat(gameLength - i));
            res.append("|\t".repeat(this.jeu[i].length));
            res.append("\n");
        }
        return res.toString();
    }

    /**
     * @return the number of heaps
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
     * @return if the specific heap is empty
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
