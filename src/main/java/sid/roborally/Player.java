package sid.roborally;

import sid.roborally.gamelogic.Position;
import sid.roborally.gfx_and_ui.PlayerGraphic;

/**
 * This shall not be pushed. It is only a placeholder for the Player-class
 */
public class Player {

    private enum State {Active, Won, Dead}
    private State playerState;
    private Position position;
    private PlayerGraphic p_graphic;

    public Player(Position pos, int textureIndex)
    {
        playerState = State.Active;
        position = pos;
        this.p_graphic = new PlayerGraphic(this, textureIndex);
    }

    public Player(Position pos)
    {
        playerState = State.Active;
        position = pos;
        this.p_graphic = new PlayerGraphic(this, 0);
    }

    public Position getPosition() { return position; }

    public PlayerGraphic getPlayerGraphic() { return p_graphic; }

    public boolean isDead() { return playerState == State.Dead; }

    public boolean hasWon() { return playerState == State.Won; }

    public void killPlayer() { playerState = State.Dead; }
}
