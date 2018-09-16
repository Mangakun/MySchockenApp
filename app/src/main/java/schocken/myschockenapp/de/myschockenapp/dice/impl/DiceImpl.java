package schocken.myschockenapp.de.myschockenapp.dice.impl;

import android.support.annotation.NonNull;

import java.util.Random;

import schocken.myschockenapp.de.myschockenapp.dice.Dice;
import schocken.myschockenapp.de.myschockenapp.dice.DiceValue;

/**
 * Implementation class of a dice.
 */
public class DiceImpl implements Dice {

    /**
     * The value of the dice.
     */
    private int diceValue;

    /**
     * An object of the class {@link Random}.
     */
    private Random random;

    /**
     * Constructor of the class {@link DiceImpl}.
     */
    public DiceImpl(){
        this.diceValue = -1;
        random = new Random();
    }
    @Override
    public void roll() {
        diceValue = (random.nextInt(6) + 1);
    }

    @Override
    public int getValue() {
        return diceValue;
    }

    @Override
    public int compareTo(@NonNull DiceValue diceValue) {
        return diceValue.getValue() - getValue();
    }

    @Override
    public String toString() {
        return "DiceImpl{" +
                "diceValue=" + diceValue +
                ", random=" + random +
                '}';
    }
}
