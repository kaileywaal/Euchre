package com.kaileywaal;

import com.kaileywaal.model.Card;
import com.kaileywaal.model.Player;
import com.kaileywaal.model.Team;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameView {
    private PrintWriter out;
    private Scanner in;

    public GameView(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }

    private void displayOptions(Object[] options) {
        out.println();
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println("[" + optionNum + "] " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }

    public void displayMessage(String message) {
        out.println(message + "\n");
    }

    public void displayCardPlayed(Player player, Card cardPlayed) {
        out.println(player.getName() + " played a " + cardPlayed + ".");
    }

    public void displayAllCardsPlayed(List<Card> cardsPlayed) {
        if(cardsPlayed.size() == 0) {
            out.println("Your turn to lead!");
        }
        else {
            out.print("So far, ");
            out.print(cardsPlayed);
            if(cardsPlayed.size() == 1) {
                out.println(" has been played.");
            } else {
                out.println(" have been played.");
            }
        }
    }

    public void displayPlayerCards(Player player) {
        out.println("You have: " + player.getHand() + " in your hand.");
    }

    public void displayTrickWinner(Player trickWinner, Card winningCard, Map<Team, Integer> teamTricksWon) {
        out.println(trickWinner + " won the trick with a " + winningCard + ".");
        List<Team> teams = new ArrayList<>();
        for(Team team : teamTricksWon.keySet()) {
            teams.add(team);
        }

        Team team1 = teams.get(0);
        Team team2 = teams.get(1);
        int team1TricksWon = teamTricksWon.get(team1);
        int team2TricksWon = teamTricksWon.get(team2)
        int tricksLeftInHand = 5 - team1TricksWon - team2TricksWon;

        out.println(team1.getName() + " has won " + team1TricksWon + (team1TricksWon > 1 ? " tricks" : " trick") + " in this hand.");
        out.println(team2.getName() + " has won " + team2TricksWon + (team2TricksWon > 1 ? " tricks" : " trick") + " in this hand.");
        if(tricksLeftInHand != 0) {
            out.println("There " + ((tricksLeftInHand == 1) ? "is " : "are ") + tricksLeftInHand + "tricks left to play in this hand.");
        }
    }

    public void displayHandWinner(Team winningTeam, int points, int numberOfTricksWon, Player caller) {
        out.println(winningTeam + " won the hand with " + numberOfTricksWon + "tricks won.");
        out.println("Since " + caller + "called trump, " + winningTeam + " earned " + points + " points.\n");
    }

    public void displayScoreUpdate(Team[] teams) {
        int team1Score = teams[0].getScore();
        int team2Score = teams[1].getScore();

        if(team1Score != team2Score) {
            Team winningTeam = team1Score > team2Score ? teams[0] : teams[1];
            Team losingTeam = team1Score < team2Score ? teams[0] : teams[1];

            out.println(winningTeam.getName() + " is currently winning with a score of " + winningTeam.getScore() + ".");
            out.println(losingTeam.getName() + " has " + losingTeam.getScore() + " points \n");
        }
        else {
            out.println("Both teams are tied with a score of " + team1Score + "\n");
        }
    }


}
