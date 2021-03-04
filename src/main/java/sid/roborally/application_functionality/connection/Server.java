package sid.roborally.application_functionality.connection;

import sid.roborally.application_functionality.GameRunner;
import sid.roborally.application_functionality.reference.Map;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class Server {

    private ServerSocket server;
    private Socket client;
    private ObjectInputStream clientToServerInput;
    private PrintWriter serverToClientOutput;
    ObjectOutputStream toClient;

    private Map map;
    private GameRunner gameRunner;
    private boolean waitingForPlayers = true;
    private HashSet<Socket> clients = new HashSet<>();


    public Server(Map map, GameRunner gameRunner) {
        this.map = map;
        this.gameRunner = gameRunner;

        listenSocket();
        listenForClients();
        sendMapToClients();
        //sendGameRunnerToClients();
    }

    public void sendMapToClients(){
        try{
            for(Socket c : clients){
                toClient = new ObjectOutputStream(c.getOutputStream());
                toClient.writeObject(map);
                toClient.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendGameRunnerToClients(){
        System.out.println("Sending game runner to client: ");
        try{
            for(Socket c : clients){
                toClient = new ObjectOutputStream(c.getOutputStream());
                toClient.writeObject(gameRunner);
                toClient.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendListClientsToClients(){
        try{
            for(Socket c : clients){
                toClient = new ObjectOutputStream(c.getOutputStream());
                toClient.writeObject(map);
                toClient.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        // Tries to read client input object and write the input back to client.
                /*
        while(true){
            try{
                System.out.println("client: "+ client);

                Object o = clientToServerInput.readObject();
                //Send data back to client
                serverToClientOutput.println(o);
                serverToClientOutput.flush();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }
        }
                */
    }
}
