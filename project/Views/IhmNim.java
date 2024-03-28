package project.Views;

import project.Models.Exception.coupException;
import project.Models.Exception.createGameException;
import project.Views.AbstractView;

import java.util.Scanner;

/**
 * Contains all the methods related to the View and User input
 */
public class IhmNim extends AbstractView {

    /**
     * @param playerName the name of the players that are playing in this turn
     * @param matchSets String Array of the board
     * @return new int{Number Of Matches , Heap Number}
     */
    public int[] demanderCoup(String matchSets, String playerName) throws coupException {
        int[] coup = new int[]{0, 0};
        Scanner scanner = new Scanner(System.in);
        System.out.println(matchSets + playerName + " à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.\n coup : ");
        scanner = new Scanner(scanner.nextLine());
        for (int i = 0; i < 2; i++) {
            if (scanner.hasNextInt()){
                coup[i] = scanner.nextInt();
            } else {
                throw new coupException();
            }
        }
        if (scanner.hasNext()){throw new coupException();}
        return coup;
    }

    /**
     * @return int with the user input that should represent the number of heap
     */
    public int creerJeu(){
        Scanner scanner;
        while ( true ) {
            System.out.print("Nombre de tas : ");
            scanner = new Scanner(System.in);
            try {
                return verifierCreationJeu(scanner.nextLine());
            } catch(Exception e) {
                invalidData();
            }
        }
    }

    /**
     * checks if the number of Heap is valid
     * @param nombre user input
     * @return number of Heap if valid, -1 otherwise
     */
    private int verifierCreationJeu(String nombre) throws createGameException {
        Scanner scanner = new Scanner(nombre);
        // scanner check if there's an int in the string
        if ( !scanner.hasNextInt()) {
            throw new createGameException();
        }
        // if there's an int we parse it to an int and store it
        int res = scanner.nextInt();
        // if scanner hase other information or res is invalid -1 returned
        if (scanner.hasNext() || res < 1 ) {
            throw new createGameException();
        }
        return res;
    }

    public void AfficherPlateau(String plateau){
        // afficher plateau
    }
}
