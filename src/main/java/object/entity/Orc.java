package object.entity;

import main.EventFight;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Orc extends Entity {

    /**
     * Konstruktor
     */
    public Orc(GamePanel gp) {
        super(gp);

        direction = "left";
        speed = 2;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/orc.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDefaultValues(int level){
        this.level = level;
        Random rand = new Random();
        maxHealth = rand.nextInt(30,50) * level;
        health = maxHealth;
        attackDamage = rand.nextInt(7,15)* level;
        gold = rand.nextInt(5, 11) * level;
    }

    // wylosowanie kierunku
    @Override
    public void setAction() {

        actionInterval++;

        if (actionInterval == 150) {
            Random rand = new Random();
            int random = rand.nextInt(100) + 1;

            if (random <= 25) direction = "up";
            else if (random <= 50) direction = "left";
            else if (random <= 75) direction = "down";
            else direction = "right";

            actionInterval = 0;
        }
    }

    @Override
    public void interactCoin(int i){

        if (i != 999){
            int random;
            Random rand = new Random();
            random = rand.nextInt(1,5);
            gp.coins[i] = null;
            gp.coinCounter--;
            gold += random;
        }
    }

    @Override
    public void interactPlayer(boolean c) {
        if (c && !gp.player.isInvisible) {
            gp.player.isInvisible = true;
            new EventFight(gp, this);
        }
    }

    @Override
    public void interactPig(int i) {
        if (i != 999) {
            gp.pigs[i] = null;
            gp.pigCounter--;
        }
    }

    @Override
    public void interactRat(int i) {
        if (i != 999) {
            gp.rats[i] = null;
            gp.ratCounter--;
        }
    }
}
