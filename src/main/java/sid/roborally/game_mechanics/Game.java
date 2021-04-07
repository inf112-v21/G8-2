package sid.roborally.game_mechanics;

import sid.roborally.application_functionality.GameRunner;
import sid.roborally.application_functionality.Player;
import sid.roborally.game_mechanics.card.*;
import sid.roborally.game_mechanics.grid.*;

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
 *
 * @author Daniel Janols
 */
public class Game {

    private GameRunner grunner;

    /* Game-elements */
    private ArrayList<Flag> flags;
    private HashSet<Player> players;
    private Grid grid;
    private CardDealer dealer;
    private ArrayList<ArchiveMarker> archiveMarkers;

    /* Phase-variables */
    private HashMap<Player, ArrayList<Card>>
            givenProgramCards, chosenProgramCards; //Cards given and selected by players.

    /* Constants */
    private static int DEAL_CARD_AMOUNT = 5;

    //=========Set-up methods===========================================================

    /**
     * <p>Game constructor.</p>
     */
    public Game() {
        players = new HashSet<>();
        dealer = new CardDealer();
        flags = new ArrayList<>();
        archiveMarkers = new ArrayList<>();
        givenProgramCards = new HashMap<>();
        chosenProgramCards = new HashMap<>();
    }

    /**
     * <p>Assigns GameRunner to game.</p>
     * @param gr GameRunner-instance
     */
    public void giveGameRunner(GameRunner gr) { grunner = gr; }

    /**
     * <p>Give Game a new empty-grid</p>
     * @param width grid-height
     * @param height grid-height
     */
    public void newGrid(int width, int height)
    { grid = new Grid(width, height); }

    //=========Phase methods============================================================

    /**
     * <p>Runs a gameround.</p>
     */
    public void runRound() {
        for(Player p : players)
            if(chosenProgramCards.get(p) != null)
                for(Card card : chosenProgramCards.get(p))
                    useCardOnPlayerRobot(p, card);

        //TODO: MOVE BOARD ELEMENTS (CONVEYOR, GEARS)

        //TODO: CALCULATE DAMAGE (LAZERS)

        //TODO: OTHER CHECKS

        doResets();
    }

    /**
     * <p>Resets everything that has to be reset at the end of the round</p>
     */
    private void doResets() {
        resetCardAssociations();
    }


    //=========Card methods=============================================================

    /**
     * <p>Resets card-associations.</p>
     */
    private void resetCardAssociations() {
        for(Player p : players) {
            givenProgramCards.get(p).clear(); //Clears list
            chosenProgramCards.get(p).clear();
        }
    }

    /**
     * <p>Deal a given amount of cards to each player </p>
     */
    public void dealToPlayers() {
        dealer.shuffleDeck();
        for(Player p : players) {
            givenProgramCards.get(p).addAll(dealer.dealCards(DEAL_CARD_AMOUNT));
        }
        dealer.resetDeck();
    }

    /**
     * <p>Sorts the cards connected to the different players based on card-priority. <br>
     *     if a higher priority card is behind a lower priority card, this card won't win until the
     *     lower priority card has been added return-list</p>
     * @return Associations between players and cards
     */
    public ArrayList<HashMap<Player,Card>> getCardsByPriority() {
        ArrayList<HashMap<Player,Card>> retList = new ArrayList<>();
        // ! This method depletes chosen-program-cards.
        boolean allEmpty = false;
        while (!allEmpty) {
            /* Find highest ranked card of the players' next cards */
            Player playerWithHighest = null; //Placeholder
            for(Player p : players) {
                if(playerWithHighest == null) {
                    if (!chosenProgramCards.get(p).isEmpty()) playerWithHighest = p;
                    continue;
                }

                if(!chosenProgramCards.get(p).isEmpty())
                    if(chosenProgramCards.get(p).get(0).getPriority()
                            > chosenProgramCards.get(playerWithHighest).get(0).getPriority())
                        playerWithHighest = p;
            }

            /* Deciding next player-card pairing */
            HashMap<Player,Card> nextPair = new HashMap<>();
            nextPair.put(playerWithHighest,chosenProgramCards.get(playerWithHighest).remove(0));
            retList.add(nextPair);

            /* Checking if we can stop */
            allEmpty = true;
            for(Player p : players)
                if(!chosenProgramCards.get(p).isEmpty()) allEmpty = false;
        }
        return retList;
    }


