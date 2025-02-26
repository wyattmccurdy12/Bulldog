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
     * This method will decide whether or not to continue rolling based on a unique strategy.
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
        } else if (getTurnScore() >= 10 || roll >= 4) {
            return false;
        }
        return true;
    }
}