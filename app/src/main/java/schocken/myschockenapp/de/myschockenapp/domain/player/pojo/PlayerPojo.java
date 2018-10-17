package schocken.myschockenapp.de.myschockenapp.domain.player.pojo;

import java.util.List;

import schocken.myschockenapp.de.myschockenapp.dice.Dice;
import schocken.myschockenapp.de.myschockenapp.player2.dm.PlayerDM;

public class PlayerPojo implements PlayerDM {

    /**
     * The name of the player.
     */
    private String playerName;

    /**
     * The dice throws of a player.
     */
    private int diceThrows;

    /**
     * A list of dices which set the player out.
     */
    private List<Dice> dicesOut;

    /**
     * A list of dices which has the player under the cup and can roll with it.
     */
    private List<Dice> dicesIn;

    /**
     * The max dice throws a player has.
     */
    private int maxDiceThrows;


    /**
     * The coasters of the player.
     */
    private int coasters;

    /**
     * The number of halfs.
     */
    private int halfs;

    /**
     * Boolean, if the player clicked on openTheCup
     */
    private boolean openTheCupCalled;


    public PlayerPojo(){

    }


    public String getPlayerName() {
        return playerName;
    }

    public int getDiceThrows() {
        return diceThrows;
    }

    public List<Dice> getDicesOut() {
        return dicesOut;
    }

    public List<Dice> getDicesIn() {
        return dicesIn;
    }

    public int getMaxDiceThrows() {
        return maxDiceThrows;
    }

    public int getCoasters() {
        return coasters;
    }

    public int getHalfs() {
        return halfs;
    }

    public boolean isOpenTheCupCalled() {
        return openTheCupCalled;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setDiceThrows(int diceThrows) {
        this.diceThrows = diceThrows;
    }

    public void setDicesOut(List<Dice> dicesOut) {
        this.dicesOut = dicesOut;
    }

    public void setDicesIn(List<Dice> dicesIn) {
        this.dicesIn = dicesIn;
    }

    public void setMaxDiceThrows(int maxDiceThrows) {
        this.maxDiceThrows = maxDiceThrows;
    }

    public void setCoasters(int coasters) {
        this.coasters = coasters;
    }

    public void setHalfs(int halfs) {
        this.halfs = halfs;
    }

    public void setOpenTheCupCalled(boolean openTheCupCalled) {
        this.openTheCupCalled = openTheCupCalled;
    }
}
