package GUIclient;
import GUIserver.*;
import javax.swing.*;

public class FrameChat extends javax.swing.JFrame {
    Client c;
    GestioneChat gc = GestioneChat.getInstance();
    
    public FrameChat(Client c) {
        initComponents();
        this.c = c;
        this.setName(c.getUsername());        
        System.out.println(c.getUsername());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        bttExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaRecive = new javax.swing.JTextArea();
        labelRecive = new javax.swing.JLabel();
        labelSend = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaSend = new javax.swing.JTextArea();
        bttSend = new javax.swing.JButton();
        bttAnnulla = new javax.swing.JButton();
        fieldText = new javax.swing.JTextField();
        labelList = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        userList = new javax.swing.JTextArea();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bttExit.setText("Esci");
        bttExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttExitMouseClicked(evt);
            }
        });

        areaRecive.setEditable(false);
        areaRecive.setColumns(20);
        areaRecive.setRows(5);
        jScrollPane1.setViewportView(areaRecive);

        labelRecive.setText("Ricevuti");

        labelSend.setText("Inviati");

        areaSend.setEditable(false);
        areaSend.setColumns(20);
        areaSend.setRows(5);
        jScrollPane2.setViewportView(areaSend);

        bttSend.setText("Invia");
        bttSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttSendMouseClicked(evt);
            }
        });

        bttAnnulla.setText("Annulla");
        bttAnnulla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttAnnullaMouseClicked(evt);
            }
        });

        labelList.setText("Lista utenti connessi");

        userList.setEditable(false);
        userList.setColumns(20);
        userList.setRows(5);
        jScrollPane3.setViewportView(userList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelRecive))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(bttAnnulla)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bttSend))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                                .addComponent(fieldText))
                            .addComponent(labelSend))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelList, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(bttExit)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelRecive, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSend)
                        .addComponent(labelList)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttExit)
                    .addComponent(bttSend)
                    .addComponent(bttAnnulla))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttExitMouseClicked
        
    }//GEN-LAST:event_bttExitMouseClicked

    private void bttAnnullaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttAnnullaMouseClicked
        fieldText.setText("");
    }//GEN-LAST:event_bttAnnullaMouseClicked

    private void bttSendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttSendMouseClicked
       String mex = fieldText.getText();
       c.sendMex(mex);
    }//GEN-LAST:event_bttSendMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaRecive;
    private javax.swing.JTextArea areaSend;
    private javax.swing.JButton bttAnnulla;
    private javax.swing.JButton bttExit;
    private javax.swing.JButton bttSend;
    private javax.swing.JTextField fieldText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelList;
    private javax.swing.JLabel labelRecive;
    private javax.swing.JLabel labelSend;
    private javax.swing.JTextArea userList;
    // End of variables declaration//GEN-END:variables

    
    public void updateUserList() {
        String list = "";
        for(String s : gc.getUtenti()) {
            list += s + '\n';
        }
        userList.setText(list);
    }
}