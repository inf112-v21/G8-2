package sid.roborally.application_functionality;

import org.junit.Before;
import org.junit.Test;
import sid.roborally.game_mechanics.Game;
import sid.roborally.game_mechanics.Position;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {

    Game game;
    Player p1, p2, p3;

    @Before
    public void addElements()
    {
        game = new Game();

        p1 = new Player(new Position(1,1), false);
        p2 = new Player(new Position(2,2), false);
        p3 = new Player(new Position(3,3), false);
    }


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
}
