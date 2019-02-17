package schocken.myschockenapp.de.myschockenapp.player2;//package de.schocken.marco.schocken_v1.player;

import junit.framework.Assert;

import org.junit.Test;

/**
 * This method tests the player action "blind".
 * Created by Snaki on 18.11.2017.
 */

public class PlayerNewGameNewHalfNextRoundTest extends PlayerTest {

    /**
     * This method tests the next game function.
     */
    @Test
    public void nextGameTest(){
        for(final Player player: players) {
            player.nextGame();
            Assert.assertEquals("The player should have 0 halfs",0,player.getHalfs());
            Assert.assertEquals("The player should have 0 coasters",0,player.getCoasters());
            Assert.assertEquals("The player should have 0 dice throws",0,player.getDiceThrows());
            try {
                player.addCoasters(5);
            } catch (MaxCoastersException e) {
                Assert.fail("It is allowed to add 5 coasters");
            }
            try {
                player.addHalf();
            } catch (MaxHalfException e) {
                Assert.fail("It is allowed to add a half");
            }
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("It is allowed to roll the dice");
            }
            Assert.assertEquals("The player should have 0 halfs",1,player.getHalfs());
            Assert.assertEquals("The player should have 0 coasters",5,player.getCoasters());
            Assert.assertEquals("The player should have 0 dice throws",1,player.getDiceThrows());
            player.nextGame();
            Assert.assertEquals("The player should have 0 halfs",0,player.getHalfs());
        }
    }

    /**
     * This method tests the next game function.
     */
    @Test
    public void nextGameTest2(){
        for(final Player player: players) {
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
               Assert.fail("The player is able to roll the dices.");
            }
            Assert.assertEquals("The player should have 1 dice throws",1,player.getDiceThrows());
            player.nextGame();
            Assert.assertEquals("The player should have 0 halfs",0,player.getHalfs());
        }
    }
    /**
     * This method tests the next game function.
     */
    @Test
    public void nextHalfTest(){
        for(final Player player: players) {
            try {
                player.addCoasters(5);
            } catch (MaxCoastersException e) {
                Assert.fail("It is allowed to add 5 coasters");
            }
            Assert.assertEquals("The player should have 0 coasters",5,player.getCoasters());
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("It is allowed to roll the dice");
            }
            Assert.assertEquals("The player should have 0 dice throws",1,player.getDiceThrows());
            try {
                player.addHalf();
            } catch (MaxHalfException e) {
                Assert.fail("It is allowed to add a half");
            }
            Assert.assertEquals("The player should have 1 halfs",1,player.getHalfs());
            player.nextHalf();
            Assert.assertEquals("The player should have 0 coasters",0,player.getCoasters());
        }
    }
    /**
     * This method tests the next game function.
     */
    @Test
    public void nextRoundTest(){
        for(final Player player: players) {
            try {
                player.addCoasters(5);
            } catch (MaxCoastersException e) {
                Assert.fail("It is allowed to add 5 coasters");
            }
            Assert.assertEquals("The player should have 0 coasters",5,player.getCoasters());
            try {
                player.rollTheDice();
            } catch (PlayerActionNotAllowedException e) {
                Assert.fail("It is allowed to roll the dice");
            }
            Assert.assertEquals("The player should have 0 dice throws",1,player.getDiceThrows());
            try {
                player.addHalf();
            } catch (MaxHalfException e) {
                Assert.fail("It is allowed to add a half");
            }
            Assert.assertEquals("The player should have 0 halfs",1,player.getHalfs());
            player.nextRound();
            Assert.assertEquals("The player should have 0 coasters",5,player.getCoasters());
            Assert.assertEquals("The player should have 0 dice throws",0,player.getDiceThrows());
            Assert.assertEquals("The player should have 0 halfs",1,player.getHalfs());
        }
    }


}
