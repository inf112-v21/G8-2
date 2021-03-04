package sid.roborally.game_mechanics.card;

    /**
    * <p>Enumeration of different actions a card can have.</p>
    */
    public enum CardAction {
        FORWARD,
        BACKWARD,
        TURN_RIGHT,
        TURN_LEFT,
        TURN_AROUND;


        /**
         * <p>Gets name associated with action.</p>
         * @param action Action
         * @return Name
         */
        public static String getActionName(CardAction action) {
        switch (action) {
            case FORWARD: return "Forward";
            case BACKWARD: return "Backward";
            case TURN_RIGHT: return "Turn Right";
            case TURN_LEFT: return "Turn Left";
            case TURN_AROUND: return "Turn Around";
            default: return "Without Action";
        }
    }
}