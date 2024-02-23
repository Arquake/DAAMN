package project.Nim;

public class NimController {
    private int[][] jeu;
    private Joueur[] joueurs;

    public NimController() {
        this.jeu = jeu;
        this.joueurs = joueurs;
    }

    /**
     * @author Matteo
     */
    public void jouer(){

        NimModele modele = new NimModele();

        NimView view = new NimView();

        Heap test = new Heap(10);

        view.afficher( test.toString(), "jhon");

        System.out.println(modele.verifierCoup("5 5",new int[10][10]));

        while (true) {
            break;
        }
    }

}
