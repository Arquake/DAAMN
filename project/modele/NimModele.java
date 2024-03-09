package project.modele;


import java.util.Scanner;

/**
 * class to manipulate object
 */
public class NimModele {

    /**
     * create the model for game of Nim
     */
    public NimModele() {
    }

    /**
     * @param coup player's move in format of string = "int int"
     * @param jeu int[][] Array of Heap and the matches in those
     * @return true if the move is valid, false otherwise
     */
    public boolean verifierCoup(String coup, Plateau jeu){

        // a scanner is instantiated
        Scanner scanner = new Scanner(coup);

        // array to fill with first and second int found in the for loop
        int[] coupIntCheck = new int[2];

        // the scanner is iterated 2 times to check if the two number are int
        for (int i = 0; i < 2; i++) {

            // if the scanner does not find an int it returns false
            if ( !scanner.hasNextInt()) {
                return false;
            }

            // add the int that got checked to the array
            coupIntCheck[i] = scanner.nextInt();
        }

        // if scanner has something else after the second number the format is not validated
        // if the first item in coupIntCheck is inferior to the minimum number of heap or greater
        // if more matches want to be subtracted than the current number in the heap or inferior to 1
        // false is returned
        return !scanner.hasNext() &&
                coupIntCheck[0] <= jeu.getNumberOfheap() && coupIntCheck[0] >= 1 &&
                coupIntCheck[1] <= jeu.getNumberOfMatchesInHeap(coupIntCheck[0]) && coupIntCheck[1] >= 1;
    }

    /**
     * if the move is valid it executes the move otherwise it doesn't
     * @param coup the move the player wants to execute
     * @param tas the game object with heaps in it
     * @return true if the move is valid

     */
    public boolean jouerCoup(String coup, Plateau tas) {
        Scanner scanner = new Scanner(coup);
        int[] coupAJouer = new int[2];
        for (int i = 0; i < 2; i++) {
            coupAJouer[i] = scanner.nextInt();
        }
        tas.removeMatches(coupAJouer[1], coupAJouer[0]);
        return tas.heapIsEmpty(coupAJouer[0]);
    }

}
