package src;

import java.util.ArrayList;
import java.util.List;

public class BulldogGameModel {
    private List<Player> players;
    private int currentPlayerIndex;
    private List<GameObserver> observers; // List of observers

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
        notifyObservers(); // Notify observers of the change
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
}