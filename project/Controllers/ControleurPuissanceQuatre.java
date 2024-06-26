package project.Controllers;


import project.Models.AI.AbstractAI;
import project.Models.AI.OptiPuissanceAI;
import project.Models.AI.SimplePuissanceAI;
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

    public PlateauPuissance jeu;

    private boolean useRotations;


    /**
     * Initialize the game
     *
     * @param ihm the Ihm ( View )
     */
    public ControleurPuissanceQuatre(AbstractIhm ihm, boolean aiPlayer) {
        boardInit( ihm, aiPlayer);
    }


    @Override
    void createConstraint() {
        jeu = new PlateauPuissance();
        String rotation = ((IhmPuissance) super.getIhm()).demanderRotation();
        jeu.setRotationActive(rotation.equalsIgnoreCase("Y"));
        if (rotation.equalsIgnoreCase("Y")) {
            jeu.setNombreRotation();
            useRotations = true;
        } else {
            useRotations = false;
        }
        if (super.getContainsAiPlayer()){
            super.createAi(getAIType());
        }

        ihm.afficherPlateau(jeu.toString());
    }


    @Override
    void handleWin() {
        if (jeu.checkWin() != -1) {
            joueurs[playerTurn].increaseScore();
            ihm.afficherPlateau(jeu.toString());
            (super.getIhm()).victory(joueurs[playerTurn].getNom());
            running = false;
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
            int[] coup;
            if (useRotations) {
                ((SimplePuissanceAI) joueurs[playerTurn]).setBoard(jeu);
                coup = ((SimplePuissanceAI) joueurs[playerTurn]).makeMove();
            } else {
                ((OptiPuissanceAI) joueurs[playerTurn]).setBoard(jeu);
                coup = ((OptiPuissanceAI) joueurs[playerTurn]).makeMove();
            }

            ((IhmPuissance)ihm).coupIa(coup[0]);
            return;
        }
        // Ask the current player for their move
        String coup = ((IhmPuissance) super.getIhm()).demanderCoup(joueurs[playerTurn].getNom(),useRotations);
        try {
            jeu.gestionCoup(coup, playerTurn);
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

    @Override
    void createBoard() {
        jeu = new PlateauPuissance();
    }

    @Override
    AbstractAI getAIType() {
        if (useRotations) {
            return new SimplePuissanceAI(jeu);
        }
        return new OptiPuissanceAI(jeu);
    }
}