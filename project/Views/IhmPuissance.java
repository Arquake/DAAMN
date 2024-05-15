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
    public String demanderCoup(String playerName, boolean rotation) {
        Scanner scanner = new Scanner(System.in);
        if (rotation) {
            System.out.println(playerName + " à vous de jouer ! Indiquez le numéro de la colonne visée ou H/A pour tourner la grille dans le sens Horaire ou Anti-Horaire. \nVotre coup : ");
        } else {
            System.out.println(playerName + " à vous de jouer ! Indiquez le numéro de la colonne visée.\nVotre coup : ");
        }
        return scanner.next();
    }

    /**
     * Demandes aux joueurs s'ils veulent activer les rotations pour leur partie
     * @return La reponse, Y ou N, respectivement oui ou non
     */
    public String demanderRotation(){
        System.out.print("Souhaitez vous jouer avec les rotations ? (Y : oui | N : non)\nVotre choix :");
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
