package src;

public class Referee {

    private int winningScore;
    private Dice dice;

    public Referee(int winningScore) {
        this.winningScore = winningScore;
        this.dice = new Dice(6); // Initialize a six-sided die
    }

    /**
     * Determines whether the game should continue based on the current player's score.
     * 
     * @param currentPlayerScore The score of the current player.
     * @return True if the game should continue, false otherwise.
     */
    public boolean shouldContinueGame(int currentPlayerScore) {
        return currentPlayerScore < winningScore; // Continue if score is below the winning score
    }

    /**
     * Hosts a round of the game, asking each player to take a turn and checking if a player has won.
     * 
     * @param model The game model containing players and game state.
     * @return True if a player has won, false otherwise.
     */
    public boolean hostRound(BulldogGameModel model) {
        for (Player player : model.getPlayers()) {
            
            int turnScore = player.play(dice); // Use the play() method instead of take_turn

            if (player.getScore() >= winningScore) {
                return true; // Player has won, stop the round
            }
        }
        return false; // No player has won, continue the game
    }
}