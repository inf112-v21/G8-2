package sid.roborally.application_functionality;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import sid.roborally.application_functionality.reference.Map;
import sid.roborally.application_functionality.reference.TileIDReference;
import sid.roborally.game_mechanics.ArchiveMarkerIDComparator;
import sid.roborally.game_mechanics.Direction;
import sid.roborally.game_mechanics.FlagIDComparator;
import sid.roborally.game_mechanics.Game;
import sid.roborally.game_mechanics.card.Card;
import sid.roborally.game_mechanics.card.CardAction;
import sid.roborally.game_mechanics.grid.*;
import sid.roborally.gfx_and_ui.screens.GameScreen;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * <h3>GameRunner</h3>
 * <p>The goal of this class is to provide mechanisms for creating, setting-up and running
 * instances of Game.<br>
 * This class will also communicate information and requests internal to external and external to
 * internal; also executing requests toward the current Game-instance<br>
 * </p><br>
 * <p>To create a game you: 1. Give it a texture. 2. Adjust setup. 3.Add players. 4. Start it. A game in 1.2.3..4</p>
 * <p>GameRunner will be instantiated in RRApplication.</p>
 * @author Danile Janols
 */
public class GameRunner{

    /* Tile elements */
    private TiledMap map;
    private TiledMapTileLayer
            board_layer,
            player_layer,
            hole_layer,
            flag_layer,
            archiveMarker_layer; //When these layers are edited the gui is edited.

    private HashSet<Player> players;

    /* The game that is run */
    private Game game;
    private GameScreen gameScreen;
    private boolean inputActive;

    /**
     * <p>GameRunner constructor.</p>
     */
    public GameRunner() {
        game = new Game();
        game.giveGameRunner(this);
        inputActive = true;
        players = new HashSet<>();
    }

    /*
     * * * * * Game-running methods start
     *
     * These methods are used for running the game and communicating
     * between GUI and Game
     */

    /**
     * <p>This method sets up a round. It deals the cards two the players and
     * then updates GUI with its given cards.</p>
     */
    public void setUpRound() {
        game.dealToPlayers();
        gameScreen.giveCards(game.getPlayerGivenCards(getLocal()));
        gameScreen.updateGUI();
    }

    /**
     * <p>This method runs the cards chosen. If the game isn't over, it will call setUpRound again.</p>
     */
    public void runRound() {
        game.runRound();
    }

    /*
     * * * * * Game-running methods end
     */

    public void giveGameScreen(GameScreen gs) { gameScreen = gs; }

    public Player getLocal() { return game.getLocal(); }
    public int getBoardWidth() { return board_layer.getWidth(); }
    public int getBoardHeight() { return board_layer.getHeight(); }

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
        archiveMarker_layer = (TiledMapTileLayer) map.getLayers().get("PlayerStart");

