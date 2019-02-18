//package schocken.myschockenapp.de.myschockenapp.player2;//package de.schocken.marco.schocken_v1.player;
//
//import junit.framework.Assert;
//
//import org.junit.Test;
//
//
///**
// * This method tests the player action "open the cup".
// * Created by Snaki on 18.11.2017.
// */
//
//public class PlayerRollTheDiceStayAndOpenTheCupTest extends PlayerTest {
//    /**
//     * This method test the player actions "open the cup, stay and open the cup".
//     */
//    @Test
//    public void rollTheDiceStayAndOpenTheCupTest1(){
//        for(final Player player: players) {
//            try {
//                player.stay();
//                Assert.fail("The player can not call stay.");
//            } catch (PlayerActionNotAllowedException e) {
//            }
//            try {
//                player.openCup();
//                Assert.fail("The player can not open the cup.");
//            } catch (PlayerActionNotAllowedException e) {
//            }
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
//        }
//    }
//
//    /**
//     * This method test the player actions "open the cup, stay and open the cup".
//     */
//    @Test
//    public void rollTheDiceStayAndOpenTheCupTest2(){
//        for(final Player player: players) {
//            try {
//                player.openCup();
//                Assert.fail("The player can not call stay.");
//            } catch (PlayerActionNotAllowedException e) {
//            }
//            try {
//                player.stay();
//                Assert.fail("The player can not open the cup.");
//            } catch (PlayerActionNotAllowedException e) {
//            }
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
//        }
//    }
//
//    /**
//     * This method test the player actions "open the cup, stay and open the cup".
//     */
//    @Test
//    public void rollTheDiceStayAndOpenTheCupTest3(){
//        for(final Player player: players) {
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
//            try {
//                player.openCup();
//                Assert.fail("The player can not open the cup.");
//            } catch (PlayerActionNotAllowedException e) {
//            }
//            try {
//                player.stay();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can call stay.");
//            }
//
//        }
//    }
//    /**
//     * This method test the player actions "open the cup, stay and open the cup".
//     */
//    @Test
//    public void rollTheDiceStayAndOpenTheCupTest4(){
//        for(final Player player: players) {
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
//
//            try {
//                player.stay();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can call stay.");
//            }
//            try {
//                player.openCup();
//                Assert.fail("The player can not open the cup.");
//            } catch (PlayerActionNotAllowedException e) {
//            }
//        }
//    }
//    /**
//     * This method test the player actions "open the cup, stay and open the cup".
//     */
//    @Test
//    public void rollTheDiceStayAndOpenTheCupTest5(){
//        for(final Player player: players) {
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 2", 2, player.getDiceThrows());
//            try {
//                player.stay();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can call stay.");
//            }
//            try {
//                player.openCup();
//                Assert.fail("The player can not open the cup.");
//            } catch (PlayerActionNotAllowedException e) {
//            }
//        }
//    }
//    /**
//     * This method test the player actions "open the cup, stay and open the cup".
//     */
//    @Test
//    public void rollTheDiceStayAndOpenTheCupTest6(){
//        for(final Player player: players) {
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 2", 2, player.getDiceThrows());
//            try {
//                player.openCup();
//                Assert.fail("The player can not open the cup.");
//            } catch (PlayerActionNotAllowedException e) {
//            }
//            try {
//                player.stay();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can call stay.");
//            }
//
//        }
//    }
//    /**
//     * This method test the player actions "open the cup, stay and open the cup".
//     */
//    @Test
//    public void rollTheDiceStayAndOpenTheCupTest7(){
//        for(final Player player: players) {
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 2", 2, player.getDiceThrows());
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 3", 3, player.getDiceThrows());
//            try {
//                player.openCup();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can open the cup.");
//            }
//            try {
//                player.stay();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can not call stay.");
//            }
//        }
//    }
//    /**
//     * This method test the player actions "open the cup, stay and open the cup".
//     */
//    @Test
//    public void rollTheDiceStayAndOpenTheCupTest8(){
//        for(final Player player: players) {
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 1", 1, player.getDiceThrows());
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 2", 2, player.getDiceThrows());
//            try {
//                player.rollTheDice();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can roll the dices");
//            }
//            Assert.assertEquals("The player dices throws (" + player.getDiceThrows() + ") should be equal 3", 3, player.getDiceThrows());
//
//            try {
//                player.stay();
//                Assert.fail("The player can not call stay.");
//            } catch (PlayerActionNotAllowedException e) {
//
//            }
//            try {
//                player.openCup();
//            } catch (PlayerActionNotAllowedException e) {
//                Assert.fail("The player can open the cup.");
//            }
//        }
//    }
//}
