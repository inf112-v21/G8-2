package sid.roborally.application_functionality.connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server;
    private Socket client;
    private BufferedReader clientInput;
    private PrintWriter serverOutput;
    private String line;


    public Server() {
        listenSocket();
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
        // Accepts client, catches if client does not connect.
        try{
          client = server.accept();
          System.out.println("Client connected: "+ client.isConnected());
        } catch (IOException e) {
            System.out.println("Accept failed: 4321");
            System.exit(-1);
        }
        //Fetches client input and output.
        try{
           clientInput = new BufferedReader(new InputStreamReader(client.getInputStream()));

           String str = clientInput.readLine();
           System.out.println("client: "+ str);

           serverOutput = new PrintWriter(client.getOutputStream(), true);
           // ObjectInputStream testObjOutput


           // Take the output, prints string to the client from server.
           serverOutput.println("Hello Client");
           serverOutput.flush();

        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }
        // Tries to read client input and write the input.
        while(true){
            try{
                System.out.println("client inputstream read");
                //client.getInputStream().read();
                System.out.println("client: "+ client);

                line = clientInput.readLine();
                //Send data back to client
                serverOutput.println(line);
            } catch (IOException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }
        }

    }
}
