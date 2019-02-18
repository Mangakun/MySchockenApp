//package schocken.myschockenapp.de.myschockenapp.player2;
//
//import junit.framework.Assert;
//
//import org.junit.Test;
//
///**
// * This class tests the function "getHalf" and "addHalf"
// */
//public class PlayerHalfTest extends  PlayerTest{
//
//    /**
//     * This method tests the function "getHalf" and "addHalf"
//     */
//    @Test
//    public void halfTest1(){
//        for(final Player player: players) {
//            try {
//                player.addHalf();
//            } catch (MaxHalfException e) {
//                Assert.fail("It is allowed to add a half");
//            }
//            Assert.assertEquals("The player should have 1 halfs",1,player.getHalfs());
//        }
//    }
//    /**
//     * This method tests the function "getHalf" and "addHalf"
//     */
//    @Test
//    public void halfTest2(){
//        for(final Player player: players) {
//            try {
//                player.addHalf();
//            } catch (MaxHalfException e) {
//                Assert.fail("It is allowed to add a half");
//            }
//            Assert.assertEquals("The player should have 1 halfs",1,player.getHalfs());
//            try {
//                player.addHalf();
//            } catch (MaxHalfException e) {
//                Assert.fail("It is allowed to add a half");
//            }
//            Assert.assertEquals("The player should have 2 halfs",2,player.getHalfs());
//        }
//    }
//    /**
//     * This method tests the function "getHalf" and "addHalf"
//     */
//    @Test
//    public void halfTest3(){
//        for(final Player player: players) {
//            try {
//                player.addHalf();
//            } catch (MaxHalfException e) {
//                Assert.fail("It is allowed to add a half");
//            }
//            Assert.assertEquals("The player should have 1 halfs",1,player.getHalfs());
//            try {
//                player.addHalf();
//            } catch (MaxHalfException e) {
//                Assert.fail("It is allowed to add a half");
//            }
//            Assert.assertEquals("The player should have 2 halfs",2,player.getHalfs());
//            try {
//                player.addHalf();
//                Assert.fail("It is not allowed to add a half");
//            } catch (MaxHalfException e) {
//
//            }
//        }
//    }
//}
