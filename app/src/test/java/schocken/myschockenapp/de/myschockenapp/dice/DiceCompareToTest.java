package schocken.myschockenapp.de.myschockenapp.dice;


import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import schocken.myschockenapp.de.myschockenapp.dice.impl.DiceImpl;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * This class tests the function "compareTo" of the dice.
 */
public class DiceCompareToTest {

    /**
     * This method tests the function "compareTo of the dice.
     */
    @Test
    public void compareTest1(){
        Dice dice1 = spy(new DiceImpl());
        when(dice1.getValue()).thenReturn(2);
        Dice dice2 = spy(new DiceImpl());
        when(dice2.getValue()).thenReturn(3);
        List<Dice> diceList = new ArrayList<Dice>();
        diceList.add(dice1);
        diceList.add(dice2);
        Assert.assertEquals(diceList.get(0).getValue(), 2);
        Collections.sort(diceList);
        Assert.assertEquals(diceList.get(0).getValue() , 3);



    }


}
