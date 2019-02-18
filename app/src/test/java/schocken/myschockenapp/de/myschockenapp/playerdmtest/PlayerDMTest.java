package schocken.myschockenapp.de.myschockenapp.playerdmtest;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import schocken.myschockenapp.de.myschockenapp.dice.Dice;
import schocken.myschockenapp.de.myschockenapp.dice.impl.DiceImpl;
import schocken.myschockenapp.de.myschockenapp.player.dm.PlayerDM;
import schocken.myschockenapp.de.myschockenapp.player.dm.impl.PlayerDMPojo;

public class PlayerDMTest {

    @Test
    public void playerDMTest(){
        PlayerDM playerDM = new PlayerDMPojo();
        playerDM.setCoasters(10);
        Assert.assertEquals(playerDM.getCoasters(),10);
        playerDM.setHalfs(1);
        Assert.assertEquals(playerDM.getHalfs(),1);
        playerDM.setMaxDiceThrows(10);
        Assert.assertEquals(playerDM.getMaxDiceThrows(),10);
        Dice dice1 = new DiceImpl();
        Dice dice2 = new DiceImpl();
        Dice dice3 = new DiceImpl();
        List<Dice> list = new ArrayList<>();
        list.add(dice1);
        list.add(dice2);
        list.add(dice3);
        playerDM.setDicesIn(list);
         Assert.assertEquals(dice1, playerDM.getDicesIn().get(0));
        Assert.assertEquals(dice2, playerDM.getDicesIn().get(1));
        Assert.assertEquals(dice3, playerDM.getDicesIn().get(2));
        playerDM.setDicesOut(list);
        Assert.assertEquals(dice1, playerDM.getDicesOut().get(0));
        Assert.assertEquals(dice2, playerDM.getDicesOut().get(1));
        Assert.assertEquals(dice3, playerDM.getDicesOut().get(2));
        playerDM.setPlayerName("Marco");
        Assert.assertEquals(playerDM.getPlayerName(),"Marco");
    }
}
