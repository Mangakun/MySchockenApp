//package schocken.myschockenapp.de.myschockenapp.observer;
//
//import junit.framework.Assert;
//
//import org.junit.Test;
//import org.mockito.ArgumentMatchers;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//
//import schocken.myschockenapp.de.myschockenapp.dice.DiceValue;
//import schocken.myschockenapp.de.myschockenapp.dice.impl.DiceImpl;
//import schocken.myschockenapp.de.myschockenapp.player.Player;
//import schocken.myschockenapp.de.myschockenapp.domain.player.impl.PlayerImpl;
//import schocken.myschockenapp.de.myschockenapp.factory.PlayerCreator;
//import schocken.myschockenapp.de.myschockenapp.observer.exceptions.NotEnoughPlayerException;
//import schocken.myschockenapp.de.myschockenapp.observer.impl.GameObserver;
//import schocken.myschockenapp.de.myschockenapp.player2.Player;
//import schocken.myschockenapp.de.myschockenapp.player2.PlayerImpl.PlayerImpl;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.spy;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
///**
// * This class tests the distribution of the coasters
// */
//public class ObserverDistributeCoastersTest {
//
//    /**
//     * This method tests the distribution of the coasters
//     */
//    @Test
//    public void distributeCoastersTest(){
//        PlayerCreator playerCreator = mock(PlayerCreator.class);
//        try {
//            Field instance = PlayerCreator.class.getDeclaredField("INSTANCE");
//            instance.setAccessible(true);
//            instance.set(instance, playerCreator);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        Observer observer = spy(new GameObserver());
//        Player player1 = spy(new PlayerImpl("Marco",(PlayerCallBack)observer));
//        // dice 1
//        DiceValue value1 = spy(new DiceImpl());
//        when(value1.getValue()).thenReturn(1);
//        // dice 2
//        DiceValue value2 = spy(new DiceImpl());
//        when(value2.getValue()).thenReturn(5);
//        // dice 3
//        DiceValue value3 = spy(new DiceImpl());
//        when(value3.getValue()).thenReturn(4);
//        // create list
//        List<DiceValue> list1 = new ArrayList<DiceValue>();
//        list1.add(value1);
//        list1.add(value2);
//        list1.add(value3);
//        when(player1.getDicesValuesOut()).thenReturn(list1);
//
//        Player player2 = spy(new PlayerImpl("Michelle",(PlayerCallBack)observer));
//        // dice 1
//        DiceValue p2value1 = spy(new DiceImpl());
//        when(p2value1.getValue()).thenReturn(2);
//        // dice 2
//        DiceValue p2value2 = spy(new DiceImpl());
//        when(p2value2.getValue()).thenReturn(5);
//        // dice 3
//        DiceValue p2value3 = spy(new DiceImpl());
//        when(p2value3.getValue()).thenReturn(4);
//        // create list
//        List<DiceValue> list2 = new ArrayList<DiceValue>();
//        list2.add(p2value1);
//        list2.add(p2value2);
//        list2.add(p2value3);
//        when(player2.getDicesValuesOut()).thenReturn(list2);
//
//        List<Player> players = new ArrayList<>();
//        players.add(player1);
//        players.add(player2);
//        when(playerCreator.createPlayers(ArgumentMatchers.any(String[].class),ArgumentMatchers.any(PlayerCallBack.class))).thenReturn(players);
//        try {
//            observer.createPlayers(new String[] {"marco","michelle"});
//        } catch (NotEnoughPlayerException e) {
//            Assert.fail("There are enough players");
//        }
//
//        observer.newGame();
//        verify(player1).turn();
//        Assert.assertEquals("The current player should be player 1",player1,observer.getCurrentPlayer());
//
//        try {
//            player1.rollTheDice();
//        } catch (PlayerActionNotAllowedException e) {
//
//        }
//        try {
//            player1.stay();
//        } catch (PlayerActionNotAllowedException e) {
//
//        }
//
//        verify((PlayerCallBack)observer).callback(ArgumentMatchers.eq(player1),ArgumentMatchers.eq(true));
//        Assert.assertEquals("The current best player should be player 1",player1,observer.getCurrentBestPlayer());
//        Assert.assertEquals("The worst player should be player 1", player1, observer.getCurrentWorstPlayer());
//        try {
//            verify(player1).setMaxDiceThrows(ArgumentMatchers.eq(1));
//        } catch (MaxDiceThrowException e) {
//            Assert.fail();
//        }
//        try {
//            verify(player2).setMaxDiceThrows(ArgumentMatchers.eq(1));
//        } catch (MaxDiceThrowException e) {
//            Assert.fail();
//        }
//
//        try {
//            player2.rollTheDice();
//        } catch (PlayerActionNotAllowedException e) {
//            Assert.fail("The player is able to roll the dices");
//        }
//        try {
//            player2.rollTheDice();
//            Assert.fail("The player is not able to roll the dices");
//        } catch (PlayerActionNotAllowedException e) {
//
//        }
//        try {
//            player2.stay();
//            Assert.fail("The player is not able to call stay");
//        } catch (PlayerActionNotAllowedException e) {
//
//        }
//
//        verify((PlayerCallBack)observer).callback(ArgumentMatchers.eq(player2),ArgumentMatchers.eq(false));
//        verify(player2,times(2)).turn();
//
//        try {
//            player2.openCup();
//        } catch (PlayerActionNotAllowedException e) {
//            Assert.fail("The player is allowed to open the cup");
//        }
//
//        try {
//            player2.stay();
//        } catch (PlayerActionNotAllowedException e) {
//            Assert.fail("The player is able to call stay");
//        }
//        verify((PlayerCallBack)observer).callback(ArgumentMatchers.eq(player2),ArgumentMatchers.eq(true));
//        Assert.assertEquals("The current best player should be player 2",player2,observer.getCurrentBestPlayer());
//        Assert.assertEquals("The worst player should be player 1", player1, observer.getCurrentWorstPlayer());
//
//        try {
//            verify(player1).addCoasters(ArgumentMatchers.eq(1));
//        } catch (MaxCoastersException e) {
//            Assert.fail("No error here");
//        }
//        Assert.assertEquals("The coasters should be 1",1,player1.getCoasters());
//    }
//
//
//    /**
//     * This method tests the distribution of the coasters
//     */
//    @Test
//    public void distributeCoastersTest2(){
//        PlayerCreator playerCreator = mock(PlayerCreator.class);
//        try {
//            Field instance = PlayerCreator.class.getDeclaredField("INSTANCE");
//            instance.setAccessible(true);
//            instance.set(instance, playerCreator);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        Observer observer = spy(new GameObserver());
//        Player player1 = spy(new PlayerImpl("Marco",(PlayerCallBack)observer));
//        // dice 1
//        DiceValue value1 = spy(new DiceImpl());
//        when(value1.getValue()).thenReturn(1);
//        // dice 2
//        DiceValue value2 = spy(new DiceImpl());
//        when(value2.getValue()).thenReturn(5);
//        // dice 3
//        DiceValue value3 = spy(new DiceImpl());
//        when(value3.getValue()).thenReturn(4);
//        // create list
//        List<DiceValue> list1 = new ArrayList<DiceValue>();
//        list1.add(value1);
//        list1.add(value2);
//        list1.add(value3);
//        when(player1.getDicesValuesOut()).thenReturn(list1);
//
//        Player player2 = spy(new PlayerImpl("Michelle",(PlayerCallBack)observer));
//        // dice 1
//        DiceValue p2value1 = spy(new DiceImpl());
//        when(p2value1.getValue()).thenReturn(1);
//        // dice 2
//        DiceValue p2value2 = spy(new DiceImpl());
//        when(p2value2.getValue()).thenReturn(1);
//        // dice 3
//        DiceValue p2value3 = spy(new DiceImpl());
//        when(p2value3.getValue()).thenReturn(1);
//        // create list
//        List<DiceValue> list2 = new ArrayList<DiceValue>();
//        list2.add(p2value1);
//        list2.add(p2value2);
//        list2.add(p2value3);
//        when(player2.getDicesValuesOut()).thenReturn(list2);
//
//        List<Player> players = new ArrayList<>();
//        players.add(player1);
//        players.add(player2);
//        when(playerCreator.createPlayers(ArgumentMatchers.any(String[].class),ArgumentMatchers.any(PlayerCallBack.class))).thenReturn(players);
//        try {
//            observer.createPlayers(new String[] {"marco","michelle"});
//        } catch (NotEnoughPlayerException e) {
//            Assert.fail("There are enough players");
//        }
//
//        observer.newGame();
//        verify(player1).turn();
//        Assert.assertEquals("The current player should be player 1",player1,observer.getCurrentPlayer());
//
//        try {
//            player1.rollTheDice();
//        } catch (PlayerActionNotAllowedException e) {
//
//        }
//        try {
//            player1.stay();
//        } catch (PlayerActionNotAllowedException e) {
//
//        }
//
//        verify((PlayerCallBack)observer).callback(ArgumentMatchers.eq(player1),ArgumentMatchers.eq(true));
//        Assert.assertEquals("The current best player should be player 1",player1,observer.getCurrentBestPlayer());
//        Assert.assertEquals("The worst player should be player 1", player1, observer.getCurrentWorstPlayer());
//        try {
//            verify(player1).setMaxDiceThrows(ArgumentMatchers.eq(1));
//        } catch (MaxDiceThrowException e) {
//            Assert.fail();
//        }
//        try {
//            verify(player2).setMaxDiceThrows(ArgumentMatchers.eq(1));
//        } catch (MaxDiceThrowException e) {
//            Assert.fail();
//        }
//
//        try {
//            player2.rollTheDice();
//        } catch (PlayerActionNotAllowedException e) {
//            Assert.fail("The player is able to roll the dices");
//        }
//        try {
//            player2.rollTheDice();
//            Assert.fail("The player is not able to roll the dices");
//        } catch (PlayerActionNotAllowedException e) {
//
//        }
//        try {
//            player2.stay();
//            Assert.fail("The player is not able to call stay");
//        } catch (PlayerActionNotAllowedException e) {
//
//        }
//
//        verify((PlayerCallBack)observer).callback(ArgumentMatchers.eq(player2),ArgumentMatchers.eq(false));
//        verify(player2,times(2)).turn();
//
//        try {
//            player2.openCup();
//        } catch (PlayerActionNotAllowedException e) {
//            Assert.fail("The player is allowed to open the cup");
//        }
//
//        try {
//            player2.stay();
//        } catch (PlayerActionNotAllowedException e) {
//            Assert.fail("The player is able to call stay");
//        }
//        verify((PlayerCallBack)observer).callback(ArgumentMatchers.eq(player2),ArgumentMatchers.eq(true));
//        Assert.assertEquals("The current best player should be player 2",player2,observer.getCurrentBestPlayer());
//        Assert.assertEquals("The worst player should be player 1", player1, observer.getCurrentWorstPlayer());
//
//
//        try {
//            verify(player1,never()).addCoasters(ArgumentMatchers.anyInt());
//        } catch (MaxCoastersException e) {
//            Assert.fail("No error here");
//        }
//        try {
//            verify(player1).setCoasters(ArgumentMatchers.eq(13));
//        } catch (MaxCoastersException e) {
//            Assert.fail();
//        }
//        Assert.assertEquals("The coasters should be 13",13,player1.getCoasters());
//        Assert.assertEquals("Player 1 should have one half",1,player1.getHalfs());
//        Assert.assertEquals("The coasters should be 0",0,player2.getCoasters());
//    }
//
//
//}
