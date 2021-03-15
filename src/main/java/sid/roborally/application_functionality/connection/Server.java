package sid.roborally.application_functionality.connection;

import sid.roborally.application_functionality.RRApplication;
import sid.roborally.application_functionality.reference.Map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * UNDER DEVELOPMENT. NOT ACTIVE CODE
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

    /**
     * Sets up the server
     * @param map the map the game is played on
     * @param rr_app the application used to play
     */
    public Server(Map map, RRApplication rr_app) {
        this.map = map;
        this.rr_app = rr_app;

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
        } catch (IOException e) {
            System.out.println("Could not listen on port 4321");
            System.exit(-1);
        }
    }
}
