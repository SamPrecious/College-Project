package platformer.game.nea;

public class HomePanel extends javax.swing.JPanel {

    public HomePanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        NewGameButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ContinueButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        LeaderboardButton = new javax.swing.JButton();
        FindMyScore = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(21, 27, 31));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));

        NewGameButton.setBackground(new java.awt.Color(255, 165, 0));
        NewGameButton.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        NewGameButton.setText("New Game");
        NewGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGameButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 42)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 165, 0));
        jLabel1.setText("Imaginitive Title");

        ContinueButton.setBackground(new java.awt.Color(255, 165, 0));
        ContinueButton.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        ContinueButton.setText("Continue");
        ContinueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContinueButtonActionPerformed(evt);
            }
        });

        ExitButton.setBackground(new java.awt.Color(255, 165, 0));
        ExitButton.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ExitButton.setText("Quit Game");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        LeaderboardButton.setBackground(new java.awt.Color(255, 165, 0));
        LeaderboardButton.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        LeaderboardButton.setText("Leaderboard");
        LeaderboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaderboardButtonActionPerformed(evt);
            }
        });

        FindMyScore.setBackground(new java.awt.Color(255, 165, 0));
        FindMyScore.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        FindMyScore.setText("Find My Score");
        FindMyScore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindMyScoreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(336, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(532, 532, 532))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(352, 352, 352)
                                .addComponent(LeaderboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(NewGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ContinueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(394, 394, 394))
                            .addComponent(FindMyScore, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(NewGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(ContinueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(FindMyScore, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LeaderboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ContinueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContinueButtonActionPerformed
        PlatformerGameNEA ContinueGame = new PlatformerGameNEA();
        ContinueGame.ContinueGameClass();
    }//GEN-LAST:event_ContinueButtonActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void NewGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewGameButtonActionPerformed
        PlatformerGameNEA NewGame = new PlatformerGameNEA();
        NewGame.NewGameClass();
    }//GEN-LAST:event_NewGameButtonActionPerformed

    private void LeaderboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeaderboardButtonActionPerformed
        System.out.println("Initialising Leaderboard");
        PlatformerGameNEA LeaderboardOpen = new PlatformerGameNEA();
        LeaderboardOpen.LeaderboardPanelClass();


    }//GEN-LAST:event_LeaderboardButtonActionPerformed

    private void FindMyScoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindMyScoreActionPerformed
        PlatformerGameNEA CommenceSearch = new PlatformerGameNEA();
        CommenceSearch.BinarySearchPanelClass();
    }//GEN-LAST:event_FindMyScoreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ContinueButton;
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton FindMyScore;
    private javax.swing.JButton LeaderboardButton;
    private javax.swing.JButton NewGameButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
