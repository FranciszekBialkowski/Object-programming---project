package object.entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity implements IEntity {

    protected GamePanel gp;
    public int level;

    public int gold;
    BufferedImage image;
    public int worldX, worldY;
    public int speed, maxHealth, health, attackDamage;
    public String direction = "";
    public Rectangle hitBox = new Rectangle(0,0,47,47);
    public int hitBoxDefaultX, hitBoxDefaultY;
    public boolean collisionOn = false;
    protected int actionInterval = 0;
    protected boolean isInvisible = false;
    protected int invisibleCounter = 0;
    public int maxLife;
    public int life;
    public String mage = "Fire Mage";
    protected int baseAD;
    protected int baseHP;

    /**
     * Konstruktor
     */
    public Entity(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void setDefaultValues(){}

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

    @Override
    public void interactCoin(int i){
        if (i != 999){
            gp.coins[i] = null;
            gp.coinCounter--;
        }
    }

    @Override
    public void interactOrc(int i){}

    @Override
    public void interactPig(int i){}

    @Override
    public void interactRat(int i){}

    @Override
    public void interactPlayer(boolean c){}

    @Override
    public void interactAnvil(int i){}

    @Override
    public void draw(Graphics2D g2){

        int screenX = worldX-gp.player.worldX + gp.player.screenX;
        int screenY = worldY-gp.player.worldY + gp.player.screenY;

        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }
}
