package project.controleur;

import project.modele.NimModele;
import project.vue.Ihm;

/**
 * Contains all methods related to the game's control to link the View and Model
 */
public class ControleurJeuNim {
    private Joueur[] joueurs;

    private final NimModele model = new NimModele();

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
     * Start the game and keep it running until the player wants to stop
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
     * game loop until one player win
     */
    private void playGame() {

        Heap jeu = new Heap(this.numberOfHeap);

        Joueur dernier_joueur = joueurs[0];

        int playerTurn = 0;

        // game loop
        while ( ! jeu.isEmpty() ){

            String coup = ihm.demanderCoup( jeu.toString(), joueurs[playerTurn].getNom() );

            if ( model.verifierCoup( coup , jeu ) ) {

                playerTurn = (playerTurn + 1) % 2;

                if ( model.jouerCoup(coup,jeu) ) {

                    dernier_joueur = joueurs[playerTurn];

                }

            } else {

                ihm.invalidData();

            }

        }
        ihm.victory(dernier_joueur.getNom());
        dernier_joueur.increaseScore();
    }


    /**
     * create two player and store them in this.joueurs Array
     */
    private void createPlayers(){
        this.joueurs = new Joueur[2];
        // creating the players
        for (int i = 0; i < 2; i++) {
            this.joueurs[i] = new Joueur(ihm.creerJoueur(i));
        }
    }

    /**
     * create the board game
     */
    private void createBoard(){
        while (true){
            this.numberOfHeap =  ihm.creerJeu();
            // if numberOfHeap < 1 we can't create a heap out of it
            if ( this.numberOfHeap > 1 ) {
                break;
            }
            ihm.invalidData();
        }
    }
}
