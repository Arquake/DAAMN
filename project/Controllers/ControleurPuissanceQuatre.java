package project.Controllers;


import project.Models.AI.PuissanceAI;
import project.Models.Exception.InvalidColumException;
import project.Models.Exception.NombreRotationMaximumAtteintException;
import project.Models.Exception.RotationInactiveException;
import project.Models.PlateauPuissance;
import project.Views.AbstractIhm;
import project.Views.IhmPuissance;


/**
 * Controller for the Puissance 4
 */
public class ControleurPuissanceQuatre extends AbstractController {

    private PlateauPuissance jeu;


    /**
     * Initialize the game
     *
     * @param ihm the Ihm ( View )
     */
    public ControleurPuissanceQuatre(AbstractIhm ihm, boolean aiPlayer) {
        super.setIhm(ihm);
        if (aiPlayer) {
            super.createAi(new PuissanceAI(jeu));
        }
        super.createPlayers();
        jeu = new PlateauPuissance();
        if (aiPlayer) {
            super.setBoardAi(jeu);
        }
    }


    @Override
    void createConstraint() {
        jeu = new PlateauPuissance();
        String rotation = ((IhmPuissance) super.getIhm()).demanderRotation();
        jeu.setRotationActive(rotation.equalsIgnoreCase("Y"));
        if (rotation.equalsIgnoreCase("Y")) {jeu.setNombreRotation(joueurs);}
        ihm.afficherPlateau(jeu.toString());
    }


    @Override
    void handleWin() {
        if (jeu.checkWin() != -1) {
            joueurs[playerTurn].increaseScore();
            ((IhmPuissance) super.getIhm()).victory(joueurs[playerTurn].getNom(), jeu.toString());
            running = false;
            ihm.afficherPlateau(jeu.toString());
        }
        else if (jeu.boardCompleted()) {
            ((IhmPuissance) super.getIhm()).noWinBoardFull(jeu.toString());
            running = false;
            ihm.afficherPlateau(jeu.toString());
        }
    }


    @Override
    void nextTurn() {
        if (running && validLastMove) {
            // If the move was successful, update the next player
            playerTurn = (playerTurn + 1) % 2;
            ihm.afficherPlateau(jeu.toString());
        }
    }


    @Override
    void manageMove() {
        // if the current player is a bot
        if (!joueurs[playerTurn].isHuman()) {
            ((PuissanceAI) joueurs[playerTurn]).makeMove();
            return;
        }
        // Ask the current player for their move
        String coup = ((IhmPuissance) super.getIhm()).demanderCoup(joueurs[playerTurn].getNom());
        try {
            jeu.gestionCoup(coup, joueurs, playerTurn);
            validLastMove = true;
        } catch (RotationInactiveException e) {
            ihm.afficherErreur(e.getMessage());
            validLastMove = false;
        } catch (InvalidColumException e) {
            ihm.afficherErreur(e.getMessage());
            validLastMove = false;
        } catch (NombreRotationMaximumAtteintException e) {
            ihm.afficherErreur(e.getMessage());
            validLastMove = false;
        }
    }
}