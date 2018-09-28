package schocken.myschockenapp.de.myschockenapp.observer;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.ArgumentMatchers;

import java.util.ArrayList;
import java.util.List;

import schocken.myschockenapp.de.myschockenapp.factory.PlayerCreator;
import schocken.myschockenapp.de.myschockenapp.observer.exceptions.NotEnoughPlayerException;
import schocken.myschockenapp.de.myschockenapp.player.Player;
import schocken.myschockenapp.de.myschockenapp.player.PlayerImpl.PlayerImpl;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.MaxCoastersException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.MaxDiceThrowException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.PlayerActionNotAllowedException;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameSimulationTest extends AbstractGameSimulationTest{

    /**
     * TODO: documentation
     */
    @Test
    public void gameSimulationTest1(){
        PlayerCreator playerCreator = mockPlayerCreator();
        Observer observer = spyGameObserver();
        Player marco = spy(new PlayerImpl("Marco", (PlayerCallBack) observer));
        Player michelle = spy(new PlayerImpl("Michelle", (PlayerCallBack) observer));
        Player marei = spy(new PlayerImpl("Marei", (PlayerCallBack) observer));
        List<Player> players = new ArrayList<>();
        players.add(marco);
        players.add(michelle);
        players.add(marei);
        when(playerCreator.createPlayers(ArgumentMatchers.any(String[].class),ArgumentMatchers.any(PlayerCallBack.class))).thenReturn(players);
        try {
            observer.createPlayers(new String[] {"x","x","x"});
        } catch (NotEnoughPlayerException e) {
            Assert.fail("There are enough players");
        }
        // set dices for this round
        setDicesOutOfPlayer(marco, 4,5,6);
        setDicesOutOfPlayer(michelle,1,2,3);
        setDicesOutOfPlayer(marei,5,3,2);

        // start a new game
        observer.newGame();

        // Marcos turn
        verify(marco).turn();
        try {
            marco.rollTheDice();
            marco.rollTheDice();
            marco.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // Michelles turn
        verify(michelle).turn();
        try {
            michelle.rollTheDice();
            michelle.rollTheDice();
            michelle.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // Mareis turn
        verify(marei).turn();
        try {
            marei.rollTheDice();
            marei.rollTheDice();
            marei.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // Marcos turn
        verify(marco,times(2)).turn();
        try {
            marco.openCup();
            marco.stay();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // Michelles turn
        verify(michelle,times(2)).turn();
        try {
            michelle.openCup();
            michelle.stay();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // mareis turn
        verify(marei,times(2)).turn();
        try {
            marei.openCup();
            marei.stay();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // distribute coasters
        try {
            verify(marei).addCoasters(ArgumentMatchers.eq(2));
        } catch (MaxCoastersException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // next round
        observer.nextRound();
        // set dices for this round
        setDicesOutOfPlayer(marco, 1,1,3);
        setDicesOutOfPlayer(michelle,3,6,4);
        setDicesOutOfPlayer(marei,1,1,6);

        // Mareis turn
        verify(marei,times(3)).turn();
        try {
            marei.rollTheDice();
            marei.rollTheDice();
            marei.stay();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // Marcos turn
        verify(marco,times(3)).turn();
        try {
            marco.rollTheDice();
            marco.stay();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // Michelles turn
        verify(michelle,times(3)).turn();
        try {
            michelle.rollTheDice();
            michelle.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // Michelles turn
        verify(michelle,times(4)).turn();
        try {
            michelle.openCup();
            michelle.stay();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // distribute coasters
        try {
            verify(michelle).addCoasters(ArgumentMatchers.eq(6));
        } catch (MaxCoastersException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // next round
        observer.nextRound();
        // set dices for this round
        setDicesOutOfPlayer(marco, 3,4,6);
        setDicesOutOfPlayer(michelle,2,3,4);
        setDicesOutOfPlayer(marei,1,2,2);


        // Michelles turn
        verify(michelle,times(5)).turn();
        try {
            michelle.rollTheDice();
            michelle.stay();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // Mareis turn
        verify(marei,times(4)).turn();
        try {
            marei.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // Marcos turn
        verify(marco,times(4)).turn();
        try {
            marco.rollTheDice();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        // Mareis turn
        verify(marei,times(5)).turn();
        try {
            marei.openCup();
            marei.stay();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        // Marcos turn
        verify(marco,times(5)).turn();
        try {
            marco.openCup();
            marco.stay();
        } catch (PlayerActionNotAllowedException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        // distribute coasters
        try {
            verify(marei,times(2)).addCoasters(ArgumentMatchers.eq(2));
        } catch (MaxCoastersException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        // next round
        observer.nextRound();
        // set dices for this round
        setDicesOutOfPlayer(marco, 3,4,6);
        setDicesOutOfPlayer(michelle,2,3,4);
        setDicesOutOfPlayer(marei,1,2,2);


    }
}
