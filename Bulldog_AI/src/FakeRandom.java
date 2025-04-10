package src;

import java.util.List;
import java.util.Iterator;

/**
 * FakeRandom class: A subclass of RandomDice that uses a predefined list of integers for rolling.
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * <p>Written with help from Github Copilot (GPT-4o)</p>
 */
public class FakeRandom extends RandomDice {
    private Iterator<Integer> iterator;

    /**
     * Constructor: Creates a FakeRandom object with the specified number of sides and a list of predefined rolls.
     * 
     * @param sides The number of sides of the die (must be between 1 and 20).
     * @param rolls The list of predefined integers to return on each roll.
     */
    public FakeRandom(int sides, List<Integer> rolls) {
        super(sides);
        if (rolls == null || rolls.isEmpty()) {
            throw new IllegalArgumentException("Rolls list cannot be null or empty.");
        }
        this.iterator = rolls.iterator();
    }

    /**
     * Rolls the die and returns the next integer in the predefined list.
     * Throws an exception if the list is exhausted.
     * 
     * @return The next integer in the predefined list.
     */
    @Override
    public int roll() {
        if (!iterator.hasNext()) {
            throw new IllegalStateException("No more predefined rolls available.");
        }
        return iterator.next();
    }
}
