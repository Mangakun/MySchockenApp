package schocken.myschockenapp.de.myschockenapp.player.impl;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import schocken.myschockenapp.de.myschockenapp.R;
import schocken.myschockenapp.de.myschockenapp.dice.Dice;
import schocken.myschockenapp.de.myschockenapp.dice.DiceValue;
import schocken.myschockenapp.de.myschockenapp.observer.PlayerCallBack;
import schocken.myschockenapp.de.myschockenapp.player.Player;
import schocken.myschockenapp.de.myschockenapp.player.dm.PlayerDM;
import schocken.myschockenapp.de.myschockenapp.player.dm.impl.PlayerDMPojo;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.DiceNotFoundException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.MaxCoastersException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.MaxDiceThrowException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.MaxHalfException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.NotEnoughDicesOutException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.PlayerActionNotAllowedException;

public class PlayerImpl implements Player {

    /**
     * An object of the class {@link PlayerDMPojo}.
     */
    PlayerDM playerDM;

    /**
     * An object of the class {@link PlayerCallBack}.
     */
    private PlayerCallBack callback;


    public PlayerImpl(final PlayerCallBack callBack, final PlayerDM playerDM){
        this.playerDM = playerDM;
        this.callback = callBack;
    }

    @Override
    public void stay() throws PlayerActionNotAllowedException {
        if (isAbleToCallStay()) {
            playerDM.getDicesOut().addAll(playerDM.getDicesIn());
            playerDM.getDicesIn().clear();
            if(callback != null) {
                this.callback.callback(this,true); // all dices are out
            }
        } else {
            throw new PlayerActionNotAllowedException("The player is not able to call \"stay\"");
        }
    }

    @Override
    public void rollTheDice() throws PlayerActionNotAllowedException {
        // check if the player is able to roll the dices
        if (isAbleToRollTheDices()) {
            playerDM.setDiceThrows(playerDM.getDiceThrows()+1);
            for (int i = 0; i < playerDM.getDicesIn().size(); ++i) {
                playerDM.getDicesIn().get(i).roll();
            }
            if (playerDM.getDiceThrows() == playerDM.getMaxDiceThrows()) {
                if(callback != null){
                    callback.callback(this,false); // because not all dices are out
                }
            }
        } else {
            throw new PlayerActionNotAllowedException("The player is not allowed to roll the dices");
        }
    }

    @Override
    public void openCup() throws PlayerActionNotAllowedException {
        if (isAbleToOpenTheCup()) {
            // TODO: die Würfel sollen noch gezeigt werden und im Anschluss soll der nächste Spiler an der Reihe sein, wenn der Spieler auf "Weiter" oder so klickt.
            playerDM.setOpenTheCupCalled(true);
        } else {
            throw new PlayerActionNotAllowedException("The player is not allowed to open the cup");
        }
    }

    @Override
    public void turn() {

    }

    @Override
    public String getName() {
        return playerDM.getPlayerName();
    }

    @Override
    public void setName(String playerName) {
        playerDM.setPlayerName(playerName);
    }


    /*
     * *********************************************
     * private methods
     * ********************************************
     */

    /**
     * This method checks, if the player is able to call the function "stay".
     * @return True, if the player is able to call stay.
     */
    private boolean isAbleToCallStay() {
        if (playerDM.getDiceThrows() > 0 && playerDM.getDiceThrows() < playerDM.getMaxDiceThrows() && playerDM.getDicesOut().size() != Resources.getSystem().getInteger(R.integer.maxdicesize)) {
            return true;
        }
        if(playerDM.isOpenTheCupCalled()){
            playerDM.setOpenTheCupCalled(false);
            return true;
        }
        return false;
    }

