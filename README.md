# Euchre
If you aren't familiar with Euchre, it is a four player trick-taking card game (similar to Bridge). You can read the official rules of Euchre [here.](https://bicyclecards.com/how-to-play/euchre/)

## An Overview
This project allows you to select the number of players who wish to play and play a full game of Euchre in the command line. If less than 4 players are playing, the remaining players will be computer players. Users can select what action they want to take from a menu that displays valid choices according to Euchre rules. The game continues until a team wins, at which point the player(s) can decide if they wish to play again.

## The Implementation
Users are able to:
- Select the number of players who wish to play against the computer
- View their cards and decide whether they want the dealer to pick up the top card or pass
- If applicable, view their cards and decide whether they want to call trump or pass
- Play cards in a trick based on what has already been played
- See who won each trick and, subsequently, who won the hand
- View score updates after each hand is played
- See the winner of the game once the game is over and a team has reached 10 points
- Once the game is over, decide whether they want to play again or end the program

## Future Plans
I built Euchre with the MVC design pattern in mind to make the logic extendable. This will allow me (or you!) to eventually build a front-end to this command line application without completely rebuilding the logic.

Additionally, as of now this version of Euchre does not allow players to "go alone." While this is very uncommon in a real-world game of Euchre, I plan on eventually adding this functionality so that it will be just like playing a game against your friends!

