package schocken.myschockenapp.de.myschockenapp.observer;

import schocken.myschockenapp.de.myschockenapp.observer.exceptions.NotEnoughPlayerException;
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
    void nextHalf() throws Exception;

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
     * This method returns the current worst player.
     * @return An object of the class {@link Player}.
     */
    Player getCurrentWorstPlayer();

    /**
     * This method returns the current best player.
     * @return An object of the class {@link Player}.
     */
    Player getCurrentBestPlayer();

    /**
     * This method creates the player of the game.
     * @param playerNames An array of player names.
     */
    void createPlayers(final String[] playerNames) throws NotEnoughPlayerException;
}
