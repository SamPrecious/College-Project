package platformer.game.nea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;

public class GamePanel extends javax.swing.JPanel implements Runnable {

    private Thread thread;
    private boolean GameRunning = false;

    public int lastX;
    public int lastY;
    public boolean QPressed = false;
    public boolean QAllowed = false;
    public boolean TimeFreeze = false;
    public boolean allowJump;
    public boolean allowFall;
    public double height;
    public double width;
    public Rectangle intersectionTop;
    public Rectangle intersectionBottom;
    public Rectangle intersectionLeft;
    public Rectangle intersectionRight;

    private int x = 764;    //These are the X and Y components for the players as they move around
    private int y = 763;
    private int simulatedX = 764;
    private int simulatedY = 763;
    private int Enemy1X = 448;
    private int Enemy1Y = 512;
    private int PosYSpeed = 5;
    private int PosXSpeed = 5;
    private int NegYSpeed = 5;
    private int NegXSpeed = 5;
    private int NormPosYSpeed = 5;    //These are the normal speeds that x and y will be at
    private int NormPosXSpeed = 5;
    private int NormNegYSpeed = 5;
    private int NormNegXSpeed = 5;
    private int i = 0;
    private int j = 0;
    private int gravity = 5;
    private int normGravity = 5;
    private int UpTo43 = 0;
    private int CurrentLevel;
    private double timeLeftInJump = 0;
    private boolean collisionTop = false;
    private boolean collisionBottom = false;
    private boolean collisionLeft = false;
    private boolean collisionRight = false;
    private int NullSpeed = 0;
    Timer timer = new Timer();
    public boolean Enemy1Forwards = true;
    private BufferedImage spriteSheet = null;
    private BufferedImage player;
    private BufferedImage enemy;
    private BufferedImage OWLogo; //This is an easter egg/secret refrence = for level 2 as users wanted that
    private BufferedImage MapTile;
    private BufferedImage FlagTile;
    private int deaths;
    private int Score = 0;
    private int BestScoreCurrent = 0;
    private Rectangle Enemy1Rect;
    private Rectangle OWRect;
    private boolean MovingLeft;
    private boolean MovingRight;
    private boolean OWFound = false;
    SpriteSheet spritesheet;

    public void init() { //init = initialise

        ImageImporter Loader = new ImageImporter();
        try {
            spriteSheet = Loader.loadImage("SpriteSheet512 V3.png");
        } catch (IOException e) {
        }

        spritesheet = new SpriteSheet(spriteSheet);

        MapTile = spritesheet.getImage(0, 64, 64, 64);
        OWLogo = spritesheet.getImage(193, 0, 65, 64);
        enemy = spritesheet.getImage(0, 129, 64, 64);     //The First number is the starting X Coordinate, the 2nd number is the starting Y Coordinate, the 3rd is the amount of X to go along and the 4th is the amount of Y to go along
        player = spritesheet.getImage(0, 0, 64, 64);     //The First number is the starting X Coordinate, the 2nd number is the starting Y Coordinate, the 3rd is the amount of X to go along and the 4th is the amount of Y to go along
        FlagTile = spritesheet.getImage(68, 68, 60, 60);

    }

    public GamePanel() {
        addKeyListener(new KeyInputs());

    }

    public synchronized void start(int Level, int Score, int Deaths, boolean Ultimate) {
        
        if(Level <= 1){
            Level = 1;
        }else{
            Level = 2;
        }
        System.out.println("LEVEL SHOULD BE: " + Level);
        System.out.println("SCORE SHOULD BE: " + Score);
        CurrentLevel = Level;
        BestScoreCurrent = Score;
        
        
        QAllowed = Ultimate; //Sets whether the player has access to the Ultimate
        CurrentLevel = Level;
        BestScoreCurrent = Score;
        deaths = Deaths;
        
        System.out.println("CurrentDeaths: " + deaths);
        if (GameRunning) {
            return;    // Stops starting if the game is already running
        }
        init();
        initComponents();

        TimerClass TimerThread = new TimerClass();
        TimerThread.start();

        System.out.println("Game Running");
        thread = new Thread((Runnable) this);
        thread.start();

        GameRunning = true;  //This declatares the game is running
        //timer.scheduleAtFixedRate(ScoreTimer(), 10, 1000);

    }

    public void ScoreTimerCalculator() {

    }

