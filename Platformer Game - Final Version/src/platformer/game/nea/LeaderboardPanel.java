package platformer.game.nea;

public class LeaderboardPanel extends javax.swing.JPanel {

    private String[] Sort = new String[1];
    private int[] Numbers;
    private int[] Helpers;
    private int Leng;
    private int LeaderboardSizeAccessible;
    private int z;
    private String[][] TrueUnsortedLeaderboard;
    private String[][] SortedLeaderboard;
    private int[] ReverseNumbers;
    private int e = 0;

    public LeaderboardPanel() {
        initComponents();
        System.out.println("Initilised");
    }

    public void RetrieveLeaderboard() {

        Sort[0] = "Sorting";
        ClientServerConnection RetrieveLeaderboard = new ClientServerConnection();
        RetrieveLeaderboard.SendInputs("RetrieveLeaderboard", Sort);

    }

    public void SortingLeaderboard(int LeaderboardSize, String[][] UnsortedLeaderboard) {
        z = 0;
        System.out.println("Sorting the leaderboard!");
        int[] Temporary1DArray = new int[LeaderboardSize + 1];
        int CurrentRow = 0;
        LeaderboardSizeAccessible = LeaderboardSize;
        TrueUnsortedLeaderboard = UnsortedLeaderboard;
        int z = 0;

        while (CurrentRow != LeaderboardSize) {
            System.out.println("[" + CurrentRow + "][1]:" + UnsortedLeaderboard[CurrentRow][1]);
            String CurrentArrayValue = UnsortedLeaderboard[CurrentRow][1];
            int CurrentArrayValueInt = Integer.parseInt(CurrentArrayValue);
            Temporary1DArray[CurrentRow] = CurrentArrayValueInt;
            CurrentRow++;
        }
        this.Numbers = Temporary1DArray;
        Leng = Temporary1DArray.length;
        this.Helpers = new int[Leng];
        MergeSort(0, Leng - 1);

    }

    private void MergeSort(int Smaller, int Larger) {

        if (Smaller < Larger) {

            int Midpoint = Smaller + (Larger - Smaller) / 2;
            MergeSort(Smaller, Midpoint);    //This sorts the left half of the array

            MergeSort(Midpoint + 1, Larger); //This sorts the right half of the array

            Merge(Smaller, Midpoint, Larger); //This merges the arrays back together

        }

    }

    private void Merge(int Smaller, int Midpoint, int Larger) {
        z++;
        System.out.println(z);

        for (int i = Smaller; i <= Larger; i++) {
            Helpers[i] = Numbers[i];
        }

        int i = Smaller;
        int j = Midpoint + 1;
        int k = Smaller;

        while (i <= Midpoint && j <= Larger) {
            if (Helpers[i] <= Helpers[j]) {
                Numbers[k] = Helpers[i];
                i++;
            } else {
                Numbers[k] = Helpers[j];
                j++;
            }
            k++;
        }

        while (i <= Midpoint) {
            Numbers[k] = Helpers[i];
            k++;
            i++;
        }

        if (z == LeaderboardSizeAccessible) {
            e = 0;
            while (e != LeaderboardSizeAccessible) {
                System.out.println("Numbers[e + 1] " + Numbers[e + 1]);
                e++;

            }

        }

        if (LeaderboardSizeAccessible == e) {
            Stack();
        }

    }

    public void Stack() {
        //Numbers is a list that has scores going up in ascending order, I am creating a stack to reverse this order so the high scores are at the top of the numbers as its part of a leaderboard
        int NumberLength = Numbers.length;
        ReverseNumbers = new int[NumberLength];
        NumberLength--;
        int CountingDown = NumberLength;
        System.out.println(CountingDown);
        int UpToNumLeng = 0;

        while (UpToNumLeng < NumberLength) {
            ReverseNumbers[UpToNumLeng] = Numbers[CountingDown];
            System.out.println(Numbers[CountingDown]);
            UpToNumLeng++;
            CountingDown--;
        }
        UpToNumLeng = 0;

        while (UpToNumLeng < NumberLength) {
            System.out.println("ReverseNum[" + UpToNumLeng + "]: " + ReverseNumbers[UpToNumLeng]);
            UpToNumLeng++;
        }

        Sorted2DLeaderboardV2();

    }

