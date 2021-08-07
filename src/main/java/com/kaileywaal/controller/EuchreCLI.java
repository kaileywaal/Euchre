package com.kaileywaal.controller;

import com.kaileywaal.GameView;
import com.kaileywaal.model.*;

public class EuchreCLI {
    private GameView view;
    private Game game;
    private Team[] teams;
    private Player[] players;

    public EuchreCLI() {
        view = new GameView(System.in, System.out);
    }

    public void play() {
        // ALL CODE TO RUN PROJECT GOES HERE
        view.displayMessage("Welcome to Euchre! \n");
        int numberOfHumanPlayers = getNumberOfHumanPlayers();
        String[] namesOfPlayers = getNamesOfPlayers(numberOfHumanPlayers);
        game = new Game(numberOfHumanPlayers, namesOfPlayers);
        teams = game.getTeams();
        players = game.getPlayers();

        while(game.shouldContinue()) {
            playHand();
        }
    }

    private int getNumberOfHumanPlayers() {
        view.displayMessage("How many players are playing?");
        Integer[] choices = {1, 2, 3, 4};
        return (int) view.getChoiceFromOptions(choices);
    }

    private String[] getNamesOfPlayers(int numberOfHumanPlayers) {
        String[] playerNames = new String[numberOfHumanPlayers];

        for(int i = 0; i < numberOfHumanPlayers; i++) {
            String message = "What is the name of player " + (i + 1);
            playerNames[i] = view.getUserInput(message);
        }
        return playerNames;
    }

    private void playHand() {
        Hand hand = new Hand(teams, players);
        // ask each player if they'd like to pick up top card
        boolean called = callTopCardAsTrump(hand);
        // if they all pass, ask each player if they want to call trump
        if(!called) {
            // CODE TO ASK EACH PLAYER IF THEY WANT TO CALL TRUMP
        }

        // once trump has been called, play 5 tricks

        // update score after each trick

        // determine winner after all 5 tricks are played

    }

    private boolean callTopCardAsTrump(Hand hand) {

        Card topCard = hand.getTopCard();
        Player dealer = Hand.getDealer();
        String[] options = {"Yes", "No"};

        view.displayMessage("\n" + dealer.getName() + " dealt and flipped over a " + topCard + ".");

        for(int i = 0; i < 4; i++) {
            Player currentPlayer = hand.getCurrentPlayer();
            if(currentPlayer.isComputer()) {
                if(currentPlayer.callTopCardAsTrump(topCard)) {
                    dealer.pickUpTopCard(topCard);
                    hand.callTrump(currentPlayer, topCard.getSuit());
                    view.displayTopCardCalledTrump(currentPlayer, topCard, true);
                    hand.moveToNextPlayer();
                    return true;
                }
            } else {
                view.displayPlayerCards(currentPlayer);
                view.displayMessage("Would you like " + dealer.getName() + " to pick up the " + topCard + "?");
                String choice = (String) view.getChoiceFromOptions(options);

                if(choice.equals("Yes")) {
                    dealer.pickUpTopCard(topCard);
                    hand.callTrump(currentPlayer, topCard.getSuit());
                    view.displayTopCardCalledTrump(currentPlayer, topCard, true);
                    hand.moveToNextPlayer();
                    return true;
                }
            }
            view.displayTopCardCalledTrump(currentPlayer, topCard, false);
            hand.moveToNextPlayer();
        }
        // If code gets to this point, nobody has called it
        return false;
    }







    public static void main(String[] args) {
        EuchreCLI euchreCLI = new EuchreCLI();
        euchreCLI.play();
    }

}
