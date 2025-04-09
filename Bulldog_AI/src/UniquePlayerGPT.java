package src;

/**
 * UniquePlayerGPT class: A unique player that uses a unique strategy to maximize the score.
 * 
 * <p>See Kettering University, CS-101, Prog 6</p>
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * Written with help from Github Copilot (GPT-4o)
 */
public class UniquePlayerGPT extends Player {

    /**
     * Constructor: Creates a default UniquePlayerGPT.
     */
    public UniquePlayerGPT() {
        this("UniqueGPT");
    }

    /**
     * Constructor: Creates a new UniquePlayerGPT object with the given name.
     * 
     * @param name The name of the player being created.
     */
    public UniquePlayerGPT(String name) {
        super(name);
    }

    /**
     * This method implements the unique logic for the player's turn using the Dice object.
     * 
     * @param dice the Dice object used for rolling
     * @return int the updated score after the turn
     */
    @Override
    public int play(Dice dice) {
        int turnScore = 0;
        while (true) {
            int roll = dice.roll();
            if (roll == 6) {
                return 0; // Turn ends with no points
            }
            turnScore += roll;

            // UniquePlayerGPT logic: Stop rolling if turn score >= 10 or roll >= 4
            if (turnScore >= 10 || roll >= 4) {
                break;
            }
        }
        setScore(getScore() + turnScore); // Update the player's total score
        return turnScore;
    }
}