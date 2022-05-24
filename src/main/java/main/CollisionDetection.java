package main;

import object.entity.Entity;


public class CollisionDetection {

    GamePanel gp;

    public CollisionDetection(GamePanel gp) {
        this.gp = gp;
    }

    // sprawdzenie kolizji z terenem
    public void checkTile(Entity entity) {

        // współrzędne granic hitboxu
        int entityLeftWorldX = entity.worldX + entity.hitBox.x;
        int entityRightWorldX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
        int entityUpWorldY = entity.worldY + entity.hitBox.y;
        int entityDownWorldY = entity.worldY + entity.hitBox.y + entity.hitBox.height;

        int entityLeftColumn = entityLeftWorldX / gp.tileSize;
        int entityRightColumn = entityRightWorldX / gp.tileSize;
        int entityUpRow = entityUpWorldY / gp.tileSize;
        int entityDownRow = entityDownWorldY / gp.tileSize;

        int tileNumber1, tileNumber2;


        switch (entity.direction) {
            case "up" -> {
                entityUpRow = (entityUpWorldY - entity.speed) / gp.tileSize;
                tileNumber1 = gp.tileManager.mapTileNumbers[entityLeftColumn][entityUpRow];
                tileNumber2 = gp.tileManager.mapTileNumbers[entityRightColumn][entityUpRow];
                if (gp.tileManager.tiles[tileNumber1].collision || gp.tileManager.tiles[tileNumber2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftColumn = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNumber1 = gp.tileManager.mapTileNumbers[entityLeftColumn][entityUpRow];
                tileNumber2 = gp.tileManager.mapTileNumbers[entityLeftColumn][entityDownRow];
                if (gp.tileManager.tiles[tileNumber1].collision || gp.tileManager.tiles[tileNumber2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entityDownRow = (entityDownWorldY + entity.speed) / gp.tileSize;
                tileNumber1 = gp.tileManager.mapTileNumbers[entityLeftColumn][entityDownRow];
                tileNumber2 = gp.tileManager.mapTileNumbers[entityRightColumn][entityDownRow];
                if (gp.tileManager.tiles[tileNumber1].collision || gp.tileManager.tiles[tileNumber2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightColumn = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNumber1 = gp.tileManager.mapTileNumbers[entityRightColumn][entityUpRow];
                tileNumber2 = gp.tileManager.mapTileNumbers[entityRightColumn][entityDownRow];
                if (gp.tileManager.tiles[tileNumber1].collision || gp.tileManager.tiles[tileNumber2].collision) {
                    entity.collisionOn = true;
                }
            }
        }

    }

    // sprawdzenie kolizji z monetą
    public int checkCoin(Entity entity) {

        int index = 999;

        for (int i = 0; i < gp.coins.length; i++) {
            if (gp.coins[i] != null) {
                entity.hitBox.x += entity.worldX;
                entity.hitBox.y += entity.worldY;

                gp.coins[i].hitBox.x += gp.coins[i].worldX;
                gp.coins[i].hitBox.y += gp.coins[i].worldY;

                switch (entity.direction) {
                    case "up" -> {
                        entity.hitBox.y -= entity.speed;
                        if (entity.hitBox.intersects(gp.coins[i].hitBox)) {
                            if (gp.coins[i].collision) entity.collisionOn = true;
                            index = i;
                        }
                    }
                    case "left" -> {
                        entity.hitBox.x -= entity.speed;
                        if (entity.hitBox.intersects(gp.coins[i].hitBox)) {
                            if (gp.coins[i].collision) entity.collisionOn = true;
                            index = i;
                        }
                    }
                    case "down" -> {
                        entity.hitBox.y += entity.speed;
                        if (entity.hitBox.intersects(gp.coins[i].hitBox)) {
                            if (gp.coins[i].collision) entity.collisionOn = true;
                            index = i;
                        }
                    }
                    case "right" -> {
                        entity.hitBox.x += entity.speed;
                        if (entity.hitBox.intersects(gp.coins[i].hitBox)) {
                            if (gp.coins[i].collision) entity.collisionOn = true;
                            index = i;
                        }
                    }
                }
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                gp.coins[i].hitBox.x = gp.coins[i].hitBoxDefaultX;
                gp.coins[i].hitBox.y = gp.coins[i].hitBoxDefaultY;
            }
        }

        return index;
    }

    // sprawdzenie kolizji ze stworzeniem
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null && target[i] != entity) {
                entity.hitBox.x += entity.worldX;
                entity.hitBox.y += entity.worldY;

                target[i].hitBox.x += target[i].worldX;
                target[i].hitBox.y += target[i].worldY;

                switch (entity.direction) {
                    case "up" -> {
                        entity.hitBox.y -= entity.speed;
                        if (entity.hitBox.intersects(target[i].hitBox)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                    }
                    case "left" -> {
                        entity.hitBox.x -= entity.speed;
                        if (entity.hitBox.intersects(target[i].hitBox)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                    }
                    case "down" -> {
                        entity.hitBox.y += entity.speed;
                        if (entity.hitBox.intersects(target[i].hitBox)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                    }
                    case "right" -> {
                        entity.hitBox.x += entity.speed;
                        if (entity.hitBox.intersects(target[i].hitBox)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                    }
                }
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                target[i].hitBox.x = target[i].hitBoxDefaultX;
                target[i].hitBox.y = target[i].hitBoxDefaultY;
            }
        }

        return index;
    }

    // sprawdzenie kolizji z graczem
    public void checkPlayer(Entity entity) {
        entity.hitBox.x += entity.worldX;
        entity.hitBox.y += entity.worldY;

        gp.player.hitBox.x += gp.player.worldX;
        gp.player.hitBox.y += gp.player.worldY;


        switch (entity.direction) {
            case "up" -> {
                entity.hitBox.y -= entity.speed;
                if (entity.hitBox.intersects(gp.player.hitBox)) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entity.hitBox.x -= entity.speed;
                if (entity.hitBox.intersects(gp.player.hitBox)) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entity.hitBox.y += entity.speed;
                if (entity.hitBox.intersects(gp.player.hitBox)) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entity.hitBox.x += entity.speed;
                if (entity.hitBox.intersects(gp.player.hitBox)) {
                    entity.collisionOn = true;
                }
            }
        }
        entity.hitBox.x = entity.hitBoxDefaultX;
        entity.hitBox.y = entity.hitBoxDefaultY;
        gp.player.hitBox.x = gp.player.hitBoxDefaultX;
        gp.player.hitBox.y = gp.player.hitBoxDefaultY;
    }
}
