package schocken.myschockenapp.de.myschockenapp.player;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import schocken.myschockenapp.de.myschockenapp.dice.DiceValue;
import schocken.myschockenapp.de.myschockenapp.dice.impl.DiceImpl;
import schocken.myschockenapp.de.myschockenapp.player.PlayerImpl.PlayerImpl;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.NotEnoughDicesOutException;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class PlayerCoasterOfDiceValueTest {


    @Test
    public void playerCoasterOfDiceValueTest1(){
        // dice 1
        DiceValue value1 = spy(new DiceImpl());
        when(value1.getValue()).thenReturn(1);
        //when(value1.compareTo(ArgumentMatchers.any(DiceValue.class))).thenCallRealMethod();
        // dice 2
        DiceValue value2 = spy(new DiceImpl());
        when(value2.getValue()).thenReturn(5);
        //when(value1.compareTo(ArgumentMatchers.any(DiceValue.class))).thenCallRealMethod();
        // dice 3
        DiceValue value3 = spy(new DiceImpl());
        when(value3.getValue()).thenReturn(4);
        //when(value1.compareTo(ArgumentMatchers.any(DiceValue.class))).thenCallRealMethod();
        // create list
        List<DiceValue> list = new ArrayList<DiceValue>();
        list.add(value1);
        list.add(value2);
        list.add(value3);
        // output
//        for(DiceValue diceValue : list){
//            System.out.println(diceValue.getValue());
//        }
//        Collections.sort(list);
//        System.out.println("++++++++++++++++++");
//        for(DiceValue diceValue : list){
//            System.out.println(diceValue.getValue());
//        }

        // mock player
        Player player = spy(new PlayerImpl(null,null));
        when(player.getDicesValuesOut()).thenReturn(list);
        // Test output
//        List<DiceValue> listTMp = player.getDicesValuesOut();
//        System.out.println("++++++++++++++++++");
//        for(DiceValue diceValue : list){
//            System.out.println(diceValue.getValue());
//        }
        try {
            Assert.assertEquals("Es wird eine 1 erwartet",1,player.getCoastersOfDiceValue());
        } catch (NotEnoughDicesOutException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void playerCoasterOfDiceValueTest2(){
        // dice 1
        DiceValue value1 = spy(new DiceImpl());
        when(value1.getValue()).thenReturn(3);
        // dice 2
        DiceValue value2 = spy(new DiceImpl());
        when(value2.getValue()).thenReturn(5);
        // dice 3
        DiceValue value3 = spy(new DiceImpl());
        when(value3.getValue()).thenReturn(4);
        // create list
        List<DiceValue> list = new ArrayList<DiceValue>();
        list.add(value1);
        list.add(value2);
        list.add(value3);
        // mock player
        Player player = spy(new PlayerImpl(null,null));
        when(player.getDicesValuesOut()).thenReturn(list);
        try {
            Assert.assertEquals("Es wird eine 2 erwartet",2,player.getCoastersOfDiceValue());
        } catch (NotEnoughDicesOutException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void playerCoasterOfDiceValueTest3(){
        // dice 1
        DiceValue value1 = spy(new DiceImpl());
        when(value1.getValue()).thenReturn(1);
        // dice 2
        DiceValue value2 = spy(new DiceImpl());
        when(value2.getValue()).thenReturn(5);
        // dice 3
        DiceValue value3 = spy(new DiceImpl());
        when(value3.getValue()).thenReturn(1);
        // create list
        List<DiceValue> list = new ArrayList<DiceValue>();
        list.add(value1);
        list.add(value2);
        list.add(value3);
        // mock player
        Player player = spy(new PlayerImpl(null,null));
        when(player.getDicesValuesOut()).thenReturn(list);
        try {
            Assert.assertEquals("Es wird eine 5 erwartet",5,player.getCoastersOfDiceValue());
        } catch (NotEnoughDicesOutException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void playerCoasterOfDiceValueTest4(){
        // dice 1
        DiceValue value1 = spy(new DiceImpl());
        when(value1.getValue()).thenReturn(1);
        // dice 2
        DiceValue value2 = spy(new DiceImpl());
        when(value2.getValue()).thenReturn(1);
        // dice 3
        DiceValue value3 = spy(new DiceImpl());
        when(value3.getValue()).thenReturn(1);
        // create list
        List<DiceValue> list = new ArrayList<DiceValue>();
        list.add(value1);
        list.add(value2);
        list.add(value3);
        // mock player
        Player player = spy(new PlayerImpl(null,null));
        when(player.getDicesValuesOut()).thenReturn(list);
        try {
            Assert.assertEquals("Es wird eine 13 erwartet",13,player.getCoastersOfDiceValue());
        } catch (NotEnoughDicesOutException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void playerCoasterOfDiceValueTest5(){
        // dice 1
        DiceValue value1 = spy(new DiceImpl());
        when(value1.getValue()).thenReturn(1);
        // dice 2
        DiceValue value2 = spy(new DiceImpl());
        when(value2.getValue()).thenReturn(1);
        // dice 3
        DiceValue value3 = spy(new DiceImpl());
        when(value3.getValue()).thenReturn(3);
        // create list
        List<DiceValue> list = new ArrayList<DiceValue>();
        list.add(value1);
        list.add(value2);
        list.add(value3);
        // mock player
        Player player = spy(new PlayerImpl(null,null));
        when(player.getDicesValuesOut()).thenReturn(list);
        try {
            Assert.assertEquals("Es wird eine 3 erwartet",3,player.getCoastersOfDiceValue());
        } catch (NotEnoughDicesOutException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void playerCoasterOfDiceValueTest6(){
        // dice 1
        DiceValue value1 = spy(new DiceImpl());
        when(value1.getValue()).thenReturn(3);
        // dice 2
        DiceValue value2 = spy(new DiceImpl());
        when(value2.getValue()).thenReturn(3);
        // dice 3
        DiceValue value3 = spy(new DiceImpl());
        when(value3.getValue()).thenReturn(3);
        // create list
        List<DiceValue> list = new ArrayList<DiceValue>();
        list.add(value1);
        list.add(value2);
        list.add(value3);
        // mock player
        Player player = spy(new PlayerImpl(null,null));
        when(player.getDicesValuesOut()).thenReturn(list);
        try {
            Assert.assertEquals("Es wird eine 3 erwartet",3,player.getCoastersOfDiceValue());
        } catch (NotEnoughDicesOutException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void playerCoasterOfDiceValueTest7(){
        // dice 1
        DiceValue value1 = spy(new DiceImpl());
        when(value1.getValue()).thenReturn(3);
        // dice 2
        DiceValue value2 = spy(new DiceImpl());
        when(value2.getValue()).thenReturn(6);
        // dice 3
        DiceValue value3 = spy(new DiceImpl());
        when(value3.getValue()).thenReturn(5);
        // create list
        List<DiceValue> list = new ArrayList<DiceValue>();
        list.add(value1);
        list.add(value2);
        list.add(value3);
        // mock player
        Player player = spy(new PlayerImpl(null,null));
        when(player.getDicesValuesOut()).thenReturn(list);
        try {
            Assert.assertEquals("Es wird eine 1 erwartet",1, player.getCoastersOfDiceValue());
        } catch (NotEnoughDicesOutException e) {
            Assert.fail(e.getMessage());
        }
    }
}
