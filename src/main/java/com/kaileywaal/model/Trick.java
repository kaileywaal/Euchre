package com.kaileywaal.model;

import java.util.HashMap;
import java.util.Map;

public class Trick {

    private Map<Card, Player> cardsInPlay;
    private Card leadingCard;
    private String leadingSuit; // string that holds the value of the suit that led
    private final String TRUMP;

    public Trick(String trump) {
        this.cardsInPlay = new HashMap<Card, Player>();
        this.TRUMP = trump;
    }

    public Map<Card, Player> getCardsInPlay() {
        return cardsInPlay;
    }

    public String getLeadingSuit() {
        return leadingSuit;
    }

    public Card getLeadingCard() {
        return leadingCard;
    }

    public void playCard(Player player, Card cardToPlay) {
        if(cardsInPlay.isEmpty()) {
            leadingCard = cardToPlay;
            leadingSuit = cardToPlay.getSuit();
        }
        cardsInPlay.put(cardToPlay, player);
    }

    public Player determineTrickWinner() {
        // Make sure all 4 players have played their cards
        if(cardsInPlay.size() != 4) {
            try {
                throw new Exception("Cannot determine winner until all players have played!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Create hashmap to store players and their rank
        Map<Player, Integer> playerRanks = new HashMap<>();

        for (Card card : cardsInPlay.keySet()) {
            int cardRank = card.getCardScore(leadingSuit, TRUMP);
            Player player = cardsInPlay.get(card);
            playerRanks.put(player, cardRank);
        }

        // Find card with lowest ranking (lower is better)
        int lowestRank = 13;
        Player trickWinner = null;
        for (Player player : playerRanks.keySet()) {
            int cardRank = playerRanks.get(player);
            if (cardRank < lowestRank) {
                lowestRank = cardRank;
                trickWinner = player;
            }
        }
        return trickWinner;
    }

    public Card findCardPlayedByPlayer(Player player) {
        // Return player who scored the lowest ranking
        Card cardPlayed = null;
        for (Map.Entry<Card, Player> entry : cardsInPlay.entrySet()) {
            if (entry.getValue().equals(player)) {
                cardPlayed = entry.getKey();
            }
        }
        return cardPlayed;
    }
}
