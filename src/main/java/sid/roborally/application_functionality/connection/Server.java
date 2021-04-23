package sid.roborally.application_functionality.connection;

import sid.roborally.application_functionality.Player;
import sid.roborally.application_functionality.reference.Map;
import sid.roborally.game_mechanics.card.Card;
import sid.roborally.game_mechanics.card.CardDeck;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * UNDER DEVELOPMENT. NOT ACTIVE CODE
 *
 * Allows a player to become a host and let others connect to them to play RoboRally
 * @author Markus Edlin & Emil Eldøen
 *
 * Quick note: refer to other players as client for better distinction between the player acting as host
 * and players connecting to host
 */
public class Server {
    /**
     * The server and its write and read variables
     * to send/accept input to/from server
     */
    private ServerSocket server;
    private ObjectInputStream clientToServerInput;
    private ObjectOutputStream serverToClientOutput;

    /**
     * Variables being sent to clients
     * or updated via input from client
     */
    private Map map;
    private boolean waitingForPlayers = true;
    private HashMap<Socket, ObjectInputStream> clientsIn = new HashMap<>();
    private HashMap<Socket, ObjectOutputStream> clientsOut = new HashMap<>();
    private HashMap<Socket, Player> clientPlayers = new HashMap<>();
    private HashMap<Socket, ArrayList<Card>>playerSelect = new HashMap<>(); // Change key back to Player after testing
    private HashMap<Integer, ArrayList<Card>> chosenCards = new HashMap<>();
    private CardDeck deck;
    private int numPlayers;
    private String errorMessage;
    private Player serverPlayer;

    /**
     * The IP address and port to be printed on server setup page
     */
    private String IPAddress;
    private int port;

    /**
     * Server constructor, also sets up essentials for information players and clients need.
     * Starts server
     *  Accepts clients.
     *      *  Gets input from client.
     *      *  Gets client's outputStream. (Allows to write to client)
     *      *  Continually fetches client input. (Allows to read info sent from clients)
     */
    public Server(int port, Integer numPlayers) {

        this.port = port;
        deck = new CardDeck();
        serverPlayer = new Player(1,true);
        serverPlayer.setLocal();
        this.numPlayers = numPlayers;


        startServer();
    }

    /**
     *  Sets up server with port number.
     *  Catches: if port not available and if I/O operations are invalid.
     */
    public void startServer(){
        // Tries to create server
        try{
            server = new ServerSocket(port);
            //Fetches local IP address
            IPAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println(IPAddress);
            this.errorMessage = "";
        } catch (IOException e) {
            this.errorMessage = "Could not listen on port " + port;
        }
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * Find all clients trying to connect
     * and adds them to hashMap to keep control
     * of all client's input and output
     * as well as which player belongs to which client
     */
    public void findPlayers(){

        int playerNumber = 1;

        while(waitingForPlayers && playerNumber != numPlayers){
            //waitingForPlayers = false; //setting to false immediately to only run once for currently one client
            try{
                Socket client = server.accept();
                playerNumber++;
                System.out.println("Client connected: "+ client.isConnected());
                clientsOut.put(client, new ObjectOutputStream(client.getOutputStream()));
                clientsIn.put(client, new ObjectInputStream(client.getInputStream()));
                Player p = new Player(playerNumber,true);
                p.setExternal();
                clientPlayers.put(client, p);
                //playerSelect(client sin player og en tom kortstokk)
            } catch (IOException e) {
                System.out.println("Accept failed: 4321");
                System.exit(-1);
            }
        }
        sendToAllPlayers(false);
    }

    /**
     * Sends different objects (things) the clients may need to run the game
     * @param thing
     */
    public void sendToAllPlayers(Object thing){
        try{
            for(Socket c : clientsOut.keySet()){
                serverToClientOutput = clientsOut.get(c);
                serverToClientOutput.writeObject(thing);
                serverToClientOutput.flush();
            }
        } catch (IOException e) {
            System.out.println("Object " + thing.getClass().toString() + " could not be sent");
            e.printStackTrace();
        }

    }

    public void sendToPlayer(Object object, Socket player) {
        try {
            serverToClientOutput = clientsOut.get(player);
            serverToClientOutput.writeObject(object);
            serverToClientOutput.flush();
        } catch (IOException e) {
            System.out.println("Object " + object.getClass().toString() + " could not be sent");
            e.printStackTrace();
        }
    }

    /**
     * Accepts chosen cards from clients
     * To be used in game for robots to move
     */
    public void listenForCardSelection(){
        try{
            for(Socket c : clientsIn.keySet()){
                clientToServerInput = clientsIn.get(c);

                ArrayList<Card> cards = (ArrayList<Card>) clientToServerInput.readObject(); //Update later for player's card selection

                playerSelect.put(c, cards);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String getAddress(){
        return IPAddress;
    }

    public CardDeck getServerDeck() {
        return deck;
    }

    /**
     * Ends the server and closes the connection
     */
    public void closeServer(){
        try{
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server couldn't be closed");
        }

    }

    public ArrayList<Player> getPlayers(){
        ArrayList<Player> players = new ArrayList<>();

        for (Socket s : clientPlayers.keySet()){
            players.add(clientPlayers.get(s));
        }
        return players;
    }

    public void sendOrder(){
        int n = 2;
        for(Socket c : clientsIn.keySet()){
            clientToServerInput = clientsIn.get(c);
            sendToPlayer(n,c);
            n++;
        }
    }
}
