package schocken.myschockenapp.de.myschockenapp.player.PlayerImpl;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import schocken.myschockenapp.de.myschockenapp.R;
import schocken.myschockenapp.de.myschockenapp.dice.Dice;
import schocken.myschockenapp.de.myschockenapp.dice.DiceValue;
import schocken.myschockenapp.de.myschockenapp.dice.impl.DiceImpl;
import schocken.myschockenapp.de.myschockenapp.gamesettings.GameSettings;
import schocken.myschockenapp.de.myschockenapp.observer.PlayerCallBack;
import schocken.myschockenapp.de.myschockenapp.player.Player;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.DiceNotFoundException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.MaxCoastersException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.MaxDiceThrowException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.MaxHalfException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.NotEnoughDicesOutException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.PlayerActionNotAllowedException;


/**
 * The implementation class of a player
 */
public class PlayerImpl implements Player, GameSettings {

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
     * An object of the class {@link PlayerCallBack}.
     */
    private PlayerCallBack callback;

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

    /**
     * Constructor of the class {@link PlayerImpl}.
     * @param playerName The name of the player.
     */
    public PlayerImpl(final String playerName){
        this(playerName,null);
    }

    /**
     * Constructor of the class {@link PlayerImpl}.
     * @param playerName The name of the player.
     * @param callBack A callback.
     */
    public PlayerImpl(final String playerName, final PlayerCallBack callBack){
        this.playerName = playerName;
        this.diceThrows = 0;
        dicesIn = new ArrayList<>();
        for (int i = 0; i < MAXDICESIZE; ++i) {
            dicesIn.add(new DiceImpl());
        }
        dicesOut = new ArrayList<>();
        maxDiceThrows = MAXDICETRHOWS;
        this.callback = callBack;
    }



    @Override
    public String getName() {
        return playerName;
    }

    @Override
    public void stay() throws PlayerActionNotAllowedException {
        if (isAbleToCallStay()) {
            dicesOut.addAll(dicesIn);
            dicesIn.clear();
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
            ++diceThrows;
            for (int i = 0; i < dicesIn.size(); ++i) {
                dicesIn.get(i).roll();
            }
            if (diceThrows == maxDiceThrows) {
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
            openTheCupCalled = true;
        } else {
            throw new PlayerActionNotAllowedException("The player is not allowed to open the cup");
        }
    }

    @Override
    public void turn() {

    }

    @Override
    public int getCoasters() {
        return coasters;
    }

    @Override
    public void addCoasters(int coasters) throws MaxCoastersException {
        if (this.coasters + coasters > MAXCOASTERS) {
            throw new MaxCoastersException("The player can not have more than 13 coasters");
        }
        this.coasters += coasters;
    }

    @Override
    public void setCoasters(int coasters) throws MaxCoastersException {
        if (coasters > MAXCOASTERS) {
            throw new MaxCoastersException("The player can not have more than 13 coasters");
        }
        this.coasters = coasters;
    }

    @Override
    public void removeCoasters(int coasters) {
        this.coasters-=coasters;
        if(coasters < 0){
            coasters = 0;
        }
    }

    @Override
    public void nextHalf() {
        coasters = 0;
    }

    @Override
    public void nextRound() {
        openTheCupCalled = false;
        diceThrows = 0;
        dicesIn.addAll(dicesOut);
        dicesOut.clear();
        maxDiceThrows = 3;
    }

    @Override
    public void nextGame() {
        halfs = 0;
    }

    @Override
    public int getHalfs() {
        return halfs;
    }

    @Override
    public int getDiceThrows() {
        return diceThrows;
    }

    @Override
    public void addHalf() throws MaxHalfException {
        this.halfs += 1;
        if(this. halfs > 2){
            throw new MaxHalfException("The player can only have maximal 2 halfs");
        }
    }

    @Override
    public void setMaxDiceThrows(int maxDiceThrows) throws MaxDiceThrowException {
        if(maxDiceThrows > 3 || maxDiceThrows < 1){
            throw new MaxDiceThrowException("The player can only have maximal "+MAXDICETRHOWS+ "dice throws and can only have minimal 1 dice throws");
        }
        this.maxDiceThrows = maxDiceThrows;
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
    public void diceBackIn(DiceValue diceValue) throws DiceNotFoundException {
        if (!dicesOut.contains(diceValue)) {
            new DiceNotFoundException("The dice value ("+diceValue.getValue()+") can not be found");
        }
        dicesIn.add((Dice) diceValue);
        dicesOut.remove(diceValue);
    }

    @Override
    public void diceOut(DiceValue diceValue) throws DiceNotFoundException {
        if (!dicesIn.contains(diceValue)) {
            new DiceNotFoundException("The dice value ("+diceValue.getValue()+") can not be found");
        }
        dicesOut.add((Dice) diceValue);
        dicesIn.remove(diceValue);
    }

    @Override
    public List<DiceValue> getDicesValuesIn() {
        return new ArrayList<DiceValue>(dicesIn);
    }

    @Override
    public List<DiceValue> getDicesValuesOut() {
        return new ArrayList<DiceValue>(dicesOut);
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
        if (diceThrows > 0 && diceThrows < maxDiceThrows && dicesOut.size() != MAXDICESIZE) {
            return true;
        }
        if(openTheCupCalled){
            openTheCupCalled = false;
            return true;
        }
        return false;
    }

    /**
     * This method checks, if the player is able to call the function "roll the dice".
     * @return True, if the player is able to call stay.
     */
    private boolean isAbleToRollTheDices() {
        if (dicesIn.size() != 0 && diceThrows < maxDiceThrows) {
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
        if (diceThrows > 0 && dicesOut.size() != MAXDICESIZE && diceThrows == maxDiceThrows) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * TODO: turn around option!
     * TODO: Würfel anzeigen
     *
     */

}
