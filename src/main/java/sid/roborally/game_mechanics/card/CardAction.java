package sid.roborally.game_mechanics.card;

/**
 * <h3>CardAction</h3>
 * <p>Enumeration for Card-actions that can be applied to a robot.</p>
 *
 * @author Daniel Janols
 */
public enum CardAction {
        FORWARD,
        BACKWARD,
        TURN_RIGHT,
        TURN_LEFT,
        TURN_AROUND;

    /**
     * <p>Gets name associated with action.</p>
     * @return Name
     */
    public String getActionName() {
        CardAction action = values()[ordinal()];
        switch (action) {
            case FORWARD: return "Forward";
            case BACKWARD: return "Backward";
            case TURN_RIGHT: return "Turn Right";
            case TURN_LEFT: return "Turn Left";
            case TURN_AROUND: return "Turn Around";
            default: return "No Action";
        }
    }
}