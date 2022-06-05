package main;

import extra.Armor;
import extra.Weapon;
import object.entity.*;
import object.Coin;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class GamePanel extends JPanel implements Runnable {

    // ustawienia wymiarów
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

    public Weapon weapon = new Weapon();   // broń

    public Armor armor = new Armor();  // zbroja

    TileManager tileManager = new TileManager(this);    // obsługa ładowania świata
    public KeyHandler keyH = new KeyHandler(); // obsługa reakcji na przyciski
    Thread gameThread;  // wątek gry
    public CollisionDetection cDetection = new CollisionDetection(this);    // obsługa wykrywania kolizji
    public AssetSetter assetSetter = new AssetSetter(this); // obsługa ustawiania obiektów na mapie
    public UI ui = new UI(this);    // obsługa interfejsu użytkownika


    public Player player = new Player(this, keyH);   // obsługa zachowania gracza
    public Coin[] coins = new Coin[10]; // tablica monet
    public Entity[] orcs = new Orc[10]; // tablica orków
    public Entity[] pigs = new Pig[10]; // tablica świń
    public Entity[] rats = new Rat[10]; // tablica szczurów
    ArrayList<Entity> entityList = new ArrayList<>();

    // liczniki
    public int coinCounter=0;
    public int orcCounter=0;
    public int pigCounter=0;
    public int ratCounter=0;

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
        setCounters();
        gameState = playState;
    }

    // uruchomienie wątku gry
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void checkLose(){
        if (player.life == 0){
            System.out.println("GAME OVER");
        }
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

    // ustawienie wartości liczników
    public void setCounters(){
        for (Coin coin : coins) {
            if (coin != null) {
                coinCounter++;
            }
        }
        for (Entity orc : orcs){
            if (orc != null){
                orcCounter++;
            }
        }
        for (Entity pig : pigs){
            if (pig != null){
                pigCounter++;
            }
        }
        for (Entity rat : rats){
            if (rat != null){
                ratCounter++;
            }
        }
    }

    // zaktualizowanie pozycji obiektów na planszy
    public void update() {

        if (gameState == playState) {


            player.move();

            for (Entity entity : orcs) {
                if (entity != null) {
                    entity.move();
                }
            }
            for (Entity entity : pigs) {
                if (entity != null) {
                    entity.move();
                }
            }
            for (Entity entity : rats) {
                if (entity != null) {
                    entity.move();
                }
            }

            // odnowienie monet jeśli się skonczą
            if (coinCounter==0){
                assetSetter.placeCoins();
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

        for (Entity entity : orcs) {
            if (entity != null) {
                entityList.add(entity);
            }
        }
        for (Entity entity : pigs) {
            if (entity != null) {
                entityList.add(entity);
            }
        }
        for (Entity entity : rats) {
            if (entity != null) {
                entityList.add(entity);
            }
        }

        entityList.sort((o1, o2) -> Integer.compare(o1.worldX, o2.worldY));

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
