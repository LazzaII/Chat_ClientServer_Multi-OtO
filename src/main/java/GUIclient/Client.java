package GUIclient;

import java.io.*;
import java.net.*;

public class Client {
    private final static int serverPort = 6789;
    private Socket s;
    private BufferedReader input;
    private BufferedReader inFromServer;
    private DataOutputStream outToServer;
    private SendThread sendThread;
    private ReadThread readThread;
    private String username;

    public void connect(String username) {
        this.username = username;
        //Inizializzazione input
        input = new BufferedReader(new InputStreamReader(System.in));

        try {
            //Recupero ip localhost
            InetAddress ip = InetAddress.getByName("localhost");

            //Conessione al server
            s = new Socket(ip, serverPort);

            // Apertura canali
            inFromServer = new BufferedReader (new InputStreamReader (s.getInputStream()));
            outToServer = new DataOutputStream(s.getOutputStream());
     
            sendThread = new SendThread();
            readThread = new ReadThread();
        }
        catch(Exception ex) {
            ex.toString();
        }
    }
    
    public String getUsername() {
        return username;
    }
    
    public void sendMex(String mex) {
        sendThread.send(mex);
    }

    private class SendThread extends Thread{
        public SendThread() {
            start();
        }
        
        public void send(String mex) {
            try{
               outToServer.writeBytes(mex); 
            } catch (Exception ex) {
                System.out.println("Impossibile inviare il messaggio");
            }         
        }
    }

    private class ReadThread extends Thread{
        public ReadThread() {
            start();
        }
    }
}