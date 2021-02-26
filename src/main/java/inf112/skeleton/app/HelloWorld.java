package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

import javax.swing.*;

public class HelloWorld extends InputAdapter implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;

    //Tiles existing in the map
    TiledMap map;
    TiledMapTileLayer board;
    TiledMapTileLayer hole;
    TiledMapTileLayer flag;
    TiledMapTileLayer player;

    //Different player cells
    TiledMapTileLayer.Cell playerLive;
    TiledMapTileLayer.Cell playerDead;
    TiledMapTileLayer.Cell playerWin;

    //Vector to keep tack of player position
    Vector2 playerPos;


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
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        //Load the tile layers
        map = new TmxMapLoader().load("assets\\example.tmx");
        board = (TiledMapTileLayer) map.getLayers().get("Board");
        hole = (TiledMapTileLayer) map.getLayers().get("Hole");
        flag = (TiledMapTileLayer) map.getLayers().get("Flag");
        player = (TiledMapTileLayer) map.getLayers().get("Player");

        //Load player textures and split
        TextureRegion tex = new TextureRegion(new Texture("tilesets/player1.png"));

        //A 2-d list containing all the 3 player textures
        TextureRegion[][] playerTextures = tex.split(300,300);

        //Change the cells to textures and position a player on the map
        playerLive = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][0]));
        playerDead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][1]));
        playerWin = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(playerTextures[0][2]));

        //set player position
        playerPos = new Vector2(0,0);


        //Creates camera
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1500, 1500);
        cam.update();

        //Creates render
        rend = new OrthogonalTiledMapRenderer(map);

        //Sets render's view to that of the camera
        rend.setView(cam);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    /**
     * Renders ("draws") the map to a window
     */
    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        //Place player on map
        player.setCell((int)playerPos.x,(int)playerPos.y,playerLive);

        //If player steps over hole tile or flag tile, change sprite
        if(hole.getCell((int)playerPos.x,(int)playerPos.y)!=null) {
            //Update player sprite to dead
            player.setCell((int) playerPos.x, (int) playerPos.y, playerDead);

            //Lose text displays
            JOptionPane.showMessageDialog(null, "You lose!");

            //Quit game
            Gdx.app.exit();
        }

        //If player steps over flag tile
        if(flag.getCell((int)playerPos.x,(int)playerPos.y)!=null){
            //Update player sprite to win
            player.setCell((int)playerPos.x,(int)playerPos.y,playerWin);

            //Victory text displays
            JOptionPane.showMessageDialog(null, "Victory!");

            //Exit game
            Gdx.app.exit();
        }

        //Render the map
        rend.render();

        //Old code for putting text, useful for win/death conditions?
        /*batch.begin();
        font.draw(batch, "Hello World", 200, 200);
        batch.end();*/
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
    /**
     * Below here are the control functions for the game
     * Can move with directional keys or W A S D
     */
    @Override
    public boolean keyUp(int keyCode){
        //Checks input is directions/W A S or D keys
        if(Input.Keys.LEFT == keyCode || Input.Keys.A == keyCode){
            //If player is would exit map, do nothing
            if(playerPos.x-1 < 0) {
                return true;
            }
            //If move is valid, update player position
            removeSprite((int)playerPos.x, (int)playerPos.y, player);
            playerPos.add(-1,0);
            return true;

        }
        if(Input.Keys.RIGHT == keyCode || Input.Keys.D == keyCode){
            if(playerPos.x+1>(board.getWidth()-1)){
                return true;
            }
            removeSprite((int)playerPos.x, (int)playerPos.y, player);
            playerPos.add(+1,0);
            return true;
        }
        if(Input.Keys.UP == keyCode || Input.Keys.W == keyCode){
            if(playerPos.y+1 > (board.getHeight()-1)){
                return true;
            }
            removeSprite((int)playerPos.x, (int)playerPos.y, player);
            playerPos.add(0,1);
            return true;
        }
        if(Input.Keys.DOWN == keyCode || Input.Keys.S == keyCode){
            if(playerPos.y-1 < 0){
                return true;
            }
            removeSprite((int)playerPos.x, (int)playerPos.y, player);
            playerPos.add(0,-1);
            return true;
        }
        return false;
    }
}
