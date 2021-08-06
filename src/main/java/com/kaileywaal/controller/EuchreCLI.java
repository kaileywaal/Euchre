package com.kaileywaal.controller;

import com.kaileywaal.model.*;

public class EuchreCLI {
    private static Player player1;
    private static Player player2;
    private static Player player3;
    private static Player player4;

    private static Team team1;
    private static Team team2;

    private static Team[] teams;

    public static void main(String[] args) {
            player1 = new HumanPlayer("P1");
            player2 = new HumanPlayer("P2");
            player3 = new ComputerPlayer("P3");
            player4 = new HumanPlayer("P4");

            Player[] team1Players = {player1, player2};
            Player[] team2Players = {player3, player4};

            team1 = new Team(team1Players, "Team 1");
            team2 = new Team(team2Players, "Team 2");

            teams = new Team[] {team1, team2};


            Hand hand = new Hand(teams);
            System.out.println("Dealer: " + Hand.getDealer());
            Hand hand1 = new Hand(teams);
            System.out.println("Dealer: " + Hand.getDealer());
            Hand hand2 = new Hand(teams);
            System.out.println("Dealer: " + Hand.getDealer());
        Hand hand3 = new Hand(teams);
        System.out.println("Dealer: " + Hand.getDealer());
        Hand hand4 = new Hand(teams);
        System.out.println("Dealer: " + Hand.getDealer());


    }



}
