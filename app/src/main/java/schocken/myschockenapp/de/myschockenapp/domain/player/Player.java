package schocken.myschockenapp.de.myschockenapp.domain.player;

import schocken.myschockenapp.de.myschockenapp.player2.exceptions.PlayerActionNotAllowedException;

public interface Player {

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
