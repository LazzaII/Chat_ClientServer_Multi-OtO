package GUIclient;

import javax.swing.JFrame;
import GUIserver.GestioneChat;

public class FrameClient extends javax.swing.JFrame {
    
    private static final String ERRORLABEL = "Username not available, insert a new one and try again"; 
    private GestioneChat gc;
    
    public FrameClient() {
        // super("User form") used to set the frame's title
        super("User form");
        initComponents();
        // set the alignment of the label
        labelIntro.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        labelErr.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        labelErr.setVisible(false);
        labelErr.setText(ERRORLABEL);
        // server initialization (SINGLETON)
        gc = GestioneChat.getInstance();
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

        bttConf.setText("Submit");
        bttConf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttConfMouseClicked(evt);
            }
        });

        bttAnnulla.setText("Cancel");
        bttAnnulla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bttAnnullaMouseClicked(evt);
            }
        });

        labelIntro.setText("Hi! Insert your username to start chatting!");

        fieldUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldUserFocusGained(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bttAnnulla)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bttConf))
                    .addComponent(labelErr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldUser)
                    .addComponent(labelIntro, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(labelIntro)
                .addGap(40, 40, 40)
                .addComponent(fieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(labelErr, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bttConf)
                    .addComponent(bttAnnulla))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttAnnullaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttAnnullaMouseClicked
        // reset the input field
        fieldUser.setText("");
    }//GEN-LAST:event_bttAnnullaMouseClicked

    private void bttConfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bttConfMouseClicked
        // check the username
        String username = fieldUser.getText();
        if(gc.isFree(username)) {
            // if the username is available it gets added to the array
            gc.addUsername(username);
            FrameChat fc = new FrameChat(username);
        }
        else labelErr.setVisible(true);     // The label is set to visible
        // reset the input field
        fieldUser.setText("");
    }//GEN-LAST:event_bttConfMouseClicked

    private void fieldUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldUserFocusGained
        // the label is set to visible
        labelErr.setVisible(false);
    }//GEN-LAST:event_fieldUserFocusGained

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
