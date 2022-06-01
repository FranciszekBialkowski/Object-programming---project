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
    public int coinCounter = 0;

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
        speed = 4;

        // życie
        maxLife = 3;
        life = maxLife;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/fireMage_down.png")));
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

        // sprawdzenie kolizji z agresywnym stworzeniem
        int aggressiveCreatureIndex = gp.cDetection.checkEntity(this, gp.aggressiveCreatures);
        interactAggressiveCreature(aggressiveCreatureIndex);

        // sprawdzenie kolizji z neutralnym stworzeniem
        int neutralCreatureIndex = gp.cDetection.checkEntity(this, gp.neutralCreatures);
        interactNeutralCreature(neutralCreatureIndex);

        // sprawdzenie kolizji z małym stworzeniem
        int smallCreatureIndex = gp.cDetection.checkEntity(this, gp.neutralCreatures);
        interactSmallCreature(smallCreatureIndex);

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
        if (invisible){
            invisibleCounter++;
            if (invisibleCounter>60){
                invisible = false;
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
            coinCounter += random;
        }
    }

    // interakcja przy kolizji ze stworzeniem
    @Override
    public void interactAggressiveCreature(int i){
        if (i != 999){
            if (!invisible){
                invisible = true;
                new EventFight(gp);
            }
        }
    }

    // interakcja przy kolizji z małym stworzeniem
    @Override
    public void interactSmallCreature(int i){
        if (i != 999){
            gp.smallCreatures[i].interactPlayer(true);
        }
    }

}
