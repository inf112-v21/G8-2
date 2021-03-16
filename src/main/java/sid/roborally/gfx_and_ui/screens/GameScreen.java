package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import sid.roborally.application_functionality.Player;
import sid.roborally.application_functionality.RRApplication;
import sid.roborally.gfx_and_ui.AppListener;

/**
 * <h3>GameScreen</h3>
 * <p>The screen that displays our in-game visuals.</p>
 * @author Daniel Janols
 */
public class GameScreen extends InputAdapter implements ApplicationListener, Screen {

    final AppListener appListener;
    private RRApplication rr_app;

    /* Renderer and camera */
    private OrthogonalTiledMapRenderer rend;
    private OrthographicCamera cam;

    public GameScreen(final AppListener appListener) {
        this.appListener = appListener;
        rr_app = appListener.getRRApp();

        /* This apparently need to be called from AppListener */
        //rr_app.setUpDemoGame();

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
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            System.out.println(touchPos.x + ":" + touchPos.y + ":" + touchPos.z);
        }

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
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
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
