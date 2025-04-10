package src;

import java.util.ArrayList;
import java.util.List;

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
    public UniquePlayerGPT(BulldogGameModel model) {
        super("UniqueGPT", model);
    }

    /**
     * Constructor: Creates a new UniquePlayerGPT object with the given name.
     * 
     * @param name The name of the player being created.
     */
    public UniquePlayerGPT(String name, BulldogGameModel model) {
        super(name, model);
    }

    /**
     * This method implements the unique logic for the player's turn using the Dice object.
     * 
     * @param dice the Dice object used for rolling
     * @return int the updated score after the turn
     */
    // @Override
    // public int play(Dice dice) {
    //     int turnScore = 0;
    //     while (true) {
    //         int roll = dice.roll();
    //         if (roll == 6) {
    //             return 0; // Turn ends with no points
    //         }
    //         turnScore += roll;

    //         // UniquePlayerGPT logic: Stop rolling if turn score >= 10 or roll >= 4
    //         if (turnScore >= 10 || roll >= 4) {
    //             break;
    //         }
    //     }
    //     setScore(getScore() + turnScore); // Update the player's total score
    //     return turnScore;
    // }

    @Override
    public List<Integer> implementRollingLogic(RandomDice dice) {
        int my_roll = 0;
        List<Integer> returnList = new ArrayList<>();
        while (getTurnScore() < 10 && my_roll < 4) {
            
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