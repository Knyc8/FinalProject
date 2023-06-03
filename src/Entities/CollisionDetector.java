package Entities;

import ClientWindow.SwingWindow;

/***
 * Handles the collisions betweens entity hitboxes and tiles
 */
public class CollisionDetector {
    SwingWindow sw;

    public CollisionDetector(SwingWindow sw) {
        this.sw = sw;
    }

    public void detectTile(Entity entity) {
        int hitBoxLeftX = entity.getXCoord() + entity.hitbox.x;
        int hitBoxRightX = entity.getXCoord() + entity.hitbox.x + entity.hitbox.width;
        int hitBoxTopY = entity.getYCoord() + entity.hitbox.y;
        int hitBoxBottomY = entity.getYCoord() + entity.hitbox.y + entity.hitbox.height;

        int hitBoxLeftSide = hitBoxLeftX/sw.getDISPLAYED_TILE_SIZE();
        int hitBoxRightSide = hitBoxRightX/sw.getDISPLAYED_TILE_SIZE();
        int hitBoxTopSide = hitBoxTopY/sw.getDISPLAYED_TILE_SIZE();
        int hitBoxBottomSide = hitBoxBottomY/sw.getDISPLAYED_TILE_SIZE();

        int tile1, tile2;

        switch (entity.direction) {
            case "south" -> {
                hitBoxBottomSide = (hitBoxBottomY + entity.getSpeed()) / sw.getDISPLAYED_TILE_SIZE();
                tile1 = sw.getTileMapper().tileNum[hitBoxBottomSide][hitBoxLeftSide];  //bottom left corner
                tile2 = sw.getTileMapper().tileNum[hitBoxBottomSide][hitBoxRightSide];  //bottom right corner
                if (sw.getTileMapper().tiles[tile1].collision || sw.getTileMapper().tiles[tile2].collision) {
                    entity.colliding = true;
                }
            }
            case "north" -> {
                hitBoxTopSide = (hitBoxTopY - entity.getSpeed()) / sw.getDISPLAYED_TILE_SIZE();
                tile1 = sw.getTileMapper().tileNum[hitBoxTopSide][hitBoxLeftSide];  //top left corner
                tile2 = sw.getTileMapper().tileNum[hitBoxTopSide][hitBoxRightSide];  //top right corner
                if (sw.getTileMapper().tiles[tile1].collision || sw.getTileMapper().tiles[tile2].collision) {
                    entity.colliding = true;
                }
            }
            case "west" -> {
                hitBoxLeftSide = (hitBoxLeftX - entity.getSpeed()) / sw.getDISPLAYED_TILE_SIZE();
                tile1 = sw.getTileMapper().tileNum[hitBoxTopSide][hitBoxLeftSide];  //top left corner
                tile2 = sw.getTileMapper().tileNum[hitBoxBottomSide][hitBoxLeftSide];  //bottom left corner
                if (sw.getTileMapper().tiles[tile1].collision || sw.getTileMapper().tiles[tile2].collision) {
                    entity.colliding = true;
                }
            }
            case "east" -> {
                hitBoxRightSide = (hitBoxRightX + entity.getSpeed()) / sw.getDISPLAYED_TILE_SIZE();
                tile1 = sw.getTileMapper().tileNum[hitBoxTopSide][hitBoxRightSide];  //top right corner
                tile2 = sw.getTileMapper().tileNum[hitBoxBottomSide][hitBoxRightSide];  //bottom right corner
                if (sw.getTileMapper().tiles[tile1].collision || sw.getTileMapper().tiles[tile2].collision) {
                    entity.colliding = true;
                }
            }
        }
    }

    public int detectEntity(Entity[] recipients, Entity entity) {
        int idx = -1;

        for (int i = 0; i < recipients.length; i++) {
            if (recipients[i] != null) {
                //Get entity hitbox
                entity.hitbox.x = entity.getXCoord() + entity.hitbox.x;
                entity.hitbox.y = entity.getYCoord() + entity.hitbox.y;

                //Get recipient hitbox
                recipients[i].hitbox.x = recipients[i].getXCoord() + recipients[i].hitbox.x;
                recipients[i].hitbox.y = recipients[i].getYCoord() + recipients[i].hitbox.y;

                switch (entity.direction) {
                    case "north" -> {
                        entity.hitbox.y -= entity.getSpeed();
                        if (entity.hitbox.intersects(recipients[i].hitbox)) {  //checks if the hitboxes are overlapping
                            entity.colliding = true;
                            idx = i;
                        }
                    }
                    case "south" -> {
                        entity.hitbox.y += entity.getSpeed();
                        if (entity.hitbox.intersects(recipients[i].hitbox)) {  //checks if the hitboxes are overlapping
                            entity.colliding = true;
                            idx = i;
                        }
                    }
                    case "west" -> {
                        entity.hitbox.x -= entity.getSpeed();
                        if (entity.hitbox.intersects(recipients[i].hitbox)) {  //checks if the hitboxes are overlapping
                            entity.colliding = true;
                            idx = i;
                        }
                    }
                    case "east" -> {
                        entity.hitbox.x += entity.getSpeed();
                        if (entity.hitbox.intersects(recipients[i].hitbox)) {  //checks if the hitboxes are overlapping
                            entity.colliding = true;
                            idx = i;
                        }
                    }
                }
                entity.hitbox.x = entity.hitboxDefaultX;
                entity.hitbox.y = entity.hitboxDefaultY;
                recipients[i].hitbox.x = recipients[i].hitboxDefaultX;
                recipients[i].hitbox.y = recipients[i].hitboxDefaultY;
            }
        }
        return idx;
    }

    public boolean detectPlayer(Entity entity) {
        boolean detected = false;
        //Get entity hitbox
        entity.hitbox.x = entity.getXCoord() + entity.hitbox.x;
        entity.hitbox.y = entity.getYCoord() + entity.hitbox.y;

        //Get player hitbox
        sw.getPlayer().hitbox.x = sw.getPlayer().getXCoord() + sw.getPlayer().hitbox.x;
        sw.getPlayer().hitbox.y = sw.getPlayer().getYCoord() + sw.getPlayer().hitbox.y;

        switch (entity.direction) {
            case "north" -> {
                entity.hitbox.y -= entity.getSpeed();
                if (entity.hitbox.intersects(sw.getPlayer().hitbox)) {  //checks if the hitboxes are overlapping
                    entity.colliding = true;
                    detected = true;
                }
            }
            case "south" -> {
                entity.hitbox.y += entity.getSpeed();
                if (entity.hitbox.intersects(sw.getPlayer().hitbox)) {  //checks if the hitboxes are overlapping
                    entity.colliding = true;
                    detected = true;
                }
            }
            case "west" -> {
                entity.hitbox.x -= entity.getSpeed();
                if (entity.hitbox.intersects(sw.getPlayer().hitbox)) {  //checks if the hitboxes are overlapping
                    entity.colliding = true;
                    detected = true;
                }
            }
            case "east" -> {
                entity.hitbox.x += entity.getSpeed();
                if (entity.hitbox.intersects(sw.getPlayer().hitbox)) {  //checks if the hitboxes are overlapping
                    entity.colliding = true;
                    detected = true;
                }
            }
        }
        entity.hitbox.x = entity.hitboxDefaultX;
        entity.hitbox.y = entity.hitboxDefaultY;
        sw.getPlayer().hitbox.x = sw.getPlayer().hitboxDefaultX;
        sw.getPlayer().hitbox.y = sw.getPlayer().hitboxDefaultY;

        return detected;
    }
}
