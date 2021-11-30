package platformer.game.nea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientServerConnection {  //IP: 192.168.1.200

    private static Socket Sock;
    private static OutputStream Output;
    private static InputStream Input;
    private static String user;
    private static boolean Host = false;
    private static InetAddress newIp;
    private static String ip;
    private static BufferedWriter BuffWriter;
    private static int hashedString = 0;
    private static boolean waiting;
    public static String PlayerID;
    public static String AccessLevel;
    public static boolean Level2;
    private String CurrentLevel;
    private String[][] UNameArray;
    private String UNameToSave;
    private int CurrentLevelInt;
    private int PlayerScoreInt;
    private int PlayerDeathsInt;
    public String NewCurrentLevel;

    public static void InitialiseConnection() {

        while (Host == false) {
            System.out.println("Please Input The Server IP Address: ");
            Scanner input = new Scanner(System.in);
            ip = input.next();               //User inputs the IP address recieved from the Server    
            try {
                newIp = InetAddress.getByName(ip);      //The program takes the string and converts it to an InetAddress datatype
                Sock = new Socket(newIp, 8353);         //Connects to the IP address on the port stated and its socket
                Output = Sock.getOutputStream();
                BuffWriter = new BufferedWriter(new OutputStreamWriter(Output));  //This writes the text from the output stream (charcter to byte stream link) into a character output stream so it can be sent over a socket.
                Host = true;

            } catch (UnknownHostException e) {

            } catch (IOException b) {

            }
        }

        //catch (IOException ex) { //THEN READ THIS AT THE SERVER (INPUT.READ) and reconvert back to string, use SQL to check if it exists, return if it does (e.g. 1 or 0)
    }

    public static void Hash(String UnhashedPassword) {
        char currentLetter;                 //CHANGE NEA DOC TO CHAR, NOT STRING
        int AsciiTotal = 0;
        int PassLeng = UnhashedPassword.length();
        int currentLetterAscii;
        int hashedLetter;
        hashedString = 0;

        while (PassLeng != 0) {                                //Loops until every letter has been covered and the password can be hashed
            PassLeng--;
            currentLetter = UnhashedPassword.charAt(PassLeng);      //Gets the current letter of the string to be converted (Starting from the back)
            currentLetterAscii = (int) currentLetter;
            hashedLetter = currentLetterAscii % 10;           //Gets the remainder of the currentLetters ascii value when divided by 10 for randomness.
            hashedString = hashedLetter + hashedString;     //Adds each hashed ascii value together until the loop ends and the whole passwords hashvalues have been added.

        }

    }

    public void SendInputs(String type, String[] UserDetails) {
        try {

            if (type.equals("Register") || type.equals("Login")) {  //Breaks into a hashing algorithm, the password is always purposefully placed in the same space of any array so it can be hashed BEFORE being sent over the server. This avoids anyone getting the password if they manage to view the datastream.
                Hash(UserDetails[1]);   //The password is always sent in the 2nd part of the array (for register and login) so it always sends the password to the method to be hashed

                String hashedPassword = Integer.toString(hashedString); //Changes the hashed value to a string so it can sit in the array
                UserDetails[1] = hashedPassword;
                System.out.println("HashedPassword: " + hashedPassword);

                BuffWriter.write(type);        //Puts type in the buffered writer, ready to send 
                BuffWriter.newLine();
                BuffWriter.flush();            //Pushes data down the stream
                int Leng = UserDetails.length;
                int i = 0;
                while (i < Leng) {  //Loops until every value in UserDetails has been pushed down the data stream
                    BuffWriter.write(UserDetails[i]);
                    BuffWriter.newLine();
                    BuffWriter.flush();
                    i++;

                }
            }

            if (type.equals("UpdateUser")) {

                BuffWriter.write(type);        //Puts type in the buffered writer, ready to send 
                BuffWriter.newLine();
                BuffWriter.flush();

                BuffWriter.write(PlayerID);
                BuffWriter.newLine();
                BuffWriter.flush();

                BuffWriter.write(UserDetails[0]);   //Sends current level 
                BuffWriter.newLine();
                BuffWriter.flush();

                BuffWriter.write(UserDetails[1]);   //Sends HighScore
                BuffWriter.newLine();
                BuffWriter.flush();

                BuffWriter.write(UserDetails[2]);   //Sends amount of times died    
                BuffWriter.newLine();
                BuffWriter.flush();

            }
            if (type.equals("LevelSelection")) {

                BuffWriter.write("LevelSelection");        //Puts type in the buffered writer, ready to send 
                BuffWriter.newLine();
                BuffWriter.flush();

                BuffWriter.write(PlayerID);        //This will have already been selected from previous screens so now it can be sent to the server and the amount of levels done should correspond with it. I only have 2 levels at the moment so you only need 1 or more levels completed for access to either level
                BuffWriter.newLine();
                BuffWriter.flush();
                
                NewCurrentLevel = UserDetails[0];
                BuffWriter.write(UserDetails[0]);
                BuffWriter.newLine();
                BuffWriter.flush();

            }
            if (type.equals("RetrieveLeaderboard")) {
                System.out.println("Retrieving Leaderboard");
                BuffWriter.write(type);
                BuffWriter.newLine();
                BuffWriter.flush();

            }
            if (type.equals("RetrieveSearch")) {
                System.out.println("Retrieving Search");
                UNameToSave = UserDetails[0];
                BuffWriter.write(type);
                BuffWriter.newLine();
                BuffWriter.flush();
            }

            if (type.equals("Ultimate")) {

                BuffWriter.write(type);
                BuffWriter.newLine();
                BuffWriter.flush();

                BuffWriter.write(PlayerID);
                BuffWriter.newLine();
                BuffWriter.flush();

            }

            if (type.equals("CheckUltimate")) {

                BuffWriter.write(type);
                BuffWriter.newLine();
                BuffWriter.flush();

                BuffWriter.write(PlayerID);
                BuffWriter.newLine();
                BuffWriter.flush();

            }

            if (type.equals("StatisticDetails")) {

                BuffWriter.write(type);
                BuffWriter.newLine();
                BuffWriter.flush();

                BuffWriter.write(PlayerID);
                BuffWriter.newLine();
                BuffWriter.flush();

                CurrentLevel = UserDetails[0];
                System.out.println("CURRENT LEVEL: " + CurrentLevel);
                BuffWriter.write(CurrentLevel);
                BuffWriter.newLine();
                BuffWriter.flush();
            }

            System.out.println("Sent");
            RecieveInputs();
        } catch (UnknownHostException e) {
            System.out.println("Unknown host!");
        } catch (IOException e) {
        }

    }

    public void RecieveInputs() {
        try {
            waiting = true;
            InputStreamReader StreamReader;
            BufferedReader BufferedInput;
            while (waiting) {
                StreamReader = new InputStreamReader(Sock.getInputStream());
                BufferedInput = new BufferedReader(StreamReader);
                if (BufferedInput.ready()) {
                    String input = BufferedInput.readLine();
                    System.out.println(input);

                    if (input.equals("AccessDenied")) {
                        System.out.println("Access Denied");
                        LoginPanel AccessLevel = new LoginPanel();
                        AccessLevel.AccessLevels("Denied");
                        waiting = false;

                    } else if (input.equals("AccessGranted")) {
                        input = BufferedInput.readLine();
                        PlayerID = input;    //This now stores the playerID so the server can be accessed mid program and connect straight to the users files to update them.
                        System.out.println("PlayerID: " + PlayerID);
                        LoginPanel AccessLevel = new LoginPanel();
                        AccessLevel.AccessLevels("Granted");
                        waiting = false;

                    } else if (input.equals("Duplicate")) {
                        System.out.println("There is a glitch in the matrix");
                        waiting = false;
                        RegisterPanel Originality = new RegisterPanel();
                        Originality.OriginalityLevel("Duplicate");

                    } else if (input.equals("NoDuplicate")) {
                        PlayerID = BufferedInput.readLine();
                        System.out.println("Its Free");
                        waiting = false;
                        RegisterPanel Originality = new RegisterPanel();
                        Originality.OriginalityLevel("NoDuplicate");

                    } else if (input.equals("1") || input.equals("2")) {
                        Level2 = true;
                        System.out.println("Level 2");
                        LevelPanel SecondAccess = new LevelPanel();
                        SecondAccess.LevelTwoAccess(Level2);
                        waiting = false;

                    } else if (input.equals("0")) {
                        Level2 = false;
                        LevelPanel SecondAccess = new LevelPanel();
                        SecondAccess.LevelTwoAccess(Level2);
                        waiting = false;

                    } else if (input.equals("Updated")) {
                        waiting = false;
                        System.exit(0);

                    } else if (input.equals("LevelStatistics")) {
                        String PlayerScore = BufferedInput.readLine();
                        String PlayerDeaths = BufferedInput.readLine();
                        System.out.println("Current Score: " + PlayerScore);
                        System.out.println("Current Deaths: " + PlayerDeaths);

                        if (PlayerScore == null || PlayerScore.isEmpty()) {
                            System.out.println("PlayerNULL");
                        }

                        PlayerScoreInt = Integer.parseInt(PlayerScore);
                        PlayerDeathsInt = Integer.parseInt(PlayerDeaths);
                        System.out.println("Ready To Start");

                        String[] NullArray = new String[0];
                        if (CurrentLevel.equals("2")) {
                            CurrentLevelInt = Integer.parseInt(CurrentLevel);

                        }
                        System.out.println("CURRENT LEVEL INT: " + CurrentLevelInt);

                        SendInputs("CheckUltimate", NullArray);

                        waiting = false;

                    } else if (input.equals("UNameSortedArr")) {  //192.168.1.200
                        waiting = false;
                        System.out.println("Recieving Array");
                        String UsernameArrSize = BufferedInput.readLine();
                        int UsernameArrSizeInt = Integer.parseInt(UsernameArrSize);
                        System.out.println("Test1");
                        UNameArray = new String[UsernameArrSizeInt][3];
                        int RemakingArray = 0;
                        System.out.println("Test2");
                        while (RemakingArray < UsernameArrSizeInt) {
                            UNameArray[RemakingArray][0] = BufferedInput.readLine();
                            UNameArray[RemakingArray][1] = BufferedInput.readLine();
                            UNameArray[RemakingArray][2] = BufferedInput.readLine();
                            RemakingArray++;
                        }

                        System.out.println("GO TO PlatformerGameNEA");
                        PlatformerGameNEA StartSearch = new PlatformerGameNEA();
                        StartSearch.FinishSearch(UNameToSave, UNameArray);

                    } else if (input.equals("UnsortedLeaderboard")) {
                        waiting = false;
                        System.out.println("Retrieving Unsorted Leaderboard");

                        String LeaderboardSize = BufferedInput.readLine();
                        int LeaderboardSizeInt = Integer.parseInt(LeaderboardSize);
                        int CurrentRow = 0;

                        System.out.println("Leaderboard Size: " + LeaderboardSizeInt);
                        String[][] UnsortedLeaderboard = new String[LeaderboardSizeInt + 1][2]; //Declares a new UnsortedLeaderboard String with 2 Columns and x amount of rows

                        while (CurrentRow < LeaderboardSizeInt) {
                            UnsortedLeaderboard[CurrentRow][0] = BufferedInput.readLine();
                            UnsortedLeaderboard[CurrentRow][1] = BufferedInput.readLine();
                            CurrentRow++;

                        }
                        System.out.println("Array[0][0] = " + UnsortedLeaderboard[0][0]);
                        System.out.println("Array[0][1] = " + UnsortedLeaderboard[0][1]);
                        System.out.println("Array[1][0] = " + UnsortedLeaderboard[1][0]);
                        System.out.println("Array[1][1] = " + UnsortedLeaderboard[1][1]);

                        PlatformerGameNEA SortThatBoard = new PlatformerGameNEA();
                        SortThatBoard.FinishSort(LeaderboardSizeInt, UnsortedLeaderboard);

                    } else if (input.equals("UltimateUpdated")) {
                        waiting = false;
                    } else if (input.equals("PowerChecked")) {
                        waiting = false;
                        String HasAbility = BufferedInput.readLine();
                        boolean Ultimate;
                        if (HasAbility.equals("Yes")) {
                            System.out.println("Ability Exists");
                            Ultimate = true;
                        } else {
                            Ultimate = false;
                            System.out.println("NO ABILITY FOUND");
                        }  //Sets the game to if the user has the Ultimate Powerup so the game can process accordingly

                        LevelPanel GamePlay = new LevelPanel();
                        System.out.println("Level: " + CurrentLevelInt);
                        System.out.println("Score: " + PlayerScoreInt);
                        System.out.println("Deaths: " + PlayerDeathsInt);
                        GamePlay.StartGame(CurrentLevelInt, PlayerScoreInt, PlayerDeathsInt, Ultimate); //Sends all the required player stats

                    }

                }
            }

        } catch (IOException y) {
            System.out.println("Error: " + y.getMessage());
        }
    }

    public void Retrieve(String UName) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAA");
        BinarySearch SortWUName = new BinarySearch();
        System.out.println(UNameArray.length);
        SortWUName.StartSearch(UName, UNameArray);
    }
}
