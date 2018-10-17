package schocken.myschockenapp.de.myschockenapp.player2;

import java.util.List;

import schocken.myschockenapp.de.myschockenapp.dice.DiceValue;
import schocken.myschockenapp.de.myschockenapp.player2.exceptions.DiceNotFoundException;

/**
 * This interface contains all methods for dice actions.
 */
public interface PlayerDiceActions {

    /**
     * This method takes a {@link DiceValue} back in.
     * @param diceValue An object of the class {@link DiceValue}.
     * @throws DiceNotFoundException
     */
    void diceBackIn(final DiceValue diceValue)throws DiceNotFoundException;

    /**
     * This method takes a {@link DiceValue} out.
     * @param diceValue An object of the class {@link DiceValue}.
     * @throws DiceNotFoundException
     */
    void diceOut(final DiceValue diceValue) throws DiceNotFoundException;

    /**
     * This method returns a list of dices which are in.
     * @return A list of dices.
     */
    List<DiceValue> getDicesValuesIn();

    /**
     * This method returns a list of dices which are out.
     * @return A list of dices.
     */
    List<DiceValue> getDicesValuesOut();

}
