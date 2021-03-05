package sid.roborally.game_mechanics.grid;

import sid.roborally.game_mechanics.Direction;

/**
 * <h3>GridObject</h3>
 * <p>This class contains the position of an entity.
 * Starting Direction is facing North.
 * Can set and get Direction</p>
 *
 * @author Markus Edlin
 */
abstract public class GridObject {

    private Position pos;
    private Direction orientation;

    public GridObject(int x, int y) {
        pos = new Position(x,y);
    }

    public Position getPosition() {
        return this.pos;
    }

    public void setPosition(Position newPos) {
        this.getPosition().setPosition(newPos);
    }

    public Direction getOrientation() { return orientation; }

    public void setOrientation(Direction orientation) { this.orientation = orientation; }

}
