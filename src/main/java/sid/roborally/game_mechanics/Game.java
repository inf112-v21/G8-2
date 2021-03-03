package sid.roborally.game_mechanics;

import sid.roborally.application_functionality.Player;
import sid.roborally.game_mechanics.card.Card;
import sid.roborally.game_mechanics.card.CardDealer;
import sid.roborally.game_mechanics.grid.Flag;
import sid.roborally.game_mechanics.grid.Grid;
import sid.roborally.game_mechanics.grid.GridObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * <h3>Game</h3>
 * <p>The goal of this class is to provide a mechanism for controlling and monitoring a single
 *    instance of a game.<br>
 *    It will also have functionalities for the different parts of the RoboRally game to run the
 *    game as it is specified to work.<br>
 *    The Game-instance will also be able to pass information to external holder of this instance, and
 *    to recieve and execute requests toward the running of the game.</p><br>
 *
 * <p>In-game it will communicate internally with Player-, Grid- and GridObject-instances.
 *    Externally it will communicate with GameRunner</p>
 */
public class Game {

    private HashMap<Flag, Player> flags_have_player; //TODO: Flags in game, player can only be added if he already have the earlier flags
    private ArrayList<Flag> flags; //TODO: Flags in the order to be moved to
    private HashSet<Player> players;
    private Grid grid; //TODO: Connect this
    private CardDealer dealer;

    //TODO: DEALER CLASS INSTANCE OG UTDELING OG VELGING AV KORT OG KJØRING AV KORT

    /**
     * <p>Game constructor.</p>
     */
    public Game()
    {
        players = new HashSet<>();
        dealer = new CardDealer();
        flags = new ArrayList<>();
    }

    /*
     * * * * * Editing game-elements.
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

    /**
     * <p>Adds a GridObject-instance to the Game's Grid.</p>
     * @param go GridObject
     */
    public void addGridObjectToGrid(GridObject go) { grid.addGridObject(go);}

    /*
     * Phase-methods
     */

    public void runRound()
    {
        //TODO: DEAL CARDS

        //TODO: GET PLAYER CHOSEN CARDS

        //TODO: MOVE ROBOTS BASED ON CHOSEN CARDS

        //TODO: MOVE BOARD ELEMENTS (CONVEYOR, GEARS)

        //TODO: CALCULATE DAMAGE (LAZERS)

        //TODO: OTHER CHECKS
    }

    /* Dealing methods */

    public void dealToPlayers()
    {
        for(Player p : players)
        {
            ArrayList<Card> giveCards = new ArrayList<>();
            for(int i = 0; i < 9; i++) giveCards.add(dealer.deal());
            p.giveProgramCards(giveCards);
        }
        dealer.resetDeck();
    }

    public void addFlag(Flag f) { flags.add(f); }

    public ArrayList<Flag> getFlags() { return flags; }

    /*
     * * * * * Player Methods:
     */

    /**
     * <p>Adds a player to the Game's Player-set.</p>
     * @param p Player-instance.
     */
    public void addPlayer(Player p)
    {
        players.add(p);
        grid.addGridObject(p.getRobot());
    }

    /**
     * <p>Checks if Game's Player-set has Player-instance.</p>
     * @param p Player
     * @return boolean Game has Player
     */
    public boolean hasPlayer(Player p)
    { return players.contains(p); }

    /**
     * <p>Removes Player-instance from game.</p>
     * @param p Player-instance.
     */
    public void remove(Player p)
    { players.remove(p); }

    /**
     * <p>Gets the Game's Player-set.</p>
     * @return players - Player set.
     */
    public HashSet<Player> getPlayers()
    { return players; }

    /**
     * <p>Gets the local player instance (not AI or External)</p>
     */
    public Player getLocal()
    {
        for(Player p : players) if(p.isLocal()) return p;
        //Game should always have a local player.
        return null;
    }

    /**
     * <p>Moves robot in direction in grid</p>
     */
    public void movePlayerRobot(Player p, Direction dir)
    {
        grid.moveRobot(p.getRobot(), dir);
        updatePlayerStatus(p); //TODO: In the future updateRobotStatus should only be called when it has finished its moves.
    }

    /**
     * <p>When something changes with Player, its instance should update
     *    its information.</p>
     * @param p Player-instance
     */
    private void updatePlayerStatus(Player p)
    {
        /* Check for possible damage */
        if(grid.positionHasHole(p.getRobot().getPosition()))
        {
            p.getRobot().setIsDead(true); //TODO: Foreløpig så har player bare én gang robot kan ødelegges.
            p.killPlayer();
        }

        /* Check if possible flag (for now this will check for "a" flag and give a win */
        if(grid.positionHasFlag(p.getRobot().getPosition())) //TODO: Midlertidig
        {
            p.getRobot().setHasWon(true);
            p.playerWon();
        }
    }

}
