package project;

import project.Controllers.controleurNim.ControleurJeuNim;
import project.Views.IhmNim;

/**
 * main class
 */
public class Main {
    /**
     * starts the game
     * @param args args
     */
    public static void main(String[] args) {
        IhmNim ihm = new IhmNim();
        ControleurJeuNim controleurJeuNim = new ControleurJeuNim(ihm);
        controleurJeuNim.jouer();
    }
}

