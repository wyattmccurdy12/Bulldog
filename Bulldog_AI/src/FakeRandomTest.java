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
    public void testSevenPlayerRollingLogic() {
        BulldogGameModel mockModel = new BulldogGameModel(); // Replace with a mock or stub if needed
        SevenPlayer sevenPlayer = new SevenPlayer("TestPlayer", mockModel);

        // Predefined rolls: 2, 3, 2 (total = 7), then 6 (ends turn with 0 points)
        List<Integer> predefinedRolls = Arrays.asList(2, 3, 2, 6);
        FakeRandom fakeRandom = new FakeRandom(6, predefinedRolls);

        List<Integer> rolls = sevenPlayer.implementRollingLogic(fakeRandom);

        // Verify the rolls
        assertEquals(Arrays.asList(2, 3, 2), rolls);

        // Verify the player's score
        assertEquals(7, sevenPlayer.getScore());

        // Test turn ending with a roll of 6
        rolls = sevenPlayer.implementRollingLogic(fakeRandom);
        assertEquals(Arrays.asList(0), rolls);
        assertEquals(7, sevenPlayer.getScore()); // Score should remain unchanged
    }
}
