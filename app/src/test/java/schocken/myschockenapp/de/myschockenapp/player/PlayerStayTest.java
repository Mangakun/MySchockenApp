package schocken.myschockenapp.de.myschockenapp.player;

import junit.framework.Assert;

import org.junit.Test;

import schocken.myschockenapp.de.myschockenapp.player.PlayerImpl.PlayerImpl;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.PlayerActionNotAllowedException;

/**
 * This class tests the players action "stay".
 */
public class PlayerStayTest {

    @Test
    public void stayTest1(){
        final Player player = new PlayerImpl("Marco");
        try {
            player.stay();
            Assert.fail("The player cant call test");
        } catch (PlayerActionNotAllowedException e) {

        }
    }

    @Test
    public void stayTest2(){
        final Player player = new PlayerImpl("Marco");
        try {
            player.stay();
            Assert.fail("The player cant call stay");
        } catch (PlayerActionNotAllowedException e) {

        }
        try {
            player.stay();
            Assert.fail("The player cant call stay");
        } catch (PlayerActionNotAllowedException e) {

        }
    }

    @Test
    public void stayTest3(){
        final Player player = new PlayerImpl("Marco");
        {
            for (int i = 0; i < 1000000; ++i) {
                try {
                    player.stay();
                    Assert.fail("The player cant call stay");
                } catch (PlayerActionNotAllowedException e) {

                }
            }
        }
    }
}
