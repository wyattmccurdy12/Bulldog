import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BulldogGameUI extends JFrame {
    private ArrayList<JCheckBox> playerCheckBoxes;
    private JButton startButton;
    private JTextArea gameLog;
    private BulldogGame game;

    public BulldogGameUI() {
        setTitle("Bulldog Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(0, 1));
        playerCheckBoxes = new ArrayList<>();

        String[] playerTypes = {"WimpPlayer", "RandomPlayer", "FifteenPlayer", "HumanPlayer", "UniquePlayerGPT", "UniquePlayerHuman"};
        for (String playerType : playerTypes) {
            JCheckBox checkBox = new JCheckBox(playerType);
            playerCheckBoxes.add(checkBox);
            playerPanel.add(checkBox);
        }

        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        gameLog = new JTextArea();
        gameLog.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(gameLog);

        add(new JLabel("Select Players:"), BorderLayout.NORTH);
        add(playerPanel, BorderLayout.WEST);
        add(startButton, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void startGame() {
        ArrayList<Player> selectedPlayers = new ArrayList<>();
        for (JCheckBox checkBox : playerCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedPlayers.add(createPlayer(checkBox.getText()));
            }
        }

        if (selectedPlayers.size() < 2 || selectedPlayers.size() > 5) {
            JOptionPane.showMessageDialog(this, "Please select between 2 and 5 players.");
            return;
        }

        game = new BulldogGame(selectedPlayers, gameLog);
        game.startGame();
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
                return new WimpPlayer();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BulldogGameUI().setVisible(true);
            }
        });
    }
}
