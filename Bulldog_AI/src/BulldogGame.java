package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * BulldogGame class manages the game between two to five players until one reaches the winning score.
 * It provides a simple UI for user interaction.
 * 
 * <p>For reference to where this idea came from, See Kettering University, CS-101, Prog 6</p>
 * 
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * Written with help from Github Copilot (GPT-4o)
 */
public class BulldogGame extends JFrame {

    private static final int WINNING_SCORE = 104;
    private JTextArea textArea;
    private JButton submitButton;
    private JButton startGameButton;
    private JButton rollAgainButton;
    private JButton endTurnButton;
    private int numPlayers;
    private Player[] players;
    private HashMap<String, Player> selectedPlayers;
    private int[] scores;
    private boolean gameWon;
    private JPanel centerPanel;
    private Random random;
    private boolean humanPlayerRolling;
    private JPanel buttonPanel;
    private Player currentPlayer;
    private int currentPlayerIndex;

    /**
     * Constructor initializes the game and sets up the UI.
     */
    public BulldogGame() {
        selectedPlayers = new HashMap<>();
        random = new Random();
        setupUI();
    }

    /**
     * Sets up the user interface for the game.
     */
    private void setupUI() {
        setTitle("Bulldog Game");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Center text area
        textArea = new JTextArea("Welcome to the bulldog dice game!\nPlease use the checkboxes above\nto indicate the players you wish to participate");
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.EAST);

        // Input panel 
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        // Add checkboxes for player selection
        JPanel playerSelectionPanel = new JPanel();
        playerSelectionPanel.setLayout(new GridLayout(6, 1));
        playerSelectionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border

        // Create checkboxes for each player type
        JCheckBox wimpPlayerCheckBox = new JCheckBox("WimpPlayer");
        JCheckBox randomPlayerCheckBox = new JCheckBox("RandomPlayer");
        JCheckBox fifteenPlayerCheckBox = new JCheckBox("FifteenPlayer");
        JCheckBox humanPlayerCheckBox = new JCheckBox("HumanPlayer");
        JCheckBox uniquePlayerGPTCheckBox = new JCheckBox("UniquePlayerGPT");
        JCheckBox uniquePlayerHumanCheckBox = new JCheckBox("UniquePlayerHuman");

        // Add action listeners to checkboxes
        wimpPlayerCheckBox.addActionListener(e -> togglePlayerSelection("WimpPlayer", new WimpPlayer(), wimpPlayerCheckBox.isSelected()));
        randomPlayerCheckBox.addActionListener(e -> togglePlayerSelection("RandomPlayer", new RandomPlayer(), randomPlayerCheckBox.isSelected()));
        fifteenPlayerCheckBox.addActionListener(e -> togglePlayerSelection("FifteenPlayer", new FifteenPlayer(), fifteenPlayerCheckBox.isSelected()));
        humanPlayerCheckBox.addActionListener(e -> togglePlayerSelection("HumanPlayer", new HumanPlayer(), humanPlayerCheckBox.isSelected()));
        uniquePlayerGPTCheckBox.addActionListener(e -> togglePlayerSelection("UniquePlayerGPT", new UniquePlayerGPT(), uniquePlayerGPTCheckBox.isSelected()));
        uniquePlayerHumanCheckBox.addActionListener(e -> togglePlayerSelection("UniquePlayerHuman", new UniquePlayerHuman(), uniquePlayerHumanCheckBox.isSelected()));

        // Add player checkboxes to the player selection panel
        playerSelectionPanel.add(wimpPlayerCheckBox);
        playerSelectionPanel.add(randomPlayerCheckBox);
        playerSelectionPanel.add(fifteenPlayerCheckBox);
        playerSelectionPanel.add(humanPlayerCheckBox);
        playerSelectionPanel.add(uniquePlayerGPTCheckBox);
        playerSelectionPanel.add(uniquePlayerHumanCheckBox);

        // Submit button
        submitButton = new JButton("Submit");
        playerSelectionPanel.add(submitButton);

        // Add the player selection panel to the west
        add(playerSelectionPanel, BorderLayout.WEST);

