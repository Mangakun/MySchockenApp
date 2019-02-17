package schocken.myschockenapp.de.myschockenapp.player.exceptions;


public class NotEnoughDicesOutException extends Exception {

    /**
     * Constructor of the class {@link NotEnoughDicesOutException}.
     * @param exceptionMessage The exception message.
     */
    public NotEnoughDicesOutException(final String exceptionMessage){
        super(exceptionMessage);
    }
}
