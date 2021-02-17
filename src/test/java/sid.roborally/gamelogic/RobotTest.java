package sid.roborally.gamelogic;

import org.junit.Before;
import org.junit.Test;
import sid.roborally.game_mechanics.Robot;
import sid.roborally.game_mechanics.Position;

import static org.junit.Assert.*;

public class RobotTest {
    private Robot r;

    @Before
    public void setUp(){
        r = new Robot(0,0);
    }

    @Test
    public void PlayerStartingPositionShouldEqualZeroZeroTest() {
        Position expected = new Position(0,0);
        Position result = r.getPosition();
        assertTrue(expected.equals(result));
    }

    @Test
    public void InitializedPlayerHasWonShouldEqualFalseTest() {
        boolean result = r.hasWon();
        assertFalse(result);
    }

    @Test
    public void StartPlayerAsAliveTest() {
        boolean result = r.isDead();
        assertFalse(result);
    }

    @Test
    public void SetPlayerAsDeadTest() {
        r.setIsDead(true);
        boolean result = r.isDead();
        assertTrue(result);
    }

    @Test
    public void SetHasWonToTrueTest() {
        r.setHasWon(true);
        boolean result = r.hasWon();
        assertTrue(result);
    }

    @Test
    public void GetLivesShouldReturnOneTest() {
        int result = r.getHealth();
        assertEquals(1,result);
    }
    //TODO test for getting and setting number of player lives
}
