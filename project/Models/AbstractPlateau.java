package project.Models;

import project.Models.Exception.heapNumberException;
import project.Models.Exception.invalidColumException;
import project.Models.Exception.matchesNumberException;

public abstract class AbstractPlateau {

    /**
     *
     * @return true if players can't play on the board anymore depending on the game | false if they can still play on it
     */
    public abstract boolean boardCompleted();


    public abstract void jouerCoup(int[] values) throws matchesNumberException, heapNumberException, invalidColumException;
}
