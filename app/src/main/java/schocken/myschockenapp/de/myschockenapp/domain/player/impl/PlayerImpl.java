package schocken.myschockenapp.de.myschockenapp.domain.player.impl;

import android.content.res.Resources;

import schocken.myschockenapp.de.myschockenapp.R;
import schocken.myschockenapp.de.myschockenapp.domain.Player;
import schocken.myschockenapp.de.myschockenapp.domain.player.pojo.PlayerPojo;
import schocken.myschockenapp.de.myschockenapp.player2.exceptions.PlayerActionNotAllowedException;

public class PlayerImpl implements Player {

    private final PlayerPojo playerPojo;

    public PlayerImpl(){
        this.playerPojo = new PlayerPojo();
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
            playerPojo.setOpenTheCupCalled(true);
        } else {
            throw new PlayerActionNotAllowedException("The player is not allowed to open the cup");
        }
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
        if (playerPojo.getDiceThrows() > 0 && playerPojo.getDiceThrows() < playerPojo.getMaxDiceThrows() && playerPojo.getDicesOut().size() != Resources.getSystem().getInteger(R.integer.maxdicesize)) {
            return true;
        }
        if(playerPojo.isOpenTheCupCalled()){
            playerPojo.setOpenTheCupCalled(false);
            return true;
        }
        return false;
    }

    /**
     * This method checks, if the player is able to call the function "roll the dice".
     * @return True, if the player is able to call stay.
     */
    private boolean isAbleToRollTheDices() {
        if (playerPojo.getDicesIn().size() != 0 && playerPojo.getDiceThrows() < playerPojo.getMaxDiceThrows()) {
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
        if (playerPojo.getDiceThrows() > 0 && playerPojo.getDicesOut().size() != Resources.getSystem().getInteger(R.integer.maxdicesize) && playerPojo.getDiceThrows() == playerPojo.getMaxDiceThrows()) {
            return true;
        } else {
            return false;
        }
    }

}
