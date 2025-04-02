package src;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Referee {

    private BulldogGameModel model;

    /**
     * Constructs a Referee with the specified game model.
     * 
     * @param model The BulldogGameModel instance representing the game logic.
     */
    public Referee(BulldogGameModel model) {
        this.model = model;
    }

    /**
     * Starts the game by resetting the model and adding players.
     * 
     * @param players The list of players to add to the game.
     */
    public void startGame(List<Player> players) {
        model.resetGame();
        for (Player player : players) {
            model.addPlayer(player);
        }
    }

    /**
     * Handles the current player's turn, including dice rolls and turn evaluation.
     * 
     * @return A message summarizing the turn's outcome.
     */
    public String continueTurn() {
        Player currentPlayer = model.getCurrentPlayer();
        StringBuilder turnSummary = new StringBuilder();

        while (!(currentPlayer instanceof HumanPlayer)) {
            int roll = model.rollDice();
            turnSummary.append(currentPlayer.getName()).append(" rolled a ").append(roll).append("\n");

            if (!currentPlayer.evaulate_roll(roll)) {
                turnSummary.append(currentPlayer.getName()).append("'s turn ends.\n");
                break;
            }

            turnSummary.append(currentPlayer.getName()).append("'s turn score is ").append(currentPlayer.getTurnScore()).append("\n");
        }

        return turnSummary.toString();
    }

    /**
     * Ends the current player's turn and checks for a winner.
     * 
     * @return A message summarizing the end of the turn or the game's outcome.
     */
    public String endTurn() {
        Player currentPlayer = model.getCurrentPlayer();
        currentPlayer.setTurnScore(0);
        model.checkWin();

        if (model.isGameWon()) {
            return currentPlayer.getName() + " has won the game with a score of " + currentPlayer.getScore() + "!\n";
        }

        model.nextPlayer();
        return model.getCurrentPlayer().getName() + "'s turn:\n";
    }

    /**
     * Retrieves the current scoreboard.
     * 
     * @return A map of player names to their scores.
     */
    public Map<String, Integer> getScoreboard() {
        Map<String, Integer> playerScores = new HashMap<>();
        for (Player player : model.getPlayers()) {
            playerScores.put(player.getName(), player.getScore());
        }
        return playerScores;
    }
}