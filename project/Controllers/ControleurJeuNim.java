package project.Controllers;

import project.Models.PlateauNim;
import project.Models.Joueur;
import project.Views.AbstractView;
import project.Views.IhmNim;

/**
 * Contains all methods related to the game's control to link the View and Model
 */
public class ControleurJeuNim extends AbstractController {

    private int numberOfHeap;

    /**
     * Initialize the game
     * @param ihm the Ihm ( View )
     */
    public ControleurJeuNim(AbstractView ihm) {
        super.setIhm(ihm);
        createBoard();
        super.createPlayers();
    }

    /**
     * game loops until one player wins
     * makes the actual heap
     * initializes game with joueur 0
     */
    protected void playGame() {
        IhmNim ihm = (IhmNim) super.getIhm();
        PlateauNim jeu = new PlateauNim(this.numberOfHeap);
        Joueur dernier_joueur = joueurs[0];
        int playerTurn = 0;

        // Game loop
        while (!jeu.boardCompleted()) {
            // Ask the current player for their move

            try {
                int[] coup = ihm.demanderCoup(jeu.toString(), joueurs[playerTurn].getNom());

                jeu.jouerCoup(coup);
                dernier_joueur = joueurs[playerTurn];
                playerTurn = (playerTurn + 1) % 2;
            } catch (Exception e) {
                ihm.invalidData();
            }
        }
        // Announce the winner and update their score
        ihm.victory(dernier_joueur.getNom());
        dernier_joueur.increaseScore();
    }





    /**
     * creates the board game
     * calls the ihm to make the board
     * and then checks if the heap number is possible
     * if invalid then it calls the ihm again to show the error message
     */
    private void createBoard(){
        do {
            this.numberOfHeap = ((IhmNim) super.getIhm()).creerJeu();
        } while (this.numberOfHeap < 1); // if numberOfHeap < 1 we can't create a heap out of it

    }
}
