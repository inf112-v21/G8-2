package sid.roborally;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import sid.roborally.application_functionality.CommandLineTool;
import sid.roborally.application_functionality.RoboRallyApplication;
import sid.roborally.gfx_and_ui.RoboRallyAppListener;

public class Main {
    public static void main(String[] args)
    {
        //Command-line tool that has menu's for the application.
        CommandLineTool clt = new CommandLineTool();

        //This instance will connect and control the whole program.
        RoboRallyApplication rr_app = new RoboRallyApplication();
        clt.giveApp(rr_app);

        rr_app.setUpLibgdxApplication();
    }
}
