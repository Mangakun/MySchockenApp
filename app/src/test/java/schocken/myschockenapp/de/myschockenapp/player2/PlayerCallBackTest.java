package schocken.myschockenapp.de.myschockenapp.player2;

import junit.framework.Assert;

import org.junit.Test;

import schocken.myschockenapp.de.myschockenapp.observer.PlayerCallBack;
import schocken.myschockenapp.de.myschockenapp.player2.PlayerImpl.PlayerImpl;
import schocken.myschockenapp.de.myschockenapp.player2.exceptions.PlayerActionNotAllowedException;

import static org.mockito.Mockito.spy;

/**
 * This class tests the callback function.
 */
public class PlayerCallBackTest {

    /**
     * This method tests the callback function.
     */
    @Test
    public void callBackTest1(){
        final Player player = spy(new PlayerImpl("Marco", new PlayerCallBack() {

            @Override
            public void callback(Player callBackPlayer, boolean finish) {
                Assert.assertEquals("Finish should be true",finish ,true);
            }
        }));
        try {
            player.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {
            Assert.fail("It is allowed to roll the dices");
        }
        try {
            player.stay();
        } catch (PlayerActionNotAllowedException e) {
            Assert.fail("It is allowed to call stay");
        }
    }

    /**
     * This method tests the callback function.
     */
    @Test
    public void callBackTest2(){
        final Player player = spy(new PlayerImpl("Marco", new PlayerCallBack() {
            @Override
            public void callback(Player callBackPlayer, boolean finish) {
                Assert.assertEquals("Finish should be true",finish ,true);
            }
        }));
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
            player.stay();
        } catch (PlayerActionNotAllowedException e) {
            Assert.fail("It is allowed to call stay");
        }
    }
    /**
     * This method tests the callback function.
     */
    @Test
    public void callBackTest3(){
        final Player player = spy(new PlayerImpl("Marco", new PlayerCallBack() {
            @Override
            public void callback(Player callbackPlayer, boolean finish) {
                Assert.assertEquals("Finish should be true",finish ,false);
            }
        }));
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
        } catch (PlayerActionNotAllowedException e) {
            Assert.fail("It is allowed to roll the dices");
        }
    }
}
