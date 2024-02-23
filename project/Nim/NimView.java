package project.Nim;

public class NimView {

    /**
     * @author Amin
     * @param playerName the name of the player which is playing at this turn
     * @param matchSets int[][] Array of Heap and the matches in those
     * @return the user input for his move
     */
    public String afficher(String matchSets, String playerName) {
        System.out.println(matchSets + playerName + " : à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.\n");
        return "";
    }

}
