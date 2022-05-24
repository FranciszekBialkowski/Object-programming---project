package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Coin {

    public BufferedImage image;
    public boolean collision = true;
    public int worldX, worldY, value;
    public Rectangle hitBox = new Rectangle(0,0,24,24);
    public int hitBoxDefaultX = 0;
    public int hitBoxDefaultY = 0;

    public Coin(){

        Random rand = new Random();
        value = rand.nextInt(5)+1;


        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/yellow.jpg")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // narysowanie monety
    public void draw(Graphics2D g2, GamePanel gp){

        int screenX = worldX-gp.player.worldX + gp.player.screenX;
        int screenY = worldY-gp.player.worldY + gp.player.screenY;

        g2.drawImage(image,screenX,screenY,gp.tileSize/2,gp.tileSize/2,null);

    }

}
