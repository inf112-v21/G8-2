package sid.roborally.application_functionality;

import sid.roborally.game_mechanics.Position;
import sid.roborally.game_mechanics.Robot;
import sid.roborally.gfx_and_ui.PlayerGraphic;

/**
 * This shall not be pushed. It is only a placeholder for the Player-class
 */
public class Player {

    private enum OwnerLocation {Local, AI, External} //Local: is the user of this application, External: multiplayers
    private enum State {Active, Won, Dead}

    private State playerState;
    private OwnerLocation ownerLocation;
    private PlayerGraphic p_graphic;

    private Robot robot; //The player-instance's robot


    public Player(Position pos, int textureIndex)
    {
        playerState = State.Active;
        ownerLocation = OwnerLocation.AI; //AI basic setting for now.
        p_graphic = new PlayerGraphic(this, textureIndex);
        robot = new Robot(pos.getX(), pos.getY());
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
    public void setLocal() { ownerLocation = OwnerLocation.Local; }

    public void setAI() { ownerLocation = OwnerLocation.AI; }

    public void setExternal() { ownerLocation = OwnerLocation.External; }

    public boolean isLocal() { return ownerLocation == OwnerLocation.Local; }

    public boolean isAI() { return ownerLocation == OwnerLocation.AI; }

    public boolean isExternal() { return ownerLocation == OwnerLocation.External; }

    /*
     * Movement
     */

    public void moveUp() { getRobot().getPosition().increment(0,1); }

    public void moveDown() { getRobot().getPosition().increment(0,-1); }

    public void moveLeft() { getRobot().getPosition().increment(-1,0); }

    public void moveRight() { getRobot().getPosition().increment(1,0); }


    /*
     * Player-state methods
     */
    public void playerWon() { playerState = State.Won; }

    public boolean isDead() { return playerState == State.Dead; }

    public boolean hasWon() { return playerState == State.Won; }

    public void killPlayer() { playerState = State.Dead; }

    /*
     * Other getter and setters
     */
    public Robot getRobot() { return robot; }

    public PlayerGraphic getPlayerGraphic() { return p_graphic; }
}
