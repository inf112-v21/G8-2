package sid.roborally.gamelogic;

import org.junit.Test;
import sid.roborally.Hole;
import sid.roborally.Position;

import static org.junit.Assert.assertTrue;

public class HoleTest {
    @Test
    public void HolePositionShouldEqualInitializedPosition() {
        Hole h = new Hole(0,0);
        Position expected = new Position(0,0);
        Position result = h.pos;
        assertTrue(expected.equals(result));
    }
}
