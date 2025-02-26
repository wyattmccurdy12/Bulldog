package src;

/**
 * RandomPlayer class: A player that randomly decides whether to roll or end the turn.
 * 
 * <p>See Kettering University, CS-101, Prog 6</p>
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * Written with help from Github Copilot (GPT-4o)
 */
public class RandomPlayer extends Player {

    /**
     * Constructor: Creates a default RandomPlayer.
     */
    public RandomPlayer() {
        this("Random");
    }

    /**
     * Constructor: Creates a new RandomPlayer object with the given name.
     * 
     * @param name The name of the player being created.
     */
    public RandomPlayer(String name) {
        super(name);
    }

    /**
     * This method will decide whether or not to continue rolling based on a random decision.
     * 
     * @param roll the value of the roll
     * @return boolean result of the roll evaluation
     */
    public boolean evaulate_roll(int roll) {
        boolean continuing = true;

        if (roll == 6) {
            setTurnScore(0);
            return false;
        } else {
            continuing = Math.random() < 0.5; // 50/50 chance to continue rolling
        }

        if (!continuing) {
            setScore(getScore() + getTurnScore());
            return false;
        } else {
            return true;
        }
    }
}