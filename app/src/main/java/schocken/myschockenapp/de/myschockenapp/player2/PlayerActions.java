package schocken.myschockenapp.de.myschockenapp.player2;


import schocken.myschockenapp.de.myschockenapp.player2.exceptions.PlayerActionNotAllowedException;

/**
 * This interface contains all player actions which are allowed.
 */

public interface PlayerActions {

    /**
     * The player finishes his round.
     * @throws PlayerActionNotAllowedException
     */
    void stay() throws PlayerActionNotAllowedException;

    /**
     * The player rolls the dices.
     * @throws PlayerActionNotAllowedException
     */
    void rollTheDice() throws PlayerActionNotAllowedException;

    /**
     * The player opens his cup.
     * @throws PlayerActionNotAllowedException
     */
    void openCup() throws PlayerActionNotAllowedException;

    /**
     * The players turn.
     */
    void turn();
}


