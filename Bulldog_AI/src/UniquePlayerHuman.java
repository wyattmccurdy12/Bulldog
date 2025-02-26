package src;

/**
 * UniquePlayerHuman class: A unique player that will simply roll four times.
 * 
 * <p>See Kettering University, CS-101, Prog 6</p>
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * Written with help from Github Copilot (GPT-4o)
 */
public class UniquePlayerHuman extends Player {
    private int num_rolls = 0;

    /**
     * Constructor: Creates a default UniquePlayerHuman.
     */
    public UniquePlayerHuman() {
        this("UniqueHuman");
    }

    /**
     * Constructor: Creates a new UniquePlayerHuman object with the given name.
     * 
     * @param name The name of the player being created.
     */
    public UniquePlayerHuman(String name) {
        super(name);
    }

    /**
     * This method will decide whether or not to continue rolling based on a unique strategy (roll four times every turn).
     * 
     * @param roll the value of the roll
     * @return boolean result of the roll evaluation
     */
    public boolean evaulate_roll(int roll) {
        num_rolls = num_rolls + 1;
        // update score
        setTurnScore(getTurnScore() + roll);

        if (roll == 6) {
            setTurnScore(0);
            return false;
        }
        else if (num_rolls > 4) {
            return false;
        }
        return true;
    }
}