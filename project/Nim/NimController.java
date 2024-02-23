package project.Nim;

public class NimController {
    private Joueur[] joueurs;

    private NimModele model = new NimModele();

    private NimView view = new NimView();

    private int numberOfHeap;

    public NimController() {
        createPlayers();
        createBoard();
    }

    /**
     * @author Matteo Nicolas Amin
     */
    public void jouer(){

        boolean running = true;
        while (running){
            playGame();

            running = startNewGame();
        }
    }

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    private void playGame() {

        Heap jeu = new Heap(this.numberOfHeap);

        int playerTurn = 0;

        // game loop
        while ( ! model.gameFinished( jeu ) ){

            String coup = view.demanderCoup( jeu.toString(), joueurs[playerTurn].getNom() );

            if ( model.verifierCoup( coup , jeu ) ) {

                playerTurn = (playerTurn + 1) % 2;

                if ( model.jouerCoup(coup,jeu) ) {

                    joueurs[playerTurn].increaseLastMatches();

                }

            } else {

                view.invalidData();

            }

        }
        switch (model.winner(joueurs[0].compareTo(joueurs[1]))){
            case 1 : view.victory(joueurs[0].getNom());break;
            case -1: view.victory(joueurs[1].getNom());break;
            case 0:view.egalite();break;

        }

    }



    private void createPlayers(){
        this.joueurs = new Joueur[2];
        // creating the players
        for (int i = 0; i < 2; i++) {
            this.joueurs[i] = new Joueur(view.creerJoueur(i));
        }
    }

    private void createBoard(){
        // creating the game board
        while (true){
            this.numberOfHeap =  model.verifierCreationJeu( view.creerJeu() );
            // if numberOfHeap < 1 we can't create a heap out of it
            if ( this.numberOfHeap > 1 ) {
                break;
            }
            view.invalidData();
        }
    }

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
