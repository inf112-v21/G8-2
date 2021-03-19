package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sid.roborally.gfx_and_ui.AppListener;

/**
 * <h3>MainMenuScreen</h3>
 * <p>Main menu screen for the application-gui.</p>
 * @author Daniel Janols
 */
public class MainMenuScreen implements Screen {

    private final AppListener appListener;
    private OrthographicCamera cam;
    private Stage stage;
    private Skin skin;
    private Button startGameButton,multiplayerButton,optionsButton,exitButton;
    private int buttWidth,buttHeight;

    public MainMenuScreen(final AppListener appListener) {
        this.appListener = appListener;
        buttWidth = appListener.getButtWidth();
        buttHeight = appListener.getButtHeight();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = appListener.getSkin();

        startGameButton = new TextButton("Singleplayer",skin,"default");
        startGameButton.setSize(buttWidth,buttHeight);
        startGameButton.setPosition(Gdx.graphics.getWidth()/2f-80f, Gdx.graphics.getHeight()/2f);
        startGameButton.setTransform(true);

        multiplayerButton = new TextButton("Multiplayer",skin,"default");
        multiplayerButton.setSize(buttWidth,buttHeight);
        multiplayerButton.setPosition(Gdx.graphics.getWidth()/2f-80f, Gdx.graphics.getHeight()/2f-75f);
        multiplayerButton.setTransform(true);

        optionsButton = new TextButton("Options",skin,"default");
        optionsButton.setSize(buttWidth,buttHeight);
        optionsButton.setPosition(Gdx.graphics.getWidth()/2f-80f, Gdx.graphics.getHeight()/2f-75*2f);
        optionsButton.setTransform(true);

        exitButton = new TextButton("Exit",skin,"default");
        exitButton.setSize(buttWidth,buttHeight);
        exitButton.setPosition(Gdx.graphics.getWidth()/2f-80f, Gdx.graphics.getHeight()/2f-75*3f);
        exitButton.setTransform(true);

        startGameButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                appListener.setScreen(new GameSetupScreen(appListener));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        multiplayerButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                appListener.setScreen(new MultiplayerScreen(appListener));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        optionsButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                appListener.setScreen(new OptionsScreen(appListener));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        exitButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(multiplayerButton);
        stage.addActor(startGameButton);
        stage.addActor(optionsButton);
        stage.addActor(exitButton);
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