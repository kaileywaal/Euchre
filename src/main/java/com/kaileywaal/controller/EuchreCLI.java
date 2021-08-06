package com.kaileywaal.controller;

import com.kaileywaal.GameView;
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
            GameView gameView = new GameView(System.in, System.out);
            String[] testOptions = {"1", "2", "3"};
            Object response = gameView.getChoiceFromOptions(testOptions);
            System.out.println("Response was: " + response);
            gameView.displayMessage("Sample message");
    }



}
