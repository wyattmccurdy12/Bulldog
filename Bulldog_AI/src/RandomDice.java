package src;

/**
 * RandomDice class: Abstract superclass for dice with a specified number of sides.
 * Subclasses must implement the roll() method.
 */
public abstract class RandomDice {
    protected int sides;

    /**
     * Constructor: Creates a RandomDice object with the specified number of sides.
     * 
     * @param sides The number of sides of the die (must be between 1 and 20).
     */
    public RandomDice(int sides) {
        if (sides < 1 || sides > 20) {
            throw new IllegalArgumentException("Number of sides must be between 1 and 20.");
        }
        this.sides = sides;
    }

    /**
     * Abstract method to roll the die.
     * 
     * @return A random integer between 1 and the number of sides.
     */
    public abstract int roll();

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
