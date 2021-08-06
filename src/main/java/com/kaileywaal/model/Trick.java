package com.kaileywaal.model;

import java.util.HashMap;
import java.util.Map;

public class Trick {

    private Map<Card, Player> cardsInPlay;
    private String leadingSuit; // string that holds the value of the suit that led
    private String trump;

    public Trick(String trump) {
        this.cardsInPlay = new HashMap<Card, Player>();
        this.trump = trump;
    }

    public void playCard(Player player, Card cardToPlay) {
        if(cardsInPlay.isEmpty()) {
            leadingSuit = cardToPlay.getSuit();
        }

        cardsInPlay.put(cardToPlay, player);
    }

    public Player determineTrickWinner() {
        // Create hashmap to store players and their rank
        Map<Player, Integer> playerRanks = new HashMap<>();

        for (Card card : cardsInPlay.keySet()) {
            int cardRank = card.getCardScore(leadingSuit, trump);
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
        Card winningCard = null;
        for (Map.Entry<Card, Player> entry : cardsInPlay.entrySet()) {
            if (entry.getValue().equals(player)) {
                winningCard = entry.getKey();
            }
        }
        return winningCard;
    }
}
