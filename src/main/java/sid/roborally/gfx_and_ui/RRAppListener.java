package sid.roborally.gfx_and_ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import sid.roborally.application_functionality.Player;
import sid.roborally.application_functionality.RRApplication;

/**
 * <h3>RoboRallyAppListener</h3>
 * <p>This class implements ApplicationListener (libgdx) and contains functionality
 *    to tell the Lwjgl3Application how and what to display, take in and so on.</p><br>
 * <p>The goal is for this class to impact the rest of the program as little as possible
 *     internally.</p>
 */
public class RRAppListener extends InputAdapter implements ApplicationListener {


    private RRApplication rr_app; //Class that controls the application

    /* Renderer and camera */
    private OrthogonalTiledMapRenderer rend;
    private OrthographicCamera cam;

    public RRAppListener(RRApplication app) {
        this.rr_app = app;
    }

    @Override
    public void create()
    {
        /* This apparently need to be called from AppListener */
        rr_app.setUpDemoGame();

        /* Creates camera */
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1500, 1500);
        cam.update();

        /* Creates render */
        rend = new OrthogonalTiledMapRenderer(rr_app.getMap());

        /* Sets render's view to that of the camera */
        rend.setView(cam);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void dispose()
    { }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        /* Sets cell-texture based on Player-texture-information */
        for(Player player : rr_app.getPlayers())
            rr_app.getPlayerLayer().setCell(
                    player.getRobot().getPosition().getX(), //Player x-position
                    player.getRobot().getPosition().getY(), //Player y-position
                    player.getPlayerGraphic().getPlayerTexture()); //Player-state texture

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
        if(Input.Keys.Q == keyCode)
            rr_app.rotateLeftInput();
        if(Input.Keys.E == keyCode)
            rr_app.rotateRightInput();
        if(Input.Keys.F == keyCode)
            rr_app.stepOneForwardInput();
        return false;
    }
}
