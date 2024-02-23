package project.Nim;

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
        System.out.print("Nom du joueur "+Integer.toString(i)+" : ");
        return scanner.nextLine();
    }

    public void invalidData() {
        System.out.println("\u001B[41m" + "\u001B[30m" + " les données fournit sont invalides " + "\u001B[0m");
    }

    public void victory(String nomJoueur) {
        System.out.println(nomJoueur + " a gagné");
    }

    public void egalite(){
        System.out.println("égalité");
    }

    public String replay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("rejouer ? (Y/N)");
        return scanner.nextLine();
    }

    public String afficher(String matchSets, String playerName) {
        System.out.println(matchSets + playerName + " : à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.\n");
        return "";
    }

}
