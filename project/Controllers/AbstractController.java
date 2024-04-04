package project.Controllers;

import project.Models.Joueur;
import project.Views.AbstractIhm;

public abstract class AbstractController {

    protected Joueur[] joueurs;

    AbstractIhm ihm;

    public void jouer(){
        do {
            playGame();

        } while (ihm.replay());
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
     * creates two player and stores them in this.joueurs Array
     */
    protected void createPlayers(){
        this.joueurs = new Joueur[2];
        for (int i = 0; i < 2; i++) {        // creating the players
            this.joueurs[i] = new Joueur(ihm.creerJoueur(i+1));
        }
    }

    protected void setIhm(AbstractIhm ihm){this.ihm = ihm;};

    protected AbstractIhm getIhm(){return this.ihm;}

    protected abstract void playGame();
}
