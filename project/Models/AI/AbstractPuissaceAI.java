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

    /**
     * Calcule le nombre de jeton alignable pour les deux joueurs.
     * @param columnCoup Colonne ou le coup est joué.
     * @param ligneCoup Ligne sur laquelle le jeton arrive.
     * @return Le maximum de jeton alignable (plafonné a 4) pour chauqe joueur sous forme d'un tableau. indice 0 pour le joueur, 1 pour l'IA.
     */
    int[] nombreAlignable(int columnCoup, int ligneCoup){
        int[] column = nombreAlignableColumn(columnCoup,ligneCoup);
        int[] ligne = nombreAlignableLigne(columnCoup,ligneCoup);
        int[] diag = nombreAlignableDiagonal(columnCoup,ligneCoup);

        int[] max = new int[2]; //[joueur, ia]
        max[0] = Math.max(Math.max(column[0], ligne[0]),diag[0]);
        max[1] = Math.max(Math.max(column[1], ligne[1]),diag[1]);

        return max;
    }

    /**
     * Calcule le nombre de jeton alignable pour les deux joueurs sur une seule colonne.
     * @param columnCoup Colonne ou le coup est joué.
     * @param ligneCoup Ligne sur laquelle le jeton arrive.
     * @return Le maximum de jeton alignable (plafonné a 4) pour chaque joueur sous forme d'un tableau. indice 0 pour le joueur, 1 pour l'IA.
     */
    private int[] nombreAlignableColumn(int columnCoup, int ligneCoup){
        PlateauPuissance plateauPuissance = (PlateauPuissance) jeu;
        int taille = plateauPuissance.getTerrain().length-1;

        //setup interval de verification
        int minIntervalDeCheck = ligneCoup-3;
        int maxIntervalDeCheck = ligneCoup+3;

        //Rogner l'interval de verification s'il depasse du plateau
        if (maxIntervalDeCheck > taille) maxIntervalDeCheck = taille;
        if (minIntervalDeCheck < 0) minIntervalDeCheck = 0;

        //Compter le nombre de jeton alignable
        int[] maxParJoueurs = new int[2];
        for (int player = 1; player < 3; player++) {
            int maxAlignable = 1;

            int[][] terrain = plateauPuissance.getTerrain();
            //bas
            for (int i = 1; i <= 3; i++) {
                if (!(ligneCoup + i > maxIntervalDeCheck)) {
                    if (terrain[ligneCoup+i][columnCoup] == player){
                        maxAlignable++;
                    }
                    else break;
                } else break;
            }

            //haut
            for (int i = 1; i <= 3; i++){
                if (! (ligneCoup-i < minIntervalDeCheck)) {
                    if (terrain[ligneCoup-i][columnCoup] == player){
                        maxAlignable++;
                    }else break;
                } else break;
            }

            maxParJoueurs[player-1] = maxAlignable;
        }
        return maxParJoueurs;
    }

    /**
     * Calcule le nombre de jeton alignable pour les deux joueurs sur une seule ligne.
     * @param columnCoup Colonne ou le coup est jouer.
     * @param ligneCoup Ligne sur laquelle le jeton arrive.
     * @return Le maximum de jeton alignable (plafonné a 4) pour chaque joueur sous forme d'un tableau. indice 0 pour le joueur, 1 pour l'IA.
     */
    private int[] nombreAlignableLigne(int columnCoup, int ligneCoup){
        PlateauPuissance plateauPuissance = (PlateauPuissance) jeu;
        int taille = plateauPuissance.getTerrain().length-1;

        //setup interval de verification
        int minIntervalDeCheck = columnCoup-3;
        int maxIntervalDeCheck = columnCoup+3;

        //Rogner l'interval de verification s'il depasse du plateau
        if (maxIntervalDeCheck > taille) maxIntervalDeCheck = taille;
        if (minIntervalDeCheck < 0) minIntervalDeCheck = 0;

        //Compter le nombre de jeton alignable
        int[] maxParJoueurs = new int[2];
        for (int player = 1; player < 3; player++) {
            int maxAlignable = 1;

            int[][] terrain = plateauPuissance.getTerrain();
            //gauche
            for (int i = 1; i <= 3; i++) {
                if (!(columnCoup + i > maxIntervalDeCheck)) {
                    if (terrain[ligneCoup][columnCoup + i] == player){
                        maxAlignable++;
                    }
                    else break;
                } else break;
            }

            //droite
            for (int i = 1; i <= 3; i++){
                if (! (columnCoup-i < minIntervalDeCheck)) {
                    if (terrain[ligneCoup][columnCoup - i] == player){
                        maxAlignable++;
                    }else break;
                } else break;
            }

            maxParJoueurs[player-1] = maxAlignable;
        }
        return maxParJoueurs;
    }

    /**
     * Calcule le nombre de jeton alignable pour les deux joueurs sur toutes les digonales.
     * @param columnCoup Colonne ou le coup est jouer.
     * @param ligneCoup Ligne sur laquelle le jeton arrive.
     * @return Le maximum de jeton alignable (plafonné a 4) pour chaque joueur sous forme d'un tableau. indice 0 pour le joueur, 1 pour l'IA.
     */
    private int[] nombreAlignableDiagonal(int columnCoup, int ligneCoup) {
        PlateauPuissance plateauPuissance = (PlateauPuissance) jeu;
        int coteTerrain = plateauPuissance.getTerrain().length -1;

        //setup interval de verification
        int minIntervalDeCheckLigne = ligneCoup-3;
        int maxIntervalDeCheckLigne = ligneCoup+3;
        int minIntervalDeCheckColumn = columnCoup-3;
        int maxIntervalDeCheckColumn = columnCoup+3;

        //Rogner l'interval de verification s'il depasse du plateau
        if (maxIntervalDeCheckLigne > coteTerrain) maxIntervalDeCheckLigne = coteTerrain;
        if (minIntervalDeCheckLigne < 0) minIntervalDeCheckLigne = 0;
        if (maxIntervalDeCheckColumn > coteTerrain) maxIntervalDeCheckColumn = coteTerrain;
        if (minIntervalDeCheckColumn < 0) minIntervalDeCheckColumn = 0;

        //Compter le nombre de jeton alignable
        int[] maxParJoueurs = new int[2];
        for (int player = 1; player < 3; player++) {

            int[] maxAlignable = new int[]{1,1,1,1}; //longueur maximum par direction
            int[][] terrain = plateauPuissance.getTerrain();

            for (int i = 1; i <= 3; i++) {
                //bas droite + +
                if (! (ligneCoup+i > maxIntervalDeCheckLigne || columnCoup+i > maxIntervalDeCheckColumn)) {
                    if (/*terrain[ligneCoup + i][columnCoup + i] == 0 ||*/ terrain[ligneCoup + i][columnCoup + i] == player) maxAlignable[0]++;
                }

                //bas gauche + -
                if (! (ligneCoup+i > maxIntervalDeCheckLigne || columnCoup-i < minIntervalDeCheckColumn)) {
                    if (terrain[ligneCoup + i][columnCoup - i] == player) maxAlignable[1]++;
                }

                //haut droit - +
                if (! (ligneCoup-i < minIntervalDeCheckLigne || columnCoup+i > maxIntervalDeCheckColumn)) {
                    if (terrain[ligneCoup - i][columnCoup + i] == player) maxAlignable[2]++;
                }

                //haut gauche - -
                if (! (ligneCoup-i < minIntervalDeCheckLigne || columnCoup-i < minIntervalDeCheckColumn)) {
                    if (terrain[ligneCoup - i][columnCoup - i] == player) maxAlignable[3]++;
                }
            }

            int maxAlignableUnit = Math.max(Math.max(maxAlignable[0], maxAlignable[1]),Math.max(maxAlignable[2],maxAlignable[3]));
            maxParJoueurs[player-1] = Math.min(maxAlignableUnit,4);
        }
        return maxParJoueurs;
    }

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
