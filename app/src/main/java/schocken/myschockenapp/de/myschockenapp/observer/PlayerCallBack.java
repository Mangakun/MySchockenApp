package schocken.myschockenapp.de.myschockenapp.observer;


/**
 * An interface of a player callback.
 */
public interface PlayerCallBack {

    /**
     * The callback method.
     * @param finish True, if the player is completely finish with his round.
     */
    void callback(final boolean finish);
}
