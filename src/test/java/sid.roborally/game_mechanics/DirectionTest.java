package sid.roborally.game_mechanics;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    //To test with GridObject we first need to make a class that inherits from GridObject.
    private class GridObjectInheriter extends GridObject {
        public GridObjectInheriter(int x, int y) { super(x, y); }
    }

    GridObjectInheriter testobject = new GridObjectInheriter(1,2);
    Robot testbot = new Robot(3,3);
    Direction dir1;


    @Test
    public void TurnLeftTest() {
        testobject.setOrientation(Direction.SOUTH);
        testobject.setOrientation(testobject.getOrientation().rotateLeft());
        assertEquals(testobject.getOrientation(),Direction.EAST);

    }

    @Test
    public void TurnRightTest() {
        //System.out.println("RIGHT RIGHT RIGHT");
        testobject.setOrientation(Direction.NORTH);
        //System.out.println(testobject.getOrientation());
        testobject.setOrientation(testobject.getOrientation().rotateRight());
        //System.out.println(testobject.getOrientation());
        assertEquals(testobject.getOrientation(),Direction.EAST);


    }

    @Test
    public void Turn180Test() {
        dir1 = Direction.NORTH;
        dir1 = dir1.rotateRight();
        dir1 = dir1.rotateRight();
        //System.out.println(dir1);
        dir1 = dir1.rotate180();
        assertEquals(dir1,Direction.NORTH);
        testbot.setOrientation(dir1);
        testbot.setOrientation(testbot.getOrientation().rotate180());
        assertEquals(testbot.getOrientation(),Direction.SOUTH);
    }

    @Test
    public void valueOf() {
    }
}