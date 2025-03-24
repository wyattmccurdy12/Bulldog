/**
 * The BulldogGameView class represents the View in the MVC architecture
 * for the Bulldog Game. It is responsible for displaying the user interface
 * and providing components for user interaction.
 * 
 * Responsibilities:
 * - Displays the game state and player information.
 * - Provides buttons and checkboxes for user input.
 * - Updates the UI based on the game's state.
 * 
 * Dependencies:
 * - JTextArea: Displays game messages and updates.
 * - JButton: Provides buttons for user actions (e.g., start game, roll dice).
 * - JCheckBox: Allows the user to select player types.
 * - JPanel: Organizes UI components into sections.
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * Written with help from Github Copilot (GPT-4o)
 */
package src;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * Constructs a new BulldogGameView and initializes the user interface.
     */
    public BulldogGameView() {
        setupUI();
    }

    /**
     * Sets up the user interface components and layout for the game.
     */
    private void setupUI() {
        setTitle("Bulldog Game");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Text area for displaying game messages
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.EAST);

        // Panel for player selection checkboxes
        playerSelectionPanel = new JPanel();
        playerSelectionPanel.setLayout(new GridLayout(6, 1));
        playerSelectionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Add checkboxes for player types
        playerCheckBoxes = new HashMap<>();
        addPlayerCheckBox("WimpPlayer");
        addPlayerCheckBox("RandomPlayer");
        addPlayerCheckBox("FifteenPlayer");
        addPlayerCheckBox("HumanPlayer");
        addPlayerCheckBox("UniquePlayerGPT");
        addPlayerCheckBox("UniquePlayerHuman");

        // Submit button for player selection
        submitButton = new JButton("Submit");
        playerSelectionPanel.add(submitButton);
        add(playerSelectionPanel, BorderLayout.WEST);

        // Center panel for dynamic game content
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(centerPanel, BorderLayout.CENTER);

        // Buttons for game actions
        startGameButton = new JButton("Start Game");
        rollAgainButton = new JButton("Roll Again");
        endTurnButton = new JButton("End Turn");
        rollAgainButton.setEnabled(false);
        endTurnButton.setEnabled(false);

        // Panel for action buttons
        buttonPanel = new JPanel();
        buttonPanel.add(startGameButton);
        buttonPanel.add(rollAgainButton);
        buttonPanel.add(endTurnButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
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
}