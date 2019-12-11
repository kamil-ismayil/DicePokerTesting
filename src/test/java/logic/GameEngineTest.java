
package logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class GameEngineTest {

    @Test
    public void determineHandStrengthPairTest() {

        int[] inputHand = {1, 1, 3, 4, 5};
        int[] expected = {1, 1, 5, 4, 3};
        int[] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthPairTest_2() {

        int[] inputHand = {4, 1, 5, 4, 6};
        int[] expected = {1, 4, 6, 5, 1};
        int[] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTest2Pair() {

        int [] inputHand = new int[]{5, 5, 4, 4, 6};
        int [] expected = new int[]{2, 5, 4, 6, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTest3ofKind() {

        int [] inputHand = new int[]{1, 1, 1, 3, 2};
        int [] expected = new int[]{3, 1, 3, 2, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTestStraight() {

        int [] inputHand = new int[]{1, 2, 3, 4, 5};
        int [] expected = new int[]{4, 5, 0, 0, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTestFullHouse() {

        int [] inputHand = new int[]{2, 2, 2, 5, 5};
        int [] expected = new int[]{5, 2, 5, 0, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTest4ofKind() {

        int [] inputHand = new int[]{6, 6, 6, 6, 1};
        int [] expected = new int[]{6, 6, 1, 0, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTest5ofKind() {

        int [] inputHand = new int[]{1, 1, 1, 1, 1};
        int [] expected = new int[]{7, 1, 0, 0, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void determineHandStrengthTestNada() {

        int [] inputHand = new int[]{1, 2, 3, 5, 6};
        int [] expected = new int[]{0, 4, 0, 0, 0};
        int [] actual = GameEngine.determineHandStrength(inputHand);
        Assertions.assertArrayEquals(expected, actual);

    }


    @Test
    public void compareHandStrengthTestExpectTrue() {

        int[] hand1 = {7, 6, 0, 0, 0};
        int[] hand2 = {0, 3, 0, 0, 0};
        Boolean result = GameEngine.compareHandStrength(hand1, hand2);
        Assertions.assertTrue(result);

    }

    @Test
    public void compareHandStrengthTestExpectFalse() {

        int[] hand2 = {7, 6, 0, 0, 0};
        int[] hand1 = {0, 3, 0, 0, 0};
        Boolean result = GameEngine.compareHandStrength(hand1, hand2);
        Assertions.assertFalse(result);

    }

    @Test
    public void compareHandStrengthTestTieExpectFalse(){

        int[] hand1 = {7,6,0,0,0};
        int[] hand2= {7,6,0,0,0};
        Boolean result = GameEngine.compareHandStrength(hand1,hand2);
        Assertions.assertFalse(result);

    }

    @Test
    public void sortHandStrengthTest() {

        int[] hand1 = {0,5,0,0,0}; // Rolled nothing, missing a 5.
        int[] hand2 = {1,2,6,5,4}; // Rolled a pair of ones, 6,5,4 kickers.
        int[] hand3 = {2,4,5,2,0}; // Rolled two pairs of 5s and 4s, with a 2 kicker.
        int[] hand4 = {5,5,1,0,0}; // Rolled a full house 5s and 1s.

        int[][] testHand = {hand1,hand2,hand3,hand4};
        int[][] expectedHand = {hand4, hand3,hand2, hand1};
        int[][] sortedHand = GameEngine.sortHandStrength(testHand);
        Assertions.assertArrayEquals(expectedHand, sortedHand);
    }

    @Test
    public void sortHandStrengthTieTest() {

        int[] hand1 = {0,5,0,0,0}; // Rolled nothing, missing a 5.
        int[] hand2 = {5,5,1,0,0}; // Rolled a full house 5s and 1s.
        int[] hand3 = {2,4,5,2,0}; // Rolled two pairs of 5s and 4s, with a 2 kicker.
        int[] hand4 = {5,5,1,0,0}; // Rolled a full house 5s and 1s.

        int[][] testHand = {hand1,hand2,hand3,hand4};
        int[][] expectedHand = {hand4, hand2,hand3, hand1};
        int[][] sortedHand = GameEngine.sortHandStrength(testHand);
        Assertions.assertArrayEquals(expectedHand, sortedHand);
    }

    @Test
    public void GameEngineConstructorHandlesAPlayerBeingNull() {
        List<Player> listOfFakePlayers = new ArrayList<>();
        listOfFakePlayers.add(null);
        listOfFakePlayers.add(new FakePlayer());
        listOfFakePlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfFakePlayers);
        Assertions.assertEquals(2,testEngine.getCurrentPlayerNumber());

    }

    @Test
    public void currentPlayerAnteTrueTest(){

        List<Player> listOfFakePlayers = new ArrayList<>();
        listOfFakePlayers.add(new FakePlayer());

        listOfFakePlayers.get(0).setMarker(15);

        GameEngine testEngine = new GameEngine(listOfFakePlayers);

        Assertions.assertTrue(testEngine.currentPlayerPayAnte());
        Assertions.assertEquals(10,testEngine.getCurrentPot());

    }

    @Test
    public void currentPlayerAnteFalseTest(){

        List<Player> listOfFakePlayers = new ArrayList<>();
        listOfFakePlayers.add(new FakePlayer());

        listOfFakePlayers.get(0).setMarker(5);

        GameEngine testEngine = new GameEngine(listOfFakePlayers);
        Assertions.assertFalse(testEngine.currentPlayerPayAnte());

    }

    @Test
    public void getPlayerTest_expect2(){

        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new RealPlayer("a", 15, true));
        listOfPlayers.add(new RealPlayer("b", 20, true));
        listOfPlayers.add(new RealPlayer("c", 15, true));
        listOfPlayers.add(new RealPlayer("d", 31, true));

        GameEngine testEngine = new GameEngine(listOfPlayers);

        Assertions.assertEquals("b", testEngine.getPlayer(2).getName());

    }

    @Test
    public void isPlayerWinnerTest_expectsTrue() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new RealPlayer("a", 15, true));

        GameEngine testEngine = new GameEngine(listOfPlayers);

        Assertions.assertTrue(testEngine.isPlayerWinner());
    }

    @Test
    public void isPlayerWinnerTest_expectsFalse() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new RealPlayer("a", 15, true));
        listOfPlayers.add(new RealPlayer("b", 20, true));

        GameEngine testEngine = new GameEngine(listOfPlayers);

        Assertions.assertFalse(testEngine.isPlayerWinner());
    }

    @Test
    public void eliminateCurrentPlayerTest_expectsStringb() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new RealPlayer("a", 15, true));
        listOfPlayers.add(new RealPlayer("b", 20, true));

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.eliminateCurrentPlayer();

        Assertions.assertEquals("b",testEngine.getCurrentPlayer().getName());
    }

    @Test
    public void roundWinnerTest_expectsListOfSingle2() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        List<Integer> expected = new ArrayList<>();
        expected.add(2);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.eliminateCurrentPlayer();
        Assertions.assertEquals(expected, testEngine.roundWinner());

    }

    @Test
    public void roundWinnerTieTest_expectsListOf1and2() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        Assertions.assertEquals(expected, testEngine.roundWinner());

    }

    @Test
    public void getCurrentPlayerBetTest_expects0() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        Assertions.assertEquals(0, testEngine.getCurrentPlayerBet());
    }

    @Test
    public void rollCurrentPlayerTest_expectArrayOf5Ones() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        Assertions.assertArrayEquals(new int[] {1, 1, 1, 1, 1}, testEngine.rollCurrentPlayer());
    }

    @Test
    public void getCurrentRoundTest_expects1() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        Assertions.assertEquals(1,testEngine.getCurrentRound());
    }

    @Test
    public void nextRoundTest_expectsRound5() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        for (int i = 1; i < 5; i++) {
            testEngine.newRound();
        }
        Assertions.assertEquals(5,testEngine.getCurrentRound());
    }

    @Test
    public void nextPlayerTest_expects2() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.nextPlayer();
        Assertions.assertEquals(2,testEngine.getCurrentPlayerNumber());
    }

    @Test
    public void nextPlayerTest_expects1() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.nextPlayer();
        testEngine.nextPlayer();
        Assertions.assertEquals(1,testEngine.getCurrentPlayerNumber());
    }

    @Test
    public void isCurrentPlayerFirstPlayerTest_expectsTrue() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);

        Assertions.assertTrue(testEngine.isCurrentPlayerFirstPlayer());
    }

    @Test
    public void isCurrentPlayerFirstPlayerTest_expectsFalse() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.nextPlayer();

        Assertions.assertFalse(testEngine.isCurrentPlayerFirstPlayer());
    }

    @Test
    public void currentPlayerFoldTest() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.nextPlayer();
        testEngine.currentPlayerFold();

        List<Integer> expected = new ArrayList<>();
        expected.add(1);

        Assertions.assertEquals(expected, testEngine.roundWinner());
    }

    @Test
    public void isCurrentPlayerLastTest_expectsTrue() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);

        Assertions.assertTrue(testEngine.isCurrentPlayerLastPlayer());
    }

    @Test
    public void isCurrentPlayerLastTest_expectsFalse() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);

        Assertions.assertFalse(testEngine.isCurrentPlayerLastPlayer());
    }

    @Test
    public void currentPlayerBetTest_expectsTrue() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);

        GameEngine testEngine = new GameEngine(listOfPlayers);

        Assertions.assertTrue(testEngine.currentPlayerBet(10));
    }

    @Test
    public void currentPlayerBetTest_expectsFalse() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(5);

        GameEngine testEngine = new GameEngine(listOfPlayers);

        Assertions.assertFalse(testEngine.currentPlayerBet(10));
    }

    @Test
    public void currentPlayerBetTest_bettingMoreThanPreviousPlayer_expectsTrue() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(15);


        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);
        testEngine.nextPlayer();

        Assertions.assertTrue(testEngine.currentPlayerBet(12));
    }

    @Test
    public void currentPlayerBetTest_bettingLessThanPreviousPlayer_expectsFalse() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(15);


        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);
        testEngine.nextPlayer();

        Assertions.assertFalse(testEngine.currentPlayerBet(5));
    }

    @Test
    public void currentPlayerCall_expects20() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(15);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);
        testEngine.nextPlayer();
        testEngine.currentPlayerCall();

        Assertions.assertEquals(20, testEngine.getCurrentPot());
    }

    @Test
    public void getHighestBetTest_expects10() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);

        Assertions.assertEquals(10, testEngine.getHihgestBet());
    }

    @Test
    public void getHighestBetTest_betThenRaise_expects15() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(20);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);
        testEngine.nextPlayer();
        testEngine.currentPlayerBet(15);

        Assertions.assertEquals(15, testEngine.getHihgestBet());
    }

    @Test
    public void getHighestBetTest_betThenFailedBet_expects10() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(20);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);
        testEngine.nextPlayer();
        testEngine.currentPlayerBet(5);

        Assertions.assertEquals(10, testEngine.getHihgestBet());
    }

    @Test
    public void getHighestBetTest_NoBet_expects0() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);

        Assertions.assertEquals(0, testEngine.getHihgestBet());
    }

    @Test
    public void getCurrentPlayerBet_expects10() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(20);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);

        Assertions.assertEquals(10, testEngine.getCurrentPlayerBet());
    }

    @Test
    public void getCurrentPlayerBet_expects15() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(20);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);
        testEngine.nextPlayer();
        testEngine.currentPlayerBet(15);

        Assertions.assertEquals(15, testEngine.getCurrentPlayerBet());
    }

    @Test
    public void getCurrentPlayerBet_newRound_expects10() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(20);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);
        testEngine.nextPlayer();
        testEngine.currentPlayerBet(15);
        testEngine.nextPlayer();

        Assertions.assertEquals(10, testEngine.getCurrentPlayerBet());
    }

    @Test
    public void getCurrentPlayerCallingCostTest_BetThenCall_expects10() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(20);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);
        testEngine.nextPlayer();

        Assertions.assertEquals(10, testEngine.getCurrentPlayerCallingCost());

    }

    @Test
    public void getCurrentPlayerCallingCostTest_BetThenRaiseThenCall_expects5() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(20);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);
        testEngine.nextPlayer();
        testEngine.currentPlayerBet(15);
        testEngine.nextPlayer();

        Assertions.assertEquals(5, testEngine.getCurrentPlayerCallingCost());
    }

    @Test
    public void isBEttingDoneTest_BetThenCall_expectsTrue() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(20);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);
        testEngine.nextPlayer();
        testEngine.currentPlayerCall();
        testEngine.nextPlayer();

        Assertions.assertTrue(testEngine.isBettingDone());
    }

    @Test
    public void isBettingDoneTest_BetThenRaise_expectsFalse() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(20);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);
        testEngine.nextPlayer();
        testEngine.currentPlayerBet(15);
        testEngine.nextPlayer();

        Assertions.assertFalse(testEngine.isBettingDone());
    }

    @Test
    public void isBettingDoneTest_BetThenRaiseThenCall_expectsTrue() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(20);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.currentPlayerBet(10);
        testEngine.nextPlayer();
        testEngine.currentPlayerBet(15);
        testEngine.nextPlayer();
        testEngine.currentPlayerCall();
        testEngine.nextPlayer();

        Assertions.assertTrue(testEngine.isBettingDone());
    }

    @Test
    public void createStorageStringTest() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.add(new FakePlayer());
        listOfPlayers.get(0).setMarker(15);
        listOfPlayers.get(1).setMarker(20);

        GameEngine testEngine = new GameEngine(listOfPlayers);
        String expected =   "1 : This is a fake storage stringtrue true\n" +
                            "2 : This is a fake storage stringtrue true\n" +
                            "0\t0\t0\t10\t1\t1\n" +
                            "0 0 0 0 ";

        Assertions.assertEquals(expected, testEngine.createStorageString());
    }

    @Test
    public void setStateFromStorageStringTest() {
        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        String storageStringInput = "1 : Player 1\t160\ttrue\t4 5 5 5 6 true true" + "\n" +
                                    "2 : Player 2\t75\ttrue\t4 3 1 2 2 true true"  + "\n" +
                                    "3 : Player 3\t90\ttrue\t4 5 1 1 6 true true"  + "\n" +
                                    "4 : Player 4\t75\ttrue\t6 1 1 4 2 true true"  + "\n" +
                                    "0\t1\t0\t100\t1\t1" + "\n" +
                                    "10 20 30 40";

        GameEngine testEngine = new GameEngine(listOfPlayers);
        testEngine.setStateFromStorageString(storageStringInput);

        Assertions.assertEquals(10, testEngine.getCurrentPlayerBet());


    }

    @Test
    public void getCurrentPlayerHandStrengthTest() {

        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        int [] expected = {7, 6, 0, 0, 0};

        Assertions.assertArrayEquals(expected, testEngine.getCurrentPlayerHandStrength());

    }

    @Test
    public void rerollCurrentPlayerTest(){

        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);

        testEngine.rerollCurrentPlayer(new boolean[] {true, true, true, true, true});

        int [] expected = {1,1,1,1,1};

        FakePlayer testPlayer = (FakePlayer) listOfPlayers.get(0);

        Assertions.assertArrayEquals(expected, testPlayer.getRerollSpy());

    }


    @Test
    public void rerollCurrentPlayerTest2(){

        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);

        testEngine.rerollCurrentPlayer(new boolean[] {false, false, false, false, false});

        int [] expected = {0,0,0,0,0};

        FakePlayer testPlayer = (FakePlayer) listOfPlayers.get(0);

        Assertions.assertArrayEquals(expected, testPlayer.getRerollSpy());

    }

    @Test
    public void rerollCurrentPlayerTest3(){

        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);

        testEngine.rerollCurrentPlayer(new boolean[] {true, false, true, false, true});

        int [] expected = {1,0,1,0,1};

        FakePlayer testPlayer = (FakePlayer) listOfPlayers.get(0);

        Assertions.assertArrayEquals(expected, testPlayer.getRerollSpy());

    }

    @Test
    public void getListOfAllPlayersTest(){

        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        List<Player> expected = listOfPlayers;

        Assertions.assertEquals(expected, testEngine.getListOfAllPlayers());

    }

    @Test
    public void saveDataToFileTest(@TempDir File tempFile) throws IOException {

        List<Player> listOfPlayers = new ArrayList<>();
        listOfPlayers.add(new FakePlayer());

        GameEngine testEngine = new GameEngine(listOfPlayers);
        String saveString = "Test";
        testEngine.saveDataToFile(saveString, tempFile);

        Assertions.assertLinesMatch(List.of(saveString), Files.readAllLines(tempFile.toPath()));






    }








    private String handStrengthOutput(int[] expected, int [] actual) {
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            string1.append(expected[i]);
            string1.append(" ");
            string2.append(actual[i]);
            string2.append(" ");
        }

        return ("Expected: " + string1.toString() + " Actual: " +string2.toString());
    }

}