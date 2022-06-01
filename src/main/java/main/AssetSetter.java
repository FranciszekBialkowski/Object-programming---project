package main;

import object.Coin;
import object.entity.AggressiveCreature;
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

        gp.neutralCreatures[0] = new NeutralCreature(gp);
        gp.neutralCreatures[0].worldX = 18 * gp.tileSize;
        gp.neutralCreatures[0].worldY = 3 * gp.tileSize;

        gp.neutralCreatures[1] = new NeutralCreature(gp);
        gp.neutralCreatures[1].worldX = 44 * gp.tileSize;
        gp.neutralCreatures[1].worldY = 44 * gp.tileSize;

        gp.neutralCreatures[2] = new NeutralCreature(gp);
        gp.neutralCreatures[2].worldX = 21 * gp.tileSize;
        gp.neutralCreatures[2].worldY = 25 * gp.tileSize;

        gp.aggressiveCreatures[0] = new AggressiveCreature(gp, 1);
        gp.aggressiveCreatures[0].worldX = 27 * gp.tileSize;
        gp.aggressiveCreatures[0].worldY = 10 * gp.tileSize;

        gp.aggressiveCreatures[1] = new AggressiveCreature(gp, 1);
        gp.aggressiveCreatures[1].worldX = 41 * gp.tileSize;
        gp.aggressiveCreatures[1].worldY = 33 * gp.tileSize;

        gp.aggressiveCreatures[2] = new AggressiveCreature(gp, 1);
        gp.aggressiveCreatures[2].worldX = 28 * gp.tileSize;
        gp.aggressiveCreatures[2].worldY = 40 * gp.tileSize;

        gp.aggressiveCreatures[3] = new AggressiveCreature(gp, 2);
        gp.aggressiveCreatures[3].worldX = 25 * gp.tileSize;
        gp.aggressiveCreatures[3].worldY = 15 * gp.tileSize;

        gp.aggressiveCreatures[4] = new AggressiveCreature(gp, 2);
        gp.aggressiveCreatures[4].worldX = 47 * gp.tileSize;
        gp.aggressiveCreatures[4].worldY = 21 * gp.tileSize;

        gp.aggressiveCreatures[5] = new AggressiveCreature(gp, 3);
        gp.aggressiveCreatures[5].worldX = 33 * gp.tileSize;
        gp.aggressiveCreatures[5].worldY = 31 * gp.tileSize;

        gp.smallCreatures[0] = new SmallCreature(gp);
        gp.smallCreatures[0].worldX = 23 * gp.tileSize;
        gp.smallCreatures[0].worldY = 10 * gp.tileSize;

        gp.smallCreatures[1] = new SmallCreature(gp);
        gp.smallCreatures[1].worldX = 22 * gp.tileSize;
        gp.smallCreatures[1].worldY = 28 * gp.tileSize;

        gp.smallCreatures[2] = new SmallCreature(gp);
        gp.smallCreatures[2].worldX = 42 * gp.tileSize;
        gp.smallCreatures[2].worldY = 39 * gp.tileSize;


    }


}
