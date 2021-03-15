package sid.roborally.application_functionality.reference;

import sid.roborally.game_mechanics.card.CardAction;

/**
 * <h3>Map</h3>
 * <p>Map enum for reference.</p>
 *
 * @author Daniel Janols
 */
public enum Map {
    /* The different maps */
    DemoMap,
    TwoPlayerDemo;

    private static final String MAP_FOLDER_PATH = "assets/maps/";

    /**
     * <p>Returns the path that corresponds to the map chosen.</p>
     * @return MapPath
     */
    public String getMapPath() {
        Map map = values()[ordinal()];
        switch (map) {
            case DemoMap:
                return MAP_FOLDER_PATH + "example.tmx";
            case TwoPlayerDemo:
                return MAP_FOLDER_PATH + "2player2flag.tmx";
            default: return "";
        }
    }
}
