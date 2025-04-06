package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
public class BulldogGameController {

    private BulldogGameModel model;
    private BulldogGameView view;
    private Referee referee;
    private Map<String, Player> selectedPlayers;

    /**
     * Constructs a new {@code BulldogGameController} with the specified model and view.
     *
     * @param model the game model that holds the state of the game
     * @param view  the game view that handles user interface interactions
     */
    public BulldogGameController(BulldogGameModel model, BulldogGameView view) {
        this.model = model;
        this.view = view;
        this.referee = new Referee(model); // Initialize the Referee
        selectedPlayers = new HashMap<>();
    }

    /**
     * Starts the game by initializing the referee, displaying the list of players,
     * updating the scoreboard, and beginning the first player's turn.
     */
    private void startGame() {
        referee.startGame(new ArrayList<>(selectedPlayers.values()));
        view.getTextArea().append("\nThe game has started! Players are:\n");
        for (Player player : model.getPlayers()) {
            view.getTextArea().append(player.getName() + "\n");
        }
        updateScoreboard();
        view.getTextArea().append("\n" + model.getCurrentPlayer().getName() + "'s turn:\n");
        continueTurn();
    }

    /**
     * Handles the current player's turn by simulating dice rolls and evaluating the results.
     * The turn continues until the player decides to stop rolling.
     */
    private void continueTurn() {
        Player currentPlayer = model.getCurrentPlayer();
        boolean continueRolling;

        do {
            int roll = rollDice(); // Simulate a dice roll
            view.getTextArea().append("\n" + currentPlayer.getName() + " rolled a " + roll + ".\n");

            continueRolling = currentPlayer.evaluate_roll(roll); // Delegate decision-making to the player
            if (!continueRolling) {
                view.getTextArea().append("\n" + currentPlayer.getName() + " ended their turn.\n");
            }
        } while (continueRolling);

        endTurn();
    }

    /**
     * Ends the current player's turn, checks if the game is won, and either updates the scoreboard
     * or continues to the next player's turn.
     */
    private void endTurn() {
        String endTurnMessage = referee.endTurn();
        view.getTextArea().append(endTurnMessage);

        if (model.isGameWon()) {
            updateScoreboard();
            return;
        }

        updateScoreboard();
        continueTurn();
    }

    /**
     * Updates the scoreboard in the view with the latest scores from the referee.
     */
    private void updateScoreboard() {
        view.updateScoreboard(referee.getScoreboard());
    }
}