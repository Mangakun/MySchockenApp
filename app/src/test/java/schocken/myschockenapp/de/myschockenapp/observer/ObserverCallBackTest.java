package schocken.myschockenapp.de.myschockenapp.observer;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import schocken.myschockenapp.de.myschockenapp.factory.PlayerCreator;
import schocken.myschockenapp.de.myschockenapp.observer.exceptions.NotEnoughPlayerException;
import schocken.myschockenapp.de.myschockenapp.observer.impl.ObserverImpl;
import schocken.myschockenapp.de.myschockenapp.player.Player;
import schocken.myschockenapp.de.myschockenapp.player.PlayerImpl.PlayerImpl;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.MaxDiceThrowException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.PlayerActionNotAllowedException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class tests the method "callback".
 */
public class ObserverCallBackTest {

    /**
     * This method tests the method "callback".
     */
    @Test(expected = RuntimeException.class)
    public void callbackTest1(){
        PlayerCallBack playerCallBack = spy(new ObserverImpl());
        Player player1 = spy(new PlayerImpl("Marco",playerCallBack));
        try {
            player1.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {
            Assert.fail("It is allowed to call roll the dice");
        }
        try {
            player1.stay();
        } catch (PlayerActionNotAllowedException e) {
            Assert.fail("It is allowed to call stay");
        }
        verify(playerCallBack).callback(ArgumentMatchers.any(Player.class), ArgumentMatchers.anyBoolean());
    }

    /**
     * This method tests the method "callback".
     */
    @Test
    public void callbackTest2(){
        PlayerCreator playerCreator = mock(PlayerCreator.class);
        try {
            Field instance = PlayerCreator.class.getDeclaredField("INSTANCE");
            instance.setAccessible(true);
            instance.set(instance, playerCreator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Observer observer = spy(new ObserverImpl());
        Player player1 = spy(new PlayerImpl("Marco",(PlayerCallBack)observer));
        Player player2 = spy(new PlayerImpl("Michelle",(PlayerCallBack)observer));
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        when(playerCreator.createPlayers(ArgumentMatchers.any(String[].class),ArgumentMatchers.any(PlayerCallBack.class))).thenReturn(players);
        try {
            observer.createPlayers(new String[]{"Marco","Michelle"});
        } catch (NotEnoughPlayerException e) {
            Assert.fail("There are enough players");
        }
        observer.newGame();
        try {
            player1.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {

        }
        try {
            player1.stay();
        } catch (PlayerActionNotAllowedException e) {

        }
        verify((PlayerCallBack)observer).callback(ArgumentMatchers.any(Player.class), ArgumentMatchers.anyBoolean());
    }

    /**
     * This method tests the method "callback".
     */
    @Test(expected = RuntimeException.class)
    public void callbackTest3(){
        PlayerCreator playerCreator = mock(PlayerCreator.class);
        try {
            Field instance = PlayerCreator.class.getDeclaredField("INSTANCE");
            instance.setAccessible(true);
            instance.set(instance, playerCreator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Observer observer = spy(new ObserverImpl());
        Player player1 = spy(new PlayerImpl("Marco",(PlayerCallBack)observer));
        Player player2 = spy(new PlayerImpl("Michelle",(PlayerCallBack)observer));
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        when(playerCreator.createPlayers(ArgumentMatchers.any(String[].class),ArgumentMatchers.any(PlayerCallBack.class))).thenReturn(players);
        try {
            observer.createPlayers(new String[]{"Marco","Michelle"});
        } catch (NotEnoughPlayerException e) {
            Assert.fail("There are enough players");
        }
        observer.newGame();
        try {
            player2.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {

        }
        try {
            player2.stay();
        } catch (PlayerActionNotAllowedException e) {

        }
        verify((PlayerCallBack)observer).callback(ArgumentMatchers.any(Player.class), ArgumentMatchers.anyBoolean());
    }

    /**
     * This method tests the method "callback".
     */
    @Test
    public void callbackTest4(){
        PlayerCreator playerCreator = mock(PlayerCreator.class);
        try {
            Field instance = PlayerCreator.class.getDeclaredField("INSTANCE");
            instance.setAccessible(true);
            instance.set(instance, playerCreator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Observer observer = spy(new ObserverImpl());
        Player player1 = spy(new PlayerImpl("Marco",(PlayerCallBack)observer));
        Player player2 = spy(new PlayerImpl("Michelle",(PlayerCallBack)observer));
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        when(playerCreator.createPlayers(ArgumentMatchers.any(String[].class),ArgumentMatchers.any(PlayerCallBack.class))).thenReturn(players);
        try {
            observer.createPlayers(new String[]{"Marco","Michelle"});
        } catch (NotEnoughPlayerException e) {
            Assert.fail("There are enough players");
        }
        observer.newGame();
        try {
            player1.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {

        }
        try {
            player1.stay();
        } catch (PlayerActionNotAllowedException e) {

        }
        try {
            verify(player1).setMaxDiceThrows(ArgumentMatchers.eq(1));
        } catch (MaxDiceThrowException e) {
            Assert.fail("It is allowed to set 1 as max dice throws");
        }
        try {
            verify(player2).setMaxDiceThrows(ArgumentMatchers.eq(1));
        } catch (MaxDiceThrowException e) {
            Assert.fail("It is allowed to set 1 as max dice throws");
        }
    }

    /**
     * This method tests the method "callback".
     */
    @Test
    public void callbackTest5(){
        PlayerCreator playerCreator = mock(PlayerCreator.class);
        try {
            Field instance = PlayerCreator.class.getDeclaredField("INSTANCE");
            instance.setAccessible(true);
            instance.set(instance, playerCreator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Observer observer = spy(new ObserverImpl());
        Player player1 = spy(new PlayerImpl("Marco",(PlayerCallBack)observer));
        Player player2 = spy(new PlayerImpl("Michelle",(PlayerCallBack)observer));
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        when(playerCreator.createPlayers(ArgumentMatchers.any(String[].class),ArgumentMatchers.any(PlayerCallBack.class))).thenReturn(players);
        try {
            observer.createPlayers(new String[]{"Marco","Michelle"});
        } catch (NotEnoughPlayerException e) {
            Assert.fail("There are enough players");
        }
        observer.newGame();
        try {
            player1.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {

        }
        try {
            player1.stay();
        } catch (PlayerActionNotAllowedException e) {

        }
        verify(player2).turn();
    }

    /**
     * This method tests the method "callback".
     */
    @Test
    public void callbackTest6(){
        PlayerCreator playerCreator = mock(PlayerCreator.class);
        try {
            Field instance = PlayerCreator.class.getDeclaredField("INSTANCE");
            instance.setAccessible(true);
            instance.set(instance, playerCreator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Observer observer = spy(new ObserverImpl());
        Player player1 = spy(new PlayerImpl("Marco",(PlayerCallBack)observer));
        Player player2 = spy(new PlayerImpl("Michelle",(PlayerCallBack)observer));
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        when(playerCreator.createPlayers(ArgumentMatchers.any(String[].class),ArgumentMatchers.any(PlayerCallBack.class))).thenReturn(players);
        try {
            observer.createPlayers(new String[]{"Marco","Michelle"});
        } catch (NotEnoughPlayerException e) {
            Assert.fail("There are enough players");
        }
        observer.newGame();
        try {
            player1.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {

        }
        try {
            player1.stay();
        } catch (PlayerActionNotAllowedException e) {

        }
        verify(player2).turn();
    }

}
