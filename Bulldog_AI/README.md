# Bulldog Dice Game

This program is a Swing implementation of Bulldog. Bulldog is a dice game in which at least two players take turns rolling a six-sided die. The players agree upon a winning score - default is 104 - and the first player to reach that score on their turn wins.

## Game Rules

During a player turn, the player may do one of two things:
- **Roll**: The player rolls the die and adds the number rolled to their current turn score.
- **End Turn**: The player decides to end their turn and adds their turn score to their total score.

**If a player rolls a six,** their turn ends and they forfeit all points earned on that turn.

Players take turns rolling until a player reaches the winning score.

## Player Types

In this implementation, there are several players that the game user may pit against one another:

- **WimpPlayer**: Always rolls the die once and then ends their turn.
- **RandomPlayer**: Randomly decides whether to roll or end the turn.
- **FifteenPlayer**: Continues rolling until their turn score reaches or exceeds fifteen points.
- **HumanPlayer**: Allows a human to decide whether to roll or end the turn.
- **UniquePlayerGPT**: If the turn score is ten or their roll is four or higher, they end their turn (whichever comes first).
- **UniquePlayerHuman**: Rolls four times.

## How to Play

1. **Setup**: Select the players you wish to participate using the checkboxes.
2. **Start Game**: Click the "Start Game" button to begin.
3. **Player Turns**: Players take turns rolling the die according to their strategies.
4. **Winning**: The first player to reach the winning score (104 points) wins the game.

## Code Structure

- **Player.java**: Abstract class that holds generic information about a player.
- **WimpPlayer.java**: Implements the WimpPlayer strategy.
- **RandomPlayer.java**: Implements the RandomPlayer strategy.
- **FifteenPlayer.java**: Implements the FifteenPlayer strategy.
- **HumanPlayer.java**: Implements the HumanPlayer strategy.
- **UniquePlayerGPT.java**: Implements the UniquePlayerGPT strategy.
- **UniquePlayerHuman.java**: Implements the UniquePlayerHuman strategy.
- **BulldogGame.java**: Manages the game between players and provides the UI.

## Student author (with help from Github Copilot running GPT 4o)

- **Wyatt McCurdy**
  - Login ID: wyatt.mccurdy@maine.edu
  - COS 420/520, Spring 2025

## Instructor
- **David Levine**

## References

- See Kettering University, CS-101, Prog 6

