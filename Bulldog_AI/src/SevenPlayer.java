package src;

import java.util.ArrayList;
import java.util.List;

/**
 * SevenPlayer class extends the Player class.
 * A SevenPlayer always rolls until it gets to seven points.
 */
public class SevenPlayer extends Player {

    /**
     * Default constructor for SevenPlayer.
     * Creates a SevenPlayer with the default name "Seven".
     */
    public SevenPlayer(BulldogGameModel model) {
        super("Seven", model);
    }

    /**
     * Constructor for SevenPlayer.
     * Creates a new SevenPlayer object with the specified name.
     * 
     * @param name the name of the Player being created
     */
    public SevenPlayer(String name, BulldogGameModel model) {
        super(name, model);
    }

    /**
     * Implements the rolling logic for SevenPlayer.
     * Rolls until the turn score reaches 7 or a roll of 6 ends the turn.
     * 
     * @param dice the Dice object used for rolling
     * @return a list of integers representing the rolls during the turn
     */
    @Override
    public List<Integer> implementRollingLogic(Dice dice) {
        List<Integer> returnList = new ArrayList<>();
        while (getTurnScore() < 7) {
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
