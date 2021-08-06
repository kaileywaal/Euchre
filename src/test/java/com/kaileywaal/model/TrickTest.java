package com.kaileywaal.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrickTest {
    private static final Player PLAYER1 = new HumanPlayer("P1");
    private static final Player PLAYER2 = new HumanPlayer("P2");
    private static final Player PLAYER3 = new ComputerPlayer("P3");
    private static final Player PLAYER4 = new HumanPlayer("P4");

    @Test
    public void determineTrickWinner_returns_correct_winner_when_jack_of_trump_is_played() {
        Trick trick = new Trick("Spades");
        trick.playCard(PLAYER1, new Card("Spades", "Jack"));
        trick.playCard(PLAYER2, new Card("Spades", "Ace"));
        trick.playCard(PLAYER3, new Card("Clubs", "Jack"));
        trick.playCard(PLAYER4, new Card("Spades", "9"));

        Player actual = trick.determineTrickWinner();

        Assert.assertEquals(PLAYER1, actual);
    }

    @Test
    public void determineTricKWinner_returns_correct_winner_when_left_hand_jack_is_best_card() {
        Trick trick = new Trick("Spades");
        trick.playCard(PLAYER1, new Card("Clubs", "Jack"));
        trick.playCard(PLAYER2, new Card("Spades", "Ace"));
        trick.playCard(PLAYER3, new Card("Diamonds", "Jack"));
        trick.playCard(PLAYER4, new Card("Spades", "9"));

        Player actual = trick.determineTrickWinner();

        Assert.assertEquals(PLAYER1, actual);
    }

    @Test
    public void determineTrickWinner_returns_correct_winner_when_no_trump_cards_are_played() {
        Trick trick = new Trick("Hearts");
        trick.playCard(PLAYER1, new Card("Spades", "9"));
        trick.playCard(PLAYER2, new Card("Clubs", "Jack"));
        trick.playCard(PLAYER3, new Card("Spades", "Ace"));
        trick.playCard(PLAYER4, new Card("Diamonds", "9"));


        Player actual = trick.determineTrickWinner();

        Assert.assertEquals(PLAYER3, actual);
    }

    @Test
    public void determineTrickWinner_returns_correct_winner_when_four_jacks_are_played() {
        Trick trick = new Trick("Hearts");
        trick.playCard(PLAYER1, new Card("Clubs", "Jack"));
        trick.playCard(PLAYER2, new Card("Hearts", "Jack"));
        trick.playCard(PLAYER3, new Card("Spades", "Jack"));
        trick.playCard(PLAYER4, new Card("Diamonds", "Jack"));


        Player actual = trick.determineTrickWinner();

        Assert.assertEquals(PLAYER2, actual);
    }

}