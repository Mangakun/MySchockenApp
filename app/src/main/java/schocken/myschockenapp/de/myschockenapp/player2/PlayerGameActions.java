package schocken.myschockenapp.de.myschockenapp.player2;


import schocken.myschockenapp.de.myschockenapp.player2.exceptions.MaxCoastersException;
import schocken.myschockenapp.de.myschockenapp.player2.exceptions.MaxDiceThrowException;
import schocken.myschockenapp.de.myschockenapp.player2.exceptions.MaxHalfException;
import schocken.myschockenapp.de.myschockenapp.player2.exceptions.NotEnoughDicesOutException;

/**
 * This interface is an extension of the interface {@link PlayerActions}.
 * This interface contains all method to get information from the player actions.
 * Created by Snaki on 18.11.2017.
 */

public interface PlayerGameActions {

    /**
     * This method returns the coasters of the player.
     * @return
     */
    int getCoasters();

    /**
     * This method adds the given coasters to the player coasters.
     * @param coasters The coasters, which shoould be added
     */
    void addCoasters(int coasters) throws MaxCoastersException;

    /**
     * This method sets the given coasters of the player.
     * @param coasters
     */
    void setCoasters(int coasters) throws MaxCoastersException;

    /**
     * This method remove the given coasters of the player.
     * @param coasters
     */
    void removeCoasters(int coasters);

    /**
     * This method starts the next half for the player.
     */
    void nextHalf();

    /**
     * This method starts the next round for the player.
     */
    void nextRound();

    /**
     * This method starts the next game for the player.
     */
    void nextGame();

    /**
     * This method returns the halfs of the player.
     * @return The count of the halfs.
     */
    int getHalfs();

    /**
     * This method returns the count of  dice throws.
     * @return The count of dice throws.
     */
    int getDiceThrows();

    /**
     * This functions adds a half to the player.
     * @throws MaxHalfException
     */
    void addHalf() throws MaxHalfException;

    /**
     * This method sets the max dice throws
     * @param maxDiceThrows The new max dice throws
     * @throws MaxDiceThrowException
     */
    void setMaxDiceThrows(final int maxDiceThrows) throws MaxDiceThrowException;

    /**
     * This method returns the dice value for compare.
     * @return The dice value for compare.
     * @throws NotEnoughDicesOutException
     */
    int getDiceValueForCompare() throws NotEnoughDicesOutException;

    /**
     * This method returns the coasters of the dice value.
     * @return The coasters.
     * @throws NotEnoughDicesOutException
     */
    int getCoastersOfDiceValue() throws NotEnoughDicesOutException;
}