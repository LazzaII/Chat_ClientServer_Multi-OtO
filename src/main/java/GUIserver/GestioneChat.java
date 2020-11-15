package GUIserver;

import GUIclient.FrameChat;
import GUIclient.FrameClient;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GestioneChat{
    private ArrayList<ServerThread> sockets = new ArrayList<>();
    private ArrayList <FrameChat> chats = new ArrayList<>();
    private ServerSocket s;
    
    public void start(){
        try{
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
            System.exit(1);
        }
    }
    
    public static void main(String[] args) {
        GestioneChat tcpServer = new GestioneChat();
        tcpServer.start();
    }

    public class ServerThread extends Thread{
            private Socket clientS;
            private BufferedReader inDalClient;
            private DataOutputStream outVersoClient;
            public String usernameClient;
            FrameChat frm = new FrameChat(usernameClient);  
    
        public ServerThread (Socket socket){
                this.clientS = socket;
        }

        @Override
        public void run (){
            try{
               chats.add(frm);
               frm.setVisible(true);
               for(FrameChat f : chats)
               {
                    f.updateUserList();
               }
            }catch (Exception e){
                e.toString();
            }
        }
        
        public void sendMex(String s){ 
            try{
                String[] mexSplit = s.split("@");
                if(sockets.size() > 1) {
                    if(mexSplit[1].equalsIgnoreCase("Everyone")) {         
                        for (ServerThread n : sockets) {
                            if(n != this) n.outVersoClient.writeBytes(usernameClient + ": " + mexSplit[0] + '\n');
                        }
                    }
                }
                else {
                    for (ServerThread n : sockets) {
                        if(n != this){
                            if(n.usernameClient.equals(mexSplit[1])) {
                                n.outVersoClient.writeBytes(usernameClient + ": " + mexSplit[0] + '\n');
                                break;
                            }
                        }
                    }
                }   
            }catch(IOException e){
                    e.toString();      
            }
        }
    }
}
    
