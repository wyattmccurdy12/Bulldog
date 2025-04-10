package src;

import java.util.ArrayList;
import java.util.List;

/**
 * FifteenPlayer class extends the Player class.
 * A FifteenPlayer always rolls until it gets to fifteen points.
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * <p>Written with help from Github Copilot (GPT-4o)</p>
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

    @Override
    public List<Integer> implementRollingLogic(RandomDice dice) {
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