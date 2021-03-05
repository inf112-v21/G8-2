package sid.roborally.game_mechanics;

import sid.roborally.application_functionality.Player;

import sid.roborally.game_mechanics.grid.ArchiveMarker;

import sid.roborally.application_functionality.comm_line.GameCommandLine;
import sid.roborally.game_mechanics.card.*;

import sid.roborally.game_mechanics.grid.Flag;
import sid.roborally.game_mechanics.grid.Grid;
import sid.roborally.game_mechanics.grid.GridObject;
import sid.roborally.game_mechanics.grid.Robot;

import java.io.Serializable;
import java.util.*;


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
public class Game implements Runnable {


    private HashMap<Flag, Player> flags_have_player;
    private ArrayList<Flag> flags;
    private HashSet<Player> players;
    private Grid grid;
    private CardDealer dealer;
    private ArrayList<ArchiveMarker> archiveMarkers;
    private boolean gameOver;

    /* Phase-variables */
    private HashMap<Player, List<Card>> givenProgramCards; //Associating program-cards with given players.
    private HashMap<Player, ArrayList<Card>> chosenProgramCards;

    /* Constants */
    private static int DEAL_CARD_AMOUNT = 5;

    /**
     * <p>Game constructor.</p>
     */
    public Game()
    {
        players = new HashSet<>();
        dealer = new CardDealer();
        flags = new ArrayList<>();

        archiveMarkers = new ArrayList<>();
        givenProgramCards = new HashMap<>();
        chosenProgramCards = new HashMap<>();
        gameOver = false;
    }

    /* Run game-thread */
    @Override
    public void run() {
        runGame();
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

    /**
     * <p>Runs game's gameloop.</p>
     */
    private void runGame() {
        while(!gameOver) {
            runRound();
            checkIfLocalHasWonOrLost();
        }
        displayExitMessage();
    }

    /**
     * <p>Checks if local host has won or lost, if any then game-over.</p>
     */
    private void checkIfLocalHasWonOrLost() {
        if(getLocal().hasWon()) gameOver = true;
        if(getLocal().isDead()) gameOver = true;
    }

    /**
     * <p>Calls GameCommandLine and asks it to display an exit-message.</p>
     */
    private void displayExitMessage() {
        if(getLocal().hasWon()) GameCommandLine.printLocalEnd(true);
        else GameCommandLine.printLocalEnd(false);
    }

    /**
     * <p>Runs a gameround.</p>
     */
    private void runRound()
    {
        //DEAL CARDS
        dealToPlayers();
        System.out.println(getLocal());

        //GET PLAYER CHOSEN CARDS
        ArrayList<Card> chosen = GameCommandLine
                .getLocalCardSequenceInput(givenProgramCards.get(getLocal()));
        for(Card c : chosen) System.out.println(c.getName());

        //MOVE ROBOTS BASED ON CHOSEN CARDS
        for(Player p : players) {
            for(Card c : chosenProgramCards.get(p));
                //TODO: CARDS NEED TURN_FUNCTIONALITY TO MAKE THEM EASIER TO USE
                //TODO: FOR NOW THE ROBOT WILL BE MOVED IN A NON_CORRESPONDING WAY
        }

        //MOVE BOARD ELEMENTS (CONVEYOR, GEARS)

        //CALCULATE DAMAGE (LAZERS)

        //OTHER CHECKS

        doResets();
    }

    /**
     * <p>Resets everything that has to be reset at the end of the round</p>
     */
    private void doResets() {
        resetCardAssociations();
    }

    /**
     * <p>Resets card-associations.</p>
     */
    private void resetCardAssociations() {
        for(Player p : players) {
            givenProgramCards.get(p).clear(); //Clears list
            chosenProgramCards.get(p).clear();
        }
    }

    /* Dealing methods */

    /**
     * <p>Deal a given amount of cards to each player </p>
     */
    public void dealToPlayers() {
        for(Player p : players)
            givenProgramCards.get(p).addAll(dealer.dealCards(DEAL_CARD_AMOUNT));
        dealer.resetDeck();
    }

    /*
     * * * * * Player Methods:
     */

    /**
     * <p>Returns the given program cards associated with the player.</p>
     * @param p Player
     * @return List of cards
     */
    public List<Card> getPlayerProgramCards(Player p)
    { return givenProgramCards.get(p); }

    /**
     * <p>Tells game what sequence of cards should be associated with player.</p>
     * @param p Player
     * @param cardSequence Card sequence to be used.
     */
    public void setChosenProgramCards(Player p, ArrayList<Card> cardSequence)
    { chosenProgramCards.get(p).addAll(cardSequence); }

    /**
     * <p>Adds a player to the Game's Player-set.</p>
     * @param p Player-instance.
     */
    public void addPlayer(Player p) {
        players.add(p);
        /* For now we also need to add the entries of players here too.*/
        givenProgramCards.put(p,new ArrayList<>());
        chosenProgramCards.put(p,new ArrayList<>());
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
     * <p>Has a delay.</p>
     */
    public void movePlayerRobot(Player p, Direction dir, int steps) {
        for(int i = 0; i < steps; i++) {
            grid.moveRobot(p.getRobot(), dir);
            updatePlayerStatus(p);
        }
    }

    /**
     * <p>Turns the player robot.</p>
     * @param p Player
     * @param action Turn-Action
     */
    public void turnPlayerRobot(Player p, CardAction action) {
        Robot robot = p.getRobot();
        switch (action) {
            case TURN_RIGHT: robot.setOrientation(robot
                    .getOrientation().rotateRight()); break;
            case TURN_LEFT: robot.setOrientation(robot
                    .getOrientation().rotateLeft()); break;
            case TURN_AROUND: robot.setOrientation(robot
                    .getOrientation().rotate180()); break;
        }
        updatePlayerStatus(p);
    }

    /**
     * <p>This method will take in a card and a Player and move the player-robot.<br>
     *     It will also add a delay between movements.</p>
     * @param p Player
     * @param card Movement-card
     */
    public void useCardOnPlayerRobot(Player p, Card card) {

        /* Checking for rotation */
        if(card instanceof TurnCard) {
            turnPlayerRobot(p, card.getAction());
            return;
        }

        /* Card shouldn't be anything else than a step-card now. */
        if(!(card instanceof StepCard)) return;

        /* At this point we know that we have a step-card, so we can extract the steps */
        int steps = ((StepCard) card).getSteps();

        /* Acting on movement */
        switch (card.getAction()) {
            case FORWARD:
                movePlayerRobot(p,
                        p.getRobot().getOrientation(),
                        steps); break;
            case BACKWARD:
                movePlayerRobot(p,
                        p.getRobot().getOrientation().rotate180(),
                        steps); break;
        }
    }

    /**
     * <p>When something changes with Player, its instance should update
     *    its information.</p>
     * @param p Player-instance
     */
    private void updatePlayerStatus(Player p) {
        /* Check for possible damage */
        if(grid.positionHasHole(p.getRobot().getPosition()))
        {
            p.getRobot().setIsDead(true);
            p.killPlayer();
        }

        /* Check if possible flag (for now this will check for "a" flag and give a win */
        if(grid.positionHasFlag(p.getRobot().getPosition()))
        {
            p.getRobot().setHasWon(true);
            p.playerWon();
        }
    }

    public void addFlag(Flag f) {
        flags.add(f);
    }

    public ArrayList<Flag> getFlags() {
        return this.flags;
    }

    public void addArchiveMarker(ArchiveMarker am) { archiveMarkers.add(am); }

    public ArrayList<ArchiveMarker> getArchiveMarkers() { return archiveMarkers; }
}
