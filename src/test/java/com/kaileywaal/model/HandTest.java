package com.kaileywaal.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class HandTest {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    private Team team1;
    private Team team2;

    private Team[] teams;

    @Before
    public void setup() {
        player1 = new HumanPlayer("P1");
        player2 = new HumanPlayer("P2");
        player3 = new ComputerPlayer("P3");
        player4 = new HumanPlayer("P4");

        Player[] team1Players = {player1, player2};
        Player[] team2Players = {player3, player4};

        team1 = new Team(team1Players, "Team 1");
        team2 = new Team(team2Players, "Team 2");

        teams = new Team[] {team1, team2};
    }


    @Test
    public void addPointToTeamThatWonTrick_should_update_points_for_correct_team() {
        Hand hand = new Hand(teams);
        Map<Team, Integer> teamPoints = hand.addPointToTeamThatWonTrick(player1);

        int actualTeam1Points = teamPoints.get(team1);
        int actualTeam2Points = teamPoints.get(team2);
        int expectedTeam1Points = 1;
        int expectedTeam2Points = 0;

        Assert.assertEquals(expectedTeam1Points, actualTeam1Points);
        Assert.assertEquals(expectedTeam2Points, actualTeam2Points);
    }

    @Test
    public void determineWinner_should_return_winning_team() {
        Hand hand = new Hand(teams);
        hand.addPointToTeamThatWonTrick(player1);
        hand.addPointToTeamThatWonTrick(player1);
        hand.addPointToTeamThatWonTrick(player1);
        hand.addPointToTeamThatWonTrick(player1);
        hand.addPointToTeamThatWonTrick(player1);

        Team actual = hand.determineWinner();

        Assert.assertEquals(team1, actual);
    }

    @Test
    public void determineWinner_adds_1_point_to_winning_team_when_they_called_it_and_win_3_tricks() {
        Hand hand = new Hand(teams);
        hand.setTrump(player1, "Spades");
        hand.addPointToTeamThatWonTrick(player1);
        hand.addPointToTeamThatWonTrick(player1);
        hand.addPointToTeamThatWonTrick(player1);
        hand.addPointToTeamThatWonTrick(player3);
        hand.addPointToTeamThatWonTrick(player3);

        hand.determineWinner();
        int actual = team1.getScore();

        Assert.assertEquals(1, actual);
    }

    @Test
    public void determineWinner_adds_2_points_to_winning_team_when_they_call_it_and_win_5_tricks() {
        Hand hand = new Hand(teams);
        hand.setTrump(player1, "Spades");
        hand.addPointToTeamThatWonTrick(player1);
        hand.addPointToTeamThatWonTrick(player1);
        hand.addPointToTeamThatWonTrick(player1);
        hand.addPointToTeamThatWonTrick(player2);
        hand.addPointToTeamThatWonTrick(player2);

        hand.determineWinner();
        int actual = team1.getScore();

        Assert.assertEquals(2, actual);
    }

    @Test
    public void determineWinner_gives_winning_team_2_points_when_they_euchre() {
        Hand hand = new Hand(teams);
        hand.setTrump(player3, "Spades");
        hand.addPointToTeamThatWonTrick(player1);
        hand.addPointToTeamThatWonTrick(player1);
        hand.addPointToTeamThatWonTrick(player1);
        hand.addPointToTeamThatWonTrick(player3);
        hand.addPointToTeamThatWonTrick(player3);

        hand.determineWinner();
        int actual = team1.getScore();

        Assert.assertEquals(2, actual);
    }

    @Test
    public void moveToNextDealer_should_return_one_more_than_current_dealer_position() {
        Hand hand = new Hand(teams);
        Player[] players = hand.getPlayers();
        int originalDealerPosition = 0;
        for(int i = 0; i < players.length; i++) {
            if(players[i].equals(Hand.getDealer())) {
                originalDealerPosition = i;
            }
        }
        int expected = originalDealerPosition == 3 ? 0 : originalDealerPosition + 1;
        Hand hand1 = new Hand(teams);
        int actual = 0;
        for(int i = 0; i < players.length; i++) {
            if(players[i].equals(Hand.getDealer())) {
                actual = i;
            }
        }

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void currentPlayer_should_return_player_after_dealer_when_hand_is_first_created() {
        Hand hand = new Hand(teams);
        Player dealer = Hand.getDealer();

        Player[] players = hand.getPlayers();
        int dealerPosition = 0;
        for(int i = 0; i < players.length; i++) {
            if(players[i].equals(Hand.getDealer())) {
                dealerPosition = i;
            }
        }
        int expectedCurrentPlayerPosition = dealerPosition == 3 ? 0 : dealerPosition + 1;
        Player expectedCurrentPlayer = players[expectedCurrentPlayerPosition];
        Player actualCurrentPlayer = hand.getCurrentPlayer();

        Assert.assertEquals(expectedCurrentPlayer, actualCurrentPlayer);
    }

    @Test
    public void moveToNextPlayer_should_return_next_player_in_loop() {
        Hand hand = new Hand(teams);
        Player currentPlayer = hand.getCurrentPlayer();
        int currentPlayerPosition = hand.findPlayerPosition(currentPlayer);
        int nextPlayerPosition = currentPlayerPosition == 3 ? 0 : currentPlayerPosition + 1;


        Player expected = hand.getPlayers()[nextPlayerPosition];
        Player actual = hand.moveToNextPlayer();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void moveToNextPlayer_should_set_current_player_to_correct_player() {
        Hand hand = new Hand(teams);
        Player currentPlayer = hand.getCurrentPlayer();
        int currentPlayerPosition = hand.findPlayerPosition(currentPlayer);
        int nextPlayerPosition = currentPlayerPosition == 3 ? 0 : currentPlayerPosition + 1;
        hand.moveToNextPlayer();

        Player expected = hand.getPlayers()[nextPlayerPosition];
        Player actual = hand.getCurrentPlayer();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setCurrentPlayer_should_set_current_player_to_correct_player() {
        Hand hand = new Hand(teams);
        Player currentPlayer = player3;
        hand.setCurrentPlayer(player2);

        Assert.assertEquals(hand.getCurrentPlayer(), player2);
    }


}