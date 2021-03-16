package sid.roborally.application_functionality.connection;


import sid.roborally.application_functionality.GameRunner;
import sid.roborally.application_functionality.RRApplication;
import sid.roborally.application_functionality.reference.Map;
import sid.roborally.game_mechanics.card.CardDeck;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * UNDER DEVELOPMENT. NOT ACTIVE CODE
 *
 * Client to allow players to connect to a server (hosted by a player) and play with each-other
 * @Author Markus Edlin & Emil Eldøen
 */
public class Client {
    private Socket serverSocket;
    private ObjectOutputStream clientToServerOutput;
    private Map map;
    private GameRunner gameRunner;
    private ObjectInputStream serverToClientInput;
    private RRApplication rr_app;
    private String hostAddress;
    private int numPlayers;
    private CardDeck deck;

    public Client(String IPAddress) {
        hostAddress = IPAddress;
        listenSocket();
        listenForMap();
        //listenForCards(); Needed to find cards from server
        listenForNumPlayers();
        setUpClientGame();
    }

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
            serverSocket = new Socket(hostAddress, 4321);
            serverToClientInput = new ObjectInputStream(serverSocket.getInputStream());


        } catch (UnknownHostException e) {
            System.out.println("Unknown host: kq6py");
            System.exit(1);
        } catch  (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
        }
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
    /**
     * Recieves map from server
     */
    private void listenForMap(){
        try{
            map = (Map) serverToClientInput.readObject();
        } catch (IOException e) {
            System.out.println("No map output from server!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Map not found!");
            e.printStackTrace();
        }
    }
    private void listenForCards(){
        try{
            deck = (CardDeck) serverToClientInput.readObject();
        } catch (IOException e) {
            System.out.println("No card output from server!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Deck not found!");
            e.printStackTrace();
        }
    }

    private void setUpClientGame(){
        rr_app = new RRApplication();
        //TODO:rr_app.setUpLibgdxApplication();
        gameRunner = rr_app.getGameRunner();

        System.out.println("Game runner: " + gameRunner + ", Map: "+map);
        //gameRunner.setUpGame(map, numPlayers);
    }

}
