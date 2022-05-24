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

    public Entity(GamePanel gp){
        this.gp = gp;
    }

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
        gp.cDetection.checkPlayer(this);

        // sprawdzenie kolizji ze stworzeniem
        int entityIndex = gp.cDetection.checkEntity(this, gp.entities);
        interactEntity(entityIndex);

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

    // interakcja przy kolizji ze stworzeniem
    @Override
    public void interactEntity(int i){}

    // narysowanie stworzenia
    @Override
    public void draw(Graphics2D g2){

        int screenX = worldX-gp.player.worldX + gp.player.screenX;
        int screenY = worldY-gp.player.worldY + gp.player.screenY;

        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }
}
