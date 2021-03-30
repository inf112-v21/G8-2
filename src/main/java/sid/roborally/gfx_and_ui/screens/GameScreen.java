package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import sid.roborally.application_functionality.GameRunner;
import sid.roborally.application_functionality.Player;
import sid.roborally.application_functionality.RRApplication;
import sid.roborally.game_mechanics.card.Card;
import sid.roborally.game_mechanics.card.StepCard;
import sid.roborally.gfx_and_ui.AppListener;

import java.util.ArrayList;

/**
 * <h3>GameScreen</h3>
 * <p>The screen that displays our in-game visuals.</p>
 * @author Daniel Janols
 */
public class GameScreen extends InputAdapter implements ApplicationListener, Screen {

    private final AppListener appListener;
    private RRApplication rr_app;
    private GameRunner grunner;

    /*
     * * * Game-information
     */

    private ArrayList<Card> givenCards;
    private ArrayList<Integer> chosenCardsIndices;
    private ArrayList<Card> chosenCards;
    private Player localPlayer;

    /*
     * * * Scene2d elements
     */

    private Stage uiStage;
    private Viewport uiView;

    /* Info-table */
    private Table infoTable; //Where we display information.
    private Window cardWindow;

    /* Control-table*/
    private static final String CONTROL_TABLE_BACKGROUND = "assets/application_skin/tempCBackground.png";
    private Table controlTable; //Where you program the robot, and manage your decisions.

    private SelectBox<String> reg1, reg2, reg3, reg4, reg5;
    private String
            baseOptionReg1 = "Register One",
            baseOptionReg2 = "Register Two",
            baseOptionReg3 = "Register Three",
            baseOptionReg4 = "Register Four",
            baseOptionReg5 = "Register Five";
    private int //Chosen card index (-1) non chosen.
            reg1index = -1,
            reg2index = -1,
            reg3index = -1,
            reg4index = -1,
            reg5index = -1;

    private Button commitButton; //Commits cards

    /*
     * * * Map-rendering
     */

    /* Renderer and camera */
    private OrthogonalTiledMapRenderer rend;
    private OrthographicCamera cam;

    /* Map-width/height and unit-size (pixels pr. position) */
    private int mapWidth, mapHeight;
    private static final float MAP_UNIT= 300f; //Time position times mapUnit to find cam-position.
    private static final float CAM_OFFSET_X = 150 + 600, CAM_OFFSET_Y = 150 - 250;
    private static final float START_ZOOM_OUT = 1; //Default zoom is too close, so need to zoom out.

    /*
     * Collective input-handling
     */

    private InputMultiplexer inputMultiplexer;


    //=======Main Methods===============================================================

    public GameScreen(final AppListener appListener) {
        this.appListener = appListener;

        getInitialInfo();

        setUpUIStage();

        setUpCamAndRenderer();

        Gdx.input.setInputProcessor(inputMultiplexer);

        startGame();
    }

    /**
     * <p>Starts the game by setting up and running a round.</p>
     */
    private void startGame() {
        newRound();
    }

    /**
     * <p>Starts a new round (by dealing the cards).</p>
     */
    private void newRound() {
        grunner.setUpRound();
    }

    /**
     * <p>Checks if card selection is valid, and if so, runs.</p>
     */
    private void commitRobotProgram() {
        if(!cardSelectionValid()) return;

        chosenCards = new ArrayList<>();
        chosenCards.add(givenCards.get(reg1index));
        chosenCards.add(givenCards.get(reg2index));
        chosenCards.add(givenCards.get(reg3index));
        chosenCards.add(givenCards.get(reg4index));
        chosenCards.add(givenCards.get(reg5index));
        giveGamerunnerChosenCards();
        grunner.runRound();

        newRound();
    }

    //=========Backend-communication====================================================

    /**
     * <p>Gives GameRunner-instance currently chosen cards.</p>
     */
    private void giveGamerunnerChosenCards() {
        grunner.giveGameCards(chosenCards);
    }

    /**
     * <p>Gives GameScreen its new set of cards.</p>
     * @param cards Given cards
     */
    public void giveCards(ArrayList<Card> cards) { givenCards = cards; }

    /**
     * <p>Checks that every register has selected a card and that noe indices are duplicated.</p>
     * @return Bool
     */
    private boolean cardSelectionValid() {
        if(     reg1index == -1 ||
                reg2index == -1 ||
                reg3index == -1 ||
                reg4index == -1 ||
                reg5index == -1
        ) return false;
        for(int i = 0; i < 5; i++) {
            int indexCard = chosenCardsIndices.get(i); //Should only occur once
            int duplicateCounter = 0;
            for(int index : chosenCardsIndices)
                if(index == indexCard) duplicateCounter++;
            if(duplicateCounter>1) return false;
        }
        return true;
    }


