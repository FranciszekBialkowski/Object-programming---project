package main;

import object.entity.Entity;
import object.entity.Player;
import object.Coin;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // ustawienia okna
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenRow = 16;
    public final int maxScreenColumn = 16;
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeight = tileSize * maxScreenRow;

    // ustawienia mapy świata
    public final int maxWorldColumn = 50;
    public final int maxWorldRow = 50;

    // klatki na sekundę
    final double FPS = 60.0;

    TileManager tileManager = new TileManager(this);    // obsługa ładowania świata
    KeyHandler keyH = new KeyHandler(); // obsługa reakcji na przyciski
    Thread gameThread;  // wątek gry
    public CollisionDetection cDetection = new CollisionDetection(this);    // obsługa wykrywania kolizji
    public AssetSetter assetSetter = new AssetSetter(this); // obsługa ustawiania obiektów na mapie
    public UI ui = new UI(this);    // obsługa interfejsu użytkownika
    public Player player = new Player(this, keyH);   // obsługa zachowania gracza
    public Coin[] coins = new Coin[10]; // tablica monet
    public Entity[] entities = new Entity[15]; // tablica stworzeń


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    // ustawienie obiektów na mapie
    public void setupGame() {

        assetSetter.placeCoins();
        assetSetter.placeCreatures();
    }

    // uruchomienie wątku gry
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // pętla gry
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            if ((String.valueOf(System.nanoTime()).charAt(5)) == 0) new Coin();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // zaktualizowanie pozycji obiektów na planszy
    public void update() {

        // Gracz
        player.move();

        // Stworzenia
        for (Entity entity : entities) {
            if (entity != null) {
                entity.move();
            }
        }
    }

    // narysowanie mapy i obiektów na podstawie aktualnych danych
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Mapa
        tileManager.draw(g2);

        // Monety
        for (Coin coin : coins) {
            if (coin != null) {
                coin.draw(g2, this);
            }
        }

        // Stworzenia
        for (Entity entity : entities) {
            if (entity != null) {
                entity.draw(g2);
            }
        }

        // Gracz
        player.draw(g2);

        // UI
        ui.draw(g2);

        g2.dispose();
    }
}
