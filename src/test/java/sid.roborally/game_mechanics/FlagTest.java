package sid.roborally.game_mechanics;

import org.junit.Test;
import sid.roborally.game_mechanics.grid.Flag;
import sid.roborally.game_mechanics.grid.Position;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Flag Test to test the creation with a correct ID & getters and setters for a flag's ID
 *
 * @author Markus Edlin
 */
public class FlagTest {

    private Flag f = new Flag(0,0,1);

    @Test
    public void FlagCreationGetIDTest() {
        int result = f.getID();
        assertEquals(1, result);
    }

    @Test
    public void SetFlagIdToTwoShouldEqualTwo() {
        f.setId(2);
        int result = f.getID();
        assertEquals(2, result);
    }

    @Test
    public void FlagPositionShouldBeZeroZero() {
        Position expected = new Position(0,0);
        Position result = f.getPosition();
        assertTrue(expected.equals(result));
    }

}
