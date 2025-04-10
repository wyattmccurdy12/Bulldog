package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Player class that holds generic information about a player of the game Bulldog.
 * 
 * <p>See Kettering University, CS-101, Prog 6</p>
 * 
 * 
 * <p>Wyatt McCurdy</p>
 * <p>Login ID: wyatt.mccurdy@maine.edu</p>
 * <p>COS 420/520, Spring 2025</p>
 * 
 * 
 * Written with help from Github Copilot (GPT-4o)
 * 
 */
public abstract class Player {
    
    private String name;    // The name of the Player
    
    private int score;      // The score earned by this Player during the game

    private int turn_score; // The score earned by the player during the turn

    BulldogGameModel model;

    private List<GameObserver> observers = new ArrayList<>(); // List of observers

    private List<Integer> currentTurnRolls = new ArrayList<>();
    
    /**
     * Constructor: Player
     * 
     * <p>Purpose: Create a new Player object</p>
     * 
     * @param name the name of the Player being created
     */
    public Player(String name, BulldogGameModel model) {
        this.name = name;
        this.score = 0;
        this.model = model;
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
        notifyObservers(); // Notify observers when the score changes
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
        notifyObservers(); // Notify observers when the turn score changes
    }

    /**
     * Method: addObserver
     * 
     * <p>Purpose: add an observer to the list</p>
     * 
     * @param observer the observer to be added
     */
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    /**
     * Method: removeObserver
     * 
     * <p>Purpose: remove an observer from the list</p>
     * 
     * @param observer the observer to be removed
     */
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    /**
     * Method: notifyObservers
     * 
     * <p>Purpose: notify all observers of a state change</p>
     */
    protected void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update();
        }
    }

    /**
     * Method: play
     * 
     * <p>Purpose: abstract method to play the game</p>
     * 
     * @param dice the Dice object used in the game
     * @return int result of the play
     */
    public int play(Dice dice) {
        setTurnScore(0);
        List<Integer> my_rolling_results = implementRollingLogic(dice);

        // int my_rolling_result = 0;
        // for (int result : my_rolling_results) {
        //     my_rolling_result = my_rolling_result + result;
        // }

        // Update current player information for the model
        model.updateCurrentPlayer(name, my_rolling_results, score, turn_score);

        return turn_score;
    }

    public abstract List<Integer> implementRollingLogic(Dice dice);

    protected int roll(Dice dice) {
        int my_roll = dice.roll();
        
        notifyObservers();

        return my_roll;
    }

}