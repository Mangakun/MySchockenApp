package schocken.myschockenapp.de.myschockenapp.player;

import schocken.myschockenapp.de.myschockenapp.player.exceptions.PlayerActionNotAllowedException;

public interface Player  extends PlayerActions, PlayerGameActions, PlayerDiceActions {

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

    /**
          * This method return the name of the player.
          * @return The name of the player.
          */
    String getName();
}
