package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class BulldogGameController {
    private BulldogGameModel model;
    private BulldogGameView view;
    private Map<String, Player> selectedPlayers;

    public BulldogGameController(BulldogGameModel model, BulldogGameView view) {
        this.model = model;
        this.view = view;
        selectedPlayers = new HashMap<>();
        initController();
    }

    private void initController() {
        view.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getCenterPanel().removeAll();
                view.getCenterPanel().add(new JLabel("Game is about to start! Players are:"));
                for (String playerType : selectedPlayers.keySet()) {
                    JLabel playerLabel = new JLabel(playerType);
                    view.getCenterPanel().add(playerLabel);
                }
                view.getCenterPanel().add(new JLabel("Click 'Start Game' to begin."));
                view.getCenterPanel().revalidate();
                view.getCenterPanel().repaint();
            }
        });

        view.getStartGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numPlayers = selectedPlayers.size();
                if (numPlayers < 2 || numPlayers > 5) {
                    view.getTextArea().append("\nPlease select between 2 to 5 players to start the game.\n");
                } else {
                    startGame();
                }
            }
        });

        view.getRollAgainButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continueHumanTurn();
            }
        });

        view.getEndTurnButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endHumanTurn();
            }
        });

        for (Map.Entry<String, JCheckBox> entry : view.getPlayerCheckBoxes().entrySet()) {
            String playerType = entry.getKey();
            JCheckBox checkBox = entry.getValue();
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    togglePlayerSelection(playerType, createPlayer(playerType), checkBox.isSelected());
                }
            });
        }
    }

    private void startGame() {
        model.resetGame();
        for (Player player : selectedPlayers.values()) {
            model.addPlayer(player);
        }
        view.getTextArea().append("\nThe game has started! Players are:\n");
        for (Player player : model.getPlayers()) {
            view.getTextArea().append(player.getName() + "\n");
        }
        view.getTextArea().append("\n" + model.getCurrentPlayer().getName() + "'s turn:\n");
        continueTurn();
    }

    private void continueTurn() {
        Player currentPlayer = model.getCurrentPlayer();
        boolean playerGoing = true;
        while (playerGoing) {
            int roll = model.rollDice();
            view.getTextArea().append(currentPlayer.getName() + " rolled a " + roll + "\n");

            playerGoing = currentPlayer.evaulate_roll(roll);
            view.getTextArea().append(currentPlayer.getName() + "'s turn score is " + currentPlayer.getTurnScore() + "\n");

            if (!playerGoing || roll == 6) {
                view.getTextArea().append("You rolled a six! Your turn ends. Please click 'End Turn'.\n");
                view.getRollAgainButton().setEnabled(false);
                view.getEndTurnButton().setEnabled(true);
                view.getButtonPanel().revalidate();
                view.getButtonPanel().repaint();
                return;
            }

            view.getRollAgainButton().setEnabled(true);
            view.getEndTurnButton().setEnabled(true);
            view.getButtonPanel().revalidate();
            view.getButtonPanel().repaint();
            return; // Wait for human player to take action
        }

        endTurn();
    }

    private void continueHumanTurn() {
        view.getRollAgainButton().setEnabled(false);
        view.getEndTurnButton().setEnabled(false);
        view.getButtonPanel().revalidate();
        view.getButtonPanel().repaint();
        continueTurn();
    }

    private void endHumanTurn() {
        Player currentPlayer = model.getCurrentPlayer();
        currentPlayer.setScore(currentPlayer.getScore() + currentPlayer.getTurnScore());
        endTurn();
    }

    private void endTurn() {
        Player currentPlayer = model.getCurrentPlayer();
        view.getTextArea().append(currentPlayer.getName() + "'s total score is " + currentPlayer.getScore() + "\n");

        currentPlayer.setTurnScore(0);
        model.checkWin();

        if (model.isGameWon()) {
            view.getTextArea().append("\n" + currentPlayer.getName() + " has won the game with a score of " + currentPlayer.getScore() + "!\n");
            view.getTextArea().append("\nThank you for playing Bulldog Game!\n");
            model.resetGame();
            return;
        }

        model.nextPlayer();
        view.getTextArea().append("\n" + model.getCurrentPlayer().getName() + "'s turn:\n");
        continueTurn();
    }

    private void togglePlayerSelection(String playerType, Player player, boolean isSelected) {
        if (isSelected) {
            selectedPlayers.put(playerType, player);
        } else {
            selectedPlayers.remove(playerType);
        }
    }

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