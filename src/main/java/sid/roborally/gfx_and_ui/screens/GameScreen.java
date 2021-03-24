package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import sid.roborally.application_functionality.Player;
import sid.roborally.application_functionality.RRApplication;
import sid.roborally.game_mechanics.card.Card;
import sid.roborally.gfx_and_ui.AppListener;

import java.util.ArrayList;

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

    private float startZoomOut = 1;

    private static final String controlTableBackground = "assets/application_skin/tempCBackground.png";
    private Table controlTable; //Where you program the robot, and manage your decisions.

    /* Info-table*/
    private Table infoTable;
    private Window cardWindow;

    private InputMultiplexer inputMultiplexer;

    private Player localPlayer;

    /* Renderer and camera */
    private OrthogonalTiledMapRenderer rend;
    private OrthographicCamera cam;

    /* Map-width/height and unit-size (pixels pr. position) */
    private int mapWidth, mapHeight;
    private static final float MAP_UNIT= 300f; //Time position times mapUnit to find cam-position.
    private static final float CAM_OFFSET_X = 150 + 600, CAM_OFFSET_Y = 150 - 250;

    public GameScreen(final AppListener appListener) {
        this.appListener = appListener;

        /* Initial set-up*/
        getInitialInfo();

        /* Our game-control overlay */
        setUpUIStage();

        /* Sets up visuals for board */
        setUpCamAndRenderer();

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public void getInitialInfo() {
        rr_app = appListener.getRRApp();

        /* Need to tell rr_app to set up game */
        localPlayer = rr_app.getGameRunner().getLocal();

        /* Get map-value info */
        mapWidth = rr_app.getGameRunner().getBoardWidth();
        mapHeight = rr_app.getGameRunner().getBoardHeight();

        /* Viewport used by every component */
        uiView = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        /* Head of input-handling */
        inputMultiplexer = new InputMultiplexer();
    }

    private void setUpUIStage() {
        uiStage = new Stage(uiView);

        /* Sets up hud */
        setUpControlTable();
        setUpInfoTable();

        inputMultiplexer.addProcessor(uiStage);

        uiStage.act();
        uiStage.draw();
    }

    /**
     * <p>Sets up the ui for our game-controls, registers and so on.</p>
     */
    private void setUpControlTable() {
        /* Bottom-table */
        controlTable = new Table();
        controlTable.setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture(controlTableBackground))));
        controlTable.setSize(uiView.getWorldWidth(), uiView.getWorldHeight()/5);
        controlTable.left().top();

        /* Register-titles */
        Label reg1Label = new Label("Register one", appListener.getSkin());
        controlTable.add(reg1Label);
        Label reg2Label = new Label("Register two", appListener.getSkin());
        controlTable.add(reg2Label);
        Label reg3Label = new Label("Register three", appListener.getSkin());
        controlTable.add(reg3Label);
        Label reg4Label = new Label("Register four", appListener.getSkin());
        controlTable.add(reg4Label);
        Label reg5Label = new Label("Register five", appListener.getSkin());
        controlTable.add(reg5Label);

        controlTable.row();

        /* Register card-selection (Initially the lists are just the register-name) */
        SelectBox<String> reg1 = new SelectBox<>(appListener.getSkin());
        reg1.setItems("Register 1");
        controlTable.add(reg1);
        SelectBox<String> reg2 = new SelectBox<>(appListener.getSkin());
        reg2.setItems("Register 2");
        controlTable.add(reg2);
        SelectBox<String> reg3 = new SelectBox<>(appListener.getSkin());
        reg3.setItems("Register 3");
        controlTable.add(reg3);
        SelectBox<String> reg4 = new SelectBox<>(appListener.getSkin());
        reg4.setItems("Register 4");
        controlTable.add(reg4);
        SelectBox<String> reg5 = new SelectBox<>(appListener.getSkin());
        reg5.setItems("Register 5");
        controlTable.add(reg5);

        /* Finally */
        uiStage.addActor(controlTable);
    }

    /**
     * <p>Sets up our ui for displaying in-game info</p>
     */
    private void setUpInfoTable() {
        infoTable = new Table();
        infoTable.setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture(controlTableBackground))));
        infoTable.setSize(uiView.getWorldWidth()/4, uiView.getWorldHeight());
        infoTable.setPosition(uiView.getWorldWidth()*3/4,0);

        cardWindow = new Window("Cards", appListener.getSkin());
        cardWindow.setSize(200,500);
        cardWindow.setPosition(500,500);
        infoTable.add(cardWindow);

        /* Finally */
        uiStage.addActor(infoTable);
    }

    public void addNewCardsToCardWindow(ArrayList<Card> cards) {
        cardWindow.clear();
        for(Card card : cards) {
            cardWindow.add(card.getName() + ": " + card.getAction().getActionName() + " : " + card.getPriority());
        }
    }


    /**
     * <p>Sets up cam and renderer to clean up constructor</p>
     */
    private void setUpCamAndRenderer() {
        /* Creates camera */
        cam = new OrthographicCamera();
        cam.setToOrtho(false, uiView.getWorldWidth(), uiView.getWorldHeight());
        cam.update();

        /* Creates render */
        rend = new OrthogonalTiledMapRenderer(rr_app.getMap());

        /* Center cam on player */
        centerCamOnPlayer(localPlayer);

        /* Sets render's view to that of the camera */
        rend.setView(cam);

        zoomCam(startZoomOut);

        inputMultiplexer.addProcessor(this);
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

        uiStage.act();
        uiStage.draw();
    }

    @Override
    public boolean scrolled(int amount) {
        zoomCam(((float) amount)/2f);
        return false;
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
