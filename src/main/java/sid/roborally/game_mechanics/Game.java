package sid.roborally.game_mechanics;

import sid.roborally.application_functionality.Player;
import sid.roborally.game_mechanics.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * <p>The responsibility of game is to keep track of a single running game-instance.
 *    In-game it will communicate internally with Player-, Grid- and GridObject-instances.
 *    Externally it will communicate with GameRunner</p>
 */
public class Game {


    private HashMap<Flag, Player> flags_have_player; //TODO: Flags in game, player can only be added if he already have the earlier flags
    private ArrayList<Flag> flags; //TODO: Flags in the order to be moved to
    private HashSet<Player> players;
    private Grid grid; //TODO: Connect this


    public Game()
    {
        players = new HashSet<>();
    }

    /*
     * Adding game-elements.
     */

    /**
     * <p>Give Game a new empty-grid</p>
     * @param width grid-height
     * @param height grid-height
     */
    public void newGrid(int width, int height)
    {
        grid = new Grid(width, height);
    }

    public void addGridObjectToGrid(GridObject go) { grid.addGridObjectToGrid(go);}

    public void printGrid() { System.out.println(grid.toString()); }

    /**
     * <p>Method to add a element to grid when setting up game.</p>
     *
     * @param gridObject Object to be added to game
     */
    public void addGameElement(GridObject gridObject)
    { grid.addGridObjectToGrid(gridObject); }

    /*
     * Player Methods
     */
    public void addPlayer(Player p)
    { players.add(p); }

    public boolean hasPlayer(Player p)
    { return players.contains(p); }

    public void remove(Player p)
    { players.remove(p); }

    public HashSet<Player> getPlayers()
    { return players; }

    //TODO: public void moveRobot(Robot r, Direction d){grid.moveRobot(r,d);}

    public Grid getGrid() { return grid; }

    /**
     * <p>Gets the local player instance (not AI or External)</p>
     */
    public Player getLocal()
    {
        for(Player p : players) if(p.isLocal()) return p;
        //Game should always have a local player.
        return null;
    }

}
