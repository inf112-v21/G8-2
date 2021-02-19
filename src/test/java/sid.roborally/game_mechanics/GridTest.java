package sid.roborally.game_mechanics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

public class GridTest {
    Grid g;
    private Robot r;

    @Before
    public void setUp() {
        g = new Grid(5,5);
        r = new Robot(1,1);
        g.addGridObject(r);
    }

    @Test
    public void CreateEmptyGridTest() {
        ArrayList<ArrayList<HashSet<GridObject>>> expected = new ArrayList<>();
        for(int i = 0; i<5; i++){
            expected.add(new ArrayList<>());
            for(int j = 0; j<5; j++){
                expected.get(i).add(new HashSet<>());
            }
        }
        g.removeGridObject(r);
        ArrayList<ArrayList<HashSet<GridObject>>> result = g.grid;
        assertEquals(expected, result);
    }

    @Test
    public void CheckIfRobotIsAtPositionTest() {
        g.grid.get(r.getPosition().getX()).get(r.getPosition().getY()).add(r);
        assertTrue(g.containsRobot(r.getPosition()));
    }

    @Test
    public void CheckIfPositionIsWithinBoardTest() {
        for(int x = 0; x<5; x++){
            for(int y = 0; y<5; y++){
                Position pos = new Position(x,y);
                boolean result = g.checkInBounds(pos);
                assertTrue(result);
            }
        }
    }

    @Test
    public void AddGridObjectToGridTest() {
        HashSet<GridObject> result = g.getGridObjectsFromPosition(new Position(1,1));
        HashSet<GridObject> expected = new HashSet<>();
        expected.add(r);
        assertEquals(expected, result);
    }

    @Test
    public void MoveGridObjectToNewPositionTest() {
        g.moveGridObjectToNewPosition(r, new Position(2,2));
        HashSet<GridObject> result = g.getGridObjectsFromPosition(new Position(2,2));

        HashSet<GridObject> expected = new HashSet<>();
        expected.add(r);

        assertEquals(expected, result);
    }
    @Test
    public void MoveRobotDirectionEastShouldIncrementX() {
        System.out.println(r.getPosition());

        g.moveRobot(r, Direction.EAST);
        System.out.println(r.getPosition());

        HashSet<GridObject> result = g.getGridObjectsFromPosition(new Position(2,1));

        assertTrue(result.contains(r));
    }
    @Test
    public void removeGridObjectFromGridTest() {
        HashSet<GridObject> expected = new HashSet<>();

        HashSet<GridObject> result = g.getGridObjectsFromPosition(new Position(1,1));
        g.removeGridObject(r);
        assertEquals(expected, result);
    }

    @Test
    public void CheckGridPositionForGridObjectTest() {
        HashSet<GridObject> result = g.getGridObjectsFromPosition(new Position(1,1));
        HashSet<GridObject> expected = new HashSet<>();
        expected.add(r);
        assertEquals(expected,result);
    }

    @Test
    public void GetNewPositionFromPositionWithDirectionTest() {
        Position expected = new Position(2,1);
        Position result = g.getNewPositionFromDirection(r,Direction.EAST);

        assertTrue(expected.equals(result));
    }
}
