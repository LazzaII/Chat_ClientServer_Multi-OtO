package GUIserver;

import GUIclient.FrameChat;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GestioneChat{
    private ArrayList <ServerThread> sockets = new ArrayList<>();
    private ArrayList <String> usernames = new ArrayList<>();
    private ServerSocket s;
    private static GestioneChat gc;
    
    public static GestioneChat getInstance() {
        if(gc == null) {
            gc = new GestioneChat();
        }
        return gc;
    }
    
    //check if username is available
    public boolean isFree(String username) {
        for(String s : usernames) {
            if(s.equals(username)) return false;
        }
        return true;
    }
    
    //add a username to the list
    public void addUsername(String username) {
        usernames.add(username);
    }
    
    public ArrayList<String> getUsernames() {
        return usernames;
    }
    
    public void start(){
        try{
            getInstance();
            
            System.out.println("server in attesa");
            s = new ServerSocket(6789);

            for(int i = 0; i < 10; i++) {
                sockets.add(new ServerThread(s.accept()));
                sockets.get(i).start();
            }
            s.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del messaggio!");
        }
    }
    
    public static void main(String[] args) {
        GestioneChat tcpServer = new GestioneChat();
        tcpServer.start();
    }

    public class ServerThread extends Thread {
        private Socket clientS;
        private BufferedReader in;
        private DataOutputStream out;
        private String usernameClient;
    
        public ServerThread (Socket socket){
            this.clientS = socket;
        }     

        @Override
        public void run (){
            try {
               transmits();
            }
            catch(Exception ex) {
                ex.toString();
            }
        } 
        
        private void sendList() {
            try {
                String list = "list#";
                for(String s : usernames) {
                    list += ('\n' + "user: " + s);
                }
                for(ServerThread s : sockets) {
                    s.out.writeBytes(list);              
                }  
            }
            catch(Exception ex) {
                ex.toString();
            }
        }
        
        private void transmits() throws Exception{
            in = new BufferedReader (new InputStreamReader (clientS.getInputStream()));
            out = new DataOutputStream(clientS.getOutputStream());        
            
            usernameClient = in.readLine();
            System.out.println("User: " + usernameClient + " connected");                      
            
            sendList();
            
            for(;;) {
                String mex = in.readLine();
                System.out.println(mex);
                if(mex.equals("#FINE#")) {
                    System.out.println("User: " + usernameClient + " disconnected");                   
                    usernames.remove(usernameClient);
                    sockets.remove(this);
                    sendList();
                    break;
                }
                else {
                    String[] mexSplit = mex.split("@");
                    if(sockets.size() > 1) {
                        if(mexSplit[1].equalsIgnoreCase("Everyone")) {         
                            for (ServerThread s : sockets) {
                                if(s != this) s.out.writeBytes(usernameClient + ": " + mexSplit[0] + ("(everyone)") + '\n');
                            }
                        }
                        else {
                            for (ServerThread s : sockets) {
                                if(s != this) {
                                    if(s.usernameClient.equals(mexSplit[1])) {
                                        s.out.writeBytes(usernameClient + ": " + mexSplit[0] + '\n');
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    else out.writeBytes("Nessuno è connesso" + '\n');
                }
            }
        out.close();
        in.close();
        clientS.close();
        }
    }
}
    