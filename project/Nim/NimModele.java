package project.Nim;


import java.util.Arrays;
import java.util.Scanner;

public class NimModele {
    /**
     * @author Nicolas
     * @param coup player's move in format of string = "int int"
     * @param jeu int[][] Array of Heap and the matches in those
     * @return true if the move is valid, false otherwise
     */
    public boolean verifierCoup(String coup, int[][] jeu){

        // a scanner is instantiated
        Scanner scanner = new Scanner(coup);

        // array to fill with first and second int found in the for loop
        int[] coupIntCheck = new int[2];

        // the scanner is iterated 2 times to check if the two number are int
        for (int i = 0; i < 2; i++) {

            // if the scanner do not find an int it returns false
            if ( !scanner.hasNextInt()) {
                return false;
            }

            // add the int that got check to the array
            coupIntCheck[i] = scanner.nextInt();
        }

        // if scanner have something else after the second number the format is not validated
        // if the first item in coupIntCheck is inferior to the minimum number of heap or greater
        // if more matches want to be subtracted than the current number in the heap or inferior to 1
        // false is returned
        if ( scanner.hasNext() ||
            coupIntCheck[0] > jeu.length || coupIntCheck[0] < 1 ||
            coupIntCheck[1] > numberOfMatches(jeu[coupIntCheck[0]]) || coupIntCheck[1] < 1
        ) { return false; }


        return true;
    }

    private int numberOfMatches(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1){res++;}
        }
        return res;
    }
}
