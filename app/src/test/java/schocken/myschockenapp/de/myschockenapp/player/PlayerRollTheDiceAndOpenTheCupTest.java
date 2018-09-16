package schocken.myschockenapp.de.myschockenapp.player;

import junit.framework.Assert;

import org.junit.Test;

import schocken.myschockenapp.de.myschockenapp.player.exceptions.PlayerActionNotAllowedException;


/**
 * This class tests the functions "roll the dice" and open the cup"
 */
public class PlayerRollTheDiceAndOpenTheCupTest extends PlayerTest {
    /**
     * This method test the player action "open the cup".
     */
    @Test
    public void rollTheDiceAndOpenTheCupTest(){
        for(final Player player: players) {
            try {
                player.openCup();
                Assert.fail("The player can not open the cup.");
            } catch (PlayerActionNotAllowedException e) {
            }
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
            try {
                player.openCup();
                Assert.fail("The player can not open the cup.");
            } catch (PlayerActionNotAllowedException e) {
            }
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 2", 2, player.getDiceThrows());
            try {
                player.openCup();
                Assert.fail("The player can not open the cup.");
            } catch (PlayerActionNotAllowedException e) {
            }
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 3", 3, player.getDiceThrows());
            try {
                player.rollTheDice();
                Assert.fail("The player can not roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 3", 3, player.getDiceThrows());
        }
    }

    /**
     * This method test the player action "open the cup".
     */
    @Test
    public void rollTheDiceAndOpenTheCupTest2(){
        for(final Player player: players) {
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
            try {
                player.openCup();
                Assert.fail("The player can not open the cup");
            } catch (PlayerActionNotAllowedException e) {
            }
        }
    }

    /**
     * This method test the player action "open the cup".
     */
    @Test
    public void rollTheDiceAndOpenTheCupTest3(){
        for(final Player player: players) {
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 2", 2, player.getDiceThrows());
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 3", 3, player.getDiceThrows());
            try {
                player.openCup();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can open the cup");
            }
        }
    }
    /**
     * This method test the player action "open the cup".
     */
    @Test
    public void rollTheDiceAndOpenTheCupTest4(){
        for(final Player player: players) {
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            try {
                player.openCup();
                Assert.fail("The player can not open the cup");
            } catch (PlayerActionNotAllowedException e) {

            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            try {
                player.openCup();
                Assert.fail("The player can not open the cup");
            } catch (PlayerActionNotAllowedException e) {

            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 2", 2, player.getDiceThrows());
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 3", 3, player.getDiceThrows());
            try {
                player.openCup();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can open the cup");
            }
        }
    }
}
