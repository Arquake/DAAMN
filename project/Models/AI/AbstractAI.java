package project.Models.AI;

import project.Models.AbstractPlateau;
import project.Models.AbstractPlayer;

public abstract class AbstractAI extends AbstractPlayer {

    AbstractPlateau jeu;
    /**
     * instantiate the AI
     * @param jeu the board to set the ai to
     */
    public AbstractAI(AbstractPlateau jeu) {
        super("AI");
        this.jeu = jeu;
    }

    /**
     * method to check if user is human or not
     * @return if the user is human true otherwise false
     */
    @Override
    public boolean isHuman() {
        return false;
    }

    /**
     * method to implement to make a move for the AI
     *
     * @return coup joué
     */
    public abstract int[] makeMove();

    /**
     * method to set the board
     * @param jeu the board to set to
     */
    public void setBoard(AbstractPlateau jeu){
        this.jeu = jeu;
    }
}