    //=========HUD-updating=============================================================

    /**
     * <p>Called when GUI has to be updated after changing values</p>
     */
    public void updateGUI() {
        updateCardSelection();
        updateWindowCards();
    }

    /**
     * <p>Updates cards displayed in info-window.</p>
     */
    public void updateWindowCards() {
        cardWindow.clear();
        for(Card card : givenCards) {
            String steps = card instanceof StepCard //Possible step-information.
                    ? ((StepCard) card).getSteps() + " "
                    : "";
            cardWindow.add(steps + card.getName() + "; Priority: " + card.getPriority());
            cardWindow.row();
        }
    }

    /**
     * <p>Updates selection-boxes and indexes.</p>
     */
    private void updateCardSelection() {

        Array<String> givenCardStrings = new Array<>();
        for(Card card : givenCards)
            if(card instanceof StepCard)
                givenCardStrings.add(((StepCard) card).getSteps() + " " + card.getName() + ": Pri " + card.getPriority());
            else
                givenCardStrings.add(card.getName() + ": Pri " + card.getPriority());

        Array<String> reg1array = new Array<>();
        reg1array.add(baseOptionReg1);
        reg1array.addAll(givenCardStrings);
        reg1.setItems(reg1array);

        Array<String> reg2array = new Array<>();
        reg2array.add(baseOptionReg2);
        reg2array.addAll(givenCardStrings);
        reg2.setItems(reg2array);

        Array<String> reg3array = new Array<>();
        reg3array.add(baseOptionReg3);
        reg3array.addAll(givenCardStrings);
        reg3.setItems(reg3array);

        Array<String> reg4array = new Array<>();
        reg4array.add(baseOptionReg4);
        reg4array.addAll(givenCardStrings);
        reg4.setItems(reg4array);

        Array<String> reg5array = new Array<>();
        reg5array.add(baseOptionReg5);
        reg5array.addAll(givenCardStrings);
        reg5.setItems(reg5array);

        /* Register 1 */
        reg1index = reg1.getSelectedIndex()-1;

        /* Register 2 */
        reg2index = reg2.getSelectedIndex()-1;

        /* Register 3 */
        reg3index = reg3.getSelectedIndex()-1;

        /* Register 4 */
        reg4index = reg4.getSelectedIndex()-1;

        /* Register 5 */
        reg5index = reg5.getSelectedIndex()-1;

        chosenCardsIndices = new ArrayList<>();
        chosenCardsIndices.add(reg1index);
        chosenCardsIndices.add(reg2index);
        chosenCardsIndices.add(reg3index);
        chosenCardsIndices.add(reg4index);
        chosenCardsIndices.add(reg5index);
    }


    //=========Set-up===================================================================

    /**
     * <p>Gets all initial information. Called in constructor.</p>
     */
    public void getInitialInfo() {
        rr_app = appListener.getRRApp();
        grunner = rr_app.getGameRunner();
        grunner.giveGameScreen(this);
        localPlayer = grunner.getLocal();

        /* Get map-value info */
        mapWidth = grunner.getBoardWidth();
        mapHeight = grunner.getBoardHeight();

        /* Viewport used by every component */
        uiView = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        /* Head of input-handling */
        inputMultiplexer = new InputMultiplexer();
    }

