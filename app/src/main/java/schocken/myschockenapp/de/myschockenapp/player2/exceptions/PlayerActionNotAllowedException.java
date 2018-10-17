package schocken.myschockenapp.de.myschockenapp.player2.exceptions;

/**
 * This class is an extension of an {@link Exception}.
 * This exception appears when the player is not allowed to do an action.
 */

public class PlayerActionNotAllowedException extends Exception {

    /**
     * Constructor of the class {@link PlayerActionNotAllowedException}.
     * @param exceptionMessage The exception message.
     */
    public PlayerActionNotAllowedException(final String exceptionMessage){
        super(exceptionMessage);
    }
}
