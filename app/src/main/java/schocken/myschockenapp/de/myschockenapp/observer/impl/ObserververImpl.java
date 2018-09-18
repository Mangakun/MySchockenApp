package schocken.myschockenapp.de.myschockenapp.observer.impl;

import java.util.List;

import schocken.myschockenapp.de.myschockenapp.factory.PlayerCreator;
import schocken.myschockenapp.de.myschockenapp.observer.Observer;
import schocken.myschockenapp.de.myschockenapp.observer.PlayerCallBack;
import schocken.myschockenapp.de.myschockenapp.player.Player;
import schocken.myschockenapp.de.myschockenapp.player.exceptions.MaxDiceThrowException;

/**
 * Implementation class of the observer
 */
public class ObserververImpl implements Observer, PlayerCallBack{

    /**
     * A list of players.
     */
    private List<Player> players;

    /**
     * The current player.
     */
    private Player currentPlayer;

    /**
     * The round starter.
     */
    private Player roundStarter;

    /**
     * Constructor of the class {@link ObserververImpl}.
     */
    public ObserververImpl(){
        players = null;
    }

    @Override
    public void newGame() {
        // init players
        for(Player player: players){
            player.nextGame();
        }
        // set first player
        currentPlayer = players.get(0);
        roundStarter = currentPlayer;
        // start with the game
        currentPlayer.turn();
    }

    @Override
    public void nextHalf() {

    }

    /**
     * This method decides the continuing.
     */
    private void end(){
        // TODO: wenn looser 13 Steine hat -> Spiel für nächste Runde
        // TODO: wenn Looser 13 Steine hat und noch nicht alle Hälften sind verteilt -> nächste Hälfte
        // TODO: wenn Looser 13 Steine hat und alle Hälften sind verteilt -> nächstes Spiel
        // TODO: Presenter benachrichtigen
    }

    @Override
    public void nextRound() {

    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void createPlayers(String[] playerNames) {
        players = PlayerCreator.getINSTANCE().createPlayers(playerNames, this);
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
            nextPlayer(players.indexOf(currentPlayer)+1);
        }
        else{
            nextPlayer(players.indexOf(currentPlayer)+1);
        }
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
        if(players.size()>0){
            if(index == players.size()){
                currentPlayer = players.get(0);
            }else{
                currentPlayer = players.get(index);
            }
            currentPlayer.turn();
        }else{
            // TODO: Spiel müsste vorbei sein
        }
    }


    private void distributeCoasters(){
        // TODO:
    }
}
