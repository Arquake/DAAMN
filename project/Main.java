package project;

import project.controleur.ControleurJeuNim;
import project.vue.Ihm;

/**
 * main class
 */
public class Main {
    /**
     * start the game
     * @param args args
     */
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        ControleurJeuNim controleurJeuNim = new ControleurJeuNim(ihm);
        controleurJeuNim.jouer();
    }
}
