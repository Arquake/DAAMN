package project.Views;



import project.Models.Exception.invalidColumException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IhmPuissance extends AbstractView {

    /**
     * Ask the player to play on a column
     * @param plateau Board of the game
     * @param playerName Player asked
     * @return a toi de jouer !
     */
    public int demanderCoup(String plateau, String playerName, Boolean erreur) throws invalidColumException {
        Scanner scanner = new Scanner(System.in);
        if (erreur){
            System.out.println(playerName + " c'est encore à vous ! Indiquez le numéro (entre 1 et 7) de la colone visée. \nVotre coup : ");
        } else {
            System.out.println(plateau + playerName + " à vous de jouer ! Indiquez le numéro de la colone visée. \nVotre coup : ");
        }
        String coup = scanner.nextLine();
        try{
            int val = Integer.parseInt(coup);
            if ( val < 1 || val > 7) {throw new invalidColumException();}
            return val;
        } catch (Exception e) {
            throw new invalidColumException();
        }

    }

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

    public void AfficherPlateau(String plateau){
        // afficher plateau
    }
}
