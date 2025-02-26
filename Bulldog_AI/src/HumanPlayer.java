package src;

import java.util.Scanner;

/**
 * HumanPlayer class: A player that allows the human user to decide whether to roll or end the turn, 
 * referring the choice to whatever is provided by the game manager. 
 * 
 * <p>See Kettering University, CS-101, Prog 6</p>
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * Written with help from Github Copilot (GPT-4o)
 */
public class HumanPlayer extends Player {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor: Creates a default HumanPlayer.
     */
    public HumanPlayer() {
        this("Human");
    }

    /**
     * Constructor: Creates a new HumanPlayer object with the given name.
     * 
     * @param name The name of the player being created.
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * This method will decide whether or not to continue rolling based on the player's input.
     * 
     * @param roll the value of the roll
     * @return boolean result of the roll evaluation
     */
    public boolean evaulate_roll(int roll) {
        // update score
        setTurnScore(getTurnScore() + roll);

        if (roll == 6) {
            setTurnScore(0);
            return false;
        } else {
            return true;
        }
    }
}