package logic;

import controller.GameController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DieTest {

    Die die;

    @Mock
    public static GameEngine gameEngine;

    @BeforeEach
    void RunningThisBeforeEach(){
        die = new Die(6);
    }

    @Test
    void getCurrentFaceCheckNotNullTrue(){
        int currentFace = die.getCurrentFace();
        Assertions.assertNotEquals(0,currentFace);
    }

    @Test
    void getSidesEquals6(){
        int actual = die.getSides();
        Assertions.assertEquals(6,actual);
    }

    @Test
    void getDieNameEquals2SidedDie(){
        die = new Die(2);
        String name = "Fair 2-sided die";
        Assertions.assertEquals(name,die.getDieName());
    }

    @Test
    void setDieNameEquals4SidedDie() {
        die.setDieName("Fair 4-sided die");
        Assertions.assertEquals("Fair 4-sided die", die.getDieName());
    }

    @Test
    void getRollHistorySizeNotNull(){
        Assertions.assertNotNull(die.getRollHistory());
    }

    @Test
    void getTimesRolledEquals3() {
        die = new Die(2);
        die.roll();
        die.roll();
        Assertions.assertEquals(3,die.getTimesRolled());
    }

    @Test
    void getCurrentFaceStringTest() {
        String currentFace = String.valueOf(die.getCurrentFace());
        Assertions.assertEquals(currentFace,die.getCurrentFaceString());
    }

    @Test
    void setCurrentFaceEqualsThree() {
        die.setCurrentFace(3);
        Assertions.assertEquals(3,die.getCurrentFace());
    }

    @Test
    void bettingDoneGettingCurrentPlayerIsHuman(){
        GameController g = new GameController(gameEngine);
        when(gameEngine.getCurrentPlayer()).thenReturn(new RealPlayer("Kamil",100,true));
        g.bettingDone();
        Assertions.assertTrue(gameEngine.getCurrentPlayer().isHuman());
    }

    @Test
    void IsBettingDonePropertyCheckTrueFXML(){
        GameController g = new GameController(gameEngine);
        when(gameEngine.getCurrentPlayer()).thenReturn(new RealPlayer("Kamil",100,true));
        g.bettingDone();
        Assertions.assertTrue(g.isBettingDone());
    }

    @Test
    void IsBettingDonePropertyCheckFalseFXML(){
        GameController g = new GameController(gameEngine);
        when(gameEngine.getCurrentPlayer()).thenReturn(new RealPlayer("Kamil",100,true));
        g.bettingDone();
        g.setBettingDone(false);
        Assertions.assertFalse(g.isBettingDone());
    }

    @Test
    void bettingDoneMethodIsCalledTrue(){
        GameController g = new GameController(gameEngine);
        when(gameEngine.getCurrentPlayer()).thenReturn(new RealPlayer("Kamil",100,true));
        g.bettingDone();
        Assertions.assertEquals("bettingDone", g.getMethodName());
    }

    @Test
    void setCurrentFaceNewFaceCantNotBeZero(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->die.setCurrentFace(0),"Current Face cant be zero");
    }

}








