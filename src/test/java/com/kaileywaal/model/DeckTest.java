package com.kaileywaal.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void deal_method_deals_each_player_5_cards() {
        Deck deck = new Deck();
        Player player1 = new ComputerPlayer("C1");
        Player player2 = new ComputerPlayer("C2");
        Player player3 = new ComputerPlayer("C3");
        Player player4 = new HumanPlayer("H1");
        Player[] players = {player1, player2, player3, player4};
        deck.deal(players);

        int p1HandSize = player1.getHand().size();
        int p2HandSize = player2.getHand().size();
        int p3HandSize = player3.getHand().size();
        int p4HandSize = player4.getHand().size();

        int expected = 5;

        Assert.assertEquals(expected, p1HandSize);
        Assert.assertEquals(expected, p2HandSize);
        Assert.assertEquals(expected, p3HandSize);
        Assert.assertEquals(expected, p4HandSize);
    }

    @Test
    public void deal_method_removes_dealt_cards_from_deck() {
        Deck deck = new Deck();
        Player player1 = new ComputerPlayer("C1");
        Player player2 = new ComputerPlayer("C2");
        Player player3 = new ComputerPlayer("C3");
        Player player4 = new HumanPlayer("H1");
        Player[] players = {player1, player2, player3, player4};
        deck.deal(players);

        // Only 4 cards should remain in deck
        int actual = deck.getCards().size();
        int expected = 4;

        Assert.assertEquals(expected, actual);
    }
}