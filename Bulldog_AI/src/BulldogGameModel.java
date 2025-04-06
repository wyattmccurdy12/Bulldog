/**
 * The BulldogGameModel class represents the Model in the MVC architecture
 * for the Bulldog Game. It is the data portion of the application, representing the game state. 
 * The model will keep all of the data for the game, and it will notify observers when 
 * it changes state. 
 * 
 * Responsibilities:
 * - Maintains the list of players and their scores.
 * - Notifies observers that state changes have taken place.
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
    private List<Player> players;
    private int currentPlayerIndex;

    /**
     * Constructs a new BulldogGameModel with an empty list of players,
     * a six-sided dice, and initializes the game state.
     */
    public BulldogGameModel() {
        players = new ArrayList<>();
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
     * Resets the game state, including player scores and turn order.
     */
    public void resetGame() {
        for (Player player : players) {
            player.setScore(0);
            player.setTurnScore(0);
        }
        currentPlayerIndex = 0;
    }
}