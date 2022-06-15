package object.entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity implements IEntity {

    GamePanel gp;
    public int level;

    public int gold;
    public BufferedImage image;
    public int worldX, worldY;
    public int speed, maxHealth, health, attackDamage;
    public String direction = "";
    public String name;
    public Rectangle hitBox = new Rectangle(0,0,47,47);
    public int hitBoxDefaultX, hitBoxDefaultY;
    public boolean collisionOn = false;
    public int actionInterval = 0;
    public boolean isInvisible = false;
    public int invisibleCounter = 0;
    public int maxLife;
    public int life;
    public String mage = "Fire Mage";
    public int baseAD;
    public int baseHP;


    public Entity(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public  void setDefaultValues(){}

    @Override
    public  void setDefaultValues(int level){}

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
        int orcIndex = gp.cDetection.checkEntity(this, gp.orcs);
        interactOrc(orcIndex);

        // sprawdzenie kolizji z neutralnym stworzeniem
        int pigIndex = gp.cDetection.checkEntity(this, gp.pigs);
        interactPig(pigIndex);

        // sprawdzenie kolizji z małym stworzeniem
        int ratIndex = gp.cDetection.checkEntity(this, gp.rats);
        interactRat(ratIndex);

        // sprawdzenie kolizji z kowadłem
        int anvilIndex = gp.cDetection.checkEntity(this, gp.anvils);
        interactAnvil(anvilIndex);


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
    public void interactCoin(int i){
        if (i != 999){
            gp.coins[i] = null;
            gp.coinCounter--;
        }
    }

    // interakcja przy kolizji z orkiem
    @Override
    public void interactOrc(int i){}

    // interakcja przy kolizji ze świnią
    @Override
    public void interactPig(int i){}

    // interakcja przy kolizji ze szczurem
    @Override
    public void interactRat(int i){}

    // interakcja przy kolizji z graczem
    @Override
    public void interactPlayer(boolean c){}

    // interakcja przy kolizji z kowadłem
    @Override
    public void interactAnvil(int i){}

    // narysowanie stworzenia
    @Override
    public void draw(Graphics2D g2){

        int screenX = worldX-gp.player.worldX + gp.player.screenX;
        int screenY = worldY-gp.player.worldY + gp.player.screenY;

        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }
}
