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
public class BulldogGameModel implements GameObserver {
    private List<Player> players;
    private int currentPlayerIndex;
    private List<GameObserver> observers; // List of observers
    private static final int WINNING_SCORE = 104; // Define the winning score in the model

    /**
     * Constructs a new BulldogGameModel with an empty list of players,
     * a six-sided dice, and initializes the game state.
     */
    public BulldogGameModel() {
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
        for (Player player : players) {
            player.addObserver(this); // Register the model as an observer
        }
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
     * Returns the current player whose turn it is.
     * 
     * @return The Player instance representing the current player.
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
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
     * Updates the model when notified by an observed subject.
     */
    @Override
    public void update() {
        notifyObservers(); // Notify the model's observers when a player changes state
    }

    /**
     * Advances to the next player and notifies observers.
     */
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        notifyObservers();
    }

    /**
     * Returns the winning score for the game.
     * 
     * @return The winning score.
     */
    public int getWinningScore() {
        return WINNING_SCORE; // Provide access to the winning score
    }
}