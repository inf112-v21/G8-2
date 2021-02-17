package sid.roborally;

/**
 * This Entity class contains the position of an entity.
 *
 * @author Markus Edlin
 */
public class Entity {

    public Position pos;

    public Entity(Integer x, Integer y) {
        pos = new Position(x,y);
    }
}
