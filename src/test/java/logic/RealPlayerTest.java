package logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RealPlayerTest {

    @Test
    void testGetPlayerName () {
        RealPlayer player = new RealPlayer("Bernt", 100, true);

        player.setName("Dante");
        System.out.println(player.getName());

        Assertions.assertEquals("Dante", player.getName());
    }

    @Test
    void testGetPlayerMark () {
        RealPlayer player = new RealPlayer("Dante", 50, true);

        player.setMarker(100);
        System.out.println(player.getMarker());

        Assertions.assertEquals(100, player.getMarker());
    }

    @Test
    void testGetPlayerIsHumanTrue () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.isHuman());

        Assertions.assertTrue(player.isHuman());
    }

    @Test
    void testGetPlayerIsHumanFalse () {
        RealPlayer player = new RealPlayer("Dante", 100, false);

        System.out.println(player.isHuman());

        Assertions.assertFalse(player.isHuman());
    }

    @Test
    void testDie1NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die1.getSides());

        Assertions.assertEquals(6, player.die1.getSides());
    }

    @Test
    void testDie2NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die2.getSides());

        Assertions.assertEquals(6, player.die2.getSides());
    }

    @Test
    void testDie3NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die3.getSides());

        Assertions.assertEquals(6, player.die3.getSides());
    }

    @Test
    void testDie4NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die4.getSides());

        Assertions.assertEquals(6, player.die4.getSides());
    }

    @Test
    void testDie5NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die5.getSides());

        Assertions.assertEquals(6, player.die5.getSides());
    }

    @Test
    void testRollSomeDieDie1 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die1.getTimesRolled() + " " + player.die2.getTimesRolled() + " " + player.die3.getTimesRolled()
                + " " + player.die4.getTimesRolled() + " " + player.die5.getTimesRolled());
        player.rollSomeDie(true, false, false, false, false);

        System.out.println(player.die1.getTimesRolled() + " " + player.die2.getTimesRolled() + " " + player.die3.getTimesRolled()
                + " " + player.die4.getTimesRolled() + " " + player.die5.getTimesRolled());

        Assertions.assertEquals(2, player.die1.getTimesRolled());
    }

    @Test
    void testRollSomeDieDie2 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die1.getTimesRolled() + " " + player.die2.getTimesRolled() + " " + player.die3.getTimesRolled()
                + " " + player.die4.getTimesRolled() + " " + player.die5.getTimesRolled());
        player.rollSomeDie(false, true, false, false, false);

        System.out.println(player.die1.getTimesRolled() + " " + player.die2.getTimesRolled() + " " + player.die3.getTimesRolled()
                + " " + player.die4.getTimesRolled() + " " + player.die5.getTimesRolled());

        Assertions.assertEquals(2, player.die2.getTimesRolled());
    }

    @Test
    void testRollSomeDieDie3 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die1.getTimesRolled() + " " + player.die2.getTimesRolled() + " " + player.die3.getTimesRolled()
                + " " + player.die4.getTimesRolled() + " " + player.die5.getTimesRolled());
        player.rollSomeDie(false, false, true, false, false);

        System.out.println(player.die1.getTimesRolled() + " " + player.die2.getTimesRolled() + " " + player.die3.getTimesRolled()
                + " " + player.die4.getTimesRolled() + " " + player.die5.getTimesRolled());

        Assertions.assertEquals(2, player.die3.getTimesRolled());
    }

    @Test
    void testRollSomeDie4 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die1.getTimesRolled() + " " + player.die2.getTimesRolled() + " " + player.die3.getTimesRolled()
                + " " + player.die4.getTimesRolled() + " " + player.die5.getTimesRolled());
        player.rollSomeDie(false, false, false, true, false);

        System.out.println(player.die1.getTimesRolled() + " " + player.die2.getTimesRolled() + " " + player.die3.getTimesRolled()
                + " " + player.die4.getTimesRolled() + " " + player.die5.getTimesRolled());

        Assertions.assertEquals(2, player.die4.getTimesRolled());
    }

    @Test
    void testRollSomeDie5 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die1.getTimesRolled() + " " + player.die2.getTimesRolled() + " " + player.die3.getTimesRolled()
                + " " + player.die4.getTimesRolled() + " " + player.die5.getTimesRolled());
        player.rollSomeDie(false, false, false, false, true);

        System.out.println(player.die1.getTimesRolled() + " " + player.die2.getTimesRolled() + " " + player.die3.getTimesRolled()
                + " " + player.die4.getTimesRolled() + " " + player.die5.getTimesRolled());

        Assertions.assertEquals(2, player.die5.getTimesRolled());
    }

    @Test
    void testRollAllDieDie1 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die1.getTimesRolled());
        player.rollAllDice();
        System.out.println(player.die1.getTimesRolled());

        Assertions.assertEquals(2, player.die1.getTimesRolled());
    }

    @Test
    void testRollAllDieDie2 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die2.getTimesRolled());
        player.rollAllDice();
        System.out.println(player.die2.getTimesRolled());

        Assertions.assertEquals(2, player.die2.getTimesRolled());
    }

    @Test
    void testRollAllDieDie3 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die3.getTimesRolled());
        player.rollAllDice();
        System.out.println(player.die3.getTimesRolled());

        Assertions.assertEquals(2, player.die3.getTimesRolled());
    }

    @Test
    void testRollAllDieDie4 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die4.getTimesRolled());
        player.rollAllDice();
        System.out.println(player.die4.getTimesRolled());

        Assertions.assertEquals(2, player.die4.getTimesRolled());
    }

    @Test
    void testRollAllDieDie5 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.die5.getTimesRolled());
        player.rollAllDice();
        System.out.println(player.die5.getTimesRolled());

        Assertions.assertEquals(2, player.die5.getTimesRolled());
    }

    @Test
    void testPayMarkerToPot () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.getMarker());
        player.payMarkerToPot(20);
        System.out.println(player.getMarker());

        Assertions.assertEquals(80, player.getMarker());
    }

    @Test
    void testPayMarkerToPotWhenPlayerMarkAtZero () {
        RealPlayer player = new RealPlayer("Dante", 0, true);

        System.out.println(player.getMarker());
        player.payMarkerToPot(20);
        System.out.println(player.getMarker());

        Assertions.assertEquals(0, player.getMarker());
    }

    @Test
    void testPayMarkerFromPot () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.getMarker());
        player.payMarkerFromPot(20);
        System.out.println(player.getMarker());

        Assertions.assertEquals(120, player.getMarker());
    }

    @Test
    void testgetDice () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        System.out.println(player.getDice().size());

        Assertions.assertEquals(5, player.getDice().size());
    }

    @Test
    void testGetDieValues () {
        RealPlayer player = new RealPlayer("Dante", 100, true);
        RealPlayer fakePlayer =  mock(RealPlayer.class);

        when(fakePlayer.getDieValues()).thenReturn(new int[] {5,5,5,5,5});
        System.out.println("Fake: " + fakePlayer.getDieValues().length + " Real: " + player.getDieValues().length);

        Assertions.assertEquals(fakePlayer.getDieValues().length, player.getDieValues().length);
    }

    @Test
    void testToStorageString () {
        RealPlayer player = new RealPlayer("Dante", 100, false);
        RealPlayer player2 = new RealPlayer("Albedo", 150, true);

        System.out.println(player.toStorageString());
        System.out.println(player2.toStorageString());

        Assertions.assertNotEquals(player2.toStorageString(), player.toStorageString());
    }


}
