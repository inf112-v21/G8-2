package sid.roborally.game_mechanics;

/**
 * This is the turn/rotate card.
 * It inherits priority from the Card class
 * It also returns a direction relative to your current facing position. Hopefully.
 * This is acquired via the getOrientation() method
 */

public class TurnCard extends Card{

    private Direction orientation;

    /**
     * @param pri decides priority.  turn left, right or around
     * @param turn "left", "right" or "around"
     */
    public TurnCard(int pri, String turn) {
        super(pri);
        this.orientation = Direction.NORTH;
        //todo akkurat nå setter jeg Direction.NORTH for å kunne bruke rotates. Må finne en måte å hente direction fra spilleren sin robots orientation, I guess?
        if (turn.equals("left"))
            this.orientation = orientation.rotateLeft();
        if (turn.equals("right"))
            this.orientation = orientation.rotateRight();
        if (turn.equals("around"))
            this.orientation = orientation.rotate180();
    }

    public Direction getOrientation(){
        return orientation;
    }


}
