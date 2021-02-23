package sid.roborally.game_mechanics;

import org.junit.Test;
import sid.roborally.game_mechanics.grid.Position;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PositionTest {


    private Position pos= new Position(0,0);

    @Test
    public void IncrementFromXZeroShouldEqualOneTest() {
        pos = pos.increment(1,0);
        assertEquals("(1,0)", pos.toString());
    }

    @Test
    public void SettingZeroZeroToOneOneTest() {
        pos.setPosition(new Position(1,1));
        assertTrue(pos.equals(new Position(1,1)));
    }
}
