package src;

import java.util.ArrayList;
import java.util.List;

public class BulldogGameModel {
    private static final int WINNING_SCORE = 104;
    private List<Player> players;
    private int currentPlayerIndex;
    private Dice dice;
    private boolean gameWon;

    public BulldogGameModel() {
        players = new ArrayList<>();
        dice = new Dice(6);
        gameWon = false;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public int rollDice() {
        return dice.roll();
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void checkWin() {
        if (getCurrentPlayer().getScore() >= WINNING_SCORE) {
            gameWon = true;
        }
    }

    public void resetGame() {
        for (Player player : players) {
            player.setScore(0);
            player.setTurnScore(0);
        }
        gameWon = false;
        currentPlayerIndex = 0;
    }
}