    /**
     * This method checks, if the player is able to call the function "roll the dice".
     * @return True, if the player is able to call stay.
     */
    private boolean isAbleToRollTheDices() {
        if (playerDM.getDicesIn().size() != 0 && playerDM.getDiceThrows() < playerDM.getMaxDiceThrows()) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * This method checks, if the player is able to call the function "open the cup".
     * @return True, if the player is able to call stay.
     */
    private boolean isAbleToOpenTheCup() {
        if (playerDM.getDiceThrows() > 0 && playerDM.getDicesOut().size() != Resources.getSystem().getInteger(R.integer.maxdicesize) && playerDM.getDiceThrows() == playerDM.getMaxDiceThrows()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void diceBackIn(DiceValue diceValue) throws DiceNotFoundException {
        if (!playerDM.getDicesOut().contains(diceValue)) {
            new DiceNotFoundException("The dice value ("+diceValue.getValue()+") can not be found");
        }
        playerDM.getDicesIn().add((Dice) diceValue);
        playerDM.getDicesOut().remove(diceValue);
    }

    @Override
    public void diceOut(DiceValue diceValue) throws DiceNotFoundException {
            if (!playerDM.getDicesIn().contains(diceValue)) {
            new DiceNotFoundException("The dice value ("+diceValue.getValue()+") can not be found");
        }
        playerDM.getDicesOut().add((Dice) diceValue);
        playerDM.getDicesIn().remove(diceValue);
    }

    @Override
    public int getCoasters() {
        return playerDM.getCoasters();
    }

    @Override
    public void addCoasters(int coasters) throws MaxCoastersException {
        playerDM.setCoasters(playerDM.getCoasters()+coasters);
    }

    @Override
    public void setCoasters(int coasters) throws MaxCoastersException {
        playerDM.setCoasters(coasters);
    }

    @Override
    public void removeCoasters(int coasters) {
        playerDM.setCoasters(playerDM.getCoasters()-coasters);
    }

    @Override
    public void nextHalf() {
        playerDM.setCoasters(0);
    }

    @Override
    public void nextRound() {
        playerDM.setOpenTheCupCalled(false);
        playerDM.setDiceThrows(0);
        playerDM.getDicesIn().addAll(playerDM.getDicesOut());
        playerDM.getDicesOut().clear();
        playerDM.setMaxDiceThrows(3);
    }

    @Override
    public void nextGame() {
        playerDM.setHalfs(0);
    }

    @Override
    public int getHalfs() {
        return playerDM.getHalfs();
    }

    @Override
    public int getDiceThrows() {
        return playerDM.getDiceThrows();
    }

    @Override
    public void addHalf() throws MaxHalfException {
        playerDM.setHalfs(playerDM.getHalfs()+1);
    }

    @Override
    public void setMaxDiceThrows(int maxDiceThrows) throws MaxDiceThrowException {
        playerDM.setMaxDiceThrows(maxDiceThrows);
    }

    @Override
    public int getDiceValueForCompare() throws NotEnoughDicesOutException {
        if(getDicesValuesOut().size() <3 ){
            throw new NotEnoughDicesOutException("There are not enough dices out");
        }
        final List<DiceValue> dicesCopy = new ArrayList<>(getDicesValuesOut());
        Collections.sort(dicesCopy);
        for(int i=0;i<dicesCopy.size()-1;++i){
            if(dicesCopy.get(i).getValue() < dicesCopy.get(i+1).getValue()){
                throw new RuntimeException("The dices are not sorted");
            }
        }
        double diceValueForCompare = 0.0;
        int coastersOfDiceValue = getCoastersOfDiceValue();
        for(int i = 0;i<dicesCopy.size();++i){
            diceValueForCompare+=dicesCopy.get(i).getValue()*Math.pow(10,dicesCopy.size()-1-i);
        }
        diceValueForCompare += coastersOfDiceValue * 1000;
        if(dicesCopy.get(1).getValue() == 1){
            diceValueForCompare*=10;
        }
        return (int)diceValueForCompare;
    }

    @Override
    public int getCoastersOfDiceValue() throws NotEnoughDicesOutException {
        if(getDicesValuesOut().size() <3 ){
            throw new NotEnoughDicesOutException("There are not enough dices out");
        }
        final List<DiceValue> dicesCopy = new ArrayList<>(getDicesValuesOut());
        Collections.sort(dicesCopy);
        for(int i=0;i<dicesCopy.size()-1;++i){
            if(dicesCopy.get(i).getValue() < dicesCopy.get(i+1).getValue()){
                throw new RuntimeException("The dices are not sorted");
            }
        }
        final int diceValue1 = dicesCopy.get(0).getValue();
        final int diceValue2 = dicesCopy.get(1).getValue();
        final int diceValue3 = dicesCopy.get(2).getValue();
        // Schocks
        if(diceValue2 == 1 && diceValue3 == 1){
            if(diceValue1 == 1){
                return 13;
            }
            return diceValue1;
        }else{
            // general
            if(diceValue1==diceValue2 && diceValue2 == diceValue3){
                return 3;

            }else{
                if(diceValue1-1 == diceValue2 && diceValue2-1 == diceValue3){
                    return 2;
                }else{
                    return 1;
                }
            }
        }
    }

        @Override
    public List<DiceValue> getDicesValuesIn() {
        return new ArrayList<DiceValue>(playerDM.getDicesIn());
    }

    @Override
    public List<DiceValue> getDicesValuesOut() {
        return new ArrayList<DiceValue>(playerDM.getDicesOut());
    }
}
