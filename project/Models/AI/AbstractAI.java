package project.Models.AI;

import project.Models.AbstractPlateau;
import project.Models.AbstractPlayer;

public abstract class AbstractAI extends AbstractPlayer {

    AbstractPlateau jeu;
    /**
     * instantiate the AI
     * @param jeu
     */
    public AbstractAI(AbstractPlateau jeu) {
        super("AI");
        this.jeu = jeu;
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    /**
     * method to implement to make a move for the AI
     */
    public abstract void makeMove();

    public void setBoard(AbstractPlateau jeu){
        this.jeu = jeu;
    }
}
