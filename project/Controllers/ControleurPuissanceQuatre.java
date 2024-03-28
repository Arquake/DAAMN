package project.Controllers;


import project.Models.PlateauPuissance;
import project.Views.AbstractView;
import project.Views.IhmPuissance;

public class ControleurPuissanceQuatre extends AbstractController {

    private PlateauPuissance jeu;

    /**
     * Initialize the game
     *
     * @param ihm the Ihm ( View )
     */
    public ControleurPuissanceQuatre(AbstractView ihm) {
        super.setIhm(ihm);
        super.createPlayers();

    }

    /**
     * game loops until one player wins
     * makes the actual heap
     * initializes game with joueur 0
     */

    protected void playGame() {
        int playerTurn = 0;
        IhmPuissance ihm = (IhmPuissance) super.getIhm();

        jeu = new PlateauPuissance();
        ihm.afficherPlateau(jeu.toString());

        int coup;
        // Game loop
        while (true) {
                // Ask the current player for their move
            coup = ihm.demanderCoup(joueurs[playerTurn].getNom());

            try {
                jeu.jouerCoup(new int[]{coup - 1, playerTurn + 1});
                if (jeu.checkWin() != -1) {
                    joueurs[playerTurn].increaseScore();
                    ihm.victory(joueurs[playerTurn].getNom(), jeu.toString());
                    break;
                }
                if (jeu.boardCompleted()) {
                    ihm.noWinBoardFull(jeu.toString());
                    break;
                }
                // If the move was successful, update the next player
                playerTurn = (playerTurn + 1) % 2;
            }
            catch (invalidColumnException e){
                ihm.invalidData();
            }
        }
        // Announce the winner and update their score
    }
}