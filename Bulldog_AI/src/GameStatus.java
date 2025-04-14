package src;

/**
 * GameStatus class: Encapsulates the state of the game.
 * Tracks whether the game is won and the current high score.
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * <p>Written with help from Github Copilot (GPT-4o)</p>
 */
public class GameStatus {
    private boolean gameWon;
    private int highScore;
    private boolean isHumanTurn;

    /**
     * Constructor: Initializes the game state with default values.
     * The game is not won, and the high score is set to 0.
     */
    public GameStatus() {
        this.gameWon = false;
        this.highScore = 0;
    }

    /**
     * Checks if the game is won.
     * 
     * @return True if the game is won, false otherwise.
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Sets the game as won or not won.
     * 
     * @param gameWon True if the game is won, false otherwise.
     */
    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    /**
     * Gets the current high score of the game.
     * 
     * @return The current high score.
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Updates the high score if the provided score is higher than the current high score.
     * 
     * @param score The new score to compare with the current high score.
     */
    public void updateHighScore(int score) {
        if (score > this.highScore) {
            this.highScore = score;
        }
    }

    /**
     * Checks if it's a human player's turn.
     * 
     * @return True if it's a human player's turn, false otherwise.
     */
    public boolean isHumanTurn() {
        return isHumanTurn;
    }

    /**
     * Sets whether it's a human player's turn.
     * 
     * @param isHumanTurn True if it's a human player's turn, false otherwise.
     */
    public void setHumanTurn(boolean isHumanTurn) {
        this.isHumanTurn = isHumanTurn;
    }
}
