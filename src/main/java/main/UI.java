package main;

import object.Coin;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Font calibri_40;
    BufferedImage coinImage;

    public UI(GamePanel gp) {
        this.gp = gp;

        calibri_40 = new Font("Forte", Font.PLAIN, 40);
        Coin coin = new Coin();
        coinImage = coin.image;
    }

    // narysowanie interfejsu użytkownika na ekranie
    public void draw(Graphics2D g2) {

        g2.setFont(calibri_40);
        g2.setColor(Color.white);
        g2.drawImage(coinImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawString("x " + gp.player.coinCounter, 80, 60);
    }
}
