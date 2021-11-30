//The aim of this panel is to allow people who have previously played to retain progress and allow their name to be put on a leaderboard.
package platformer.game.nea;

public class LoginPanel extends javax.swing.JPanel {

    public LoginPanel() {
        initComponents();

    }
    String Login = "Login";  //Defines that this is register data so the server knows what to do with it
    private static int BruteForce = 0;
    private String[] DetailsArray = new String[2];

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        UsernameField = new javax.swing.JTextField();
        StartButton = new javax.swing.JButton();
        PasswordField1 = new javax.swing.JPasswordField();

        jPanel1.setBackground(new java.awt.Color(21, 27, 31));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 165, 0));
        jLabel1.setText("Login");

        UsernameField.setBackground(new java.awt.Color(255, 165, 0));
        UsernameField.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        UsernameField.setText("Username");
        UsernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameFieldActionPerformed(evt);
            }
        });

        StartButton.setBackground(new java.awt.Color(255, 165, 0));
        StartButton.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        StartButton.setText("Start!");
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });

        PasswordField1.setBackground(new java.awt.Color(255, 165, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(586, 586, 586)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(475, 475, 475)
                        .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(383, 383, 383)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(UsernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                            .addComponent(PasswordField1))))
                .addContainerGap(417, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addGap(74, 74, 74)
                .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
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

    private void UsernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameFieldActionPerformed

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        DetailsArray[0] = UsernameField.getText();    //Places all of the inputted details into an array
        DetailsArray[1] = PasswordField1.getText();

        ClientServerConnection ParseInputs = new ClientServerConnection();
        ParseInputs.SendInputs(Login, DetailsArray);    //Returns the array to the server connection class so it can be sent to the main server for verification


    }//GEN-LAST:event_StartButtonActionPerformed
    public static void AccessLevels(String Access) {
        if (BruteForce < 5) {
            System.out.println("REJ");
            if (Access.equals("Granted")) {
                System.out.println("Granted!");
                PlatformerGameNEA LevelSelection = new PlatformerGameNEA();
                LevelSelection.LevelPanelClass();
            } else {
                System.out.println("Denied!");
                BruteForce++;
                int Attempts = 5 - BruteForce;
                System.out.println("Attempts Left: " + Attempts);

            }
        }else{
            System.out.println("Sorry, you have tried too many times. Please restart to continue. ");
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField PasswordField1;
    private javax.swing.JButton StartButton;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
