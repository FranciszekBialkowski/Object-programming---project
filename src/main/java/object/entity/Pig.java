package object.entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Pig extends Entity{

    /**
     * Konstruktor
     */
    public Pig(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pig.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void setAction() {

        actionInterval ++;

        if (actionInterval == 120){
            Random rand = new Random();
            int random = rand.nextInt(100)+1;

            if (random<=20) direction = "stay";
            else if (random<=40) direction = "up";
            else if (random<=60) direction = "left";
            else if (random<=80) direction = "down";
            else direction = "right";

            actionInterval = 0;
        }
    }


}
