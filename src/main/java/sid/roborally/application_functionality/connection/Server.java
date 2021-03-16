package sid.roborally.application_functionality.connection;

import sid.roborally.application_functionality.RRApplication;
import sid.roborally.application_functionality.reference.Map;
import sid.roborally.game_mechanics.card.Card;
import sid.roborally.game_mechanics.card.CardDeck;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;

/**
 * UNDER DEVELOPMENT. NOT ACTIVE CODE
 *
 * Allows a player to become a host and let others connect to them to play RoboRally
 * @Author Markus Edlin & Emil Eldøen
 */
public class Server {

    private ServerSocket server;
    private Socket client;
    private ObjectInputStream clientToServerInput;
    private ObjectOutputStream serverToClientOutput;

    private Map map;
    private RRApplication rr_app;
    private boolean waitingForPlayers = true;
    private HashSet<Socket> clients = new HashSet<>();
    private String IPAddress;
    /**
     * Sets up the server
     * @param map the map the game is played on
     */
    public Server(Map map) {
        this.map = map;

        listenSocket();
        listenForClients();
        sendMapToClients();
        sendNumPlayersToClients();
    }

    public void sendMapToClients(){
        try{
            for(Socket c : clients){
                serverToClientOutput = new ObjectOutputStream(c.getOutputStream());
                serverToClientOutput.writeObject(map);
                serverToClientOutput.flush();
            }
        } catch (IOException e) {
            System.out.println("Map couldn't be sent to client!");
            e.printStackTrace();
        }
    }

    public void sendDeckToClients(CardDeck deck){
        try {
            for (Socket c : clients) {
                serverToClientOutput = new ObjectOutputStream(c.getOutputStream());
                serverToClientOutput.writeObject(deck);
                serverToClientOutput.flush();
            }
        } catch (IOException e){
            System.out.println("Cards could not be sent to client!");
            e.printStackTrace();
        }
    }
    private void sendNumPlayersToClients(){
        try{
            for(Socket c : clients){
                serverToClientOutput = new ObjectOutputStream(c.getOutputStream());
                serverToClientOutput.writeInt(clients.size()+1); //+1 because server counts as one player
                serverToClientOutput.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendListClientsToClients(){
        try{
            for(Socket c : clients){
                serverToClientOutput = new ObjectOutputStream(c.getOutputStream());
                serverToClientOutput.writeObject(map);
                serverToClientOutput.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find all clients trying to connect
     */
    public void listenForClients(){
        while(waitingForPlayers){
            waitingForPlayers = false; //setting to false immediately to only run once for currently one client
            try{
                client = server.accept();
                System.out.println("Client connected: "+ client.isConnected());
                clients.add(client);
            } catch (IOException e) {
                System.out.println("Accept failed: 4321");
                System.exit(-1);
            }
        }
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
            String IPAddress = InetAddress.getLocalHost().getHostAddress().toString();
            System.out.println(IPAddress);
        } catch (IOException e) {
            System.out.println("Could not listen on port 4321");
            System.exit(-1);
        }
    }

    public String getAddress(){
        return IPAddress;
    }
}
