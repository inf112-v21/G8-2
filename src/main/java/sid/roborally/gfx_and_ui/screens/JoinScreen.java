package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sid.roborally.gfx_and_ui.AppListener;

/**
 * <h3>JoinScreen</h3>
 * <p>A screen where you can join a multiplayer game.</p>
 *
 * @author Andreas Henriksen
 */
public class JoinScreen implements Screen {
    private final AppListener appListener;
    private OrthographicCamera cam;
    private Stage stage;
    private Skin skin;
    private Button joinButton,backButton;
    private int buttWidth,buttHeight;
    TextField joinIP;
    TextField joinPort;

    public JoinScreen(final AppListener appListener) {
        this.appListener = appListener;

        buttWidth = appListener.getButtWidth();
        buttHeight = appListener.getButtWidth();

        stage = new Stage(new ScreenViewport());

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);
        buttWidth = appListener.getButtWidth();
        buttHeight = appListener.getButtHeight();

        Gdx.input.setInputProcessor(stage);

        skin = appListener.getSkin();

        joinIP = new TextField("", skin);
        joinIP.setColor(Color.RED);
        joinIP.setMessageText("Enter IP");
        joinIP.setPosition(200,500);

        joinPort = new TextField("", skin);
        joinPort.setColor(Color.RED);
        joinPort.setMessageText("Enter Port");
        joinPort.setPosition(200,400);

        stage.addActor(joinIP);
        stage.addActor(joinPort);

        backButton = new TextButton("Back", skin, "default");
        backButton.setSize(buttWidth, buttHeight);
        backButton.setPosition(Gdx.graphics.getWidth() / 2f - 80f, Gdx.graphics.getHeight() / 2f);
        backButton.setTransform(true);

        backButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                appListener.setScreen(new MainMenuScreen(appListener));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        joinButton = new TextButton("Join game",skin,"default");
        joinButton.setSize(buttWidth,buttHeight);
        joinButton.setPosition(Gdx.graphics.getWidth()/2f-80f, Gdx.graphics.getHeight()/2f-100f);
        joinButton.setTransform(true);

        joinButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //appListener.setScreen(new MainMenuScreen(appListener));
                System.out.println("Attempting to join: "+ joinIP.getText() + " on port " + joinPort.getText());
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(backButton);
        stage.addActor(joinButton);
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
