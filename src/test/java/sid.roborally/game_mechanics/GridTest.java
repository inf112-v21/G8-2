package sid.roborally.game_mechanics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

public class GridTest {
    Grid g;

    @Test
    public void CreateEmptyGridTest() {
        Grid g = new Grid(5,5);

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
        g = new Grid(5,5);
        g.grid.get(x).get(y).add(r);
        assertTrue(g.containsRobot(r.getPosition()));
    }

    @Test
    public void CheckIfPositionIsWithinBoardTest() {
        g = new Grid(5,5);
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
        g = new Grid(5,5);
        Robot r = new Robot(1,1);
        g.addGridObjectToGrid(r);
        HashSet<GridObject> result = g.getGridObjectsFromPosition(new Position(1,1));
        HashSet<GridObject> expected = new HashSet<>();
        expected.add(r);
        assertEquals(expected, result);
    }

    @Test
    public void MoveGridObjectToNewPositionTest() {
        g = new Grid(5,5);
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
        g = new Grid(5,5);
        Robot r = new Robot(1,1);
        HashSet<GridObject> expected = new HashSet<>();
        g.addGridObjectToGrid(r);
        HashSet<GridObject> result = g.getGridObjectsFromPosition(r.getPosition());
        g.removeGridObjectFromGrid(r);

        assertEquals(expected, result);
    }
    /*
    @Test
    public void CheckGridPositionForGridObjectTest() {
        g = new Grid(5,5);
        Robot r = new Robot(1,1);
        HashSet<GridObject> result = g.getFromPosition(r.getPosition());
        HashSet<GridObject> expected = new HashSet<>();
        expected.add(r);
        assertEquals(expected,result);
    }

     */
}
