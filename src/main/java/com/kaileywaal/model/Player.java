package com.kaileywaal.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private List<Card> hand = new ArrayList<Card>();
    private boolean isComputer;
    private String name;

    public Player(boolean isComputer, String name) {
        this.isComputer = isComputer;
        this.name = name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public boolean isComputer() {
        return isComputer;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void dealCard(Card card) {
        hand.add(card);
    }

    public List<Card> getValidPlayableCards(List<Card> cardsPlayed, String trump) {
        // Returns list of cards that are allowed to be played based on what has already been played
        // Make sure to include left hand suit as trump suit, not as its actual suit





        return null;
    }

}
