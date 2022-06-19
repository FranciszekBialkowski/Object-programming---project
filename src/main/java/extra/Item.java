package extra;

import java.awt.image.BufferedImage;

public abstract class Item implements IItem{

    protected BufferedImage image;
    public int upgradeCost;
    public int level;
    public int damage;

    public Item(){
        level = 1;
        upgradeCost = 10;
    }

}