        /* Adjust setup in case a grid based on a previous map already existed */
        adjustSetup();
    }

    /*
     * * * * * Game-setup methods
     */

    public void setUpGame(Map map) {
        /* First tell game what texture it should use*/
        setGameTexture(map.getMapPath());

        /* Adjust setup based on map chosen */
        adjustSetup();

        /* Place the players onto the map */
        placePlayers(players);
    }

    /**
     * <p>Places players on their start-positions according to their startID</p>
     */
    private void placePlayers(HashSet<Player> players) {
        for(Player p : players) {
            ArchiveMarker am = game.getArchiveMarkers()
                    .get(p.getStartID()-1); //PlayerID is 1,2,3, but first archive element is in the 0 index.
            Position startPosition = am.getPosition(); //Starts at marker start-position
            p.giveRobotStartPosition(startPosition);
            p.getRobot().setArchiveMarker(am); //Giving robot the archive marker
            game.addPlayer(p);
        }
    }

    /**
     * <p>Will be called when the Game-instance needs to set-up itself again.
     * Grid and layout will be reset to current setting. Player-instances will
     * not be removed from game.</p>
     */
    private void adjustSetup() {
        game.newGrid(board_layer.getWidth(), board_layer.getHeight());
        giveMapDataToGrid();
    }

    /**
     * <p>This method will go trough the map-elements and add them to the grid-instance in game</p>
     */
    private void giveMapDataToGrid() {
        for (int x = 0; x < board_layer.getWidth(); x++) {
            for (int y = 0; y < board_layer.getHeight(); y++) {
                /* Adding possible game-elements to grid */
                addPossibleHoleToGrid(x, y);
                addPossibleFlagToGrid(x, y);
                addPossibleArchiveToGrid(x, y);
            }
        }
        /* When everything is added some elements must also be sorted */
        game.getFlags().sort(new FlagIDComparator());
        game.getArchiveMarkers().sort(new ArchiveMarkerIDComparator());
    }

    /**
     * <p>If position has a Hole, hole is added to grid.</p>
     * @param x x-position
     * @param y y-position
     */
    private void addPossibleHoleToGrid(int x, int y)
    { if (hole_layer.getCell(x, y) != null) game.addGridObjectToGrid(new Hole(x, y)); }

    /**
     * <p>If position has a Flag, flag is added to grid.</p>
     * @param x x-position
     * @param y y-position
     */
    private void addPossibleFlagToGrid(int x, int y) {
        /* Adding flag to Game */
        if (flag_layer.getCell(x,y)==null) return;

        int flagIndex = flag_layer.getCell(x, y).getTile().getId();
        Flag f = new Flag(x, y, TileIDReference.flagIndexToId(flagIndex));
        game.addFlag(f);
    }

    /**
     * <p>If position has an Archive, archive is added to grid.</p>
     * @param x x-position
     * @param y y-position
     */
    private void addPossibleArchiveToGrid(int x, int y) {
        if(archiveMarker_layer.getCell(x, y) == null) return;
        /* Adding marker to game */
        int index = archiveMarker_layer.getCell(x,y).getTile().getId();
        ArchiveMarker am = new ArchiveMarker(x,y, TileIDReference.archiveIndexToID(index));
        game.addArchiveMarker(am);
    }

    /*
     * * * * * Game-running
     */

    /**
     * This method will run the game that has been created, and loop until it's over
     */
    public void runGame() {
    }

    /**
     * <p>This method will be called when something happened and the application has to check
     *    if anything should be handled from the game and passed out</p>
     */
    private void somethingHappenedToGame() {
        /* Checking for possible win or loss */
        if(game.getLocal().hasWon())
            inputActive = false;
        if(game.getLocal().isDead())
            inputActive = false;
    }

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

    /*
     * * * * * Player-related-methods
     */

    public void addPlayer(Player p) { players.add(p); }

    /**
     * <p>Returns a set with the Player-instances associated with the current Game-instance.</p>
     * @return players - Set of Players
     */
    public HashSet<Player> getPlayers() { return players; }

    /**
     * Resets the local position of the given Player-instance in the player-layer.
     * @param p Player
     */
    public void resetPlayerTexture(Player p)
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
        somethingHappenedToGame();
        if(!inputActive) return;
        resetPlayerTexture(game.getLocal());
        game.movePlayerRobot(game.getLocal(), Direction.NORTH,1);
    }

    /**
     * <p>Tells the gamerunner that it has recieved a DOWN-input
     * <br>S and ArrowDown</p>
     */
    public void moveDownInput()
    {
        somethingHappenedToGame();
        if(!inputActive) return;
        resetPlayerTexture(game.getLocal());
        game.movePlayerRobot(game.getLocal(), Direction.SOUTH,1);
    }

    /**
     * <p>Tells the gamerunner that it has recieved a LEFT-input
     * <br>A and ArrowLeft</p>
     */
    public void moveLeftInput()
    {
        somethingHappenedToGame();
        if(!inputActive) return;
        resetPlayerTexture(game.getLocal());
        game.movePlayerRobot(game.getLocal(), Direction.WEST,1);
    }

    /**
     * <p>Tells the gamerunner that it has recieved a RIGHT-input
     * <br>D and ArrowRight</p>
     */
    public void moveRightInput()
    {
        somethingHappenedToGame();
        if(!inputActive) return;
        resetPlayerTexture(game.getLocal());
        game.movePlayerRobot(game.getLocal(), Direction.EAST,1);
    }

    /**
     * <p>Tells the gamerunner that it has recieved a ROTATE LEFT-input
     * <br>Q</p>
     */
    public void rotateLeftInput()
    {
        somethingHappenedToGame();
        if(!inputActive) return;
        resetPlayerTexture(game.getLocal());
        game.turnPlayerRobot(game.getLocal(), CardAction.TURN_LEFT);
        System.out.println("Now facing:" + game.getLocal().getRobot().getOrientation());
    }

    /**
     * <p>Tells the gamerunner that it has recieved a ROTATE RIGHT-input
     * <br>E</p>
     */
    public void rotateRightInput()
    {
        somethingHappenedToGame();
        if(!inputActive) return;
        resetPlayerTexture(game.getLocal());
        game.turnPlayerRobot(game.getLocal(), CardAction.TURN_RIGHT);
        System.out.println("Now facing:" + game.getLocal().getRobot().getOrientation());
    }
    /**
     * <p>Tells the gamerunner that it has recieved a STEP 1 FORWARD-input
     * <br>F</p>
     */
    public void stepOneForwardInput()
    {
        somethingHappenedToGame();
        if(!inputActive) return;
        resetPlayerTexture(game.getLocal());
        game.movePlayerRobot(game.getLocal(),game.getLocal().getRobot().getOrientation(),1);
    }

    public void giveGameCards(ArrayList<Card> chosenCards) {
        game.setPlayerChosenCards(getLocal(),chosenCards);
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }
}
