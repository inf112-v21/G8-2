package sid.roborally.gamelogic;

import org.junit.Before;
import org.junit.Test;
import sid.roborally.Entity;

import static org.junit.Assert.assertEquals;

/**
 * This EntityTest class tests creating a new entity with a x and y coordinate which is then
 * stored as a position with the Position class. Also testing getters and setters for Entity using Position
 * Will test for Direction once Direction is setup.
 *
 * @author Markus Edlin
 */
public class  EntityTest{
    private Entity e;

    @Before
    public void setUp(){
        e = new Entity(0,0);
    }

    @Test
    public void PositionToStringShouldBeZeroZero(){
        String result = e.pos.toString();
        assertEquals("(0,0)", result);
    }

    @Test
    public void XPositionValueShouldEqualZero() {
        int result = e.pos.getX();
        assertEquals(0, result);
    }

    @Test
    public void YPositionValueShouldEqualZero() {
        e = new Entity(1,0);
        int result = e.pos.getY();
        assertEquals(0, result);
    }

    @Test
    public void SettingYValueToOneShouldEqualOne() {
        e.pos.setY(1);
        int result = e.pos.getY();
        assertEquals(1, result);
    }
    @Test
    public void SettingXValueToOneShouldEqualOne() {
        e.pos.setX(1);
        int result = e.pos.getX();
        assertEquals(1, result);
    }
    //TODO Tests for Direction

}
