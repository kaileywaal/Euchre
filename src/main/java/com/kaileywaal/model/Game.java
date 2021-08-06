package com.kaileywaal.model;

import java.io.PrintWriter;
import java.util.Map;

public class Game {
    private Player[] players;
    private Team[] teams;
    private Map<Team, Integer> teamScores;

    public Game(int numberOfHumanPlayers, String[] humanPlayerNames){
        // Add players based on how many human players there are
        int totalNumberOfPlayers = 4;
        int numberOfComputerPlayers = totalNumberOfPlayers - numberOfHumanPlayers;
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < numberOfHumanPlayers; j++) {
                Player player = new HumanPlayer(humanPlayerNames[j]);
                players[i] = player;
            }
            for(int k = 0; k < numberOfComputerPlayers; k++) {
                Player player = new ComputerPlayer("Computer " + (k + 1));
                players[i] = player;
            }
        }

        // Add players to team
        assert players != null;
        Player[] team1Players = {players[0], players[2]};
        Player[] team2Players = {players[1], players[3]};
        Team team1 = new Team(team1Players, "Team 1");
        Team team2 = new Team(team2Players, "Team 2");

        teams[0] = team1;
        teams[1] = team2;

        // initialize teamScores to zero
        for (Team team : teams) {
            teamScores.put(team, 0);
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public Team[] getTeams() {
        return teams;
    }

    public Map<Team, Integer> getTeamScores() {
        return teamScores;
    }



    public boolean shouldContinue() {
        boolean shouldContinue = true;
        for(Team team : teams) {
            if (team.getScore() >= 10) {
                shouldContinue = false;
                break;
            }
        }
        return shouldContinue;
    }

}

