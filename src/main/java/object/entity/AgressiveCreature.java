package object.entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class AgressiveCreature extends Entity{

    int level;

    public AgressiveCreature(GamePanel gp , int level){
        super(gp);

        this.level = level;
        name = "orc?";
        direction = "left";
        speed = 2;
        maxHealth = 30 * level;
        health = maxHealth;
        attackDamage = 10 * level;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/orc_down.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // wylosowanie kierunku
    @Override
    public void setAction() {

        actionInterval ++;

        if (actionInterval == 150){
            Random rand = new Random();
            int random = rand.nextInt(100)+1;

            if (random<=25) direction = "up";
            else if (random<=50) direction = "left";
            else if (random<=75) direction = "down";
            else direction = "right";

            actionInterval = 0;
        }

    }

}
