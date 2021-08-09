package com.kaileywaal.controller;

import com.kaileywaal.GameView;
import com.kaileywaal.model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class EuchreCLI {
    private GameView view;
    private Game game;
    private Team[] teams;
    private Player[] players;

    public EuchreCLI() {
        view = new GameView(System.in, System.out);
    }

    public void play() {
        boolean continuePlaying = true;
        // ALL CODE TO RUN PROJECT GOES HERE
        view.displayMessage("Welcome to Euchre! \n");

        while(continuePlaying) {
            int numberOfHumanPlayers = getNumberOfHumanPlayers();
            String[] namesOfPlayers = getNamesOfPlayers(numberOfHumanPlayers);
            game = new Game(numberOfHumanPlayers, namesOfPlayers);
            teams = game.getTeams();
            players = game.getPlayers();
            view.displayTeams(teams);

            while (game.shouldContinue()) {
                playHand();
            }

            Team gameWinner = game.getGameWinner();
            view.displayGameWinner(gameWinner, teams);
            view.displayMessage("Thank you for playing! Would you like to play again?");
            String continueChoice = (String) view.getChoiceFromOptions(new String[] {"Yes", "No"});
            if(continueChoice.equals("No")) {
                continuePlaying = false;
            }
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
            callTrump(hand);
        }
        // once trump has been called, play 5 tricks
        for(int i = 0; i < 5; i++) {
            playTrick(hand, i);
        }
        Team winner = hand.determineWinner();
        int points = hand.addPointsToHandWinner(winner);
        int tricksWon = hand.getTricksWonByTeam(winner);
        Player caller = hand.getCaller();
        view.displayHandWinner(winner, points, tricksWon, caller);
        view.displayScoreUpdate(teams);
    }

    private boolean callTopCardAsTrump(Hand hand) {
        // Returns true if someone calls it, otherwise return false

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
                view.displayMessage("\nYour turn, " + currentPlayer.getName() + "!");
                view.displayPlayerCards(currentPlayer);
                view.displayMessage("Would you like " + dealer.getName() + " to pick up the " + topCard + "?");
                view.displayTeammate(currentPlayer, teams);
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
        // once trump is called, set current player to the player after the dealer
        hand.setCurrentPlayer(dealer);
        hand.moveToNextPlayer();
        // If code gets to this point, nobody has called it
        return false;
    }

    private void callTrump(Hand hand) {
        Player dealer = Hand.getDealer();
        String passedSuit = hand.getTopCard().getSuit();
        String trump;
        view.displayMessage("\nEveryone passed on the " + hand.getTopCard() + ".\n");


        for(int i = 0; i < 4; i++) {
            Player currentPlayer = hand.getCurrentPlayer();
            boolean isStuck = currentPlayer.equals(dealer);
            if(currentPlayer.isComputer()) {
                trump = currentPlayer.callTrumpOrPass(passedSuit, isStuck);
                view.displayPlayerCalledTrump(currentPlayer, trump);
                if(trump != null) {
                    hand.callTrump(currentPlayer, trump);
                    hand.moveToNextPlayer();
                    break;
                }
            }
            else {
                view.displayMessage("\nYour turn, " + currentPlayer.getName() + "!");
                if(isStuck) {
                    view.displayMessage("You are stuck, so you must call trump");
                    view.displayMessage("You flipped over a " + passedSuit + ", so " + passedSuit + "cannot be trump");
                } else {
                    view.displayMessage(dealer.getName() + " flipped over a " + passedSuit + ", so " + passedSuit + " cannot be trump");
                }
                view.displayPlayerCards(currentPlayer);
                view.displayMessage("Which suit would you like to be trump?");
                String response = (String) view.getChoiceFromOptions(getSuitOptions(passedSuit, isStuck));
                if(response.equals("Pass, please!")) {
                    response = null;
                }
                view.displayPlayerCalledTrump(currentPlayer, response);
                if(response != null) {
                    hand.callTrump(currentPlayer, response);
                    hand.moveToNextPlayer();
                    break;
                }
            }
            hand.moveToNextPlayer();
        }
        // once trump is called, set current player to the player after the dealer
        hand.setCurrentPlayer(dealer);
        hand.moveToNextPlayer();
    }

    private String[] getSuitOptions(String passedSuit, boolean isStuck) {
        String[] suits = Deck.getSuits();
        List<String> validSuits = new ArrayList<>();
        for(String suit: suits) {
            if(!suit.equals(passedSuit)) {
                validSuits.add(suit);
            }
        }

        // if the player is not the dealer, they have the option to pass
        if(!isStuck) {
            validSuits.add("Pass, please!");
        }

        return validSuits.toArray(new String[validSuits.size()]);
    }

    private void playTrick(Hand hand, int trickNumber) {
        Trick trick = new Trick(hand.getTrump());
        // loop through 4 players
        for(int i = 0; i < 4; i++) {
            Player currentPlayer = hand.getCurrentPlayer();
            List<Card> cardsInPlay = new ArrayList<>(trick.getCardsInPlay().keySet());
            if(currentPlayer.isComputer()) {
                Card cardPlayed = currentPlayer.playCard(trick.getLeadingCard(), hand.getTrump());
                trick.playCard(currentPlayer, cardPlayed);
                view.displayCardPlayedByPlayer(currentPlayer, trick.findCardPlayedByPlayer(currentPlayer));
            }
            else {
                view.displayMessage("\nYour turn, " + currentPlayer.getName());
                if(!cardsInPlay.isEmpty()) {
                    String leadingSuit = trick.getLeadingSuit();
                    String leadingRank = trick.getLeadingCard().getRank();
                    // If leading card is the left hand Jack, display trump as the leading suit
                    if(leadingSuit.equals(Card.getLeftHandSuit(hand.getTrump())) && leadingRank.equals("Jack")) {
                        leadingSuit = hand.getTrump();
                    }
                    view.displayMessage(leadingSuit + " led, so if you have any " + leadingSuit + " you must play one.");
                }
                view.displayAllCardsPlayed(cardsInPlay);
                view.displayTeammate(currentPlayer, teams);
                view.displayTrump(hand.getTrump());
                view.displayMessage("What would you like to play?");
                List<Card> validPlayableCards = currentPlayer.getValidPlayableCards(trick.getLeadingCard(), hand.getTrump());
                Card choice = (Card) view.getChoiceFromOptions(validPlayableCards.toArray(new Object[validPlayableCards.size()]));
                trick.playCard(currentPlayer, choice);
                currentPlayer.removeCardFromHand(choice);
                view.displayCardPlayedByPlayer(currentPlayer, choice);
            }
            hand.moveToNextPlayer();
        }
        Player winner = trick.determineTrickWinner();
        hand.addPointToTeamThatWonTrick(winner);
        // Display number of tricks won except for on last trick in hand
        if(trickNumber != 4) {
            view.displayTrickWinner(winner, trick.findCardPlayedByPlayer(winner), hand.getTricksWonByTeam());
        }
        hand.setCurrentPlayer(winner);
    }


    public static void main(String[] args) {
        EuchreCLI euchreCLI = new EuchreCLI();
        euchreCLI.play();
    }

}
