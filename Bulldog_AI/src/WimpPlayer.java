package src;

import java.util.ArrayList;
import java.util.List;

/**
 * WimpPlayer class: extends Player class.
 * A WimpPlayer always rolls the die once.
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
    public WimpPlayer(BulldogGameModel model) {
        this("Wimp", model);
    }

    /**
     * Constructor: WimpPlayer
     * Purpose: Create a new WimpPlayer object
     * 
     * @param name the name of the Player being created
     */
    public WimpPlayer(String name, BulldogGameModel model) {
        super(name, model);
    }

    @Override
    public List<Integer> implementRollingLogic(RandomDice dice) {

        List<Integer> returnList = new ArrayList<>();
        
        int roll = roll(dice);
        setTurnScore(getTurnScore() + roll);

        if (roll == 6) {
            setTurnScore(0);
            returnList.add(0);
            return returnList;
        }
        
        returnList.add(roll);
        setScore(getScore() + getTurnScore());
        return returnList;
    }


}