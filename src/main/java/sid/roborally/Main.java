package sid.roborally;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import sid.roborally.gfx_and_ui.RoboRallyAppListener;

public class Main {
    public static void main(String[] args)
    {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Robo rally");
        config.setWindowedMode(500, 500);

        new Lwjgl3Application(new RoboRallyAppListener(), config);
    }
}
