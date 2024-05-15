package project.Models.AI;

import project.Models.AbstractPlateau;
import project.Models.PlateauPuissance;

public abstract class AbstractPuissaceAI extends AbstractAI{
    /**
     * instantiate the AI
     *
     * @param jeu the board to set the ai to
     */
    public AbstractPuissaceAI(AbstractPlateau jeu) {
        super(jeu);
    }

    abstract int gagnerParRotation(boolean isRotationActive, PlateauPuissance terrain);

    abstract int[] nombreAlignable(int column, int ligne);

    /**
     * Recupère la ligne sur laquelle arrivera le jeton joué.
     * @param column Colonne dans laquelle le jeton est joué.
     * @return Le ligne sur laquelle arrive le jeton.
     */
    int getLigne(int column) {
        int[][] terrain = ((PlateauPuissance) jeu).getTerrain();
        for (int ligne = 1; ligne < terrain.length; ligne++) {
            if (terrain[ligne][column] != 0){
                return ligne-1;
            }
        }
        return terrain.length-1;
    }

    /**
     * Creer une copie du terrain.
     * @param base Le terrain a copier.
     * @return La copie du terrain.
     */
    PlateauPuissance creerCopieTerrain(PlateauPuissance base){
        PlateauPuissance copie = new PlateauPuissance();

        int len = base.getTerrain().length;

        int[][] terrainCopie = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                terrainCopie[i][j] = base.getTerrain()[i][j];
            }
        }

        copie.setTerrain(terrainCopie);
        return copie;
    }

    /**
     * Set le plateau sur lequel l'ia joue.
     * @param jeu Le plateau en question.
     */
    public void setBoard(AbstractPlateau jeu){
        super.setBoard(jeu);
    }
}
