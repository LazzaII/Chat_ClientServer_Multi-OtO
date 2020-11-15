package GUIserver;
import GUIclient.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GestioneChat{
    private ArrayList<ServerThread> sockets = new ArrayList();
    private ArrayList<String> utenti = new ArrayList();
    private ArrayList<FrameChat> frameChats = new ArrayList();
    private ServerSocket s;
    private static GestioneChat instance = null;

    //Utilizziamo un singleton della gestione chat perche i client devono fare riferimento allo stesso server
    public static GestioneChat getInstance() {
        if(instance == null) instance = new GestioneChat();
        return instance;
    }
    
    //avvia il server che si mette in ascolto sul socket
    public void start(){
        getInstance();
        try{
            System.out.println("server in attesa");
            instance.s = new ServerSocket(6789);
            
            for(int i = 0; i < 10; i++) {
                instance.sockets.add(new ServerThread(instance.s.accept()));
                instance.sockets.get(i).start();
            }
            instance.s.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del messaggio!");
            System.exit(1);
        }
    }
    
    //controlla se l'username è già presente, altrimenti lo inserisce
    public boolean controlloUsername(String username){      
        for(String u : instance.utenti){
            if(username.equalsIgnoreCase(u)) {
               return false;
            }
        }               
        instance.utenti.add(username);       
        return true;
    }

    public boolean addFrameChat(FrameChat fc) {
        if(instance.frameChats.add(fc)) {
            for(FrameChat fch : instance.frameChats) {
                fch.updateUserList();
            }
            return true;
        }   
        return false;
    }    

    public ArrayList<String> getUtenti() {
        return instance.utenti;
    }
    
    public static void main(String[] args) {
        GestioneChat tcpServer = new GestioneChat();
        tcpServer.start();
    }

    public class ServerThread extends Thread{
        private Socket clientS;
        private BufferedReader inDalClient;
        private DataOutputStream outVersoClient;
        private String usernameClient;

        public ServerThread (Socket socket){
            this.clientS = socket;
        }

        @Override
        public void run (){
            try{
                comunica();
            } catch (Exception e){
                e.toString();
            }
        }

        public void comunica () throws Exception {
            
        }
    }
}