        // Center panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border
        add(centerPanel, BorderLayout.CENTER);

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();
                centerPanel.add(new JLabel("Game is about to start! Players are:"));
                for (String playerType : selectedPlayers.keySet()) {
                    JLabel playerLabel = new JLabel(playerType);
                    centerPanel.add(playerLabel);
                }
                centerPanel.add(new JLabel("Click 'Start Game' to begin."));
                centerPanel.revalidate();
                centerPanel.repaint();
            }
        });

        // Start Game button
        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numPlayers = selectedPlayers.size();
                if (numPlayers < 2 || numPlayers > 5) {
                    textArea.append("\nPlease select between 2 to 5 players to start the game.\n");
                } else {
                    startGame();
                }
            }
        });

        // Roll Again and End Turn buttons for human player
        rollAgainButton = new JButton("Roll Again");
        endTurnButton = new JButton("End Turn");
        rollAgainButton.setEnabled(false);
        endTurnButton.setEnabled(false);

        rollAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                humanPlayerRolling = true;
                continueHumanTurn();
            }
        });

        endTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                humanPlayerRolling = false;
                endHumanTurn();
            }
        });

        buttonPanel = new JPanel();
        buttonPanel.add(startGameButton);
        buttonPanel.add(rollAgainButton);
        buttonPanel.add(endTurnButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Starts the game by initializing players and their scores, and managing the game loop.
     */
    public void startGame() {
        gameWon = false;
        scores = new int[numPlayers];
        players = selectedPlayers.values().toArray(new Player[0]);
        currentPlayerIndex = 0;
        currentPlayer = players[currentPlayerIndex];

        // Clear the center panel and move game start text to the EAST text area
        centerPanel.removeAll();
        centerPanel.revalidate();
        centerPanel.repaint();

        textArea.append("\nThe game has started! Players are:\n");
        for (Player player : players) {
            textArea.append(player.getName() + "\n");
        }
        textArea.append("\n" + currentPlayer.getName() + "'s turn:\n");
        continueTurn();
    }

    /**
     * Continues the current player's turn until they decide to stop or roll a losing number.
     */
    private void continueTurn() {
        boolean player_going = true;
        while (player_going) {
            if (currentPlayer instanceof HumanPlayer && humanPlayerRolling) {
                int roll = roll_d6();
                textArea.append(currentPlayer.getName() + " rolled a " + roll + "\n");
    
                player_going = currentPlayer.evaulate_roll(roll);
                textArea.append(currentPlayer.getName() + "'s turn score is " + currentPlayer.getTurnScore() + "\n");
    
                if (!player_going || roll == 6) {
                    textArea.append("You rolled a six! Your turn ends. Please click 'End Turn'.\n");
                    rollAgainButton.setEnabled(false);
                    endTurnButton.setEnabled(true);
                    buttonPanel.revalidate();
                    buttonPanel.repaint();
                    return;
                }
    
                rollAgainButton.setEnabled(true);
                endTurnButton.setEnabled(true);
                buttonPanel.revalidate();
                buttonPanel.repaint();
                return; // Wait for human player to take action
            } else if (!(currentPlayer instanceof HumanPlayer)) {
                int roll = roll_d6();
                textArea.append(currentPlayer.getName() + " rolled a " + roll + "\n");
    
                player_going = currentPlayer.evaulate_roll(roll);
                textArea.append(currentPlayer.getName() + "'s turn score is " + currentPlayer.getTurnScore() + "\n");
    
                if (!player_going) {
                    currentPlayer.setScore(currentPlayer.getScore() + currentPlayer.getTurnScore());
                    break;
                }
            } else {
                rollAgainButton.setEnabled(true);
                endTurnButton.setEnabled(true);
                buttonPanel.revalidate();
                buttonPanel.repaint();
                return; // Wait for human player to take action
            }
        }
    
        endTurn();
    }
    
    /**
     * Continues the human player's turn by enabling the roll again and end turn buttons.
     */
    private void continueHumanTurn() {
        humanPlayerRolling = true;
        rollAgainButton.setEnabled(false);
        endTurnButton.setEnabled(false);
        buttonPanel.revalidate();
        buttonPanel.repaint();
        continueTurn();
    }

    /**
     * Ends the human player's turn by disabling the roll again and end turn buttons.
     */
    private void endHumanTurn() {
        humanPlayerRolling = false;
        rollAgainButton.setEnabled(false);
        endTurnButton.setEnabled(false);
        buttonPanel.revalidate();
        buttonPanel.repaint();
        endTurn();
    }

    /**
     * Ends the current player's turn and checks if they have won the game.
     * If not, it moves to the next player's turn.
     */
    private void endTurn() {
        textArea.append(currentPlayer.getName() + "'s total score is " + currentPlayer.getScore() + "\n");

        // Reset the current player's turn score to zero
        currentPlayer.setTurnScore(0);

        // Check if the current player has won
        if (currentPlayer.getScore() >= WINNING_SCORE) {
            textArea.append("\n" + currentPlayer.getName() + " has won the game with a score of " + currentPlayer.getScore() + "!\n");
            gameWon = true;
            textArea.append("\nThank you for playing Bulldog Game!\n");
            resetGame();
            return;
        }

        // Move to the next player
        currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        currentPlayer = players[currentPlayerIndex];
        textArea.append("\n" + currentPlayer.getName() + "'s turn:\n");
        continueTurn();
    }

    /**
     * Resets the game state and player scores to allow a new game to be played.
     */
    private void resetGame() {
        for (Player player : players) {
            player.setScore(0);
            player.setTurnScore(0);
        }
        selectedPlayers.clear();
        textArea.append("\nGame has been reset. You may select new players and start a new game.\n");
    }

    /**
     * Rolls a six-sided die.
     * @return The result of the die roll (1-6).
     */
    private int roll_d6() {
        return random.nextInt(6) + 1;
    }

    /**
     * Toggles the selection of a player type - controlled by a checkbox (on/off).
     * @param playerType The type of player.
     * @param player The player object.
     * @param isSelected Whether the player is selected.
     */
    private void togglePlayerSelection(String playerType, Player player, boolean isSelected) {
        System.out.println(player.getName() + " has been added to hashmap.");
        if (isSelected) {
            selectedPlayers.put(playerType, player);
        } else {
            selectedPlayers.remove(playerType);
        }
    }

    /**
     * Main method to start the BulldogGame.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new BulldogGame();
    }
}