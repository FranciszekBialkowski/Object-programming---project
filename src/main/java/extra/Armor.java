package extra;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Armor extends Item{

    int health;

    public Armor(){
        super();
        name = "Robe";
        health = 10;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/yellow.jpg")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // ulepszenie zbroi
    @Override
    public void upgrade() {
        level++;
        upgradeCost *=2;
        health += 10;
    }
}