    public void Sorted2DLeaderboardV2() {
        int p = 0;
        while (p < LeaderboardSizeAccessible) {
            System.out.println("SortedLeaderboard[" + z + "][0]" + TrueUnsortedLeaderboard[z][0]);
            System.out.println("SortedLeaderboard[" + z + "][1]" + TrueUnsortedLeaderboard[z][1]);
            String ComparisonScoreString2 = TrueUnsortedLeaderboard[z][0];
            System.out.println("THE STRING OF IT IS: " + ComparisonScoreString2);
            p++;
        }
        SortedLeaderboard = new String[LeaderboardSizeAccessible + 1][2];
        int i = 0;
        //Numbers[1 - ArraySize] have sorted scores stored
        while (i != LeaderboardSizeAccessible) {
            System.out.println("Setting Score");
            int CurrentScore = ReverseNumbers[i]; //Gives us the current score
            String CurrentScoreString = Integer.toString(CurrentScore);
            SortedLeaderboard[i][1] = CurrentScoreString;
            int j = 0;
            boolean found = false;
            while (j != LeaderboardSizeAccessible) {
                System.out.println("Setting Username");
                String ComparisonScoreString = TrueUnsortedLeaderboard[j][1];
                System.out.println("ComparisonScoreString: " + ComparisonScoreString);
                System.out.println("Current: " + CurrentScoreString);
                
                if (ComparisonScoreString.equals(CurrentScoreString) && !found) {
                    SortedLeaderboard[i][0] = TrueUnsortedLeaderboard[j][0];
                    System.out.println("IS " + SortedLeaderboard[i][0]);
                    System.out.println(j);
                    TrueUnsortedLeaderboard[j][1] = "9999"; //Score value can never be reached
                    found = true;
                }
                j++;
            }
            i++;
            
            

        }

        int z = 0;
        System.out.println("Array Sorted! ");
        while (z < LeaderboardSizeAccessible) {
            System.out.println("SortedLeaderboard[" + z + "][0]" + SortedLeaderboard[z][0]);
            System.out.println("SortedLeaderboard[" + z + "][1]" + SortedLeaderboard[z][1]);
            z++;
        }
        
        int j = 0;
        while(j < LeaderboardSizeAccessible){
                System.out.println(TrueUnsortedLeaderboard[j][0]);
                System.out.println(TrueUnsortedLeaderboard[j][1]);
                j++;
            }
        First.setText("NICE");
        First.setText(SortedLeaderboard[0][0]);
        Score1.setText(SortedLeaderboard[0][1]);
        
        if (LeaderboardSizeAccessible > 1) {
            Second.setText(SortedLeaderboard[1][0]);
            Score2.setText(SortedLeaderboard[1][1]);

            if (LeaderboardSizeAccessible > 2) {
                Third.setText(SortedLeaderboard[2][0]);
                Score3.setText(SortedLeaderboard[2][1]);

                if (LeaderboardSizeAccessible > 3) {
                    Fourth.setText(SortedLeaderboard[3][0]);
                    Score4.setText(SortedLeaderboard[3][1]);
                    if (LeaderboardSizeAccessible > 4) {
                        Fifth.setText(SortedLeaderboard[4][0]);
                        Score5.setText(SortedLeaderboard[4][1]);

                    }
                }

            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        First = new javax.swing.JLabel();
        Third = new javax.swing.JLabel();
        Fourth = new javax.swing.JLabel();
        Fifth = new javax.swing.JLabel();
        Second = new javax.swing.JLabel();
        Score1 = new javax.swing.JLabel();
        Score2 = new javax.swing.JLabel();
        Score3 = new javax.swing.JLabel();
        Score4 = new javax.swing.JLabel();
        Score5 = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(21, 27, 31));
        jPanel3.setPreferredSize(new java.awt.Dimension(1280, 720));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 42)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 165, 0));
        jLabel2.setText("Leaderboard");

        First.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        First.setForeground(new java.awt.Color(255, 165, 0));
        First.setText("NO VALUES");

        Third.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        Third.setForeground(new java.awt.Color(255, 165, 0));
        Third.setText("NO VALUES");

        Fourth.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        Fourth.setForeground(new java.awt.Color(255, 165, 0));
        Fourth.setText("NO VALUES");

        Fifth.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        Fifth.setForeground(new java.awt.Color(255, 165, 0));
        Fifth.setText("NO VALUES");

        Second.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        Second.setForeground(new java.awt.Color(255, 165, 0));
        Second.setText("NO VALUES");

        Score1.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        Score1.setForeground(new java.awt.Color(255, 165, 0));
        Score1.setText("NO VALUES");

        Score2.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        Score2.setForeground(new java.awt.Color(255, 165, 0));
        Score2.setText("NO VALUES");

        Score3.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        Score3.setForeground(new java.awt.Color(255, 165, 0));
        Score3.setText("NO VALUES");

        Score4.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        Score4.setForeground(new java.awt.Color(255, 165, 0));
        Score4.setText("NO VALUES");

        Score5.setFont(new java.awt.Font("Arial Black", 1, 36)); // NOI18N
        Score5.setForeground(new java.awt.Color(255, 165, 0));
        Score5.setText("NO VALUES");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(499, 499, 499)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(First)
                            .addComponent(Fourth)
                            .addComponent(Fifth)
                            .addComponent(Third)
                            .addComponent(Second))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Score1)
                            .addComponent(Score4)
                            .addComponent(Score5)
                            .addComponent(Score3)
                            .addComponent(Score2))))
                .addContainerGap(407, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(First, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Second, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(Third, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Fourth, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Fifth, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Score1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Score2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(Score3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Score4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Score5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fifth;
    private javax.swing.JLabel First;
    private javax.swing.JLabel Fourth;
    private javax.swing.JLabel Score1;
    private javax.swing.JLabel Score2;
    private javax.swing.JLabel Score3;
    private javax.swing.JLabel Score4;
    private javax.swing.JLabel Score5;
    private javax.swing.JLabel Second;
    private javax.swing.JLabel Third;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
