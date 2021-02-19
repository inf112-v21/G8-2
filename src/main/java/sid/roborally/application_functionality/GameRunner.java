package sid.roborally.application_functionality;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import sid.roborally.game_mechanics.*;

import java.util.HashSet;

/**
 * <h3>GameRunner</h3>
 * <p>The goal of this class is to provide mechanisms for creating, setting-up and running
 * instances of Game.<br>
 * This class will also communicate information and requests internal to external and external to
 * internal; also executing requests toward the current Game-instance<br>
 * </p><br>
 * <p>GameRunner will be instantiated in RoboRallyApplication and will itself contain instances of
 *    Game, TiledMap and TiledMapTileLayer.</p>
 */
public class GameRunner {

    private TiledMap map;
    private TiledMapTileLayer
            board_layer,
            player_layer,
            hole_layer,
            flag_layer; //When these layers are edited the gui is edited.

    private Game game;

    /**
     * <p>GameRunner constructor.</p>
     */
    public GameRunner() {
        game = new Game();
    }

    /**
     * <p>Sets the currentGameTexture</p>
     *
     * @param texturePath Path to chosen texture.
     */
    public void setGameTexture(String texturePath) {
        map = new TmxMapLoader().load(texturePath);
        board_layer = (TiledMapTileLayer) map.getLayers().get("Board");
        player_layer = (TiledMapTileLayer) map.getLayers().get("Player");
        hole_layer = (TiledMapTileLayer) map.getLayers().get("Hole");
        flag_layer = (TiledMapTileLayer) map.getLayers().get("Flag");

        adjustSetup();
    }

    /**
     * <p>Will be called when the Game-instance needs to set-up itself again.
     * Grid and layout will be reset to current setting. Player-instances
     * not be removed from game.</p>
     */
    private void adjustSetup()
    {
        game.newGrid(board_layer.getWidth(), board_layer.getHeight());
        giveMapDataToGrid();
    }

    /**
     * <p>This method will go trough the map-elements and add them to the grid-instance in game</p>
     */
    private void giveMapDataToGrid()
    {
        for(int x = 0; x < board_layer.getWidth(); x++)
            for(int y = 0; y < board_layer.getHeight(); y++)
            {
                if(hole_layer.getCell(x,y) != null) game.addGridObjectToGrid(new Hole(x,y));
                if(flag_layer.getCell(x,y) != null) game.addGridObjectToGrid(new Flag(x,y, 0)); //TODO: Find a better way to assign flags their ID.
            }
    }

    /**
     * Sets up a demo-game
     */
    public void setUpDemoGame()
    {
        setGameTexture("assets/example.tmx");

        Player demoPlayer = new Player(new Position(1,1), true);
        demoPlayer.setLocal();

        game.newGrid(5,5);
        game.addPlayer(demoPlayer);
    }

    /**
     * This method will run the game that has been created.
     */
    public void runGame()
    {
        //TODO: Make mechanism for running game. Game should only be affected when a user does something (local, ai or external)
    }

    /**
     * <p>This method will be called when something happened and the application has to check
     *    if anything should be handled from the game and passed out</p>
     */
    public void somethingHappenedToGame() {}

    /*
     * * * * * Tiled methods:
     */

    /**
     * <p>Returns local TiledMap instance</p>
     * @return map - TiledMap instance
     */
    public TiledMap getMap() { return map; }

    /**
     * <p>Returns local TiledMapTileLayer (player-layer) instance</p>
     * @return player_layer - TiledMapTileLayer instance
     */
    public TiledMapTileLayer getPlayerLayer() { return player_layer; }

    /**
     * <p>Returns local TiledMapTileLayer (hole-layer) instance</p>
     * @return hole_layer - TiledMapTileLayer instance
     */
    public TiledMapTileLayer getHoleLayer() { return hole_layer; }

    /**
     * <p>Returns local TiledMapTileLayer (flag-layer) instance</p>
     * @return flag_layer - TiledMapTileLayer instance
     */
    public TiledMapTileLayer getFlagLayer() { return flag_layer; }

    /**
     * <p>Returns local TiledMapTileLayer (board-layer) instance</p>
     * @return board_layer - TiledMapTileLayer instance
     */
    public TiledMapTileLayer getBoardLayer() { return board_layer; }


    /*
     * * * * * Player-related-methods
     */

    /**
     * <p>Returns a set with the Player-instances associated with the current Game-instance.</p>
     * @return players - Set of Players
     */
    public HashSet<Player> getPlayers() { return game.getPlayers(); }

    /**
     * Resets the local position of the given Player-instance in the player-layer.
     * @param p
     */
    private void resetPlayerTexture(Player p)
    {
        Position localPlayerPos = p.getRobot().getPosition();
        player_layer.setCell(localPlayerPos.getX(), localPlayerPos.getY(), null);
    }

    /*
     * * * * * Keyboard-input:
     */

    /**
     * <p>Tells the gamerunner that it has recieved a UP-input
     * <br>W and ArrowUp</p>
     */
    public void moveUpInput()
    {
        resetPlayerTexture(game.getLocal());
        //TODO: game.moveRobot(game.getLocal().getRobot(), Direction.NORTH);
        //game.getLocal().moveUp();
        game.moveRobot(game.getLocal().getRobot(), Direction.NORTH);
    }

    /**
     * <p>Tells the gamerunner that it has recieved a DOWN-input
     * <br>S and ArrowDown</p>
     */
    public void moveDownInput()
    {
        resetPlayerTexture(game.getLocal());
        //game.getLocal().moveDown();
        game.moveRobot(game.getLocal().getRobot(), Direction.SOUTH);
    }

    /**
     * <p>Tells the gamerunner that it has recieved a LEFT-input
     * <br>A and ArrowLeft</p>
     */
    public void moveLeftInput()
    {
        resetPlayerTexture(game.getLocal());
        //game.getLocal().moveLeft();
        game.moveRobot(game.getLocal().getRobot(), Direction.WEST);
    }

    /**
     * <p>Tells the gamerunner that it has recieved a RIGHT-input
     * <br>D and ArrowRight</p>
     */
    public void moveRightInput()
    {
        resetPlayerTexture(game.getLocal());
        //game.getLocal().moveRight();
        game.moveRobot(game.getLocal().getRobot(), Direction.EAST);
    }
}