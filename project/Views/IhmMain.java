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
        System.out.print("A quel jeu souhaitez vous jouer ? N : Nim, P : Puissance 4");
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
        }
        return rep;
    }
}
