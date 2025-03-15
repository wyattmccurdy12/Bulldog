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

    public BulldogGameView() {
        setupUI();
    }

    private void setupUI() {
        setTitle("Bulldog Game");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.EAST);

        playerSelectionPanel = new JPanel();
        playerSelectionPanel.setLayout(new GridLayout(6, 1));
        playerSelectionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        playerCheckBoxes = new HashMap<>();
        addPlayerCheckBox("WimpPlayer");
        addPlayerCheckBox("RandomPlayer");
        addPlayerCheckBox("FifteenPlayer");
        addPlayerCheckBox("HumanPlayer");
        addPlayerCheckBox("UniquePlayerGPT");
        addPlayerCheckBox("UniquePlayerHuman");

        submitButton = new JButton("Submit");
        playerSelectionPanel.add(submitButton);
        add(playerSelectionPanel, BorderLayout.WEST);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(centerPanel, BorderLayout.CENTER);

        startGameButton = new JButton("Start Game");
        rollAgainButton = new JButton("Roll Again");
        endTurnButton = new JButton("End Turn");
        rollAgainButton.setEnabled(false);
        endTurnButton.setEnabled(false);

        buttonPanel = new JPanel();
        buttonPanel.add(startGameButton);
        buttonPanel.add(rollAgainButton);
        buttonPanel.add(endTurnButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addPlayerCheckBox(String playerType) {
        JCheckBox checkBox = new JCheckBox(playerType);
        playerCheckBoxes.put(playerType, checkBox);
        playerSelectionPanel.add(checkBox);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getStartGameButton() {
        return startGameButton;
    }

    public JButton getRollAgainButton() {
        return rollAgainButton;
    }

    public JButton getEndTurnButton() {
        return endTurnButton;
    }

    public Map<String, JCheckBox> getPlayerCheckBoxes() {
        return playerCheckBoxes;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }
}