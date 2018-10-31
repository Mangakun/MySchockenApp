package schocken.myschockenapp.de.myschockenapp.observer.impl;

import java.util.ArrayList;
import java.util.List;

import schocken.myschockenapp.de.myschockenapp.domain.game.pojo.GamePojo;
import schocken.myschockenapp.de.myschockenapp.domain.player.pojo.PlayerPojo;
import schocken.myschockenapp.de.myschockenapp.factory.PlayerCreator;
import schocken.myschockenapp.de.myschockenapp.observer.Observer;
import schocken.myschockenapp.de.myschockenapp.observer.PlayerCallBack;
import schocken.myschockenapp.de.myschockenapp.observer.exceptions.NotEnoughPlayerException;
import schocken.myschockenapp.de.myschockenapp.player2.Player;
import schocken.myschockenapp.de.myschockenapp.player2.exceptions.MaxCoastersException;
import schocken.myschockenapp.de.myschockenapp.player2.exceptions.MaxDiceThrowException;
import schocken.myschockenapp.de.myschockenapp.player2.exceptions.NotEnoughDicesOutException;

/**
 * Implementation class of the observer
 */
public class GameObserver implements Observer, PlayerCallBack {

    private final GamePojo gamePojo;

    /**
     * The coasters stack
     */
    private CoasterStackObserver coasterStackObserver;

    /**
     * Constructor of the class {@link GameObserver}.
     */
    public GameObserver() {
        gamePojo = new GamePojo();
        coasterStackObserver = new CoasterStackObserverImpl();
    }

