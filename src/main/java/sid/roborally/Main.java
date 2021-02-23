package sid.roborally;


import sid.roborally.application_functionality.CommandLineTool;
import sid.roborally.application_functionality.RoboRallyApplication;


public class Main {
    public static void main(String[] args)
    {
        //Command-line tool that has menu's for the application.
        CommandLineTool clt = new CommandLineTool();

        //This instance will connect and control the whole program.
        RoboRallyApplication rr_app = new RoboRallyApplication();
        clt.giveApp(rr_app);

        /* Start program in command-line */
        clt.run();

        //TODO: Skal ikke starte app automatisk
        //rr_app.setUpLibgdxApplication();
    }
}
