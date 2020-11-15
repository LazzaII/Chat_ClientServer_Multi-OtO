package GUIclient;

import java.io.*;
import java.net.*;

public class Client{

    private final static int serverPort = 6789;
    private Socket s;
    private BufferedReader input;
    private BufferedReader inFromServer;
    private DataOutputStream outToServer;
    private ReadThread read;
    private SendThread send;
    String username;
    
    public Client(String username) {
        this.username = username;
        connetti();
    }
    
    public void connetti(){
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
            
            send = new SendThread();
            read = new ReadThread();
        }
        catch(Exception ex) {
           ex.toString();
        }    
    }
    
    public class SendThread extends Thread{
        public SendThread() {
            start();
        }

        @Override
        public void run() {
            try {
                outToServer.writeBytes(username + '\n');                      
            }
            catch (Exception ex) {
                ex.toString();
                System.exit(1);
            }
        }

        public void close() {
            try {
                inFromServer.close();
                outToServer.close();
                s.close();
                read.close();
                this.stop();
            }
            catch(Exception ex) {
                ex.toString();
            }
            System.exit(0);
        }
    }

    public class ReadThread extends Thread{
        String msg;
        public ReadThread() {
            start();
        }

        @Override
        public void run() {
            try {
                for(;;) {
                   msg = inFromServer.readLine();
                }
            }
            catch (Exception ex) {
                ex.toString();
                System.exit(1);
            }
        }

        public void close() {
            try {
                this.stop();
            }
            catch(Exception ex) {
                ex.toString();
            }
            System.exit(0);
        }
    }
    
}
    