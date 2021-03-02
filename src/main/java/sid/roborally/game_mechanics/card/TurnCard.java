package sid.roborally.game_mechanics.card;

/**
 * This is the turn/rotate card.
 * It inherits priority from the Card class
 * It returns a string with which way to turn.
 * This is acquired via the getTurnDirection() method
 */

public class TurnCard extends Card{

    private String turnDirection;

    /**
     * @param pri decides priority.  turn left, right or around
     * @param turnDir "left", "right" or "around"
     */
    public TurnCard(int pri, String turnDir, String name) {
        super(pri,name);
        this.turnDirection = turnDir;

    }


    /**
     * @return String turnDirection
     */
    public String getTurnDirection(){
        return turnDirection;
    }


}
