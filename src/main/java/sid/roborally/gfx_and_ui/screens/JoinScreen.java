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
import com.badlogic.gdx.scenes.scene2d.ui.*;
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
    private Table table;
    TextField joinIP;
    TextField joinPort;

    public JoinScreen(final AppListener appListener) {
        this.appListener = appListener;
        buttWidth = appListener.getButtWidth();
        buttHeight = appListener.getButtWidth();
        this.table = new Table();
        stage = new Stage(new ScreenViewport());

        table.setFillParent(true);
        table.center();
        stage.addActor(table);


        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);

        Gdx.input.setInputProcessor(stage);

        skin = appListener.getSkin();

        joinIP = new TextField("", skin);
        joinIP.setColor(Color.RED);
        joinIP.setMessageText("Enter IP");

        joinPort = new TextField("", skin);
        joinPort.setColor(Color.RED);
        joinPort.setMessageText("Enter Port");

        backButton = new TextButton("Back", skin, "default");
        joinButton = new TextButton("Join game", skin,"default");

        table.add(joinIP);
        table.row();
        table.add(joinPort);
        table.row();
        table.add(joinButton);
        table.row();
        table.add(backButton);


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
