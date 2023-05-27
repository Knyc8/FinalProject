package Characters;

import ClientWindow.SwingWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Projectile extends Entity{
    Entity user;

    public Projectile(SwingWindow sw) {
        super(sw);

        hitbox = new Rectangle(24, 60, 45, 30);
        name = "fireball";
        speed = 10;
        maxHp = 45;
        hp = maxHp;
        dmg = 1;
        alive = false;
        getImg();
    }

    public void getImg() {
        try {
            back1 = ImageIO.read(getClass().getResource("/projectiles/pigball_up_1.png"));
            back2 = ImageIO.read(getClass().getResource("/projectiles/pigball_up_2.png"));
            front1 = ImageIO.read(getClass().getResource("/projectiles/pigball_down_1.png"));
            front2 = ImageIO.read(getClass().getResource("/projectiles/pigball_down_2.png"));
            left1 = ImageIO.read(getClass().getResource("/projectiles/pigball_left_1.png"));
            left2 = ImageIO.read(getClass().getResource("/projectiles/pigball_left_2.png"));
            right1 = ImageIO.read(getClass().getResource("/projectiles/pigball_right_1.png"));
            right2 = ImageIO.read(getClass().getResource("/projectiles/pigball_right_2.png"));
            front3 = ImageIO.read(getClass().getResource("/projectiles/explosion.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
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
        if (user == sw.getPlayer()) {

        }
//        if (user != sw.getPlayer()) { for enemies with projectiles in the future
//
//        }

        super.update();

        spriteCount++;
        if (spriteCount > 5) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCount = 0;
        }

        if (colliding == true || hp < 5) {
            speed = 0;
        }
        else {
            speed = 15;
        }

        hp--;
        if (hp <= 0) {
            alive = false;
        }
    }

    public void draw(Graphics2D graphics2D) {
        int screenX = xCoord - sw.getPlayer().xCoord + sw.getPlayer().SCREEN_X;
        int screenY = yCoord - sw.getPlayer().yCoord + sw.getPlayer().SCREEN_Y + sw.getDISPLAYED_TILE_SIZE()/5;
        int size = sw.getDISPLAYED_TILE_SIZE();

        if (xCoord + sw.getDISPLAYED_TILE_SIZE() > sw.getPlayer().xCoord - sw.getPlayer().SCREEN_X &&
                xCoord - sw.getDISPLAYED_TILE_SIZE() < sw.getPlayer().xCoord + sw.getPlayer().SCREEN_X &&
                yCoord + sw.getDISPLAYED_TILE_SIZE() > sw.getPlayer().yCoord - sw.getPlayer().SCREEN_Y &&
                yCoord - sw.getDISPLAYED_TILE_SIZE() < sw.getPlayer().yCoord + sw.getPlayer().SCREEN_Y) {

            BufferedImage image = null;

            if (colliding == true || hp < 5)
            {
                image = front3;
            }
            else {
                switch(direction) {
                    case "back":
                        if (spriteNum == 1) {
                            image = back1;
                        }
                        if (spriteNum == 2) {
                            image = back2;
                        }
                        break;
                    case "front":
                        if (spriteNum == 1) {
                            image = front1;
                        }
                        if (spriteNum == 2) {
                            image = front2;
                        }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                        break;
                        case "right":
                            if (spriteNum == 1) {
                                image = right1;
                            }
                            if (spriteNum == 2) {
                                image = right2;
                            }
                }
            }

            graphics2D.drawImage(image, screenX, screenY, size, size, null);
        }
    }
}
