package project.modele;


import project.controleur.Heap;
import project.controleur.Joueur;

import java.util.Scanner;

public class NimModele {



    /**
     * @author Nicolas
     * @param coup player's move in format of string = "int int"
     * @param jeu int[][] Array of Heap and the matches in those
     * @return true if the move is valid, false otherwise
     */
    public boolean verifierCoup(String coup, Heap jeu){

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
        return !scanner.hasNext() &&
                coupIntCheck[0] <= jeu.getNumberOfheap() && coupIntCheck[0] >= 1 &&
                coupIntCheck[1] <= jeu.getNumberOfMatchesInHeap(coupIntCheck[0]) && coupIntCheck[1] >= 1;
    }

    /**
     * @author
     * @param coup the move the player wants to execute
     * @param tas the gam object with heaps in it
     * @return if the move is valid
     * if the move is valid execute the move otherwise don't
     */
    public boolean jouerCoup(String coup, Heap tas) {
        Scanner scanner = new Scanner(coup);
        int[] coupAJouer = new int[2];
        for (int i = 0; i < 2; i++) {
            coupAJouer[i] = scanner.nextInt();
        }
        tas.removeMatches(coupAJouer[1], coupAJouer[0]);
        return tas.heapIsEmpty(coupAJouer[0]);
    }
}