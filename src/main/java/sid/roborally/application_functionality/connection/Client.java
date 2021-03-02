package sid.roborally.application_functionality.connection;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket serverSocket;
    private PrintWriter clientOutput;
    private BufferedReader serverInput;

    public Client() {
        listenSocket();
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
            clientOutput = new PrintWriter(serverSocket.getOutputStream(),true);

            //input from client to server
            clientOutput.println("hello");
            clientOutput.flush();

            serverInput = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            String str = serverInput.readLine();
            System.out.println("received: " + str);

        } catch (UnknownHostException e) {
            System.out.println("Unknown host: kq6py");
            System.exit(1);
        } catch  (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
        }
    }
}
