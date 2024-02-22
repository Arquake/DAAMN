package project.Nim;

public class NimView {

    /**
     * @author Amin
     * @param playerName the name of the player which is playing at this turn
     * @param Jeu int[][] Array of Heap and the matches in those
     * @return the user input for his move
     */
    public String afficher(int[][] matchSets, String playerName) {
        String res =" ";
        int maxRowWidth = getMaxRowWidth(matchSets); // get the length of the longest row

        for (int[] row : matchSets) {
            int padding = (maxRowWidth - row.length) / 2; // Calculate padding for pyramid style
            for (int i = 0; i < padding; i++) {
                res+= " " ; // Append leading spaces for padding
            }
            for (int matches : row) {
                res+= "| " ; // Each match is represented by a '|' with a space next to it
            }
            res+= "\n" ; // New line after each row
        }

        res+= playerName + " : à vous de jouer un coup sous la forme 'm n' où m est le tas choisi et n le nombre d'allumettes à retirer dans ce tas.\n";
        return res;
    }
    private int getMaxRowWidth(int[][] matchSets) {
        int maxWidth = 0;
        for (int[] row : matchSets) {
            int rowWidth = row.length;
            if (rowWidth > maxWidth) {
                maxWidth = rowWidth;
            }
        }
        return maxWidth * 2; // Assuming each match and space takes up 2 characters
    }

    // only for testing purposes
    /** public static void main(String[] args) {
        NimView view = new NimView();
        // Example match sets for demonstration
        int[][] matchSets = {{1},{1,1,1},{1, 1, 1,1,1},{1, 1, 1, 1, 1, 1, 1}};
        String playerName = "Dupont";
        String gameDisplay = view.afficher(matchSets, playerName);
        System.out.println(gameDisplay);
    }
     */


}
