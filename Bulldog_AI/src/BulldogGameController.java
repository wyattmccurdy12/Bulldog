/**
 * The BulldogGameController class serves as the Controller in the MVC architecture
 * for the Bulldog Game. It manages the interaction between the Model (game logic)
 * and the View (user interface). It handles user input, updates the Model, and
 * refreshes the View accordingly.
 * 
 * Responsibilities:
 * - Initializes the game by setting up players and starting the game loop.
 * - Handles user actions such as rolling the dice, ending turns, and selecting players.
 * - Updates the View to reflect the current state of the game.
 * 
 * Dependencies:
 * - BulldogGameModel: The Model that contains the game logic and state.
 * - BulldogGameView: The View that displays the game interface to the user.
 * - Player: Represents the players in the game.
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * Written with help from Github Copilot (GPT-4o)
 */
package src;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;

public class BulldogGameController {

    private BulldogGameModel model;
    private BulldogGameView view;
    private Map<String, Player> selectedPlayers;

    /**
     * Constructs a BulldogGameController with the specified Model and View.
     * 
     * @param model The BulldogGameModel instance representing the game logic.
     * @param view  The BulldogGameView instance representing the user interface.
     */
    public BulldogGameController(BulldogGameModel model, BulldogGameView view) {
        this.model = model;
        this.view = view;
        selectedPlayers = new HashMap<>();
        initController();
    }

    /**
     * Initializes the controller by setting up event listeners for the View's
     * components and handling user interactions.
     */
    private void initController() {
        setupSubmitButton();
        setupStartGameButton();
        setupRollAgainButton();
        setupEndTurnButton();
        setupPlayerSelection();
    }

    /**
     * Configures the "Submit" button to process selected players.
     */
    private void setupSubmitButton() {
        view.getSubmitButton().addActionListener(e -> {
            // Update the welcome message in the view
            view.updateWelcomeMessage("Game is about to start! Players are: " + String.join(", ", selectedPlayers.keySet()));
        });
    }

    /**
     * Configures the "Start Game" button to initialize the game.
     */
    private void setupStartGameButton() {
        view.getStartGameButton().addActionListener(e -> {
            int numPlayers = selectedPlayers.size();
            if (numPlayers < 2 || numPlayers > 5) {
                view.getTextArea().append("\nPlease select between 2 to 5 players to start the game.\n");
            } else {
                startGame();
            }
        });
    }

    /**
     * Configures the "Roll Again" button for human players.
     */
    private void setupRollAgainButton() {
        view.getRollAgainButton().addActionListener(e -> continueHumanTurn());
    }

    /**
     * Configures the "End Turn" button for human players.
     */
    private void setupEndTurnButton() {
        view.getEndTurnButton().addActionListener(e -> endHumanTurn());
    }

    /**
     * Configures player selection checkboxes.
     */
    private void setupPlayerSelection() {
        for (Map.Entry<String, JCheckBox> entry : view.getPlayerCheckBoxes().entrySet()) {
            String playerType = entry.getKey();
            JCheckBox checkBox = entry.getValue();
            checkBox.addActionListener(e -> togglePlayerSelection(playerType, createPlayer(playerType), checkBox.isSelected()));
        }
    }

    /**
     * Starts the game by resetting the Model, adding selected players, and
     * beginning the game loop.
     */
    private void startGame() {
        model.resetGame();
        for (Player player : selectedPlayers.values()) {
            model.addPlayer(player);
        }
        view.getTextArea().append("\nThe game has started! Players are:\n");
        for (Player player : model.getPlayers()) {
            view.getTextArea().append(player.getName() + "\n");
        }
        updateScoreboard(); // Update the scoreboard with initial scores
        view.getTextArea().append("\n" + model.getCurrentPlayer().getName() + "'s turn:\n");
        continueTurn();
    }

    /**
     * Continues the current player's turn by rolling the dice and evaluating the
     * result. Handles both robot and human players.
     */
    private void continueTurn() {
        Player currentPlayer = model.getCurrentPlayer();

        if (currentPlayer instanceof HumanPlayer) {
            enableHumanPlayerControls();
            view.getTextArea().append("\n" + currentPlayer.getName() + "'s turn. Please roll or end your turn.\n");
            return;
        }

        // Handle robot players automatically
        while (true) {
            int roll = model.rollDice();
            view.getTextArea().append(currentPlayer.getName() + " rolled a " + roll + "\n");

            if (!currentPlayer.evaulate_roll(roll)) {
                view.getTextArea().append(currentPlayer.getName() + "'s turn ends.\n");
                break;
            }

            view.getTextArea().append(currentPlayer.getName() + "'s turn score is " + currentPlayer.getTurnScore() + "\n");
        }

        endTurn();
    }

