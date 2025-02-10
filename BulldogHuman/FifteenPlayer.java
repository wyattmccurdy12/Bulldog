/********************************************************/
/* Wyatt McCurdy                                           */
/* COS 520, Spring 2025                                    */
/* Bulldog Game                                            */
/* FifteenPlayer class: extends Player class               */
/*           A FifteenPlayer always rolls the die as       */
/*           many times as it takes to get fifteen points  */
/********************************************************/

public class FifteenPlayer extends Player {

	/********************************************************/
	/* Constructor: FifteenPlayer                              */
	/* Purpose: Create a default FifteenPlayer                 */
	/* Parameters:                                          */
	/*   none                                               */
	/********************************************************/
	public FifteenPlayer () {
		this("IScoreFifteen");
	}

	/********************************************************/
	/* Constructor: FifteenPlayer                              */
	/* Purpose: Create a new FifteenPlayer object              */
	/* Parameters:                                          */
	/*   String name:  the name of the Player being created */
	/********************************************************/
	public FifteenPlayer (String name) {
		super(name);
	}

	/********************************************************/
	/* Method:  play                                        */
	/* Purpose: Play one round                              */
	/*          One round for a FifteenPlayer is            */
	/*          as many rolls as it takes to get to 15      */
	/* Parameters:                                          */
	/*   none                                               */
	/* Returns:                                             */
	/*   the score earned by the player on this turn,       */
	/*       which will be zero if a six was rolled         */
	/********************************************************/
	public int play() {
        int turn_score = 0;
        while (turn_score < 15) {
            int roll = (int) (Math.random()*6 + 1);
            System.out.print("Player " + getName() + " rolled " + roll );
            if (roll != 6) {
				turn_score = turn_score + roll;
                System.out.println(" and has a score of " + turn_score + " for the turn.");
            } else {
				System.out.println();
                System.out.println(getName() + " rolled a six and got a zero for the turn.");
				System.out.println(getName() + " currently has " + getScore() + " points.");
				turn_score = 0; 
				return turn_score;
            }
        }

		int cur_score = getScore();
		setScore(turn_score + cur_score);

		System.out.println("");
		System.out.println("******************************************");
		System.out.println(getName() + " currently has " + getScore() + " points.");
    
		return turn_score;
	}
}
