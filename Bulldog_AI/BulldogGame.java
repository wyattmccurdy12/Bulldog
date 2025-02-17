import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * BulldogGame class: Creates a user interface for the Bulldog game.
 * Allows users to select players and start the game.
 */
public class BulldogGame extends JFrame {
    private ArrayList<JCheckBox> playerCheckBoxes;
    private Map<JCheckBox, JTextField> playerNameFields;
    private JButton startButton;
    private JTextArea gameLog;
    private JPanel mainPanel;
    private JPanel playerPanel;
    private JPanel gamePanel;
    private JPanel playerTilesPanel;

    private JButton rollButton;
    private JButton passButton;
    private ArrayList<Player> selectedPlayers;
    private int currentPlayerIndex;
    private int WINNING_SCORE = 104;

    /**
     * Constructor: Initializes the BulldogGame.
     */
    public BulldogGame() {
        setTitle("Bulldog Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        mainPanel = new JPanel(new BorderLayout());
        playerPanel = new JPanel(new BorderLayout());
        gamePanel = new JPanel(new BorderLayout());
        playerTilesPanel = new JPanel(new GridLayout(0, 1));

        initializeWelcomeScreen();
        initializePlayerSelectionScreen();
        initializeGameScreen();

        add(mainPanel, "Main");
        add(playerPanel, "PlayerSelection");
        add(gamePanel, "Game");

        showCard("Main");
    }

    /**
     * Initializes the welcome screen.
     */
    private void initializeWelcomeScreen() {
        JLabel welcomeLabel = new JLabel("Welcome to Bulldog Game!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        
        JTextArea descriptionArea = new JTextArea(
            "Bulldog is a 2-5 player game with the following rules:\n" +
            "- Each player's turn, they may roll the dice as many times as they like.\n" +
            "- If they roll a six, they lose the turn and must wait for their next turn.\n" +
            "- If they roll any other number, they may add that number to their total score.\n\n" +
            "You may roll as many times as you like, but if you get a six, your turn is over and all points for your turn are forfeited.\n\n" +
            "Enjoy Bulldog!"
        );
        descriptionArea.setEditable(false);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setOpaque(false);
        descriptionArea.setFont(new Font("Serif", Font.PLAIN, 16));
        
        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCard("PlayerSelection");
            }
        });

        mainPanel.add(welcomeLabel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
        mainPanel.add(playButton, BorderLayout.SOUTH);
    }

    /**
     * Initializes the player selection screen.
     */
    private void initializePlayerSelectionScreen() {
        playerCheckBoxes = new ArrayList<>();
        playerNameFields = new HashMap<>();

        JLabel headerLabel = new JLabel("Please choose your players and give them names!", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 18));
        playerPanel.add(headerLabel, BorderLayout.NORTH);