    @Override
    public void newGame() {
        System.out.println("Nächstes Spiel");
        //        // erster Spieler startet bisher das Spiel
//        /*
//        Eine andere Möglichkeit wäre, dass jeder würfelt und
//         */
        gamePojo.getCurrentPlayers().addAll(gamePojo.getPlayers());
        // init players
        for (Player player : gamePojo.getCurrentPlayers()) {
            player.nextGame();
        }
        // set first player
        gamePojo.setRoundStarter(gamePojo.getPlayers().get(0));
        try {
            nextHalf();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nextHalf() throws Exception {
        System.out.println("Nächste Hälfte");
        // init players
        for (Player player : gamePojo.getCurrentPlayers()) {
            player.nextHalf();
        }
        coasterStackObserver.resetCoasters();
        nextRound();
    }

    @Override
    public void nextRound() {
        System.out.println("Nächste Runde");
        resetCurrentPlayers();
        // init players
        for (Player player : gamePojo.getCurrentPlayers()) {
            player.nextRound();
        }
        // start with the game
        gamePojo.setCurrentPlayer(gamePojo.getRoundStarter());
        gamePojo.getCurrentPlayer().turn();
    }


    /**
     * This method decides the continuing.
     */
    private void end() {
        distributeCoasters();
        // one player has both halfs -> game ends
        if (coasterStackObserver.playerHasBothHalfs()) {
            System.out.println("Game ist zuende");
        } else {
            if (coasterStackObserver.allHalfsDistributed()) {
                gamePojo.getCurrentPlayers().clear();
                gamePojo.getCurrentPlayers().add(coasterStackObserver.getFirstHalf());
                gamePojo.getCurrentPlayers().add(coasterStackObserver.getSecondHalf());
                gamePojo.setRoundStarter(gamePojo.getCurrentPlayers().get(0));
                // try {
                //      nextHalf();
                // } catch (Exception e) {
                //     e.printStackTrace();
                // }
            } else {
                if(gamePojo.getCurrentWorstPlayer().getCoasters() == 13){
                    gamePojo.getCurrentPlayers().clear();
                    gamePojo.getCurrentPlayers().addAll(gamePojo.getPlayers());
                    // normal next round
                    //nextRound();
                }else {
                    if (coasterStackObserver.coasterStackEmpty()) {
                        gamePojo.getCurrentPlayers().clear();
                        for (Player player : gamePojo.getPlayers()) {
                            if (player.getCoasters() == 0) {
                                continue;
                            }
                            gamePojo.getCurrentPlayers().add(player);
                        }
                    } else {
                        gamePojo.getCurrentPlayers().clear();
                        gamePojo.getCurrentPlayers().addAll(gamePojo.getPlayers());
                        // normal next round
                        //nextRound();
                    }
                }
                gamePojo.setRoundStarter(gamePojo.getCurrentWorstPlayer());
            }

        }
        // TODO: Presenter benachrichtigen
    }

    @Override
    public void createPlayers(String[] playerNames) throws NotEnoughPlayerException {
        // check string length
        if (playerNames.length < 2) {
            throw new NotEnoughPlayerException("There should be more than 2 players");
        }
        gamePojo.setPlayers(PlayerCreator.getINSTANCE().createPlayers(playerNames, this));
        // check return from factory
        if (gamePojo.getPlayers().size() < 2) {
            throw new NotEnoughPlayerException("There should be more than 2 players");
        }
    }

    @Override
    public void callback(Player callbackPlayer, boolean finish) {
        if (gamePojo.getCurrentPlayer() != callbackPlayer) {
            throw new RuntimeException("Wrong player has called the callback");
        }
        if (finish) {
            if (gamePojo.getCurrentPlayer() == gamePojo.getRoundStarter()) {
                // distribute max dice throws
                distributeMaxDiceThrows(gamePojo.getCurrentPlayer().getDiceThrows());
            }
            determineCurrentBestPlayer();
            determineCurrentWorstPlayer();
            // delete current player from list
            int oldIndex = gamePojo.getCurrentPlayers().indexOf(gamePojo.getCurrentPlayer());
            gamePojo.getCurrentPlayers().remove( gamePojo.getCurrentPlayer());
            if (gamePojo.getCurrentPlayers().size() <= 0) {
                end();
            } else {
                nextPlayer(oldIndex);
            }
        } else {
            nextPlayer(gamePojo.getCurrentPlayers().indexOf(gamePojo.getCurrentPlayer()) + 1);
        }
    }


    /**
     * This method distributes the max dice throws.
     *
     * @param maxDiceThrows The new max dice throws.
     */
    private void distributeMaxDiceThrows(final int maxDiceThrows) {
        for (final Player player : gamePojo.getCurrentPlayers()) {
            try {
                player.setMaxDiceThrows(maxDiceThrows);
            } catch (MaxDiceThrowException e) {
                // TODO ????
            }
        }
    }

    /**
     * This method calls the next player.
     *
     * @param index The index of the next player.
     */
    private void nextPlayer(final int index) {
        if (index == gamePojo.getCurrentPlayers().size()) {
            gamePojo.setCurrentPlayer(gamePojo.getCurrentPlayers().get(0));
        } else {
            gamePojo.setCurrentPlayer(gamePojo.getCurrentPlayers().get(index));
        }
        gamePojo.getCurrentPlayer().turn();
    }

    /**
     * This method determine the current worst player.
     */
    private void determineCurrentWorstPlayer() {
        // not determined yet
        if (gamePojo.getCurrentWorstPlayer() == null) {
            gamePojo.setCurrentWorstPlayer(gamePojo.getCurrentPlayer());
        } else {
            int currentWorstPlayerDiceValue = -1;
            try {
                currentWorstPlayerDiceValue = gamePojo.getCurrentWorstPlayer().getDiceValueForCompare();
            } catch (NotEnoughDicesOutException e) {
                // TODO ?
            }
            int currentPlayerDiceValue = -1;
            try {
                currentPlayerDiceValue = gamePojo.getCurrentPlayer().getDiceValueForCompare();
            } catch (NotEnoughDicesOutException e) {
                // TODO ?
            }
            // worst dice value
            if (currentPlayerDiceValue < currentWorstPlayerDiceValue) {
                gamePojo.setCurrentWorstPlayer(gamePojo.getCurrentPlayer());
            } else {
                // same dice value
                if (currentPlayerDiceValue == currentWorstPlayerDiceValue) {
                    // look for throws needed
                    if (gamePojo.getCurrentPlayer().getDiceThrows() > gamePojo.getCurrentWorstPlayer().getDiceThrows()) {
                        gamePojo.setCurrentWorstPlayer(gamePojo.getCurrentPlayer());
                    }
                }
            }
        }
    }

    /**
     * This method determine the current best player.
     */
    private void determineCurrentBestPlayer() {
        // not determined yet
        if (gamePojo.getCurrentBestPlayer() == null) {
            gamePojo.setCurrentBestPlayer(gamePojo.getCurrentPlayer());

        } else {
            int currentBestPlayerDiceValue = -1;
            try {
                currentBestPlayerDiceValue = gamePojo.getCurrentBestPlayer().getDiceValueForCompare();
            } catch (NotEnoughDicesOutException e) {
                e.printStackTrace();
                // TODO ?
            }
            int currentPlayerDiceValue = -1;
            try {
                currentPlayerDiceValue = gamePojo.getCurrentPlayer().getDiceValueForCompare();
            } catch (NotEnoughDicesOutException e) {
                e.printStackTrace();
                // TODO ?
            }
            // worst dice value
            if (currentPlayerDiceValue > currentBestPlayerDiceValue) {
                gamePojo.setCurrentBestPlayer(gamePojo.getCurrentPlayer());
            } else {
                // same dice value
                if (currentPlayerDiceValue == currentBestPlayerDiceValue) {
                    // look for throws needed
                    if (gamePojo.getCurrentPlayer().getDiceThrows() < gamePojo.getCurrentBestPlayer().getDiceThrows()) {
                        gamePojo.setCurrentBestPlayer(gamePojo.getCurrentPlayer());
                    }
                }
            }
        }
    }

    /**
     * This method distribute the coasters from the best to the worst player.
     */
    private void distributeCoasters() {
        if (gamePojo.getCurrentBestPlayer() == gamePojo.getCurrentWorstPlayer()) {
            try {
                throw new Exception("The best player cant be the worst player at once !");
            } catch (Exception e) {
                e.printStackTrace(); // TODO
            }
        }
        int coasters = 0;
        try {
            coasters = gamePojo.getCurrentBestPlayer().getCoastersOfDiceValue();
        } catch (NotEnoughDicesOutException e) {
            e.printStackTrace();
        }
        if (coasters == 13) {
            try {
                coasterStackObserver.takeAwayHalf(gamePojo.getCurrentWorstPlayer());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // reset other players
            for (final Player player : gamePojo.getCurrentPlayers()) {
                if (player != gamePojo.getCurrentWorstPlayer()) {
                    try {
                        player.setCoasters(0);
                    } catch (MaxCoastersException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            if (!coasterStackObserver.takeAwayCoasters(coasters, gamePojo.getCurrentWorstPlayer())) {
                coasters = Math.min(gamePojo.getCurrentBestPlayer().getCoasters(), coasters);
                gamePojo.getCurrentBestPlayer().removeCoasters(coasters);
                try {
                    gamePojo.getCurrentWorstPlayer().addCoasters(coasters);
                } catch (MaxCoastersException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void resetCurrentPlayers() {
        gamePojo.setCurrentWorstPlayer(null);
        gamePojo.setCurrentBestPlayer(null);
        gamePojo.setCurrentPlayer(null);
    }

    private interface CoasterStackObserver {

        boolean coasterStackEmpty();

        boolean allHalfsDistributed();

        void resetCoasters();

        void resetHalfs();

        Player getFirstHalf();

        Player getSecondHalf();

        boolean takeAwayCoasters(int coasters, final Player worstPlayer);

        void takeAwayHalf(final Player worstPlayer) throws Exception;

        boolean playerHasBothHalfs();


    }

    /**
     * This class represents the coasters stack.
     */
    private class CoasterStackObserverImpl implements CoasterStackObserver {

        /**
         * The coasters stack
         */
        private int coastersStack;

        /**
         * The player who has the first half
         */
        private Player firstHalf;

        /**
         * The player who has the second half
         */
        private Player secondHalf;

        /**
         * Constructor of the class {@link CoasterStackObserverImpl}.
         */
        public CoasterStackObserverImpl() {
            coastersStack = 13;
            firstHalf = null;
            secondHalf = null;
        }

        /**
         * This method tries to return the coasters.
         *
         * @param coasters
         * @return
         */
        public boolean takeAwayCoasters(int coasters, final Player worstPlayer) {
            if (coastersStack <= 0) {
                return false;
            }
            int value = Math.min(coastersStack, coasters);
            try {
                worstPlayer.addCoasters(value);
            } catch (MaxCoastersException e) {
                e.printStackTrace();
            }
            coastersStack -= value;
            return true;
            //return returnValue;
        }

        /**
         * This method takes away a half
         *
         * @param worstPlayer
         * @throws Exception
         */
        public void takeAwayHalf(final Player worstPlayer) throws Exception {
            if (firstHalf == null) {
                firstHalf = worstPlayer;
                worstPlayer.setCoasters(13);
                worstPlayer.addHalf();
            } else {
                if (secondHalf == null) {
                    secondHalf = worstPlayer;
                    worstPlayer.setCoasters(13);
                    worstPlayer.addHalf();
                } else {
                    throw new Exception("Beide hälften sind schon gesetzt");
                }
            }
        }

        public boolean playerHasBothHalfs() {

            return firstHalf != null && firstHalf == secondHalf;
        }

        public Player getFirstHalf() {
            return firstHalf;
        }

        public Player getSecondHalf() {
            return secondHalf;
        }

        @Override
        public boolean coasterStackEmpty() {
            return coastersStack == 0;
        }

        @Override
        public boolean allHalfsDistributed() {
            return firstHalf != null && secondHalf != null;
        }

        /**
         * This method returns the coasters.
         */
        public void resetCoasters() {
            coastersStack = 13;
        }

        @Override
        public void resetHalfs() {
            firstHalf = null;
            secondHalf = null;
        }

    }
}
