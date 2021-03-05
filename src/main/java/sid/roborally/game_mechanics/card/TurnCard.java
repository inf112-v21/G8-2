package sid.roborally.game_mechanics.card;

/**
 * This is the turn/rotate card.
 * It inherits priority from the Card class
 * It returns a string with which way to turn.
 * This is acquired via the getTurnDirection() method
 */

public class TurnCard extends Card{

    /**
     * @param pri decides priority.  turn left, right or around
     */
    public TurnCard(int pri, CardAction action) {
        super(pri,action);
    }

    /**
     * @return String turnDirection
     */
    public String getTurnDirection(){
        return this.getName();
    }


}
