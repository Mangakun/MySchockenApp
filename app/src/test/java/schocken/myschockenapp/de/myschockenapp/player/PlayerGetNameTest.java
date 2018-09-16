package schocken.myschockenapp.de.myschockenapp.player;

import junit.framework.Assert;

import org.junit.Test;

import schocken.myschockenapp.de.myschockenapp.player.PlayerImpl.PlayerImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

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
