//package schocken.myschockenapp.de.myschockenapp.observer.impl;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import schocken.myschockenapp.de.myschockenapp.factory.PlayerCreator;
//import schocken.myschockenapp.de.myschockenapp.observer.Observer;
//import schocken.myschockenapp.de.myschockenapp.observer.PlayerCallBack;
//import schocken.myschockenapp.de.myschockenapp.observer.exceptions.NotEnoughPlayerException;
//import schocken.myschockenapp.de.myschockenapp.player.Player;
//import schocken.myschockenapp.de.myschockenapp.player.exceptions.NotEnoughDicesOutException;
//
//public class GameObserver3 implements Observer, PlayerCallBack {
//
//    private List<Player> allPlayers;
//
//    private List<Player> currentPlayers;
//
//    private Player currentBestPlayer;
//
//    private Player currentWorstPlayer;
//
//    private Player roundStarter;
//
//    private CoasterStackObserver coasterStackObserver;
//
//    private Player currentPlayer;
//
//    public GameObserver3(){
//        coasterStackObserver = new CoasterStackObserverImpl();
//
//    }
//
//    @Override
//    public void newGame() {
//        currentPlayers = new ArrayList<>(allPlayers);
//         // next game of all players
//        for(Player player: currentPlayers){ // TODO: vielleicht Iterator benutzen ?
//            player.nextGame();
//        }
//        roundStarter = currentPlayers.get(0);
//        try {
//            nextHalf();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void nextHalf() throws Exception {
//        for (Player player : currentPlayers) {// TODO: vielleicht Iterator benutzen ?
//            player.nextHalf();
//        }
//    }
//
//    @Override
//    public void nextRound() {
//        // init players
//        for (Player player : currentPlayers) {  // TODO: vielleicht Iterator benutzen ?
//            player.nextRound();
//        }
//        currentPlayer = roundStarter;
//        currentPlayer.turn();
//    }
//
//    @Override
//    public Player getCurrentPlayer() {
//        return currentPlayer;
//    }
//
//    @Override
//    public Player getCurrentWorstPlayer() {
//        return currentWorstPlayer;
//    }
//
//    @Override
//    public Player getCurrentBestPlayer() {
//        return currentBestPlayer;
//    }
//
//    @Override
//    public void createPlayers(String[] playerNames) throws NotEnoughPlayerException {
//        // check string length
//        if (playerNames.length < 2) {
//            throw new NotEnoughPlayerException("There should be more than 2 players");
//        }
//        allPlayers = PlayerCreator.getINSTANCE().createPlayers(playerNames, this);
//        // check return from factory
//        if (allPlayers.size() < 2) {
//            throw new NotEnoughPlayerException("There should be more than 2 players");
//        }
//    }
//
//    @Override
//    public void callback(Player callbackPlayer, boolean finish) {
//        if (currentPlayer != callbackPlayer) {
//            throw new RuntimeException("Wrong player has called the callback");
//        }
//        if (finish) {
//            if (currentPlayer == roundStarter) {
//                // distribute max dice throws
//                distributeMaxDiceThrows(currentPlayer.getDiceThrows());
//            }
//            determineCurrentBestPlayer();
//            determineCurrentWorstPlayer();
//            // delete current player from list
//            int oldIndex = currentPlayers.indexOf(currentPlayer);
//            currentPlayers.remove(currentPlayer);
//            if (currentPlayers.size() <= 0) {
//                end();
//            } else {
//                nextPlayer(currentPlayers.indexOf(currentPlayer) + 1);
//            }
//        } else {
//            nextPlayer(currentPlayers.indexOf(currentPlayer) + 1);
//        }
//    }
//
//
//
//
//    /**
//     * This method determine the current worst player.
//     */
//    private void determineCurrentWorstPlayer() {
//        final Player currentPlayer = playerIterator.getCurrentPlayer();
//        // not determined yet
//        if (currentWorstPlayer == null) {
//            currentWorstPlayer = currentPlayer;
//        } else {
//            int currentWorstPlayerDiceValue = -1;
//            try {
//                currentWorstPlayerDiceValue = currentWorstPlayer.getDiceValueForCompare();
//            } catch (NotEnoughDicesOutException e) {
//                // TODO ?
//            }
//            int currentPlayerDiceValue = -1;
//            try {
//                currentPlayerDiceValue = currentPlayer.getDiceValueForCompare();
//            } catch (NotEnoughDicesOutException e) {
//                // TODO ?
//            }
//            // worst dice value
//            if (currentPlayerDiceValue < currentWorstPlayerDiceValue) {
//                currentWorstPlayer = currentPlayer;
//            } else {
//                // same dice value
//                if (currentPlayerDiceValue == currentWorstPlayerDiceValue) {
//                    // look for throws needed
//                    if (currentPlayer.getDiceThrows() > currentWorstPlayer.getDiceThrows()) {
//                        currentWorstPlayer = currentPlayer;
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * This method determine the current best player.
//     */
//    private void determineCurrentBestPlayer() {
//        final Player currentPlayer = playerIterator.getCurrentPlayer();
//        // not determined yet
//        if (currentBestPlayer == null) {
//            currentBestPlayer = currentPlayer;
//        } else {
//            int currentBestPlayerDiceValue = -1;
//            try {
//                currentBestPlayerDiceValue = currentBestPlayer.getDiceValueForCompare();
//            } catch (NotEnoughDicesOutException e) {
//                e.printStackTrace();
//                // TODO ?
//            }
//            int currentPlayerDiceValue = -1;
//            try {
//                currentPlayerDiceValue = currentPlayer.getDiceValueForCompare();
//            } catch (NotEnoughDicesOutException e) {
//                e.printStackTrace();
//                // TODO ?
//            }
//            // worst dice value
//            if (currentPlayerDiceValue > currentBestPlayerDiceValue) {
//                currentBestPlayer = currentPlayer;
//            } else {
//                // same dice value
//                if (currentPlayerDiceValue == currentBestPlayerDiceValue) {
//                    // look for throws needed
//                    if (currentPlayer.getDiceThrows() < currentBestPlayer.getDiceThrows()) {
//                        currentBestPlayer = currentPlayer;
//                    }
//                }
//            }
//        }
//    }
//
//
//
//    private interface CoasterStackObserver{
//
//        boolean coasterStackEmpty();
//
//        boolean allHalfsDistributed();
//
//        void resetCoasters();
//
//        void resetHalfs();
//
//
//    }
//
//    private class CoasterStackObserverImpl implements CoasterStackObserver{
//
//        private int coasters;
//
//        private Player firstHalf;
//
//        private Player secondHalf;
//
//        public CoasterStackObserverImpl(){
//
//        }
//
//        @Override
//        public boolean coasterStackEmpty() {
//            return coasters == 0;
//        }
//
//        @Override
//        public boolean allHalfsDistributed() {
//            return firstHalf != null && secondHalf != null;
//        }
//
//        @Override
//        public void resetCoasters() {
//            coasters = 13;
//        }
//
//        @Override
//        public void resetHalfs() {
//            firstHalf = null;
//            secondHalf = null;
//        }
//    }
//
//
//    private interface PlayerIterator extends Iterator<Player>{
//
//        void setRoundStarter(Player roundStarter);
//
//        Player getCurrentPlayer();
//
//    }
//
//    private class PlayerIteratorImpl implements PlayerIterator{
//
//        private int cursor;
//
//        private int lastCursor;
//
//        private CoasterStackObserver coasterStackObserver;
//
//        public PlayerIteratorImpl(CoasterStackObserver coasterStackObserver){
//            this.coasterStackObserver = coasterStackObserver;
//            cursor = 0;
//            lastCursor = -1;
//        }
//
//        @Override
//        public boolean hasNext() {
//            throw new RuntimeException("STUB");
//            //return false;
//        }
//
//        private int nextCursor(int i){
//            return (i+1)%players.size();
//        }
//
//        @Override
//        public Player next() {
//            int i = cursor;
//            lastCursor = i;
//            if(coasterStackObserver.allHalfsDistributed()){
//                // take next player who has a half
//                while(players.get(i).getHalfs() == 0){
//                    i = nextCursor(i);
//                }
//                cursor = i;
//                return players.get(i);
//
//            }
//            if(coasterStackObserver.coasterStackEmpty()){
//                // take next player who has coasters
//                while(players.get(i).getCoasters() == 0){
//                    i = nextCursor(i);
//                }
//                cursor = i;
//                return players.get(i);
//            }
//            i = nextCursor(i);
//            cursor = i;
//            return players.get(i);
//        }
//
//        @Override
//        public void setRoundStarter(Player roundStarter) {
//            cursor = players.indexOf(roundStarter);
//        }
//
//        @Override
//        public Player getCurrentPlayer() {
//            return players.get(lastCursor);
//        }
//    }
//}
