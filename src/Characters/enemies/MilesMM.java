package Characters.enemies;

import Characters.Entity;
import ClientWindow.SwingWindow;

import java.awt.*;
import java.util.Random;

public class MilesMM extends Entity {
    public MilesMM(SwingWindow sw) {
        super(sw);

        name = "Miles the Magical Mouse";
        speed = 5;
        maxHp = 1;
        hp = maxHp;

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

        getImg();
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
}
