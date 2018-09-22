package schocken.myschockenapp.de.myschockenapp.observer.impl;

import java.util.ArrayList;
import java.util.List;

import schocken.myschockenapp.de.myschockenapp.factory.PlayerCreator;
import schocken.myschockenapp.de.myschockenapp.observer.Observer;
import schocken.myschockenapp.de.myschockenapp.observer.PlayerCallBack;
import schocken.myschockenapp.de.myschockenapp.observer.exceptions.NotEnoughPlayerException;
import schocken.myschockenapp.de.myschockenapp.player.Player;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.MaxCoastersException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.MaxDiceThrowException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.MaxHalfException;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.NotEnoughDicesOutException;

/**
 * Implementation class of the observer
 */
public class ObserverImpl implements Observer, PlayerCallBack{

    /**
     * A list of players.
     */
    private List<Player> players;

    /**
     * A list of current players.
     */
    private List<Player> currentPlayers;

    /**
     * The current player.
     */
    private Player currentPlayer;

    /**
     * The round starter.
     */
    private Player roundStarter;

    /**
     * The current best player.
     */
    private Player currentBestPlayer;

    /**
     * The current worst player.
     */
    private Player currentWorstPlayer;

    /**
     * The coasters stack
     */
    private int coastersStack;

    /**
     * Constructor of the class {@link ObserverImpl}.
     */
    public ObserverImpl(){
        players = null;
        currentPlayers = null;
    }

    @Override
    public void newGame() {
        //        // erster Spieler startet bisher das Spiel
//        /*
//        Eine andere Möglichkeit wäre, dass jeder würfelt und
//         */

        // init players
        for(Player player: currentPlayers){
            player.nextGame();
        }
        // set first player
        roundStarter = players.get(0);
        nextHalf();
    }

    @Override
    public void nextHalf() {
        // init players
        for(Player player: players){
            player.nextHalf();
        }
        coastersStack = 13;
        nextRound();
    }

    @Override
    public void nextRound() {
        resetCurrentPlayers();
        currentPlayers.addAll(players);
        // init players
        for(Player player: players){
            player.nextRound();
        }
        // start with the game
        currentPlayer = roundStarter;
        currentPlayer.turn();
    }


    /**
     * This method decides the continuing.
     */
    private void end(){
        distributeCoasters();
        // TODO: neues Spiel > loser hat alle hälften
        // TODO: nexte Hälfte > loser hat 13 coasters
        // TODO: neue Runde > ansonsten
        // TODO: Presenter benachrichtigen
        //this.roundStarter = currentWorstPlayer;
    }



    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public Player getCurrentWorstPlayer() {
        return currentWorstPlayer;
    }

    @Override
    public Player getCurrentBestPlayer() {
        return currentBestPlayer;
    }

    @Override
    public void createPlayers(String[] playerNames) throws NotEnoughPlayerException{
        // check string length
        if(playerNames.length < 2 ){
            throw new NotEnoughPlayerException("There should be more than 2 players");
        }
        players = PlayerCreator.getINSTANCE().createPlayers(playerNames, this);
        // check return from factory
        if(players.size()<2){
            throw new NotEnoughPlayerException("There should be more than 2 players");
        }
        currentPlayers = new ArrayList<>();
    }

    @Override
    public void callback(Player callbackPlayer, boolean finish) {
        if(currentPlayer != callbackPlayer){
            throw new RuntimeException("Wrong player has called the callback");
        }
        if(finish){
            if(currentPlayer == roundStarter){
                // distribute max dice throws
                distributeMaxDiceThrows(currentPlayer.getDiceThrows());
            }
            determineCurrentBestPlayer();
            determineCurrentWorstPlayer();
            // delete current player from list
            int oldIndex = currentPlayers.indexOf(currentPlayer);
            currentPlayers.remove(currentPlayer);
            if(currentPlayers.size() <= 0) {
                end();
            }else {
                nextPlayer(currentPlayers.indexOf(currentPlayer) + 1);
            }
        }else{
            nextPlayer(currentPlayers.indexOf(currentPlayer)+1);
        }
    }

    /**
     * This method resets special objects.
     */
    private void resetCurrentPlayers(){
        currentPlayer = null;
        currentBestPlayer = null;
        currentWorstPlayer = null;
        currentPlayers.clear();
    }

