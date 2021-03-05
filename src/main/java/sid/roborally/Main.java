package sid.roborally;


import sid.roborally.application_functionality.CommandLineTool;
import sid.roborally.application_functionality.RRApplication;

/**
 * <h3>Main</h3>
 * @author Daniel Janols
 */
public class Main {
    public static void main(String[] args)
    {
        /* Command-line tool that has menu's for the application. */
        CommandLineTool clt = new CommandLineTool();

        /* This instance will connect and control the whole program */
        RRApplication rr_app = new RRApplication();
        clt.giveApp(rr_app);

        /* Start program in command-line */
        clt.run();
    }
}
