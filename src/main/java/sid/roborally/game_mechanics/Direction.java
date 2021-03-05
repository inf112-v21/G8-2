package sid.roborally.game_mechanics;

/**
 *  <h3>Direction</h3>
 *  <p>Directions that can be used by any GridObject.
 *  Rotate methods for right, left and 180.</p>
 *  @author Terje Trommestad
 */

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    /**
     * <p>Returns direction when rotation is applied.</p>
     * @return Rotated direction
     */
    public Direction rotateRight() {
        return values()[(ordinal() + 1) % 4];
    }

    /**
     * <p>Returns direction when rotation is applied.</p>
     * @return Rotated direction
     */
    public Direction rotate180() {
         return values()[(ordinal() + 2) % 4];
     }

    /**
     * <p>Returns direction when rotation is applied.</p>
     * @return Rotated direction
     */
     public Direction rotateLeft() {
        return values()[(ordinal() + 3) % 4];
    }
}









