package logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



import static org.mockito.Mockito.*;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class RealPlayerTest {

    @Test
    void testGetPlayerName () {
        RealPlayer player = new RealPlayer("Bernt", 100, true);

        player.setName("Dante");

        Assertions.assertEquals("Dante", player.getName(), "player name should be Dante");
    }

    @Test
    void testGetPlayerMark () {
        RealPlayer player = new RealPlayer("Dante", 50, true);

        player.setMarker(100);

        Assertions.assertEquals(100, player.getMarker(), "player mark should be 100");
    }

    @Test
    void testGetPlayerIsHumanTrue () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertTrue(player.isHuman(), "player isHuman should be true");
    }

    @Test
    void testGetPlayerIsHumanFalse () {
        RealPlayer player = new RealPlayer("Dante", 100, false);

        Assertions.assertFalse(player.isHuman(), "player isHuman should be false");
    }

    @Test
    void testDie1NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertEquals(6, player.die1.getSides(), "player die1 should have 6 sides");
    }

    @Test
    void testDie2NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertEquals(6, player.die2.getSides(), "player die2 should have 6 sides");
    }

    @Test
    void testDie3NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertEquals(6, player.die3.getSides(), "player die3 should have 6 sides");
    }

    @Test
    void testDie4NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertEquals(6, player.die4.getSides(), "player die4 should have 6 sides");
    }

    @Test
    void testDie5NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertEquals(6, player.die5.getSides(), "player die5 should have 6 sides");
    }

    @Test
    void testRollSomeDieDie1 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollSomeDie(true, false, false, false, false);

        Assertions.assertEquals(2, player.die1.getTimesRolled(), "die1 should have rolled two times after it's creation");
    }

    @Test
    void testRollSomeDieDie2 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollSomeDie(false, true, false, false, false);

        Assertions.assertEquals(2, player.die2.getTimesRolled(), "die2 should have rolled two times after it's creation");
    }

    @Test
    void testRollSomeDieDie3 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollSomeDie(false, false, true, false, false);

        Assertions.assertEquals(2, player.die3.getTimesRolled(), "die3 should have rolled two times after it's creation");
    }

    @Test
    void testRollSomeDie4 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollSomeDie(false, false, false, true, false);

        Assertions.assertEquals(2, player.die4.getTimesRolled(), "die4 should have rolled two times after it's creation");
    }

    @Test
    void testRollSomeDie5 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollSomeDie(false, false, false, false, true);

        Assertions.assertEquals(2, player.die5.getTimesRolled(), "die5 should have rolled two times after it's creation");
    }

    @Test
    void testRollAllDieDie1 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollAllDice();

        Assertions.assertEquals(2, player.die1.getTimesRolled(), "die1 should have rolled two times after it's creation");
    }

    @Test
    void testRollAllDieDie2 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollAllDice();

        Assertions.assertEquals(2, player.die2.getTimesRolled(), "die2 should have rolled two times after it's creation");
    }

    @Test
    void testRollAllDieDie3 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollAllDice();

        Assertions.assertEquals(2, player.die3.getTimesRolled(), "die3 should have rolled two times after it's creation");
    }

    @Test
    void testRollAllDieDie4 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollAllDice();

        Assertions.assertEquals(2, player.die4.getTimesRolled(), "die4 should have rolled two times after it's creation");
    }

    @Test
    void testRollAllDieDie5 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollAllDice();

        Assertions.assertEquals(2, player.die5.getTimesRolled(), "die5 should have rolled two times after it's creation");
    }

    @Test
    void testPayMarkerToPot () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.payMarkerToPot(20);

        Assertions.assertEquals(80, player.getMarker(), "the player should have 80 marks");
    }

    @Test
    void testPayMarkerToPotWhenPlayerCannotPay () {
        RealPlayer player = new RealPlayer("Dante", 10, true);

        player.payMarkerToPot(20);

        Assertions.assertEquals(10, player.getMarker(), "hte player shouldn't not be paying and should still have 10 marks");
    }

    @Test
    void testPayMarkerFromPot () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.payMarkerFromPot(20);

        Assertions.assertEquals(120, player.getMarker(),"the player should have 120 marks");
    }

    @Test
    void testgetDice () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertEquals(5, player.getDice().size(), "the size should be 5");
    }

    @Test
    void testGetDieValues () {
        RealPlayer player = new RealPlayer("Dante", 100, true);
        RealPlayer fakePlayer =  mock(RealPlayer.class);

        when(fakePlayer.getDieValues()).thenReturn(new int[] {1,2,3,4,5});

        Assertions.assertEquals(fakePlayer.getDieValues().length, player.getDieValues().length, "The lenght should be 5");
    }

    @Test
    void testToStorageString () {
        RealPlayer player = new RealPlayer("Dante", 80, true);
        RealPlayer player2 = new RealPlayer("Albedo", 150, false);

        player.rollAllDice(); player.payMarkerToPot(40);
        player2.rollAllDice(); player2.payMarkerFromPot(40);

        Assertions.assertNotEquals(player2.toStorageString(), player.toStorageString(),  player2.toStorageString() + "\n" + player.toStorageString());
    }


}
