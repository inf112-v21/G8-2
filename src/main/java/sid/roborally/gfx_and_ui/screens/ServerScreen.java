package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sid.roborally.application_functionality.Player;
import sid.roborally.application_functionality.connection.Server;
import sid.roborally.application_functionality.reference.Map;
import sid.roborally.gfx_and_ui.AppListener;

import java.util.ArrayList;

/**
 * <h3>HostScreen</h3>
 * <p>A screen where you have the option to host a game.</p>
 *
 * @author Andreas Henriksen
 */
public class ServerScreen implements Screen {

    private final AppListener appListener;
    private OrthographicCamera cam;
    private Stage stage;
    private Table table;
    private Button closeServerButton, lookForPlayersButton;
    private Skin skin;
    protected Server server;
    private TextField IPField, portField;
    private Label errorLabel, portLabel, IPLabel;
    BitmapFont font;
    SpriteBatch batch;
    private int mapIndex;
    private int numPlayers;
    private ArrayList<Player> players;

    public ServerScreen(final AppListener appListener, Server server, int mapIndex, int numPlayers) {
        this.server = server;
        this.appListener = appListener;
        this.table = new Table();
        stage = new Stage(new ScreenViewport());
        font = new BitmapFont();
        batch = new SpriteBatch();
        this.mapIndex = mapIndex;
        this.numPlayers = numPlayers;
        players = new ArrayList<>();


        table.center();

        table.setFillParent(true);
        table.setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture("assets/application_skin/GameBackground.png"))));
        stage.addActor(table);

        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);

        Gdx.input.setInputProcessor(stage);

        skin = appListener.getSkin();

        this.IPField = new TextField("", skin);
        this.IPLabel = new Label("", skin);
        this.portField = new TextField("4321", skin);
        this.errorLabel = new Label("", skin);
        this.portLabel = new Label("Port", skin);


        //Initializes the necessary TextButtons for the screen
        closeServerButton = new TextButton("Close server", skin, "default");
        lookForPlayersButton = new TextButton("Look for players", skin, "default");

        addMenuActors();

        closeServerButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //Closes the server and goes back to MultiplayerSetupScreen
                server.closeServer();
                appListener.setScreen(new MultiplayerSetupScreen(appListener));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        lookForPlayersButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //Starts searching for clients that wants to connect to server
                server.findPlayers();
                server.sendToAllPlayers(server.getServerDeck());
                server.sendToAllPlayers(Map.values()[mapIndex]);
                server.sendToAllPlayers((numPlayers));
                server.sendOrder();
                //server.sendToAllPlayers(server.getPlayers());
                Player serverPlayer = new Player(1,true);
                serverPlayer.setLocal();
                players.add(serverPlayer);
                for(int i = 2; i<=numPlayers;i++){
                    Player p = new Player(i,true);
                    p.setExternal();
                    players.add(p);
                }
                setUpGame(players);
                appListener.setScreen(new GameScreen(appListener));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
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
            appListener.getRRApp().getGameRunner().addPlayer(player);
        appListener.getRRApp().getGameRunner().setUpGame(Map.values()[mapIndex]);
        appListener.getRRApp().startGame();
    }

    /**
     * Adds the actors needed for the HostScreen
     */
    private void addMenuActors() {
        table.add(IPLabel).width(200);
        table.row();
        table.add(IPField).width(200).padBottom(30);
        table.row();
        table.add(portLabel);
        table.row();
        table.add(portField);
        table.row();
        table.add(lookForPlayersButton);
        table.row();
        table.add(closeServerButton).padRight(20);
        IPField.setText(server.getAddress());
        IPLabel.setText("Host IP Address");
        IPLabel.setAlignment(Align.center);
        IPField.setAlignment(Align.center);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.8F, .5F, .1F,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        cam.update();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
