package sid.roborally.gfx_and_ui.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sid.roborally.gfx_and_ui.AppListener;

/**
 * <h3>OptionsScreen</h3>
 * <p> The only option is going back! Amazing. </p>
 *
 * @author Terje Trommestad
 */
public class OptionsScreen implements Screen {

    final AppListener appListener;
    private OrthographicCamera cam;
    private Stage stage;
    private Skin skin;
    private Button fullscreenButton, windowedButton, backButton;
    private Table table;
    private int buttWidth,buttHeight;

    public OptionsScreen(final AppListener appListener) {

        this.appListener = appListener;

        buttWidth = appListener.getButtWidth();
        buttHeight = appListener.getButtWidth();
        this.table = new Table();
        stage = new Stage(new ScreenViewport());

        /* Theoden-background */
        table.setFillParent(true);
        table.setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture("assets/application_skin/youhavenopower.jpg"))));
        table.center();


        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);
        buttWidth = appListener.getButtWidth();
        buttHeight = appListener.getButtHeight();

                stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);

        skin = appListener.getSkin();

        fullscreenButton = new TextButton("Fullscreen", skin, "default");

        windowedButton = new TextButton("Windowed", skin, "default");

        backButton = new TextButton("Back", skin, "default");

        table.add(fullscreenButton);
        table.row();
        table.add(windowedButton);
        table.row();
        table.add(backButton);


        fullscreenButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                new FitViewport(9, 16);
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        windowedButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (Gdx.graphics.isFullscreen()) {
                    Gdx.graphics.setWindowedMode(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2+200);
                    new FitViewport(9, 16);
                }
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        backButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Start Game-button pushed");
                appListener.setScreen(new MainMenuScreen(appListener));
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Downtouch");
                return true;
            }
        });
        stage.addActor(table);
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