    public synchronized void stop() {
        if (!GameRunning) {
            return; //Stops stopping if the game is already stopped
        }
        try {
            thread.join();
            GameRunning = false; //This stops the game/thread from running
        } catch (Exception e) {
            e.printStackTrace(); //Catches any errors and explains them.
        }
    }

    public void run() {

        this.requestFocus();                           //SOURCE: https://gamedev.stackexchange.com/questions/52841/the-most-efficient-and-accurate-game-loop - I used this source to learn how to do an efficient, good game loop.
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int fps = 0;

        while (GameRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = System.nanoTime();

            while (delta >= 1) {
                update();
                delta--;
            }
            render();
            fps++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + fps);
                fps = 0;

            }
        }
        stop();
    }

    public static void main() {
    }

    public void map(Graphics g) {

        setIgnoreRepaint(true);
    }

    public void paint(Graphics g) {

        g.setColor(Color.BLUE); //sets the colour of the background to black
        g.fillRect(0, 0, getWidth(), getHeight());

        /*
        g.setColor(Color.RED);
        g.fillRect(simulatedX, simulatedY, 64, 1);             //This predicts where the player will be. The player would teleport inside blocks without simulating the X and Y values as teleporting the player out of a block is worse than making it never happen at all
        g.fillRect(simulatedX, simulatedY + 64, 64, 1);
        g.fillRect(simulatedX, simulatedY, 1, 64);
        g.fillRect(simulatedX + 64, simulatedY, 1, 64);
         */
        //g.fillRect(Enemy1X, Enemy1Y, 64, 64);
        //System.out.println("Width: "+ getWidth());       1280
        //System.out.println("Height: "+ getHeight());     720
        //g.setColor(Color.RED);       //this is the player model that is printed
        //g.fillRect(x, y, 40, 40); 
        g.drawImage(player, x, y, this);  //This draws the player using the part of my spritesheet with the player icon on it as specified in init

        if (CurrentLevel > 1) {      //If the current level is more than 1 enemies will appear as they are hidden until level 1
            g.drawImage(enemy, Enemy1X, Enemy1Y, this);
            g.drawImage(OWLogo, 64, 384, this);
        }

        //This is the initial map that the player starts on.
        int i = 0;
        while (i != 1920) {
            g.drawImage(MapTile, i, 960, this); //936 + 24 to each
            g.drawImage(MapTile, i, 0, this);
            i = i + 64;
        }
        int j = 64;
        while (j != 1024) {                            //999 Original
            g.drawImage(MapTile, 0, j, this);
            g.drawImage(MapTile, 1856, j, this);
            j = j + 64;
        }

        //These are the various obstacles for the map
        g.drawImage(MapTile, 1792, 831, this);        //These lines are jumpTile1
        g.drawImage(MapTile, 1728, 831, this);

        g.drawImage(MapTile, 1408, 767, this);        //These lines are jumpTile2
        g.drawImage(MapTile, 1344, 767, this);
        g.drawImage(MapTile, 1280, 767, this);

        g.drawImage(MapTile, 960, 703, this);        //These lines are jumpTile3
        g.drawImage(MapTile, 896, 703, this);
        g.drawImage(MapTile, 832, 703, this);

        g.drawImage(MapTile, 576, 575, this);        //These lines are jumpTile4
        g.drawImage(MapTile, 512, 575, this);
        g.drawImage(MapTile, 448, 575, this);

        g.drawImage(MapTile, 192, 447, this);        //These lines are jumpTile5     
        g.drawImage(MapTile, 128, 447, this);
        g.drawImage(MapTile, 64, 447, this);

        g.drawImage(MapTile, 448, 319, this);        //These lines are jumpTile6  
        g.drawImage(MapTile, 512, 319, this);
        g.drawImage(MapTile, 576, 319, this);

        g.drawImage(MapTile, 768, 447, this);        //This is jumpTile7     

        g.drawImage(MapTile, 960, 447, this);        //This is jumpTile8     

        g.drawImage(MapTile, 1216, 319, this);       //These are jumpTile9   
        g.drawImage(MapTile, 1280, 319, this);

        g.drawImage(MapTile, 1472, 511, this);       //This is jumpTile10

        g.drawImage(MapTile, 1664, 383, this);       //These are jumpTile11
        g.drawImage(MapTile, 1728, 383, this);
        g.drawImage(MapTile, 1792, 383, this);

        g.drawImage(MapTile, 1600, 255, this);       //These are jumpTile12
        g.drawImage(MapTile, 1664, 255, this);

        g.drawImage(MapTile, 1600, 191, this);       //This is jumpTile13

        g.drawImage(MapTile, 1408, 191, this);       //This is jumpTile14
        g.drawImage(MapTile, 1472, 191, this);
        g.drawImage(MapTile, 1536, 191, this);

        g.drawImage(MapTile, 1408, 127, this);       //This is jumpTile15
        g.drawImage(MapTile, 1408, 63, this);

        g.drawImage(FlagTile, 1476, 131, this);     //This is the goal that the player wants to get to

        /*        if (allowJump = false) {
            NegYSpeed = gravity;            
        }*/
        setIgnoreRepaint(true);     //ignores any autorepaints that are done by the system and instead only repaints when called in the render method

    }

    public void collision() {  //In this method rectangles are created around the players and objects of the map to detect collisions within them and sort out any issues that may be caused

        Rectangle jumpTile1 = new Rectangle(1728, 831, 128, 64);
        Rectangle jumpTile2 = new Rectangle(1280, 767, 192, 64);
        Rectangle jumpTile3 = new Rectangle(832, 703, 192, 64);
        Rectangle jumpTile4 = new Rectangle(448, 575, 192, 64);
        Rectangle jumpTile5 = new Rectangle(64, 447, 192, 64);
        Rectangle jumpTile6 = new Rectangle(448, 319, 192, 64);
        Rectangle jumpTile7 = new Rectangle(768, 447, 64, 64);
        Rectangle jumpTile8 = new Rectangle(960, 447, 64, 64);
        Rectangle jumpTile9 = new Rectangle(1216, 319, 128, 64);
        Rectangle jumpTile10 = new Rectangle(1472, 511, 64, 64);
        Rectangle jumpTile11 = new Rectangle(1664, 383, 192, 64);
        Rectangle jumpTile12 = new Rectangle(1600, 255, 128, 64);
        Rectangle jumpTile13 = new Rectangle(1600, 191, 64, 64);
        Rectangle jumpTile14 = new Rectangle(1408, 191, 192, 64);
        Rectangle jumpTile15 = new Rectangle(1408, 63, 64, 128);

        //Invisible Rectangles Created Here
        Rectangle playerRectTop = new Rectangle(simulatedX + PosXSpeed, simulatedY, 64 - PosXSpeed - PosXSpeed, 1);             //This predicts where the player will be. The player would teleport inside blocks without simulating the X and Y values as teleporting the player out of a block is worse than making it never happen at all
        Rectangle playerRectBottom = new Rectangle(simulatedX + PosXSpeed, simulatedY + 64, 64 - PosXSpeed - PosXSpeed, 1);
        Rectangle playerRectLeft = new Rectangle(simulatedX, simulatedY + PosYSpeed, 1, 64 - PosYSpeed - PosYSpeed);
        Rectangle playerRectRight = new Rectangle(simulatedX + 64, simulatedY + PosYSpeed, 1, 64 - PosYSpeed - PosYSpeed);

        if (CurrentLevel > 1) {
            Enemy1Rect = new Rectangle(Enemy1X, Enemy1Y, 64, 64);
            OWRect = new Rectangle(64, 384, 64, 64);  //Gives collision to the Secret
        }

        Rectangle FlagTileRect = new Rectangle(1476, 131, 60, 60);

        /*
        Rectangle playerRectTop = new Rectangle(x, y, 64, 1);       
        Rectangle playerRectBottom = new Rectangle(x, y + 64, 64, 1);
        Rectangle playerRectLeft = new Rectangle(x, y + 10, 1, 44);       //The Reason that 10 has been added onto x and 20 has been removed from y is for some leway. If the max player speed is 10 pixels then this value means thats how far that the player can intercept the block without getting stuck. The 20 compensates -10 for the normal compensation and an extra -10 to balance the +10 in the beginning.
        Rectangle playerRectRight = new Rectangle(x + 64, y + 10, 1, 44);
         */
        Rectangle flooringRect = new Rectangle(0, 960, 1920, 64);             //Rectangle(int x, int y, int width, int height)
        Rectangle ceilingRect = new Rectangle(0, 0, 1920, 64);
        Rectangle LeftWallRect = new Rectangle(0, 0, 64, 1080);
        Rectangle RightWallRect = new Rectangle(1856, 0, 64, 1080);

        Rectangle theInescapableFloor = new Rectangle(0, 940, 1290, 64);

        /*
        FORMULAa
        Bottom of the panel has collided with the top of the flooring
        Bottom of panel coords are Y + 128 and [X to [X + 128]] 
        Top of flooring coords are FlooringY and [FlooringX to [FlooringX + Flooring Length]] (Varies depending on flooring sizes)
        
        The bottom has hit the top if:        (Y + 128 + 1) = FlooringY && flooringRect intersects   The + 1 is to indicate how its slightly pushing into it.
         */
        //Unfortunately I had to make many IF statements to determine what exact rectangles are being intersected with as there was no function to tell me what rectangles are overlapping with one
//        if (flooringRect.intersects(playerRectTop) || flooringRect.intersects(playerRectBottom) || flooringRect.intersects(playerRectLeft) || flooringRect.intersects(playerRectRight)) {
//            intersectionTop = flooringRect.intersection(playerRectTop);
//            intersectionBottom = flooringRect.intersection(playerRectBottom);
//            intersectionLeft = flooringRect.intersection(playerRectLeft);
//            intersectionRight = flooringRect.intersection(playerRectRight);
//        }else if (jumpTile1.intersects(playerRectTop) || jumpTile1.intersects(playerRectBottom) || jumpTile1.intersects(playerRectLeft) || jumpTile1.intersects(playerRectRight)) {
//            intersectionTop = jumpTile1.intersection(playerRectTop);
//            intersectionBottom = jumpTile1.intersection(playerRectBottom);
//            intersectionLeft = jumpTile1.intersection(playerRectLeft);
//            intersectionRight = jumpTile1.intersection(playerRectRight);
//        }else if (jumpTile2.intersects(playerRectTop) || jumpTile2.intersects(playerRectBottom) || jumpTile2.intersects(playerRectLeft) || jumpTile2.intersects(playerRectRight)) {
//            intersectionTop = jumpTile2.intersection(playerRectTop);
//            intersectionBottom = jumpTile2.intersection(playerRectBottom);
//            intersectionLeft = jumpTile2.intersection(playerRectLeft);
//            intersectionRight = jumpTile2.intersection(playerRectRight);
//        }else if (jumpTile3.intersects(playerRectTop) || jumpTile3.intersects(playerRectBottom) || jumpTile3.intersects(playerRectLeft) || jumpTile3.intersects(playerRectRight)) {
//            intersectionTop = jumpTile3.intersection(playerRectTop);
//            intersectionBottom = jumpTile3.intersection(playerRectBottom);
//            intersectionLeft = jumpTile3.intersection(playerRectLeft);
//            intersectionRight = jumpTile3.intersection(playerRectRight);
//        }else if (jumpTile4.intersects(playerRectTop) || jumpTile4.intersects(playerRectBottom) || jumpTile4.intersects(playerRectLeft) || jumpTile4.intersects(playerRectRight)) {
//            intersectionTop = jumpTile4.intersection(playerRectTop);
//            intersectionBottom = jumpTile4.intersection(playerRectBottom);
//            intersectionLeft = jumpTile4.intersection(playerRectLeft);
//            intersectionRight = jumpTile4.intersection(playerRectRight);
//        }else if (jumpTile5.intersects(playerRectTop) || jumpTile5.intersects(playerRectBottom) || jumpTile5.intersects(playerRectLeft) || jumpTile5.intersects(playerRectRight)) {
//            intersectionTop = jumpTile5.intersection(playerRectTop);
//            intersectionBottom = jumpTile5.intersection(playerRectBottom);
//            intersectionLeft = jumpTile5.intersection(playerRectLeft);
//            intersectionRight = jumpTile5.intersection(playerRectRight);
//        }else if (jumpTile6.intersects(playerRectTop) || jumpTile6.intersects(playerRectBottom) || jumpTile6.intersects(playerRectLeft) || jumpTile6.intersects(playerRectRight)) {
//            intersectionTop = jumpTile6.intersection(playerRectTop);
//            intersectionBottom = jumpTile6.intersection(playerRectBottom);
//            intersectionLeft = jumpTile6.intersection(playerRectLeft);
//            intersectionRight = jumpTile6.intersection(playerRectRight);
//        }else if (jumpTile7.intersects(playerRectTop) || jumpTile7.intersects(playerRectBottom) || jumpTile7.intersects(playerRectLeft) || jumpTile7.intersects(playerRectRight)) {
//            intersectionTop = jumpTile7.intersection(playerRectTop);
//            intersectionBottom = jumpTile7.intersection(playerRectBottom);
//            intersectionLeft = jumpTile7.intersection(playerRectLeft);
//            intersectionRight = jumpTile7.intersection(playerRectRight);
//        }else if (jumpTile8.intersects(playerRectTop) || jumpTile8.intersects(playerRectBottom) || jumpTile8.intersects(playerRectLeft) || jumpTile8.intersects(playerRectRight)) {
//            intersectionTop = jumpTile8.intersection(playerRectTop);
//            intersectionBottom = jumpTile8.intersection(playerRectBottom);
//            intersectionLeft = jumpTile8.intersection(playerRectLeft);
//            intersectionRight = jumpTile8.intersection(playerRectRight);
//        }else if (jumpTile9.intersects(playerRectTop) || jumpTile9.intersects(playerRectBottom) || jumpTile9.intersects(playerRectLeft) || jumpTile9.intersects(playerRectRight)) {
//            intersectionTop = jumpTile9.intersection(playerRectTop);
//            intersectionBottom = jumpTile9.intersection(playerRectBottom);
//            intersectionLeft = jumpTile9.intersection(playerRectLeft);
//            intersectionRight = jumpTile9.intersection(playerRectRight);
//        }else if (jumpTile10.intersects(playerRectTop) || jumpTile10.intersects(playerRectBottom) || jumpTile10.intersects(playerRectLeft) || jumpTile10.intersects(playerRectRight)) {
//            intersectionTop = jumpTile10.intersection(playerRectTop);
//            intersectionBottom = jumpTile10.intersection(playerRectBottom);
//            intersectionLeft = jumpTile10.intersection(playerRectLeft);
//            intersectionRight = jumpTile10.intersection(playerRectRight);
//        }else if (jumpTile11.intersects(playerRectTop) || jumpTile11.intersects(playerRectBottom) || jumpTile11.intersects(playerRectLeft) || jumpTile11.intersects(playerRectRight)) {
//            intersectionTop = jumpTile11.intersection(playerRectTop);
//            intersectionBottom = jumpTile11.intersection(playerRectBottom);
//            intersectionLeft = jumpTile11.intersection(playerRectLeft);
//            intersectionRight = jumpTile11.intersection(playerRectRight);
//        }else if (jumpTile12.intersects(playerRectTop) || jumpTile12.intersects(playerRectBottom) || jumpTile12.intersects(playerRectLeft) || jumpTile12.intersects(playerRectRight)) {
//            intersectionTop = jumpTile12.intersection(playerRectTop);
//            intersectionBottom = jumpTile12.intersection(playerRectBottom);
//            intersectionLeft = jumpTile12.intersection(playerRectLeft);
//            intersectionRight = jumpTile12.intersection(playerRectRight);
//        }else if (jumpTile13.intersects(playerRectTop) || jumpTile13.intersects(playerRectBottom) || jumpTile13.intersects(playerRectLeft) || jumpTile13.intersects(playerRectRight)) {
//            intersectionTop = jumpTile13.intersection(playerRectTop);
//            intersectionBottom = jumpTile13.intersection(playerRectBottom);
//            intersectionLeft = jumpTile13.intersection(playerRectLeft);
//            intersectionRight = jumpTile13.intersection(playerRectRight);
//        }else if (jumpTile14.intersects(playerRectTop) || jumpTile14.intersects(playerRectBottom) || jumpTile14.intersects(playerRectLeft) || jumpTile14.intersects(playerRectRight)) {
//            intersectionTop = jumpTile14.intersection(playerRectTop);
//            intersectionBottom = jumpTile14.intersection(playerRectBottom);
//            intersectionLeft = jumpTile14.intersection(playerRectLeft);
//            intersectionRight = jumpTile14.intersection(playerRectRight);
//        }else if (jumpTile15.intersects(playerRectTop) || jumpTile15.intersects(playerRectBottom) || jumpTile15.intersects(playerRectLeft) || jumpTile15.intersects(playerRectRight)) {
//            intersectionTop = jumpTile15.intersection(playerRectTop);
//            intersectionBottom = jumpTile15.intersection(playerRectBottom);
//            intersectionLeft = jumpTile15.intersection(playerRectLeft);
//            intersectionRight = jumpTile15.intersection(playerRectRight);
//        }else{
//            
//            intersectionTop = new Rectangle(0, 0, 0, 0);
//            intersectionBottom = new Rectangle(0, 0, 0, 0);
//            intersectionLeft = new Rectangle(0, 0, 0, 0);
//            intersectionRight = new Rectangle(0, 0, 0, 0);
//        }
        //The intersection may be overriden multiple times however 
        if (flooringRect.intersects(playerRectBottom) || ceilingRect.intersects(playerRectBottom)
                || LeftWallRect.intersects(playerRectBottom) || RightWallRect.intersects(playerRectBottom)
                || jumpTile1.intersects(playerRectBottom) || jumpTile2.intersects(playerRectBottom)
                || jumpTile3.intersects(playerRectBottom) || jumpTile4.intersects(playerRectBottom)
                || jumpTile5.intersects(playerRectBottom) || jumpTile6.intersects(playerRectBottom)
                || jumpTile7.intersects(playerRectBottom) || jumpTile8.intersects(playerRectBottom)
                || jumpTile9.intersects(playerRectBottom) || jumpTile10.intersects(playerRectBottom)
                || jumpTile11.intersects(playerRectBottom) || jumpTile12.intersects(playerRectBottom)
                || jumpTile13.intersects(playerRectBottom) || jumpTile14.intersects(playerRectBottom)
                || jumpTile15.intersects(playerRectBottom)) {
            //System.out.println("COLLISION ON THE BOTTOM");

            allowJump = true;
            collisionBottom = true;
            //height = intersectionBottom.getHeight() - 1;
            //simulatedY -= (PosYSpeed);
            //PosYSpeed = 0;
            //System.out.println("Player bottom intersection is now: "+ height + " " + simulatedY);
            //public Rectangle intersectionTop;
        } else {
            //PosYSpeed = gravity;
            //allowJump = false;
            //System.out.println("NO COLLISION ON THE BOTTOM");
            collisionBottom = false;

        }

        if (flooringRect.intersects(playerRectTop) || ceilingRect.intersects(playerRectTop)
                || LeftWallRect.intersects(playerRectTop) || RightWallRect.intersects(playerRectTop)
                || jumpTile1.intersects(playerRectTop) || jumpTile2.intersects(playerRectTop)
                || jumpTile3.intersects(playerRectTop) || jumpTile4.intersects(playerRectTop)
                || jumpTile5.intersects(playerRectTop) || jumpTile6.intersects(playerRectTop)
                || jumpTile7.intersects(playerRectTop) || jumpTile8.intersects(playerRectTop)
                || jumpTile9.intersects(playerRectTop) || jumpTile10.intersects(playerRectTop)
                || jumpTile11.intersects(playerRectTop) || jumpTile12.intersects(playerRectTop)
                || jumpTile13.intersects(playerRectTop) || jumpTile14.intersects(playerRectTop)
                || jumpTile15.intersects(playerRectTop)) {
            //System.out.println("COLLISION IN THE TOP");

            collisionTop = true;

            //height = intersectionTop.getHeight() - 1;
            //simulatedY -= (NegYSpeed);
            //NegYSpeed = 0;
            //allowJump = false;
        } else {
            collisionTop = false;
            //allowJump = true;
            //NegYSpeed = 5;
        }

        if (flooringRect.intersects(playerRectRight) || ceilingRect.intersects(playerRectRight)
                || LeftWallRect.intersects(playerRectRight) || RightWallRect.intersects(playerRectRight)
                || jumpTile1.intersects(playerRectRight) || jumpTile2.intersects(playerRectRight)
                || jumpTile3.intersects(playerRectRight) || jumpTile4.intersects(playerRectRight)
                || jumpTile5.intersects(playerRectRight) || jumpTile6.intersects(playerRectRight)
                || jumpTile7.intersects(playerRectRight) || jumpTile8.intersects(playerRectRight)
                || jumpTile9.intersects(playerRectRight) || jumpTile10.intersects(playerRectRight)
                || jumpTile11.intersects(playerRectRight) || jumpTile12.intersects(playerRectRight)
                || jumpTile13.intersects(playerRectRight) || jumpTile14.intersects(playerRectRight)
                || jumpTile15.intersects(playerRectRight)) {
            //System.out.println("COLLISION IN THE LEFT");

            collisionRight = true;
            // allowJump = true;
            //allowJump = false;
            //width = intersectionRight.getWidth();
            //simulatedX -= PosXSpeed;
            //PosXSpeed = 0;
        } else {
            //PosXSpeed = 5;
            collisionRight = false;
        }

        if (flooringRect.intersects(playerRectLeft) || ceilingRect.intersects(playerRectLeft)
                || LeftWallRect.intersects(playerRectLeft) || RightWallRect.intersects(playerRectLeft)
                || jumpTile1.intersects(playerRectLeft) || jumpTile2.intersects(playerRectLeft)
                || jumpTile3.intersects(playerRectLeft) || jumpTile4.intersects(playerRectLeft)
                || jumpTile5.intersects(playerRectLeft) || jumpTile6.intersects(playerRectLeft)
                || jumpTile7.intersects(playerRectLeft) || jumpTile8.intersects(playerRectLeft)
                || jumpTile9.intersects(playerRectLeft) || jumpTile10.intersects(playerRectLeft)
                || jumpTile11.intersects(playerRectLeft) || jumpTile12.intersects(playerRectLeft)
                || jumpTile13.intersects(playerRectLeft) || jumpTile14.intersects(playerRectLeft)
                || jumpTile15.intersects(playerRectLeft)) {
            //System.out.println("COLLISION IN THE RIGHT");

            collisionLeft = true;
            // allowJump = true;
            //allowJump = false;
            //width = intersectionLeft.getWidth();
            //simulatedX += NegXSpeed;
            //NegXSpeed = 0;

        } else {
            //NegXSpeed = 5;
            collisionLeft = false;

        }

        //if (simulatedY >= 940) {  //This line of code is here so that if anyone somehow bugs the game and glitches through the floor they will be put back above it. The only reason I have not done this with other sides is because of escape routes that may be put in to breach the map revealing secrets.
        //    simulatedY = 876;
        //}
        x = simulatedX;
        y = simulatedY;
        if (CurrentLevel > 1) {
            if (Enemy1Rect.intersects(playerRectBottom) || Enemy1Rect.intersects(playerRectTop) || Enemy1Rect.intersects(playerRectLeft) || Enemy1Rect.intersects(playerRectRight)) {
                System.out.println("DEAD!");
                endGame("Dead");
            }
            if (OWRect.intersects(playerRectBottom) || OWRect.intersects(playerRectTop) || OWRect.intersects(playerRectLeft) || OWRect.intersects(playerRectRight) && OWFound == false) {
                System.out.println("SECRET ABILITY DISCOVERED: TIMESTOP. PRESS Q TO ACTIVATE! ");
                OWFound = true;

                String[] NullArray = new String[0];
                ClientServerConnection NewAbility = new ClientServerConnection();
                NewAbility.SendInputs("Ultimate", NullArray);
            }
        }

        if (FlagTileRect.intersects(playerRectBottom) || FlagTileRect.intersects(playerRectTop) || FlagTileRect.intersects(playerRectLeft) || FlagTileRect.intersects(playerRectRight)) {
            System.out.println("Well Done! ");
            endGame("Congratulations");
        }

    }

