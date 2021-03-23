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
    private HashMap<Socket, Player> clientpLayers = new HashMap<>();
    private HashMap<Socket, ArrayList<Card>>playerSelect = new HashMap<>(); // Change key back to Player after testing
    private CardDeck deck;

    /**
     * The IP address to be printed on server setup page
     */
    private String IPAddress;

    /**
     * Sets up the server
     * @param map the map the game is played on
     */
    public Server(Map map) {
        this.map = map;
        deck = new CardDeck();

        listenSocket();
        listenForClients();
        sendDeckToClients(deck);
        sendMapToClients();
        sendNumPlayersToClients();
        //listenForCardSelection();
    }

    /**
     *  Sets up server with port number.
     *  Accepts clients.
     *  Gets input from client.
     *  Gets client's outputStream.
     *  Continually fetches client input.
     *  Catches: if port not available and if I/O operations are invalid.
     */
    public void listenSocket(){
        // Tries to create server
        try{
            server = new ServerSocket(4321);
            //Fetches local IP adress
            IPAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println(IPAddress);
        } catch (IOException e) {
            System.out.println("Could not listen on port 4321");
            System.exit(-1);
        }
    }

    /**
     * Find all clients trying to connect
     * and adds them to hashMap to keep control
     * of all client's input and output
     * as well as which player belongs to which client
     */
    public void listenForClients(){
        while(waitingForPlayers){
            waitingForPlayers = false; //setting to false immediately to only run once for currently one client
            try{
                Socket client = server.accept();
                System.out.println("Client connected: "+ client.isConnected());
                clientsOut.put(client, new ObjectOutputStream(client.getOutputStream()));
                clientsIn.put(client, new ObjectInputStream(client.getInputStream()));
                //clientPlayers.put(client, player (fra client eller lages på server))
                //playerSelect(client sin player og en tom kortstokk)
            } catch (IOException e) {
                System.out.println("Accept failed: 4321");
                System.exit(-1);
            }
        }
    }
    public void sendToClient(Object thing){
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

    /**
     * @param deck the deck of all the cards used in a game
     */
    public void sendDeckToClients(CardDeck deck){
        sendToClient(deck);
        /*try {
            for (Socket c : clientsOut.keySet()) {
                serverToClientOutput = clientsOut.get(c);
                serverToClientOutput.writeObject(deck);
                serverToClientOutput.flush();
            }
        } catch (IOException e){
            System.out.println("Cards could not be sent to client!");
            e.printStackTrace();
        }*/
    }

    public void sendMapToClients(){
        sendToClient(map);
        /*try{
            for(Socket c : clientsOut.keySet()){
                serverToClientOutput = clientsOut.get(c);
                serverToClientOutput.writeObject(map);
                serverToClientOutput.flush();
            }
        } catch (IOException e) {
            System.out.println("Map couldn't be sent to client!");
            e.printStackTrace();
        }*/
    }

    private void sendNumPlayersToClients(){
        try{
            for(Socket c : clientsOut.keySet()){
                serverToClientOutput = clientsOut.get(c);
                serverToClientOutput.writeInt(clientsOut.size()+1); //+1 because server counts as one player
                serverToClientOutput.flush();
            }
        } catch (IOException e) {
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
}
