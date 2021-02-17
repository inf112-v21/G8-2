package sid.roborally.gamelogic;

import org.junit.Before;
import org.junit.Test;
import sid.roborally.game_mechanics.GridObject;
import sid.roborally.game_mechanics.Position;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This EntityTest class tests creating a new entity with a x and y coordinate which is then
 * stored as a position with the Position class. Also testing getters and setters for Entity using Position
 * Will test for Direction once Direction is setup.
 *
 * @author Markus Edlin
 */
public class GridObjectTest {
    private GridObject e;

    @Before
    public void setUp(){
        e = new GridObject(0,0);
    }

    @Test
    public void PositionToStringShouldBeZeroZero(){
        String result = e.getPosition().toString();
        assertEquals("(0,0)", result);
    }

    @Test
    public void InitialXPositionValueShouldEqualZero() {
        e = new GridObject(0,1);
        int result = e.getPosition().getX();
        assertEquals(0, result);
    }

    @Test
    public void InitialYPositionValueShouldEqualZero() {
        e = new GridObject(1,0);
        int result = e.getPosition().getY();
        assertEquals(0, result);
    }

    @Test
    public void SettingPositionToOneOneShouldEqualThatTest() {
        e.setPosition(1,1);
        Position expected = new Position(1,1);
        Position result = e.getPosition();
        assertTrue(expected.equals(result));
    }
}
    //TODO Tests for Direction

