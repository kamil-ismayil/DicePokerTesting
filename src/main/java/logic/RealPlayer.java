package logic;

import java.util.ArrayList;

public class RealPlayer implements Player {
    private String name;
    private int marker;
    private ArrayList<Die> dice = new ArrayList();
    Die die1,die2, die3, die4, die5;
    boolean isHuman;


    public RealPlayer(String name, int marker, boolean isHuman) {
        this.name = name;
        this.marker = marker;
        this.isHuman = isHuman;

        die1 = new Die(6);
        die2 = new Die(6);
        die3 = new Die(6);
        die4 = new Die(6);
        die5 = new Die(6);

        dice.add(die1);
        dice.add(die2);
        dice.add(die3);
        dice.add(die4);
        dice.add(die5);

    }

    //Rolls the dice if the dice value is true
    @Override
    public void rollSomeDie(boolean die_1, boolean die_2, boolean die_3, boolean die_4, boolean die_5){
            if(die_1) {
                die1.roll();
            }
            if(die_2) {
                die2.roll();
            }
            if(die_3) {
                die3.roll();
            }
            if(die_4) {
                die4.roll();
            }
            if(die_5) {
                die5.roll();
            }

    }

    @Override
    public boolean isHuman() { return isHuman; }

    @Override
    public void rollAllDice() {
        die1.roll();
        die2.roll();
        die3.roll();
        die4.roll();
        die5.roll();

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getMarker() {
        return marker;
    }

    @Override
    public void setMarker(int marker) {
        this.marker = marker;
    }

    @Override
    public boolean payMarkerToPot(int payment) {

        if (this.marker - payment > 0) {
            marker -= payment;
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public ArrayList<Die> getDice() {
        return dice;
    }

    @Override
    public int[] getDieValues() {

        int[] output = new int[5];
        int i = 0;
        for (Die die : dice) {
            output[i++] = die.getCurrentFace();
        }

        return output;
    }

    @Override
    public void payMarkerFromPot(int pot) {

        this.marker += pot;

    }

    @Override
    public String toStorageString() {

        StringBuilder storageString = new StringBuilder();

        storageString.append(this.name + "\t");
        storageString.append(this.marker + "\t");
        storageString.append(this.isHuman +  "\t");
        dice.forEach(x -> storageString.append(x.getCurrentFace()).append(" "));

        return storageString.toString();
    }
}
