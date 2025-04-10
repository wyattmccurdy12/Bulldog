package src;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

/**
 * FakeRandomTest class: Tests the functionality of FakeRandom and SevenPlayer.
 */
public class FakeRandomTest {

    @Test
    public void testFakeRandomRolls() {
        List<Integer> predefinedRolls = Arrays.asList(3, 4, 5);
        FakeRandom fakeRandom = new FakeRandom(6, predefinedRolls);

        assertEquals(3, fakeRandom.roll());
        assertEquals(4, fakeRandom.roll());
        assertEquals(5, fakeRandom.roll());

        Exception exception = assertThrows(IllegalStateException.class, fakeRandom::roll);
        assertEquals("No more predefined rolls available.", exception.getMessage());
    }

    @Test
    public void testSevenPlayerStopsAtSeven() {
        BulldogGameModel mockModel = new BulldogGameModel(); // Replace with a mock or stub if needed
        SevenPlayer sevenPlayer = new SevenPlayer("TestPlayer", mockModel);

        // Predefined rolls: 2, 3, 2 (total = 7)
        List<Integer> predefinedRolls = Arrays.asList(2, 3, 2);
        FakeRandom fakeRandom = new FakeRandom(6, predefinedRolls);

        int turnScore = sevenPlayer.play(fakeRandom);

        // Verify the turn score and total score
        assertEquals(7, turnScore);
        assertEquals(7, sevenPlayer.getScore());
    }

    @Test
    public void testSevenPlayerResetsOnSix() {
        BulldogGameModel mockModel = new BulldogGameModel(); // Replace with a mock or stub if needed
        SevenPlayer sevenPlayer = new SevenPlayer("TestPlayer", mockModel);

        // Predefined rolls: 2, 3, 6 (resets score to 0)
        List<Integer> predefinedRolls = Arrays.asList(2, 3, 6);
        FakeRandom fakeRandom = new FakeRandom(6, predefinedRolls);

        int turnScore = sevenPlayer.play(fakeRandom);

        // Verify the turn score and total score
        assertEquals(0, turnScore);
        assertEquals(0, sevenPlayer.getScore());
    }

    @Test
    public void testSevenPlayerEdgeCaseExceedsSeven() {
        BulldogGameModel mockModel = new BulldogGameModel(); // Replace with a mock or stub if needed
        SevenPlayer sevenPlayer = new SevenPlayer("TestPlayer", mockModel);

        // Predefined rolls: 4, 4 (total = 8, exceeds 7)
        List<Integer> predefinedRolls = Arrays.asList(4, 4);
        FakeRandom fakeRandom = new FakeRandom(6, predefinedRolls);

        int turnScore = sevenPlayer.play(fakeRandom);

        // Verify the turn score and total score
        assertEquals(8, turnScore);
        assertEquals(8, sevenPlayer.getScore());
    }

    @Test
    public void testSevenPlayerMultipleTurns() {
        BulldogGameModel mockModel = new BulldogGameModel(); // Replace with a mock or stub if needed
        SevenPlayer sevenPlayer = new SevenPlayer("TestPlayer", mockModel);

        // First turn: 2, 3, 2 (total = 7)
        // Second turn: 3, 6 (resets to 0)
        List<Integer> predefinedRolls = Arrays.asList(2, 3, 2, 3, 6);
        FakeRandom fakeRandom = new FakeRandom(6, predefinedRolls);

        int firstTurnScore = sevenPlayer.play(fakeRandom);
        assertEquals(7, firstTurnScore);
        assertEquals(7, sevenPlayer.getScore());

        int secondTurnScore = sevenPlayer.play(fakeRandom);
        assertEquals(0, secondTurnScore);
        assertEquals(7, sevenPlayer.getScore()); // Total score remains unchanged
    }
}
