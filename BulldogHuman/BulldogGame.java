/********************************************************/
/* Wyatt McCurdy                                          */
/* Login ID: wyatt.mccurdy@maine.edu                      */
/* COS 520 Spring 2025                                    */
/* Bulldog game                                           */
/* BulldogGame class: contains the main function          */
/*           The main function allows a player to choose  */
/*           a player, and play one round of Bulldog      */
/********************************************************/

import java.util.ArrayList;
import java.util.Scanner;
public class BulldogGame {
    public static void main(String[] args) {

        // Initialize the list of players.
        ArrayList<Player> players = new ArrayList<Player>();
        // players.

        System.out.println("Welcome to Bulldog! What is your choice for player 1?");
        System.out.println("1. Random");
        System.out.println("2. Human");
        System.out.println("3. Fifteen");
        System.out.println("4. Unique");

        // New scanner for user input
        Scanner s = new Scanner(System.in);

        // Player chooses
        int player1_choice = s.nextInt();


        // The user will choose which type of player they want to be...4
        if (player1_choice == 1) {
            players.add(new RandomPlayer());
        }
        else if (player1_choice == 2) {
            players.add(new HumanPlayer());
        }
        else if (player1_choice == 3) {
            players.add(new FifteenPlayer());
        }
        else if (player1_choice == 4) {
            players.add(new UniquePlayer());
        }
        System.out.println("You have chosen " + players.get(0).getName() + " for player 1.");

        System.out.println();

        System.out.println("What is your choice for player 2?");

        int player2_choice = s.nextInt();

        /*
         * Player 2 goes
         */



       // The user will choose which type of player they want to be...4
       if (player2_choice == 1) {
            players.add(new RandomPlayer());
        }
        else if (player2_choice == 2) {
            players.add(new HumanPlayer());
        }
        else if (player2_choice == 3) {
            players.add(new FifteenPlayer());
        }
        else if (player2_choice == 4) {
            players.add(new UniquePlayer());
        }

        System.out.println("You have chosen " + players.get(1).getName() + " for player 2.");

        Player player1 = players.get(0);
        Player player2 = players.get(1);
        // Player 1 score and player 2 score
        int turn = 1;

        System.out.println("################################################################");
        System.out.println("Game Start!");

        while (player1.getScore() < 104 && player2.getScore() < 104) {
            System.out.println("Turn " + turn + ": " + player1.getName());
            player1.play();
            System.out.println("**********************************************************");
            System.out.println();
            player2.play();
            System.out.println("**********************************************************");
            System.out.println();
            turn = turn + 1;
            
        }
        if (player1.getScore() > player2.getScore()) {
            System.out.println("Player 1 wins!");
            System.out.println("Player 1 final score: " + player1.getScore());
            System.out.println("Player 2 final score: " + player2.getScore());
        }
        else if (player2.getScore() > player1.getScore()) {
            System.out.println("Player 2 wins!");
            System.out.println("Player 1 final score: " + player1.getScore());
            System.out.println("Player 2 final score: " + player2.getScore());
        }
        else {
            System.out.println("The game ends in a tie!");
            System.out.println("Player 1 final score: " + player1.getScore());
            System.out.println("Player 2 final score: " + player2.getScore());
        }

        s.close();
    }
}
