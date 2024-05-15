package project.Views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IhmMain {

    /**
     * Demande au joueurs à quel jeu ils souhaitent jouer
     * @return La reponse : "N" pour Nim et "P" pour Puissance 4
     */
    public String demanderJeu(){
        System.out.println("A quel jeu souhaitez vous jouer ? (N : Nim, P : Puissance 4)");
        System.out.println("Votre choix : ");
        Scanner sc = new Scanner(System.in);
        String rep = null;

        List<String> listeRep = new ArrayList<>(Arrays.asList("N","P"));

        while (sc.hasNext()) {
            rep = sc.next();
            if (listeRep.contains(rep.toUpperCase())) {
                break;
            } else {
                sc = new Scanner(System.in);
            }
            System.out.println("\u001B[41m" + "\u001B[30m " + "Choix incorrecte veuillez réessayer :" + " \u001B[0m");
        }
        return rep;
    }

    /**
     * ask the player if he wants to play against an AI or not
     * @return true if he wants to play against an AI | false otherwise
     */
    public boolean askAI(){
        System.out.println("Voulez vous jouer contre une IA ? (Y : oui | N : non)\nVotre choix :");
        Scanner sc = new Scanner(System.in);
        String rep = null;

        List<String> listeRep = new ArrayList<>(Arrays.asList("Y","N"));

        while (sc.hasNext()) {
            rep = sc.next();
            if (listeRep.contains(rep.toUpperCase())) {
                break;
            } else {
                sc = new Scanner(System.in);
            }
            System.out.println("\u001B[41m" + "\u001B[30m " + "Choix incorrecte veuillez réessayer :" + " \u001B[0m");
        }
        return rep.equalsIgnoreCase("Y");
    }
}
