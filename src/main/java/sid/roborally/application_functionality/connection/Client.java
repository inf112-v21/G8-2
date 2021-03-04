package sid.roborally.application_functionality.connection;


import sid.roborally.application_functionality.GameRunner;
import sid.roborally.application_functionality.RRApplication;
import sid.roborally.application_functionality.reference.Map;
import sid.roborally.application_functionality.reference.TextureReference;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket serverSocket;
    private ObjectOutputStream clientToServerOutput;

    private Map map;
    private GameRunner gameRunner;
    private ObjectInputStream serverToClientInput;

    RRApplication rr_app;

    public Client() {
        listenSocket();
        listenForMap();
        setUpClientGame();
    }

    public void setUpClientGame(){
        rr_app = new RRApplication();
        rr_app.setUpLibgdxApplication();
        gameRunner = rr_app.getGameRunner();

        System.out.println("Game runner: " + gameRunner + ", Map: "+map);
        System.out.println(gameRunner.getMap());
        gameRunner.setUpClientGame(map);

    }

    public void listenForMap(){
        try{
            serverToClientInput = new ObjectInputStream(serverSocket.getInputStream());
            map = (Map) serverToClientInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void listenForGameRunner(){
        try{
            serverToClientInput = new ObjectInputStream(serverSocket.getInputStream());
            System.out.println(serverToClientInput);
            gameRunner = (GameRunner) serverToClientInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connects client to server with port num.
     * Fetches client outputStream and server inputStream.
     * Reads server input and prints to console.
     * Catch: Throws exception if host is invalid or I/O operation is invalid.
     */
    public void listenSocket(){
        //Create socket connection
        try{
            System.out.println("Connecting...");//feedback to user
            serverSocket = new Socket("localhost", 4321);
            clientToServerOutput = new ObjectOutputStream(serverSocket.getOutputStream());

            //input from client to server
            //clientToServerOutput.writeObject(new String("Client "+ this + " has connected."));
            //clientToServerOutput.flush();


        } catch (UnknownHostException e) {
            System.out.println("Unknown host: kq6py");
            System.exit(1);
        } catch  (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
        }
    }
}
