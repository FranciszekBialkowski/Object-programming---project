package main;

import extra.Armor;
import extra.Weapon;
import object.entity.*;
import object.Coin;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class GamePanel extends JPanel implements Runnable {

    // ustawienia wymiarów
    private final int originalTileSize = 16;
    private final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    private final int maxScreenRow = 16;
    private final int maxScreenColumn = 16;
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeight = tileSize * maxScreenRow;

    // ustawienia mapy świata
    public final int maxWorldColumn = 50;
    public final int maxWorldRow = 50;

    public Weapon weapon = new Weapon();   // broń

    public Armor armor = new Armor();  // zbroja

    TileManager tileManager = new TileManager(this);    // obsługa ładowania świata
    public KeyHandler keyH = new KeyHandler(this); // obsługa reakcji na przyciski
    private Thread gameThread;  // wątek gry
    public CollisionDetection cDetection = new CollisionDetection(this);    // obsługa wykrywania kolizji
    public AssetSetter assetSetter = new AssetSetter(this); // obsługa ustawiania obiektów na mapie
    public UI ui = new UI(this);    // obsługa interfejsu użytkownika


    public final Player player = new Player(this, keyH);   // obsługa zachowania gracza

    public Coin[] coins = new Coin[10]; // tablica monet
    public Entity[] orcs = new Orc[10]; // tablica orków
    public Entity[] pigs = new Pig[10]; // tablica świń
    public Entity[] rats = new Rat[10]; // tablica szczurów
    public Entity[] anvils = new Anvil[1]; // tablica kowadeł
    ArrayList<Entity> entityList = new ArrayList<>(); // tablica wszystkich stworzeń


    // liczniki
    public int coinCounter=0;
    public int orcCounter=0;
    public int pigCounter=0;
    public int ratCounter=0;

    // stany gry
    public int gameState;
    public final int pauseState = 1;
    public final int playState = 2;
    public final int menuState = 3;
    public final int secondMenuState = 4;

    // tablica zapisująca wartości liczników w epokach
    private final ArrayList<String> dataList = new ArrayList<>();
    private int interval = 899;
    private int era = 1;

    /**
     * Konstruktor
     */
    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    /**
     * ustawienie obiektów na mapie
     */
    void setupGame() {

        assetSetter.placeCoins();
        assetSetter.placeCreatures();
        assetSetter.placeAnvils();
        setCounters();
        gameState = menuState;
    }


    /**
     * uruchomienie wątku gry
     */
    void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * sprawdzenie czy warunek przegranej jest prawdziwy
     */
    void checkLose(){
        if (player.life == 0){
            JOptionPane.showMessageDialog(null,"Straciłeś ostatnie życie - symulacja się kończy",
                    "GAME OVER",JOptionPane.INFORMATION_MESSAGE);
            try {
                writeToFile();
            } catch (IOException e){
                throw new RuntimeException(e);
            }

            System.exit(0);
        }
    }

    /**
     * pętla gry
     */
    @Override
    public void run() {

        // klatki na sekundę
        double FPS = 60.0;
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

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


    /**
     * ustawienie wartości liczników
     */
    private void setCounters(){
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

    /**
     * zaktualizowanie pozycji obiektów na planszy
     */
    private void update() {

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
            for (Entity entity : anvils) {
                if (entity != null) {
                    entity.move();
                }
            }

            // odnowienie monet jeśli się skonczą
            if (coinCounter==0){
                assetSetter.placeCoins();
            }
        }
        if (gameState == playState) updateDataList();
    }

    /**
     * odświeżenie listy statystyk
     */
    private void updateDataList() {
        interval++;

        if (interval == 900){
            String text = String.format("|   %2d  |    %2d   |   %2d   |    %2d   |     %2d       |      %2d      |",era,
                    orcCounter,pigCounter,ratCounter, weapon.level, armor.level);
            dataList.add(text);
            era++;
            interval=0;
        }
    }

    /**
     * narysowanie mapy i obiektów na podstawie aktualnych danych
     * @param g obiekt klasy Graphics
     */
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // widok menu
        if(gameState == menuState || gameState == secondMenuState) {
            ui.draw(g2);
        }else{

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
            for (Entity entity : anvils) {
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

    /**
     * zapisanie danych z gry do pliku txt
     */
    public void writeToFile() throws IOException {
        File file = new File("lastSimulationData.txt");
        FileWriter fw = new FileWriter(file);
        fw.write("epoka - 15 sekund symulacji\n\n");
        fw.write("| epoka | orkowie | świnie | szczury | pozion broni | poziom zbroi |\n");
        fw.write("====================================================================\n");
        for (String row : dataList) {
            fw.write(row);
            fw.write("\n");
        }
        fw.close();
    }
    }

