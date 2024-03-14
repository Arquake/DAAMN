package project.vue;

import java.util.Scanner;

/**
 * Contains all the methods related to the View and User input
 */
public class Ihm {

    /**
     * @param playerName the name of the players that are playing in this turn
     * @param matchSets String Array of the board
     * @return new int{Number Of Matches , Heap Number}
     */
    public int[] demanderCoup(String matchSets, String playerName) {
        int[] coup = new int[]{0, 0};
        Scanner scanner = new Scanner(System.in);
        System.out.println(matchSets + playerName + " à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.\n coup : ");
        while ( coup[0] <= 0 || coup[1] <= 0 ) {
            for (int i = 0; i < 2; i++) {
                if (scanner.hasNextInt()) {
                    System.out.println(scanner.nextInt());
                }
            }
            for (int i = 0; i < 2; i++) {
                if (scanner.hasNextInt()) {
                    coup[i] = scanner.nextInt();
                } else { coup = new int[]{0, 0}; break; }
            }
        }
        return coup;
    }

    /**
     * @return int with the user input that should represent the number of heap
     */
    public int creerJeu(){
        int res = -1;
        Scanner scanner = new Scanner("");
        while ( true ) {
            System.out.print("Nombre de tas : ");
            scanner = new Scanner(System.in);
            res = verifierCreationJeu(scanner.nextLine());
            if (res != -1) { break; }
            invalidData();
        }

        return res;
    }

    /**
     * receives user player name
     * @param i which player it is making right now
     * @return the player name to the controller
     */
    public String creerJoueur(int i){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nom du joueur "+i+" : ");
        return scanner.nextLine();
    }

    /**
     * prints to the screen if data is invalid
     */
    public void invalidData() {
        System.out.println("\u001B[41m" + "\u001B[30m" + " les données fournit sont invalides " + "\u001B[0m");
    }

    /**
     * prints which player have won
     * @param nomJoueur le nom du joueur
     */
    public void victory(String nomJoueur) {
        System.out.println("\u001B[42m" + "\u001B[30m " + nomJoueur + " a gagné " + "\u001B[0m");
    }

    /**
     * asks the player if they want to replay
     * @return the user input should be y|Y|n|N
     */
    public boolean replay() {
        Scanner scanner = new Scanner(System.in);
        String res ="";

        System.out.println("Rejouer ? (Y/N)");
        while (true) {
            if (scanner.hasNext()) {
                res = scanner.next();
            }
            if (res.equalsIgnoreCase("y") || res.equalsIgnoreCase("n")){
                return res.equalsIgnoreCase("y");
            } else {
                System.out.println("Rejouer ? (Y/N)");
            }
        }
    }

    /**
     * prints how many games the player has won
     * @param nombreParties number of games the player has won
     * @param nomJoueur name of the player
     */
    public void partieGagnerJoueur(int nombreParties, String nomJoueur){
        System.out.println("\n\u001B[42m" + "\u001B[30m " + nomJoueur + " a gagné " + nombreParties + " parties " + "\u001B[0m");
    }

    /**
     * prints which player has won the most games
     * @param joueur the name of the player
     */
    public void endVictory(String joueur){
        System.out.println("\n\u001B[42m" + "\u001B[30m" + " le vainqueur est : " + joueur + " \u001B[0m");
    }

    /**
     * print "ex aequo" because the two players have the same score
     */
    public void endExeaquo(){
        System.out.println("\n\u001B[43m" + "\u001B[30m " + " ex aequo " + "\u001B[0m");
    }

    /**
     * checks if the number of Heap is valid
     * @param nombre user input
     * @return number of Heap if valid, -1 otherwise
     */
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
        return res >= 1 ? res : -1;
    }
}
