package project.Nim;

public class NimController {
    private Joueur[] joueurs;

    private NimModele model = new NimModele();

    private NimView view = new NimView();

    private int numberOfHeap;
    private Joueur dernier_joueur;

    public NimController() {
        createBoard();
        createPlayers();
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
        for (Joueur j: joueurs) {
            view.partieGagnerJoueur(j.getScore(),j.getNom());
        }

        if (joueurs[0].getScore() == joueurs[1].getScore()) { view.endExeaquo(); }
        else {
            view.endVictory(model.isBetter(joueurs[0], joueurs[1]));
        }
    }

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
        view.victory(dernier_joueur.getNom());joueurs[1].increaseScore();
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
        for (Joueur j: joueurs) {
            j.resetLastMatches();
        }
        return model.isReplaying(response);
    }
}
