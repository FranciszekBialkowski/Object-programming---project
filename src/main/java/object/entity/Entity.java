package object.entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity implements IEntity {

    GamePanel gp;
    public BufferedImage image;
    public int worldX, worldY;
    public int speed, maxHealth, health, attackDamage;
    public String direction = "";
    public String name;
    public Rectangle hitBox = new Rectangle(0,0,47,47);
    public int hitBoxDefaultX, hitBoxDefaultY;
    public boolean collisionOn = false;
    public int actionInterval = 0;
    public boolean invisible = false;
    public int invisibleCounter = 0;

    // życie gracza
    public int maxLife;
    public int life;


    public Entity(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void setAction(){}

    @Override
    public void move(){
        setAction();

        collisionOn = false;

        // sprawdzenie kolizji z terenem
        gp.cDetection.checkTile(this);

        // sprawdzenie kolizji z monetą
        int coinIndex = gp.cDetection.checkCoin(this);
        interactCoin(coinIndex);

        // sprawdzenie kolizji z graczem
        boolean playerCollision = gp.cDetection.checkPlayer(this);
        interactPlayer(playerCollision);

        // sprawdzenie kolizji z agresywnym stworzeniem
        int aggressiveCreatureIndex = gp.cDetection.checkEntity(this, gp.aggressiveCreatures);
        interactAggressiveCreature(aggressiveCreatureIndex);

        // sprawdzenie kolizji z neutralnym stworzeniem
        int neutralCreatureIndex = gp.cDetection.checkEntity(this, gp.neutralCreatures);
        interactNeutralCreature(neutralCreatureIndex);

        // sprawdzenie kolizji z małym stworzeniem
        int smallCreatureIndex = gp.cDetection.checkEntity(this, gp.neutralCreatures);
        interactSmallCreature(smallCreatureIndex);


        // jeśli kolizja nie wystąpiła, stworzenie może się poruszyć
        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "left" -> worldX -= speed;
                case "down" -> worldY += speed;
                case "right" -> worldX += speed;
            }
        }
    }

    // interakcja przy kolizji z monetą
    @Override
    public void interactCoin(int i){}

    // interakcja przy kolizji z agresywnym stworzeniem
    @Override
    public void interactAggressiveCreature(int i){}

    // interakcja przy kolizji z neutralnym stworzeniem
    @Override
    public void interactNeutralCreature(int i){}

    // interakcja przy kolizji z małym stworzeniem
    @Override
    public void interactSmallCreature(int i){}

    // interakcja przy kolizji z graczem
    @Override
    public void interactPlayer(boolean c){}

    // narysowanie stworzenia
    @Override
    public void draw(Graphics2D g2){

        int screenX = worldX-gp.player.worldX + gp.player.screenX;
        int screenY = worldY-gp.player.worldY + gp.player.screenY;

        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }
}
