package sid.roborally;

/**
 * This Entity class contains the position of an entity.
 * Starting Direction is facing North.
 * Can set and get Direction
 *
 * @author Markus Edlin
 */
public class GridObject {

    private Position pos;
    private Direction orientation;

    public GridObject(int x, int y) {
        pos = new Position(x,y);
    }

    public Position getPosition() {
        return this.pos;
    }

    public void setPosition(int x, int y) {
        this.getPosition().setPosition(x,y);
    }

 public Direction getOrientation() {
     return orientation;
 }

 public void setOrientation(Direction orientation) {
     this.orientation = orientation;
 }

}
