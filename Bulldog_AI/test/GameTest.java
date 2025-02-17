package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import src.FifteenPlayer;


public class GameTest {
    
    @Test
    public void fifteen_turn_test() {
        // Test to see whether or not FifteenPlayer will roll less than 15
        // Given just one try
        FifteenPlayer fifteen_player = new FifteenPlayer();

        int score = fifteen_player.play();
        assertTrue("Score should be at least 15 (or zero)", score >= 15 || score == 0);
    }
}
