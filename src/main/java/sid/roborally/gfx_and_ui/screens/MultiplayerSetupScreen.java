package sid.roborally.gfx_and_ui.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import sid.roborally.application_functionality.connection.Server;
import sid.roborally.gfx_and_ui.AppListener;

public class MultiplayerSetupScreen extends GameSetupScreen {

    private TextField portField;
    private Server server;
    private Label errorLabel;

    public MultiplayerSetupScreen(AppListener appListener) {
        super(appListener);

        this.portField = new TextField("4321", getSkin());
        this.errorLabel = new Label("", getSkin());

        getButtonTable().row();
        getButtonTable().add(errorLabel);
        getButtonTable().row();
        getButtonTable().add(portField);

        getStartGameButton().setText("Host game");

        getStartGameButton().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                try {
                    //Tries to setup a server with the given port
                    server = new Server(Integer.parseInt(portField.getText()));
                    if (server.getErrorMessage().length() > 0) {
                        errorLabel.setText(server.getErrorMessage());
                    } else {
                        appListener.setScreen(new HostScreen(appListener, server, getMapBox().getSelectedIndex()));
                    }
                } catch (NumberFormatException e) {
                    errorLabel.setText("Port must be a whole number");
                }
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        getBackButton().addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                appListener.setScreen(new MultiplayerScreen(appListener));
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

    }
}
