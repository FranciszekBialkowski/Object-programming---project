package main;

import object.Coin;
import object.entity.Anvil;
import object.entity.Orc;
import object.entity.Pig;
import object.entity.Rat;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // ustawienie monet na mapie
    public void placeCoins() {

        gp.coins[0] = new Coin();
        gp.coins[0].worldX = 18 * gp.tileSize;
        gp.coins[0].worldY = 6 * gp.tileSize;

        gp.coins[1] = new Coin();
        gp.coins[1].worldX = 23 * gp.tileSize;
        gp.coins[1].worldY = 10 * gp.tileSize;

        gp.coins[2] = new Coin();
        gp.coins[2].worldX = 31 * gp.tileSize;
        gp.coins[2].worldY = 19 * gp.tileSize;

        gp.coins[3] = new Coin();
        gp.coins[3].worldX = 17 * gp.tileSize;
        gp.coins[3].worldY = 27 * gp.tileSize;

        gp.coins[4] = new Coin();
        gp.coins[4].worldX = 41 * gp.tileSize;
        gp.coins[4].worldY = 35 * gp.tileSize;

        gp.coins[5] = new Coin();
        gp.coins[5].worldX = 46 * gp.tileSize;
        gp.coins[5].worldY = 39 * gp.tileSize;

        gp.coins[6] = new Coin();
        gp.coins[6].worldX = 19 * gp.tileSize;
        gp.coins[6].worldY = 46 * gp.tileSize;

        gp.coins[7] = new Coin();
        gp.coins[7].worldX = 23 * gp.tileSize;
        gp.coins[7].worldY = 35 * gp.tileSize;
    }

    // ustawienie stworzeń na mapie
    public void placeCreatures() {

        gp.pigs[0] = new Pig(gp);
        gp.pigs[0].worldX = 18 * gp.tileSize;
        gp.pigs[0].worldY = 3 * gp.tileSize;

        gp.pigs[1] = new Pig(gp);
        gp.pigs[1].worldX = 44 * gp.tileSize;
        gp.pigs[1].worldY = 44 * gp.tileSize;

        gp.pigs[2] = new Pig(gp);
        gp.pigs[2].worldX = 21 * gp.tileSize;
        gp.pigs[2].worldY = 25 * gp.tileSize;

        gp.orcs[0] = new Orc(gp);
        gp.orcs[0].worldX = 27 * gp.tileSize;
        gp.orcs[0].worldY = 10 * gp.tileSize;

        gp.orcs[1] = new Orc(gp);
        gp.orcs[1].worldX = 41 * gp.tileSize;
        gp.orcs[1].worldY = 33 * gp.tileSize;

        gp.orcs[2] = new Orc(gp);
        gp.orcs[2].worldX = 28 * gp.tileSize;
        gp.orcs[2].worldY = 40 * gp.tileSize;

        gp.orcs[3] = new Orc(gp);
        gp.orcs[3].worldX = 25 * gp.tileSize;
        gp.orcs[3].worldY = 15 * gp.tileSize;

        gp.orcs[4] = new Orc(gp);
        gp.orcs[4].worldX = 47 * gp.tileSize;
        gp.orcs[4].worldY = 21 * gp.tileSize;

        gp.orcs[5] = new Orc(gp);
        gp.orcs[5].worldX = 33 * gp.tileSize;
        gp.orcs[5].worldY = 31 * gp.tileSize;

        gp.rats[0] = new Rat(gp);
        gp.rats[0].worldX = 23 * gp.tileSize;
        gp.rats[0].worldY = 10 * gp.tileSize;

        gp.rats[1] = new Rat(gp);
        gp.rats[1].worldX = 22 * gp.tileSize;
        gp.rats[1].worldY = 28 * gp.tileSize;

        gp.rats[2] = new Rat(gp);
        gp.rats[2].worldX = 42 * gp.tileSize;
        gp.rats[2].worldY = 39 * gp.tileSize;

        gp.anvils[0] = new Anvil(gp);
        gp.anvils[0].worldX = 34 * gp.tileSize;
        gp.anvils[0].worldY = 24 * gp.tileSize;


    }


}
