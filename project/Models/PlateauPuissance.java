package project.Models;


import project.Models.Exception.InvalidColumException;

import java.util.Arrays;

public class PlateauPuissance extends AbstractPlateau {

    /**
     * terrain int[line][column]
     * 0 0 bottom left corner
     */
    private int[][] terrain = new int[7][7];

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
     * @throws InvalidColumException if the column selected is less than 0 or greater than the number of columns
     */
    public void jouerCoup(int[] data) throws InvalidColumException {
        if ((data[0] < 0) || (data[0] >=terrain[0].length) || terrain[0][data[0]] != 0){throw new InvalidColumException();};
        for (int i = terrain.length-1; i >= 0 ; i--) {
            if (terrain[i][data[0]] == 0) {terrain[i][data[0]] = data[1];return;}
        }
        throw new InvalidColumException();
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

    /**
     * Tourne la grille dans le sens anti horaire et y applique la gravité
     */
    public void tournerSensAntiHoraire(){
        int nbLignes = terrain.length;
        int nbColonnes = terrain[1].length;
        int[][] newTerrain = new int[nbLignes][nbColonnes];

        for (int lignes = 0; lignes < nbLignes; lignes++) {
            for (int colonnes = 0; colonnes < nbColonnes; colonnes++) {
                newTerrain[colonnes][nbColonnes-1-lignes] = terrain[lignes][colonnes];
            }
        }
        gravite(newTerrain);
        terrain = newTerrain;
    }

    /**
     * Tourne la grille dans le sens horaire et y appliqye la gravité
     */
    public void tournerSensHoraire(){
        int nbLignes = terrain.length;
        int nbColonnes = terrain[1].length;
        int[][] newTerrain = new int[nbLignes][nbColonnes];

        for (int lignes = 0; lignes < nbLignes; lignes++) {
            for (int colonnes = 0; colonnes < nbColonnes; colonnes++) {
                newTerrain[nbLignes-1-colonnes][lignes] = terrain[lignes][colonnes];
            }
        }

        for (int[] ints : newTerrain) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println();
        gravite(newTerrain);
        terrain = newTerrain;
        for (int[] ints : terrain) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * Applique la gravité sur le terrain
     * @param terrain le terrain sur lequel appliqué la gravité
     */
    public static void gravite(int[][] terrain) {
        for (int colonne = 0; colonne < terrain[0].length; colonne++) { //get la colonne (de gauche a droite)
            for (int ligneActuelle = terrain.length-1; ligneActuelle > 0; ligneActuelle--) { //get la ligne (de bas en haut)

                if (terrain[ligneActuelle][colonne] == 0 ) { // si la case traité est un 0

                    for (int i = ligneActuelle; i > 0; i--) { // on parcours tout les nums de lignes de bas en haut a partir de la où se trouve le zero (s'arrete avant de passer a 0)
                        terrain[i][colonne] = terrain[i-1][colonne]; // prend la case de la ligne au dessus et la "descend" d'une ligne*
                        if (terrain[i-1][colonne] != 0){
                            ligneActuelle=terrain.length;
                        }
                        //System.out.println(terrain[i][colonne] + " devient " + terrain[i-1][colonne] + " || i=" + i +" colonne : " + colonne);
                    }

                    terrain[0][colonne] = 0; //set la case de la premiere ligne de la colonne traitée a 0 ("on remonte le zero jusqu'en haut")
                }
            }
        }
    }
}
