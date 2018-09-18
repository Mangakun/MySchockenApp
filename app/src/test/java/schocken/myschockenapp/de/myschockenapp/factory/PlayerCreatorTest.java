package schocken.myschockenapp.de.myschockenapp.factory;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

import schocken.myschockenapp.de.myschockenapp.player.Player;

/**
 * This class tests the factory {@link PlayerCreator}.
 */
public class PlayerCreatorTest {

    /**
     * This class tests the method "createPlayers".
     */
    @Test
    public void playerCreatorTest(){
        PlayerCreator playerCreator = PlayerCreator.getINSTANCE();
        List<Player> playerList = playerCreator.createPlayers(new String[]{"Marco","Michelle"},null);
        Assert.assertEquals("The names should be euqal",playerList.get(0).getName(),"Marco");
        Assert.assertEquals("The names should be euqal",playerList.get(1).getName(),"Michelle");

    }
}
