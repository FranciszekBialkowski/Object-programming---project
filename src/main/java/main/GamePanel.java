package main;

import object.entity.*;
import object.Coin;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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
    public Entity[] aggressiveCreatures = new AggressiveCreature[10]; // tablica agresywnych stworzeń
    public Entity[] neutralCreatures = new NeutralCreature[10]; // tablica neutralnych stworzeń
    public Entity[] smallCreatures = new SmallCreature[10]; // tablica małych stworzeń
    ArrayList<Entity> entityList = new ArrayList<>();

    // stany gry
    public int gameState;
    public final int pauseState = 0;
    public final int playState = 1;


    public GamePanel() throws IOException {
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
        gameState = playState;
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

        if (gameState == playState) {

            player.move();

            for (Entity entity : aggressiveCreatures) {
                if (entity != null) {
                    entity.move();
                }
            }
            for (Entity entity : neutralCreatures) {
                if (entity != null) {
                    entity.move();
                }
            }
            for (Entity entity : smallCreatures) {
                if (entity != null) {
                    entity.move();
                }
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

        // Lista obiektów poruszających się
        entityList.add(player);

        for (Entity entity : aggressiveCreatures) {
            if (entity != null) {
                entityList.add(entity);
            }
        }
        for (Entity entity : neutralCreatures) {
            if (entity != null) {
                entityList.add(entity);
            }
        }
        for (Entity entity : smallCreatures) {
            if (entity != null) {
                entityList.add(entity);
            }
        }

        Collections.sort(entityList, new Comparator<Entity>() {
            @Override
            public int compare(Entity o1, Entity o2) {
                return Integer.compare(o1.worldX, o2.worldY);
            }
        });

        // narysowanie obiektów poruszających się
        for (Entity entity : entityList) {
            entity.draw(g2);
        }

        // wyczyszczenie listy
        for (int i = 0; i < entityList.size(); i++) {
            entityList.remove(i);
        }


        // UI
        ui.draw(g2);

        g2.dispose();
    }
}
