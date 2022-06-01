package main;

import object.Coin;
import object.Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {

    GamePanel gp;
    Font calibri_40;
    BufferedImage coinImage;
    BufferedImage heartFull, heartBlank;

    public UI(GamePanel gp) throws IOException {
        this.gp = gp;

        calibri_40 = new Font("Forte", Font.PLAIN, 40);
        Coin coin = new Coin();
        coinImage = coin.image;

        Heart heart = new Heart(gp);
        heartFull = heart.image1;
        heartBlank = heart.image2;
    }


    // narysowanie interfejsu użytkownika na ekranie
    public void draw(Graphics2D g2) {

        g2.setFont(calibri_40);
        g2.setColor(Color.white);
        g2.drawImage(coinImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.coinCounter, 80, 60);

        int i = 0;
        int x =0;
        int y =720;
        while (i<gp.player.maxLife){
            g2.drawImage(heartBlank,x,y, gp.tileSize, gp.tileSize, null);
            i++;
            x += gp.tileSize;
        }

        i = 0;
        x =0;
        while (i<gp.player.life){
            g2.drawImage(heartFull,x,y, gp.tileSize, gp.tileSize, null);
            i++;
            x += gp.tileSize;
        }
    }
}
