package GUIserver;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GestioneChat{
    
    private static int MAXCLIENT = 10;
    private static final String ENDCOMMUNICATION = "#FINE#";
    private ArrayList <ServerThread> sockets = new ArrayList<>();
    private ArrayList <String> usernames = new ArrayList<>();
    private ServerSocket s;
    private static GestioneChat gc;
    
    // we use a singleton to create only one server that is shared between the frame
    public static GestioneChat getInstance() {
        if(gc == null) {
            gc = new GestioneChat();
        }
        return gc;
    }
    
    // check if username is available
    public boolean isFree(String username) {
        for(String s : usernames) {
            if(s.equals(username)) return false;
        }
        return true;
    }
    
    // add a username to the list [NON FUNZIONA NON SAPPIAMO IL MOTIVO]
    public void addUsername(String username) {
        usernames.add(username);
    }
    
    // get
    public ArrayList<String> getUsernames() {
        return usernames;
    }
    
    public void start(){
        try{
            // check if the server is already instanced
            getInstance();
            
            // server is waiting client
            System.out.println("server in attesa");
            s = new ServerSocket(6789);
            
            // server accept the client connection (MAX = 10)
            for(int i = 0; i < MAXCLIENT; i++) {
                sockets.add(new ServerThread(s.accept()));
                sockets.get(i).start();
            }
            // closing the server
            s.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del messaggio!");
        }
    }
    
    // MAIN
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
        
        // this method is used to send the connected user list
        private void sendList() {
            try {
                String list = "list#";
                for(String s : usernames) {
                    list += ('\n' + "user: " + s);
                }
                list += '\n';
                for(ServerThread s : sockets) {
                    s.out.writeBytes(list);              
                }  
            }
            catch(Exception ex) {
                ex.toString();
            }
        }
        
        private void transmits() throws Exception {
            // open the stream and connect the data socket to client
            in = new BufferedReader (new InputStreamReader (clientS.getInputStream()));
            out = new DataOutputStream(clientS.getOutputStream());        
            
            // get the username in input
            usernameClient = in.readLine();
            System.out.println("User: " + usernameClient + " connected");                      
            
            // call the medthod sendList() to send the list of the connected user to all the conncted user
            sendList();
            
            for(;;) {
                // get from client the message
                String mex = in.readLine();
                // if the message contain #FINE# the connection to the server is closed
                if(mex.equals(ENDCOMMUNICATION)) {
                    System.out.println("User: " + usernameClient + " disconnected");                   
                    usernames.remove(usernameClient);
                    sockets.remove(this);
                    // then the server send the new list of the connected user to all the conncted user
                    sendList();
                    break;
                }
                else {  // else the messsage is send to the interested user or to all the connected user
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
                    } // if there is only one conncted user (the sender) the server say "There is no one connected"
                    else out.writeBytes("There is no one connected" + '\n');
                }
            }
        // close the stream and the data socket
        out.close();
        in.close();
        clientS.close();
        }
    }
}
    