    /**
     * <p>Sets up the ui for our game-controls, registers and so on.</p>
     */
    private void setUpControlTable() {
        /* Bottom-table */
        controlTable = new Table();
        controlTable.setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture(CONTROL_TABLE_BACKGROUND))));
        controlTable.setSize(uiView.getScreenWidth(), uiView.getScreenHeight()/5f);
        controlTable.left().top();

        /* Register-titles */
        Label reg1Label = new Label(baseOptionReg1, appListener.getSkin());
        controlTable.add(reg1Label);
        Label reg2Label = new Label(baseOptionReg2, appListener.getSkin());
        controlTable.add(reg2Label);
        Label reg3Label = new Label(baseOptionReg3, appListener.getSkin());
        controlTable.add(reg3Label);
        Label reg4Label = new Label(baseOptionReg4, appListener.getSkin());
        controlTable.add(reg4Label);
        Label reg5Label = new Label(baseOptionReg5, appListener.getSkin());
        controlTable.add(reg5Label);

        controlTable.row();

        /* Register card-selection (Initially the lists are just the register-name) */
        reg1 = new SelectBox<>(appListener.getSkin());
        reg1.setItems(baseOptionReg1);
        reg1.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateCardSelection();
                return true;
            }
        });
        controlTable.add(reg1);

        reg2 = new SelectBox<>(appListener.getSkin());
        reg2.setItems(baseOptionReg2);
        reg2.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateCardSelection();
                return true;
            }
        });
        controlTable.add(reg2);

        reg3 = new SelectBox<>(appListener.getSkin());
        reg3.setItems(baseOptionReg3);
        reg3.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateCardSelection();
                return true;
            }
        });
        controlTable.add(reg3);

        reg4 = new SelectBox<>(appListener.getSkin());
        reg4.setItems(baseOptionReg4);
        reg4.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateCardSelection();
                return true;
            }
        });
        controlTable.add(reg4);

        reg5 = new SelectBox<>(appListener.getSkin());
        reg5.setItems(baseOptionReg5);
        reg5.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                updateCardSelection();
                return true;
            }
        });
        controlTable.add(reg5);

        /* Commit card-button */
        commitButton = new TextButton("Commit Program",appListener.getSkin());
        commitButton.setSize(appListener.getButtWidth(),appListener.getButtHeight());
        commitButton.addListener(
                new InputListener(){
                    @Override
                    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                        updateCardSelection();
                        commitRobotProgram();
                    }
                    @Override
                    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }
                });
        controlTable.add(commitButton);

        /* Finally */
        uiStage.addActor(controlTable);
    }

    /**
     * <p>Sets up our ui for displaying in-game info</p>
     */
    private void setUpInfoTable() {
        infoTable = new Table();
        infoTable.setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture(CONTROL_TABLE_BACKGROUND))));
        infoTable.setSize(uiView.getWorldWidth()/4, uiView.getWorldHeight());
        infoTable.setPosition(uiView.getWorldWidth()*3/4,0);

        cardWindow = new Window("Cards", appListener.getSkin());
        cardWindow.setSize(200,500);
        cardWindow.setPosition(500,500);
        infoTable.add(cardWindow);

        /* Finally */
        uiStage.addActor(infoTable);
    }

    /**
     * <p>Sets up cam and renderer (The board-visuals)</p>
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

        zoomCam(START_ZOOM_OUT);

        inputMultiplexer.addProcessor(this);
    }

    /**
     * <p>Sets up the UI-stage (HUD)</p>
     */
    private void setUpUIStage() {
        uiStage = new Stage(uiView);

        /* Sets up hud */
        setUpInfoTable();
        setUpControlTable();

        inputMultiplexer.addProcessor(uiStage);

        uiStage.act();
        uiStage.draw();
    }


    //=========Rendering================================================================

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

    private void updateRendWithCam() {
        rend.setView(cam);
    }

    /**
     * <p>Positions camera at the center of the map.</p>
     */
    private void centerCamOnMapCenter() {
        float mapCenterX = MAP_UNIT * mapWidth/2;
        float mapCenterY = MAP_UNIT * mapHeight/2;
        cam.position.set(mapCenterX,mapCenterY,0);
        cam.update();
        rend.setView(cam);
    }

    /**
     * <p>Centers cam on given player.</p>
     * @param p Player
     */
    private void centerCamOnPlayer(Player p) {
        float playerX = p.getRobot().getPosition().getX() * MAP_UNIT;
        float playerY = p.getRobot().getPosition().getY() * MAP_UNIT;

        cam.position.set(playerX + CAM_OFFSET_X, playerY + CAM_OFFSET_Y,0);
        cam.update();
        updateRendWithCam();
    }


    //=========Interface-implementations================================================

    @Override
    public void create() {
    }

    @Override
    public void render() {
    }


    //=========Input-listening==========================================================

    @Override
    public boolean scrolled(int amount) {
        zoomCam(((float) amount)/2f);
        return false;
    }

    @Override
    public void resize(int width, int height) {
        uiView.update(width,height);
        uiStage.act();
        uiStage.draw();
        cam.update();
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
    public void show() {
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

    /**
     * <p>Zooms camera.</p>
     * @param zoom Zoom
     */
    private void zoomCam(float zoom) {
        cam.zoom += zoom;
        cam.update();
        rend.setView(cam);
    }


    //=========Other interface-implementations==========================================

    @Override
    public void dispose() {
        uiStage.dispose();
        rend.dispose();
    }

}
