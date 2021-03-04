package sid.roborally.application_functionality;

import junit.framework.AssertionFailedError;
import org.junit.Before;
import org.junit.Test;
import sid.roborally.game_mechanics.Direction;
import sid.roborally.game_mechanics.Game;
import sid.roborally.game_mechanics.FlagIDComparator;
import sid.roborally.game_mechanics.grid.ArchiveMarker;
import sid.roborally.game_mechanics.grid.Flag;
import sid.roborally.game_mechanics.grid.Position;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * <h3>GameTest</h3>
 * <p>This class tests the Game-class to check that all
 *    it features work as intended.</p>
 */
public class GameTest {

    Game game;
    Player p1, p2, p3;

    @Before
    public void addElements()
    {
        game = new Game();
        game.newGrid(5,5);

        p1 = new Player(new Position(1,1), false);
        p2 = new Player(new Position(2,2), false);
        p3 = new Player(new Position(3,3), false);
    }


    /**
     * <p>Checks that a game-instance will keep track of its players.</p>
     */
    @Test
    public void gameKeepsTrackOfPlayers()
    {
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);

        assertTrue(game.hasPlayer(p2));

        game.remove(p2);

        assertFalse(game.hasPlayer(p2));
    }

    /**
     * <p>Checks to see if game can move the player's robot in grid.</p>
     */
    @Test
    public void canMovePlayerRobot()
    {
        game.addPlayer(p1); //Player's robot should have position (1,1)
        assertEquals(new Position(1,1).toString(),
                p1.getRobot().getPosition().toString());

        //Moving player's robot.
        game.movePlayerRobot(p1, Direction.NORTH);
        game.movePlayerRobot(p1, Direction.EAST);
        game.movePlayerRobot(p1, Direction.EAST);

        //New position should be (1+1+1, 1+1) = (3,2)
        assertEquals(new Position(3,2).toString(),
                p1.getRobot().getPosition().toString());
    }

    /**
     * <p>Checks to see that the game can retrieve a local player.</p>
     */
    @Test
    public void canGetLocalPlayer()
    {
        game.addPlayer(p1);
        game.addPlayer(p2);
        game.addPlayer(p3);

        p2.setLocal();

        assertFalse(game.getLocal().equals(p1));
        assertTrue(game.getLocal().equals(p2));
        assertFalse(game.getLocal().equals(p3));

        p2.setAI();
        p3.setLocal();

        assertFalse(game.getLocal().equals(p1));
        assertFalse(game.getLocal().equals(p2));
        assertTrue(game.getLocal().equals(p3));
    }

    @Test
    public void AddFlagToFlagsListTest() {
        Flag f = new Flag(0,0, 1);
        game.addFlag(f);
        assertTrue(game.getFlags().contains(f));
    }

    @Test
    public void sortFlagsListBasedOffOfPriority() {
        ArrayList<Flag> expected = new ArrayList<>();
        
        Flag f1 = new Flag(0,0,1);
        Flag f2 = new Flag(1,1,2);
        Flag f3 = new Flag(2,2,3);

        expected.add(f1);
        expected.add(f2);
        expected.add(f3);

        game.addFlag(f3);
        game.addFlag(f1);
        game.addFlag(f2);

        assertNotEquals(expected,game.getFlags());

        game.getFlags().sort(new FlagIDComparator());
        assertEquals(expected, game.getFlags());
    }

    @Test
    public void addArchiveMarkerAddsArchiveMarkerTest() {
        ArchiveMarker am = new ArchiveMarker(0,1,1);
        game.addArchiveMarker(am);
        assertTrue(game.getArchiveMarkers().contains(am));
    }
}
