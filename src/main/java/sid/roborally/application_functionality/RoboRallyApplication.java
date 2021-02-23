package sid.roborally.application_functionality;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import sid.roborally.gfx_and_ui.RoboRallyAppListener;

import java.util.HashSet;

/**
 * <h3>RoboRallyApplication</h3>
 *
 * <p>The goal of this class is to provide the program with mechanisms for
 *    passing input both when in Menu-mode or Game-mode. <br>
 *    To be a convenient way to tie together the different parts of the
 *    program.</p><br>
 *
 * <p>This class will be instantiated in Main, both to
 *    recieve and pass information and requests.<br>
 *    It will contain instances of several parts of the program as this class
 *    is central in tying together the program.</p>
 *
 * */
public class RoboRallyApplication {

    private enum InputHolder {Menu, GameRunner} //This is to know where to route input
    private InputHolder inputHolder;

    private GameRunner grunner; //Sets up and starts a game.
    private CommandLineTool clt; //This is the game-controller when the game-menus

    /**
     * <p>RoboRallyApplication constructor.</p>
     */
    public RoboRallyApplication()
    {
        inputHolder = InputHolder.Menu;

        grunner = new GameRunner();
    }

    public void setUpLibgdxApplication() //TODO: Separer ansvar og gjør det lett for RoboRallyApplication å styre Vinduet.
    {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Robo rally");
        config.setWindowedMode(500, 500);

        new Lwjgl3Application(new RoboRallyAppListener(this), config);
    }

    /*
     * Demo and Test-functionality:
     */

    public void setUpAndRunDemo()
    {
        grunner.setUpDemoGame(); //TODO: Only for now. Later we need a general solution.
        inputHolder = InputHolder.GameRunner;
        grunner.runGame();
    }

    /*
     * Functionality:
     */

    /**
     * <p>Quits/Shuts down the whole application.</p>
     */
    public void quitApplication() { Gdx.app.exit(); }

    /**
     * <p>Gives a message from the gamerunner about the game.</p>
     * @return message
     */
    public String getGameMessage() { return grunner.getAppMessage(); }

    /*
     * Tiled methods:
     */

    /**
     * <p>TiledMap instance-getter</p>
     * @return map - Local TiledMap instance.
     */
    public TiledMap getMap() { return grunner.getMap(); }

    /**
     * <p>TiledMapTileLayer (player-layer>-getter</p>
     * @return playerLayer - Local player-layer.
     */
    public TiledMapTileLayer getPlayerLayer() { return grunner.getPlayerLayer(); }

    /**
     * <p>TiledMapTileLayer (hole-layer>-getter</p>
     * @return holeLayer - Local hole-layer.
     */
    public TiledMapTileLayer getHoleLayer() { return grunner.getHoleLayer(); }

    /**
     * <p>TiledMapTileLayer (flag-layer>-getter</p>
     * @return flagLayer - Local flag-layer.
     */
    public TiledMapTileLayer getFlagLayer() { return grunner.getFlagLayer(); }

    /**
     * <p>TiledMapTileLayer (board-layer>-getter</p>
     * @return boardLayer - Local board-layer.
     */
    public TiledMapTileLayer getBoardLayer() { return grunner.getBoardLayer(); }

    /**
     * <p>Returns a set of the Player-instances associated with the current game.</p>
     * @return players - Set of Player-instances.
     */
    public HashSet<Player> getPlayers() { return grunner.getPlayers(); }

    /*
     * Keyboard-input
     */

    /**
     * <p>Tells the application that it has recieved a UP-input
     * <br>W and ArrowUp</p>
     */
    public void moveUpInput()
    { if(inputHolder == InputHolder.GameRunner) grunner.moveUpInput(); }

    /**
     * <p>Tells the application that it has recieved a DOWN-input
     * <br>S and ArrowDown</p>
     */
    public void moveDownInput()
    { if(inputHolder == InputHolder.GameRunner) grunner.moveDownInput(); }

    /**
     * <p>Tells the application that it has recieved a LEFT-input
     * <br>A and ArrowLeft</p>
     */
    public void moveLeftInput()
    { if(inputHolder == InputHolder.GameRunner) grunner.moveLeftInput(); }

    /**
     * <p>Tells the application that it has recieved a RIGHT-input
     * <br>D and ArrowRight</p>
     */
    public void moveRightInput()
    { if(inputHolder == InputHolder.GameRunner) grunner.moveRightInput(); }

    /**
     * <p>Tells the application that it has recieved a ESCAPE-input
     * <br>ESCAPE/ESC</p>
     */
    public void escapeInput() { quitApplication(); }
}
