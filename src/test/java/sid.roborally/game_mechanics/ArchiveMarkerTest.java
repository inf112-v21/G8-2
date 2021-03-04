package sid.roborally.game_mechanics;

import org.junit.Test;
import sid.roborally.game_mechanics.grid.ArchiveMarker;
import sid.roborally.game_mechanics.grid.Position;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArchiveMarkerTest {
    @Test
    public void getPositionShouldEqualZeroZero() {
        ArchiveMarker am1 = new ArchiveMarker(0,0,1);

        Position result = am1.getPosition();

        assertTrue(new Position(0,0).equals(result));
    }

    @Test
    public void getArchiveIDShouldReturnOne() {
        ArchiveMarker am1 = new ArchiveMarker(0,0,1);

        int result = am1.getID();

        assertEquals(1,result);
    }
}
