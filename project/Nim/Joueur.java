package project.Nim;

public class Joueur implements Comparable<Joueur>  {
    private String nom;
    private int score = 0;

    private int lastMatches = 0;

    public Joueur(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void increaseScore() {
        this.score++;
    }

    public int getlastMatches() {
        return lastMatches;
    }

    public void increaseLastMatches() {
        this.lastMatches++;
    }


    @Override
    public int compareTo(Joueur o) {
        return this.lastMatches - o.lastMatches;
    }
}
