package Characters;

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
        int hitBoxLeftX = entity.xCoord + entity.hitbox.x;
        int hitBoxRightX = entity.xCoord + entity.hitbox.x + entity.hitbox.width;
        int hitBoxTopY = entity.yCoord + entity.hitbox.y;
        int hitBoxBottomY = entity.yCoord + entity.hitbox.y + entity.hitbox.height;

        int hitBoxLeftSide = hitBoxLeftX/sw.getDISPLAYED_TILE_SIZE();
        int hitBoxRightSide = hitBoxRightX/sw.getDISPLAYED_TILE_SIZE();
        int hitBoxTopSide = hitBoxTopY/sw.getDISPLAYED_TILE_SIZE();
        int hitBoxBottomSide = hitBoxBottomY/sw.getDISPLAYED_TILE_SIZE();

        int tile1, tile2;

        switch (entity.direction) {
            case "front":
                hitBoxBottomSide = (hitBoxBottomY + entity.speed)/sw.getDISPLAYED_TILE_SIZE();
                tile1 = sw.getTileMapper().tileNum[hitBoxBottomSide][hitBoxLeftSide];  //bottom left corner
                tile2 = sw.getTileMapper().tileNum[hitBoxBottomSide][hitBoxRightSide];  //bottom right corner
                if (sw.getTileMapper().tiles[tile1].collision == true || sw.getTileMapper().tiles[tile2].collision == true)
                {
                    entity.colliding = true;
                }
                break;
            case "back":
                hitBoxTopSide = (hitBoxTopY - entity.speed)/sw.getDISPLAYED_TILE_SIZE();
                tile1 = sw.getTileMapper().tileNum[hitBoxTopSide][hitBoxLeftSide];  //top left corner
                tile2 = sw.getTileMapper().tileNum[hitBoxTopSide][hitBoxRightSide];  //top right corner
                if (sw.getTileMapper().tiles[tile1].collision == true || sw.getTileMapper().tiles[tile2].collision == true)
                {
                    entity.colliding = true;
                }
                break;
            case "left":
                hitBoxLeftSide = (hitBoxLeftX - entity.speed)/sw.getDISPLAYED_TILE_SIZE();
                tile1 = sw.getTileMapper().tileNum[hitBoxTopSide][hitBoxLeftSide];  //top left corner
                tile2 = sw.getTileMapper().tileNum[hitBoxBottomSide][hitBoxLeftSide];  //bottom left corner
                if (sw.getTileMapper().tiles[tile1].collision == true || sw.getTileMapper().tiles[tile2].collision == true)
                {
                    entity.colliding = true;
                }
                break;
            case "right":
                hitBoxRightSide = (hitBoxRightX + entity.speed)/sw.getDISPLAYED_TILE_SIZE();
                tile1 = sw.getTileMapper().tileNum[hitBoxTopSide][hitBoxRightSide];  //top right corner
                tile2 = sw.getTileMapper().tileNum[hitBoxBottomSide][hitBoxRightSide];  //bottom right corner
                if (sw.getTileMapper().tiles[tile1].collision == true || sw.getTileMapper().tiles[tile2].collision == true)
                {
                    entity.colliding = true;
                }
                break;
        }
    }
}
