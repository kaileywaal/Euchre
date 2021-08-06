package com.kaileywaal.model;

import java.util.*;

public class Hand {
    private Map<Team, Integer> tricksWonByTeam = new HashMap<>(); // keeps track of how many tricks each team has won
    private Team[] teams;
    private Player[] players;
    private Player caller; // keeps track of the player who called the current trump card
    private final Card TOPCARD;
    private String trump;


    public Hand(Team[] teams) {
        this.teams = teams;
        Deck deck = new Deck();

        List<Player> playerList = new ArrayList<>();
        for(Team team : teams) {
            tricksWonByTeam.put(team, 0);
            Player[] playersInTeam = team.getPlayers();
            Collections.addAll(playerList, playersInTeam);
        }
        this.players = playerList.toArray(new Player[4]);
        this.TOPCARD = deck.deal(this.players);
    }

    public String getTrump() {
        return trump;
    }

    public void setTrump(Player caller, String trump) {
        this.caller = caller;
        this.trump = trump;
    }

    public Card getTopCard() {
        return TOPCARD;
    }

    public Map<Team, Integer> addPointToTeamThatWonTrick(Player winningPlayer) {
        // Determine which team the winning player was on
        Team winningTeam = null;
        for(Team team: teams) {
            Player[] teamPlayers = team.getPlayers();
            for(Player player: teamPlayers) {
                if(player.equals(winningPlayer)) {
                    winningTeam = team;
                    break;
                }
            }
        }
        // adds one point to winner of trick, returns the new scores of each team
        int tricksWon = tricksWonByTeam.get(winningTeam);
        tricksWonByTeam.put(winningTeam, tricksWon + 1);

        return tricksWonByTeam;
    }

    public Team determineWinner() {
        makeSureAllTricksWerePlayed();
        Team winner = tricksWonByTeam.get(teams[0]) > tricksWonByTeam.get(teams[1]) ? teams[0] : teams[1];
        addPointsToHandWinner(winner);
        return winner;
    }

    private void addPointsToHandWinner(Team winningTeam) {
        int tricksWonByWinningTeam = tricksWonByTeam.get(winningTeam);
        boolean winningTeamCalledTrump = false;

        for(Player player: winningTeam.getPlayers()) {
            if (player.equals(caller)) {
                winningTeamCalledTrump = true;
                break;
            }
        }
        // if the winning team didn't call it, OR if the winning team called it an won all 5 tricks, they get 2 points
        if(!winningTeamCalledTrump || tricksWonByWinningTeam == 5) {
            winningTeam.addToScore(2);
        }
        else {
            winningTeam.addToScore(1);
        }
    }

    private void makeSureAllTricksWerePlayed() {
        int tricksPlayed = 0;
        for(Team team : tricksWonByTeam.keySet()) {
            tricksPlayed += tricksWonByTeam.get(team);
        }
        if(tricksPlayed != 5) {
            try {
                throw new Exception("Cannot determine winner until all tricks have been played!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
