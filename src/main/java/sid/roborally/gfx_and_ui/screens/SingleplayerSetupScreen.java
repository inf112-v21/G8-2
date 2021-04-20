package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import sid.roborally.application_functionality.Player;
import sid.roborally.application_functionality.reference.Map;
import sid.roborally.application_functionality.reference.PlayerTexture;
import sid.roborally.gfx_and_ui.AppListener;

import java.util.ArrayList;

public class SingleplayerSetupScreen extends GameSetupScreen {

    public SingleplayerSetupScreen(AppListener appListener) {
        super(appListener);

        getStartGameButton().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                addPlayers(getPlayers(), getPlayerBox().getSelected());
                setUpGame(getPlayers());
                appListener.setScreen(new GameScreen(appListener));
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
    }

    /**
     * Starts the game with the selected map, and the selected amount of players
     * @param players a list of players
     */
    private void setUpGame(ArrayList<Player> players) {
        for (Player player : players)
            getRR_app().getGameRunner().addPlayer(player);
        getRR_app().getGameRunner().setUpGame(Map.values()[getMapBox().getSelectedIndex()]);
        getRR_app().startGame();
    }

    /**
     * Adds the amount of players you want to have into an ArrayList
     * @param players ArrayList to hold players
     * @param amount amount of players
     */
    private void addPlayers(ArrayList<Player> players, int amount) {
        Player localPlayer = new Player(1);
        localPlayer.setLocal();
        localPlayer.givePlayerTexture(getLocalChosenPlayerTexture());
        players.add(localPlayer);
        if (amount > 1) {
            for (int i = 2; i <= amount; i++) {
                Player newPlayer = new Player(i, true);
                players.add(newPlayer);
            }
        }
    }
}
