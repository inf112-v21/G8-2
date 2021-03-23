package sid.roborally;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import sid.roborally.gfx_and_ui.AppListener;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.awt.*;

/**
 * <h3>Main</h3>
 * @author Daniel Janols
 */
public class Main {

    private static final int WIDTH = 1024, HEIGHT = 780;

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Robo-Rally");
        config.setWindowedMode(WIDTH, HEIGHT);
        config.setMaximized(true);
        new Lwjgl3Application(new AppListener(), config);
    }
}
