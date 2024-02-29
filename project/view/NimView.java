package project.view;

import java.util.Scanner;

public class NimView {

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
    public String creerJeu(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre de tas : ");
        return scanner.nextLine();
    }


    public String creerJoueur(int i){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nom du joueur "+i+" : ");
        return scanner.nextLine();
    }

    public void invalidData() {
        System.out.println("\u001B[41m" + "\u001B[30m" + " les données fournit sont invalides " + "\u001B[0m");
    }

    public void victory(String nomJoueur) {
        System.out.println("\u001B[42m" + "\u001B[30m " + nomJoueur + " a gagné " + "\u001B[0m");
    }

    public String replay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("rejouer ? (Y/N)");
        return scanner.nextLine();
    }

    public void partieGagnerJoueur(int nombreParties, String nomJoueur){
        System.out.println("\n\u001B[42m" + "\u001B[30m " + nomJoueur + " a gagné " + nombreParties + " parties " + "\u001B[0m");
    }

    public void endVictory(String joueur){
        System.out.println("\n\u001B[42m" + "\u001B[30m" + " le vainqueur est : " + joueur + " \u001B[0m");
    }

    public void endExeaquo(){
        System.out.println("\n\u001B[43m" + "\u001B[30m " + " ex aequo " + "\u001B[0m");
    }

}
