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
     * This method implements the WimpPlayer's logic for playing a turn.
     * 
     * @param dice the Dice object used for rolling
     * @return int result of the turn score
     */
    @Override
    public int play(Dice dice) {
        int roll = dice.roll();
        if (roll == 6) {
            return 0; // Turn ends with no points
        }
        setScore(getScore() + roll); // Update the player's total score
        return roll; // Return the roll as the turn score
    }
}