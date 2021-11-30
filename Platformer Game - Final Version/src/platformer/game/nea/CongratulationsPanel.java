    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.game.nea;

/**
 *
 * @author zambl
 */
public class CongratulationsPanel extends javax.swing.JPanel {

    /**
     * Creates new form CongratulationsPanel
     */
    private int DeathsToGame;
    private int HighScore;
    private int Level;
    private String[] UpdateArray = new String[3];
    public CongratulationsPanel(int CurrentLevel, int CurrentBestScore, int CurrentDeaths) {
        initComponents();
        Level = CurrentLevel;
        HighScore = CurrentBestScore;
        DeathsToGame = CurrentDeaths;
        
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ExitButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(21, 27, 31));
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setVerifyInputWhenFocusTarget(false);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 42)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 165, 0));
        jLabel1.setText("Congratulations");

        ExitButton.setBackground(new java.awt.Color(255, 165, 0));
        ExitButton.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(447, 447, 447)
                .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(464, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(488, 488, 488)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(528, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(143, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(jLabel1)
                    .addContainerGap(643, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        UpdateArray[0] = Integer.toString(Level);
        UpdateArray[1] = Integer.toString(HighScore);
        UpdateArray[2] = Integer.toString(DeathsToGame);
        
        System.out.println("UPDATING USER WITH LEVEL: " + Level);
        ClientServerConnection ParseInputs = new ClientServerConnection();
        ParseInputs.SendInputs("UpdateUser", UpdateArray); 
        
        
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitButton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
