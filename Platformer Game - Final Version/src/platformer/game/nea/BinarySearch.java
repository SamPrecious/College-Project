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
public class BinarySearch extends javax.swing.JPanel {

    private String[][] PlayerArray;
    private String FinalScore;

    private String[] Search = new String[1];

    public void BinarySearch() {
        
        initComponents();
        repaint();
    }

    public void Start(String UNAME) {
        Search[0] = UNAME;
        ClientServerConnection RetrieveLeaderboard = new ClientServerConnection();
        RetrieveLeaderboard.SendInputs("RetrieveSearch", Search);
    }

    public void SaveArray(String[][] UsernameArray) {
        System.out.println("Saving Array");
        PlayerArray = new String[PlayerArray.length][3];
        PlayerArray = UsernameArray;
        System.out.println(PlayerArray.length);

    }

    public void StartSearch(String Username, String[][] UserArray) {
        //Since the set will be unordered and you wont have a range to work with when binary searching ASCII values the binary search only applies to the first part of the search before it turns into a linear search. 
        //Finds ascii value of first letter of Username to compare with the other strings as their their first letters are in alphabetical and thus ascii order

        System.out.println("HEY! ");
        char ToFindChar = Username.charAt(0);
        int FirstCharAscii = (int) ToFindChar;

        int ArrayLength = UserArray.length;
        int i = 0;
        while (i < ArrayLength) {
            System.out.println("Array[" + i + "][0]: " + UserArray[i][0]); //Gives Username
            System.out.println("Array[" + i + "][2]: " + UserArray[i][2]); //Gives Score
            i++;
        }

        int MidPointer = ArrayLength / 2;
        int isOdd = MidPointer % 2;
        if (isOdd == 1) {
            MidPointer++; //This adds 1 to the value of Char/2 if its odd to take preference for the bottom half of an Descending order array

        }
        boolean found = false;
        String UsernameComparison = UserArray[MidPointer][0];
        char ComparisonChar = UsernameComparison.charAt(0);
        System.out.println(ArrayLength);


        String CurrentUsername;
        int TempVal;
        int MaxPointer = ArrayLength;
        int MinPointer = 1;
        int Range;
        Boolean FinalFound = false;

        while (found == false) { //This binary searches for the first Char of the string
            CurrentUsername = UserArray[MidPointer - 1][0];
            System.out.println("HEY!");
            char CurrentUsernameChar = CurrentUsername.charAt(0);
            int CurrentAscii = (int) CurrentUsernameChar;
            System.out.println("Player Input Ascii Val: " + FirstCharAscii);
            System.out.println("Comparison Ascii Val  : " + CurrentAscii);
            if (CurrentUsername.equals(Username)) { //This checks if the username in the middle of the Array matches with it.
                found = true;
                FinalFound = true;
                
            } else if (CurrentAscii > FirstCharAscii) {
                MaxPointer = MidPointer - 1;
                Range = MaxPointer - MinPointer;
                isOdd = Range % 2;
                Range = Range/2;
                if (isOdd == 1) {
                    Range++; //This adds 1 to the value of Char/2 if its odd to take preference for the bottom half of an Descending order array
                }  
                MidPointer = MinPointer + Range;
                System.out.println("MidPoint: " + MidPointer);
                System.out.println("FirstCharTooLow");
                
            } else if (CurrentAscii < FirstCharAscii) {
                MinPointer = MidPointer + 1;
                Range = MaxPointer - MinPointer;
                isOdd = Range % 2;
                Range = Range/2;
                if (isOdd == 1) {
                    Range++; //This adds 1 to the value of Char/2 if its odd to take preference for the bottom half of an Descending order array
                }
                MidPointer = MinPointer + Range;
                System.out.println("MidPoint: " + MidPointer);
                System.out.println("FirstCharTooHigh");
            } else if (CurrentAscii == FirstCharAscii){
                found = true;
            }
        }
        int SearchVal = 0;
        int x = 0;
        int z = 1;
        while(x == 0 && FinalFound == false){    //Binary Search will not work after first character as we wont have a range so we move to a linear search
            char ToFindChar2 = Username.charAt(z);
            int SecondCharAscii = (int) ToFindChar;
        
            CurrentUsername = UserArray[MidPointer - 1][0];
            char CurrentUsernameChar = CurrentUsername.charAt(z);
            int CurrentAscii = (int) CurrentUsernameChar;
            
            if(CurrentAscii < SecondCharAscii){
                x = 1;
                SearchVal = -1;
            }else if(CurrentAscii > SecondCharAscii){
                x = 1;
                SearchVal = 1;
            }else if(CurrentAscii == SecondCharAscii){
                z++;
            }
            
        }
        boolean NoMore = false;
        while(!FinalFound && !NoMore){
            System.out.println("Searching through final values");
            MidPointer = MidPointer + SearchVal;
            CurrentUsername = UserArray[MidPointer - 1][0];
            
            if(CurrentUsername.equals(Username)){
                FinalFound = true;
            }
            if(MidPointer == 0 || MidPointer == CurrentUsername.length()){  //If it gets to the end of the array and user is not found then this happens
                NoMore = true;
            }

        }
        
        if(FinalFound){
            System.out.println("Input UName: " + Username);
            System.out.println("Username: " + UserArray[MidPointer - 1][0]);
            FinalScore = UserArray[MidPointer - 1][2];
            System.out.println("Score: " + FinalScore);
            Score.setText(Username + ": " + FinalScore);    //Sets the final 
        
            System.out.println("Done");
            
        }else{
            System.out.println("User Not Found");
            Score.setText("User Not Found");    //Sets the final 

        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        BinarySearch = new javax.swing.JButton();
        UsernameField = new javax.swing.JTextField();
        Score = new javax.swing.JLabel();
        ExitButton = new javax.swing.JButton();

        jPanel3.setBackground(new java.awt.Color(21, 27, 31));
        jPanel3.setPreferredSize(new java.awt.Dimension(1280, 720));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 42)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 165, 0));
        jLabel2.setText("Binary Search");

        BinarySearch.setBackground(new java.awt.Color(255, 165, 0));
        BinarySearch.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        BinarySearch.setText("Search!");
        BinarySearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BinarySearchActionPerformed(evt);
            }
        });

        UsernameField.setBackground(new java.awt.Color(255, 165, 0));
        UsernameField.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        UsernameField.setText("Username");
        UsernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameFieldActionPerformed(evt);
            }
        });

        Score.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        Score.setForeground(new java.awt.Color(255, 165, 0));
        Score.setText("Score" + FinalScore);

        ExitButton.setBackground(new java.awt.Color(255, 165, 0));
        ExitButton.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(568, 568, 568)
                .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(400, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(518, 518, 518))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(BinarySearch, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                            .addComponent(UsernameField))
                        .addGap(380, 380, 380))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(Score)
                        .addGap(496, 496, 496))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(BinarySearch, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(Score, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(ExitButton)
                .addGap(113, 113, 113))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BinarySearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BinarySearchActionPerformed
        String Username = UsernameField.getText();
        Start(Username);

    }//GEN-LAST:event_BinarySearchActionPerformed

    private void UsernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameFieldActionPerformed
        ClientServerConnection RetrieveArray = new ClientServerConnection();
        RetrieveArray.Retrieve("Julie");

    }//GEN-LAST:event_UsernameFieldActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BinarySearch;
    private javax.swing.JButton ExitButton;
    private javax.swing.JLabel Score;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
