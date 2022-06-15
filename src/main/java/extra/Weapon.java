package extra;


import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Weapon extends Item{

    public Weapon(){
        super();
        name = "Staff";
        damage = 10;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/staff.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void upgrade() {
        level++;
        damage += 10;
        upgradeCost+=10;
    }
}
