package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BulldogGameController {

    private BulldogGameModel model;
    private BulldogGameView view;
    private Referee referee;
    private Map<String, Player> selectedPlayers;

    public BulldogGameController(BulldogGameModel model, BulldogGameView view) {
        this.model = model;
        this.view = view;
        this.referee = new Referee(model); // Initialize the Referee
        selectedPlayers = new HashMap<>();
    }

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

    private void continueTurn() {
        Player currentPlayer = model.getCurrentPlayer();

        if (currentPlayer instanceof HumanPlayer) {
            enableHumanPlayerControls();
            view.getTextArea().append("\n" + currentPlayer.getName() + "'s turn. Please roll or end your turn.\n");
            return;
        }

        String turnSummary = referee.continueTurn();
        view.getTextArea().append(turnSummary);
        endTurn();
    }

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

    private void updateScoreboard() {
        view.updateScoreboard(referee.getScoreboard());
    }

    /**
     * Enables controls for the human player to interact with the game.
     */
    private void enableHumanPlayerControls() {
        // Enable the UI controls for the human player
        view.enableRollButton(true); // Assuming the view has a method to enable the roll button
        view.enableEndTurnButton(true); // Assuming the view has a method to enable the end-turn button
    }
}