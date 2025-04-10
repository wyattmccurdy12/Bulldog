package src;

import java.util.ArrayList;
import java.util.List;

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
    public RandomPlayer(BulldogGameModel model) {
        super("Random", model);
    }

    /**
     * Constructor: Creates a new RandomPlayer object with the given name.
     * 
     * @param name The name of the player being created.
     */
    public RandomPlayer(String name, BulldogGameModel model) {
        super(name, model);
    }

    /**
     * This method allows the RandomPlayer to play a turn using the dice.
     * 
     * @param dice The dice object used for rolling.
    //  * @return int The turn score after the player finishes their turn.
    //  */
    // @Override
    // public int play(Dice dice) {
    //     setTurnScore(0); // Reset turn score at the start of the turn
    //     while (Math.random() < 0.5) { // 50/50 chance of rolling again
    //         int roll = roll(dice);
    //         if (roll == 6) {
    //             setTurnScore(0); // Reset turn score if a 6 is rolled
    //             return 0; // Turn ends with no points
    //         }
    //         setTurnScore(getTurnScore() + roll); // Update turn score
    //     }
    //     setScore(getScore() + getTurnScore()); // Add turn score to total score
    //     return getTurnScore(); // Return the turn score
    // }


    @Override
    public List<Integer> implementRollingLogic(RandomDice dice) {
        List<Integer> returnList = new ArrayList<>();
        while (Math.random() < 0.5) {
            
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