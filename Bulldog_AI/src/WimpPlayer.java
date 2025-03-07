package src;
/**
 * WimpPlayer class: extends Player class
 * A WimpPlayer always rolls the die once.
 * 
 * <p>See Kettering University, CS-101, Prog 6</p>
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 */
public class WimpPlayer extends Player {

    /**
     * Constructor: WimpPlayer
     * Purpose: Create a default WimpPlayer
     */
    public WimpPlayer() {
        this("Wimp");
    }

    /**
     * Constructor: WimpPlayer
     * Purpose: Create a new WimpPlayer object
     * 
     * @param name the name of the Player being created
     */
    public WimpPlayer(String name) {
        super(name);
    }

    /**
     * This method will decide whether or not to continue rolling based on the WimpPlayer strategy (roll once).
     * 
     * @param roll the value of the roll
     * @return boolean result of the roll evaluation
     */
    public boolean evaulate_roll(int roll) {
        // update score
        setTurnScore(getTurnScore() + roll);

        if (roll == 6) {
            setTurnScore(0);
        }

        return false; // WimpPlayer always stops after one roll
    }
}