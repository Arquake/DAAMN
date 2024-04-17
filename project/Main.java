package project;

import project.Controllers.AbstractController;
import project.Controllers.ControleurJeuNim;
import project.Controllers.ControleurPuissanceQuatre;
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

        /**
         * Methode pour IA ou HUMAIN
         */
        boolean bot = true;
        /**
         * à implémenter
         */

        if (jeu.equalsIgnoreCase("N")){
            AbstractIhm ihm = new IhmNim();
            AbstractController controller = new ControleurJeuNim(ihm, bot);
            controller.jouer();
        } else {
            AbstractIhm ihm = new IhmPuissance();
            AbstractController controller = new ControleurPuissanceQuatre(ihm, bot);
            controller.jouer();
        }
    }
}

