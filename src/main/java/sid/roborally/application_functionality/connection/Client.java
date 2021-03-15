package sid.roborally.application_functionality.connection;


import sid.roborally.application_functionality.GameRunner;
import sid.roborally.application_functionality.RRApplication;
import sid.roborally.application_functionality.reference.Map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * UNDER DEVELOPMENT. NOT ACTIVE CODE
 */
public class Client {
    private Socket serverSocket;
    private ObjectOutputStream clientToServerOutput;

    private Map map;
    private GameRunner gameRunner;
    private ObjectInputStream serverToClientInput;
    private RRApplication rr_app;
    //private Player player;
    private int numPlayers;
    public Client() {
        listenSocket();
        listenForMap();
        listenForNumPlayers();
        setUpClientGame();
    }

    private void listenForNumPlayers() {
        try{
            serverToClientInput = new ObjectInputStream(serverSocket.getInputStream());
            numPlayers = serverToClientInput.readInt();
            System.out.println(numPlayers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpClientGame(){
        rr_app = new RRApplication();
        //TODO:rr_app.setUpLibgdxApplication();
        gameRunner = rr_app.getGameRunner();

        System.out.println("Game runner: " + gameRunner + ", Map: "+map);
        System.out.println(gameRunner.getMap());
        //gameRunner.setUpGame(map, numPlayers);
    }

    /**
     * Recieves map from server
     */
    private void listenForMap(){
        try{
            serverToClientInput = new ObjectInputStream(serverSocket.getInputStream());
            map = (Map) serverToClientInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* Remove this?
    private void listenForGameRunner(){
        try{
            serverToClientInput = new ObjectInputStream(serverSocket.getInputStream());
            System.out.println(serverToClientInput);
            gameRunner = (GameRunner) serverToClientInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Connects client to server with port num.
     * Fetches client outputStream and server inputStream.
     * Reads server input and prints to console.
     * Catch: Throws exception if host is invalid or I/O operation is invalid.
     */
    private void listenSocket(){
        //Create socket connection
        try{
            System.out.println("Connecting...");//feedback to user
            serverSocket = new Socket("localhost", 4321);


        } catch (UnknownHostException e) {
            System.out.println("Unknown host: kq6py");
            System.exit(1);
        } catch  (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
        }
    }
}
