package project.Nim;

public class Joueur implements Comparable<Joueur>  {
    private final String nom;
    private int score = 0;

    public Joueur(String nom) {
        this.nom = nom;
    }

    /**
     * @return the name of the player
     */
    public String getNom() {
        return nom;
    }

    /**
     * @author
     * Increase the score of the player by 1
     */
    public void increaseScore() {
        this.score++;
    }

    /**
     * @author
     * @return the score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * @author
     * @param player the other player to compare score with
     * @return the difference between this player score and the other one
     * @return -1 this player have a lower score | 0 players have an equal score | 1 this player have a better score
     */
    @Override
    public int compareTo(Joueur player) {
        int score = this.score - player.score;
        if ( score < 0 ) { return -1; }
        return this.score - player.score > 0 ? 1 : 0 ;
    }


}
