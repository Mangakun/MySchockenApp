package schocken.myschockenapp.de.myschockenapp.player2;

import junit.framework.Assert;

import org.junit.Test;


/**
 * This class tests the player actions "roll the dice" and stay.
 * Created by Snaki on 26.11.2017.
 */

public class PlayerRollTheDiceAndStayTest extends PlayerTest {


    /**
     * This method test the player actions "roll the dice" and stay.
     */
    @Test
    public void rollTheDiceAndStayTest(){
        for(final Player player: players) {
            try {
                player.stay();
                Assert.fail("Player cant call stay. At first he has to roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
            try {
                player.stay();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can not call stay!");
            }
        }
    }

    /**
     * This method test the player actions "roll the dice" and stay.
     */
    @Test
    public void rollTheDiceAndStayTest2(){
        for(final Player player: players) {
            try {
                player.stay();
                Assert.fail("Player cant call stay. At first he has to roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
            try {
                player.stay();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can not call stay!");
            }
            try {
                player.rollTheDice();
                Assert.fail("The player can not roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
        }
    }


    /**
     * This method test the player actions "roll the dice" and stay.
     */
    @Test
    public void rollTheDiceAndStayTest3(){
        for(final Player player: players) {
            try {
                player.stay();
                Assert.fail("Player cant call stay. At first he has to roll the dices");
            } catch (PlayerActionNotAllowedException e) {
            }
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
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 2, player.getDiceThrows());
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 3, player.getDiceThrows());
            try {
                player.stay();
                Assert.fail("The player can not call stay!");
            } catch (PlayerActionNotAllowedException e) {

            }
            try {
                player.rollTheDice();
                Assert.fail("The player can not roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
        }
    }



    /**
     * This method test the player actions "roll the dice" and stay.
     */
    @Test
    public void rollTheDiceAndStayTest4(){
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
                player.stay();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 2", 2, player.getDiceThrows());
            try {
                player.rollTheDice();
                Assert.fail("The player can not roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 2", 2, player.getDiceThrows());
            try {
                player.stay();
                Assert.fail("The player can not call stay!");
            } catch (PlayerActionNotAllowedException e) {

            }
            try {
                player.rollTheDice();
                Assert.fail("The player can not roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 2", 2, player.getDiceThrows());
        }
    }



    /**
     * This method test the player actions "roll the dice" and stay.
     */
    @Test
    public void rollTheDiceAndStayTest5(){
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
                player.stay();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 2", 2, player.getDiceThrows());
            try {
                player.rollTheDice();
                Assert.fail("The player can not roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 2", 2, player.getDiceThrows());
            try {
                player.stay();
                Assert.fail("The player can not call stay!");
            } catch (PlayerActionNotAllowedException e) {

            }
            try {
                player.rollTheDice();
                Assert.fail("The player can not roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 2", 2, player.getDiceThrows());
        }
    }


    /**
     * This method test the player actions "roll the dice" and stay.
     */
    @Test
    public void rollTheDiceAndStayTest6(){
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
                player.rollTheDice();
                Assert.fail("The player can not roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 3", 3, player.getDiceThrows());
            try {
                player.stay();
                Assert.fail("The player can not call stay!");
            } catch (PlayerActionNotAllowedException e) {

            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 3", 3, player.getDiceThrows());
            try {
                player.stay();
                Assert.fail("The player can not call stay!");
            } catch (PlayerActionNotAllowedException e) {

            }
            try {
                player.rollTheDice();
                Assert.fail("The player can not roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 3", 3, player.getDiceThrows());
        }
    }

}