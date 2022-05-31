package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tiles;
    public int[][] mapTileNumbers;

    public TileManager(GamePanel gp){
        this.gp = gp;

        tiles = new Tile[10];
        mapTileNumbers = new int[gp.maxWorldColumn][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map.txt");
    }

    // przypisanie grafiki każdego rodzaju terenu
    public void getTileImage(){

        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/wall.png")));
            tiles[0].collision = true;
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/smallTree.png")));
            tiles[1].collision = true;
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/bigTree_upper.png")));
            tiles[2].collision = true;
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/bigTree_lower.png")));
            tiles[3].collision = true;
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/grass.png")));
            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/black.jpg")));
            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/campfire_upleft.png")));
            tiles[6].collision = true;
            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/campfire_upright.png")));
            tiles[7].collision = true;
            tiles[8] = new Tile();
            tiles[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/campfire_downleft.png")));
            tiles[8].collision = true;
            tiles[9] = new Tile();
            tiles[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/campfire_downright.png")));
            tiles[9].collision = true;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // załadowanie mapy z pliku tekstowego
    public void loadMap(String filePath){

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            int column = 0;

            while (row<gp.maxWorldRow && column<gp.maxWorldColumn){

                String line = br.readLine();

                while (column<gp.maxWorldColumn){

                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[column]);

                    mapTileNumbers[column][row] = num;
                    column++;
                }
                if (column == gp.maxWorldColumn){
                    column = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // narysowanie terenu
    public void draw(Graphics2D g2){

        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn<gp.maxWorldColumn && worldRow<gp.maxWorldRow){

            int tileNumber = mapTileNumbers[worldColumn][worldRow];

            int worldX = worldColumn*gp.tileSize;
            int worldY = worldRow*gp.tileSize;
            int screenX = worldX-gp.player.worldX + gp.player.screenX;
            int screenY = worldY-gp.player.worldY + gp.player.screenY;


            g2.drawImage(tiles[tileNumber].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
            worldColumn++;


            if (worldColumn == gp.maxWorldColumn){
                worldColumn = 0;

                worldRow++;

            }
        }

    }
}
