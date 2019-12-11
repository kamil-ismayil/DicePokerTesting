package controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.GameEngine;
import logic.Player;
import logic.RealPlayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameController {

    GameEngine mainGame;

    public GameController(GameEngine gameEngine) {
        this.mainGame = gameEngine;
    }

    boolean[] rerollSelected;

    boolean turn = false;

    //GameEngine  mainGame;

    @FXML
    private ToggleButton die1, die2, die3, die4, die5;

    @FXML
    private Button p1_die1, p1_die2, p1_die3, p1_die4, p1_die5;

    @FXML
    private Button p2_die1, p2_die2, p2_die3, p2_die4, p2_die5;

    @FXML
    private Button p3_die1, p3_die2, p3_die3, p3_die4, p3_die5;

    @FXML
    private Button p4_die1, p4_die2, p4_die3, p4_die4, p4_die5;

    @FXML
    private Label player1, player2, player3, player4, p1_mark, p2_mark, p3_mark, p4_mark;

    @FXML
    private Label player_turn, pot_text, p1_pot, p2_pot, p3_pot, p4_pot;

    @FXML
    private Label PlayerPot[];
    private Label playerBets[];

    @FXML
    private TextField Bet_field;

    @FXML
    private Button Bet_button, Call_button;

    @FXML
    private Button Fold_button, save;

    @FXML
    private Button Roll, Reroll;

    @FXML
    private ToggleButton Die1, Die2, Die3, Die4, Die5;

    @FXML
    private GridPane p1Grid, p2Grid, p3Grid, p4Grid;

    @FXML
    private VBox p1Box, p2Box, p3Box, p4Box;


    public ToggleButton[] Dices;
    public Button[] P1Dice;
    public Button[] P2Dice;
    public Button[] P3Dice;
    public Button[] P4Dice;

    private Button[][] allPlayerDice;
    String methodName;
    BooleanProperty bettingDone = new SimpleBooleanProperty();
    int startPosition = 0;

    @FXML
    public void initialize(){

        Dices = new ToggleButton[5];

        Dices[0] = Die1;
        Dices[1] = Die2;
        Dices[2] = Die3;
        Dices[3] = Die4;
        Dices[4] = Die5;

        PlayerPot = new Label[4];
        PlayerPot[0] = p1_mark;
        PlayerPot[1] = p2_mark;
        PlayerPot[2] = p3_mark;
        PlayerPot[3] = p4_mark;

        playerBets = new Label[4];
        playerBets[0] = p1_pot;
        playerBets[1] = p2_pot;
        playerBets[2] = p3_pot;
        playerBets[3] = p4_pot;

        allPlayerDice = new Button[4][5];
        allPlayerDice[0][0] = p1_die1;
        allPlayerDice[0][1] = p1_die2;
        allPlayerDice[0][2] = p1_die3;
        allPlayerDice[0][3] = p1_die4;
        allPlayerDice[0][4] = p1_die5;
        allPlayerDice[1][0] = p2_die1;
        allPlayerDice[1][1] = p2_die2;
        allPlayerDice[1][2] = p2_die3;
        allPlayerDice[1][3] = p2_die4;
        allPlayerDice[1][4] = p2_die5;
        allPlayerDice[2][0] = p3_die1;
        allPlayerDice[2][1] = p3_die2;
        allPlayerDice[2][2] = p3_die3;
        allPlayerDice[2][3] = p3_die4;
        allPlayerDice[2][4] = p3_die5;
        allPlayerDice[3][0] = p4_die1;
        allPlayerDice[3][1] = p4_die2;
        allPlayerDice[3][2] = p4_die3;
        allPlayerDice[3][3] = p4_die4;
        allPlayerDice[3][4] = p4_die5;

        RealPlayer p1 = new RealPlayer("Kamil",100,true);
        RealPlayer p2 = new RealPlayer("Kamil",100,true);
        RealPlayer p3 = new RealPlayer("Kamil",100,true);
        RealPlayer p4 = new RealPlayer("Kamil",100,true);

        Bet_button.disableProperty().bind(bettingDone);
        Call_button.disableProperty().bind(bettingDone);
        Fold_button.disableProperty().bind(bettingDone);
        Bet_field.disableProperty().bind(bettingDone);
        Reroll.disableProperty().bind(bettingDone);

        rerollSelected = new boolean[5];

        ArrayList<Player> lop = new ArrayList<>();
        lop.add(p1);
        lop.add(p2);
        lop.add(p3);
        lop.add(p4);
        mainGame = new GameEngine(lop);

        currentPlayerTurn();

        if(p1 != null){
            startPosition = 1;
            player1.setText(p1.getName());
            turn = true;
            p1_mark.setText(String.valueOf(p1.getMarker()));
            player_turn.setText(p1.getName() + " turn");
        } else {p1_pot.setText("N/A");}

        if(p2 != null) {
            startPosition = 2;
            player2.setText(p2.getName());
            p2_mark.setText(String.valueOf(p2.getMarker()));
            if(turn == false){
                turn = true;
                player_turn.setText(p2.getName() + " turn");
            }
        } else {p2_pot.setText("N/A");}

        if(p3 != null) {
            player3.setText(p3.getName());
            p3_mark.setText(String.valueOf(p3.getMarker()));
            if(turn == false){
                turn = true;
                player_turn.setText(p3.getName() + " turn");
            }
        } else {p3_pot.setText("N/A");}

        if(p4 != null) {
            player4.setText(p4.getName());
            p4_mark.setText(String.valueOf(p4.getMarker()));
            if(turn == false) {
                turn = true;
                player_turn.setText(p4.getName() + " turn");
            }
        } else {p4_pot.setText("N/A");}

        currentPlayerTurn();
        toggleButton();

        pot_text.setText(String.valueOf(mainGame.getCurrentPot()));

        Bet_button.setDisable(true);
        Call_button.setDisable(true);
        Fold_button.setDisable(true);

        Bet_field.setText("");
        Bet_field.setDisable(true);

        Reroll.setDisable(true);

        if (!mainGame.getCurrentPlayer().isHuman()) {
            this.roll();
        }
    }

    public void roll(){

        if (mainGame.currentPlayerPayAnte()) {

            int diceValues[] = mainGame.rollCurrentPlayer();
            updateMiddleDie(mainGame.getCurrentPlayerNumber());

            int currentPlayerNumber = mainGame.getCurrentPlayerNumber();  //Current player number refers to which players graphics are to be updated.
            System.out.println("Player Number: " + currentPlayerNumber);

            updatePlayerDie(currentPlayerNumber);
            updateMarkerAndPot(currentPlayerNumber);
            mainGame.nextPlayer();
            currentPlayerTurn ();

            updateMiddleDie(mainGame.getCurrentPlayerNumber());
            if (mainGame.isCurrentPlayerFirstPlayer()) {
                Roll.setDisable(true);
                Reroll.setDisable(true);

                Bet_button.setDisable(false);
                Call_button.setDisable(false);
                Fold_button.setDisable(false);
                Bet_field.setDisable(false);

                if (!mainGame.getCurrentPlayer().isHuman()) {
                    aiBetting();
                    return;
                }
            }

        }
        else {
            playerBets[mainGame.getCurrentPlayerNumber() - 1].setText("Defeated");
            PlayerPot[mainGame.getCurrentPlayerNumber() - 1].setText("0");
            mainGame.eliminateCurrentPlayer();
            currentPlayerTurn ();
            updatePlayerDie(mainGame.getCurrentPlayerNumber());
            updateMarkerAndPot(mainGame.getCurrentPlayerNumber());
            if (mainGame.isPlayerWinner()) {
                player_turn.setText(mainGame.getCurrentPlayer().getName() + " WINS!");
                System.out.println("Player " + mainGame.getCurrentPlayerNumber() + " has won the game!");
                Roll.setDisable(true);
                Reroll.setDisable(true);

                Bet_button.setDisable(true);
                Call_button.setDisable(true);
                Fold_button.setDisable(true);
                Bet_field.setDisable(true);
                return;

            }

        }
        if (!mainGame.getCurrentPlayer().isHuman()) {
            roll();
            return;
        }
    }

    public void reroll() {
        System.out.println("Reroll 1: " + rerollSelected[0]);
        System.out.println("Reroll 2: " + rerollSelected[1]);
        System.out.println("Reroll 3: " + rerollSelected[2]);
        System.out.println("Reroll 4: " + rerollSelected[3]);
        System.out.println("Reroll 5: " + rerollSelected[4]);

        mainGame.rerollCurrentPlayer(rerollSelected);
        updatePlayerDie(mainGame.getCurrentPlayerNumber());
        updateMiddleDie(mainGame.getCurrentPlayerNumber());
        mainGame.nextPlayer();
        currentPlayerTurn();
        for (int i = 0; i < 5; i++) {
            if (Dices[i].isSelected()) {
                Dices[i].fire();
            }
        }
        updateMiddleDie(mainGame.getCurrentPlayerNumber());

        if (mainGame.isBettingDone()) {
            ArrayList<Integer> winningNumbers = mainGame.roundWinner();
            if (winningNumbers.size() == 1) {
                victory(winningNumbers.get(0));
                return;
            }
            else {
                //TODO Code tie here
            }

        }
        if (!mainGame.getCurrentPlayer().isHuman()) {
            aiReroll();
        }

    }

    public void bet() {
        try {
            int playerBet = Integer.valueOf(Bet_field.getText());
            if (mainGame.currentPlayerBet(playerBet)) {
                playerBets[mainGame.getCurrentPlayerNumber() - 1].setText(String.valueOf(mainGame.getCurrentPlayerBet()));
                updateMarkerAndPot(mainGame.getCurrentPlayerNumber());
                mainGame.nextPlayer();
                currentPlayerTurn();
                updateMiddleDie(mainGame.getCurrentPlayerNumber());
            }
        }
        catch (NumberFormatException nfe) {
            Bet_field.setText("");
        }

        if (!mainGame.getCurrentPlayer().isHuman()) {
            aiBetting();
            return;
        }


    }

    public void call() {

        mainGame.currentPlayerCall();
        playerBets[mainGame.getCurrentPlayerNumber() - 1].setText("Called");
        updateMarkerAndPot(mainGame.getCurrentPlayerNumber());
        mainGame.nextPlayer();
        currentPlayerTurn();
        updateMiddleDie(mainGame.getCurrentPlayerNumber());

        if (mainGame.isBettingDone()) {
            bettingDone();
            return;
        }
        if (!mainGame.getCurrentPlayer().isHuman()) {
            aiBetting();
            return;
        }



    }

    public void fold() {
        System.out.println("Player Number " + mainGame.getCurrentPlayerNumber() + " folded.");

        playerBets[mainGame.getCurrentPlayerNumber() - 1].setText("Folded");
        mainGame.currentPlayerFold();
        currentPlayerTurn();
        updateMiddleDie(mainGame.getCurrentPlayerNumber());

        System.out.println("It is now player number " + mainGame.getCurrentPlayerNumber() + " turn");
        if (mainGame.isCurrentPlayerLastPlayer()) {
            victory(mainGame.getCurrentPlayerNumber());
            return;
        }
        if (mainGame.isBettingDone()) {
            bettingDone();
            return;
        }
        if (!mainGame.getCurrentPlayer().isHuman()) {
            aiBetting();
            return;
        }

    }

    public void save() {
        try {
            File saveFile = new File("SaveGames\\SaveGame.sav");
            mainGame.saveDataToFile(mainGame.createStorageString(),saveFile);
            System.exit(0);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }


    public void updatePlayerDie(int playerNumber) {

        int[] playerDice = mainGame.getPlayer(playerNumber).getDieValues();

        for (int i = 0; i < 5; i++) {
            allPlayerDice[playerNumber - 1][i].getStyleClass().clear();
            if(playerDice[i] == 1){
                allPlayerDice[playerNumber - 1][i].getStyleClass().add("dice1");
            } else if(playerDice[i] == 2){
                allPlayerDice[playerNumber - 1][i].getStyleClass().add("dice2");
            } else if(playerDice[i] == 3){
                allPlayerDice[playerNumber - 1][i].getStyleClass().add("dice3");
            } else if(playerDice[i] == 4){
                allPlayerDice[playerNumber - 1][i].getStyleClass().add("dice4");
            } else if(playerDice[i] == 5){
                allPlayerDice[playerNumber - 1][i].getStyleClass().add("dice5");
            } else if(playerDice[i] == 6){
                allPlayerDice[playerNumber - 1][i].getStyleClass().add("dice6");
            }
        }



    }

    public void updateMiddleDie(int playerNumber) {

        int[] diceValues = mainGame.getCurrentPlayer().getDieValues();
        for(int i=0; i< diceValues.length; i++) {

            Dices[i].getStyleClass().clear();
            if(diceValues[i] == 1){
                Dices[i].getStyleClass().add("dice1");
            } else if(diceValues[i] == 2){
                Dices[i].getStyleClass().add("dice2");
            } else if(diceValues[i] == 3){
                Dices[i].getStyleClass().add("dice3");
            } else if(diceValues[i] == 4){
                Dices[i].getStyleClass().add("dice4");
            } else if(diceValues[i] == 5){
                Dices[i].getStyleClass().add("dice5");
            } else if(diceValues[i] == 6){
                Dices[i].getStyleClass().add("dice6");
            }
        }


    }

    public void updateMarkerAndPot(int playerNumber) {
        PlayerPot[playerNumber - 1].setText(String.valueOf(mainGame.getPlayer(playerNumber).getMarker()));
        pot_text.setText(String.valueOf(mainGame.getCurrentPot()));
    }

    public void victory(int playerNumber) {
        System.out.println("Player number " + playerNumber + " has won the round and " + mainGame.getCurrentPot() + " chips.");
        mainGame.getPlayer(playerNumber).payMarkerFromPot(mainGame.getCurrentPot());
        updateMarkerAndPot(playerNumber);
        newRound();
        currentPlayerTurn();
        updateMarkerAndPot(mainGame.getCurrentPlayerNumber());

    }

    public void aiBetting() {

        int highestBettingAmount = mainGame.getCurrentPlayer().getMarker() - 1;
        int lowestBettingAmount = mainGame.getHihgestBet() + 1;

        int callingCost = mainGame.getCurrentPlayerCallingCost();

        int[] handStrength = mainGame.getCurrentPlayerHandStrength();

        if (lowestBettingAmount > highestBettingAmount) {
            this.fold();
            return;
        }

        if (callingCost > mainGame.getCurrentPlayer().getMarker()) {
            this.fold();
            return;
        }
        if (callingCost == 0) {
            if (handStrength[0] == 7) {
                Bet_field.setText(String.valueOf(highestBettingAmount));
                this.bet();
                return;
            }
            else if (handStrength[0] > 4) {
                Bet_field.setText(String.valueOf(Math.max(highestBettingAmount/2, lowestBettingAmount)));
                this.bet();
                return;
            }
            else if (handStrength[0] > 0) {
                Bet_field.setText(String.valueOf(Math.min(highestBettingAmount, lowestBettingAmount + 4)));
                this.bet();
                return;
            }
            else {
                this.call();
                return;
            }
        }
        else if (callingCost < highestBettingAmount/4) {
            if (handStrength[0] == 7) {
                Bet_field.setText(String.valueOf(highestBettingAmount));
                this.bet();
                return;
            }
            else if (handStrength[0] > 0) {
                call();
                return;
            }
            else {
                fold();
            }
        }
        else if (callingCost < highestBettingAmount/2) {
            if (handStrength[0] == 7) {
                Bet_field.setText(String.valueOf(highestBettingAmount));
                this.bet();
                return;
            }
            else if (handStrength[0] > 3) {
                call();
                return;
            }
            else {
                fold();
            }
        }
        else {
            if (handStrength[0] == 7) {
                Bet_field.setText(String.valueOf(highestBettingAmount));
                this.bet();
                return;
            }
            else {
                fold();
                return;
            }
        }

    }

    public void aiReroll() {

        boolean[] toReroll = new boolean[5];

        int[] diceValues = mainGame.getCurrentPlayer().getDieValues();

        boolean smallStraight = true;
        boolean largeStraight = true;

        ArrayList<Integer> singeltons = new ArrayList<>();
        ArrayList<Integer> tempSingeltons = new ArrayList<>();
        for (int i = 1; i < 7; i ++) {
            tempSingeltons = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                if (diceValues[j] == i) {
                    tempSingeltons.add(j);
                }
            }
            if (i == 1 && tempSingeltons.size() > 0) {
                largeStraight = false;
            }
            if (i == 6 && tempSingeltons.size() > 0) {
                smallStraight = false;
            }
            if (tempSingeltons.size() == 1) {
                singeltons.add(tempSingeltons.get(0));
            }
        }

        if (singeltons.size() == 5) {
            if (smallStraight || largeStraight) {

            }
            else {
                toReroll[singeltons.get(0)] = true;
            }
        }
        else {
            singeltons.forEach(x -> toReroll[x] = true);
        }


        for (int i = 0; i < 5; i++) {
            if (toReroll[i]) {
                Dices[i].fire();
            }
        }

        this.reroll();


    }

    public void newRound() {
        mainGame.newRound();
        for (int i = 0; i < 4; i++) {
            if (!playerBets[i].getText().equals("Defeated")) {
                playerBets[i].setText("0");
            }
        }
        Reroll.setDisable(true);
        Roll.setDisable((false));

        if (!mainGame.getCurrentPlayer().isHuman()) {
            roll();
            return;
        }

    }

    public void bettingDone() {
        methodName = "bettingDone";
        bettingDone.setValue(true);

        if (!mainGame.getCurrentPlayer().isHuman()) {
            aiReroll();
        }

    }
    public String getMethodName() {
        return methodName;
    }

    public boolean isBettingDone() {
        return bettingDone.get();
    }

    public BooleanProperty bettingDoneProperty() {
        return bettingDone;
    }

    public void setBettingDone(boolean bettingDone) {
        this.bettingDone.set(bettingDone);
    }

    public void toggleButton () {
        Reflection reflection = new Reflection();
        Glow glow = new Glow();
        Bloom bloom = new Bloom();

        //Reflection settings
        reflection.setBottomOpacity(0.1);
        reflection.setTopOpacity(0.5);
        reflection.setTopOffset(0.1);
        reflection.setFraction(0.7);

        //Glow effect level
        glow.setLevel(0.4);

        //Bloom effect level
        bloom.setThreshold(0.1);

        //Action event on selected button
        Die1.setOnAction(event -> {
            if (Die1.isSelected()) {
                Die1.setEffect(reflection);
                reflection.setInput(glow);
                glow.setInput(bloom);
                rerollSelected[0] = true;
            }else {
                Die1.setEffect(null);
                rerollSelected[0] = false;

            }
        });

        Die2.setOnAction(event -> {
            if (Die2.isSelected()) {
                Die2.setEffect(reflection);
                reflection.setInput(glow);
                glow.setInput(bloom);
                rerollSelected[1] = true;
            }else {
                Die2.setEffect(null);
                rerollSelected[1] = false;

            }
        });

        Die3.setOnAction(event -> {
            if (Die3.isSelected()) {
                Die3.setEffect(reflection);
                reflection.setInput(glow);
                glow.setInput(bloom);
                rerollSelected[2] = true;
            }else {
                Die3.setEffect(null);
                rerollSelected[2] = false;

            }
        });

        Die4.setOnAction(event -> {
            if (Die4.isSelected()) {
                Die4.setEffect(reflection);
                reflection.setInput(glow);
                glow.setInput(bloom);
                rerollSelected[3] = true;
            }else {
                Die4.setEffect(null);
                rerollSelected[3] = false;

            }
        });

        Die5.setOnAction(event -> {
            if (Die5.isSelected()) {
                Die5.setEffect(reflection);
                reflection.setInput(glow);
                glow.setInput(bloom);
                rerollSelected[4] = true;
            }else {
                Die5.setEffect(null);
                rerollSelected[4] = false;

            }
        });

    }

    public void currentPlayerTurn () {
        int playerTurn = mainGame.getCurrentPlayerNumber();

        if (playerTurn == 1) {
            player_turn.setText(mainGame.getCurrentPlayer().getName() + " turn");
            player_turn.setTextFill(Color.rgb(214, 0, 0));
            p1Box.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.RED,10,0,0,0));
        }  else if (p1_pot.getText().equals("Defeated") || p1_pot.getText().equals("N/A")) {
            p1Box.setEffect(null);
            p1Box.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.GREY,200,0.4,0,0));
            p1Box.setDisable(true);
        } else {
            p1Box.setEffect(null);
        }

        if (playerTurn == 2) {
            player_turn.setText(mainGame.getCurrentPlayer().getName() + " turn");
            player_turn.setTextFill(Color.rgb(0, 0, 204));
            p2Box.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.BLUE,10,0,0,0));
        }  else if (p2_pot.getText().equals("Defeated") || p2_pot.getText().equals("N/A")) {
            p2Box.setEffect(null);
            p2Box.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.GREY,200,0.4,0,0));
            p2Box.setDisable(true);
        } else {
            p2Box.setEffect(null);
        }

        if (playerTurn == 3) {
            player_turn.setText(mainGame.getCurrentPlayer().getName() + " turn");
            player_turn.setTextFill(Color.rgb(0, 153, 51));
            p3Box.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.GREEN,10,0,0,0));
        }  else if (p3_pot.getText().equals("Defeated") || p3_pot.getText().equals("N/A")) {
            p3Box.setEffect(null);
            p3Box.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.GREY,200,0.4,0,0));
            p3Box.setDisable(true);
        } else {
            p3Box.setEffect(null);
        }

        if (playerTurn == 4) {
            player_turn.setText(mainGame.getCurrentPlayer().getName() + " turn");
            player_turn.setTextFill(Color.rgb(255, 165, 0));
            p4Box.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.ORANGE,10,0,0,0));
        } else if (p4_pot.getText().equals("Defeated") || p4_pot.getText().equals("N/A")) {
            p4Box.setEffect(null);
            p4Box.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.GREY,200,0.4,0,0));
            p4Box.setDisable(true);
        } else {
            p4Box.setEffect(null);
        }
    }

    public void loadGame() {
        try {
            mainGame.loadDataFromFile("SaveFile");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        Player firstRealPlayer = mainGame.getCurrentPlayer();
        for (int i = 1; i < 5; i++) {
        mainGame.nextPlayer();
            if (mainGame.getCurrentPlayerNumber() == 1) {
                startPosition = 1;
                player1.setText(mainGame.getCurrentPlayer().getName());
                turn = true;
                p1_mark.setText(String.valueOf(mainGame.getCurrentPlayer().getMarker()));
                player_turn.setText(mainGame.getCurrentPlayer().getName() + " turn");
            }

            if (mainGame.getCurrentPlayerNumber() == 2) {
                startPosition = 2;
                player2.setText(mainGame.getCurrentPlayer().getName());
                p2_mark.setText(String.valueOf(mainGame.getCurrentPlayer().getMarker()));
                if (turn == false) {
                    turn = true;
                    player_turn.setText(mainGame.getCurrentPlayer().getName() + " turn");
                }
            }


            if (mainGame.getCurrentPlayerNumber() == 3) {
                player3.setText(mainGame.getCurrentPlayer().getName());
                p3_mark.setText(String.valueOf(mainGame.getCurrentPlayer().getMarker()));
                if (turn == false) {
                    turn = true;
                    player_turn.setText(mainGame.getCurrentPlayer().getName() + " turn");
                }
            }


            if (mainGame.getCurrentPlayerNumber() == 4) {
                player4.setText(mainGame.getCurrentPlayer().getName());
                p4_mark.setText(String.valueOf(mainGame.getCurrentPlayer().getMarker()));
                if (turn == false) {
                    turn = true;
                    player_turn.setText(mainGame.getCurrentPlayer().getName() + " turn");
                }
            }

        }

        currentPlayerTurn();



    }



}
