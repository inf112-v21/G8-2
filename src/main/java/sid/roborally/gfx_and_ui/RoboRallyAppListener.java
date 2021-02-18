package sid.roborally.gfx_and_ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import sid.roborally.application_functionality.Player;
import sid.roborally.application_functionality.RoboRallyApplication;

/**
 * <h3>RoboRallyAppListener</h3>
 * <p>This class implements ApplicationListener (libgdx) and contains functionality
 *    to tell the Lwjgl3Application how and what to display, take in and so on.</p><br>
 * <p>The goal is for this class to impact the rest of the program as little as possible
 *     internally.</p>
 */
public class RoboRallyAppListener extends InputAdapter implements ApplicationListener {

    private RoboRallyApplication rr_app; //The wrapper app which controls the whole program

    private SpriteBatch batch;
    private BitmapFont font;

    private boolean drawVictory;
    private boolean drawLoss;

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

        drawVictory = false;
        drawLoss = false;

        batch = new SpriteBatch();
        font = new BitmapFont();

        //Application wrapper
        rr_app = new RoboRallyApplication();

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
    public void dispose()
    {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        /* Check if game is lost */ //TODO: Find a better place for this
        /* Check if game is won */ //TODO: Find a better place for this
        if(drawVictory) displayVictory();
        if(drawLoss) displayLoss();

        for(Player player : rr_app.getPlayers())
            rr_app.getPlayerLayer().setCell(player.getRobot().getPosition().getX(),
                    player.getRobot().getPosition().getY(),
                    player.getPlayerGraphic().getPlayerTexture());

        /* Render the map */
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

    private void displayVictory()
    {
        batch.begin();
        font.setColor(Color.CYAN);

        font.draw(batch, "Victory!", 10, 10);
        batch.end();
    }

    private void displayLoss()
    {
        batch.begin();
        font.setColor(Color.RED);

        font.draw(batch, "You Lost!", 10, 10);
        batch.end();
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
        if(Input.Keys.V == keyCode) drawVictory = true;
        if(Input.Keys.L == keyCode) drawVictory = true;
        return false;
    }
}
