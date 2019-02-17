package schocken.myschockenapp.de.myschockenapp.player2;


import junit.framework.Assert;

import org.junit.Test;

/**
 * This class tests the function "setMaxDiceThrows".
 */
public class PlayerMaxDiceThrowsTest extends PlayerTest{

    /**
     * This method tests the function "setMaxDiceThrows"
     */
    @Test
    public void setMaxDiceThrowsTest1() {
        for (final Player player: players){
            try {
                player.setMaxDiceThrows(2);
            } catch (MaxDiceThrowException e) {
                Assert.fail("It is allowed to set 2 as max dices throws");
            }
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("It is allowed to roll the dices");
            }
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("It is allowed to roll the dices");
            }
            try {
                player.rollTheDice();
                Assert.fail("It is not allowed to roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
        }
    }

    /**
     * This method tests the function "setMaxDiceThrows"
     */
    @Test
    public void setMaxDiceThrowsTest2() {
        for (final Player player: players){
            try {
                player.setMaxDiceThrows(0);
                Assert.fail("It is allowed to set 0 as max dices throws");
            } catch (MaxDiceThrowException e) {

            }
        }
    }

    /**
     * This method tests the function "setMaxDiceThrows"
     */
    @Test
    public void setMaxDiceThrowsTest3() {
        for (final Player player: players){
            try {
                player.setMaxDiceThrows(4);
                Assert.fail("It is allowed to set 4 as max dices throws");
            } catch (MaxDiceThrowException e) {

            }
        }
    }

    /**
     * This method tests the function "setMaxDiceThrows"
     */
    @Test
    public void setMaxDiceThrowsTest4() {
        for (final Player player: players){
            try {
                player.setMaxDiceThrows(1);
            } catch (MaxDiceThrowException e) {
                Assert.fail("It is allowed to set 2 as max dices throws");
            }
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("It is allowed to roll the dices");
            }
            try {
                player.rollTheDice();
                Assert.fail("It is not allowed to roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
            try {
                player.rollTheDice();
                Assert.fail("It is not allowed to roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
        }
    }


}
