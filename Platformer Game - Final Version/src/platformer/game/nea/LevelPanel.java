
package platformer.game.nea;


public class LevelPanel extends javax.swing.JPanel {
        
    private String[] LevelArray = new String[1]; //Its just here to save time so i dont have to change all the classes around. Would normally only send "LevelSelection"
    private Boolean Level2Access = false;
    private int Level;
    
    /**
     * Creates new form LevelPanel
     */
    public LevelPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Level1 = new javax.swing.JButton();
        Level2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(21, 27, 31));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 165, 0));
        jLabel1.setText("Levels");

        Level1.setBackground(new java.awt.Color(255, 165, 0));
        Level1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        Level1.setText("Level 1");
        Level1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Level1ActionPerformed(evt);
            }
        });

        Level2.setBackground(new java.awt.Color(255, 165, 0));
        Level2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        Level2.setText("Level 2");
        Level2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Level2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(576, 576, 576)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(477, 477, 477)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Level2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Level1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(503, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addComponent(Level1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(Level2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(338, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Level1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Level1ActionPerformed
        
        LevelArray[0] = "1";
        ClientServerConnection ParseInputs = new ClientServerConnection();
        ParseInputs.SendInputs("StatisticDetails", LevelArray);
        /*
        PlatformerGameNEA BeginGame = new PlatformerGameNEA();
        BeginGame.BeginGameClass(1);
        */  

    }//GEN-LAST:event_Level1ActionPerformed

    private void Level2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Level2ActionPerformed
        
        Level2Access = false;
        LevelArray[0] = "2";
        ClientServerConnection ParseInputs = new ClientServerConnection();
        ParseInputs.SendInputs("LevelSelection", LevelArray);
        
    }//GEN-LAST:event_Level2ActionPerformed
    public void LevelTwoAccess(boolean Level2) {
        Level2Access = Level2;
        if(Level2 == false){  //Since this is a short project I have only had time to implement 2 levels. If the user has beaten more than 0 levels they are able to play level 2 either way so it works
            System.out.println("Sorry, you dont seem to have beaten this level yet" );
            Level = 1;
        }else{
            LevelArray[0] = "2";
            Level = 2;
            System.out.println("Level = "+ Level);
            ClientServerConnection ParseInputs = new ClientServerConnection();
            ParseInputs.SendInputs("StatisticDetails", LevelArray);
            
        }
        
    }
    public void StartGame(int NewLevel, int Score, int Deaths, boolean Ultimate){
        
        System.out.println("CurrentLevel: "+ Level);
        PlatformerGameNEA BeginGame = new PlatformerGameNEA();
        BeginGame.BeginGameClass(NewLevel, Score, Deaths, Ultimate);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Level1;
    private javax.swing.JButton Level2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
