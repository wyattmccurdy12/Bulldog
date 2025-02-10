/********************************************************/
/* Wyatt McCurdy                                        */
/* COS 520, Spring 2025                                 */
/* Bulldog Game                                         */
/* UniquePlayer class: extends Player class             */
/*           A UniquePlayer will simply roll four times  */
/********************************************************/

public class UniquePlayer extends Player {

	/********************************************************/
	/* Constructor: UniquePlayer                            */
	/* Purpose: Create a default UniquePlayer               */
	/* Parameters:                                          */
	/*   none                                               */
	/********************************************************/
	public UniquePlayer () {
		this("UniquePlayer");
	}

	/********************************************************/
	/* Constructor: UniquePlayer                            */
	/* Purpose: Create a new UniquePlayer object            */
	/* Parameters:                                          */
	/*   String name:  the name of the Player being created */
	/********************************************************/
	public UniquePlayer (String name) {
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
		// Initialize
        int turn_score = 0;
        int numRolls = 0;
        
		while (numRolls < 4) {
            int roll = (int) (Math.random()*6 + 1);
            System.out.println(getName() + " rolled a " + roll + ".");
            if (roll != 6) {
				turn_score = turn_score + roll;
                System.out.println(getName() + " has " + turn_score + " points for the turn.");
            } else {
                turn_score = 0;
				System.out.println(getName() + " rolled a 6 and lost all points this turn.");
				return turn_score;
            }
			numRolls = numRolls + 1;
        }

		System.out.println();
		System.out.println(getName() + " scored " + turn_score + " points this turn.");

		int cur_score = getScore();
		cur_score = cur_score + turn_score;
		setScore(cur_score);
		System.out.println(getName() + " has " + getScore() + " total points.");
		return turn_score;
	}
}
