package schocken.myschockenapp.de.myschockenapp.player.exceptions;

/**
 * This class is an extension of the class {@link Exception}.
 * This exceptions is triggered when to much dices are added to the player.
 * Created by Snaki on 02.12.2017.
 */

public class MaxDiceThrowException extends Exception {

    /**
     * Constructor of the class {@link NotEnoughDicesOutException}.
     * @param exceptionMessage The exception message.
     */
    public MaxDiceThrowException(final String exceptionMessage){
        super(exceptionMessage);
    }
}
