package schocken.myschockenapp.de.myschockenapp.observer;

import schocken.myschockenapp.de.myschockenapp.player.Player;

/**
 * Interface for the {@link Observer}, which oberves the game.
 */
public interface Observer {

    /**
     * This method starts a new game.
     */
    void newGame();

    /**
     * This method starts a new half of a round.
     */
    void nextHalf();

    /**
     * This method starts a new round.
     */
    void nextRound();

    /**
     * This method returns the current player.
     * @return An object of the class {@link Player}.
     */
    Player getCurrentPlayer();

    /**
     * This method creates the player of the game.
     * @param playerNames An array of player names.
     */
    void createPlayers(final String[] playerNames);
}
