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
    private PrintWriter serverOut;
    private BufferedReader serverIn;
    private TextField textField = new TextField("test text",20);
    private Object button;

    public Client() {
        listenSocket();
    }

    public void listenSocket(){
        //Create socket connection
        try{
            System.out.println("Connecting...");//feedback to user
            serverSocket = new Socket("localhost", 4321);
            serverOut = new PrintWriter(serverSocket.getOutputStream(),true);

            //input from client to server
            serverOut.println("hello");
            serverOut.flush();

            serverIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: kq6py");
            System.exit(1);
        } catch  (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
        }
    }
    public void actionPerformed(ActionEvent event){
        Object source = event.getSource();

        if(source == button){
            //Send data over socket
            String text = textField.getText();
            serverOut.println(text);
            textField.setText(new String(""));
            serverOut.println(text);
        }
//Receive text from server
        try{
            String line = serverIn.readLine();
            System.out.println("Text received: " + line);
        } catch (IOException e){
            System.out.println("Read failed");
            System.exit(1);
        }
    }
}
