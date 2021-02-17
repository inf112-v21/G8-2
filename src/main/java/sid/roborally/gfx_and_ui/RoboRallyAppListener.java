package sid.roborally.gfx_and_ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import sid.roborally.MockPlayer;
import sid.roborally.gamelogic.Position;
import sid.roborally.RoboRallyApplication;

/**
 * @author Emil-E/Daniel-J
 */
public class RoboRallyAppListener extends InputAdapter implements ApplicationListener {


    private RoboRallyApplication rr_app; //The wrapper app which controls the whole program

    //Different player cells
    TiledMapTileLayer.Cell playerLive;
    TiledMapTileLayer.Cell playerDead;
    TiledMapTileLayer.Cell playerWin;

    //Vector to keep tack of player position
    //TODO:Vector2 playerPos; Dette skal være unødvendig

    //Renderer and camera
    OrthogonalTiledMapRenderer rend;
    OrthographicCamera cam;

    /**
     * Create() function finds maps, tile layers and sprites,
     * sets up renderer and camera
     * as well as splitting up the sprites for later use in the render function
     */
    @Override
    public void create() {

        //Application wrapper
        rr_app = new RoboRallyApplication();

        //Load player textures and split
        TextureRegion tex = new TextureRegion(new Texture("player.png"));

        //A 2-d list containing all the 3 player textures
        TextureRegion[][] playerTextures = tex.split(300,300);

        //Change the cells to textures and position a player on the map
        playerLive = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][0]));
        playerDead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][1]));
        playerWin = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][2]));

        //Creates camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1500, 1500);
        cam.update();

        //Creates render
        rend = new OrthogonalTiledMapRenderer(rr_app.getMap());

        //Sets render's view to that of the camera
        rend.setView(cam);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void dispose() {}

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        /* Check if game is lost */
        //TODO: Check if game is lost, if so:
        //Lose text displays
        //TODO:JOptionPane.showMessageDialog(null, "You lose!");
        //Quit game
        //TODO: Gdx.app.exit();

        /* Check if game is won */
        //TODO: Check if game is won, if so:
        //Victory text displays
        //TODO: JOptionPane.showMessageDialog(null, "Victory!");
        //Exit game
        //TODO: Gdx.app.exit();

        for(MockPlayer player : rr_app.getPlayers())
        {
            rr_app.getPlayerLayer().setCell(player.getPosition().getX(),
                    player.getPosition().getY(), player.getPlayerGraphic().getPlayerTexture());
        }


        //TODO: Skal være overflødig: rr_app.getPlayerLayer().setCell((int)playerPos.x,(int)playerPos.y,playerLive);
        //TODO: Skal være overflødig: rr_app.getPlayerLayer().setCell(4, 2, playerDead);


        //If player steps over hole tile or flag tile, change sprite (Dette sjekker bare for én player og ikke flere)
        //TODO: Dette skal være overflødig fordi state skal bli hentet i første del av render
        /*if(rr_app.getHoleLayer().getCell((int)playerPos.x,(int)playerPos.y)!=null) {
            //Update player sprite to dead
            rr_app.getPlayerLayer().setCell((int) playerPos.x, (int) playerPos.y, playerDead);

            //Lose text displays
            JOptionPane.showMessageDialog(null, "You lose!");

            //Quit game
            Gdx.app.exit();
        }*/

        //If player steps over flag tile
        //TODO: Dette skal bli overflødig
        /*
        if(rr_app.getFlagTiles().getCell((int)playerPos.x,(int)playerPos.y)!=null){
            //Update player sprite to win
            rr_app.getPlayerLayer().setCell((int)playerPos.x,(int)playerPos.y,playerWin);

            //Victory text displays
            JOptionPane.showMessageDialog(null, "Victory!");

            //Exit game
            Gdx.app.exit();
        }*/

        //Render the map
        rend.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
    //Removes a sprite on a given layer in a given position
    private void removeSprite(int posX, int posY, TiledMapTileLayer layer){
        layer.setCell(posX,posY, null);
    }

    /*
     * Below here are the control functions for the game
     * Can move with directional keys or W A S D
     */
    @Override
    public boolean keyUp(int keyCode){
        if(Input.Keys.LEFT == keyCode || Input.Keys.A == keyCode)
            rr_app.moveLeftInput();
        if(Input.Keys.RIGHT == keyCode || Input.Keys.D == keyCode)
            rr_app.moveRightInput();
        if(Input.Keys.UP == keyCode || Input.Keys.W == keyCode)
            rr_app.moveUpInput();
        if(Input.Keys.DOWN == keyCode || Input.Keys.S == keyCode)
            rr_app.moveDownInput();
        if(Input.Keys.ESCAPE == keyCode)
            rr_app.escapeInput();
        return false;
    }
}
