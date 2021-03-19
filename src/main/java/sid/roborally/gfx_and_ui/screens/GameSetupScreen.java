package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sid.roborally.application_functionality.Player;
import sid.roborally.application_functionality.RRApplication;
import sid.roborally.application_functionality.reference.Map;
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
    private Button startGameButton, backButton;
    private SelectBox<String> mapBox;
    private SelectBox<Integer> playerBox;
    private Table menuTable;
    private RRApplication rr_app;
    private ArrayList<Player> players;

    public GameSetupScreen(AppListener appListener) {
        this.appListener = appListener;
        rr_app = appListener.getRRApp();
        this.players = new ArrayList<>();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = appListener.getSkin();

        Array<String> mapNames = new Array<>();
        addMaps(mapNames);

        mapBox = new SelectBox<>(skin, "default");
        mapBox.setItems(mapNames);

        /* Creating a drop down menu to select amount of players */
        playerBox = new SelectBox<>(skin, "default");
        playerBox.setPosition(Gdx.graphics.getWidth() / 2f + 150f, Gdx.graphics.getHeight() / 2f + 83f);
        playerBox.setSize(125, 35);
        playerBox.setItems(1, 2, 3, 4, 5, 6, 7, 8);

        startGameButton = new TextButton("Singleplayer",skin,"default");
        startGameButton.setTransform(true);

        backButton = new TextButton("Back",skin,"default");
        backButton.setTransform(true);

        menuTable = new Table();
        addButtons(menuTable);


        startGameButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                addPlayers(players, playerBox.getSelected());
                setUpGame(players);
                appListener.setScreen(new GameScreen(appListener));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
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

        stage.addActor(playerBox);
        stage.addActor(menuTable);
    }

    /**
     * Starts the game with the selected map, and the selected amount of players
     * @param players a list of players
     */
    private void setUpGame(ArrayList<Player> players) {
        for (Player player : players)
            rr_app.getGameRunner().addPlayer(player);
        rr_app.getGameRunner().setUpGame(Map.values()[mapBox.getSelectedIndex()]);
        rr_app.startGame();
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
     * Adds all needed buttons to the screen
     * @param menuTable table to hold all buttons and drop down menus
     */
    private void addButtons(Table menuTable) {
        menuTable.add(mapBox);
        menuTable.row();
        menuTable.add(startGameButton);
        menuTable.row();
        menuTable.add(backButton);
        menuTable.setFillParent(true);
    }

    /**
     * Adds the amount of players you want to have into an ArrayList
     * @param players ArrayList to hold players
     * @param amount amount of players
     */
    private void addPlayers(ArrayList<Player> players, int amount) {
        Player localPlayer = new Player(1, true);
        localPlayer.setLocal();
        players.add(localPlayer);
        if (amount > 1) {
            for (int i = 2; i <= amount; i++) {
                Player newPlayer = new Player(i, true);
                players.add(newPlayer);
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.8F, .5F, .1F,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        cam.update();
        appListener.batch.setProjectionMatrix(cam.combined);

        appListener.batch.begin();
        appListener.batch.end();

        stage.act();
        stage.draw();
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
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
