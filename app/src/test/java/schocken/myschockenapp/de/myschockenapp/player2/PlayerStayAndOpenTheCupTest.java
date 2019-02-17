package schocken.myschockenapp.de.myschockenapp.player2;

import junit.framework.Assert;

import org.junit.Test;


/**
 * This method tests the player action "open the cup".
 * Created by Snaki on 18.11.2017.
 */

public class PlayerStayAndOpenTheCupTest extends PlayerTest {
    /**
     * This method test the player action "open the cup".
     */
    @Test
    public void StayAndOpenTheCupTest1(){
        for(final Player player: players) {
            try {
                player.openCup();
                Assert.fail("The player can not open the cup.");
            } catch (PlayerActionNotAllowedException e) {
            }
            try {
                player.stay();
                Assert.fail("The player can not call stay.");
            } catch (PlayerActionNotAllowedException e) {

            }
        }
    }

    /**
     * This method test the player action "open the cup".
     */
    @Test
    public void StayAndOpenTheCupTest2(){
        for(final Player player: players) {
            try {
                player.stay();
                Assert.fail("The player can not call stay.");
            } catch (PlayerActionNotAllowedException e) {

            }
            try {
                player.openCup();
                Assert.fail("The player can not open the cup.");
            } catch (PlayerActionNotAllowedException e) {
            }
        }
    }

    /**
     * This method test the player action "open the cup".
     */
    @Test
    public void StayAndOpenTheCupTest3(){
        for(final Player player: players) {
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can roll the dices");
            }
            try {
                player.stay();
                Assert.fail("The player can not call stay");
            } catch (PlayerActionNotAllowedException e) {
            }
            try {
                player.openCup();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can open the cup");
            }
            try {
                player.rollTheDice();
                Assert.fail("The player can not roll the dices");
            } catch (PlayerActionNotAllowedException e) {

            }
            try {
                player.openCup();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can open the cup");
            }
            try {
                player.stay();

            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("The player can call stay");
            }
        }
    }
}
