package schocken.myschockenapp.de.myschockenapp.factory;

import java.util.ArrayList;
import java.util.List;


import schocken.myschockenapp.de.myschockenapp.observer.PlayerCallBack;
import schocken.myschockenapp.de.myschockenapp.player2.Player;
import schocken.myschockenapp.de.myschockenapp.player2.PlayerImpl.PlayerImpl;

public class PlayerCreator {

    /**
     * Instance of player creator
     */
    private static PlayerCreator INSTANCE = new PlayerCreator();

    /**
     * Private constructor of the class.
     */
    private PlayerCreator(){

    }

    /**
     * This method creates a list of players.
     * @param playersNames An array of player names.
     * @param playerCallback An object of the class {@link PlayerCallBack}.
     * @return A list of players.
     */
    public List<Player> createPlayers(final String[] playersNames, final PlayerCallBack playerCallback){
        List<Player> players = new ArrayList<>();
        for(String playerName : playersNames){
            final Player player = new PlayerImpl(playerName,playerCallback);
             players.add(player);
        }
         return players;
    }

    public static PlayerCreator getINSTANCE() {
        return INSTANCE;
    }
}
