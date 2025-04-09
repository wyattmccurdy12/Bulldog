package src;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JCheckBox;

/**
 * The BulldogGameController class is responsible for managing the flow of the Bulldog game.
 * It acts as the intermediary between the model, view, and referee.
 * The controller updates the view. 
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * Written with help from Github Copilot (GPT-4o)
 */
public class BulldogGameController implements GameObserver {

    private BulldogGameModel model;
    private BulldogGameView view;
    private Referee referee;

    /**
     * Constructs a new {@code BulldogGameController} with the specified model and view.
     *
     * @param model the game model that holds the state of the game
     * @param view  the game view that handles user interface interactions
     */
    public BulldogGameController(BulldogGameModel model, BulldogGameView view) {
        this.model = model;
        this.view = view;
        this.referee = new Referee(model.getWinningScore()); // Initialize the Referee without direct model interaction
        // startGame();
    }

    /**
     * Starts the game by repeatedly asking the referee to host rounds
     * until the game is won. Updates the view accordingly.
     */
    public void startGame() {

        boolean gameWon = false;

        System.out.println("getting players...");
        view.getTextArea().append("\nThe game has started! Players are:\n");
        for (Player player : model.getPlayers()) {
            view.getTextArea().append(player.getName() + "\n");
            System.out.println("Got player: " + player.getName());
        }

        while (!gameWon) {
            gameWon = referee.hostRound(model); // Ask the referee to host a round

            
        }

        Player winner = model.getCurrentPlayer(); // Get the winning player
        view.getTextArea().append("\nGame over! " + winner.getName() + " wins with a score of " + winner.getScore() + "!\n");
        System.out.println("Game has successfully started.");
    }

    /**
     * Called when the model notifies its observers of a change.
     * This method queries the referee and updates the view.
     */
    @Override
    public void update() {

        view.updateScoreboard(getPlayerScores()); // Update the scoreboard in the view

        // display the current player's score in the text area
        Player currentPlayer = model.getCurrentPlayer();
        view.getTextArea().append("\nCurrent player: " + currentPlayer.getName() + " with score: " + currentPlayer.getScore() + "\n");

    }

    /**
     * Retrieves player names and scores, and returns them as a Map<String, Integer>.
     *
     * @return a map containing player names as keys and their scores as values
     */
    public Map<String, Integer> getPlayerScores() {
        Map<String, Integer> playerScores = new HashMap<>();
        for (Player player : model.getPlayers()) {
            playerScores.put(player.getName(), player.getScore());
        }
        return playerScores;
    }

    /**
     * Loads players into the model based on the selected checkboxes in the view.
     */
    public void loadPlayers() {
        Map<String, JCheckBox> checkBoxes = view.getPlayerCheckBoxes();
        List<Player> players = new ArrayList<>();

        if (checkBoxes.get("WimpPlayer").isSelected()) {
            players.add(new WimpPlayer());
            System.out.println("wimp has been added");
        }
        if (checkBoxes.get("RandomPlayer").isSelected()) {
            players.add(new RandomPlayer());
            System.out.println("random has been added");
        }
        if (checkBoxes.get("FifteenPlayer").isSelected()) {
            players.add(new FifteenPlayer());
            System.out.println("fifteen has been added");
        }
        if (checkBoxes.get("UniquePlayerGPT").isSelected()) {
            players.add(new UniquePlayerGPT());
            System.out.println("uniquegpt has been added");
        }
        if (checkBoxes.get("UniquePlayerHuman").isSelected()) {
            players.add(new UniquePlayerHuman());
            System.out.println("unieuqhuman has been added");
        }

        model.initializePlayers(players); // Load the selected players into the model
        System.out.println("players have been initd");
    }
}