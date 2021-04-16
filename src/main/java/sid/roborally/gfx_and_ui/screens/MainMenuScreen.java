package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    private Button singleplayerButton,multiplayerButton,optionsButton,exitButton;
    private Table buttonTable, backgroundTable;
    private Window titleWindow;

    public MainMenuScreen(final AppListener appListener) {
        this.appListener = appListener;
        this.buttonTable = new Table();
        this.backgroundTable = new Table();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);

        stage = new Stage(new ScreenViewport());

        buttonTable.setFillParent(true);
        backgroundTable.setFillParent(true);
        stage.addActor(backgroundTable);
        stage.addActor(buttonTable);

        Gdx.input.setInputProcessor(stage);

        skin = appListener.getSkin();

        this.titleWindow = new Window("", skin);
        titleWindow.setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture("assets/application_skin/RoboRally.png"))));

        backgroundTable.setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture("assets/application_skin/GameBackground.png"))));

        singleplayerButton = new TextButton("Singleplayer",skin,"default");
        multiplayerButton = new TextButton("Multiplayer",skin,"default");
        optionsButton = new TextButton("Options",skin,"default");
        exitButton = new TextButton("Exit",skin,"default");


        singleplayerButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                appListener.setScreen(new SingleplayerSetupScreen(appListener));
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
        backgroundTable.add(titleWindow).padBottom(700);
        buttonTable.add(singleplayerButton);
        buttonTable.row();
        buttonTable.add(multiplayerButton).width(singleplayerButton.getWidth());
        buttonTable.row();
        buttonTable.add(optionsButton).width(singleplayerButton.getWidth());
        buttonTable.row();
        buttonTable.add(exitButton).width(singleplayerButton.getWidth());
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.8F, .5F, .1F,1);
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