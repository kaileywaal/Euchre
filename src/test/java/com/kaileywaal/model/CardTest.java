package com.kaileywaal.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {

    @Test
    public void getCardScore_returns_correct_score_for_right_jack() {
        Card card = new Card("Spades", "Jack");
        int actual = card.getCardScore("Diamonds", "Spades");
        int expected = 1;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCardScore_returns_correct_score_for_left_jack() {
        Card card = new Card("Spades", "Jack");
        int actual = card.getCardScore("Diamonds", "Clubs");
        int expected = 2;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCardScore_returns_correct_score_for_trump_Ace() {
        Card card = new Card("Spades", "Ace");
        int actual = card.getCardScore("Clubs", "Spades");
        int expected = 3;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getCardScore_returns_correct_score_for_leading_Ace() {
        Card card = new Card("Spades", "Ace");
        int actual = card.getCardScore("Spades", "Clubs");
        int expected = 8;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getLeftHandSuit_should_return_Diamonds_when_trump_is_Hearts() {
        String actual = Card.getLeftHandSuit("Hearts");
        Assert.assertEquals("Diamonds", actual);
    }

    @Test
    public void getLeftHandSuit_should_return_Hearts_when_trump_is_Diamonds() {
        String actual = Card.getLeftHandSuit("Diamonds");
        Assert.assertEquals("Hearts", actual);
    }

    @Test
    public void getLeftHandSuit_should_return_Clubs_when_trump_is_Spades() {
        String actual = Card.getLeftHandSuit("Spades");
        Assert.assertEquals("Clubs", actual);
    }

    @Test
    public void getLeftHandSuit_should_return_Spades_when_trump_is_Clubs() {
        String actual = Card.getLeftHandSuit("Clubs");
        Assert.assertEquals("Spades", actual);
    }
}