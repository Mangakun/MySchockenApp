package schocken.myschockenapp.de.myschockenapp.observer.impl;

import java.util.ArrayList;
import java.util.List;

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
    private CoasterStackObserver coasterStackObserver;

    /**
     * Constructor of the class {@link GameObserver}.
     */
    public GameObserver() {
        players = null;
        currentPlayers = null;
        coasterStackObserver = new CoasterStackObserverImpl();
    }

    @Override
    public void newGame() {
        System.out.println("Nächstes Spiel");
        //        // erster Spieler startet bisher das Spiel
//        /*
//        Eine andere Möglichkeit wäre, dass jeder würfelt und
//         */
        currentPlayers.addAll(players);
        // init players
        for (Player player : currentPlayers) {
            player.nextGame();
        }
        // set first player
        roundStarter = players.get(0);
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
        for (Player player : currentPlayers) {
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
        for (Player player : players) {
            player.nextRound();
        }
        // start with the game
        currentPlayer = roundStarter;
        currentPlayer.turn();
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
                currentPlayers.clear();
                currentPlayers.add(coasterStackObserver.getFirstHalf());
                currentPlayers.add(coasterStackObserver.getSecondHalf());
                roundStarter = currentPlayers.get(0);
                // try {
                //      nextHalf();
                // } catch (Exception e) {
                //     e.printStackTrace();
                // }
            } else {
                if(currentWorstPlayer.getCoasters() == 13){
                    currentPlayers.clear();
                    currentPlayers.addAll(players);
                    // normal next round
                    //nextRound();
                }else {
                    if (coasterStackObserver.coasterStackEmpty()) {
                        currentPlayers.clear();
                        for (Player player : players) {
                            if (player.getCoasters() == 0) {
                                continue;
                            }
                            currentPlayers.add(player);
                        }
                    } else {
                        currentPlayers.clear();
                        currentPlayers.addAll(players);
                        // normal next round
                        //nextRound();
                    }
                }
                this.roundStarter = currentWorstPlayer;
            }

        }
        // TODO: Presenter benachrichtigen
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
    public void createPlayers(String[] playerNames) throws NotEnoughPlayerException {
        // check string length
        if (playerNames.length < 2) {
            throw new NotEnoughPlayerException("There should be more than 2 players");
        }
        players = PlayerCreator.getINSTANCE().createPlayers(playerNames, this);
        // check return from factory
        if (players.size() < 2) {
            throw new NotEnoughPlayerException("There should be more than 2 players");
        }
        currentPlayers = new ArrayList<>();
    }

    @Override
    public void callback(Player callbackPlayer, boolean finish) {
        if (currentPlayer != callbackPlayer) {
            throw new RuntimeException("Wrong player has called the callback");
        }
        if (finish) {
            if (currentPlayer == roundStarter) {
                // distribute max dice throws
                distributeMaxDiceThrows(currentPlayer.getDiceThrows());
            }
            determineCurrentBestPlayer();
            determineCurrentWorstPlayer();
            // delete current player from list
            int oldIndex = currentPlayers.indexOf(currentPlayer);
            currentPlayers.remove(currentPlayer);
            if (currentPlayers.size() <= 0) {
                end();
            } else {
                nextPlayer(oldIndex);
            }
        } else {
            nextPlayer(currentPlayers.indexOf(currentPlayer) + 1);
        }
    }


    /**
     * This method distributes the max dice throws.
     *
     * @param maxDiceThrows The new max dice throws.
     */
    private void distributeMaxDiceThrows(final int maxDiceThrows) {
        for (final Player player : players) {
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
        if (index == currentPlayers.size()) {
            currentPlayer = currentPlayers.get(0);
        } else {
            currentPlayer = currentPlayers.get(index);
        }
        currentPlayer.turn();
    }

    /**
     * This method determine the current worst player.
     */
    private void determineCurrentWorstPlayer() {
        // not determined yet
        if (currentWorstPlayer == null) {
            currentWorstPlayer = currentPlayer;
        } else {
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
            if (currentPlayerDiceValue < currentWorstPlayerDiceValue) {
                currentWorstPlayer = currentPlayer;
            } else {
                // same dice value
                if (currentPlayerDiceValue == currentWorstPlayerDiceValue) {
                    // look for throws needed
                    if (currentPlayer.getDiceThrows() > currentWorstPlayer.getDiceThrows()) {
                        currentWorstPlayer = currentPlayer;
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
        if (currentBestPlayer == null) {
            currentBestPlayer = currentPlayer;
        } else {
            int currentBestPlayerDiceValue = -1;
            try {
                currentBestPlayerDiceValue = currentBestPlayer.getDiceValueForCompare();
            } catch (NotEnoughDicesOutException e) {
                e.printStackTrace();
                // TODO ?
            }
            int currentPlayerDiceValue = -1;
            try {
                currentPlayerDiceValue = currentPlayer.getDiceValueForCompare();
            } catch (NotEnoughDicesOutException e) {
                e.printStackTrace();
                // TODO ?
            }
            // worst dice value
            if (currentPlayerDiceValue > currentBestPlayerDiceValue) {
                currentBestPlayer = currentPlayer;
            } else {
                // same dice value
                if (currentPlayerDiceValue == currentBestPlayerDiceValue) {
                    // look for throws needed
                    if (currentPlayer.getDiceThrows() < currentBestPlayer.getDiceThrows()) {
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
        if (currentBestPlayer == currentWorstPlayer) {
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
            e.printStackTrace();
        }
        if (coasters == 13) {
            try {
                coasterStackObserver.takeAwayHalf(currentWorstPlayer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // reset other players
            for (final Player player : players) {
                if (player != currentWorstPlayer) {
                    try {
                        player.setCoasters(0);
                    } catch (MaxCoastersException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            if (!coasterStackObserver.takeAwayCoasters(coasters, currentWorstPlayer)) {
                coasters = Math.min(currentBestPlayer.getCoasters(), coasters);
                currentBestPlayer.removeCoasters(coasters);
                try {
                    currentWorstPlayer.addCoasters(coasters);
                } catch (MaxCoastersException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void resetCurrentPlayers() {
        currentWorstPlayer = null;
        currentBestPlayer = null;
        currentPlayer = null;
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
