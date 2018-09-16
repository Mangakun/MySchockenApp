package schocken.myschockenapp.de.myschockenapp.player;

import junit.framework.Assert;

import org.junit.Test;

import schocken.myschockenapp.de.myschockenapp.player.exceptions.PlayerActionNotAllowedException;


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
}
