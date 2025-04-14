package src;

import java.util.ArrayList;
import java.util.List;

/**
 * HumanPlayer class: A player that allows a human to decide whether to roll or end the turn.
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * <p>Written with help from Github Copilot (GPT-4o)</p>
 */
public class HumanPlayer extends Player {

    /**
     * Constructor: Creates a default HumanPlayer.
     */
    public HumanPlayer(BulldogGameModel model) {
        super("Human", model);
    }

    /**
     * Constructor: Creates a new HumanPlayer object with the given name.
     * 
     * @param name The name of the player being created.
     */
    public HumanPlayer(String name, BulldogGameModel model) {
        super(name, model);
    }

    @Override
    public List<Integer> implementRollingLogic(RandomDice dice) {
        List<Integer> returnList = new ArrayList<>();
        // Human player logic to be implemented
        return returnList;
    }

    @Override
    public boolean checkHuman() {
        return true;
    }
}