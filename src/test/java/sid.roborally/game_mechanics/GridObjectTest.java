package sid.roborally.game_mechanics;

import org.junit.Before;
import org.junit.Test;
import sid.roborally.game_mechanics.grid.GridObject;
import sid.roborally.game_mechanics.grid.Position;

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

    //To test with GridObject we first need to make a class that inherits from GridObject.
    private class GridObjectInheriter extends GridObject {
        public GridObjectInheriter(int x, int y) { super(x, y); }
    }

    private GridObjectInheriter e;

    @Before
    public void setUp(){
        e = new GridObjectInheriter(0,0);
    }

    @Test
    public void PositionToStringShouldBeZeroZero(){
        String result = e.getPosition().toString();
        assertEquals("(0,0)", result);
    }

    @Test
    public void InitialXPositionValueShouldEqualZero() {
        e = new GridObjectInheriter(0,1);
        int result = e.getPosition().getX();
        assertEquals(0, result);
    }

    @Test
    public void InitialYPositionValueShouldEqualZero() {
        e = new GridObjectInheriter(1,0);
        int result = e.getPosition().getY();
        assertEquals(0, result);
    }

    @Test
    public void SettingPositionToOneOneShouldEqualThatTest() {
        e.setPosition(new Position(1,1));
        Position expected = new Position(1,1);
        Position result = e.getPosition();
        assertTrue(expected.equals(result));
    }
}
    //TODO Tests for Direction

