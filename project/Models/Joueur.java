package project.Models;

public class Joueur implements Comparable<Joueur>  {
    private final String nom;
    private int score = 0;

    /**
     * instantiate the player
     * @param nom player's name
     */
    public Joueur(String nom) {
        this.nom = nom;
    }

    /**
     * get the nom of the player
     * @return the name of the player
     */
    public String getNom() {
        return nom;
    }

    /**
     * Increases the players score by 1
     */
    public void increaseScore() {
        this.score++;
    }

    /**
     * get the score of the player
     * @return the score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * compare two player scores
     * @param player the other player to compare score with
     * @return the difference between this player score and the other one
     * -1 this player has a lower score | 0 players has an equal score | 1 this player has a higher score
     */
    @Override
    public int compareTo(Joueur player) {
        int score = this.score - player.score;
        return Integer.compare(score, 0);
    }


}
