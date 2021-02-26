package sid.roborally.application_functionality.comm_line;

import sid.roborally.application_functionality.GameRunner;

public class GameCommandLine {

    private GameRunner grunner;

    public GameCommandLine(GameRunner gameRunner)
    { this.grunner = gameRunner; }

    public void startGame()
    {
        grunner.runGame();
    }
}
