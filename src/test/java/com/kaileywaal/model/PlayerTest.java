package com.kaileywaal.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

// FIXME : fix tests

public class PlayerTest {
    private final Player PLAYER = new HumanPlayer("Test");

    private final Card JACKOFSPADES = new Card("Spades", "Jack");
    private final Card JACKOFDIAMONDS = new Card("Diamonds", "Jack");
    private final Card NINEOFHEARTS = new Card("Hearts", "9");
    private final Card QUEENOFHEARTS = new Card("Hearts", "Queen");
    private final Card ACEOFHEARTS = new Card("Hearts", "Ace");

    @Before
    public void setup() {
        PLAYER.dealCard(JACKOFSPADES);
        PLAYER.dealCard(JACKOFDIAMONDS);
        PLAYER.dealCard(ACEOFHEARTS);
        PLAYER.dealCard(NINEOFHEARTS);
        PLAYER.dealCard(QUEENOFHEARTS);
    }

    @Test
    public void getValidPlayableCards_should_return_full_hand_when_nobody_else_has_played() {
        int actual =  PLAYER.getValidPlayableCards(null, "Spades").size();
        int expected = 5;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getValidPlayableCards_should_return_left_hand_jack_when_trump_suit_leads() {
        Card nineOfHearts = new Card("Hearts", "9");

        List<Card> playableCards = PLAYER.getValidPlayableCards(nineOfHearts, "Hearts");
        boolean actual = playableCards.contains(JACKOFDIAMONDS);

        Assert.assertTrue(actual);
    }

    @Test
    public void getValidPlayableCards_should_not_return_left_hand_jack_with_its_typical_suit() {
        Card nineOfDiamonds = new Card("Diamonds", "9");

        List<Card> playableCards = PLAYER.getValidPlayableCards(nineOfDiamonds, "Hearts");
        boolean playableCardsReturnsLeftHandWithTypicalSuit = false;

        for(Card card: playableCards) {
            if(card.getSuit().equals("Diamonds") && card.getRank().equals("9")) {
                playableCardsReturnsLeftHandWithTypicalSuit = true;
            }
        }

        Assert.assertFalse(playableCardsReturnsLeftHandWithTypicalSuit);
    }

    @Test
    public void getValidPlayableCards_should_only_return_cards_that_follow_suit() {
        Card nineOfSpades = new Card("Spades", "9");

        List<Card> playableCards = PLAYER.getValidPlayableCards(nineOfSpades, "Hearts");
        boolean cardFoundThatDoesNotFollowSuit = false;

        for(Card card: playableCards) {
            if (!card.getSuit().equals("Spades")) {
                cardFoundThatDoesNotFollowSuit = true;
                break;
            }
        }


        Assert.assertEquals(1, playableCards.size());
        Assert.assertFalse(cardFoundThatDoesNotFollowSuit);
    }

    @Test
    public void getValidPlayableCards_returns_trump_cards_when_off_suit_jack_leads() {
        Card jackOfHearts = new Card("Hearts", "Jack");

        List<Card> playableCards = PLAYER.getValidPlayableCards(jackOfHearts, "Diamonds");

        boolean cardFoundThatDoesNotFollowSuit = false;
        for(Card card: playableCards) {
            if (!card.getSuit().equals("Diamonds")) {
                cardFoundThatDoesNotFollowSuit = true;
                break;
            }
        }

        Assert.assertFalse(cardFoundThatDoesNotFollowSuit);
        Assert.assertEquals(1, playableCards.size());
    }
}