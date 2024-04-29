package project.Models.AI;

import project.Models.AbstractPlateau;
import project.Models.Exception.CoupException;
import project.Models.Exception.HeapNumberException;
import project.Models.Exception.MatchesNumberException;
import project.Models.Exception.NotEnoughMatchesException;
import project.Models.PlateauNim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NimAI extends AbstractAI {

    /**
     * instantiate the AI
     *
     * @param jeu board of the game
     */
    public NimAI(AbstractPlateau jeu) {
        super(jeu);
    }

    /**
     * method to get the winning strategy for the AI
     * @return winning strategy
     */
    public int[] getStrategieGagnante(){
        int[] plateau= ((PlateauNim)jeu).getPlateau();
        int resultatXOR = 0;
        int[] coup = new int[2];
        for (int j : plateau) {
            resultatXOR = resultatXOR ^ j;
        }
        if (resultatXOR == 0){
            for (int i= 0; i< plateau.length; i++){
                if (plateau[i] >0){
                    coup[0] = i+1;
                    coup[1] = 1;

                    return coup;

                }
            }
        } else {
            for (int i= 0; i< plateau.length; i++){
                int resultat = plateau[i] ^ resultatXOR;
                if (resultat < plateau[i]){
                    coup[0] = i+1;
                    coup[1] = plateau[i] - resultat;
                    return coup;

                }
            }
        }
        return new int[] { -1, -1 }; // this should never happen
        // feel free to test it out and find bugs in it idc
    }

    public int[] getStrategieAleatoire(){
        int[] plateau= ((PlateauNim)jeu).getPlateau();
        int maxMatches = ((PlateauNim)jeu).getMaxMatches();
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        for (int i= 0; i< plateau.length; i++){
            if (plateau[i] > 0){
                for (int j = 1; j <= plateau[i] && j<= maxMatches; j++){
                    possibleMoves.add(new int[]{i+1,j});
                }

            }
        }
        Random random = new Random(); // Create a Random object
        int randomIndex = random.nextInt(possibleMoves.size());
        int [] coup = possibleMoves.get(randomIndex); // I know coup is redundant, but it's good for readability
        /**
         * to test out if the possible moves are correct
        for (int[] move : possibleMoves) {
            System.out.println(Arrays.toString(move));
        }
        */
        return coup;

    }



    @Override
    public void makeMove() {
        int[] coup = new int[2];
        if ( !((PlateauNim)jeu).hasConstraints()){
            coup = getStrategieGagnante();
        }
        else {
            coup = getStrategieAleatoire();
        }

        try {
            ((PlateauNim)jeu).jouerCoup(coup);
        } catch (HeapNumberException | MatchesNumberException | CoupException | NotEnoughMatchesException e) {
            throw new RuntimeException(e);
        }
    }

}
