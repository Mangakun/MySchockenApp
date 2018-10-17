package schocken.myschockenapp.de.myschockenapp.observer.exceptions;

public class NotEnoughPlayerException extends Exception {

    /**
     * Constructor of the class {@link NotEnoughPlayerException}.
     */
    public NotEnoughPlayerException(final String exceptionMessage){
        super(exceptionMessage);
    }
}
