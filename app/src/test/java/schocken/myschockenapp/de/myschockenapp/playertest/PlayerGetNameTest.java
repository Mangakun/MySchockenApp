package schocken.myschockenapp.de.myschockenapp.playertest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import schocken.myschockenapp.de.myschockenapp.player.Player;
import schocken.myschockenapp.de.myschockenapp.player.dm.PlayerDM;
import schocken.myschockenapp.de.myschockenapp.player.dm.impl.PlayerDMPojo;
import schocken.myschockenapp.de.myschockenapp.player.impl.PlayerImpl;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

/**
 * This class tests the function "getName" of a player.
 */

@RunWith(MockitoJUnitRunner.class)
public class PlayerGetNameTest {

    @Mock
    PlayerDM playerDM;

    @InjectMocks
    Player player = new PlayerImpl(null,null);

    @Test
    public void getNameTest1(){
        String playerName = "Marco";
        Mockito.when(playerDM.getPlayerName()).thenReturn(playerName);
        Assert.assertEquals("The player name should be "+playerName,playerName,player.getName());
    }

}
