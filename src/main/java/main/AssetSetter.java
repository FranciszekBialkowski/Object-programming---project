package main;

import object.Coin;
import object.entity.AgressiveCreature;
import object.entity.NeutralCreature;
import object.entity.SmallCreature;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // ustawienie monet na mapie
    public void placeCoins() {

        gp.coins[0] = new Coin();
        gp.coins[0].worldX = 23 * gp.tileSize;
        gp.coins[0].worldY = 7 * gp.tileSize;

        gp.coins[1] = new Coin();
        gp.coins[1].worldX = 23 * gp.tileSize;
        gp.coins[1].worldY = 40 * gp.tileSize;

        gp.coins[2] = new Coin();
        gp.coins[2].worldX = 11 * gp.tileSize;
        gp.coins[2].worldY = 11 * gp.tileSize;

        gp.coins[3] = new Coin();
        gp.coins[3].worldX = 3 * gp.tileSize;
        gp.coins[3].worldY = 41 * gp.tileSize;

        gp.coins[4] = new Coin();
        gp.coins[4].worldX = 42 * gp.tileSize;
        gp.coins[4].worldY = 40 * gp.tileSize;

        gp.coins[5] = new Coin();
        gp.coins[5].worldX = 46 * gp.tileSize;
        gp.coins[5].worldY = 13 * gp.tileSize;

        gp.coins[6] = new Coin();
        gp.coins[6].worldX = 31 * gp.tileSize;
        gp.coins[6].worldY = 10 * gp.tileSize;

        gp.coins[7] = new Coin();
        gp.coins[7].worldX = 17 * gp.tileSize;
        gp.coins[7].worldY = 35 * gp.tileSize;
    }

    // ustawienie stworzeń na mapie
    public void placeCreatures() {

        gp.entities[0] = new NeutralCreature(gp);
        gp.entities[0].worldX = 23 * gp.tileSize;
        gp.entities[0].worldY = 20 * gp.tileSize;

        gp.entities[1] = new NeutralCreature(gp);
        gp.entities[1].worldX = 3 * gp.tileSize;
        gp.entities[1].worldY = 4 * gp.tileSize;

        gp.entities[2] = new NeutralCreature(gp);
        gp.entities[2].worldX = 40 * gp.tileSize;
        gp.entities[2].worldY = 20 * gp.tileSize;

        gp.entities[3] = new AgressiveCreature(gp, 1);
        gp.entities[3].worldX = 23 * gp.tileSize;
        gp.entities[3].worldY = 30 * gp.tileSize;

        gp.entities[4] = new AgressiveCreature(gp, 1);
        gp.entities[4].worldX = 11 * gp.tileSize;
        gp.entities[4].worldY = 40 * gp.tileSize;

        gp.entities[5] = new AgressiveCreature(gp, 1);
        gp.entities[5].worldX = 41 * gp.tileSize;
        gp.entities[5].worldY = 10 * gp.tileSize;

        gp.entities[6] = new AgressiveCreature(gp, 2);
        gp.entities[6].worldX = 45 * gp.tileSize;
        gp.entities[6].worldY = 45 * gp.tileSize;

        gp.entities[7] = new AgressiveCreature(gp, 2);
        gp.entities[7].worldX = 3 * gp.tileSize;
        gp.entities[7].worldY = 35 * gp.tileSize;

        gp.entities[8] = new AgressiveCreature(gp, 3);
        gp.entities[8].worldX = 27 * gp.tileSize;
        gp.entities[8].worldY = 5 * gp.tileSize;

        gp.entities[9] = new SmallCreature(gp);
        gp.entities[9].worldX = 23 * gp.tileSize;
        gp.entities[9].worldY = 45 * gp.tileSize;

        gp.entities[10] = new SmallCreature(gp);
        gp.entities[10].worldX = 10 * gp.tileSize;
        gp.entities[10].worldY = 10 * gp.tileSize;

        gp.entities[11] = new SmallCreature(gp);
        gp.entities[11].worldX = 38 * gp.tileSize;
        gp.entities[11].worldY = 28 * gp.tileSize;


    }


}
