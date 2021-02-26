package sid.roborally.application_functionality;

import sid.roborally.application_functionality.comm_line.GameCommandLine;

import java.util.Scanner;

public class CommandLineTool {

    private RRApplication rr_app; //RoboRallyApplication
    private GameCommandLine gcl;

    Scanner sc; //Input-scanner

    private static String MAIN_MENU_WELCOME = "\nWelcome to RoboRally. Select one of the following options:\n";
    private static String MAIN_MENU_OPTIONS = "1. Start a new game (write \"1\")" + "\n0. Quit application";
    private static int MAIN_MENU_OPTIONS_NUM = 2;

    private static String NEW_GAME_MENU_WELCOME = "\nNew Game Menu. Select one of the following options:\n";
    private static String NEW_GAME_MENU_OPTIONS = "1. Start a new demo-game (write \"1\")\n" +
            "2. Go back to Main Menu\n" + "0. Quit application";
    private static int NEW_GAME_MENU_OPTIONS_NUM = 3;

    public CommandLineTool() {}

    public void giveApp(RRApplication app)
    {
        this.rr_app = app;
    }

    public void run()
    {
        sc = new Scanner(System.in);
        commandLineMainMenu();
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
                    rr_app.setUpLibgdxApplication();
                    rr_app.setUpDemo();
                    gcl = new GameCommandLine(rr_app.getGameRunner());
                    gcl.startGame();
                    break;
                }
            case 2:
                commandLineMainMenu(); break; //MAIN MENU OPTION
            default:
                throw new IllegalStateException("Unexpected value: " + optionChosen);
        }
    }

    /*
     * Application calls (controlling Application, GameRunner, AppListener)
     */


}
