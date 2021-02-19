package sid.roborally.game_mechanics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PositionTest {
    @Test
    public void IncrementFromXZeroShouldEqualOneTest() {
        Position pos = new Position(0,0);
        pos = pos.increment(1,0);
        assertEquals("(1,0)", pos.toString());
    }

    @Test
    public void SettingZeroZeroToOneOneTest() {
        Position pos = new Position(0,0);
        pos.setPosition(new Position(1,1));
        assertTrue(pos.equals(new Position(1,1)));
    }
}
