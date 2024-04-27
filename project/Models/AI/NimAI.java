package project.Models.AI;

import project.Models.AbstractPlateau;
import project.Models.Exception.CoupException;
import project.Models.Exception.HeapNumberException;
import project.Models.Exception.MatchesNumberException;
import project.Models.Exception.NotEnoughMatchesException;
import project.Models.PlateauNim;

public class NimAI extends AbstractAI {

    /**
     * instantiate the AI
     *
     * @param jeu
     */
    public NimAI(AbstractPlateau jeu) {
        super(jeu);
    }

    @Override
    public void makeMove() {
        System.out.println("AI is playing");
        int[] plateau= ((PlateauNim)jeu).getPlateau();
        int resultatXOR = 0;
        int[] coup = new int[]{0,0};
        for (int i= 0; i< plateau.length; i++){
            resultatXOR = resultatXOR ^ plateau[i];
        }
        System.out.println("resultatXOR = " + resultatXOR);
        if (resultatXOR == 0){
            for (int i= 0; i< plateau.length; i++){
                if (plateau[i] >0){
                    coup[0] = i+1;
                    coup[1] = 1;
                    break;
                }
            }
        } else {
            for (int i= 0; i< plateau.length; i++){
                int resultat = plateau[i] ^ resultatXOR;
                if (resultat < plateau[i]){
                    coup[0] = i+1;
                    coup[1] = plateau[i] - resultat;
                    break;
                }
            }
        }
        try {
            ((PlateauNim)jeu).jouerCoup(coup);
        } catch (HeapNumberException | MatchesNumberException | CoupException | NotEnoughMatchesException e) {
            throw new RuntimeException(e);
        }
        return;
    }

}
