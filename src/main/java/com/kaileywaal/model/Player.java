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

    public void removeCardFromHand(Card cardToRemove) {
        hand.remove(cardToRemove);
    }

    public List<Card> getValidPlayableCards(List<Card> cardsPlayed, String trump) {
        // If nobody else has played a card, any card goes
        if (cardsPlayed.isEmpty()) {
            return getHand();
        } else {
            String leadingSuit = cardsPlayed.get(0).getSuit();
            return getCardsThatFollowSuit(leadingSuit, trump);
        }
    }

    private List<Card> getCardsThatFollowSuit(String leadingSuit, String trump){
        List<Card> cardsThatFollowSuit = new ArrayList<Card>();
        String leftHandSuit = Card.getLeftHandSuit(trump);

        for (Card card : hand) {
            String suit = card.getSuit();
            String rank = card.getRank();

            // Off suit Jack should be treated as the trump suit
            if(rank.equals("Jack") && suit.equals(leftHandSuit)) {
                suit = trump;
            }

            if (suit.equals(leadingSuit)) {
                cardsThatFollowSuit.add(card);
            }
        }
        return cardsThatFollowSuit.isEmpty() ? hand: cardsThatFollowSuit;
    }

    public void pickUpTopCard(Card topCard) {
        hand.add(topCard);
        if(isComputer) {
            // TODO: improve logic to have computer make informed decision about which card to put down
            removeCardFromHand(hand.get(0));
        }
    }

    public abstract boolean callTopCardAsTrump(Card topCard);
}
