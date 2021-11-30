package platformer.game.nea;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPanel extends javax.swing.JPanel {

    String Register = "Register";  //Defines that this is register data so the server knows what to do with it
    private String[] DetailsArray = new String[3];

    public RegisterPanel() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        UsernameField = new javax.swing.JTextField();
        BeginButton = new javax.swing.JButton();
        EmailField = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        Score = new javax.swing.JLabel();
        Score1 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        jPanel1.setBackground(new java.awt.Color(21, 27, 31));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 42)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 165, 0));
        jLabel1.setText("Register");

        UsernameField.setBackground(new java.awt.Color(255, 165, 0));
        UsernameField.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        UsernameField.setText("Username");
        UsernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameFieldActionPerformed(evt);
            }
        });

        BeginButton.setBackground(new java.awt.Color(255, 165, 0));
        BeginButton.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        BeginButton.setText("Begin");
        BeginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BeginButtonActionPerformed(evt);
            }
        });

        EmailField.setBackground(new java.awt.Color(255, 165, 0));
        EmailField.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        EmailField.setText("Email");

        jPasswordField1.setBackground(new java.awt.Color(255, 165, 0));

        jPasswordField2.setBackground(new java.awt.Color(255, 165, 0));

        Score.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        Score.setForeground(new java.awt.Color(255, 165, 0));
        Score.setText("Confirm:");

        Score1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        Score1.setForeground(new java.awt.Color(255, 165, 0));
        Score1.setText("Password:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(571, Short.MAX_VALUE)
                .addComponent(BeginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(484, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(538, 538, 538))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Score1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Score, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EmailField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPasswordField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(370, 370, 370))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Score))
                        .addGap(124, 124, 124)
                        .addComponent(BeginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Score1))
                .addGap(113, 113, 113))
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
    
    
    String PassRegEx = "([...a-zA-Z])([...0-9])";
    String EmailRegEx = "([...@])";
    Pattern Pass = Pattern.compile(PassRegEx);
    Pattern Email = Pattern.compile(EmailRegEx);
    

    private void BeginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BeginButtonActionPerformed

        DetailsArray[0] = UsernameField.getText();    //Places all of the inputted details into an array
        DetailsArray[1] = jPasswordField1.getText();
        DetailsArray[2] = EmailField.getText();
        
        Matcher MatchPass = Pass.matcher(DetailsArray[1]);
        Matcher MatchEmail = Email.matcher(DetailsArray[2]);
        
        boolean Valid = true;
        
        if(DetailsArray[0].length() < 4 || DetailsArray[0].length() > 16){
            System.out.println("Username MUST be inbetween 4 and 16 characters" );
            Valid = false;
        }
        
        if(!MatchEmail.find()){
            System.out.println("The email MUST be valid ");
            Valid = false;
        }
        if (!DetailsArray[1].equals(jPasswordField2.getText())) {  //Checks to make sure the passwords match     
            System.out.println("Passwords Must Match! ");
            Valid = false;
        }else if(!MatchPass.find()){
            System.out.println("Passwords MUST contain Numbers AND Letters ");
            Valid = false;
        }else if(DetailsArray[1].length() < 8){
            System.out.println("Passwords MUST be at least 8 characters long! ");
            Valid = false;
        }
        if(Valid == true){
            ClientServerConnection ParseInputs = new ClientServerConnection();
            ParseInputs.SendInputs(Register, DetailsArray);    //Returns the array to the server connection class so it can be sent to the main server for verification
        }


    }//GEN-LAST:event_BeginButtonActionPerformed
    
    
    public static void OriginalityLevel(String Duplication) {
        if (Duplication.equals("Duplicate")) {
            System.out.println("This account could not be activated due to an unoriginal username or email. Please try again");

        } else {
            System.out.println("Your account has been created!");
            
            /*
            PlatformerGameNEA BeginGame = new PlatformerGameNEA();
            BeginGame.BeginGameClass(1, 0, 0, false);  
            */
            String[] LevelArray = new String[1];
            LevelArray[0] = "1";
            
            //Goes to create a Statistics part in the leaderboard before passing us to level 1
            ClientServerConnection ParseInputs = new ClientServerConnection();
            ParseInputs.SendInputs("StatisticDetails", LevelArray);
        }
    }
    private void UsernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameFieldActionPerformed

    }//GEN-LAST:event_UsernameFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BeginButton;
    private javax.swing.JTextField EmailField;
    private javax.swing.JLabel Score;
    private javax.swing.JLabel Score1;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
