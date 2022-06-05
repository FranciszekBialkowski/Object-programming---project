package object.entity;

import main.EventFight;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Player extends Entity{

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    // licznik monet
    public int playerCoins = 0;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        name = "Player";

        hitBox = new Rectangle();
        hitBox.x = 4;
        hitBox.y = 4;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        hitBox.width = 32;
        hitBox.height = 36;

        setDefaultValues();
    }

    // ustawienie domyślnych wartości
    public void setDefaultValues(){
        worldX = gp.tileSize * 27;
        worldY= gp.tileSize * 25;

        if (mage.equals("Fire Mage")){
            baseHP = 50;
            baseAD = 10;
            speed = 3;
        } else if (mage.equals("Ice Mage")) {
            baseHP = 75;
            baseAD = 5;
            speed = 3;
        } else {
            baseHP = 50;
            baseAD = 5;
            speed = 4;
        }

        attackDamage = baseAD + gp.weapon.damage;
        health = baseHP + gp.armor.health;
        maxHealth = health;


        // życie
        maxLife = 3;
        life = maxLife;

        try {
            if (mage.equals("Fire Mage")){
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/fireMage_down.png")));
            } else if (mage.equals("Ice Mage")) {
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/blue.jpg")));
            } else {
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/yellow.jpg")));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    // zaktualizowanie pozycji postaci
    @Override
    public void move(){
        if (keyH.upClicked){
            direction = "up";
        }
        else if (keyH.leftClicked) {
            direction = "left";
        }
        else if (keyH.downClicked) {
            direction = "down";
        }
        else if (keyH.rightClicked) {
            direction = "right";
        }
        else direction = "";

        // sprawdzenie kolizji z terenem
        collisionOn = false;
        gp.cDetection.checkTile(this);

        // sprawdzenie kolizji z monetą
        int coinIndex = gp.cDetection.checkCoin(this);
        interactCoin(coinIndex);

        // sprawdzenie kolizji z orkiem
        int orcIndex = gp.cDetection.checkEntity(this, gp.orcs);
        interactOrc(orcIndex);

        // sprawdzenie kolizji ze świnią
        int pigIndex = gp.cDetection.checkEntity(this, gp.pigs);
        interactPig(pigIndex);

        // sprawdzenie kolizji ze szczurem
        int ratIndex = gp.cDetection.checkEntity(this, gp.rats);
        interactRat(ratIndex);

        // jeśli kolizja nie wystąpiła, gracz może się poruszyć
        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "left" -> worldX -= speed;
                case "down" -> worldY += speed;
                case "right" -> worldX += speed;
            }
        }

        // tryb niewidzialny (po walce)
        if (isInvisible){
            invisibleCounter++;
            if (invisibleCounter>90){
                isInvisible = false;
                invisibleCounter = 0;
            }
        }
    }

    // podniesienie monety
    @Override
    public void interactCoin(int i){

        if (i != 999){
            int random;
            Random rand = new Random();
            random = rand.nextInt(1,5);
            gp.coins[i] = null;
            gp.coinCounter--;
            playerCoins += random;
        }
    }

    // interakcja przy kolizji z orkiem
    @Override
    public void interactOrc(int i){
        if (i != 999){
            if (!isInvisible){
                isInvisible = true;
                new EventFight(gp, gp.orcs[i]);
            }
        }
    }

    // interakcja przy kolizji ze szczurem
    @Override
    public void interactRat(int i){
        if (i != 999){
            gp.rats[i].interactPlayer(true);
        }
    }

}
