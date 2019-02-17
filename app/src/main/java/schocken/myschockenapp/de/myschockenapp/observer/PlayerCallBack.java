package schocken.myschockenapp.de.myschockenapp.observer;


import schocken.myschockenapp.de.myschockenapp.player.Player;


/**
 * An interface of a player callback.
 */
public interface PlayerCallBack {

    /**
     * The callback method.
     * @param callbackPlayer  The player which has called this method.
     * @param finish True, if the player is completely finish with his round.
     */
    void callback(final Player callbackPlayer, final boolean finish);
}
