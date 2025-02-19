package src;
/********************************************************/
/* Wyatt McCurdy                                        */
/* COS 520, Spring 2025                                 */
/* Bulldog Game                                         */
/* UniquePlayerHuman class: extends Player class        */
/*           A UniquePlayerHuman will simply roll       */
/*           four times                                 */
/********************************************************/

public class UniquePlayerHuman extends Player {

    /********************************************************/
    /* Constructor: UniquePlayerHuman                       */
    /* Purpose: Create a default UniquePlayerHuman          */
    /* Parameters:                                          */
    /*   none                                               */
    /********************************************************/
    public UniquePlayerHuman() {
        this("UniqueHuman");
    }

    /********************************************************/
    /* Constructor: UniquePlayerHuman                       */
    /* Purpose: Create a new UniquePlayerHuman object       */
    /* Parameters:                                          */
    /*   String name:  the name of the Player being created */
    /********************************************************/
    public UniquePlayerHuman(String name) {
        super(name);
    }

    /**
     * This method will decide whether or not to continue rolling based on a unique strategy.
     */
    public boolean evaulate_roll(int roll) {
        // update score
        setTurnScore(getTurnScore() + roll);

        if (roll == 6) {
            setTurnScore(0);
            return false;
        } else if (getTurnScore() >= 10 || roll >= 4) {
            return false;
        }
        return true;
    }

    /********************************************************/
    /* Method:  play                                        */
    /* Purpose: Play one round for this player              */
    /* Parameters:                                          */
    /*   none                                               */
    /* Returns:                                             */
    /*   the score earned by the player on this turn,       */
    /*       which will be zero if a six was rolled         */
    /********************************************************/
    public int play() {
        setTurnScore(0);
        int numRolls = 0;
        boolean continueRolling = true;

        while (continueRolling && numRolls < 4) {
            int roll = (int) (Math.random() * 6 + 1);
            continueRolling = evaulate_roll(roll);
            numRolls++;
        }

        setScore(getScore() + getTurnScore());
        return getTurnScore();
    }
}