
package platformer.game.nea;

import java.awt.event.KeyAdapter; //KeyAdapter is very useful and includes KeyListener
import java.awt.event.KeyEvent;


public class KeyInputs extends KeyAdapter{ 
    
    GamePanel gamepanel;

    
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    
    private static final boolean[] keys = new boolean[256]; // 256 accounts for enough keys 
    
    
    public void keyPressed(KeyEvent e){
        keys[e.getKeyCode()] = true;      //Gets the value of the key that is being pressed
    }
    public void keyReleased(KeyEvent e){
         keys[e.getKeyCode()] = false;     //Gets the value of the key that is being released
    }
    
    public static boolean isKeyActive(int keyCode){  //Each key has a keyCode that is unique to that individual key so you can work out what key is being held down
        return keys[keyCode];
    }
}
