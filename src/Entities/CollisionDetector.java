package Entities;

import ClientWindow.SwingWindow;

/***
 * Handles the collisions betweens entity hitboxes and tiles
 */
public class CollisionDetector {
    //VARIABLES
    private SwingWindow sw;

    //CONSTRUCTOR
    public CollisionDetector(SwingWindow sw) {
        this.sw = sw;
    }

    //METHODS
    public void detectTile(Entity entity) {
        int hitBoxLeftX = entity.getXCoord() + entity.getHitbox().x;
        int hitBoxRightX = entity.getXCoord() + entity.getHitbox().x + entity.getHitbox().width;
        int hitBoxTopY = entity.getYCoord() + entity.getHitbox().y;
        int hitBoxBottomY = entity.getYCoord() + entity.getHitbox().y + entity.getHitbox().height;

        int hitBoxLeftSide = hitBoxLeftX/sw.getDISPLAYED_TILE_SIZE();
        int hitBoxRightSide = hitBoxRightX/sw.getDISPLAYED_TILE_SIZE();
        int hitBoxTopSide = hitBoxTopY/sw.getDISPLAYED_TILE_SIZE();
        int hitBoxBottomSide = hitBoxBottomY/sw.getDISPLAYED_TILE_SIZE();

        int tile1, tile2;

        switch (entity.getDirection()) {
            case "south" -> {
                hitBoxBottomSide = (hitBoxBottomY + entity.getSpeed()) / sw.getDISPLAYED_TILE_SIZE();
                tile1 = sw.getTileMapper().tileNum[hitBoxBottomSide][hitBoxLeftSide];  //bottom left corner
                tile2 = sw.getTileMapper().tileNum[hitBoxBottomSide][hitBoxRightSide];  //bottom right corner
                if (sw.getTileMapper().tiles[tile1].getCollision() || sw.getTileMapper().tiles[tile2].getCollision()) {
                    entity.setColliding(true);
                }
            }
            case "north" -> {
                hitBoxTopSide = (hitBoxTopY - entity.getSpeed()) / sw.getDISPLAYED_TILE_SIZE();
                tile1 = sw.getTileMapper().tileNum[hitBoxTopSide][hitBoxLeftSide];  //top left corner
                tile2 = sw.getTileMapper().tileNum[hitBoxTopSide][hitBoxRightSide];  //top right corner
                if (sw.getTileMapper().tiles[tile1].getCollision() || sw.getTileMapper().tiles[tile2].getCollision()) {
                    entity.setColliding(true);
                }
            }
            case "west" -> {
                hitBoxLeftSide = (hitBoxLeftX - entity.getSpeed()) / sw.getDISPLAYED_TILE_SIZE();
                tile1 = sw.getTileMapper().tileNum[hitBoxTopSide][hitBoxLeftSide];  //top left corner
                tile2 = sw.getTileMapper().tileNum[hitBoxBottomSide][hitBoxLeftSide];  //bottom left corner
                if (sw.getTileMapper().tiles[tile1].getCollision() || sw.getTileMapper().tiles[tile2].getCollision()) {
                    entity.setColliding(true);
                }
            }
            case "east" -> {
                hitBoxRightSide = (hitBoxRightX + entity.getSpeed()) / sw.getDISPLAYED_TILE_SIZE();
                tile1 = sw.getTileMapper().tileNum[hitBoxTopSide][hitBoxRightSide];  //top right corner
                tile2 = sw.getTileMapper().tileNum[hitBoxBottomSide][hitBoxRightSide];  //bottom right corner
                if (sw.getTileMapper().tiles[tile1].getCollision() || sw.getTileMapper().tiles[tile2].getCollision()) {
                    entity.setColliding(true);
                }
            }
        }
    }

