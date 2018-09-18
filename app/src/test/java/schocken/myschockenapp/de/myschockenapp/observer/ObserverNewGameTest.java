package schocken.myschockenapp.de.myschockenapp.observer;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import schocken.myschockenapp.de.myschockenapp.factory.PlayerCreator;
import schocken.myschockenapp.de.myschockenapp.observer.impl.ObserververImpl;
import schocken.myschockenapp.de.myschockenapp.player.Player;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class tests the method "newGame" of the {@link Observer}.
 */
public class ObserverNewGameTest {

    /**
     * This class tests the method "newGame".
     */
    @Test
    public void newGameTest1(){
        Observer observer = spy(new ObserververImpl());
        observer.createPlayers(new String[]{"Marco","Michelle"});
        Assert.assertNull(observer.getCurrentPlayer());
        observer.newGame();
        Assert.assertNotNull(observer.getCurrentPlayer());
        Assert.assertEquals("The names should be equal","Marco",observer.getCurrentPlayer().getName());
    }

    /**
     * This class tests the method "newGame".
     */
    @Test
    public void newGameTest2(){
        PlayerCreator playerCreator = mock(PlayerCreator.class);
        try {
            Field instance = PlayerCreator.class.getDeclaredField("INSTANCE");
            instance.setAccessible(true);
            instance.set(instance, playerCreator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Observer observer = spy(new ObserververImpl());
        Player player1 = mock(Player.class);
        when(player1.getName()).thenReturn("Marco");
        Player player2 = mock(Player.class);
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        when(playerCreator.createPlayers(ArgumentMatchers.any(String[].class),ArgumentMatchers.any(PlayerCallBack.class))).thenReturn(players);
        observer.createPlayers(new String[]{});
        observer.newGame();
        verify(player1).turn();
    }


}
