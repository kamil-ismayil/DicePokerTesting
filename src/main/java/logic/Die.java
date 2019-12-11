package logic;

import java.util.ArrayList;
/**
 *  Class Represents a Fair N-sided die (sides CANNOT be negative or zero).
 *
 *  Class can also be used to make dice with custom sides, in this case a
 *  String array should be used as constructor argument and a die with sides
 *  equal to the length of the array will be created.
 *
 *  Dice start with a random face up.
 *
 *  The dice can also be given specific names. The names are the only class
 *  variable that can be changed post constructor.
 *
 *  Ues the method roll() to roll the die, then use method getCurrentFace()
 *  or getCurrentFaceString() to read the die.
 *
 * **/
public class Die {

    // ---- Class Variables ---- //

    private int sides;
    private int currentFace;
    private int timesRolled = 0;

    private String dieName;
    private String[] sideOptions;

    private ArrayList<Integer> rollHistory = new ArrayList<>();

    // ---- Constructors ---- //

    public Die (int numberOfSides) {

        if (numberOfSides < 1) { throw (new IllegalArgumentException("Cannot create die with less than 1 side")); };

        sides = numberOfSides;
        roll();

        dieName = "Fair " + sides + "-sided die";

        sideOptions = new String[sides];
        for (int i = 0; i < sides; i++) {
            sideOptions[i] = "" + (i + 1);
        }

    }

    // ---- Methods ---- //
    public void roll() {

        currentFace = (int) (1 + Math.random()*sides);
        timesRolled++;
        rollHistory.add(currentFace);

    } //Sets the currentFace variable to a random face.

    int getSides() {return sides;}
    int getCurrentFace() {return currentFace;} //Use this to read a normal die
    int getTimesRolled() {return timesRolled;}

    public ArrayList<Integer> getRollHistory() { return rollHistory;};

    public String getDieName() { return dieName;}
    public String getCurrentFaceString() { return sideOptions[currentFace-1]; } //Use this to read a special die

    public void setDieName(String newName) {
        dieName = newName;
    }
    public void setCurrentFace(int newFace) {

        if (newFace < 1 || newFace > sides) {
            throw (new IllegalArgumentException("Can only set this die to side 1-" + sides));
        }
        currentFace = newFace;
    }
}