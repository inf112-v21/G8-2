package sid.roborally.application_functionality.connection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server;
    private Socket client;
    private BufferedReader in;
    private PrintWriter output;
    private String line;
    private Object button;
    private TextArea textArea = new TextArea(5,20);

    public Server() {
        listenSocket();
    }

    public void listenSocket(){
        try{
            server = new ServerSocket(4321);
        } catch (IOException e) {
            System.out.println("Could not listen on port 4321");
            System.exit(-1);
        }
        /*try{
          client = server.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 4321");
            System.exit(-1);
        }
        /*
        try{
           in = new BufferedReader(new InputStreamReader(client.getInputStream()));
           output = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }*/
        /*
        while(true){
            try{
                line = in.readLine();
                //Send data back to client
                output.println(line);
            } catch (IOException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }
        }

         */
    }
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if(source == button){
            textArea.setText(line);
        }
    }
}
