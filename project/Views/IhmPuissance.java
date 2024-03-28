package project.Views;



import project.Models.Exception.invalidColumException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IhmPuissance extends AbstractView {

    /**
     * Ask the player to play on a column
     * @param playerName Player asked
     * @return Column played
     */
    public int demanderCoup(String playerName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(playerName + " à vous de jouer ! Indiquez le numéro de la colone visée. \nVotre coup : ");

        int coup = -1;
        while (scanner.hasNext()) {
            if ( scanner.hasNextInt()) {
                coup = scanner.nextInt();
                break;
            } else {
                scanner = new Scanner(System.in);
            }
        }
        return coup;
    }

    /**
     * Affiche le plateau de jeu dans la console
     * @param jeu le string du plateau
     */


    /**
     * prints which player have won
     * @param nomJoueur player's name
     */
    public void victory(String nomJoueur, String plateau) {
        System.out.println(plateau + "\n\u001B[42m" + "\u001B[30m " + nomJoueur + " a gagné !\u001B[0m");
    }

    /**
     * Print when the board is full and what follows it
     * @param jeu Board showed
     */
    public void noWinBoardFull(String jeu){
        System.out.println(jeu + "\n\u001B[43m" + "\u001B[30m " + "La grille est complête et personne n'a aligné 4 jetons ! C'est un match nul. \u001B[0m");
    }
}
