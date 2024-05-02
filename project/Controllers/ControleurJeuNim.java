package project.Controllers;

import project.Models.AI.AbstractAI;
import project.Models.AI.NimAI;
import project.Models.Exception.CoupException;
import project.Models.Exception.HeapNumberException;
import project.Models.Exception.MatchesNumberException;
import project.Models.Exception.NotEnoughMatchesException;
import project.Models.PlateauNim;
import project.Views.AbstractIhm;
import project.Views.IhmNim;

/**
 * Contains all methods related to the game's control to link the View and Model
 * contient toutes les méthodes liées au contrôle du jeu pour lier la vue et le modèle
 */
public class ControleurJeuNim extends AbstractController {

    PlateauNim jeu;

    /**
     * Nombre de tas sur le plateau
     */
    private int numberOfHeap;

    /**
     * Initialize the game
     * Initialize le jeu
     * @param ihm the Ihm ( View )
     *            la vue
     */

    public ControleurJeuNim(AbstractIhm ihm, boolean aiPlayer) {
        boardInit( ihm, aiPlayer);
    }

    /**
     * creates the board game
     * crée le plateau de jeu
     * calls the ihm to make the board
     * appelle l'ihm pour créer le plateau
     * and then checks if the heap number is possible
     * et vérifie ensuite si le nombre de tas est possible
     * if invalid then it calls the ihm again to show the error message
     * si invalide, il appelle à nouveau l'ihm pour afficher le message d'erreur
     */
    void createBoard(){
        do {
            this.numberOfHeap = ((IhmNim) super.getIhm()).creerJeu();
        } while (this.numberOfHeap < 1); // if numberOfHeap < 1 we can't create a heap out of it
            // si numberOfHeap < 1, nous ne pouvons pas en créer un tas
    }

    @Override
    AbstractAI createAI() {
        return new NimAI(jeu);
    }


    @Override
    void createConstraint() {
        int allumetteMax = ((IhmNim) super.getIhm()).demanderContrainte();
        jeu = new PlateauNim(this.numberOfHeap, allumetteMax);
    }


    @Override
    void handleWin() {
        running = !jeu.boardCompleted();
        if (!running){
            // Announce the winner and update their score
            // Annoncez le vainqueur et mettez à jour son score
            ihm.victory(joueurs[playerTurn].getNom());
            joueurs[playerTurn].increaseScore();
        }
    }


    @Override
    void nextTurn() {
        if (running && validLastMove) {
            // If the move was successful, update the next player
            playerTurn = (playerTurn + 1) % 2;
        }
    }


    @Override
    void manageMove() {
        // if the current player is a bot
        if (!joueurs[playerTurn].isHuman()) {
            //ended up setting the jeu over here to make sure it would actually fucking work
            ((NimAI) joueurs[playerTurn]).setBoard(jeu);
            ((NimAI) joueurs[playerTurn]).makeMove();
            return;
        }
        // Ask the current player for their move
        // Demander au joueur actuel son coup
        try {
            ihm.afficherPlateau(jeu.toString());
            int[] coup = ((IhmNim) super.getIhm()).demanderCoup(joueurs[playerTurn].getNom());
            jeu.jouerCoup(coup);
            validLastMove = true;
        } catch (HeapNumberException e) {
            ihm.afficherErreur(e.getMessage());
            validLastMove = false;
        } catch (CoupException e) {
            ihm.afficherErreur(e.getMessage());
            validLastMove = false;
        } catch (MatchesNumberException e) {
            ihm.afficherErreur(e.getMessage());
            validLastMove = false;
        } catch (NotEnoughMatchesException e) {
            ihm.afficherErreur(e.getMessage());
            validLastMove = false;
        }
    }
}
