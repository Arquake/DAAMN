package project.Views;

import java.util.Scanner;

public abstract class AbstractView {


    /**
     * receives user player name
     * @param i which player it is making right now
     * @return the player name to the controller
     */
    public String creerJoueur(int i){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nom du joueur "+(i+1)+" : ");
        return scanner.nextLine();
    }


    /**
     * prints to the screen if data is invalid
     */
    public void invalidData() {
        System.out.println("\u001B[41m" + "\u001B[30m" + " Les données fournit sont invalides " + "\u001B[0m");
    }


    /**
     * prints which player have won
     * @param nomJoueur player's name
     */
    public void victory(String nomJoueur) {
        System.out.println("\n\u001B[42m" + "\u001B[30m " + nomJoueur + " a gagné !\u001B[0m");
    }


    /**
     * asks the player if they want to replay
     * @author Matteo
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
        System.out.println("\n\u001B[42m" + "\u001B[30m" + " Le vainqueur est : " + joueur + " ! \u001B[0m");
    }

    /**
     * print "ex aequo" because the two players have the same score
     */
    public void endExeaquo(){
        System.out.println("\n\u001B[43m" + "\u001B[30m" + " Vous êtes ex aequo ! " + "\u001B[0m");
    }

    /**
     *
     */
    abstract void AfficherPlateau(String plateau);
}
