package schocken.myschockenapp.de.myschockenapp.player2;

import junit.framework.Assert;

import org.junit.Test;


import java.util.List;
import java.util.Random;


import schocken.myschockenapp.de.myschockenapp.dice.DiceValue;
import schocken.myschockenapp.de.myschockenapp.player2.PlayerImpl.PlayerImpl;
import schocken.myschockenapp.de.myschockenapp.player2.exceptions.DiceNotFoundException;
import schocken.myschockenapp.de.myschockenapp.player2.exceptions.PlayerActionNotAllowedException;

import static org.mockito.Mockito.spy;

public class PlayerDiceOutBackInTest {

    private int getRandom(final int max){
        Random rn = new Random();
        int result =  rn.nextInt(max); // random number between 0 and max
        return result;
    }

    @Test
    public void playerDiceOutBackInTest1(){
        Player player = spy(new PlayerImpl(null,null));
        Assert.assertEquals("The player should have 3 dices in ", 3, player.getDicesValuesIn().size());
        Assert.assertEquals("The player should have 0 dices out ", 0, player.getDicesValuesOut().size());
        try {
            player.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {
            Assert.fail("The player can roll the dices");
        }
        Assert.assertEquals("The player should have 3 dices in ", 3, player.getDicesValuesIn().size());
        Assert.assertEquals("The player should have 0 dices out ", 0, player.getDicesValuesOut().size());
        List<DiceValue> dicesValuesIn = player.getDicesValuesIn();
        int randomEntry = getRandom(dicesValuesIn.size());
        try {
            player.diceOut(dicesValuesIn.get(randomEntry));
        } catch (DiceNotFoundException e) {
            Assert.fail("The dice should be in the list of dices in");

        }
        Assert.assertEquals("The player should have 2 dices in ", 2, player.getDicesValuesIn().size());
        Assert.assertEquals("The player should have 1 dices out ", 1, player.getDicesValuesOut().size());
        List<DiceValue> dicesValuesOut = player.getDicesValuesOut();
        randomEntry = getRandom(dicesValuesOut.size());
        try {
            player.diceBackIn(dicesValuesOut.get(randomEntry));
        } catch (DiceNotFoundException e) {
            Assert.fail("The dice should be in the list of dices out");
        }
        Assert.assertEquals("The player should have 3 dices in ", 3, player.getDicesValuesIn().size());
        Assert.assertEquals("The player should have 0 dices out ", 0, player.getDicesValuesOut().size());
    }

    @Test
    public void playerDiceOutBackInTest2(){
        Player player = spy(new PlayerImpl(null,null));
        Assert.assertEquals("The player should have 3 dices in ", 3, player.getDicesValuesIn().size());
        Assert.assertEquals("The player should have 0 dices out ", 0, player.getDicesValuesOut().size());
        try {
            player.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {
            Assert.fail("The player can roll the dices");
        }
        Assert.assertEquals("The player should have 3 dices in ", 3, player.getDicesValuesIn().size());
        Assert.assertEquals("The player should have 0 dices out ", 0, player.getDicesValuesOut().size());
        for(int i = 0;i<3;++i) {
            List<DiceValue> dicesValuesIn = player.getDicesValuesIn();
            int randomEntry = getRandom(dicesValuesIn.size());
            try {
                player.diceOut(dicesValuesIn.get(randomEntry));
            } catch (DiceNotFoundException e) {
                Assert.fail("The dice should be in the list of dices in");

            }
        }
        Assert.assertEquals("The player should have 0 dices in ", 0, player.getDicesValuesIn().size());
        Assert.assertEquals("The player should have 3 dices out ", 3, player.getDicesValuesOut().size());
    }


    @Test
    public void playerDiceOutBackInTest3(){
        Player player = spy(new PlayerImpl(null,null));
        Assert.assertEquals("The player should have 3 dices in ", 3, player.getDicesValuesIn().size());
        Assert.assertEquals("The player should have 0 dices out ", 0, player.getDicesValuesOut().size());
        try {
            player.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {
            Assert.fail("The player can roll the dices");
        }
        Assert.assertEquals("The player should have 3 dices in ", 3, player.getDicesValuesIn().size());
        Assert.assertEquals("The player should have 0 dices out ", 0, player.getDicesValuesOut().size());
        for(int i = 0;i<3;++i) {
            List<DiceValue> dicesValuesIn = player.getDicesValuesIn();
            int randomEntry = getRandom(dicesValuesIn.size());
            try {
                player.diceOut(dicesValuesIn.get(randomEntry));
            } catch (DiceNotFoundException e) {
                Assert.fail("The dice should be in the list of dices in");
            }
        }
        Assert.assertEquals("The player should have 0 dices in ", 0, player.getDicesValuesIn().size());
        Assert.assertEquals("The player should have 3 dices out ", 3, player.getDicesValuesOut().size());

        for(int i = 0;i<3;++i) {
            List<DiceValue> dicesValuesOut = player.getDicesValuesOut();
            int randomEntry = getRandom(dicesValuesOut.size());
            try {
                player.diceBackIn(dicesValuesOut.get(randomEntry));
            } catch (DiceNotFoundException e) {
                Assert.fail("The dice should be in the list of dices in");
            }
        }
        Assert.assertEquals("The player should have 3 dices in ", 3, player.getDicesValuesIn().size());
        Assert.assertEquals("The player should have 0 dices out ", 0, player.getDicesValuesOut().size());
    }
}
