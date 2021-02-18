package sid.roborally.game_mechanics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

public class GridTest {
    Grid g;

    @Before
    public void setUp() {
        g = new Grid(5,5);
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
        ArrayList<ArrayList<HashSet<GridObject>>> result = g.grid;
        assertEquals(expected, result);
    }

    @Test
    public void CheckIfRobotIsAtPositionTest() {
        int x = 0;
        int y = 0;
        Robot r = new Robot(x,y);
        g.grid.get(x).get(y).add(r);
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
        Robot r = new Robot(1,1);
        g.addGridObjectToGrid(r);
        HashSet<GridObject> result = g.getGridObjectsFromPosition(new Position(1,1));
        HashSet<GridObject> expected = new HashSet<>();
        expected.add(r);
        assertEquals(expected, result);
    }

    @Test
    public void MoveGridObjectToNewPositionTest() {
        Robot r = new Robot(1,1);
        g.addGridObjectToGrid(r);
        g.moveGridObjectToNewPosition(r, new Position(2,2));
        HashSet<GridObject> result = g.getGridObjectsFromPosition(new Position(2,2));
        HashSet<GridObject> expected = new HashSet<>();
        expected.add(r);
        assertEquals(expected, result);
    }
    @Test
    public void removeGridObjectFromGridTest() {
        Robot r = new Robot(1,1);
        HashSet<GridObject> expected = new HashSet<>();
        g.addGridObjectToGrid(r);
        HashSet<GridObject> result = g.getGridObjectsFromPosition(r.getPosition());
        //assertFalse(result.equals(expected));
        g.removeGridObjectFromGrid(r);
        assertEquals(expected, result);
    }
    @Test
    public void CheckGridPositionForGridObjectTest() {
        Robot r = new Robot(1,1);
        g.addGridObjectToGrid(r);
        HashSet<GridObject> result = g.getGridObjectsFromPosition(r.getPosition());
        HashSet<GridObject> expected = new HashSet<>();
        expected.add(r);
        assertEquals(expected,result);
    }
}
