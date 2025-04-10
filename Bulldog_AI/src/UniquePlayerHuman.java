package src;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Constructor: Creates a default UniquePlayerHuman.
     */
    public UniquePlayerHuman(BulldogGameModel model) {
        this("UniqueHuman", model);
    }

    /**
     * Constructor: Creates a new UniquePlayerHuman object with the given name.
     * 
     * @param name The name of the player being created.
     */
    public UniquePlayerHuman(String name, BulldogGameModel model) {
        super(name, model);
    }

    /**
     * This method will take a turn for the UniquePlayerHuman.
     * 
     * @param dice The dice object used for rolling
     * @return int The score for the turn
     */
    // @Override
    // public int play(Dice dice) {
    //     int turnScore = 0;
    //     int rolls = 0;
    //     while (rolls < 4) { // Roll exactly four times
    //         int roll = dice.roll();
    //         if (roll == 6) {
    //             return 0; // Turn ends with no points
    //         }
    //         turnScore += roll;
    //         rolls++;
    //     }
    //     setScore(getScore() + turnScore); // Update the player's total score
    //     return turnScore;
    // }

    @Override
    public List<Integer> implementRollingLogic(RandomDice dice) {
        int rolls = 0;
        List<Integer> returnList = new ArrayList<>();
        while (rolls < 4) {
            
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