package sid.roborally.application_functionality.reference;

/**
 * <h3>TextureReference</h3>
 * <p>This class will provide reference methods to map and player-references
 *    for to their textures. Thus providing an easy way to get their texture-data
 *    without the rest of the program having to bother with strings.</p>
 */
public class TextureReference {

    public enum Map {DemoMap, TwoPlayerDemo}
    public enum PlayerTexture {Player1}

    private static String MAP_FOLDER_PATH = "assets/maps/";
    private static String PLAYER_FOLDER_PATH = "assets/player_tex/";

    /**
     * <p>Returns the path that corresponds to the map chosen.</p>
     * @param map Map chosen
     * @return MapPath
     */
    public static String getMapPath(Map map)
    {
        String retMap;
        switch(map)
        {
            case DemoMap:
                retMap = "example.tmx"; break;
            case TwoPlayerDemo:
                retMap = "2player2flag.tmx"; break;
            default: retMap = ""; break;
        }
        return retMap.equals("") ? "" : MAP_FOLDER_PATH + retMap;
    }

    /**
     * <p>Returns the path that corresponds to the player chosen.</p>
     * @param pt Player texture chosen
     * @return Texture-path
     */
    public static String getPlayerTexPath(PlayerTexture pt)
    {
        String retTex;
        switch(pt)
        {
            case Player1:
                retTex = "player1.png"; break;
            default: retTex = ""; break;
        }
        return retTex.equals("") ? "" : PLAYER_FOLDER_PATH + retTex;
    }
}
