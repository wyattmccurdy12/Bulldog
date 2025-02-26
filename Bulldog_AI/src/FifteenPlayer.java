package src;

/**
 * FifteenPlayer class extends the Player class.
 * A FifteenPlayer always rolls until it gets to fifteen points.
 * 
 * <p>See Kettering University, CS-101, Prog 6</p>
 * 
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 520, Spring 2025</p>
 * 
 * Written with help from Github Copilot (GPT-4o)
 */
public class FifteenPlayer extends Player {

    /**
     * Default constructor for FifteenPlayer.
     * Creates a FifteenPlayer with the default name "Fifteen".
     */
    public FifteenPlayer() {
        this("Fifteen");
    }

    /**
     * Constructor for FifteenPlayer.
     * Creates a new FifteenPlayer object with the specified name.
     * 
     * @param name the name of the Player being created
     */
    public FifteenPlayer(String name) {
        super(name);
    }

    /**
     * Evaluates the roll and decides whether to continue rolling.
     * 
     * @param roll the roll provided by the game
     * @return true if the player should continue rolling, false otherwise
     */
    public boolean evaulate_roll(int roll) {
        // update score
        setTurnScore(getTurnScore() + roll);

        if (roll == 6) {
            setTurnScore(0);
            return false;
        } else if (getTurnScore() >= 15) {
            setScore(getScore() + getTurnScore()); // Update overall score
            return false;
        }
        return true;
    }
}