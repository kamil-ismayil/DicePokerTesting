package logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



import static org.mockito.Mockito.*;



public class RealPlayerTest {

    @Test
    void testGetPlayerName () {
        RealPlayer player = new RealPlayer("Bernt", 100, true);

        player.setName("Dante");

        Assertions.assertEquals("Dante", player.getName());
    }

    @Test
    void testGetPlayerMark () {
        RealPlayer player = new RealPlayer("Dante", 50, true);

        player.setMarker(100);

        Assertions.assertEquals(100, player.getMarker());
    }

    @Test
    void testGetPlayerIsHumanTrue () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertTrue(player.isHuman());
    }

    @Test
    void testGetPlayerIsHumanFalse () {
        RealPlayer player = new RealPlayer("Dante", 100, false);

        Assertions.assertFalse(player.isHuman());
    }

    @Test
    void testDie1NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertEquals(6, player.die1.getSides());
    }

    @Test
    void testDie2NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertEquals(6, player.die2.getSides());
    }

    @Test
    void testDie3NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertEquals(6, player.die3.getSides());
    }

    @Test
    void testDie4NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertEquals(6, player.die4.getSides());
    }

    @Test
    void testDie5NumberOfSides () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertEquals(6, player.die5.getSides());
    }

    @Test
    void testRollSomeDieDie1 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollSomeDie(true, false, false, false, false);

        Assertions.assertEquals(2, player.die1.getTimesRolled());
    }

    @Test
    void testRollSomeDieDie2 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollSomeDie(false, true, false, false, false);

        Assertions.assertEquals(2, player.die2.getTimesRolled());
    }

    @Test
    void testRollSomeDieDie3 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollSomeDie(false, false, true, false, false);

        Assertions.assertEquals(2, player.die3.getTimesRolled());
    }

    @Test
    void testRollSomeDie4 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollSomeDie(false, false, false, true, false);

        Assertions.assertEquals(2, player.die4.getTimesRolled());
    }

    @Test
    void testRollSomeDie5 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollSomeDie(false, false, false, false, true);

        Assertions.assertEquals(2, player.die5.getTimesRolled());
    }

    @Test
    void testRollAllDieDie1 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollAllDice();

        Assertions.assertEquals(2, player.die1.getTimesRolled());
    }

    @Test
    void testRollAllDieDie2 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollAllDice();

        Assertions.assertEquals(2, player.die2.getTimesRolled());
    }

    @Test
    void testRollAllDieDie3 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollAllDice();

        Assertions.assertEquals(2, player.die3.getTimesRolled());
    }

    @Test
    void testRollAllDieDie4 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollAllDice();

        Assertions.assertEquals(2, player.die4.getTimesRolled());
    }

    @Test
    void testRollAllDieDie5 () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.rollAllDice();

        Assertions.assertEquals(2, player.die5.getTimesRolled());
    }

    @Test
    void testPayMarkerToPot () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.payMarkerToPot(20);

        Assertions.assertEquals(80, player.getMarker());
    }

    @Test
    void testPayMarkerToPotWhenPlayerCannotPay () {
        RealPlayer player = new RealPlayer("Dante", 10, true);

        player.payMarkerToPot(20);

        Assertions.assertEquals(10, player.getMarker());
    }

    @Test
    void testPayMarkerFromPot () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        player.payMarkerFromPot(20);

        Assertions.assertEquals(120, player.getMarker());
    }

    @Test
    void testgetDice () {
        RealPlayer player = new RealPlayer("Dante", 100, true);

        Assertions.assertEquals(5, player.getDice().size());
    }

    @Test
    void testGetDieValues () {
        RealPlayer player = new RealPlayer("Dante", 100, true);
        RealPlayer fakePlayer =  mock(RealPlayer.class);

        when(fakePlayer.getDieValues()).thenReturn(new int[] {1,2,3,4,5});

        Assertions.assertEquals(fakePlayer.getDieValues().length, player.getDieValues().length);
    }

    @Test
    void testToStorageString () {
        RealPlayer player = new RealPlayer("Dante", 80, true);
        RealPlayer player2 = new RealPlayer("Albedo", 150, false);

        player.rollAllDice(); player.payMarkerToPot(40);
        player2.rollAllDice(); player2.payMarkerFromPot(40);

        Assertions.assertNotEquals(player2.toStorageString(), player.toStorageString());
    }


}
