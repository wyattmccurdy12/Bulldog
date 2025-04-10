package src;

import java.util.ArrayList;
import java.util.List;

/**
 * RandomPlayer class: A player that randomly decides whether to roll or end the turn.
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * <p>Written with help from Github Copilot (GPT-4o)</p>
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
     */
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