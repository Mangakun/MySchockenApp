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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class tests the method "createPlayers"
 */
public class ObserverCreatePlayersTest {


    /**
     * This method tests the method "createPlayers".
     */
    @Test
    public void createPlayersTest1(){
        Observer observer = spy(new ObserververImpl());
        String[] players = new String[]{"Marco","Michelle"};
        observer.createPlayers(players);
        verify(observer,times(1)).createPlayers(ArgumentMatchers.eq(players));
    }

    /**
     * This method tests the method "createPlayers".
     */
    @Test
    public void createPlayersTest2(){
        PlayerCreator playerCreator = mock(PlayerCreator.class);
        try {
            Field instance = PlayerCreator.class.getDeclaredField("INSTANCE");
            instance.setAccessible(true);
            instance.set(instance, playerCreator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Observer observer = spy(new ObserververImpl());
        String[] players = new String[]{"Marco","Michelle"};
        observer.createPlayers(players);
        verify(observer,times(1)).createPlayers(ArgumentMatchers.eq(players));
        verify(playerCreator).createPlayers(ArgumentMatchers.eq(players),ArgumentMatchers.eq((PlayerCallBack)observer));
    }


    /**
     * This method tests the method "createPlayers".
     */
    @Test
    public void createPlayersTest3(){
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
        Assert.assertEquals("The names should be equal","Marco",observer.getCurrentPlayer().getName());
    }

}
