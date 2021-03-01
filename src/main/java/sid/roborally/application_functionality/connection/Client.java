package sid.roborally.application_functionality.connection;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket clientSocket;
    private PrintWriter clientOut;
    private BufferedReader clientIn;
    private TextField textField = new TextField("test text",20);
    private Object button;

    public Client() {
        listenSocket();
    }

    public void listenSocket(){
        //Create socket connection
        try{
            System.out.println("Connecting...");//feedback to user
            clientSocket = new Socket("kq6py", 4321);
            clientOut = new PrintWriter(clientSocket.getOutputStream(),true);
            clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
            clientOut.println(text);
            textField.setText(new String(""));
            clientOut.println(text);
        }
//Receive text from server
        try{
            String line = clientIn.readLine();
            System.out.println("Text received: " + line);
        } catch (IOException e){
            System.out.println("Read failed");
            System.exit(1);
        }
    }
}
