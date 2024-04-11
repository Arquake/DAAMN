package project.Controllers;


import project.Models.Exception.InvalidColumException;
import project.Models.Exception.NombreRotationMaximumAtteintException;
import project.Models.Exception.RotationInactiveException;
import project.Models.Joueur;
import project.Models.PlateauPuissance;
import project.Views.AbstractIhm;
import project.Views.IhmPuissance;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Controller for the Puissance 4
 */
public class ControleurPuissanceQuatre extends AbstractController {

    private PlateauPuissance jeu;
    private final IhmPuissance ihm;

    /**
     * Initialize the game
     *
     * @param ihm the Ihm ( View )
     */
    public ControleurPuissanceQuatre(AbstractIhm ihm) {
        super.setIhm(ihm);
        super.createPlayers();
        this.ihm = (IhmPuissance) super.getIhm();
        jeu = new PlateauPuissance();
    }

    /**
     * game loops until one player wins
     * makes the actual heap
     * initializes game with joueur 0
     */

    protected void playGame() {
        String rotation = ihm.demanderRotation();
        boolean isRotationActive = rotation.equalsIgnoreCase("Y");

        jeu.setNombreRotation(joueurs);

        int playerTurn = 0;

        jeu = new PlateauPuissance();
        ihm.afficherPlateau(jeu.toString());

        String coup;
        // Game loop
        while (true) {
            // Ask the current player for their move
            coup = ihm.demanderCoup(joueurs[playerTurn].getNom());
            try {
                jeu.gestionCoup(coup, joueurs, playerTurn, isRotationActive);

                if (jeu.checkWin() != -1) {
                    joueurs[playerTurn].increaseScore();
                    ihm.victory(joueurs[playerTurn].getNom(), jeu.toString());
                    break;
                }
                if (jeu.boardCompleted()) {
                    ihm.noWinBoardFull(jeu.toString());
                    break;
                }
                ihm.afficherPlateau(jeu.toString());
                // If the move was successful, update the next player
                playerTurn = (playerTurn + 1) % 2;
            }
            catch (NombreRotationMaximumAtteintException e) {
                ihm.afficherErreur(e.getMessage());
            } catch (RotationInactiveException e) {
                ihm.afficherErreur(e.getMessage());
            } catch (InvalidColumException e){
                ihm.afficherErreur(e.getMessage());
            }
        }
    // Announce the winner and update their score
    }
}