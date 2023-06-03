package Entities.enemies;

import Entities.Entity;
import ClientWindow.SwingWindow;

import java.awt.*;

public class Enemy extends Entity {
    //VARIABLES
//    private int room;  //future implementation
    private int actionCount;

    //CONSTRUCTOR
    public Enemy(SwingWindow sw) {
        super(sw);

//        room = r;  //future implementation
        setDefaultValues();

        getImg();
    }


    //GETTERS
    public void getImg() {
        setBack1(setImage("/enemy_sprites/MMM_b1.png"));
        setBack2(setImage("/enemy_sprites/MMM_b2.png"));
        setBack3(setImage("/enemy_sprites/MMM_b3.png"));
        setFront1(setImage("/enemy_sprites/MMM_f1.png"));
        setFront2(setImage("/enemy_sprites/MMM_f2.png"));
        setFront3(setImage("/enemy_sprites/MMM_f3.png"));
        setLeft1(setImage("/enemy_sprites/MMM_l1.png"));
        setLeft2(setImage("/enemy_sprites/MMM_l2.png"));
        setLeft3(setImage("/enemy_sprites/MMM_l3.png"));
        setRight1(setImage("/enemy_sprites/MMM_r1.png"));
        setRight2(setImage("/enemy_sprites/MMM_r2.png"));
        setRight3(setImage("/enemy_sprites/MMM_r3.png"));
    }


    //SETTERS
    public void setDefaultValues() {
        setName("Miles the Magical Mouse");
        setSpeed(8);
        setMaxHp(1);
        setHp(getMaxHp());
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

        setHitbox(new Rectangle(30, 36, 36, 30));
        setHitboxDefaultX(30);
        setHitboxDefaultY(36);
    }


    //OTHER METHODS
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
