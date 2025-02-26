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
public class Dice {
    private int sides;
    private Random random;

    /**
     * Constructor: Creates a Dice object with the specified number of sides.
     * 
     * @param sides The number of sides of the die (can be up to 20).
     */
    public Dice(int sides) {
        if (sides < 1 || sides > 20) {
            throw new IllegalArgumentException("Number of sides must be between 1 and 20.");
        }
        this.sides = sides;
        this.random = new Random(42); // Seed the random number generator with 42
    }

    /**
     * Rolls the die and returns a uniformly random integer between 1 and the number of sides.
     * 
     * @return A random integer between 1 and the number of sides.
     */
    public int roll() {
        return random.nextInt(sides) + 1;
    }

    /**
     * Gets the number of sides of the die.
     * 
     * @return The number of sides.
     */
    public int getSides() {
        return sides;
    }

    /**
     * Sets the number of sides of the die.
     * 
     * @param sides The number of sides to set (must be between 1 and 20).
     */
    public void setSides(int sides) {
        if (sides < 1 || sides > 20) {
            throw new IllegalArgumentException("Number of sides must be between 1 and 20.");
        }
        this.sides = sides;
    }
}