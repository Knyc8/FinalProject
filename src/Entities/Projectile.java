package Entities;

import ClientWindow.SwingWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends Entity{
    Entity user;
    BufferedImage explosion1, explosion2;
    int spriteCount = 0;
    int spriteNum = 1;

    public Projectile(SwingWindow sw) {
        super(sw);

        hitbox = new Rectangle(15, 21, 66, 60);
        name = "fireball";
        speed = 15;
        maxHp = 45;
        hp = maxHp;
        dmg = 1;
        alive = false;
        collidable = true;
        getImg();
    }

    public void getImg() {
        back1 = setImage("/projectiles/pigball_up_1.png");
        back2 = setImage("/projectiles/pigball_up_2.png");
        front1 = setImage("/projectiles/pigball_down_1.png");
        front2 = setImage("/projectiles/pigball_down_2.png");
        left1 = setImage("/projectiles/pigball_left_1.png");
        left2 = setImage("/projectiles/pigball_left_2.png");
        right1 = setImage("/projectiles/pigball_right_1.png");
        right2 = setImage("/projectiles/pigball_right_2.png");
        explosion1 = setImage("/projectiles/explosion.png");
        explosion2 = setImage("/projectiles/explosion2.png");
    }

    public void set(int xCoord, int yCoord, String dir, Boolean alive, Entity user){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        direction = dir;
        this.alive = alive;
        this.user = user;
        this.hp = this.maxHp;
    }

    public void update() {

        super.update();
        int enemyIdx = sw.getCollisionDetector().detectEntity(sw.monsters, this);
        if (enemyIdx != -1) {
            sw.getPlayer().damage(enemyIdx);
        }

        hp--;
        if (hp <= 0) {
            alive = false;
        }

        this.spriteCount++;
        if (this.spriteCount > 2) {
            if (this.spriteNum == 1) {
                this.spriteNum = 2;
            } else if (this.spriteNum == 2) {
                this.spriteNum = 1;
            }
            this.spriteCount = 0;
        }
    }

    public void draw(Graphics2D graphics2D) {
        int screenX = xCoord - sw.getPlayer().xCoord + sw.getPlayer().SCREEN_X;
        int screenY = yCoord - sw.getPlayer().yCoord + sw.getPlayer().SCREEN_Y;// + sw.getDISPLAYED_TILE_SIZE()/5;
        int size = sw.getDISPLAYED_TILE_SIZE();

        if (xCoord + sw.getDISPLAYED_TILE_SIZE() > sw.getPlayer().xCoord - sw.getPlayer().SCREEN_X &&
                xCoord - sw.getDISPLAYED_TILE_SIZE() < sw.getPlayer().xCoord + sw.getPlayer().SCREEN_X &&
                yCoord + sw.getDISPLAYED_TILE_SIZE() > sw.getPlayer().yCoord - sw.getPlayer().SCREEN_Y &&
                yCoord - sw.getDISPLAYED_TILE_SIZE() < sw.getPlayer().yCoord + sw.getPlayer().SCREEN_Y) {

            BufferedImage image = null;

            if (colliding == true || hp < 10)
            {
                if (this.spriteNum == 1) {
                    image = explosion1;
                }
                if (this.spriteNum == 2) {
                    image = explosion2;
                }
                speed = 0;
            }
            else {
                speed = 15;
                switch(direction) {
                    case "north":
                        if (this.spriteNum == 1) {
                            image = back1;
                        }
                        if (this.spriteNum == 2) {
                            image = back2;
                        }
                        break;
                    case "south":
                        if (this.spriteNum == 1) {
                            image = front1;
                        }
                        if (this.spriteNum == 2) {
                            image = front2;
                        }
                        break;
                    case "west":
                        if (this.spriteNum == 1) {
                            image = left1;
                        }
                        if (this.spriteNum == 2) {
                            image = left2;
                        }
                        break;
                        case "east":
                            if (this.spriteNum == 1) {
                                image = right1;
                            }
                            if (this.spriteNum == 2) {
                                image = right2;
                            }
                }
            }

            graphics2D.drawImage(image, screenX, screenY, size, size, null);
            graphics2D.draw(hitbox);
        }
    }
}
