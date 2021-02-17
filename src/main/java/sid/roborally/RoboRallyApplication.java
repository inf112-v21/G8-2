package sid.roborally;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import sid.roborally.gamelogic.Position;

import java.util.HashSet;

//TODO: Make mechanism to pass TiledMap-information on to Game-instance
//TODO: Make mechanism to pass Game-information on to AppListener to render
//TODO: Make mechanism to pass input from AppListener to Game-instance
//TODO: Players must have a layer each
//TODO:

/**
 * @author Daniel-J
 *
 * ApplicationClass that wraps around program and controls it's logic
 */
public class RoboRallyApplication {

    //Map layers
    private TiledMap map;
    private TiledMapTileLayer board, player_tl, hole, flag; //When these layers are edited the gui is edited.

    //TODO: Player-position placeholder for Player-List
    private HashSet<Position> playerPositions;

    private HashSet<Player> players; //TODO: Game will control this in the future?

    /*
    In the beginning this application will only launch a demo-game (hard-coded for now)
     */
    public RoboRallyApplication()
    {
        players = new HashSet<>();
        loadDemoSettings();
    }

    /*
    Loading demo-settings (will be hard-coded for now)
     */
    private void loadDemoSettings()
    {
        map = new TmxMapLoader().load("assets/example.tmx");
        board = (TiledMapTileLayer) map.getLayers().get("Board");
        player_tl = (TiledMapTileLayer) map.getLayers().get("Player");
        hole = (TiledMapTileLayer) map.getLayers().get("Hole");
        flag = (TiledMapTileLayer) map.getLayers().get("Flag");

        players.add(new Player(new Position(1,2)));
        Player deadPlayer = new Player(new Position(3,3));
        deadPlayer.killPlayer();
        players.add(deadPlayer);
    }

    public TiledMap getMap() { return map; }

    public TiledMapTileLayer getPlayerLayer() { return player_tl; }

    public TiledMapTileLayer getHoleLayer() { return hole; }

    public TiledMapTileLayer getFlagLayer() { return flag; }

    public TiledMapTileLayer getBoardLayer() { return board; }

    public HashSet<Player> getPlayers() { return players; }

    /*
     * Keyboard-input
     */

    /**
     * W and ArrowUp
     */
    public void moveUpInput() {}

    /**
     * S and ArrowDown
     */
    public void moveDownInput() {}

    /**
     * A and ArrowLeft
     */
    public void moveLeftInput() {}

    /**
     * D and ArrowRight
     */
    public void moveRightInput() {}

    /**
     * Escape tapped
     */
    public void escapeInput() { quitApplication(); }

    public void quitApplication() { Gdx.app.exit(); }

}
