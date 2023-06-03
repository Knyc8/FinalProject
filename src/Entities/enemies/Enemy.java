package Entities.enemies;

import Entities.Entity;
import ClientWindow.SwingWindow;

import java.awt.*;

public class Enemy extends Entity {
//    private int room;  //future implementation
    int actionCount;
    public Enemy(SwingWindow sw) {
        super(sw);

//        room = r;  //future implementation
        setDefaultValues();

        getImg();
    }

    public void setDefaultValues() {
        setName("Miles the Magical Mouse");
        setSpeed(8);
        setMaxHp(1);
        setHp(getMaxHp());
        collidable = true;
        actionCount = 0;

        int rand = (int) (Math.random()*4) + 1;  //random number from 1-4
        if (rand == 1) {
            setDirection("north");
        }
        if (rand == 2) {
            setDirection("south");
        }
        if (rand == 3) {
            setDirection("west");
        }
        if (rand == 4) {
            setDirection("east");
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

        if (actionCount == 30) {
            int rand = (int) (Math.random()*4) + 1;  //random number from 1-4

            if (rand == 1) {
                setDirection("north");
            }
            if (rand == 2) {
                setDirection("south");
            }
            if (rand == 3) {
                setDirection("west");
            }
            if (rand == 4) {
                setDirection("east");
            }
            actionCount = 0;
        }
    }

    public void update() {
        super.update();

        restrainBounds();
        spriteCount++;
        if (spriteCount > 1500) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCount = 0;
        }
    }

    public void restrainBounds(){
        if (getYCoord() < getSw().getDISPLAYED_TILE_SIZE() * 6)
        {
            if (getXCoord() < getSw().getDISPLAYED_TILE_SIZE() * 17)
            {
                setXCoord(getSw().getDISPLAYED_TILE_SIZE() * 17);
            }
        }
        if (getXCoord() < getSw().getDISPLAYED_TILE_SIZE() * 13)
            if (getYCoord() < getSw().getDISPLAYED_TILE_SIZE() * 8)
            {
                setYCoord(getSw().getDISPLAYED_TILE_SIZE() * 8);
            }
    }
}
