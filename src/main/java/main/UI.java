package main;

import object.Coin;
import object.Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class UI {

    private final GamePanel gp;
    private final Font forte_40;
    private Font forte_60;
    private final BufferedImage coinImage;
    private final BufferedImage heartFull;
    private final BufferedImage heartBlank;
    private BufferedImage fireMage;
    private BufferedImage iceMage;
    private BufferedImage lightningMage;
    private Graphics2D g2;

    public int commandNum = 0;

    /**
     * Konstruktor
     */
    public UI(GamePanel gp) {
        this.gp = gp;

        forte_40 = new Font("Forte", Font.PLAIN, 40);
        Coin coin = new Coin();
        coinImage = coin.image;

        Heart heart = new Heart();
        heartFull = heart.image1;
        heartBlank = heart.image2;
    }


    /**
     * narysowanie interfejsów użytkownika na ekranie
     * @param g2 obiekt klasy Graphics2D
     */
    void draw(Graphics2D g2) {
        this.g2 = g2;

        if(gp.gameState == gp.menuState || gp.gameState == gp.secondMenuState){
            drawMenuScreen();
        }else{

            g2.setFont(forte_40);
            g2.setColor(Color.white);
            g2.drawImage(coinImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.playerCoins, 80, 60);

            int i = 0;
            int x = 0;
            int y = 720;
            while (i < gp.player.maxLife) {
                g2.drawImage(heartBlank, x, y, gp.tileSize, gp.tileSize, null);
                i++;
                x += gp.tileSize;
            }

            i = 0;
            x = 0;
            while (i < gp.player.life) {
                g2.drawImage(heartFull, x, y, gp.tileSize, gp.tileSize, null);
                i++;
                x += gp.tileSize;
            }
        }
    }


    /**
     * narysowanie menu głównego
     */
    private void drawMenuScreen(){

        if(gp.gameState == gp.menuState) {
            forte_60 = new Font("Forte", Font.PLAIN, 60);

            g2.setFont(forte_60);
            String text = "Wybierz postac";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 2;

            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            x = gp.tileSize;
            y = gp.tileSize * 4;
            g2.drawImage(fireMage, x, y, gp.tileSize * 3, gp.tileSize * 3, null);

            x = gp.tileSize * 6;
            g2.drawImage(iceMage, x, y, gp.tileSize * 3, gp.tileSize * 3, null);

            x = gp.tileSize * 12;
            g2.drawImage(lightningMage, x, y, gp.tileSize * 3, gp.tileSize * 3, null);

            try {
                fireMage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/fireMage.png")));
                iceMage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/iceMage.png")));
                lightningMage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/lightningMage.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            g2.setFont(forte_40);

            text = "Mag ognia";
            x = getXforCenteredText(text);
            y = gp.tileSize * 10;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Mag lodu";
            x = getXforCenteredText(text);
            y = gp.tileSize * 11;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Mag blyskawic";
            x = getXforCenteredText(text);
            y = gp.tileSize * 12;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }

        } else if(gp.gameState == gp.secondMenuState){

            g2.setFont(forte_60);
            String text = "Wybierz poziom trudnosci";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 2;

            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            text = "Latwy";
            x = getXforCenteredText(text);
            y = gp.tileSize * 7;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Sredni";
            x = getXforCenteredText(text);
            y = gp.tileSize * 9;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Trudny";
            x = getXforCenteredText(text);
            y = gp.tileSize * 11;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
    }

    /**
     * znaleznienie współrzędnej x w jakiej należy umieścić tekst, aby był wycentrowany
     * @param text tekst
     * @return współrzędna x w jakiej należy umieścić tekst
     */
    private int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
}
