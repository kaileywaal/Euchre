package com.kaileywaal.model;

public class Team {
    private Player[] players;
    private int score;
    private String name;

    public Team(Player[] players, String name) {
        this.players = players;
        this.score = 0;
        this.name = name;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getScore() {
        return score;
    }

    public void addToScore(int scoreToAdd) {
        this.score += scoreToAdd;
    }

    public String toString() {
        return name + " players: " + players[0] + ", " + players[1];
    }

}
