package com.kaileywaal.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComputerPlayerTest {
    private final ComputerPlayer PLAYER = new ComputerPlayer("Test");

    private final Card NINEOFDIAMONDS = new Card("Diamonds", "9");
    private final Card JACKOFDIAMONDS = new Card("Diamonds", "Jack");
    private final Card NINEOFHEARTS = new Card("Hearts", "9");
    private final Card QUEENOFHEARTS = new Card("Hearts", "Queen");
    private final Card ACEOFHEARTS = new Card("Hearts", "Ace");

    @Before
    public void setup() {
        PLAYER.dealCard(NINEOFDIAMONDS);
        PLAYER.dealCard(JACKOFDIAMONDS);
        PLAYER.dealCard(ACEOFHEARTS);
        PLAYER.dealCard(NINEOFHEARTS);
        PLAYER.dealCard(QUEENOFHEARTS);
    }

    @Test
    public void callTopCardAsTrump_returns_true_when_computer_has_3_of_same_suit() {
        Card card = new Card("Hearts", "10");

        boolean actual = PLAYER.callTopCardAsTrump(card);

        Assert.assertTrue(actual);
    }

    @Test
    public void callTopCardAsTrump_returns_false_when_computer_has_2_cards_that_follow_suit() {
        Card card = new Card("Diamonds", "10");

        boolean actual = PLAYER.callTopCardAsTrump(card);

        Assert.assertFalse(actual);
    }

    @Test
    public void callTrumpOrPass_returns_Hearts_when_computer_has_three_hearts() {
        String actual = PLAYER.callTrumpOrPass("Diamonds", false);

        Assert.assertEquals("Hearts", actual);
    }

    @Test
    public void callTrumpOrPass_returns_Diamonds_when_hearts_was_passed_and_computer_is_stuck() {
        String actual = PLAYER.callTrumpOrPass("Hearts", true);

        Assert.assertEquals("Diamonds", actual);
    }

    @Test
    public void callTrumpOrPass_returns_null_when_computer_has_only_2_of_each_suit_and_is_not_stuck() {
        PLAYER.removeCardFromHand(NINEOFHEARTS);
        PLAYER.dealCard(new Card("Spades", "Jack"));

        String actual = PLAYER.callTrumpOrPass("Clubs", false);

        Assert.assertNull(actual);
    }
}