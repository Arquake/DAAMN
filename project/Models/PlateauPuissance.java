package project.Models;


import project.Models.Exception.invalidColumException;

public class PlateauPuissance extends AbstractPlateau {

    /**
     * terrain int[line][column]
     * 0 0 bottom left corner
     */
    private final int[][] terrain = new int[7][7];

    /**
     * @return the power 4 grid with pieces in it
     */
    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        for (int[] i : terrain){
            content.append("\n\u001B[44m                                             \u001B[0m\n");
            content.append("\u001B[44m   \u001B[0m");
            for (int j : i) {
                switch (j) {
                    case 1 -> content.append("\u001B[41m   \u001B[0m\u001B[44m   \u001B[0m");
                    case 2 -> content.append("\u001B[43m   \u001B[0m\u001B[44m   \u001B[0m");
                    default -> content.append("\u001B[47m   \u001B[0m\u001B[44m   \u001B[0m");
                }
            }
        }
        content.append("\n\u001B[44m \u001B[30m   1     2     3     4     5     6     7    \u001B[0m\n");
        return content.toString();
    }

    /**
     * play the move given in the parameters by a given player
     * @param data [column numeroJoueur]
     *              column : number as column entered by the player - 1 if the player wrote 1 the number given should be 0
     *              numeroJoueur : number of the player
     * @throws invalidColumException if the column selected is less than 0 or greater than the number of columns
     */
    public void jouerCoup(int[] data) throws invalidColumException {
        if ((data[0] < 0) || (data[0] >=terrain[0].length) || terrain[0][data[0]] != 0){throw new invalidColumException();};
        for (int i = terrain.length-1; i >= 0 ; i--) {
            if (terrain[i][data[0]] == 0) {terrain[i][data[0]] = data[1];return;}
        }
        throw new invalidColumException();
    }

    /**
     * check if a player have won the game
     * @return the player who won | 0 otherwise
     */
    public int checkWin(){
        int column = checkColumn();
        int row = checkRow();
        int diagonal = checkDiagonal();
        return column != -1? column:row != -1? row:diagonal;
    }


    /**
     * check every row of the board if someone has won
     * @return 0 if nobody won | the player number otherwise
     */
    private int checkRow(){
        for (int i = terrain.length-1; i >= 0 ; i--) {
            for (int j = terrain[0].length-1; j >= 3 ; j--) {
                if (terrain[i][j] != 0) {
                    for (int k = 3; k >= 0 ; k--) {
                        if (terrain[i][j-k] != terrain[i][j]) {
                            break;
                        }
                        else if (k==0){
                            return terrain[i][j]-1;
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * check every column of the board if someone has won
     * @return 0 if nobody won | the player number otherwise
     */
    private int checkColumn(){
        for (int i = terrain[0].length-1; i >= 0 ; i--) {
            for (int j = terrain.length-1; j >= 3 ; j--) {
                if (terrain[j][i] != 0) {
                    for (int k = 3; k >= 0 ; k--) {
                        if (terrain[j-k][i] != terrain[j][i]) {
                            break;
                        }
                        else if (k==0){
                            return terrain[j][i]-1;
                        }
                    }
                    break;
                }
            }
        }
        return -1;
    }

    /**
     * check every diagonal of the board if someone has won
     * @return 0 if nobody won | the player number otherwise
     */
    private int checkDiagonal(){
        int topRight = checkTopRightDiagonal();
        int topBottomRight = checkBottomRightDiagonal();
        return topRight != -1? topRight:topBottomRight;
    }

    /**
     * check every diagonal from top right to bottom left of the board if someone has won
     * @return 0 if nobody won | the player number otherwise
     */
    private int checkTopRightDiagonal(){
        int currentCase;
        for (int i = terrain.length-1; i >= 3 ; i--) {
            for (int j = terrain[0].length-1; j >= 3 ; j--) {
                currentCase = terrain[i][j];
                if( currentCase != 0 &&
                        terrain[i-1][j-1] == currentCase &&
                        terrain[i-2][j-2] == currentCase &&
                        terrain[i-3][j-3] == currentCase
                ) {
                    return currentCase-1;
                }
            }
        }
        return -1;
    }

    /**
     * check every diagonal from bottom right to top left of the board if someone has won
     * @return 0 if nobody won | the player number otherwise
     */
    private int checkBottomRightDiagonal(){
        int currentCase;
        for (int i = 0; i <= terrain.length-4 ; i++) {
            for (int j = terrain[0].length-1; j >= 3 ; j--) {
                currentCase = terrain[i][j];
                if( currentCase != 0 &&
                        terrain[i+1][j-1] == currentCase &&
                        terrain[i+2][j-2] == currentCase &&
                        terrain[i+3][j-3] == currentCase
                ) {
                    return currentCase-1;
                }
            }
        }
        return -1;
    }

    /**
     * check if the board have no more playable space available
     * @return true if the board is full
     */
    public boolean boardCompleted(){
        for (int[] line : terrain) {
            for (int element : line) {
                if (element == 0){return false;}
            }
        }
        return true;
    }
}
