/********************************************************/
/* Wyatt McCurdy                                        */
/* COS 520, Spring 2025                                 */
/* Bulldog Game                                         */
/* UniquePlayer class: extends Player class             */
/*           A UniquePlayer will simply roll four times  */
/********************************************************/

public class UniquePlayerHuman extends Player {

    /********************************************************/
    /* Constructor: UniquePlayer                            */
    /* Purpose: Create a default UniquePlayer               */
    /* Parameters:                                          */
    /*   none                                               */
    /********************************************************/
    public UniquePlayerHuman() {
        this("UniqueHuman");
    }

    /********************************************************/
    /* Constructor: UniquePlayer                            */
    /* Purpose: Create a new UniquePlayer object            */
    /* Parameters:                                          */
    /*   String name:  the name of the Player being created */
    /********************************************************/
    public UniquePlayerHuman(String name) {
        super(name);
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
        int turnScore = 0;
        int numRolls = 0;

        while (numRolls < 4) {
            int roll = (int) (Math.random() * 6 + 1);
            if (roll != 6) {
                turnScore += roll;
            } else {
                turnScore = 0;
                break;
            }
            numRolls++;
        }

        setScore(getScore() + turnScore);
        return turnScore;
    }
}
