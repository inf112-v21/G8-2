package sid.roborally.gfx_and_ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import sid.roborally.application_functionality.RRApplication;
import sid.roborally.gfx_and_ui.screens.MainMenuScreen;

/**
 * <h3>AppListener</h3>
 * <p>The applistener for the application-gui.</p>
 * @author Daniel Janols
 */
public class AppListener extends Game {

    private Skin skin;
    private SpriteBatch batch;
    private BitmapFont font;
    private RRApplication rr_app;

    private static final int BUTT_WIDTH = 150, BUTT_HEIGHT = 100;

    public void create() {
        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("assets/application_skin/rusty-robot-ui.json"));
        font = new BitmapFont();
        rr_app = new RRApplication(); //Our program
        this.setScreen(new MainMenuScreen(this));
    }

    public SpriteBatch getBatch() { return batch; }

    public BitmapFont getFont() { return font; }

    public Skin getSkin() { return skin; }

    public RRApplication getRRApp() { return rr_app; }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public int getButtWidth() {
        return BUTT_WIDTH;
    }

    public int getButtHeight() {
        return BUTT_HEIGHT;
    }
}
