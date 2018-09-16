package schocken.myschockenapp.de.myschockenapp.dice;


import junit.framework.Assert;

import org.junit.Test;

import schocken.myschockenapp.de.myschockenapp.dice.impl.DiceImpl;

/**
 * This class tests the function "roll" of the dice.
 */
public class DiceRollTest {


    /**
     * This method tests the roll function of the dice.
     * The dice value should always between 1 and 6.
     */
    @Test
    public void rollTest1(){
        Dice dice = new DiceImpl();
        dice.roll();
        Assert.assertTrue("The dice value should be between 1 and 6", 0<dice.getValue() && dice.getValue()<7);
    }

    /**
     * This test calls multiple times the method "roll".
     * The dice value should always between 1 and 6.
     */
    @Test
    public void rollTest2(){
        Dice dice = new DiceImpl();
        for(int i=0; i<10000000;++i){
            dice.roll();
            Assert.assertTrue("The dice value should be between 1 and 6", 0<dice.getValue() && dice.getValue()<7);
        }
    }


}
