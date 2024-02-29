package project.modele;


import project.controleur.Heap;
import project.controleur.Joueur;

import java.util.Scanner;

public class NimModele {

    public int verifierCreationJeu(String nombre){
        Scanner scanner = new Scanner(nombre);
        // scanner check if there's an int in the string
        if ( !scanner.hasNextInt()) {
            return -1;
        }
        // if there's an int we parse it to an int and store it
        int res = Integer.parseInt(scanner.next());
        // if scanner hase other information or res is invalid -1 returned
        if (scanner.hasNext() ) { return -1; }
        // if everything is valid we return the res
        return res;
    }

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
        if ( scanner.hasNext() ||
            coupIntCheck[0] > jeu.getNumberOfheap() || coupIntCheck[0] < 1 ||
            coupIntCheck[1] > jeu.getNumberOfMatchesInHeap(coupIntCheck[0]) || coupIntCheck[1] < 1
        ) { return false; }

        return true;
    }

    /**
     * @author
     * @param tas game object with heaps in it
     * @return if all heaps in game object are empty
     */
    public boolean gameFinished(Heap tas) {
        return tas.isEmpty();
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

    /**
     * @author
     * @param response the string the user has given if he wants to replay or not
     * @return true if the string is a Yes or No, false otherwise
     */
    public boolean replayValidResponse(String response){
        Scanner scanner = new Scanner(response);
        if (!scanner.hasNext()) {return false;}
        String res = scanner.next();
        return !scanner.hasNext() && (res.equals("Y") || res.equals("y") || res.equals("N") || res.equals("n"));
    }

    /**
     * @author
     * @param response the string the user has given if he wants to replay or not
     * @return true if he wants to replay, false if he wants to stop
     */
    public boolean isReplaying(String response){
        Scanner scanner = new Scanner(response);
        String res = scanner.next();
        return res.equals("Y") || res.equals("y");
    }

    /**
     * @author
     * @param j1 first player
     * @param j2 second player
     * @return the name of the player with a better score
     */
    public String isBetter(Joueur j1, Joueur j2){
        return j1.compareTo(j2) > 0 ? j1.getNom() : j2.getNom();
    }
}
