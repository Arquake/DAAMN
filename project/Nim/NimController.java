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

        System.out.println(modele.verifierCoup("5 5",new int[10][10]));

        while (true) {
            break;
        }
    }
}
