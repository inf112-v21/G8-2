package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sid.roborally.application_functionality.connection.Client;
import sid.roborally.application_functionality.connection.Server;
import sid.roborally.gfx_and_ui.AppListener;

/**
 * <h3>JoinScreen</h3>
 * <p>A screen where you can join a multiplayer game.</p>
 *
 * @author Andreas Henriksen
 */
public class JoinScreen implements Screen {
    private final AppListener appListener;
    private OrthographicCamera cam;
    private Stage stage;
    private Skin skin;
    private Button joinButton,backButton;
    private Table table;
    private Label portLabel, IPLabel, errorLabel;
    private TextField joinIP;
    private TextField joinPort;
    private TextField.TextFieldStyle messageStyle;
    private Client client;

    public JoinScreen(final AppListener appListener) {
        this.appListener = appListener;
        this.table = new Table();
        stage = new Stage(new ScreenViewport());

        table.setFillParent(true);
        table.center();
        stage.addActor(table);


        cam = new OrthographicCamera();
        cam.setToOrtho(false, 800, 480);

        Gdx.input.setInputProcessor(stage);

        skin = appListener.getSkin();
        messageStyle = new TextField.TextFieldStyle();

        joinIP = new TextField("", skin);
        joinPort = new TextField("", skin);
        portLabel = new Label("Enter port", skin);
        IPLabel = new Label("Enter IP", skin);
        errorLabel = new Label("", skin);

        backButton = new TextButton("Back", skin, "default");
        joinButton = new TextButton("Join game", skin,"default");

        table.add(errorLabel);
        table.row();
        table.add(IPLabel);
        table.row();
        table.add(joinIP).padBottom(30);
        table.row();
        table.add(portLabel);
        table.row();
        table.add(joinPort);
        table.row();
        table.add(joinButton);
        table.row();
        table.add(backButton).width(joinButton.getWidth());
        table.setBackground(new TextureRegionDrawable(new TextureRegion(
                new Texture("assets/application_skin/GameBackground.png"))));


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


        joinButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //appListener.setScreen(new MainMenuScreen(appListener));
                errorLabel.setText("");
                try {
                    client = new Client(joinIP.getText(), Integer.parseInt(joinPort.getText()));
                    System.out.println("client is here");
                    client.getDeck();
                    client.getMap();
                } catch (NumberFormatException e) {
                    errorLabel.setText("IP or port is invalid");
                }
                System.out.println("Attempting to join: "+ joinIP.getText() + " on port " + joinPort.getText());
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

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
