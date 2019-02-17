package schocken.myschockenapp.de.myschockenapp.observer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import schocken.myschockenapp.de.myschockenapp.dice.DiceValue;
import schocken.myschockenapp.de.myschockenapp.dice.impl.DiceImpl;
import schocken.myschockenapp.de.myschockenapp.player.Player;
import schocken.myschockenapp.de.myschockenapp.factory.PlayerCreator;
import schocken.myschockenapp.de.myschockenapp.observer.impl.GameObserver;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public abstract class AbstractGameSimulationTest {

    /**
     * TODO: documentation
     */
    protected void setDicesOutOfPlayer(final Player player, int diceValue1, int diceValue2, int diceValue3){
        // dice 1
        DiceValue value1 = spy(new DiceImpl());
        when(value1.getValue()).thenReturn(diceValue1);
        // dice 2
        DiceValue value2 = spy(new DiceImpl());
        when(value2.getValue()).thenReturn(diceValue2);
        // dice 3
        DiceValue value3 = spy(new DiceImpl());
        when(value3.getValue()).thenReturn(diceValue3);
        // create list
        List<DiceValue> list1 = new ArrayList<DiceValue>();
        list1.add(value1);
        list1.add(value2);
        list1.add(value3);
        when(player.getDicesValuesOut()).thenReturn(list1);
    }

    /**
     * TODO: documentation
     */
    protected PlayerCreator mockPlayerCreator(){
        PlayerCreator playerCreator = mock(PlayerCreator.class);
        try {
            Field instance = PlayerCreator.class.getDeclaredField("INSTANCE");
            instance.setAccessible(true);
            instance.set(instance, playerCreator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return playerCreator;
    }

    /**
     * TODO: documentation
     * @return
     */
    protected Observer spyGameObserver(){
        return spy(new GameObserver());
    }


}