    /**
     * Enables controls for human players during their turn.
     */
    private void enableHumanPlayerControls() {
        view.getRollAgainButton().setEnabled(true);
        view.getEndTurnButton().setEnabled(true);
        view.getButtonPanel().revalidate();
        view.getButtonPanel().repaint();
    }

    /**
     * Continues the human player's turn by processing their decision to roll again.
     */
    private void continueHumanTurn() {
        Player currentPlayer = model.getCurrentPlayer();
        int roll = model.rollDice();
        view.getTextArea().append(currentPlayer.getName() + " rolled a " + roll + "\n");

        if (!currentPlayer.evaulate_roll(roll)) {
            view.getTextArea().append("You rolled a 6 or decided to stop. Your turn ends.\n");
            disableHumanPlayerControls();
            endTurn();
        } else {
            view.getTextArea().append(currentPlayer.getName() + "'s turn score is " + currentPlayer.getTurnScore() + "\n");
        }
    }

    /**
     * Ends the human player's turn by updating their score and proceeding to the
     * next player.
     */
    private void endHumanTurn() {
        Player currentPlayer = model.getCurrentPlayer();
        currentPlayer.setScore(currentPlayer.getScore() + currentPlayer.getTurnScore());
        view.getTextArea().append(currentPlayer.getName() + "'s total score is " + currentPlayer.getScore() + "\n");
        disableHumanPlayerControls();
        updateScoreboard(); // Update the scoreboard after the turn ends
        endTurn();
    }

    /**
     * Disables controls for human players after their turn ends.
     */
    private void disableHumanPlayerControls() {
        view.getRollAgainButton().setEnabled(false);
        view.getEndTurnButton().setEnabled(false);
        view.getButtonPanel().revalidate();
        view.getButtonPanel().repaint();
    }

    /**
     * Ends the current player's turn, checks for a winner, and proceeds to the next
     * player.
     */
    private void endTurn() {
        Player currentPlayer = model.getCurrentPlayer();
        currentPlayer.setTurnScore(0);
        model.checkWin();

        if (model.isGameWon()) {
            view.getTextArea().append("\n" + currentPlayer.getName() + " has won the game with a score of " + currentPlayer.getScore() + "!\n");
            view.getTextArea().append("\nThank you for playing Bulldog Game!\n");
            model.resetGame();
            updateScoreboard(); // Clear the scoreboard after the game ends
            return;
        }

        model.nextPlayer();
        updateScoreboard(); // Update the scoreboard for the next player's turn
        view.getTextArea().append("\n" + model.getCurrentPlayer().getName() + "'s turn:\n");
        continueTurn();
    }

    /**
     * Updates the scoreboard in the view with the current players and their scores.
     */
    private void updateScoreboard() {
        Map<String, Integer> playerScores = new HashMap<>();
        for (Player player : model.getPlayers()) {
            playerScores.put(player.getName(), player.getScore());
        }
        view.updateScoreboard(playerScores);
    }

    /**
     * Toggles the selection of a player type in the game.
     * 
     * @param playerType The type of player to toggle.
     * @param player     The Player instance to add or remove.
     * @param isSelected Whether the player type is selected.
     */
    private void togglePlayerSelection(String playerType, Player player, boolean isSelected) {
        if (isSelected) {
            selectedPlayers.put(playerType, player);
        } else {
            selectedPlayers.remove(playerType);
        }
    }

    /**
     * Creates a Player instance based on the specified player type.
     * 
     * @param playerType The type of player to create.
     * @return A Player instance of the specified type.
     */
    private Player createPlayer(String playerType) {
        switch (playerType) {
            case "WimpPlayer":
                return new WimpPlayer();
            case "RandomPlayer":
                return new RandomPlayer();
            case "FifteenPlayer":
                return new FifteenPlayer();
            case "HumanPlayer":
                return new HumanPlayer();
            case "UniquePlayerGPT":
                return new UniquePlayerGPT();
            case "UniquePlayerHuman":
                return new UniquePlayerHuman();
            default:
                throw new IllegalArgumentException("Unknown player type: " + playerType);
        }
    }
}