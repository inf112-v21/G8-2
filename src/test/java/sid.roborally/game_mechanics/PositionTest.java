package sid.roborally.game_mechanics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PositionTest {
    @Test
    public void IncrementFromXZeroShouldEqualOneTest() {
        Position pos = new Position(0,0);
        pos.increment(1,0);
        int result = pos.getX();
        assertEquals(1, result);
    }

    @Test
    public void SettingZeroZeroToOneOneTest() {
        Position pos = new Position(0,0);
        pos.setPosition(1,1);
        assertTrue(pos.equals(new Position(1,1)));
    }
}
