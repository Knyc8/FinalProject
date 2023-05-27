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
        speed = 15;
        maxHp = 30;
        hp = maxHp;
        dmg = 1;
        alive = false;
        getImg();
    }

    public void getImg() {
        try {
             back1 = ImageIO.read(getClass().getResource("/projectiles/fball_up.png"));
             front1 = ImageIO.read(getClass().getResource("/projectiles/fball_down.png"));
             left1 = ImageIO.read(getClass().getResource("/projectiles/fball_left.png"));
             right1 = ImageIO.read(getClass().getResource("/projectiles/fball_right.png"));
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

        hp--;
        if (hp <= 0) {
            alive = false;
        }
    }

    public void draw(Graphics2D graphics2D) {
        int screenX = xCoord - sw.getPlayer().xCoord + sw.getPlayer().SCREEN_X + sw.getDISPLAYED_TILE_SIZE()/4;
        int screenY = yCoord - sw.getPlayer().yCoord + sw.getPlayer().SCREEN_Y + sw.getDISPLAYED_TILE_SIZE()/3;
        int size = sw.getDISPLAYED_TILE_SIZE() / 2;

        if (xCoord + sw.getDISPLAYED_TILE_SIZE() > sw.getPlayer().xCoord - sw.getPlayer().SCREEN_X &&
                xCoord - sw.getDISPLAYED_TILE_SIZE() < sw.getPlayer().xCoord + sw.getPlayer().SCREEN_X &&
                yCoord + sw.getDISPLAYED_TILE_SIZE() > sw.getPlayer().yCoord - sw.getPlayer().SCREEN_Y &&
                yCoord - sw.getDISPLAYED_TILE_SIZE() < sw.getPlayer().yCoord + sw.getPlayer().SCREEN_Y) {

            BufferedImage image = null;

            if (colliding == true)
            {
                image = front3;
                size = sw.getDISPLAYED_TILE_SIZE();
                screenX -= sw.getDISPLAYED_TILE_SIZE()/4;
                screenY -= sw.getDISPLAYED_TILE_SIZE()/4;
            }
            else {
                switch (direction) {
                    case "back":
                        image = back1;
                        break;
                    case "front":
                        image = front1;
                        break;
                    case "left":
                        image = left1;
                        break;
                    case "right":
                        image = right1;
                        break;
                }
            }

            graphics2D.drawImage(image, screenX, screenY, size, size, null);
        }
    }
}
