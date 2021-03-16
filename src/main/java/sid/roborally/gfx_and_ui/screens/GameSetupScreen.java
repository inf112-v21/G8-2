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


public class GameSetupScreen implements Screen {

    private final AppListener appListener;
    private OrthographicCamera cam;
    private Stage stage;
    private Skin skin;
    private Button startGameButton, backButton;
    private SelectBox<String> mapBox;
    private Table menuTable;
    private RRApplication rr_app;

    public GameSetupScreen(AppListener appListener) {
        this.appListener = appListener;
        rr_app = appListener.getRRApp();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = appListener.getSkin();

        Array<String> mapNames = new Array<>();
        for (Map map : Map.values())
            mapNames.add(map.name());

        mapBox = new SelectBox<>(skin, "default");
        mapBox.setItems(mapNames);

        startGameButton = new TextButton("Singleplayer",skin,"default");
        startGameButton.setTransform(true);

        backButton = new TextButton("Back",skin,"default");
        backButton.setTransform(true);

        Player localPlayer = new Player(1, true);
        localPlayer.setLocal();

        menuTable = new Table();
        menuTable.add(mapBox);
        menuTable.row();
        menuTable.add(startGameButton);
        menuTable.row();
        menuTable.add(backButton);
        menuTable.setFillParent(true);


        startGameButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                rr_app.getGameRunner().addPlayer(localPlayer);
                rr_app.getGameRunner().setUpGame(Map.values()[mapBox.getSelectedIndex()]);
                rr_app.startGame();
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

        stage.addActor(menuTable);
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
