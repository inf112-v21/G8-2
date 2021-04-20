package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sid.roborally.application_functionality.Player;
import sid.roborally.application_functionality.RRApplication;
import sid.roborally.application_functionality.reference.Map;
import sid.roborally.application_functionality.reference.PlayerTexture;
import sid.roborally.gfx_and_ui.AppListener;

import java.util.ArrayList;

/**
 * <h3>GameSetupScreen</h3>
 * <p>A screen where you are able to setup a game.</p>
 *
 * @author Andreas Henriksen & Terje Trommestad
 */
public class GameSetupScreen implements Screen {

    private final AppListener appListener;
    private OrthographicCamera cam;
    private Stage stage;
    private Skin skin;
    private TextButton startGameButton, backButton;
    private SelectBox<String> mapBox, playerModelBox;
    private SelectBox<Integer> playerBox;
    private Table buttonTable;
    private RRApplication rr_app;
    private ArrayList<Player> players;
    private Window window, playerModelWindow;
    private PlayerTexture localChosenPlayerTexture;

    public GameSetupScreen(AppListener appListener) {
        this.appListener = appListener;
        rr_app = appListener.getRRApp();
        this.players = new ArrayList<>();
        localChosenPlayerTexture = PlayerTexture.Player1;

        skin = appListener.getSkin();
        this.window = new Window("Map Preview", skin);
        this.window.setPosition(window.getWidth()-100,window.getHeight());
        this.window.setSize(500,500);

        this.playerModelWindow = new Window("Model Preview",skin);
        this.playerModelWindow.setPosition(window.getWidth()+800,window.getHeight()-150);
        this.playerModelWindow.setSize(300,300);

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = appListener.getSkin();

        Array<String> mapNames = new Array<>();
        addMaps(mapNames);

        mapBox = new SelectBox<>(skin, "default");
        mapBox.setItems(mapNames);

        Array<String> playerModelNames = new Array<>();
        addPlayerModels(playerModelNames);

        playerModelBox = new SelectBox<>(skin,"default");
        //playerModelBox.setPosition(Gdx.graphics.getWidth() / 2f + 350f, Gdx.graphics.getHeight() / 2f + 83f);
        playerModelBox.setSize(125, 35);
        playerModelBox.setItems("Owl","Tank","X-Wing");

        /* Creating a drop down menu to select amount of players */
        playerBox = new SelectBox<>(skin, "default");
        playerBox.setPosition(Gdx.graphics.getWidth() / 2f + 150f, Gdx.graphics.getHeight() / 2f + 83f);
        playerBox.setSize(125, 35);
        playerBox.setItems(1, 2, 3, 4, 5, 6, 7, 8);

        startGameButton = new TextButton("Start",skin,"default");
        startGameButton.setTransform(true);

        backButton = new TextButton("Back",skin,"default");
        backButton.setTransform(true);

        buttonTable = new Table();
        addButtons(buttonTable);
        buttonTable.setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture("assets/application_skin/GameBackground.png"))));

        /*
         * Input for selectbox (mapbox)
         */
        mapBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                updateMapPreview();
            }
        }
        );

        /*
         * Input for playermodelbox
         */
        playerModelBox.addListener(new ChangeListener() { //
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                updatePlayerModelPreview();
                updateSelectedPlayerTexture();
            }
        });

        backButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                appListener.setScreen(new MainMenuScreen(appListener));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        updatePlayerModelPreview();
        updateMapPreview();
        stage.addActor(buttonTable);
        stage.addActor(window);
        stage.addActor(playerModelWindow);

    }



    public void setLocalChosenPlayerTexture(PlayerTexture pt) { localChosenPlayerTexture = pt; }
    public PlayerTexture getLocalChosenPlayerTexture() { return localChosenPlayerTexture; }

    public Table getButtonTable() {
        return buttonTable;
    }


    /**
     * Sets player model to the chosen item in playermodelbox
     */
    private void updateSelectedPlayerTexture() {
        int chosen = playerModelBox.getSelectedIndex();
        setLocalChosenPlayerTexture(PlayerTexture.values()[chosen]);
    }

    /**
     * Sets preview image for map
     */
    private void updateMapPreview() {

        if (mapBox.getSelected().equals(Map.DemoMap.name())) {
            window.setBackground(new TextureRegionDrawable(new TextureRegion(
                    new Texture("assets/maps/example.png"))));
        }
        if (mapBox.getSelected().equals(Map.TwoPlayerDemo.name())) {
            window.setBackground(new TextureRegionDrawable(new TextureRegion(
                    new Texture("assets/maps/2player2flag.png"))));
        }
        if (mapBox.getSelected().equals(Map.BigMap.name())) {
            window.setBackground(new TextureRegionDrawable(new TextureRegion(
                    new Texture("assets/maps/bigMap.png"))));
        }

    }

    /**
     * Sets preview background
     */
    private void updatePlayerModelPreview() {
        if (playerModelBox.getSelected().equals("Owl")) {
            playerModelWindow.setBackground(new TextureRegionDrawable(new TextureRegion(
                    new Texture("assets/player_tex/player1.png"),0,0,300,300)));
        }
        if (playerModelBox.getSelected().equals("Tank")) {
            playerModelWindow.setBackground(new TextureRegionDrawable(new TextureRegion(
                    new Texture("assets/player_tex/tank.png"),0,0,300,300)));
        }
        if (playerModelBox.getSelected().equals("X-Wing")) {
            playerModelWindow.setBackground(new TextureRegionDrawable(new TextureRegion(
                    new Texture("assets/player_tex/xwing.png"),0,0,300,300)));
        }
    }

    public SelectBox<String> getMapBox() {
        return mapBox;
    }

    public Skin getSkin() {
        return skin;
    }

    public SelectBox<Integer> getPlayerBox() {
        return playerBox;
    }

    public RRApplication getRR_app() {
        return rr_app;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Adds all available maps to an Array
     * @param maps array of maps
     */
    private void addMaps(Array<String> maps) {
        for (Map map : Map.values())
            maps.add(map.name());
    }

    /**
     * Adds all available playermodels to an Array
     * @param pmodel array of maps
     */
    private void addPlayerModels(Array<String> pmodel) {
        for (PlayerTexture player : PlayerTexture.values())
            pmodel.add(player.name());
    }

    /**
     * Adds all needed buttons to the screen
     * @param table table to hold all buttons and drop down menus
     */
    private void addButtons(Table table) {
        table.add(startGameButton).padBottom(20);
        table.row();
        table.add(mapBox);
        table.add(playerBox).padLeft(20).width(playerBox.getWidth()-10);
        table.add(playerModelBox).padLeft(20);
        table.row();
        table.add(backButton).padTop(80);
        table.setFillParent(true);
    }


    public TextButton getStartGameButton() {
        return startGameButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.8F, .5F, .1F, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        cam.update();

        stage.act();
        stage.draw();
        }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
}