    public int detectEntity(Entity[] recipients, Entity entity) {
        int idx = -1;

        for (int i = 0; i < recipients.length; i++) {
            if (recipients[i] != null) {
                //Get entity hitbox
                entity.getHitbox().x = entity.getXCoord() + entity.getHitbox().x;
                entity.getHitbox().y = entity.getYCoord() + entity.getHitbox().y;

                //Get recipient hitbox
                recipients[i].getHitbox().x = recipients[i].getXCoord() + recipients[i].getHitbox().x;
                recipients[i].getHitbox().y = recipients[i].getYCoord() + recipients[i].getHitbox().y;

                switch (entity.getDirection()) {
                    case "north" -> {
                        entity.getHitbox().y -= entity.getSpeed();
                        if (entity.getHitbox().intersects(recipients[i].getHitbox())) {  //checks if the hitboxes are overlapping
                            entity.setColliding(true);
                            idx = i;
                        }
                    }
                    case "south" -> {
                        entity.getHitbox().y += entity.getSpeed();
                        if (entity.getHitbox().intersects(recipients[i].getHitbox())) {  //checks if the hitboxes are overlapping
                            entity.setColliding(true);
                            idx = i;
                        }
                    }
                    case "west" -> {
                        entity.getHitbox().x -= entity.getSpeed();
                        if (entity.getHitbox().intersects(recipients[i].getHitbox())) {  //checks if the hitboxes are overlapping
                            entity.setColliding(true);
                            idx = i;
                        }
                    }
                    case "east" -> {
                        entity.getHitbox().x += entity.getSpeed();
                        if (entity.getHitbox().intersects(recipients[i].getHitbox())) {  //checks if the hitboxes are overlapping
                            entity.setColliding(true);
                            idx = i;
                        }
                    }
                }
                entity.getHitbox().x = entity.getHitboxDefaultX();
                entity.getHitbox().y = entity.getHitboxDefaultY();
                recipients[i].getHitbox().x = recipients[i].getHitboxDefaultX();
                recipients[i].getHitbox().y = recipients[i].getHitboxDefaultY();
            }
        }
        return idx;
    }

    public boolean detectPlayer(Entity entity) {
        boolean detected = false;
        //Get entity hitbox
        entity.getHitbox().x = entity.getXCoord() + entity.getHitbox().x;
        entity.getHitbox().y = entity.getYCoord() + entity.getHitbox().y;

        //Get player hitbox
        sw.getPlayer().getHitbox().x = sw.getPlayer().getXCoord() + sw.getPlayer().getHitbox().x;
        sw.getPlayer().getHitbox().y = sw.getPlayer().getYCoord() + sw.getPlayer().getHitbox().y;

        switch (entity.getDirection()) {
            case "north" -> {
                entity.getHitbox().y -= entity.getSpeed();
                if (entity.getHitbox().intersects(sw.getPlayer().getHitbox())) {  //checks if the hitboxes are overlapping
                    entity.setColliding(true);
                    detected = true;
                }
            }
            case "south" -> {
                entity.getHitbox().y += entity.getSpeed();
                if (entity.getHitbox().intersects(sw.getPlayer().getHitbox())) {  //checks if the hitboxes are overlapping
                    entity.setColliding(true);
                    detected = true;
                }
            }
            case "west" -> {
                entity.getHitbox().x -= entity.getSpeed();
                if (entity.getHitbox().intersects(sw.getPlayer().getHitbox())) {  //checks if the hitboxes are overlapping
                    entity.setColliding(true);
                    detected = true;
                }
            }
            case "east" -> {
                entity.getHitbox().x += entity.getSpeed();
                if (entity.getHitbox().intersects(sw.getPlayer().getHitbox())) {  //checks if the hitboxes are overlapping
                    entity.setColliding(true);
                    detected = true;
                }
            }
        }
        entity.getHitbox().x = entity.getHitboxDefaultX();
        entity.getHitbox().y = entity.getHitboxDefaultY();
        sw.getPlayer().getHitbox().x = sw.getPlayer().getHitboxDefaultX();
        sw.getPlayer().getHitbox().y = sw.getPlayer().getHitboxDefaultY();

        return detected;
    }
}
