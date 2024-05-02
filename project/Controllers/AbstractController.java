package project.Controllers;

import project.Models.AI.AbstractAI;
import project.Models.AbstractPlateau;
import project.Models.AbstractPlayer;
import project.Models.Joueur;
import project.Views.AbstractIhm;

public abstract class AbstractController {

    AbstractPlateau jeu;
    /**
     * if at least one player is an AI
     */
    private boolean containsAiPlayer;

    /**
     * if the last move made by a player was valid or not
     */
    protected boolean validLastMove = false;

    /**
     * true if the game is running false if it has to stop
     */
    protected boolean running = false;

    /**
     * current player turn
     */
    protected int playerTurn;

    /**
     * array of all the players in the game
     */
    protected AbstractPlayer[] joueurs = new AbstractPlayer[2];

    /**
     * ihm to use during the game
     */
    AbstractIhm ihm;

    /**
     * method to start any game
     * It will start the game and when it stops show the winner and how many game each player Won
     */
    public void jouer(){
        do {
            playGame();

        } while (ihm.replay());
        for (AbstractPlayer j : joueurs) {
            ihm.partieGagnerJoueur(j.getScore(), j.getNom());
        }

        if (joueurs[0].compareTo(joueurs[1]) == 0) {
            ihm.endExeaquo();
        } else {
            ihm.endVictory(joueurs[0].compareTo(joueurs[1]) > 0 ? joueurs[0].getNom() : joueurs[1].getNom());
        }
    }

    /**
     * creates two player and stores them in this.joueurs Array
     */
    protected void createPlayers(){
        this.joueurs[0] = new Joueur(ihm.creerJoueur(1));
        if (!containsAiPlayer) {this.joueurs[1] = new Joueur(ihm.creerJoueur(2));}
    }

    /**
     * method to set an ihm for the game
     * @param ihm the ihm that will be used
     */
    protected void setIhm(AbstractIhm ihm){this.ihm = ihm;}

    /**
     * get the ihm that have been created
     * @return the Ihm
     */
    protected AbstractIhm getIhm(){return this.ihm;}

    /**
     * method to create constraint in the game
     */
    abstract void createConstraint();

    /**
     * method to handle win or stalemate in the game
     */
    abstract void handleWin();

    /**
     * method to go the next person's turn
     */
    abstract void nextTurn();

    /**
     * method to manage moves
     */
    abstract void manageMove();

    /**
     * method to play a game
     */
    public void playGame() {

        playerTurn = 0;
        createConstraint();
        running = true;
        // Game loop
        // Boucle de jeu
        while (running) {
            manageMove();
            handleWin();
            nextTurn();
        }
    }

    void createAi(AbstractAI ai){
        containsAiPlayer = true;
        this.joueurs[1] = ai;
    }

    void setBoardAi(){
        ((AbstractAI)this.joueurs[1]).setBoard(jeu);
    }

    abstract void createBoard();

    abstract AbstractAI createAI();

    protected void boardInit(AbstractIhm ihm, boolean aiPlayer){
        setIhm(ihm);
        if (aiPlayer) {
            createAi(createAI());
        }
        createPlayers();
        createBoard();
        if (aiPlayer) {
            setBoardAi();
        }
    }
}
