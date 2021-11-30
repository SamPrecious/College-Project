
package platformer.game.nea;

import java.awt.image.BufferedImage;


public class SpriteSheet {
    
    private BufferedImage image;
    
    public SpriteSheet(BufferedImage image){
        this.image = image;
    }
    
    public BufferedImage getImage(int x, int y, int width, int height){
        
        BufferedImage img = image.getSubimage(x, y, width, height);
        return img;
    }
    
    //BufferedImage img = image.getSubimage((x*32)-32, (y * 32) - 32, width, height);
}
