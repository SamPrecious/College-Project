package platformer.game.nea;

public class DeathPanel extends javax.swing.JPanel {

    private int DeathsToGame;
    private int HighScore;
    private String[] UpdateArray = new String[3];
    private boolean PlayerUlt;

    public DeathPanel(int CurrentBestScore, int CurrentDeaths, boolean Ultimate) {
        initComponents();
        HighScore = CurrentBestScore;
        DeathsToGame = CurrentDeaths;
        PlayerUlt = Ultimate;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        RetryButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(21, 27, 31));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 42)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 165, 0));
        jLabel1.setText("Not Congratulations");

        RetryButton.setBackground(new java.awt.Color(255, 165, 0));
        RetryButton.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        RetryButton.setText("Retry?");
        RetryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetryButtonActionPerformed(evt);
            }
        });

        ExitButton.setBackground(new java.awt.Color(255, 165, 0));
        ExitButton.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(405, 405, 405)
                        .addComponent(RetryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(469, 469, 469)
                        .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(375, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(451, 451, 451))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(74, 74, 74)
                .addComponent(RetryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE)
                .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void RetryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetryButtonActionPerformed
        PlatformerGameNEA BeginGame = new PlatformerGameNEA();
        BeginGame.BeginGameClass(2, HighScore, DeathsToGame, PlayerUlt);    //You can currently only die on level 2 as it is the only level with enemies so it is not yet necessary to add in code to support retrying on different levels as this  
        //panel will only ever be needed for level 2
    }//GEN-LAST:event_RetryButtonActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        UpdateArray[0] = "2"; //This is the level we are on. It is level 2 because you cant die on Level 1
        UpdateArray[1] = Integer.toString(HighScore);
        UpdateArray[2] = Integer.toString(DeathsToGame);

        ClientServerConnection ParseInputs = new ClientServerConnection();
        ParseInputs.SendInputs("UpdateUser", UpdateArray);

        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton RetryButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
