package project.Controllers;

import project.Models.PlateauNim;
import project.Models.Joueur;
import project.Views.AbstractView;
import project.Views.IhmNim;

/**
 * Contains all methods related to the game's control to link the View and Model
 * contient toutes les méthodes liées au contrôle du jeu pour lier la vue et le modèle
 */
public class ControleurJeuNim extends AbstractController {

    private int numberOfHeap;

    /**
     * Initialize the game
     * initialise le jeu
     * @param ihm the Ihm ( View )
     *            la vue
     */
    public ControleurJeuNim(AbstractView ihm) {
        super.setIhm(ihm);
        createBoard();
        super.createPlayers();
    }

    /**
     * game loops until one player wins
     * boucle de jeu jusqu'à ce qu'un joueur gagne
     * makes the actual heap
     * fait le tas actuel
     * initializes game with joueur 0
     * initialise le jeu avec le joueur 0
     */
    protected void playGame() {
        IhmNim ihm = (IhmNim) super.getIhm();

        PlateauNim jeu = new PlateauNim(this.numberOfHeap);
        Joueur dernier_joueur = joueurs[0];
        int playerTurn = 0;
        boolean withconst = ihm.demanderContrainte();

        //c'est une solution temporaire pour le moment
        int allumetteMax = 100;
        //c'est une solution temporaire pour le moment

        if (withconst) {
            allumetteMax = ihm.demanderAllumettesMax();
        }
        // Game loop
        // Boucle de jeu
        while (!jeu.boardCompleted()) {
            // Ask the current player for their move
            // Demander au joueur actuel son coup

            try {
                int[] coup = ihm.demanderCoup(jeu.toString(), joueurs[playerTurn].getNom());
                if (allumetteMax < coup[1]) {
                    ihm.invalidData();
                    continue;
                }
                jeu.jouerCoup(coup);
                dernier_joueur = joueurs[playerTurn];
                playerTurn = (playerTurn + 1) % 2;
            } catch (Exception e) {
                ihm.invalidData();
            }
        }
        // Announce the winner and update their score
        // Annoncez le vainqueur et mettez à jour son score
        ihm.victory(dernier_joueur.getNom());
        dernier_joueur.increaseScore();
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
    private void createBoard(){
        do {
            this.numberOfHeap = ((IhmNim) super.getIhm()).creerJeu();
        } while (this.numberOfHeap < 1); // if numberOfHeap < 1 we can't create a heap out of it
                                        // si numberOfHeap < 1, nous ne pouvons pas en créer un tas

    }
}
