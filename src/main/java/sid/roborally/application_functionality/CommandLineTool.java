package sid.roborally.application_functionality;

import sid.roborally.application_functionality.comm_line.GameCommandLine;
import sid.roborally.application_functionality.connection.Client;
import sid.roborally.application_functionality.connection.Server;
import sid.roborally.application_functionality.reference.Map;
import java.util.Scanner;

public class CommandLineTool {

    private RRApplication rr_app; //RoboRallyApplication
    private GameCommandLine gcl;

    Scanner sc; //Input-scanner

    private static String MAIN_MENU_WELCOME = "\nWelcome to RoboRally. Select one of the following options:\n";
    private static String SETUP_SERVER_OPTIONS = "\n1. Create a new server. (write \"1\"))\n" +
                                                "2. Join a current game. (write \"2\")\n" +
                                                "0. Quit application";
    private static int SETUP_SERVER_OPTIONS_NUM = 3;

    private static String MAIN_MENU_OPTIONS = "1. Start a new game (write \"1\")" + "\n0. Quit application";
    private static int MAIN_MENU_OPTIONS_NUM = 2;

    private static String NUM_PLAYERS_CHOICE =
                            "\n1. 2 Players\n" +
                            "2. 3 players\n"+
                            "3. 4 players\n"+
                            "4. 5 players\n"+
                            "5. 6 players\n"+
                            "6. 7 players\n"+
                            "7. 8 players\n"+
                            "0. Quit application";
    private static int NUM_PLAYERS_CHOICE_NUMCHOICES = 8;

    private static String NEW_GAME_MENU_WELCOME = "\nNew Game Menu. Select one of the following options:\n";
    private static String NEW_GAME_MENU_OPTIONS =   "1. Start a new demo-game (write \"1\")\n" +
                                                    "2. Go back to Main Menu\n" +
                                                    "0. Quit application";
    private static int NEW_GAME_MENU_OPTIONS_NUM = 3;

    public CommandLineTool() {}

    public void giveApp(RRApplication app)
    {
        this.rr_app = app;
    }

    public void run()
    {
        sc = new Scanner(System.in);
        serverSetupMenu();
    }

    /*
     * Input-parser.
     */

    /**
     * <p>Gets the max number of input and returns the first valid number given.</p>
     * @param optionsNum
     * @return int - ZERO if no valid input was given (shouldn't ever happen).
     */
    private int getValidInput(int optionsNum)
    {
        int optionChosen;
        while(sc.hasNextInt())
        {
            optionChosen = sc.nextInt();
            if(optionChosen >= 0 && optionChosen <= optionsNum)
                return optionChosen;
            else System.out.println("\nPlease give a valid input...\n");
        }
        return 0;
    }


    /*
     * Main Menu-methods
     */

    /**
     * <p>Runs a command-line Main Menu</p>
     */
    private void commandLineMainMenu()
    {
        System.out.println(MAIN_MENU_WELCOME + MAIN_MENU_OPTIONS);

        /* Get and act on input */
        int optionChosen = getValidInput(MAIN_MENU_OPTIONS_NUM);
        switch (optionChosen)
        {
            case 0: return;
            case 1: { newGameMenu(); break; }
            default:
                throw new IllegalStateException("Unexpected value: " + optionChosen);
        }
    }

    /*
     * New Game Menu-methods
     */

    private void newGameMenu()
    {
        System.out.println(NEW_GAME_MENU_WELCOME + NEW_GAME_MENU_OPTIONS);

        /* Get and act on input */
        int optionChosen = getValidInput(NEW_GAME_MENU_OPTIONS_NUM);
        switch (optionChosen)
        {
            case 0: return;
            case 1: //Set up and run demogame (demo_game_option)
                {
                    //TODO: numberOfPlayersMenu(Map.TwoPlayerDemo);
                    rr_app.setUpLibgdxApplication();
                    rr_app.setUpDemo();
                    break;
                }
            case 2:
                commandLineMainMenu(); break; //MAIN MENU OPTION
            default:
                throw new IllegalStateException("Unexpected value: " + optionChosen);
        }
    }
    private void serverSetupMenu()
    {
        System.out.println(NEW_GAME_MENU_WELCOME + SETUP_SERVER_OPTIONS);
        int optionChosen = getValidInput(SETUP_SERVER_OPTIONS_NUM);
        switch (optionChosen)
        {
            case 0: return;


            case 1: {
                commandLineMainMenu(); break; }
            case 2: {
                //start client here.. type in port number somewhere? Another asking menu
                new Client();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + optionChosen);
        }
    }
    private void numberOfPlayersMenu(Map map)
    {
        System.out.println(NUM_PLAYERS_CHOICE);
        int optionChosen = getValidInput(SETUP_SERVER_OPTIONS_NUM);
        switch (optionChosen)
        {
            case 0: return;

            case 1: {
                new Server(map,rr_app);
                rr_app.setUpLibgdxApplication();
                rr_app.getGameRunner().setUpGame(map, 2);
                gcl = new GameCommandLine(rr_app.getGameRunner());
                gcl.startGame();
                break;
                 }
            case 2: {
                new Server(map,rr_app);
                rr_app.setUpLibgdxApplication();
                rr_app.getGameRunner().setUpGame(map,3);
                gcl = new GameCommandLine(rr_app.getGameRunner());
                gcl.startGame();
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + optionChosen);
        }
    }

    /*
     * Application calls (controlling Application, GameRunner, AppListener)
     */


}
