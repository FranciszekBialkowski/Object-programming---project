package object.entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class NeutralCreature extends Entity{

    public NeutralCreature(GamePanel gp){
        super(gp);

        name = "pig";
        direction = "down";
        speed = 1;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/pig_down.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // wylosowanie kierunku
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
