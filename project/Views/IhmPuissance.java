package project.Views;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IhmPuissance extends AbstractIhm {

    /**
     * Ask the player to play on a column
     *
     * @param playerName Player asked
     * @return Column played
     */
    public String demanderCoup(String playerName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(playerName + " à vous de jouer ! Indiquez le numéro de la colonne visée ou H/A pour tourner la grille dans le sens Horaire ou Anti-Horaire. \nVotre coup : ");
        return scanner.next();
    }

    /**
     * Demandes aux joueurs s'ils veulent activer les rotations pour leur partie
     * @return La reponse, Y ou N, respectivement oui ou non
     */
    public String demanderRotation(){
        System.out.print("Souhaitez vous jouer avec les rotations ? (Y/N)");
        Scanner sc = new Scanner(System.in);
        String rep = null;

        List<String> listeRep = new ArrayList<>(Arrays.asList("N","Y"));

        while (sc.hasNext()) {
            rep = sc.next();
            if (listeRep.contains(rep.toUpperCase())) {
                break;
            } else {
                sc = new Scanner(System.in);
            }
        }
        return rep;
    }




    /**
     * prints which player have won
     * @param nomJoueur player's name
     * @param plateau the string of the current board
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

    /**
     * Affiche le coup joué par l'IA
     * @param coup Coup joué par l'ia.
     */
    public void coupIa (int coup){
        if (coup == -1){
            System.out.println("L'IA a efféctué une rotation.");
        } else {
            System.out.println("L'IA a joué en "+(coup+1)+".");
        }
    }
}
