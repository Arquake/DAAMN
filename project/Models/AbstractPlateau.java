package project.Models;

import project.Models.Exception.*;

public abstract class AbstractPlateau {

    /**
     * method to check if a board is can still be played on or not
     * @return true if players can't play on the board anymore depending on the game | false if they can still play on it
     */
    public abstract boolean boardCompleted();


    /**
     * method to make a move in the game
     * @param values array of parameters
     * @throws MatchesNumberException exception for Nim when the number of matches is too big to be played
     * @throws HeapNumberException exception for Nim if the heap can't be played on
     * @throws InvalidColumException exception for puissance 4 if the column entered is invalid
     */
    public abstract void jouerCoup(int[] values) throws MatchesNumberException, HeapNumberException, InvalidColumException, EmptyHeapException, NotEnoughMatchesException;
}
