package sid.roborally.application_functionality;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import sid.roborally.game_mechanics.Position;

import java.util.HashSet;

//TODO: Make mechanism to pass tile information to game to set up grid

/**
 * @author Daniel-J
 *
 * ApplicationClass that wraps around program and controls it's logic
 */
public class RoboRallyApplication {

    private enum InputHolder {Menu, GameRunner} //This is to know where to route input
    private InputHolder inputHolder;

    private GameRunner grunner; //Sets up and starts a game.

    /*
    In the beginning this application will only launch a demo-game (hard-coded for now)
     */
    public RoboRallyApplication()
    {
        inputHolder = InputHolder.Menu;

        grunner = new GameRunner();
        grunner.setUpDemoGame(); //TODO: Only for now
        inputHolder = InputHolder.GameRunner;
        grunner.runGame();
    }

    /*
     * Tiled methods
     */
    public TiledMap getMap() { return grunner.getMap(); }

    public TiledMapTileLayer getPlayerLayer() { return grunner.getPlayerLayer(); }

    public TiledMapTileLayer getHoleLayer() { return grunner.getHoleLayer(); }

    public TiledMapTileLayer getFlagLayer() { return grunner.getFlagLayer(); }

    public TiledMapTileLayer getBoardLayer() { return grunner.getBoardLayer(); }

    public HashSet<Player> getPlayers() { return grunner.getPlayers(); }

    /*
     * Keyboard-input
     */

    /**
     * W and ArrowUp
     */
    public void moveUpInput()
    { if(inputHolder == InputHolder.GameRunner) grunner.moveUpInput(); }

    /**
     * S and ArrowDown
     */
    public void moveDownInput()
    { if(inputHolder == InputHolder.GameRunner) grunner.moveDownInput(); }

    /**
     * A and ArrowLeft
     */
    public void moveLeftInput()
    { if(inputHolder == InputHolder.GameRunner) grunner.moveLeftInput(); }

    /**
     * D and ArrowRight
     */
    public void moveRightInput()
    { if(inputHolder == InputHolder.GameRunner) grunner.moveRightInput(); }

    /**
     * Escape tapped
     */
    public void escapeInput() { quitApplication(); }

    public void quitApplication() { Gdx.app.exit(); }

}
