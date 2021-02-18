package sid.roborally.game_mechanics;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    GridObject testbot1 = new GridObject(1,2);
    Direction dir1;


    @Test
    public void left() {
        dir1 = Direction.NORTH;
        assertTrue(dir1 == Direction.NORTH);
        dir1 = Direction.WEST;
        assertTrue(dir1 == Direction.WEST);

    }

    @Test
    public void right() {
    }

    @Test
    public void values() {
        System.out.println(testbot1.getOrientation());
        testbot1.setOrientation(Direction.SOUTH);
        System.out.println(testbot1.getOrientation());
    }

    @Test
    public void valueOf() {
    }
}