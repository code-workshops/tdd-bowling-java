import com.galvanize.BowlingGame;
import junit.framework.TestCase;

public class BowlingGameTestCase extends TestCase {
    private BowlingGame game;

    protected void setUp() {
        game = new BowlingGame();
    }

    private void rollMany(int rolls, int pins) {
        for (int i = 0; i < rolls; i++) {
            game.roll(pins);
        }
    }

    private void rollSpare() {
        game.roll(6);
        game.roll(4);
    }

    private void rollStrike() {
        game.roll(10);
    }

    public void testGutterGame() {
        rollMany(20, 0);
        assertEquals(0, game.getScore());
    }

    public void testAllOnes() {
        rollMany(20, 1);
        assertEquals(20, game.getScore());
    }

    public void testSpareFrameCountsNextRoll() {
        rollSpare();
        game.roll(3);
        rollMany(17, 0);
        assertEquals(16, game.getScore());
    }

    public void testOneStrike() {
        rollStrike();
        game.roll(3);
        game.roll(4);
        rollMany(16, 0);
        assertEquals(24, game.getScore());
    }

    public void testPerfectGame() {
        rollMany(12,10);
        assertEquals(300, game.getScore());
    }

    public void testGetCurrentFrame() {
        rollMany(10, 0);
        int currentFrame = game.getCurrentFrame();
        assertEquals(6, currentFrame);
    }

}
