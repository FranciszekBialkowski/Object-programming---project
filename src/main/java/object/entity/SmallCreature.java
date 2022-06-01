package object.entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class SmallCreature extends Entity{

    public SmallCreature(GamePanel gp){
        super(gp);

        name = "mouse";
        direction = "up";
        speed = 3;
//        hitBox.width = gp.tileSize/2-1;
//        hitBox.height = gp.tileSize/2-1;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/rat_down.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // wylosowanie kierunku
    @Override
    public void setAction() {

        actionInterval ++;

        if (actionInterval == 100){
            speed = 3;
            Random rand = new Random();
            int random = rand.nextInt(100)+1;

            if (random<=25) direction = "up";
            else if (random<=50) direction = "left";
            else if (random<=75) direction = "down";
            else direction = "right";

            actionInterval = 0;
        }
    }

    // podniesienie monety
    @Override
    public void interactCoin(int i){

        if (i != 999){
            gp.coins[i] = null;
        }
    }

    // interakcja przy kolizji z agresywnym stworzeniem
    @Override
    public void interactAggressiveCreature(int i){
        if (i != 999){
            switch (direction) {
                case "up" -> direction = "down";
                case "left" -> direction = "right";
                case "down" -> direction = "up";
                case "right" -> direction = "left";
            }
            speed = 5;
        }
    }

    // interakcja przy kolizji z neutralnym stworzeniem
    @Override
    public void interactNeutralCreature(int i){
        if (i != 999){
            switch (direction) {
                case "up" -> direction = "down";
                case "left" -> direction = "right";
                case "down" -> direction = "up";
                case "right" -> direction = "left";
            }
            speed = 5;
        }
    }

    // interakcja przy kolizji z małym stworzeniem
    @Override
    public void interactSmallCreature(int i){
        if (i != 999){
            switch (direction) {
                case "up" -> direction = "down";
                case "left" -> direction = "right";
                case "down" -> direction = "up";
                case "right" -> direction = "left";
            }
            speed = 5;
        }
    }

    @Override
    public void interactPlayer(boolean c) {
        if (c){
            switch (direction) {
                case "up" -> direction = "down";
                case "left" -> direction = "right";
                case "down" -> direction = "up";
                case "right" -> direction = "left";
            }
            speed = 5;
        }
    }

    @Override
    public void draw(Graphics2D g2){

        int screenX = worldX-gp.player.worldX + gp.player.screenX;
        int screenY = worldY-gp.player.worldY + gp.player.screenY;

        g2.drawImage(image,screenX,screenY,gp.tileSize/2,gp.tileSize/2,null);
    }
}
