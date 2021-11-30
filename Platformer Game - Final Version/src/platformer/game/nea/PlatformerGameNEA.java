package platformer.game.nea;

import java.awt.Dimension;
import javax.swing.JPanel;

public class PlatformerGameNEA {

    public static ProjectionFrame frame = new ProjectionFrame();
    public static BinarySearch SearchIT = new BinarySearch();
    public static LeaderboardPanel Retrieve = new LeaderboardPanel();

    public static boolean SearchDone = false;

    public static void main(String[] args) {

        ClientServerConnection ClientToServer = new ClientServerConnection();
        ClientToServer.InitialiseConnection();

        FrameCalibrator(new HomePanel());
    }

    public static void FrameCalibrator(JPanel FrameMethod) {
        frame.getContentPane().add(FrameMethod); //This makes the Projected Panel become the HomePanel
        frame.setContentPane(FrameMethod);
        frame.setPreferredSize(new Dimension(1920, 1080));
        frame.setLocationRelativeTo(null); // This sets the JFrame to centralise and makes the middle of it in the middle of the monitor
        frame.setVisible(true); //The frame is currently hidden. This frame makes it viewable. 
    }

    public static void NewGameClass() {
        FrameCalibrator(new RegisterPanel());

    }

    public static void ContinueGameClass() {
        FrameCalibrator(new LoginPanel());

    }

    public static void BeginGameClass(int Level, int Score, int Deaths, boolean Ultimate) {

        if(Ultimate){
            System.out.println("LEVEL " + Level);
        }
        GamePanel gamepanel = new GamePanel();
        FrameCalibrator(gamepanel);
        gamepanel.start(Level, Score, Deaths, Ultimate);

    }

    public static void LevelPanelClass() {
        FrameCalibrator(new LevelPanel());
    }

    public static void DeathPanelClass(int CurrentDeaths, int Score, boolean PlayerUltimate) {
        FrameCalibrator(new DeathPanel(Score, CurrentDeaths, PlayerUltimate));
    }

    public static void CongratulationsPanelClass(int NewLevel, int FinalCurrentDeaths, int FinalCurrentScore) {
        FrameCalibrator(new CongratulationsPanel(NewLevel, FinalCurrentDeaths, FinalCurrentScore));
    }

    public static void BinarySearchPanelClass() {  //Done slightly differently for The searching and sorting tabs as otherwise it causes issues
        
        SearchDone = true;
        
        frame.getContentPane().add(SearchIT); //This makes the Projected Panel become the HomePanel
        frame.setContentPane(SearchIT);
        frame.setPreferredSize(new Dimension(1920, 1080));
        frame.setLocationRelativeTo(null); // This sets the JFrame to centralise and makes the middle of it in the middle of the monitor
        frame.setVisible(true);
        
        SearchIT.BinarySearch();

    }

    public static void FinishSearch(String NewUsername, String[][] DetailsArray) {
        System.out.println("platgamenea");
        if(SearchDone == true){
            System.out.println("TrueSearch");
        }else{
            System.out.println("False Search");
        }
        
        SearchIT.StartSearch(NewUsername, DetailsArray);

    }
    
    public static void FinishSort(int LeaderboardSize, String[][] LeaderboardArray){
       
        Retrieve.SortingLeaderboard(LeaderboardSize, LeaderboardArray);
        
        //LeaderboardPanel SortTheLeaderboard = new LeaderboardPanel();
        //SortTheLeaderboard.SortingLeaderboard(LeaderboardSizeInt, UnsortedLeaderboard);
    }

    public static void LeaderboardPanelClass() {


        
        
        frame.getContentPane().add(Retrieve); //This makes the Projected Panel become the HomePanel
        frame.setContentPane(Retrieve);
        frame.setPreferredSize(new Dimension(1920, 1080));
        frame.setLocationRelativeTo(null); // This sets the JFrame to centralise and makes the middle of it in the middle of the monitor
        frame.setVisible(true);

        Retrieve.RetrieveLeaderboard();

    }
}
