package GUIclient;

import javax.swing.JFrame;
import GUIserver.GestioneChat;
import java.util.ArrayList;

public class FrameClient extends javax.swing.JFrame {
    Client c;
    static ArrayList<String> utenti = new ArrayList<>();

    public static ArrayList<String> getUtenti() {
        return utenti;
    }
    
    public FrameClient() {
        initComponents();
        labelErr.setHorizontalAlignment(javax.swing.JLabel.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bttConf = new javax.swing.JButton();
        bttAnnulla = new javax.swing.JButton();
        labelIntro = new javax.swing.JLabel();
        labelErr = new javax.swing.JLabel();
        fieldUser = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bttConf.setText("Conferma");
        bttConf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttConfMouseClicked(evt);
            }
        });

        bttAnnulla.setText("Annulla");
        bttAnnulla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttAnnullaMouseClicked(evt);
            }
        });

        labelIntro.setText("Benvenuto! Inserisci il tuo username");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelIntro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldUser)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bttAnnulla)
                        .addGap(54, 54, 54)
                        .addComponent(bttConf))
                    .addComponent(labelErr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(labelIntro)
                .addGap(40, 40, 40)
                .addComponent(fieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(labelErr, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttConf)
                    .addComponent(bttAnnulla))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttAnnullaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttAnnullaMouseClicked
        fieldUser.setText("");
    }//GEN-LAST:event_bttAnnullaMouseClicked

    private void bttConfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttConfMouseClicked
        boolean presente = false;
        String username = fieldUser.getText();
        if(utenti.isEmpty())utenti.add(username);
        else{
            for(String u : utenti){
                if(username.equalsIgnoreCase(u)){
                    labelErr.setText("Nome utente non disponibile");
                presente = true;
                break;
                }
            }
        }
        if(!presente){
            utenti.add(username);
            c = new Client(username);
            labelErr.setText("");
        }
    }//GEN-LAST:event_bttConfMouseClicked

    public static void main(String args[]) {
        FrameClient frm = new FrameClient();
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttAnnulla;
    private javax.swing.JButton bttConf;
    private javax.swing.JTextField fieldUser;
    private javax.swing.JLabel labelErr;
    private javax.swing.JLabel labelIntro;
    // End of variables declaration//GEN-END:variables
}
