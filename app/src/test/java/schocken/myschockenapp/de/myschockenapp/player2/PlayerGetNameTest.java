package schocken.myschockenapp.de.myschockenapp.player2;

import junit.framework.Assert;

import org.junit.Test;

import schocken.myschockenapp.de.myschockenapp.player2.PlayerImpl.PlayerImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

/**
 * This class tests the function "getName" of a player.
 */
public class PlayerGetNameTest {

    /**
     * This method tests the function "getName".
     */
    @Test
    public void getNameTest1(){
        final String playerName = "Marco";
        Player player = spy(new PlayerImpl(playerName));
        Assert.assertEquals("The name should be "+playerName,playerName,player.getName());
    }
}
