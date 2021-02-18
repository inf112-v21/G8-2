package sid.roborally.gfx_and_ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import sid.roborally.application_functionality.Player;

/**
 * <p>This class will be connected to the Player class and
 * its responsibility is to control the grahics part of a Player/User-entity</p>
 *
 * @author Daniel-J
 */
public class PlayerGraphic {

    //Player's state-cells (graphics for different states)
    TiledMapTileLayer.Cell playerLive;
    TiledMapTileLayer.Cell playerDead;
    TiledMapTileLayer.Cell playerWin;

    Player player;

    public PlayerGraphic(Player player, int textureIndex)
    {
        this.player = player;

        /* Giving player its chosen textures */
        setSelectedStateGraphics(textureIndex);
    }

    private void setSelectedStateGraphics(int index)
    {
        TextureRegion tex = new TextureRegion(new Texture("assets/player.png"));
        //[][0]:Alive; [][1]:Dead; [][2]:Won;
        TextureRegion[][] playerTextures = tex.split(300,300);

        playerLive = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[index][0]));
        playerDead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[index][1]));
        playerWin = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[index][2]));
    }

    public TiledMapTileLayer.Cell getPlayerTexture()
    {
        if(player.hasWon()) return playerWin;
        if(player.isDead()) return playerDead;
        else return playerLive;
    }

    public Player getPlayer() { return player; }
}
