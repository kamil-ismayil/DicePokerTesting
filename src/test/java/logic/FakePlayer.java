package logic;

import java.util.ArrayList;
import java.util.List;

public class FakePlayer implements Player {



    int rerollSpy[] = new int[5];

    int marker = 0;
    String name = "";



    @Override
    public void rollSomeDie(boolean die_1, boolean die_2, boolean die_3, boolean die_4, boolean die_5) {
        if(die_1){
            rerollSpy[0] = 1;
        }
        if(die_2){
            rerollSpy[1] = 1;
        }
        if(die_3){
            rerollSpy[2] = 1;
        }
        if(die_4){
            rerollSpy[3] = 1;
        }
        if(die_5){
            rerollSpy[4] = 1;
        }
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public void rollAllDice() {

    }

    public int[] getRerollSpy() { return rerollSpy; }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getMarker() {
        return this.marker;
    }

    @Override
    public void setMarker(int marker) {
        this.marker = marker;
    }

    @Override
    public boolean payMarkerToPot(int payment) {
        return (this.getMarker() >= 10);
    }

    @Override
    public List<Die> getDice() {
        Die d1 = new Die(1);
        List<Die> output = new ArrayList<>();
        output.add(d1);
        output.add(d1);
        output.add(d1);
        output.add(d1);
        output.add(d1);
        return output;
    }

    @Override
    public int[] getDieValues() {
        return new int[] {6,6,6,6,6};
    }

    @Override
    public void payMarkerFromPot(int pot) {

    }

    @Override
    public String toStorageString() {
        return "This is a fake storage string";
    }
}
