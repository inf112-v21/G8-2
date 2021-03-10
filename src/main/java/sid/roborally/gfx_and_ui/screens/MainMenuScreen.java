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
 * @author Daniel Janols
 */
public class MainMenuScreen implements Screen {

    private final AppListener appListener;
    private OrthographicCamera cam;
    private Stage stage;
    private Skin skin;
    private Button startGameButton,multiplayerButton,demoGameButton,optionsButton,exitButton;

    private static final int BUTT_WIDTH = 140, BUTT_HEIGHT = 70;

    public MainMenuScreen(final AppListener appListener) {
        this.appListener = appListener;

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = appListener.getSkin();

        startGameButton = new TextButton("Singleplayer",skin,"default");
        startGameButton.setSize(BUTT_WIDTH,BUTT_HEIGHT);
        startGameButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        startGameButton.setTransform(true);

        multiplayerButton = new TextButton("Multiplayer",skin,"default");
        multiplayerButton.setSize(BUTT_WIDTH,BUTT_HEIGHT);
        multiplayerButton.setPosition(Gdx.graphics.getWidth()/2-80, Gdx.graphics.getHeight()/2-100);
        multiplayerButton.setTransform(true);

        demoGameButton = new TextButton("Demo Game",skin,"default");
        demoGameButton.setSize(BUTT_WIDTH,BUTT_HEIGHT);
        demoGameButton.setPosition(Gdx.graphics.getWidth()/2-80, Gdx.graphics.getHeight()/2-100);
        demoGameButton.setTransform(true);

        optionsButton = new TextButton("Options",skin,"default");
        optionsButton.setSize(BUTT_WIDTH,BUTT_HEIGHT);
        optionsButton.setPosition(Gdx.graphics.getWidth()/2-80, Gdx.graphics.getHeight()/2-200);
        optionsButton.setTransform(true);

        exitButton = new TextButton("Exit",skin,"default");
        exitButton.setSize(BUTT_WIDTH,BUTT_HEIGHT);
        exitButton.setPosition(Gdx.graphics.getWidth()/2-80, Gdx.graphics.getHeight()/2-300);
        exitButton.setTransform(true);



        startGameButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Start Game-button pushed");
                appListener.setScreen(new GameScreen(appListener));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Downtouch");
                return true;
            }
        });
        multiplayerButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Multiplayer pushed");
                appListener.setScreen(new MainMenuScreen(appListener));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Downtouch");
                return true;
            }
        });
        demoGameButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Demo Game pushed");
                appListener.setScreen(new GameScreen(appListener));
                //todo demo game
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Downtouch");
                return true;
            }
        });

        optionsButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Options pushed");
                appListener.setScreen(new OptionsScreen(appListener));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Downtouch");
                return true;
            }
        });

        exitButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Exit pushed");
                Gdx.app.exit();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Downtouch");
                return true;
            }
        });

        stage.addActor(multiplayerButton);
        stage.addActor(startGameButton);
        stage.addActor(demoGameButton);
        stage.addActor(optionsButton);
        stage.addActor(exitButton);

    }

    private void startGame() {

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