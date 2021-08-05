package com.kaileywaal.model;

public class Card {
    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getCardScore(String leadingSuit, String trump) {
        // returns ranking for card based on leading suit and trump -- lower is better!
        String rightHandSuit = getLeftHandSuit(trump);
        String suit = getSuit();
        String rank = getRank();

        if(suit.equals(trump) && rank.equals("Jack")) {
            return 1;
        }
        else if (suit.equals(rightHandSuit) && rank.equals("Jack")) {
            return 2;
        }
        else if (suit.equals(trump)) {
            switch(rank) {
                case "Ace":
                    return 3;
                case "King":
                    return 4;
                case "Queen":
                    return 5;
                case "10":
                    return 6;
                case "9":
                    return 7;
            }
        }
        else if (suit.equals(leadingSuit)) {
            switch(rank) {
                case "Ace":
                    return 8;
                case "King":
                    return 9;
                case "Queen":
                    return 10;
                case "10":
                    return 11;
                case "9":
                    return 12;
            }
        }
        return 13;
    }

    public static String getLeftHandSuit(String trump) {
        String rightHandSuit = "";
        switch(trump) {
            case "Hearts":
                rightHandSuit = "Diamonds";
                break;
            case "Diamonds":
                rightHandSuit = "Hearts";
                break;
            case "Spades":
                rightHandSuit = "Clubs";
                break;
            case "Clubs":
                rightHandSuit = "Spades";
                break;
        }
        return rightHandSuit;
    }


    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
