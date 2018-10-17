package schocken.myschockenapp.de.myschockenapp.player2;

/**
 * This is the interface of a player.
 */
public interface Player extends PlayerActions, PlayerGameActions, PlayerDiceActions {

    /**
     * This method return the name of the player.
     * @return The name of the player.
     */
    String getName();
}
