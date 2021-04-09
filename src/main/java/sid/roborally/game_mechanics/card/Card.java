package sid.roborally.game_mechanics.card;

import java.io.Serializable;

/**
 * <h3>Card</h3>
 * <p>General-card class. Different cards inherits from this.</p>
 *
 * @author Emil ELdooen
 */
public abstract class Card implements Serializable {

    private int priority; //Determines card-playing order.
    private CardAction action;

    public Card(int pri, CardAction action){
        this.priority = pri;
        this.action = action;
    }

    /**
     * <p>Returns card-name/type</p>
     */
    public String getName() { return action.getActionName(); }

    /**
     * <p>Gets card priority</p>
     * @return priority-value
     */
    public int getPriority(){
        return priority;
    }

    /**
     * <p>Gets action associated with card.</p>
     * @return action
     */
    public CardAction getAction() { return action; }
}
