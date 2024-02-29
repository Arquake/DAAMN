package project.vue;

import java.util.Scanner;

/**
 * Contains all the methods related to the View
 */
public class Ihm {

    /**
     * @author Amin
     * @param playerName the name of the player which is playing at this turn
     * @param matchSets int[][] Array of Heap and the matches in those
     * @return the user input for his move
     */
    public String demanderCoup(String matchSets, String playerName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(matchSets + playerName + " à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.\n coup : ");
        return scanner.nextLine();
    }

    /**
     * @author Nicolas
     * @return string with the user input that should represent the number of heap
     */
    public int creerJeu(){
        int res =-1;
        Scanner scanner = new Scanner("");
        while ( res == -1 ) {
            System.out.print("Nombre de tas : ");
            scanner = new Scanner(System.in);
            res = verifierCreationJeu(scanner.nextLine());
        }

        return res;
    }

    /**
     * receive user player name
     * @param i which player si it
     * @return the player name
     */
    public String creerJoueur(int i){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nom du joueur "+i+" : ");
        return scanner.nextLine();
    }

    /**
     * print to the screen if data is invalid
     * @autor
     */
    public void invalidData() {
        System.out.println("\u001B[41m" + "\u001B[30m" + " les données fournit sont invalides " + "\u001B[0m");
    }

    /**
     * print which player have won
     * @author
     * @param nomJoueur le nom du joueur
     */
    public void victory(String nomJoueur) {
        System.out.println("\u001B[42m" + "\u001B[30m " + nomJoueur + " a gagné " + "\u001B[0m");
    }

    /**
     * ask the player if he wants to replay
     * @author
     * @return the user intput that should be y|Y|n|N
     */
    public boolean replay() {
        Scanner scanner = new Scanner(System.in);
        String res ="";
        System.out.println("rejouer ? (Y/N)");
        scanner.nextLine();
        if (scanner.hasNext()){ res = scanner.next(); }
        System.out.println("1er" + res);
        boolean test = scanner.hasNext() || (!res.equalsIgnoreCase("y") && !res.equalsIgnoreCase("n"));
        while (test){
            System.out.println("rejouer ? (Y/N)");
            scanner.nextLine();
            if (scanner.hasNext()){ res = scanner.next(); }
        }
        return res.equalsIgnoreCase("y");
    }

    /**
     * print how many game the player have won
     * @param nombreParties number of game the player won
     * @param nomJoueur name of the player
     * @author
     */
    public void partieGagnerJoueur(int nombreParties, String nomJoueur){
        System.out.println("\n\u001B[42m" + "\u001B[30m " + nomJoueur + " a gagné " + nombreParties + " parties " + "\u001B[0m");
    }

    /**
     * print which player have won the most games
     * @author
     * @param joueur
     */
    public void endVictory(String joueur){
        System.out.println("\n\u001B[42m" + "\u001B[30m" + " le vainqueur est : " + joueur + " \u001B[0m");
    }

    /**
     * print "ex aequo" because two player have the same score
     * @author
     */
    public void endExeaquo(){
        System.out.println("\n\u001B[43m" + "\u001B[30m " + " ex aequo " + "\u001B[0m");
    }

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
}
