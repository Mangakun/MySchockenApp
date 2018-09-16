package schocken.myschockenapp.de.myschockenapp.dice;


/**
 * This interface handles the dice value
 */
public interface DiceValue extends Comparable<DiceValue> {

    /**
     * This method returns the value of the dice.
     * @return The dice value.
     */
    int getValue();
}
