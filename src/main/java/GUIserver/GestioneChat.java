package GUIserver;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GestioneChat{
    private ArrayList<ServerThread> sockets = new ArrayList();
    private ArrayList <String> utenti = new ArrayList();
    private ServerSocket s;
    private static GestioneChat instance = null;

    //Utilizziamo un singleton della gestione chat perche i client devono fare riferimento allo stesso server
    public static GestioneChat getInstance() {
    if(instance == null)
      instance = new GestioneChat();
    return instance;
    }
    
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
    
    public boolean controlloUsername(String username){
        if(utenti.isEmpty()) return true;
         for(String u : utenti){
            if(!username.equalsIgnoreCase(u)) return true;
         }
        return false;
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
            inDalClient = new BufferedReader(new InputStreamReader(clientS.getInputStream()));
            outVersoClient = new DataOutputStream(clientS.getOutputStream());
            outVersoClient.writeBytes("Inserisci username" + '\n');
            usernameClient = inDalClient.readLine();

            utenti.add(usernameClient);

            System.out.println(usernameClient + " connesso");
            outVersoClient.writeBytes(usernameClient + " connesso" + '\n' + '\n');
            for(int i = 0; i < sockets.size(); i++){
                sockets.get(i).outVersoClient.writeBytes("Utenti connessi:" + '\n');
                for(int j = 0; j < utenti.size(); j++) {
                    sockets.get(i).outVersoClient.writeBytes("Utente:" + utenti.get(j) + '\n');
                }
                sockets.get(i).outVersoClient.writeBytes("\n");
            }

            try {
                for(;;) {
                    String mex = inDalClient.readLine();
                    if(mex.equalsIgnoreCase("FINE")){
                        System.out.println(usernameClient + ": utente disconnesso" + '\n');
                        utenti.remove(usernameClient);
                        sockets.remove(this);
                        for(int i = 0; i < sockets.size(); i++){
                            sockets.get(i).outVersoClient.writeBytes("Utenti connessi:" + '\n');
                            for(int j = 0; j < utenti.size(); j++) {
                                sockets.get(i).outVersoClient.writeBytes("Utente:" + utenti.get(j) + '\n');
                            }
                            sockets.get(i).outVersoClient.writeBytes("\n");
                        }
                        break;
                    }
                    else if(sockets.size() > 1) {
                        if(mex.contains("@Everyone")) {
                            String[] mexSplit = mex.split("@");
                            for (ServerThread s : sockets)
                            {
                                if(s != this) s.outVersoClient.writeBytes(usernameClient + ": " + mexSplit[0] + '\n');
                            }
                        }
                        else {
                            String[] mexSplit = mex.split("@");
                            for (ServerThread s : sockets)
                            {
                                if(s != this)
                                {
                                    if(s.usernameClient.equals(mexSplit[1])) s.outVersoClient.writeBytes(usernameClient + ": " + mexSplit[0] + '\n');
                                    else outVersoClient.writeBytes("Nessun destinatario trovato");
                                }
                            }
                        }
                    }
                    else outVersoClient.writeBytes("Nessuno � connesso" + '\n');
                }
            }catch (Exception ex) {
                System.out.println("Errore sconosciuto");
                System.exit(1);
            }
            outVersoClient.close();
            inDalClient.close();
            clientS.close();
        }
    }
}