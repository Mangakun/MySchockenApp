//package schocken.myschockenapp.de.myschockenapp.player2;
//
//
//import junit.framework.Assert;
//
//import org.junit.Test;
//
///**
// * This class tests the functions "getCoasters", "setCoasters" and "addCoasters".
// */
//public class PlayerCoastersTest extends PlayerTest{
//
//    /**
//     * This method tests the function "setCoasters"
//     */
//    @Test
//    public void setCoastersTest1() {
//        for (final Player player: players){
//            int coasters = 12;
//            try {
//                player.setCoasters(coasters);
//            } catch (MaxCoastersException e) {
//                Assert.fail("It is possible to set "+coasters+" coasters");
//            }
//            Assert.assertEquals("The penalties should be equal by setPenalty",coasters,player.getCoasters());
//        }
//    }
//
//    /**
//     * This method tests the function "setCoasters"
//     */
//    @Test
//    public void setCoastersTest2() {
//        for (final Player player: players){
//            int coasters = 59;
//            try {
//                player.setCoasters(coasters);
//                Assert.fail("It is not possible to set "+coasters+" coasters");
//            } catch (MaxCoastersException e) {
//
//            }
//        }
//    }
//
//    /**
//     * This method tests the "addCoasters" function.
//     */
//    @Test
//    public void addCoastersTest()  {
//        for (final Player player: players) {
//            for (int i = 0; i < 15; ++i) {
//                try {
//                    player.addCoasters(i);
//                    if(i > 13){
//                        Assert.fail("It is not possible to set more than 13 coasters");
//                    }
//                } catch (MaxCoastersException e) {
//
//                }
//            }
//        }
//    }
//    /**
//     * This method tests the "addCoasters" and "getCoasters" function.
//     */
//    @Test
//    public void addCoastersTest2()  {
//        for (final Player player: players) {
//            try {
//                player.addCoasters(1);
//            } catch (MaxCoastersException e) {
//                Assert.fail("It is possible to add 1 coaster");
//            }
//            Assert.assertEquals("The penalties of the player should be 1",1,player.getCoasters());
//            try {
//                player.addCoasters(1);
//            } catch (MaxCoastersException e) {
//                Assert.fail("It is possible to add 1 coaster");
//            }
//            Assert.assertEquals("The penalties of the player should be 2",2,player.getCoasters());
//            try {
//                player.addCoasters(4);
//            } catch (MaxCoastersException e) {
//                Assert.fail("It is possible to add 4 coaster");
//            }
//            Assert.assertEquals("The penalties of the player should be 6",6,player.getCoasters());
//            try {
//                player.addCoasters(7);
//            } catch (MaxCoastersException e) {
//                e.printStackTrace();
//            }
//            Assert.assertEquals("The penalties of the player should be 13",13,player.getCoasters());
//        }
//    }
//
//    /**
//     * This method tests the "addCoasters" and "getCoasters" function.
//     */
//    @Test
//    public void addCoastersTest3()  {
//        for (final Player player: players) {
//            try {
//                player.addCoasters(1);
//            } catch (MaxCoastersException e) {
//                Assert.fail("It is possible to add 1 coaster");
//            }
//            Assert.assertEquals("The penalties of the player should be 1",1,player.getCoasters());
//            try {
//                player.addCoasters(1);
//            } catch (MaxCoastersException e) {
//                Assert.fail("It is possible to add 1 coaster");
//            }
//            Assert.assertEquals("The penalties of the player should be 2",2,player.getCoasters());
//            try {
//                player.addCoasters(4);
//            } catch (MaxCoastersException e) {
//                Assert.fail("It is possible to add 4 coaster");
//            }
//            Assert.assertEquals("The penalties of the player should be 6",6,player.getCoasters());
//            try {
//                player.addCoasters(8);
//                Assert.fail("It is not possible to add 8 coaster");
//            } catch (MaxCoastersException e) {
//            }
//        }
//    }
//}
