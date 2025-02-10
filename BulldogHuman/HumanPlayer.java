import java.util.Scanner;

public class HumanPlayer extends Player {

	public HumanPlayer () {
		this("Human");
	}

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public int play() {
        System.out.print("Roll? (1 - yes / 2 - no) ");
        int turn_score = 0;
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        int doIPlay = scanner.nextInt();
        while (doIPlay == 1) {

            int roll = (int) (Math.random()*6 + 1);

            if (roll == 6) {
                turn_score = 0;
                System.out.println(getName() + " rolled a 6 and scored zero points for the turn.");
                int score = getScore();
                System.out.println(getName() + " currently has a score of " + getScore());
                // scanner.close();
                return 0;
            }

            System.out.println("You rolled a " + roll + ".");
            turn_score = turn_score + roll;
            System.out.println("Turn score: "+ turn_score);
            System.out.print("Roll? (1 - yes / 2 - no) ");
            doIPlay = scanner.nextInt();

        }

        int cur_score = getScore();
        setScore(cur_score + turn_score);
        System.out.println(getName() + " has " + getScore() + " points at the end of the turn.");

        return turn_score;
    }
}