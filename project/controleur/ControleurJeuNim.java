package project.controleur;

import project.modele.Plateau;
import project.modele.Joueur;
import project.vue.Ihm;

/**
 * Contains all methods related to the game's control to link the View and Model
 */
public class ControleurJeuNim {
    private Joueur[] joueurs;

    private final Ihm ihm;

    private int numberOfHeap;

    /**
     * Initialize the game
     * @param ihm the Ihm ( View )
     */
    public ControleurJeuNim(Ihm ihm) {
        this.ihm = ihm;
        createBoard();
        createPlayers();
    }

    /**
     * Starts the game and keeps it running until the player wants to stop
     * uses playGame() to play the actual game
     * after each full game it asks the players if they want to play the game again using the Ihm
     * if they say no then it prints the stats
     */
    public void jouer(){

        boolean running = true;
        while (running){
            playGame();

            running = ihm.replay();
        }
        for (Joueur j: joueurs) {
            ihm.partieGagnerJoueur(j.getScore(),j.getNom());
        }

        if (joueurs[0].compareTo(joueurs[1]) == 0) { ihm.endExeaquo(); }
        else {
            ihm.endVictory(joueurs[0].compareTo(joueurs[1]) > 0 ? joueurs[0].getNom() : joueurs[1].getNom());
        }
    }

    /**
     * game loops until one player wins
     * makes the actual heap
     * initializes game with joueur 0
     *
     */
    private void playGame() {

        Plateau jeu = new Plateau(this.numberOfHeap);
        Joueur dernier_joueur = joueurs[0];
        int playerTurn = 0;

        // Game loop
        while (!jeu.isEmpty()) {
            // Ask the current player for their move
            int[] coup = ihm.demanderCoup(jeu.toString(), joueurs[playerTurn].getNom());


            try {
                jeu.removeMatches(coup);
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
     * creates two player and stores them in this.joueurs Array
     */
    private void createPlayers(){
        this.joueurs = new Joueur[2];
        for (int i = 0; i < 2; i++) {        // creating the players
            this.joueurs[i] = new Joueur(ihm.creerJoueur(i));
        }
    }

    /**
     * creates the board game
     * calls the ihm to make the board
     * and then checks if the heap number is possible
     * if invalid then it calls the ihm again to show the error message
     */
    private void createBoard(){
        do {
            this.numberOfHeap = ihm.creerJeu();
        } while (this.numberOfHeap < 1); // if numberOfHeap < 1 we can't create a heap out of it

    }
}
