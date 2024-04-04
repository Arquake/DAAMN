package project.Views;

import project.Models.Exception.CoupException;
import project.Models.Exception.CreateGameException;

import java.util.Scanner;

/**
 * Contains all the methods related to the View and User input
 * contient toutes les méthodes liées à la vue et à l'entrée utilisateur
 */
public class IhmNim extends AbstractIhm {

    /**
     * @param playerName the name of the players that are playing in this turn
     *                   le nom des joueurs qui jouent ce tour
     * @return new int{Number Of Matches , Heap Number}
     *        nouveau int{Nombre d'allumettes , Nombre de tas}
     * @throws CoupException if the input is invalid
     *                  si l'entrée est invalide
     */
    public int[] demanderCoup(String playerName) throws CoupException {
        int[] coup = new int[]{0, 0};
        Scanner scanner = new Scanner(System.in);
        System.out.println(playerName + " à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.\n coup : ");
        scanner = new Scanner(scanner.nextLine());
        for (int i = 0; i < 2; i++) {
            if (scanner.hasNextInt()){
                coup[i] = scanner.nextInt();
            } else {
                throw new CoupException();
            }
        }
        if (scanner.hasNext()){throw new CoupException();}
        return coup;
    }

    /**
     * @return int with the user input that should represent the number of heap
     *     int avec l'entrée utilisateur qui devrait représenter le nombre de tas
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
     * vérifie si le nombre de tas est valide
     * @param nombre user input
     *               entrée utilisateur
     * @return number of Heap if valid, -1 otherwise
     *        nombre de tas si valide, -1 sinon
     */
    private int verifierCreationJeu(String nombre) throws CreateGameException {
        Scanner scanner = new Scanner(nombre);
        // scanner check if there's an int in the string
        // scanner vérifie s'il y a un int dans la chaîne
        if ( !scanner.hasNextInt()) {
            throw new CreateGameException();
        }
        // if there's an int we parse it to an int and store it
        // si un int est trouvé, nous le stockons
        int res = scanner.nextInt();
        // if scanner hase other information or res is invalid -1 returned
        // si le scanner contient d'autres informations ou si res est invalide, -1 est renvoyé
        if (scanner.hasNext() || res < 1 ) {
            throw new CreateGameException();
        }
        return res;
    }


    /**
     * @return int with the user input that should represent the max number of matches that can be removed
     *         if the user doesn't want to play with constraints, 0 is returned
     *     int avec l'entrée utilisateur qui devrait représenter le nombre maximum d'allumettes pouvant être retirées
     *     si l'utilisateur ne veut pas jouer avec des contraintes, 0 est renvoyé
     */
    public int demanderContrainte (){
        Scanner scanner = new Scanner(System.in);
        int res = 0;

        System.out.println("Voulez-vous jouer avec des contraintes ? si oui tapez le nombre d'allumettes maximum à retirer sinon tapez 0");
        while (true) {
            if (scanner.hasNextInt()) {
                res = scanner.nextInt();
                if (res < 0) {
                    invalidData();
                } else {
                    return res;
                }
            } else {
                invalidData();
            }
        }
    }
}
