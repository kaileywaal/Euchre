package com.kaileywaal.model;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private Player[] players = new Player[4];
    private Team[] teams = new Team[2];
    private Map<Team, Integer> teamScores = new HashMap<>();

    public Game(int numberOfHumanPlayers, String[] humanPlayerNames){
        // Add players based on how many human players there are
        int totalNumberOfPlayers = 4;
        // Populate human players into players array
        for (int i = 0; i < numberOfHumanPlayers; i++) {
            Player player = new HumanPlayer(humanPlayerNames[i]);
            players[i] = player;
        }
        // Populate remainder of players array with computer players
        int computerNumber = 1;
        for(int i = numberOfHumanPlayers; i < totalNumberOfPlayers; i++) {
            Player player = new ComputerPlayer("Computer " + computerNumber);
            computerNumber++;
            players[i] = player;
        }

        // Add players to team
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

    public int getTeamScores(Team team) {
        return teamScores.get(team);
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