    /**
     * This method distributes the max dice throws.
     * @param maxDiceThrows The new max dice throws.
     */
    private void distributeMaxDiceThrows(final int maxDiceThrows){
        for(final Player player: players){
            try {
                player.setMaxDiceThrows(maxDiceThrows);
            } catch (MaxDiceThrowException e) {
                // TODO ????
            }
        }
    }

    /**
     * This method calls the next player.
     * @param index The index of the next player.
     */
    private void nextPlayer(final int index){
            if(index == currentPlayers.size()){
                currentPlayer = currentPlayers.get(0);
            }else{
                currentPlayer = currentPlayers.get(index);
            }
            currentPlayer.turn();
    }

    /**
     * This method determine the current worst player.
     */
    private void determineCurrentWorstPlayer(){
        // not determined yet
        if(currentWorstPlayer == null){
            currentWorstPlayer = currentPlayer;
        }else{
            int currentWorstPlayerDiceValue = -1;
            try {
                currentWorstPlayerDiceValue = currentWorstPlayer.getDiceValueForCompare();
            } catch (NotEnoughDicesOutException e) {
                // TODO ?
            }
            int currentPlayerDiceValue = -1;
            try {
                currentPlayerDiceValue = currentPlayer.getDiceValueForCompare();
            } catch (NotEnoughDicesOutException e) {
                // TODO ?
            }
            // worst dice value
            if(currentPlayerDiceValue < currentWorstPlayerDiceValue){
                currentWorstPlayer = currentPlayer;
            }else{
                // same dice value
                if(currentPlayerDiceValue == currentWorstPlayerDiceValue){
                    // look for throws needed
                    if(currentPlayer.getDiceThrows() > currentWorstPlayer.getDiceThrows()){
                        currentWorstPlayer = currentPlayer;
                    }
                }
            }
        }
    }

    /**
     * This method determine the current best player.
     */
    private void determineCurrentBestPlayer(){
        // not determined yet
        if(currentBestPlayer == null){
            currentBestPlayer = currentPlayer;
        }else{
            int currentBestPlayerDiceValue = -1;
            try {
                currentBestPlayerDiceValue = currentBestPlayer.getDiceValueForCompare();
            } catch (NotEnoughDicesOutException e) {
                // TODO ?
            }
            int currentPlayerDiceValue = -1;
            try {
                currentPlayerDiceValue = currentPlayer.getDiceValueForCompare();
            } catch (NotEnoughDicesOutException e) {
                // TODO ?
            }
            // worst dice value
            if(currentPlayerDiceValue > currentBestPlayerDiceValue){
                currentBestPlayer = currentPlayer;
            }else{
                // same dice value
                if(currentPlayerDiceValue == currentBestPlayerDiceValue){
                    // look for throws needed
                    if(currentPlayer.getDiceThrows() < currentBestPlayer.getDiceThrows()){
                        currentBestPlayer = currentPlayer;
                    }
                }
            }
        }
    }

    /**
     * This method distribute the coasters from the best to the worst player.
     */
    private void distributeCoasters() {
       if(currentBestPlayer == currentWorstPlayer){
            try {
                throw new Exception("The best player cant be the worst player at once !");
            } catch (Exception e) {
                e.printStackTrace(); // TODO
            }
        }
        int coasters = 0;
        try {
            coasters = currentBestPlayer.getCoastersOfDiceValue();
        } catch (NotEnoughDicesOutException e) {
        }
        if(coasters == 13){
            try {
                currentWorstPlayer.setCoasters(13);
            } catch (MaxCoastersException e) {
                //TODO ?
            }
            // add half
            try {
                currentWorstPlayer.addHalf();
            } catch (MaxHalfException e) {
                // TODO ?
            }
            // reset other players
            for(final Player player : players){
                if(player != currentWorstPlayer){
                    try {
                        player.setCoasters(0);
                    } catch (MaxCoastersException e) {
                        // TODO ?
                    }
                }
            }
        }else{
            if(coastersStack > 0){
                coasters = Math.min(coastersStack, coasters);
                coastersStack-=coasters;
            }else {
                coasters = Math.min(currentBestPlayer.getCoasters(), coasters);
                currentBestPlayer.removeCoasters(coasters);
            }
            try {
                currentWorstPlayer.addCoasters(coasters);
            } catch (MaxCoastersException e) {
                // TODO
            }
        }
    }
}
