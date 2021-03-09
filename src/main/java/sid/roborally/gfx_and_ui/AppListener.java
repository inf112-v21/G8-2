package sid.roborally.gfx_and_ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sid.roborally.application_functionality.RRApplication;
import sid.roborally.gfx_and_ui.screens.MainMenuScreen;

public class AppListener extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    private RRApplication rr_app;

    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        rr_app = new RRApplication();
        this.setScreen(new MainMenuScreen(this));
    }

    public RRApplication getRRApp() { return rr_app; }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
