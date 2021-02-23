package sid.roborally;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import sid.roborally.application_functionality.RoboRallyApplication;
import sid.roborally.gfx_and_ui.RoboRallyAppListener;
import com.badlogic.gdx.Gdx;

public class Main {
    public static void main(String[] args)
    {
        //This instance will connect and control the whole program.
        RoboRallyApplication rr_app = new RoboRallyApplication();

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Robo rally");
        config.setWindowedMode(500, 500);

        new Lwjgl3Application(new RoboRallyAppListener(rr_app), config);
    }
}
