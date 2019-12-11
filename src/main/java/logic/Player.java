package logic;

import java.util.ArrayList;
import java.util.List;

public interface Player {
    //Rolls the dice if the dice value is true
    void rollSomeDie(boolean die_1, boolean die_2, boolean die_3, boolean die_4, boolean die_5);

    boolean isHuman();

    void rollAllDice();

    String getName();

    void setName(String name);

    int getMarker();

    void setMarker(int marker);

    boolean payMarkerToPot(int payment);

    List<Die> getDice();

    int[] getDieValues();

    void payMarkerFromPot(int pot);

    String toStorageString();
}
