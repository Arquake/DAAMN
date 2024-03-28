package project;

import project.Controllers.AbstractController;
import project.Controllers.ControleurJeuNim;
import project.Controllers.ControleurPuissanceQuatre;
import project.Views.AbstractView;
import project.Views.IhmNim;
import project.Views.IhmPuissance;

/**
 * main class
 */
public class Main {
    /**
     * starts the game
     * @param args args
     */
    public static void main(String[] args) {
        AbstractView ihm = new IhmPuissance();
        AbstractController controller = new ControleurPuissanceQuatre(ihm);
        controller.jouer();
    }
}

