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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sid.roborally.application_functionality.connection.Server;
import sid.roborally.game_mechanics.card.CardDeck;
import sid.roborally.gfx_and_ui.AppListener;

/**
 * <h3>HostScreen</h3>
 * <p>A screen where you have the option to host a game.</p>
 *
 * @author Andreas Henriksen
 */
public class HostScreen implements Screen {

    private final AppListener appListener;
    private OrthographicCamera cam;
    private Stage stage;
    private Table table;
    private Button hostGameButton, backButton, closeServerButton, lookForPlayersButton;
    private Skin skin;
    protected Server server;
    private TextField IPField, portField;
    private Label errorLabel, portLabel, IPLabel;
    BitmapFont font;
    SpriteBatch batch;

    public HostScreen(final AppListener appListener) {
        this.appListener = appListener;
        this.table = new Table();
        stage = new Stage(new ScreenViewport());
        font = new BitmapFont();
        batch = new SpriteBatch();

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

        hostGameButton = new TextButton("Start server", skin, "default");

        backButton = new TextButton("Back", skin, "default");

        closeServerButton = new TextButton("Close server", skin, "default");

        lookForPlayersButton = new TextButton("Look for players", skin, "default");

        table.add(portField);
        table.row();
        table.add(errorLabel);
        table.row();
        table.add(hostGameButton);
        table.row();
        table.add(backButton).width(hostGameButton.getWidth());

        hostGameButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                try {
                    server = new Server(Integer.parseInt(portField.getText()));
                    if (server.getErrorMessage().length() > 0) {
                        //server.closeServer();
                        errorLabel.setText(server.getErrorMessage());
                    } else {
                        setupServerMenu();
                    }
                } catch (NumberFormatException e) {
                    errorLabel.setText("Port must be a whole number");
                }
                //appListener.setScreen(new ConnectionScreen(appListener));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        backButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                appListener.setScreen(new MultiplayerScreen(appListener));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });


        closeServerButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                server.closeServer();
                appListener.setScreen(new MultiplayerScreen(appListener));
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        lookForPlayersButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                server.findPlayers();
                server.sendToPlayers(new CardDeck());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
    }

    /**
     * Sets up the menu items needed for the server
     */
    public void setupServerMenu() {
        table.removeActor(hostGameButton);
        table.removeActor(backButton);
        table.removeActor(errorLabel);
        table.row();
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
