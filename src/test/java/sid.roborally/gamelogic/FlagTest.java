package sid.roborally.gamelogic;

import org.junit.Test;
import sid.roborally.Flag;
import sid.roborally.Position;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Flag Test to test the creation with a correct ID & getters and setters for a flag's ID
 *
 * @author Markus Edlin
 */
public class FlagTest {

    @Test
    public void FlagCreationGetIDTest() {
        Flag f = new Flag(0,0,1);
        int result = f.getId();
        assertEquals(1, result);
    }

    @Test
    public void SetFlagIdToTwoShouldEqualTwo() {
        Flag f = new Flag(0,0,1);
        f.setId(2);
        int result = f.getId();
        assertEquals(2, result);
    }

    @Test
    public void FlagPositionShouldBeZeroZero() {
        Flag f = new Flag(0,0,1);
        Position expected = new Position(0,0);
        Position result = f.getPosition();
        assertTrue(expected.equals(result));
    }

}
