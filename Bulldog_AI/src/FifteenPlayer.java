package src;

import java.util.ArrayList;
import java.util.List;

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
    public FifteenPlayer(BulldogGameModel model) {
        super("Fifteen", model);
    }

    /**
     * Constructor for FifteenPlayer.
     * Creates a new FifteenPlayer object with the specified name.
     * 
     * @param name the name of the Player being created
     */
    public FifteenPlayer(String name, BulldogGameModel model) {
        super(name, model);
        // this.model = model;
    }

    // /**
    //  * Implements the turn logic for FifteenPlayer.
    //  * 
    //  * @param dice the Dice object used for rolling
    //  * @return the turn score after the rolls
    //  */
    // @Override
    // public int play(Dice dice) {
    //     while (getTurnScore() < 15) {
    //         int roll = roll(dice);
    //         setTurnScore(getTurnScore() + roll);

    //         if (roll == 6) {
    //             setTurnScore(0);
    //             return 0; // Turn ends with no points
    //         }
    //     }

    //     setScore(getScore() + getTurnScore()); // Add turn score to total score
    //     return getTurnScore(); // Return the turn score
    // }

    @Override
    public List<Integer> implementRollingLogic(Dice dice) {
        List<Integer> returnList = new ArrayList<>();
        while (getTurnScore() < 15) {
            
            int roll = roll(dice);
            setTurnScore(getTurnScore() + roll);

            if (roll == 6) {
                setTurnScore(0);
                returnList.add(0);
                return returnList;
            }
            returnList.add(roll);


        }
        setScore(getScore() + getTurnScore());
        return returnList;

    }
}