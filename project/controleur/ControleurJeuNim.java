package project.controleur;

import project.modele.NimModele;
import project.vue.Ihm;

public class ControleurJeuNim {
    private Joueur[] joueurs;

    private final NimModele model = new NimModele();

    private final Ihm view = new Ihm();

    private int numberOfHeap;
    private Joueur dernier_joueur;

    /**
     * Initialize the game
     */
    public ControleurJeuNim(Ihm ihm) {
        createBoard();
        createPlayers();
    }

    /**
     * @author Matteo Nicolas Amin
     * Start the game and keep it running until the player wants to stop
     */
    public void jouer(){

        boolean running = true;
        while (running){
            playGame();

            running = startNewGame();
        }
        for (Joueur j: joueurs) {
            view.partieGagnerJoueur(j.getScore(),j.getNom());
        }

        if (joueurs[0].getScore() == joueurs[1].getScore()) { view.endExeaquo(); }
        else {
            view.endVictory(model.isBetter(joueurs[0], joueurs[1]));
        }
    }

    /**
     * game loop until one player win
     */
    private void playGame() {

        Heap jeu = new Heap(this.numberOfHeap);

        dernier_joueur = joueurs[0];
        int playerTurn = 0;

        // game loop
        while ( ! model.gameFinished( jeu ) ){

            String coup = view.demanderCoup( jeu.toString(), joueurs[playerTurn].getNom() );

            if ( model.verifierCoup( coup , jeu ) ) {

                playerTurn = (playerTurn + 1) % 2;

                if ( model.jouerCoup(coup,jeu) ) {

                    dernier_joueur= joueurs[playerTurn];

                }

            } else {

                view.invalidData();

            }

        }
        view.victory(dernier_joueur.getNom());dernier_joueur.increaseScore();
    }


    /**
     * create two player and store them in this.joueurs Array
     */
    private void createPlayers(){
        this.joueurs = new Joueur[2];
        // creating the players
        for (int i = 0; i < 2; i++) {
            this.joueurs[i] = new Joueur(view.creerJoueur(i));
        }
    }

    /**
     * create the board game
     */
    private void createBoard(){
        while (true){
            this.numberOfHeap =  model.verifierCreationJeu( view.creerJeu() );
            // if numberOfHeap < 1 we can't create a heap out of it
            if ( this.numberOfHeap > 1 ) {
                break;
            }
            view.invalidData();
        }
    }

    /**
     * create a new game
     * @return the choice if the player wants to replay
     */
    private boolean startNewGame(){
        boolean running;
        String response = "";
        response = view.replay();
        while (!model.replayValidResponse(response)){
            response = view.replay();
        }
        return model.isReplaying(response);
    }
}
