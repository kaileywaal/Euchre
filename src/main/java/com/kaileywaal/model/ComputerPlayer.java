package com.kaileywaal.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComputerPlayer extends Player{

    public ComputerPlayer(String name) {
        super(true, name);
    }

    //TODO: improve computer logic
    // TODO: allow computer to see Jacks as off suit
    public boolean callTopCardAsTrump(Card topCard) {
        List<Card> hand = getHand();
        String suitToCall = topCard.getSuit();
        int numberOfSuit = 0;
        for (Card card : hand) {
            if (card.getSuit().equals(suitToCall)) {
                numberOfSuit++;
            }
        }
        return numberOfSuit >= 3;
    }

    public String callTrumpOrPass(String passedSuit, boolean isStuck) {
        List<Card> cards = getHand();
        Map<String, Integer> suitsMap = new HashMap<>();
        // Initialize map with all suits
        String[] suits = Deck.getSuits();
        for(String suit: suits) {
            suitsMap.put(suit, 0);
        }
        // Count up how many cards of each suit are in hand
        for(Card card: cards) {
            String suit = card.getSuit();
            int previousCount = suitsMap.get(suit);
            suitsMap.put(suit, previousCount + 1);
        }
        String highestSuit = determineHighestSuit(suitsMap, passedSuit);

        if (isStuck)  { return highestSuit; }
        else if (suitsMap.get(highestSuit) >= 3) { return highestSuit; }
        return null;
    }

    private String determineHighestSuit(Map<String, Integer> suitsMap, String passedSuit) {
        int highestSuitCount = 0;
        String highestSuit = "";
        for (Map.Entry<String, Integer> suit : suitsMap.entrySet()) {
            String cardSuit = suit.getKey();
            int numberOfCards = suit.getValue();

            // Make sure that the highest suit is not the suit that was passed on
            if (!cardSuit.equals(passedSuit) && numberOfCards > highestSuitCount) {
                highestSuit = cardSuit;
                highestSuitCount = Math.max(numberOfCards, highestSuitCount);
            }
        }
        return highestSuit;
    }

    //TODO: Improve computer logic for card choice
    public Card playCard(List<Card> cardsPlayed, String trump) {
        List<Card> validCardOptions = getValidPlayableCards(cardsPlayed, trump);
        Card cardToPlay = validCardOptions.get(0);
        getHand().remove(cardToPlay);
        return cardToPlay;
    }

}
