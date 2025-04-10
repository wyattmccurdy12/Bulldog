package src;

import java.util.Random;

/**
 * Dice class: Represents a die with a specified number of sides.
 * It returns a uniformly random integer from a random seed 42.
 * 
 * <p>See Kettering University, CS-101, Prog 6</p>
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * Written with help from Github Copilot (GPT-4o)
 */
public class Dice extends RandomDice {
    private Random random;

    /**
     * Constructor: Creates a Dice object with the specified number of sides.
     * 
     * @param sides The number of sides of the die (can be up to 20).
     */
    public Dice(int sides) {
        super(sides);
        this.random = new Random();
    }

    /**
     * Rolls the die and returns a uniformly random integer between 1 and the number of sides.
     * 
     * @return A random integer between 1 and the number of sides.
     */
    @Override
    public int roll() {
        return random.nextInt(sides) + 1;
    }
}