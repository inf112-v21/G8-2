package sid.roborally.application_functionality.connection;


import sid.roborally.application_functionality.GameRunner;
import sid.roborally.application_functionality.Player;
import sid.roborally.application_functionality.RRApplication;
import sid.roborally.application_functionality.reference.Map;
import sid.roborally.game_mechanics.card.Card;
import sid.roborally.game_mechanics.card.CardDeck;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * UNDER DEVELOPMENT. NOT ACTIVE CODE
 *
 * Client to allow players to connect to a server (hosted by a player) and play with each-other
 * @author Markus Edlin & Emil Eldøen
 */
public class Client {
    /**
     * Variables used by the client to
     * connect, accept and wirte input from/to server
     */
    private Socket serverSocket;
    private ObjectOutputStream clientToServerOutput;
    private ObjectInputStream serverToClientInput;
    private String hostAddress;
    private int numPlayers;
    private int port;

    /**
     * Variables used to create a local instance of the game
     */
    private Map map;
    private GameRunner gameRunner;
    private RRApplication rr_app;
    private CardDeck deck;

    public Client(String IPAddress, int port) {
        this.port = port;
        this.hostAddress = IPAddress;
        connectToServer();
    }

    /**
     * Connects client to server with port number and IPAdress given by user.
     * Fetches client outputStream and server inputStream.
     * Reads server input and prints to console.
     * Catch: Throws exception if host is invalid or I/O operation is invalid.
     */
    private void connectToServer(){
        //Create socket connection
        try{
            System.out.println("Connecting...");//feedback to user
            serverSocket = new Socket(hostAddress, port);
            serverToClientInput = new ObjectInputStream(serverSocket.getInputStream());
            clientToServerOutput = new ObjectOutputStream(serverSocket.getOutputStream());
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + hostAddress + ":" + port);
        } catch  (IOException e) {
            System.out.println("No I/O");
        }
    }

    /**
     * Receives the deck of all cards used in the game
     */
    public void getDeck(){
        try{
            deck = (CardDeck) serverToClientInput.readObject();

        } catch (IOException e) {
            System.out.println("No card output from server!");
        } catch (ClassNotFoundException e) {
            System.out.println("Deck not found!");
        }
    }

    /**
     * Receives map from server
     */
    public Map getMap(){
        try{
            map = (Map) serverToClientInput.readObject();
            return map;
        } catch (IOException e) {
            System.out.println("No map output from server!");
        } catch (ClassNotFoundException e) {
            System.out.println("Map not found!");
        }
        return null;
    }

    public int getNumPlayers() {
        try{
            numPlayers = (int) serverToClientInput.readObject();
            return numPlayers;
        } catch (IOException | ClassNotFoundException e) {
            return 0;
        }
    }

    public int getOrder(){
        try{
            return (int) serverToClientInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return 0;
        }
    }

    public ArrayList<Player> getPlayers(){
        try{
            return (ArrayList<Player>) serverToClientInput.readObject();
        } catch (IOException e) {
            System.out.println("No card output from server!");
        } catch (ClassNotFoundException e) {
            System.out.println("Deck not found!");
        }
        return null;
    }

    /**
     * Sets up a local version of roborally
     */
    private void setUpClientGame(){
        rr_app = new RRApplication();
        gameRunner = rr_app.getGameRunner();

        System.out.println("Map: "+map);
    }

    private void sendCardSelectionToServer(ArrayList<Card> cards){
        try{
            clientToServerOutput.writeObject(cards);
            clientToServerOutput.flush();
        } catch (IOException e) {
        }
    }

    public boolean getWaitingForServer(){
        try {
            return (boolean) serverToClientInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
        return true;
    }

}
