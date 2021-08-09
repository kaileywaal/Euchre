package com.kaileywaal.model;

import java.util.List;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(false, name);
    }


    @Override
    public boolean callTopCardAsTrump(Card topCard) {
        return false;
    }

    @Override
    public String callTrumpOrPass(String passedSuit, boolean isStuck) {
        return null;
    }

    @Override
    public Card playCard(Card leadingCard, String trump) {
        return null;
    }

}
