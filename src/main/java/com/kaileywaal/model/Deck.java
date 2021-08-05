package com.kaileywaal.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<Card>();
    private final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private final String[] RANKS = {"9", "10", "Jack", "Queen", "King", "Ace"};

    public Deck() {
        for (int i = 0; i < SUITS.length; i++) {
            for(int j = 0; j < RANKS.length; j++) {
                cards.add(new Card(SUITS[i], RANKS[j]));
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public String[] getSUITS() {
        return SUITS;
    }

    public String[] getRANKS() {
        return RANKS;
    }

    public void removeCard(Card cardToRemove) {
        cards.remove(cardToRemove);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal(Player[] playersToDealTo) {
        shuffle();
        // Deal 5 cards to each player in the game
        for (int i = 0; i < 5; i++) {
            for (Player player : playersToDealTo) {
                player.dealCard(cards.get(0));
                removeCard(cards.get(0));
            }
        }
        // returns top card
        return (cards.get(0));
    }


}
