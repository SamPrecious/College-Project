package platformer.game.nea;

public class TimerClass extends Thread {

    public static int Score;
    public static boolean TimerRunning = false;

    public void run() {
        try {
             //Counts down the users score from 300 to 0. Pauses 1000 milliseconds (1 second) and then drops the score by 1 before repeating creatinga mock countdown timer
            Score = 300;
            while (Score > 0 && TimerRunning == false) {
                Thread.sleep(1000);
                Score = Score - 1;
                System.out.println(Score);
            }

            System.out.println("Outputting score");
           
            
            GamePanel NewScore = new GamePanel();
            NewScore.ReturnScore(Score);
            
        } catch (Exception e) {

        }
    }

    public int ReturnScore(){
        TimerRunning = true;
        return Score;
        
    }

}
