package src;

import java.util.Scanner;

public class HumanPlayer extends Player {

    private Scanner scanner;

    public HumanPlayer() {
        this("Human");
    }

    public HumanPlayer(String name) {
        super(name);
        this.scanner = new Scanner(System.in); // Initialize scanner for user input
    }

    /**
     * This method will decide whether or not to continue rolling based on the player's input.
     *
     * @param roll the value of the roll
     * @return boolean result of the roll evaluation
     */
    public boolean evaluate_roll(int roll) {
        // Update score
        setTurnScore(getTurnScore() + roll);

        if (roll == 6) {
            setTurnScore(0);
            System.out.println(getName() + " rolled a 6! Turn score reset to 0.");
            return false;
        }

        // Prompt the user for input
        System.out.println(getName() + ", your current turn score is " + getTurnScore() + ".");
        System.out.print("Do you want to roll again? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();

        return input.equals("yes");
    }
}