package project;

import project.Controllers.AbstractController;
import project.Controllers.ControleurJeuNim;
import project.Controllers.ControleurPuissanceQuatre;
import project.Models.PlateauPuissance;
import project.Views.AbstractIhm;
import project.Views.IhmMain;
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

        IhmMain ihmMain = new IhmMain();
        String jeu = ihmMain.demanderJeu();

        AbstractController controller;

        boolean bot = ihmMain.askAI();

        if (jeu.equalsIgnoreCase("N")){
            AbstractIhm ihm = new IhmNim();
            controller = new ControleurJeuNim(ihm, bot);

        } else {
            AbstractIhm ihm = new IhmPuissance();
            controller = new ControleurPuissanceQuatre(ihm, bot);
        }
        controller.jouer();
    }
}

