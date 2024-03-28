package project.Controllers.ControleurPuissance4;


import project.Models.Joueur;
import project.Models.Exception.Plateau;
import project.Views.IhmPuissance;

public class ControleurPuissanceQuatre {
    private Joueur[] joueurs;
    private final IhmPuissance ihm;

    /**
     * Initialize the game
     *
     * @param ihm the Ihm ( View )
     */
    public ControleurPuissanceQuatre(IhmPuissance ihm) {
        this.ihm = ihm;
        creerListeJoueurs();

    }

    /**
     * Starts the game and keeps it running until the player wants to stop
     * uses playGame() to play the actual game
     * after each full game it asks the players if they want to play the game again using the Ihm
     * if they say no then it prints the stats
     */
    public void jouer() {

        boolean running = true;
        while (running) {
            jouerTour();

            running = ihm.replay();
        }
        for (Joueur j : joueurs) {
            ihm.partieGagnerJoueur(j.getScore(), j.getNom());
        }

        if (joueurs[0].compareTo(joueurs[1]) == 0) {
            ihm.endExeaquo();
        } else {
            ihm.endVictory(joueurs[0].compareTo(joueurs[1]) > 0 ? joueurs[0].getNom() : joueurs[1].getNom());
        }
    }

    /**
     * game loops until one player wins
     * makes the actual heap
     * initializes game with joueur 0
     */

    private void jouerTour() {
        int playerTurn = 0;


        Plateau jeu = new Plateau();

        int coup;
        boolean erone = false;
        // Game loop
        while (true) {
            try {
                // Ask the current player for their move
                // Verify and play the move if it's valid

                coup = ihm.demanderCoup(jeu.toString(), joueurs[playerTurn].getNom(), erone);
                erone = false;
                if (jeu.jouerCoup(coup - 1, playerTurn + 1)) {
                    if (jeu.checkWin() != -1) {
                        joueurs[playerTurn].increaseScore();
                        ihm.victory(joueurs[playerTurn].getNom(), jeu.toString());
                        break;
                    }
                    if (jeu.boardIsFull()) {
                        ihm.noWinBoardFull(jeu.toString());
                        break;
                    }
                    // If the move was successful, update the next player
                    playerTurn = (playerTurn + 1) % 2;
                }
            } catch (Exception e) {
                erone = true;
                ihm.invalidData();
            }
        }
        // Announce the winner and update their score
    }


    /**
     * creates two player and stores them in this.joueurs Array
     */
    private void creerListeJoueurs() {
        this.joueurs = new Joueur[2];
        // creating the players
        for (int i = 0; i < 2; i++) {
            this.joueurs[i] = new Joueur(ihm.creerJoueur(i));
        }
    }
}