package sid.roborally.gamelogic;

import org.junit.Before;
import org.junit.Test;
import sid.roborally.Robot;
import sid.roborally.Position;

import static org.junit.Assert.*;

public class RobotTest {
    private Robot p;

    @Before
    public void setUp(){
        p = new Robot(0,0);
    }

    @Test
    public void PlayerStartingPositionShouldEqualZeroZero() {
        Position expected = new Position(0,0);
        Position result = p.getPosition();
        assertTrue(expected.equals(result));
    }

    @Test
    public void InitializedPlayerHasWonShouldEqualFalseTest() {
        boolean result = p.hasWon();
        assertFalse(result);
    }

    @Test
    public void StartPlayerAsAliveTest() {
        boolean result = p.isDead();
        assertFalse(result);
    }
    //TODO test for getting and setting number of player lives
}
