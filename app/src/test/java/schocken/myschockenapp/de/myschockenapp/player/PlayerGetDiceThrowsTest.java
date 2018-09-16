package schocken.myschockenapp.de.myschockenapp.player;

import junit.framework.Assert;

import org.junit.Test;

import schocken.myschockenapp.de.myschockenapp.player.PlayerImpl.PlayerImpl;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.PlayerActionNotAllowedException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * This class tests the method "getDiceThrows"
 */
public class PlayerGetDiceThrowsTest {

    /**
     * This method tests the function "getDiceThrows".
     */
    @Test
    public void getDiceThrowsTest1(){
        Player player = mock(Player.class);
        final int diceThrows = 2;
        when(player.getDiceThrows()).thenReturn(diceThrows);
        Assert.assertEquals("The dice throws should be"+diceThrows,diceThrows,player.getDiceThrows());
    }


    /**
     * This method tests the function "getDiceThrows".
     */
    @Test
    public void getDiceThrowsTest2(){
        Player player = spy(new PlayerImpl("Marco"));
        try {
            player.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {
            Assert.fail("The player is allowed to roll the dices");
        }
        Assert.assertEquals("The dice throws should be 1",1,player.getDiceThrows());
    }
}
