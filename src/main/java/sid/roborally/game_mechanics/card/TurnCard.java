package sid.roborally.game_mechanics.card;

/**
 * <h3>TurnCard</h3>
 * <p>TurnCard contains information about how player should turn.</p>
 *
 * @author Terje Trommestad
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
