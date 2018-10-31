package schocken.myschockenapp.de.myschockenapp.domain.game.pojo;

import java.util.List;

import schocken.myschockenapp.de.myschockenapp.player2.Player;

public class GamePojo {

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

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setCurrentPlayers(List<Player> currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setRoundStarter(Player roundStarter) {
        this.roundStarter = roundStarter;
    }

    public void setCurrentBestPlayer(Player currentBestPlayer) {
        this.currentBestPlayer = currentBestPlayer;
    }

    public void setCurrentWorstPlayer(Player currentWorstPlayer) {
        this.currentWorstPlayer = currentWorstPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getRoundStarter() {
        return roundStarter;
    }

    public Player getCurrentBestPlayer() {
        return currentBestPlayer;
    }

    public Player getCurrentWorstPlayer() {
        return currentWorstPlayer;
    }
}
