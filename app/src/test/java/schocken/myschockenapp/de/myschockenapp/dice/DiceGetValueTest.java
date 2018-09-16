package schocken.myschockenapp.de.myschockenapp.dice;

import junit.framework.Assert;

import org.junit.Test;

import schocken.myschockenapp.de.myschockenapp.dice.impl.DiceImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class tests the "getValue" function of a dice.
 */
public class DiceGetValueTest {

    /**
     * This method tests the "getValue" method.
     */
    @Test
    public void getValueTest1(){
        Dice dice = mock(Dice.class);
        final int returnValue = 5;
        when(dice.getValue()).thenReturn(returnValue);
        Assert.assertTrue("The return value should be "+returnValue, dice.getValue() == returnValue);
    }
}
