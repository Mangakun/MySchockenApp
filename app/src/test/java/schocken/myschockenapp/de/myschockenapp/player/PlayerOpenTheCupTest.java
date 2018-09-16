package schocken.myschockenapp.de.myschockenapp.player;

import junit.framework.Assert;

import org.junit.Test;


import schocken.myschockenapp.de.myschockenapp.player.exceptions.PlayerActionNotAllowedException;


/**
 * This class tests the function "open the cup".
 */
public class PlayerOpenTheCupTest extends PlayerTest {

    /**
     * This method test method "open the cup".
     */
    @Test
    public void openTheCupTest1(){
        for(final Player player: players) {
            try {
                player.openCup();
                Assert.fail("The player can not open the cup. The player has to roll the dices first");
            } catch (PlayerActionNotAllowedException e) {
            }
        }
    }

    /**
     * This method test method "open the cup".
     */
    @Test
    public void openTheCupTest2(){
        for(final Player player: players) {
            try {
                player.openCup();
                Assert.fail("The player can not open the cup. The player has to roll the dices first");
            } catch (PlayerActionNotAllowedException e) {
            }
            try {
                player.openCup();
                Assert.fail("The player can not open the cup. The player has to roll the dices first");
            } catch (PlayerActionNotAllowedException e) {
            }
        }
    }

    /**
     * This method test method "open the cup".
     */
    @Test
    public void openTheCupTest3(){
        for(final Player player: players) {
            try {
                player.openCup();
                Assert.fail("The player can not open the cup. The player has to roll the dices first");
            } catch (PlayerActionNotAllowedException e) {
            }
            try {
                player.openCup();
                Assert.fail("The player can not open the cup. The player has to roll the dices first");
            } catch (PlayerActionNotAllowedException e) {
            }
            try {
                player.openCup();
                Assert.fail("The player can not open the cup. The player has to roll the dices first");
            } catch (PlayerActionNotAllowedException e) {
            }
        }
    }
}
