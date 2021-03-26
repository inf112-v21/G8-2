package sid.roborally.application_functionality.reference;

/**
 * <h3>PlayerTexture</h3>
 * <p>PlayerTexture enum for reference.</p>
 *
 * @author Daniel Janols
 */
public enum PlayerTexture {
    /* The different player-textures */
    Player1; //Owl-texture

    private static final String PLAYER_TEXTURE_FOLDER_PATH = "assets/player_tex/";

    /**
     * <p>Gets path associated with given player-texture</p>
     * @return Path (String)
     */
    public String getTexturePath() {
        PlayerTexture pt = values()[ordinal()];
        switch (pt) {
            case Player1:
                return PLAYER_TEXTURE_FOLDER_PATH + "player1.png";
            default: return "";
        }
    }
}
