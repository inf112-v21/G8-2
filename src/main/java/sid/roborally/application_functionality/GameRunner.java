package sid.roborally.application_functionality;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import sid.roborally.game_mechanics.*;

import java.util.HashSet;

/**
 * Sets up a and runs game. Input and output from game is passed here.
 */
public class GameRunner {

    private TiledMap map;
    private TiledMapTileLayer
            board_layer,
            player_layer,
            hole_layer,
            flag_layer; //When these layers are edited the gui is edited.

    private Game game;
    
    public GameRunner() 
    { game = new Game(); }
    
    public void setUpGameWithTexture(String texturePath)
    {
        map = new TmxMapLoader().load(texturePath);
        board_layer = (TiledMapTileLayer) map.getLayers().get("Board");
        player_layer = (TiledMapTileLayer) map.getLayers().get("Player");
        hole_layer = (TiledMapTileLayer) map.getLayers().get("Hole");
        flag_layer = (TiledMapTileLayer) map.getLayers().get("Flag");
    }

    /**
     * <p>This method will go trough the map-elements and add them to the grid-element in game</p>
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

    public void setUpDemoGame()
    {
        setUpGameWithTexture("assets/example.tmx");

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
    public void somethingHappenedToGame()
    {

    }

    /*
     * Tiled methods
     */
    public TiledMap getMap() { return map; }

    public TiledMapTileLayer getPlayerLayer() { return player_layer; }

    public TiledMapTileLayer getHoleLayer() { return hole_layer; }

    public TiledMapTileLayer getFlagLayer() { return flag_layer; }

    public TiledMapTileLayer getBoardLayer() { return board_layer; }

    public HashSet<Player> getPlayers() { return game.getPlayers(); }

    /*
     * Keyboard-input
     */

    private void resetPlayerTexture()
    {
        Position localPlayerPos = game.getLocal().getRobot().getPosition();
        player_layer.setCell(localPlayerPos.getX(), localPlayerPos.getY(), null);
    }

    /**
     * W and ArrowUp
     */
    public void moveUpInput()
    {
        game.printGrid();
        resetPlayerTexture();
        //TODO: game.moveRobot(game.getLocal().getRobot(), Direction.NORTH);
        game.getLocal().moveUp();
    }

    /**
     * S and ArrowDown
     */
    public void moveDownInput()
    {
        resetPlayerTexture();
        game.getLocal().moveDown();
    }

    /**
     * A and ArrowLeft
     */
    public void moveLeftInput()
    {
        resetPlayerTexture();
        game.getLocal().moveLeft();
    }

    /**
     * D and ArrowRight
     */
    public void moveRightInput()
    {
        resetPlayerTexture();
        game.getLocal().moveRight();
    }

}