    //=========Player methods===========================================================

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
     * <p>Gets the local player instance (not AI or External)</p>
     */
    public Player getLocal() {
        for(Player p : players) if(p.isLocal()) return p;
        return null;
    }

    /**
     * <p>Moves robot in direction in grid</p>
     * <p>Has a delay.</p>
     */
    public void movePlayerRobot(Player p, Direction dir, int steps) {
        for(int i = 0; i < steps; i++) {
            if(grunner != null) grunner.resetPlayerTexture(p);
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

        Robot playerRobot = p.getRobot();
        Position playerPosition = playerRobot.getPosition();

        /* Check for possible damage */
        if(grid.positionHasHole(playerPosition)) {
            playerRobot.setIsDead(true);
            p.killPlayer();
        }

        /* Check for possible addition of flag */
        if(grid.positionHasFlag(playerPosition)) {
            Flag flagAtPosition = grid.getFlagAtPosition(playerPosition);
            int flagID = flagAtPosition.getID();
            HashSet<Flag> playerFlags = playerRobot.getFlags();

            if (flagID != 1){
                if(!playerFlags.contains(flagAtPosition) && playerRobot.containsFlagWithID(flagID-1))
                    playerRobot.addFlag(flagAtPosition);
            }else
                if (!playerFlags.contains(flagAtPosition)) playerRobot.addFlag(flagAtPosition);

            /* Check if all flags are found*/
            if(playerRobot.getFlags().containsAll(flags)){
                playerRobot.setHasWon(true);
                p.playerWon();
            }
        }
    }


    //=========Adding Board-elements====================================================

    /**
     * <p>Adds a GridObject-instance to the Game's Grid.</p>
     * @param go GridObject
     */
    public void addGridObjectToGrid(GridObject go)
    { grid.addGridObject(go);}

    /**
     * <p>Adds flag to game</p>
     * @param f Flag
     */
    public void addFlag(Flag f) {
        if(!grid.positionHasFlag(f.getPosition())) addGridObjectToGrid(f);
        if(!flags.contains(f)) flags.add(f);
    }

    public void emptyFlags(){
        flags = new ArrayList<>();
    }

    /**
     * <p>Adds archive-marker to game</p>
     * @param am Archive-marker
     */
    public void addArchiveMarker(ArchiveMarker am) {
        addGridObjectToGrid(am);
        if(!containsArchiveMarkerWithID(am.getID())) archiveMarkers.add(am);
    }


    //=========Checks===================================================================

    /**
     * <p>Checks if game contains Flag with ID.</p>
     * @param id ID
     * @return Boolean
     */
    public boolean containsFlagWithID(int id){
        for(Flag f : flags){ if (f.getID() == id) return true; }
        return false;
    }

    /**
     * <p>Checks if game contains ArchiveMarker with ID.</p>
     * @param id ID
     * @return Boolean
     */
    public boolean containsArchiveMarkerWithID(int id){
        for(ArchiveMarker am : archiveMarkers){ if(am.getID() == id) return true; }
        return false;
    }


    //=========Getters and Setters======================================================

    /**
     * <p>Sets cards chosen by Player p.</p>
     * @param p Player
     * @param chosenCards Chosen cards
     */
    public void setPlayerChosenCards(Player p, ArrayList<Card> chosenCards) {
        chosenProgramCards.get(p).clear();
        chosenProgramCards.get(p).addAll(chosenCards);
    }

    /**
     * <p>Gets the cards given to Player p</p>
     * @param p Player
     * @return Cards
     */
    public ArrayList<Card> getPlayerGivenCards(Player p) { return givenProgramCards.get(p); }

    /**
     * <p>Gets archiveMarkers in game</p>
     * @return ArchiveMarkers
     */
    public ArrayList<ArchiveMarker> getArchiveMarkers() { return archiveMarkers; }

    /**
     * <p>Gets Flags in game</p>
     * @return Flags
     */
    public ArrayList<Flag> getFlags() { return flags; }
}
