package Entities.enemies;

import Dungeon.DungeonPlacer;
import Entities.Entity;
import ClientWindow.SwingWindow;
import Entities.Projectile;

import java.awt.*;

public class MilesMM extends Entity {
    public int room;
    public MilesMM(SwingWindow sw, int r) {
        super(sw);

        room = r;
        setDefaultValues();

        getImg();
    }

    public void setDefaultValues() {
        name = "Miles the Magical Mouse";
        speed = 5;
        maxHp = 2;
        hp = maxHp;
        collidable = true;

        int rand = (int) (Math.random()*4) + 1;  //random number from 1-4
        if (rand == 1) {
            direction = "back";
        }
        if (rand == 2) {
            direction = "front";
        }
        if (rand == 3) {
            direction = "left";
        }
        if (rand == 4) {
            direction = "right";
        }

        hitbox = new Rectangle();
        hitbox.x = 30;
        hitbox.y = 36;
        hitbox.width = 36;
        hitbox.height =30;
        hitboxDefaultX = 30;
        hitboxDefaultY = 36;
    }

    public void getImg() {
        back1 = setImage("/enemy_sprites/MMM_b1.png");
        back2 = setImage("/enemy_sprites/MMM_b2.png");
        back3 = setImage("/enemy_sprites/MMM_b3.png");
        front1 = setImage("/enemy_sprites/MMM_f1.png");
        front2 = setImage("/enemy_sprites/MMM_f2.png");
        front3 = setImage("/enemy_sprites/MMM_f3.png");
        left1 = setImage("/enemy_sprites/MMM_l1.png");
        left2 = setImage("/enemy_sprites/MMM_l2.png");
        left3 = setImage("/enemy_sprites/MMM_l3.png");
        right1 = setImage("/enemy_sprites/MMM_r1.png");
        right2 = setImage("/enemy_sprites/MMM_r2.png");
        right3 = setImage("/enemy_sprites/MMM_r3.png");
    }

    public void action() {
        actionCount++;

        if (actionCount == 120) {
            int rand = (int) (Math.random()*4) + 1;  //random number from 1-4

            if (rand == 1) {
                direction = "back";
            }
            if (rand == 2) {
                direction = "front";
            }
            if (rand == 3) {
                direction = "left";
            }
            if (rand == 4) {
                direction = "right";
            }
            actionCount = 0;
        }
    }

    public void update() {
//        super.update();

        restrainBounds();
    }

    public void restrainBounds(){
//        if (xCoord < sw.getDISPLAYED_TILE_SIZE() * sw.dungeonPlacer.roomInfo[room-1][0]) {
//            xCoord = sw.getDISPLAYED_TILE_SIZE() * sw.dungeonPlacer.roomInfo[room-1][0];
//        }
//        if (xCoord > sw.getDISPLAYED_TILE_SIZE() * sw.dungeonPlacer.roomInfo[room-1][1]) {
//            xCoord = sw.getDISPLAYED_TILE_SIZE() * sw.dungeonPlacer.roomInfo[room-1][1];
//        }
//        if (yCoord < sw.getDISPLAYED_TILE_SIZE() * sw.dungeonPlacer.roomInfo[room-1][2]) {
//            yCoord = sw.getDISPLAYED_TILE_SIZE() * sw.dungeonPlacer.roomInfo[room-1][2];
//        }
//        if (yCoord > sw.getDISPLAYED_TILE_SIZE() * sw.dungeonPlacer.roomInfo[room-1][3]) {
//            yCoord = sw.getDISPLAYED_TILE_SIZE() * sw.dungeonPlacer.roomInfo[room-1][3];
//        }
    }
}
