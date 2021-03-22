package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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

    private Stage uiStage;
    private Viewport uiView;

    private Image centerImage;

    private InputMultiplexer inputMultiplexer;

    private Player localPlayer;

    /* Renderer and camera */
    private OrthogonalTiledMapRenderer rend;
    private OrthographicCamera cam;

    /* Map-width/height and unit-size (pixels pr. position) */
    private int mapWidth, mapHeight;
    private static final float MAP_UNIT= 300f; //Time position times mapUnit to find cam-position.
    private static final float CAM_OFFSET_X = 150, CAM_OFFSET_Y = 150;
    private float camOffsetX, camOffsetY; //Camera-position is in center and of screen.

    public GameScreen(final AppListener appListener) {
        this.appListener = appListener;
        rr_app = appListener.getRRApp();

        uiView = new FitViewport(1600,1600);
        uiStage = new Stage(uiView);

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(uiStage);

        /* This apparently need to be called from AppListener */
        rr_app.setUpDemoGame();
        localPlayer = rr_app.getGameRunner().getLocal();

        /* Get map-value info */
        mapWidth = rr_app.getGameRunner().getBoardWidth();
        mapHeight = rr_app.getGameRunner().getBoardHeight();

        /* Sets up visuals for board */
        setUpCamAndRenderer();

        uiStage.act();
        uiStage.draw();

        Gdx.input.setInputProcessor(this);
    }

    /**
     * <p>Sets up cam and renderer to clean up constructor</p>
     */
    private void setUpCamAndRenderer() {
        /* Creates camera */
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 3000,3000);
        cam.update();

        /* Creates render */
        rend = new OrthogonalTiledMapRenderer(rr_app.getMap());

        /* Center cam on player */
        centerCamOnPlayer(localPlayer);

        /* Sets render's view to that of the camera */
        rend.setView(cam);
    }

    private void updateRendWithCam() {
        rend.setView(cam);
    }

    private void centerCamOnMapCenter() {
        float mapCenterX = MAP_UNIT * mapWidth/2;
        float mapCenterY = MAP_UNIT * mapHeight/2;
        cam.position.set(mapCenterX,mapCenterY,0);
        cam.update();
        rend.setView(cam);
    }

    private void centerCamOnPlayer(Player p) {
        float playerX = p.getRobot().getPosition().getX() * MAP_UNIT;
        float playerY = p.getRobot().getPosition().getY() * MAP_UNIT;

        cam.position.set(playerX + CAM_OFFSET_X, playerY + CAM_OFFSET_Y,0);
        cam.update();
        updateRendWithCam();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        /* Sets cell-texture based on Player-texture-information */
        for(Player player : rr_app.getPlayers())
            rr_app.getPlayerLayer().setCell(
                    player.getRobot().getPosition().getX(), //Player x-position
                    player.getRobot().getPosition().getY(), //Player y-position
                    player.getPlayerGraphic().getPlayerTexture()); //Player-state texture

        /* Render the map */
        rend.render();

        uiStage.act();
        uiStage.draw();
    }

    @Override
    public void create() {
    }

    @Override
    public void resize(int width, int height) {
        uiView.update(width,height);
        uiStage.act();
        uiStage.draw();
        cam.update();
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
        uiStage.dispose();
    }

    @Override
    public boolean keyUp(int keyCode){
        if(Input.Keys.LEFT == keyCode || Input.Keys.A == keyCode) {
            rr_app.moveLeftInput();
            centerCamOnPlayer(localPlayer);
        }
        if(Input.Keys.RIGHT == keyCode || Input.Keys.D == keyCode) {
            rr_app.moveRightInput();
            centerCamOnPlayer(localPlayer);
        }
        if(Input.Keys.UP == keyCode || Input.Keys.W == keyCode) {
            rr_app.moveUpInput();
            centerCamOnPlayer(localPlayer);
        }
        if(Input.Keys.DOWN == keyCode || Input.Keys.S == keyCode){
            rr_app.moveDownInput();
            centerCamOnPlayer(localPlayer);
        }
        if(Input.Keys.ESCAPE == keyCode)
            rr_app.escapeInput();
        if(Input.Keys.Q == keyCode)
            rr_app.rotateLeftInput();
        if(Input.Keys.E == keyCode)
            rr_app.rotateRightInput();
        if(Input.Keys.F == keyCode)
            rr_app.stepOneForwardInput();
        if(Input.Keys.M == keyCode)
            centerCamOnMapCenter();
        if(Input.Keys.SPACE == keyCode)
            centerCamOnPlayer(rr_app.getGameRunner().getLocal());
        if(Input.Keys.Z == keyCode)
            zoomCam(0.1f);
        if(Input.Keys.X == keyCode)
            zoomCam(-0.1f);
        return false;
    }

    private void zoomCam(float zoom) {
        cam.zoom += zoom;
        cam.update();
        rend.setView(cam);
    }
}
