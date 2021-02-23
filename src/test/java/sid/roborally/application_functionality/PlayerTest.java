package sid.roborally.application_functionality;

import org.junit.Before;
import org.junit.Test;
import sid.roborally.game_mechanics.grid.Position;

import static org.junit.Assert.assertTrue;

public class PlayerTest {

    Player p1, p2, p3;

    @Before
    public void setUp()
    {
        p1 = new Player(new Position(1,1), false);
        p2 = new Player(new Position(2,2), false);
        p3 = new Player(new Position(3,3), false);

        p1.setLocal();
        p2.setAI();
        p3.setExternal();
    }

    @Test
    public void playerKnowsItsOwnerLocation()
    {
        assertTrue(p1.isLocal());
        assertTrue(p2.isAI());
        assertTrue(p3.isExternal());
    }
}
