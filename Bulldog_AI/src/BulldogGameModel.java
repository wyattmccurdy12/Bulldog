/**
 * The BulldogGameModel class represents the Model in the MVC architecture
 * for the Bulldog Game. It manages the game logic, including player data,
 * dice rolls, and determining the game's state.
 * 
 * Responsibilities:
 * - Maintains the list of players and their scores.
 * - Tracks the current player's turn.
 * - Handles dice rolls and determines if the game is won.
 * - Resets the game state when necessary.
 * 
 * Dependencies:
 * - Player: Represents individual players in the game.
 * - Dice: Represents the dice used for rolling in the game.
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * Written with help from Github Copilot (GPT-4o)
 */
package src;

import java.util.ArrayList;
import java.util.List;

public class BulldogGameModel {
    private static final int WINNING_SCORE = 104;
    private List<Player> players;
    private int currentPlayerIndex;
    private Dice dice;
    private boolean gameWon;

    /**
     * Constructs a new BulldogGameModel with an empty list of players,
     * a six-sided dice, and initializes the game state.
     */
    public BulldogGameModel() {
        players = new ArrayList<>();
        dice = new Dice(6);
        gameWon = false;
    }

    /**
     * Adds a player to the game.
     * 
     * @param player The Player instance to add.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Returns the list of players in the game.
     * 
     * @return A list of Player instances.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the current player whose turn it is.
     * 
     * @return The Player instance representing the current player.
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Advances to the next player's turn.
     */
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    /**
     * Rolls the dice and returns the result.
     * 
     * @return The result of the dice roll.
     */
    public int rollDice() {
        return dice.roll();
    }

    /**
     * Checks if the game has been won by the current player.
     * 
     * @return True if the game is won, false otherwise.
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Checks if the current player has reached the winning score
     * and updates the game state accordingly.
     */
    public void checkWin() {
        if (getCurrentPlayer().getScore() >= WINNING_SCORE) {
            gameWon = true;
        }
    }

    /**
     * Resets the game state, including player scores and turn order.
     */
    public void resetGame() {
        for (Player player : players) {
            player.setScore(0);
            player.setTurnScore(0);
        }
        gameWon = false;
        currentPlayerIndex = 0;
    }
}