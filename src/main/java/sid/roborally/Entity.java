package sid.roborally;

/**
 * This Entity class contains the position of an entity.
 * Starting Direction is facing North.
 * Can set and get Direction
 *
 * @author Markus Edlin
 */
public class Entity {

    public Position pos;
    public Direction orientation;


    public Entity(Integer x, Integer y) {
        pos = new Position(x,y);
    }

    public Direction getOrientation() {
        return orientation;
    }

    public void setOrientation(Direction orientation) {
        this.orientation = orientation;
    }


}
