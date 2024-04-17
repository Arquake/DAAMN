package project.Models;

public class Joueur extends AbstractPlayer {

    /**
     * instantiate the player
     *
     * @param nom player's name
     */
    public Joueur(String nom) {
        super(nom);
    }

    @Override
    public boolean isHuman() {
        return true;
    }

}
