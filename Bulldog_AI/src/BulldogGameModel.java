package src;

import java.util.ArrayList;
import java.util.List;

/**
 * The BulldogGameModel is responsible for storing and managing game data. 
 * The data stored here is data for players. When players change state, 
 * then the model will update observers. 
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * Written with help from Github Copilot (GPT-4o)
 */
public class BulldogGameModel {
    private List<Player> players;

    private List<GameObserver> observers; // List of observers
    private static final int WINNING_SCORE = 104; // Define the winning score in the model

    // Add some attributes: current player name, list of current player roll values, current player score
    private String currentPlayerName;
    private List<Integer> currentPlayerRolls = new ArrayList<>();
    private int currentPlayerScore = 0;
    private int currentPlayerTurnScore = 0;

    /**
     * Constructs a new BulldogGameModel with an empty list of players,
     * a six-sided dice, and initializes the game state.
     */
    public BulldogGameModel() {
        players = new ArrayList<>();
        observers = new ArrayList<>(); // Initialize the observers list
    }

    /**
     * Adds a player to the game.
     * 
     * @param player The Player instance to add.
     */
    public void addPlayer(Player player) {
        players.add(player);
        notifyObservers(); // Notify observers of the change
    }

    /**
     * Initializes the players and registers the model as an observer for all players.
     * 
     * @param players The list of Player instances to initialize.
     */
    public void initializePlayers(List<Player> players) {
        this.players = players;
        notifyObservers(); // Notify observers of the initial state
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
     * Resets the game state, including player scores and turn order.
     */
    public void resetGame() {
        for (Player player : players) {
            player.setScore(0);
            player.setTurnScore(0);
        }
        notifyObservers(); // Notify observers of the change
    }

    /**
     * Registers an observer to be notified of game state changes.
     * 
     * @param observer The observer to register.
     */
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    /**
     * Unregisters an observer so it no longer receives notifications.
     * 
     * @param observer The observer to unregister.
     */
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers of a change in the game state.
     */
    private void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update();
        }
    }



    /**
     * Returns the winning score for the game.
     * 
     * @return The winning score.
     */
    public int getWinningScore() {
        return WINNING_SCORE; // Provide access to the winning score
    }

    /**
     * Updates the current player's name, rolls, and score.
     * 
     * @param name  The name of the current player.
     * @param rolls The list of roll values for the current player.
     * @param score The current score of the player.
     */
    public void updateCurrentPlayer(String name, List<Integer> rolls, int score, int turn_score) {
        this.currentPlayerName = name;
        this.currentPlayerRolls = new ArrayList<>(rolls); // Create a copy of the rolls list
        this.currentPlayerScore = score;
        this.currentPlayerTurnScore = turn_score;

        notifyObservers();

    }

    /**
     * Returns the name of the current player.
     * 
     * @return The current player's name.
     */
    public String getCurrentPlayerName() {
        return currentPlayerName;
    }

    /**
     * Returns the list of rolls for the current player.
     * 
     * @return A list of integers representing the current player's rolls.
     */
    public List<Integer> getCurrentPlayerRolls() {
        return new ArrayList<>(currentPlayerRolls); // Return a copy to avoid external modification
    }

    /**
     * Returns the current player's turn score.
     * 
     * @return The current player's turn score.
     */
    public int getCurrentPlayerTurnScore() {
        return currentPlayerTurnScore;
    }

    /**
     * Returns the current player's total score.
     * 
     * @return The current player's total score.
     */
    public int getCurrentPlayerScore() {
        return currentPlayerScore;
    }
}