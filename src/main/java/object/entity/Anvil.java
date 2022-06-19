package object.entity;

import main.EventAnvil;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Anvil extends Entity {

    /**
     * Konstruktor
     */
    public Anvil(GamePanel gp){
        super(gp);

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/anvil.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void interactPlayer(boolean c) {
        if (c && !gp.player.isInvisible) {
            gp.player.isInvisible = true;
            new EventAnvil(gp);
        }
    }
}



