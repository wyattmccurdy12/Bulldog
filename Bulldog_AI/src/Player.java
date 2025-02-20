package src;

/**
 * Abstract Player class that holds generic information about a player of the game Bulldog.
 * 
 * <p>See Kettering University, CS-101, Prog 6</p>
 * 
 * <p>Programming Assignment 6</p>
 * 
 * <p>David Levine</p>
 * <p>Login ID: david.b.levine@maine.edu</p>
 * <p>COS 497, Summer 2024</p>
 */
public abstract class Player {
    
    private String name;    // The name of the Player
    
    private int score;      // The score earned by this Player during the game

    private int turn_score; // The score earned by the player during the turn
    
    /**
     * Constructor: Player
     * 
     * <p>Purpose: Create a new Player object</p>
     * 
     * @param name the name of the Player being created
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }
    
    /**
     * Method: getName
     * 
     * <p>Purpose: return the name of this Player</p>
     * 
     * @return the name of this Player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method: getScore
     * 
     * <p>Purpose: return the current score of this Player</p>
     * 
     * @return the current score of this Player
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Method: getTurnScore
     * 
     * <p>Purpose: return the current turn score of this Player</p>
     * 
     * @return the current turn score of this Player
     */
    public int getTurnScore() {
        return this.turn_score;
    }

    /**
     * Method: setScore
     * 
     * <p>Purpose: set the current score of this Player</p>
     * 
     * @param score the new value of the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Method: setTurnScore
     * 
     * <p>Purpose: set the current turn score of this Player</p>
     * 
     * @param score the new value of the turn score
     */
    public void setTurnScore(int score) {
        this.turn_score = score;
    }

    /**
     * Method: evaulate_roll
     * 
     * <p>Purpose: abstract method to evaluate the roll</p>
     * 
     * @param roll the value of the roll
     * @return boolean result of the roll evaluation
     */
    public abstract boolean evaulate_roll(int roll);
}