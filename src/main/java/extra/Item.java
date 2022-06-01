package extra;

import java.awt.image.BufferedImage;

public abstract class Item implements IItem{

    public BufferedImage image;
    public String name;
    public int upgradeCost;
    public int level;

    public Item(){
        level = 1;
        upgradeCost = 10;
    }

}
