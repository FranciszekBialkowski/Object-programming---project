package object.entity;

import main.EventFight;
import main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class AggressiveCreature extends Entity{

    int level;

    public AggressiveCreature(GamePanel gp , int level){
        super(gp);

        this.level = level;
        name = "orc";
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

    // spotkanie gracza
    @Override
    public void interactPlayer(boolean c) {
        if (c && !gp.player.invisible){
            gp.player.invisible = true;
            new EventFight(gp);
        }
    }
}
