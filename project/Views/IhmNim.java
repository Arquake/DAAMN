package project.Views;

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
     */
    public int[] demanderCoup(String playerName) {
        int[] coup = new int[2];
        Scanner scanner;

        while (true) {
            scanner = new Scanner(System.in);
            System.out.println(playerName + " à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.\n coup : ");
            String input = scanner.nextLine();
            scanner = new Scanner(input);

            if (scanner.hasNextInt()) {
                coup[0] = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    coup[1] = scanner.nextInt();
                    // Check if both integers are greater than or equal to 1
                    if (coup[0] >= 1 && coup[1] >= 1) {
                        return coup;
                    } else {
                        afficherErreur("\u001B[41m" +"Les nombres saisis doivent être supérieurs ou égaux à 1."+"\u001B[0m");
                        continue;
                    }
                }
            }
            afficherErreur("\u001B[41m" +"Format incorrect. Veuillez saisir deux entiers séparés par un espace."+"\u001B[0m");
        }
    }

    /**
     * @return int with the user input that should represent the number of heap
     *     int avec l'entrée utilisateur qui devrait représenter le nombre de tas
     */
    public int creerJeu() {
        Scanner scanner;
        while (true) {
            System.out.print("Nombre de tas : ");
            scanner = new Scanner(System.in);
            int result = verifierCreationJeu(scanner.nextLine());
            if (result != -1) {
                return result;
            } else {
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
    private int verifierCreationJeu(String nombre) {
        Scanner scanner = new Scanner(nombre);

        if (!scanner.hasNextInt()) {
            return -1;
        }

        int res = scanner.nextInt();

        if (scanner.hasNext() || res < 1) {
            return -1;
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
        int res;

        System.out.println("Voulez-vous jouer avec des contraintes ? Si oui tapez le nombre d'allumettes maximum à retirer sinon tapez 0.");
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
