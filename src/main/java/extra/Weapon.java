package extra;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Weapon extends Item{

    int damage;

    public Weapon(){
        super();
        name = "Staff";
        damage = 10;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/brown.jpg")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // ulepszenie broni
    @Override
    public void upgrade() {
        level++;
        upgradeCost *=2;
        damage += 10;
    }
}
