package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Heart {

    GamePanel gp;
    public BufferedImage image1;
    public BufferedImage image2;

    public Heart(GamePanel gp) {

        this.gp = gp;

        try {
            image1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/heart_full.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/heart_blank.png")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
