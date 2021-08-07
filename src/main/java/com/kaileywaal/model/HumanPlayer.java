package com.kaileywaal.model;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(false, name);
    }


    @Override
    public boolean callTopCardAsTrump(Card topCard) {
        return false;
    }
}
