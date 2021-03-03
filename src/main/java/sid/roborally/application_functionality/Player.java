package sid.roborally.application_functionality;

import sid.roborally.game_mechanics.card.Card;
import sid.roborally.game_mechanics.card.CardDeck;
import sid.roborally.game_mechanics.grid.Position;
import sid.roborally.game_mechanics.grid.Robot;
import sid.roborally.gfx_and_ui.PlayerGraphic;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>Player</h3>
 *
 * <p>The goal of this class is to provide a mechanism and entity to keep
 *    information on a current player (user/AI).<br>
 *    To connect the graphics-part of a player and player-mechanisms for taking
 *    part in a game and communicating player-graphics and other player-information.</p><br>
 *
 * <p>This class will be instantiated in GameRunner and will also be associated with one or more
 *    game-instances.<br>
 *    The Player-instance shouldn't communicate it's orders directly with robot or grid; it should
 *    communicate its input trough the game-instance.</p>
 * */
public class Player {

    private enum OwnerLocation {Local, AI, External} //Local: is the user of this application, External: multiplayers
    private enum State {Active, Won, Dead}

    private State playerState;
    private OwnerLocation ownerLocation;
    private PlayerGraphic p_graphic;
    private ArrayList<Card> programCards;
    private Robot robot; //The player-instance's robot

    /**
     * <p>Player constructor that specifies a chosen skin-texture index.</p>
     *
     * @param pos Player position
     * @param textureIndex Texture-index
     */
    public Player(Position pos, int textureIndex)
    {
        playerState = State.Active;
        ownerLocation = OwnerLocation.AI; //TODO: AI basic setting for now.
        p_graphic = new PlayerGraphic(this, textureIndex);
        robot = new Robot(pos.getX(), pos.getY());
    }

    //TODO: Tror dette heller kan gjøres i Game, og bruke player sin ID til å finne ut hvem som skal velge hvilke kort
   public void giveProgramCards (List<Card> cards){ //skal si at mine programmeringskort er disse 5 kortene.
        this.programCards = (ArrayList<Card>) cards;
   }

   //TODO: Samme som li:50.
   public ArrayList<Card> getProgramCards () {
        return programCards;
   }

    /**
     * <p>Player constructor that does not specify a chosen texture and will
     * pick a basic texture if you don't tell it to not have a texture.</p>
     *
     * @param pos Player position
     * @param giveGraphics Should the player-instance have graphics.
     */
    public Player(Position pos, boolean giveGraphics)
    {
        playerState = State.Active;
        p_graphic = giveGraphics ? new PlayerGraphic(this, 0) : null;
        robot = new Robot(pos.getX(), pos.getY());
    }

    /*
     * Owner locality methods:
     */

    /**
     * <p>Sets Player-instance as a local-player</p>
     */
    public void setLocal() { ownerLocation = OwnerLocation.Local; }

    /**
     * <p>Sets Player-instance as a AI-player</p>
     */
    public void setAI() { ownerLocation = OwnerLocation.AI; }

    /**
     * <p>Sets Player-instance as a external-player</p>
     */
    public void setExternal() { ownerLocation = OwnerLocation.External; }

    /**
     * <p>Informs if Player-instance is Local</p>
     * @return boolean Is player local
     */
    public boolean isLocal() { return ownerLocation == OwnerLocation.Local; }

    /**
     * <p>Informs if Player-instance is AI</p>
     * @return boolean Is player AI
     */
    public boolean isAI() { return ownerLocation == OwnerLocation.AI; }

    /**
     * <p>Informs if Player-instance is External</p>
     * @return boolean Is player external
     */
    public boolean isExternal() { return ownerLocation == OwnerLocation.External; }


    /*
     * Player-state methods
     */
    //TODO: Reconsider if this should be taken from robot instead, or from game.
    public void playerWon() { playerState = State.Won; }

    public boolean isDead() { return playerState == State.Dead; }

    public boolean hasWon() { return playerState == State.Won; }

    public void killPlayer() { playerState = State.Dead; }

    /*
     * Other getter and setters
     */

    /**
     * <p>Gets Robot associated with this Player-instance.</p>
     * @return robot Robot-instance
     */
    public Robot getRobot() { return robot; }

    /**
     * <p>Gets PlayerGraphic associated with this Player-instance.</p>
     * @return p_graphic PlayerGraphic-instance.
     */
    public PlayerGraphic getPlayerGraphic() { return p_graphic; }
}