        JPanel selectionPanel = new JPanel(new GridLayout(0, 2));
        String[] playerTypes = {"WimpPlayer", "RandomPlayer", "FifteenPlayer", "HumanPlayer", "UniquePlayerGPT", "UniquePlayerHuman"};
        for (String playerType : playerTypes) {
            JCheckBox checkBox = new JCheckBox(playerType);
            JTextField nameField = new JTextField(playerType);
            playerCheckBoxes.add(checkBox);
            playerNameFields.put(checkBox, nameField);
            selectionPanel.add(checkBox);
            selectionPanel.add(nameField);
        }

        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        selectionPanel.setBorder(BorderFactory.createTitledBorder("Player Selection"));
        playerPanel.add(selectionPanel, BorderLayout.CENTER);
        playerPanel.add(startButton, BorderLayout.SOUTH);
    }

    /**
     * Initializes the game screen.
     */
    private void initializeGameScreen() {
        gameLog = new JTextArea();
        gameLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameLog);

        rollButton = new JButton("Roll");
        passButton = new JButton("Pass");

        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roll();
            }
        });

        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pass();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(0, 1));

        controlPanel.add(rollButton);
        controlPanel.add(passButton);

        gamePanel.add(playerTilesPanel, BorderLayout.CENTER);
        gamePanel.add(controlPanel, BorderLayout.WEST);
        gamePanel.add(scrollPane, BorderLayout.SOUTH);
    }

    /**
     * Starts the game with the selected players.
     */
    private void startGame() {
        selectedPlayers = new ArrayList<>();
        for (JCheckBox checkBox : playerCheckBoxes) {
            if (checkBox.isSelected()) {
                String playerName = playerNameFields.get(checkBox).getText();
                selectedPlayers.add(createPlayer(checkBox.getText(), playerName));
                addPlayerTile(playerName, checkBox.getText());
            }
        }

        if (selectedPlayers.size() < 2 || selectedPlayers.size() > 5) {
            JOptionPane.showMessageDialog(this, "Please select between 2 and 5 players.");
            return;
        }
        
        showCard("Game");
        playGame();
    }

    /**
     * Creates a player instance based on the player type and name.
     * @param playerType The type of player to create.
     * @param playerName The name of the player.
     * @return The created player instance.
     */
    private Player createPlayer(String playerType, String playerName) {
        switch (playerType) {
            case "WimpPlayer":
                return new WimpPlayer(playerName);
            case "RandomPlayer":
                return new RandomPlayer(playerName);
            case "FifteenPlayer":
                return new FifteenPlayer(playerName);
            case "HumanPlayer":
                return new HumanPlayer(playerName);
            case "UniquePlayerGPT":
                return new UniquePlayerGPT(playerName);
            case "UniquePlayerHuman":
                return new UniquePlayerHuman(playerName);
            default:
                return new WimpPlayer(playerName);
        }
    }

    /**
     * Adds a player tile to the game screen.
     * @param playerName The name of the player.
     * @param playerType The type of the player.
     */
    private void addPlayerTile(String playerName, String playerType) {
        JPanel playerTile = new JPanel();
        playerTile.setLayout(new GridLayout(0, 1));
        playerTile.setBorder(BorderFactory.createTitledBorder(playerName));

        JLabel typeLabel = new JLabel("Type: " + playerType);
        JLabel scoreLabel = new JLabel("Score: 0");
        JLabel turnScoreLabel = new JLabel("Turn Score: 0");

        playerTile.add(typeLabel);
        playerTile.add(scoreLabel);
        playerTile.add(turnScoreLabel);

        playerTilesPanel.add(playerTile);
        playerTilesPanel.revalidate();
        playerTilesPanel.repaint();
    }

    /**
     * Handles the roll action for the current player.
     */
    private void roll() {
        Player currentPlayer = selectedPlayers.get(currentPlayerIndex);

        int turn_score = currentPlayer.play();
        
        currentPlayer.setScore(turn_score);
    }


    /**
     * Handles the pass action for the current player.
     */
    private void pass() {
        Player currentPlayer = selectedPlayers.get(currentPlayerIndex);

        log("Player " + currentPlayer.getName() + " passed. Total score: " + currentPlayer.getScore());
        nextTurn();
    }

    /**
     * Advances to the next player's turn.
     */
    private void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % selectedPlayers.size();
        log("Player " + selectedPlayers.get(currentPlayerIndex).getName() + "'s turn.");
    }

    /**
     * Plays the game by iterating through the selected players and invoking their play methods in turn.
     */
    private void playGame() {
        int max_score = 0;
        while (max_score < WINNING_SCORE) {
            Player currentPlayer = selectedPlayers.get(currentPlayerIndex);
            log("Player " + currentPlayer.getName() + "'s turn.");
            int score = currentPlayer.play();
            if (score > max_score) {max_score = score;}
            currentPlayer.informGame(score, currentPlayer.getScore());
            if (currentPlayer.getScore() >= WINNING_SCORE) {
                log("Player " + currentPlayer.getName() + " wins!");
                break;
            }
            nextTurn();
        }
    }

    /**
     * Logs a message to the game log.
     * @param message The message to log.
     */
    private void log(String message) {
        gameLog.append(message + "\n");
        gameLog.setCaretPosition(gameLog.getDocument().getLength());
    }

    /**
     * Shows the specified card in the CardLayout.
     * @param card The name of the card to show.
     */
    private void showCard(String card) {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), card);
    }


    /**
     * Main method to run the BulldogGame.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BulldogGame().setVisible(true);
            }
        });
    }
}
