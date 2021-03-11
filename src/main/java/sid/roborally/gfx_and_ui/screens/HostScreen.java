package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sid.roborally.gfx_and_ui.AppListener;

/**
 * <h3>CardAction</h3>
 * <p>A screen where you have the option to host a game</p>
 *
 * @author Andreas Henriksen
 */
public class HostScreen implements Screen {

    private final AppListener appListener;
    private OrthographicCamera cam;
    private Stage stage;
    private Table table;
    private int buttWidth,buttHeight;

    public HostScreen(final AppListener appListener) {
        this.appListener = appListener;
        buttWidth = appListener.getButtWidth();
        buttHeight = appListener.getButtWidth();

        this.table = new Table();
        stage = new Stage(new ScreenViewport());

        table.setFillParent(true);
        table.setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture("assets/application_skin/youshallnotpass.png"))));

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

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
