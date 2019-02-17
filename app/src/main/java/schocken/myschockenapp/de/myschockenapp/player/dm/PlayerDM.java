package schocken.myschockenapp.de.myschockenapp.player.dm;

import java.util.List;

import schocken.myschockenapp.de.myschockenapp.dice.Dice;

public interface PlayerDM {


    public String getPlayerName();

    public int getDiceThrows();

    public List<Dice> getDicesOut();

    public List<Dice> getDicesIn();

    public int getMaxDiceThrows();

    public int getCoasters();

    public int getHalfs();
    public boolean isOpenTheCupCalled();

    public void setPlayerName(String playerName);

    public void setDiceThrows(int diceThrows);

    public void setDicesOut(List<Dice> dicesOut);

    public void setDicesIn(List<Dice> dicesIn);

    public void setMaxDiceThrows(int maxDiceThrows);

    public void setCoasters(int coasters);

    public void setHalfs(int halfs);
    public void setOpenTheCupCalled(boolean openTheCupCalled) ;
}
