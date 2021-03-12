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
 * <h3>CardAction</h3>
 * <p>A screen for the multiplayer options in the game.</p>
 *
 * @author Andreas Henriksen
 */
public class MultiplayerScreen implements Screen{

    private final AppListener appListener;
    private OrthographicCamera cam;
    private Stage stage;
    private Skin buttonSkin;
    private Button hostButton, joinButton, backButton;
    private int buttWidth,buttHeight;

    public MultiplayerScreen(final AppListener appListener) {
        this.appListener = appListener;
        buttWidth = appListener.getButtWidth();
        buttHeight = appListener.getButtHeight();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        buttonSkin = appListener.getSkin();

        hostButton = new TextButton("Host game",buttonSkin,"default");
        hostButton.setSize(buttWidth,buttHeight);
        hostButton.setPosition(Gdx.graphics.getWidth()/2f-80, Gdx.graphics.getHeight()/2f);
        hostButton.setTransform(true);

        joinButton = new TextButton("Join game",buttonSkin,"default");
        joinButton.setSize(buttWidth,buttHeight);
        joinButton.setPosition(Gdx.graphics.getWidth()/2f-80f, Gdx.graphics.getHeight()/2f-100f);
        joinButton.setTransform(true);

        backButton = new TextButton("Go back",buttonSkin,"default");
        backButton.setSize(buttWidth,buttHeight);
        backButton.setPosition(Gdx.graphics.getWidth()/2f-80f, Gdx.graphics.getHeight()/2f-200f);
        backButton.setTransform(true);

        hostButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Host button pushed");
                appListener.setScreen(new HostScreen(appListener));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Downtouch");
                return true;
            }
        });
        joinButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Join button pushed");
                appListener.setScreen(new JoinScreen(appListener));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Downtouch");
                return true;
            }
        });
        backButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Back button pushed");
                appListener.setScreen(new MainMenuScreen(appListener));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Downtouch");
                return true;
            }
        });

        stage.addActor(hostButton);
        stage.addActor(joinButton);
        stage.addActor(backButton);
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
