package src;
/********************************************************/
/* David Levine                                         */
/* Login ID: david.b.levine@maine.edu                   */
/* COS 497, Summer 2024                                 */
/* Programming Assignment 6                             */
/* WimpPlayer class: extends Player class               */
/*           A WimpPlayer always rolls the die once     */
/*      See Kettering University, CS-101, Prog 6        */
/********************************************************/

public class WimpPlayer extends Player {

    /********************************************************/
    /* Constructor: WimpPlayer                              */
    /* Purpose: Create a default WimpPlayer                 */
    /* Parameters:                                          */
    /*   none                                               */
    /********************************************************/
    public WimpPlayer() {
        this("Wimp");
    }

    /********************************************************/
    /* Constructor: WimpPlayer                              */
    /* Purpose: Create a new WimpPlayer object              */
    /* Parameters:                                          */
    /*   String name:  the name of the Player being created */
    /********************************************************/
    public WimpPlayer(String name) {
        super(name);
    }

    /**
     * This method will decide whether or not to continue rolling based on the WimpPlayer strategy.
     */
    public boolean evaulate_roll(int roll) {
        // update score
        setTurnScore(getTurnScore() + roll);

        if (roll == 6) {
            setTurnScore(0);

        }

        setScore(getScore() + getTurnScore());
        return false; // WimpPlayer always stops after one roll
    }


}