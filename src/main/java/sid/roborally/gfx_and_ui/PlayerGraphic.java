package sid.roborally.gfx_and_ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import sid.roborally.application_functionality.Player;
import sid.roborally.application_functionality.reference.PlayerTexture;

import java.io.Serializable;

/**
 * <h3>PlayerGraphic</h3>
 *
 * <p>This class will be connected to a Player instance, and will be passed
 *    by the Player outwards when graphics-information about the Player is needed.</p>
 */
public class PlayerGraphic implements Serializable {

    /* Player's state-cells (graphics for different states) */
    TiledMapTileLayer.Cell playerLive;
    TiledMapTileLayer.Cell playerDead;
    TiledMapTileLayer.Cell playerWin;

    Player player;

    /**
     * <p>PlayerGraphics constructor creates an instance with a player and
     *    a index for what skin-texture-set the player will have.</p>
     * @param player The player that owns this class.
     * @param pt PlayerTexture type
     */
    public PlayerGraphic(Player player, PlayerTexture pt)
    {
        this.player = player;
        setSelectedStateGraphics(pt);
    }

    /**
     * <p>Choses what Player-texture to associate with this PlayerGraphic instance</p>
     * @param pt Playertexture
     */
    private void setSelectedStateGraphics(PlayerTexture pt)
    {
        TextureRegion tex = new TextureRegion(
                new Texture(pt.getTexturePath()));

        //[][0]:Alive; [][1]:Dead; [][2]:Won;
        TextureRegion[][] playerTextures = tex.split(300,300);

        playerLive = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][0]));
        playerDead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][1]));
        playerWin = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][2]));
    }

    /**
     * <p>Will check what state the Player-instance is in and return the
     *    appropriate player-texture</p>
     *
     * @return TileLayer.Cell representing current player_state
     */
    public TiledMapTileLayer.Cell getPlayerTexture()
    {
        if(player.hasWon()) return playerWin;
        if(player.isDead()) return playerDead;
        else return playerLive;
    }

    /**
     * <p>Get Player-instance associated with this PlayerGraphic</p>
     * @return player - Associated Player instance
     */
    public Player getPlayer() { return player; }
}