//    public TimerTask ScoreTimer(){
//        TimerTask ScoreTimer = new TimerTask(){
//            @Override
//            public void run(){
//               w
//                System.out.println(ScoreTime);
//                ScoreTime--;
//            }
//        };
//      return ScoreTimer;  
//    }
    public void falling() {
        /*
        if(allowJump != true){
            y += gravity;
        }
         */
    }

    private void update() {
        collision();  //This checks for collision and changes the x and y speeds respectively.
        //jumping();    //This checks to see if a player presses the jump button and subsequently does the action for jumping if the player initates it

        if (allowJump == true && KeyInputs.isKeyActive(KeyEvent.VK_W) && !collisionTop) {
            simulatedY -= 11;
            timeLeftInJump = 10.5;
            //System.out.println("F" + timeLeftInJump);
        }
        if (timeLeftInJump <= 11 && timeLeftInJump >= 0.5 && !collisionTop) {
            allowJump = false;
            //System.out.println("S" + timeLeftInJump);
            simulatedY -= timeLeftInJump;
            timeLeftInJump = timeLeftInJump - 0.45;
        } else {
            timeLeftInJump = 0;
        }
        if (timeLeftInJump == 0 && (!collisionBottom)) {
            NullSpeed = gravity;
            simulatedY += PosYSpeed;
        }
        //System.out.println(timeLeftInJump);
        //With 0 elevation this jumping method allows the block to move around 4 blocks away and it goes 120 pixels high
        if (KeyInputs.isKeyActive(KeyEvent.VK_D) && !collisionRight) {
            simulatedX += PosXSpeed;
            MovingRight = true;
            player = spritesheet.getImage(128, 0, 64, 64);     //The First number is the starting X Coordinate, the 2nd number is the starting Y Coordinate, the 3rd is the amount of X to go along and the 4th is the amount of Y to go along

        } else {
            MovingRight = false;
        }
        if (KeyInputs.isKeyActive(KeyEvent.VK_A) && !collisionLeft) {
            simulatedX -= NegXSpeed;
            MovingLeft = true;
            player = spritesheet.getImage(64, 0, 64, 64);     //The First number is the starting X Coordinate, the 2nd number is the starting Y Coordinate, the 3rd is the amount of X to go along and the 4th is the amount of Y to go along
        } else {
            MovingLeft = false;
        }
        /*
        if (KeyInputs.isKeyActive(KeyEvent.VK_Q) && !QPressed){
            QPressed = true;
            String[] NullArray = new String[0];
            
            ClientServerConnection CheckAbility = new ClientServerConnection();
            CheckAbility.SendInputs("CheckUltimate", NullArray);
            
            System.out.println("Zip Zap Time is SLOW");
        }
         */
        if (KeyInputs.isKeyActive(KeyEvent.VK_Q) && QAllowed) {
            TimeFreeze = true;  //When active the enemies (the only other moving terrain) are frozen in time. Only works while holding it which makes jumping harder as a trade off.
        } else {
            TimeFreeze = false;
        }
        if (KeyInputs.isKeyActive(KeyEvent.VK_Q) && !QAllowed){
            System.out.println("You Dont Have Access to this ability");
        }

        if (MovingLeft == false && MovingRight == false) {
            player = spritesheet.getImage(0, 0, 64, 64);
        }
        if (UpTo43 == 43) {
            UpTo43 = 0;
            if (Enemy1Forwards == true) {
                Enemy1Forwards = false;
            } else {
                Enemy1Forwards = true;
            }
        }
        if (CurrentLevel > 1 && UpTo43 < 43 && Enemy1Forwards == true && !TimeFreeze) {
            Enemy1X = Enemy1X + 3;
            UpTo43 = UpTo43 + 1;
        }
        if (CurrentLevel > 1 && UpTo43 < 43 && Enemy1Forwards == false && !TimeFreeze) { //If timefreeze is active the enemy wont move
            Enemy1X = Enemy1X - 3;
            UpTo43 = UpTo43 + 1;
        }
        if (TimeFreeze) {
            System.out.println("Zip Zap Time Is Paused");
        }

        //if(KeyInputs.wasActive(KeyEvent.VK_SPACE))
        //y = 1080;
    }

    public void ReturnScore(int NewScore) {
        Score = NewScore;
        System.out.println("returnedScore: " + Score);
    }

    private void endGame(String state) {
        GameRunning = false;

        if (state.equals("Dead")) {
            deaths = deaths + 1;
            System.out.println("You have died: " + deaths + " times.");
            PlatformerGameNEA PlayerIsDead = new PlatformerGameNEA();
            PlayerIsDead.DeathPanelClass(deaths, BestScoreCurrent, QAllowed);
        } else if (state.equals("Congratulations")) {

            System.out.println("So you've won!");

            TimerClass RetrieveScore = new TimerClass();
            Score = RetrieveScore.ReturnScore();

            System.out.println("CurrentScore: " + Score);
            if (BestScoreCurrent < Score) {
                BestScoreCurrent = Score;   //Updates if they beat their original best score
            }//Score only changes when the player wins. You dont take the a different score from the player if they sd
            System.out.println("Your best score is: " + BestScoreCurrent);
            PlatformerGameNEA PlayerWon = new PlatformerGameNEA();
            System.out.println("RETURNING SCORE: " + BestScoreCurrent);
            PlayerWon.CongratulationsPanelClass(CurrentLevel, BestScoreCurrent, deaths);
        } else {
            System.out.println("Invalid Game Finish Method");
        }

    }

    private void render() {
        repaint();  //Repaint repaints the whole scene so if something gets added or removed repaint will make sure to notice this change
        // init();
        //  initComponents(); //Every time the game loop goes through the render command the initialise routine is done

    }

    private void textBoxes() {
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(21, 27, 31));

        jLabel1.setText("JAYLABEL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(589, 589, 589)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(360, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(303, 303, 303)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(234, Short.MAX_VALUE))
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(21, 27, 31));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    private javax.swing.JLabel jLabel1;

}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

