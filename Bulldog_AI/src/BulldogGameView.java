package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.util.Map;

import java.util.HashMap;
import java.util.List;

public class BulldogGameView extends JFrame {
    private JTextArea textArea;
    private JButton submitButton;
    private JButton startGameButton;
    private JButton rollAgainButton;
    private JButton endTurnButton;
    private JPanel playerSelectionPanel;
    private JPanel centerPanel;
    private JPanel buttonPanel;
    private Map<String, JCheckBox> playerCheckBoxes;
    private JTable scoreboardTable;
    private DefaultTableModel scoreboardModel;
    private JLabel welcomeMessage;
    private BulldogGameController controller;

    /**
     * Constructs a new BulldogGameView and initializes the user interface.
     */
    public BulldogGameView() {
        setupUI();
    }

    /**
     * Registers the controller to be used by the view.
     * 
     * @param controller The controller to be registered.
     */
    public void registerController(BulldogGameController controller) {
        this.controller = controller;
        System.out.println("Controller has been set.");
    }

    /**
     * Sets up the user interface components and layout for the game.
     */
    private void setupUI() {
        setTitle("Bulldog Game");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setupTextArea();
        setupPlayerSelectionPanel();
        setupCenterPanel();
        setupButtonPanel();

        setVisible(true);
    }

    private void setupTextArea() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.EAST);
    }

    private void setupPlayerSelectionPanel() {
        playerSelectionPanel = new JPanel();
        playerSelectionPanel.setLayout(new GridLayout(7, 1));
        playerSelectionPanel.setBorder(BorderFactory.createTitledBorder("Player Selection"));

        playerCheckBoxes = new HashMap<>();
        addPlayerCheckBox("WimpPlayer");
        addPlayerCheckBox("RandomPlayer");
        addPlayerCheckBox("FifteenPlayer");
        addPlayerCheckBox("HumanPlayer");
        addPlayerCheckBox("UniquePlayerGPT");
        addPlayerCheckBox("UniquePlayerHuman");

        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            if (controller != null) {
                System.out.println("adding players now...");
                controller.loadPlayers();
                textArea.append("\nPlayers have been added. Ready to start the game.\n");
            } else {
                textArea.append("\nController is not set. Cannot load players.\n");
            }
        });
        playerSelectionPanel.add(submitButton);
        add(playerSelectionPanel, BorderLayout.WEST);
    }

    private void setupCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Game Info"));

        welcomeMessage = new JLabel("Welcome to the Bulldog Game! Select players to begin.");
        welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(welcomeMessage, BorderLayout.NORTH);

        scoreboardModel = new DefaultTableModel(new String[] { "Player", "Score" }, 0);
        scoreboardTable = new JTable(scoreboardModel);
        JScrollPane scoreboardScrollPane = new JScrollPane(scoreboardTable);
        centerPanel.add(scoreboardScrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void setupButtonPanel() {
        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> {
            if (controller != null) {
                controller.startGame();
            } else {
                textArea.append("\nController is not set. Cannot start the game.\n");
            }
        });

        rollAgainButton = new JButton("Roll Again");
        endTurnButton = new JButton("End Turn");
        rollAgainButton.setEnabled(false);
        endTurnButton.setEnabled(false);

        buttonPanel = new JPanel();
        buttonPanel.add(startGameButton);
        buttonPanel.add(rollAgainButton);
        buttonPanel.add(endTurnButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds a checkbox for a specific player type to the player selection panel.
     * 
     * @param playerType The name of the player type to add.
     */
    private void addPlayerCheckBox(String playerType) {
        JCheckBox checkBox = new JCheckBox(playerType);
        playerCheckBoxes.put(playerType, checkBox);
        playerSelectionPanel.add(checkBox);
    }

    /**
     * Updates the scoreboard with the current players and their scores.
     * 
     * @param players A map of player names to their scores.
     */
    public void updateScoreboard(Map<String, Integer> players) {
        scoreboardModel.setRowCount(0); // Clear existing rows
        for (Map.Entry<String, Integer> entry : players.entrySet()) {
            scoreboardModel.addRow(new Object[] { entry.getKey(), entry.getValue() });
        }
    }

    /**
     * Updates the welcome message in the center panel.
     * 
     * @param message The new welcome message to display.
     */
    public void updateWelcomeMessage(String message) {
        welcomeMessage.setText(message);
    }

    /**
     * Enables or disables the roll button.
     * 
     * @param enable True to enable the button, false to disable it.
     */
    public void enableRollButton(boolean enable) {
        rollAgainButton.setEnabled(enable);
    }

    /**
     * Enables or disables the end turn button.
     * 
     * @param enable True to enable the button, false to disable it.
     */
    public void enableEndTurnButton(boolean enable) {
        endTurnButton.setEnabled(enable);
    }

    /**
     * Updates the text area with the current player's information.
     * 
     * @param playerName The name of the current player.
     * @param rolls      The list of roll values for the current player.
     * @param turnScore  The current player's turn score.
     * @param totalScore The current player's total score.
     */
    public void updateTextArea(String playerName, List<Integer> rolls, int turnScore, int totalScore) {
        textArea.append("\nCurrent Player: " + playerName);
        textArea.append("\nRolls: " + rolls);
        textArea.append("\nTurn Score: " + turnScore);
        textArea.append("\nTotal Score: " + totalScore + "\n");
    }

    /**
     * Returns the text area for displaying game messages.
     * 
     * @return The JTextArea instance.
     */
    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * Returns the submit button for player selection.
     * 
     * @return The JButton instance for submitting player selections.
     */
    public JButton getSubmitButton() {
        return submitButton;
    }

    /**
     * Returns the start game button.
     * 
     * @return The JButton instance for starting the game.
     */
    public JButton getStartGameButton() {
        return startGameButton;
    }

    /**
     * Returns the roll again button.
     * 
     * @return The JButton instance for rolling the dice again.
     */
    public JButton getRollAgainButton() {
        return rollAgainButton;
    }

    /**
     * Returns the end turn button.
     * 
     * @return The JButton instance for ending the current player's turn.
     */
    public JButton getEndTurnButton() {
        return endTurnButton;
    }

    /**
     * Returns the map of player type checkboxes.
     * 
     * @return A map where the keys are player type names and the values are JCheckBox instances.
     */
    public Map<String, JCheckBox> getPlayerCheckBoxes() {
        return playerCheckBoxes;
    }

    /**
     * Returns the center panel for dynamic game content.
     * 
     * @return The JPanel instance for the center panel.
     */
    public JPanel getCenterPanel() {
        return centerPanel;
    }

    /**
     * Returns the button panel for game actions.
     * 
     * @return The JPanel instance for the button panel.
     */
    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    /**
     * Returns the scoreboard table for dynamic updates.
     * 
     * @return The JTable instance for the scoreboard.
     */
    public JTable getScoreboardTable() {
        return scoreboardTable;
    }
    

}