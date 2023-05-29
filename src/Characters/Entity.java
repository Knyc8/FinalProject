package Characters;

import ClientWindow.SwingWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    //setup
    SwingWindow sw;
    public int xCoord, yCoord;  //represents the coordinates of the world as the player stays in the center

    public BufferedImage back1, back2, back3, front1, front2, front3,  left1, left2, left3, right1, right2, right3;
    public String direction;
    public static int spriteCount = 0;
    public static int spriteNum = 1;
    public Rectangle hitbox;
    public boolean colliding = false;
    public boolean alive;
    public boolean dying;
    public Projectile projectile;

    //stats
    public String name;
    public int speed;
    public int maxHp;
    public int hp;
    public int dmg;

    public Entity (SwingWindow sw) {
        this.sw = sw;
    }
    public void draw(Graphics2D graphics2D) {
        int screenX = xCoord - sw.getPlayer().xCoord + sw.getPlayer().SCREEN_X;
        int screenY = yCoord - sw.getPlayer().yCoord + sw.getPlayer().SCREEN_Y;

        if (xCoord + sw.getDISPLAYED_TILE_SIZE() > sw.getPlayer().xCoord - sw.getPlayer().SCREEN_X &&
                xCoord - sw.getDISPLAYED_TILE_SIZE() < sw.getPlayer().xCoord + sw.getPlayer().SCREEN_X &&
                yCoord + sw.getDISPLAYED_TILE_SIZE() > sw.getPlayer().yCoord - sw.getPlayer().SCREEN_Y &&
                yCoord - sw.getDISPLAYED_TILE_SIZE() < sw.getPlayer().yCoord + sw.getPlayer().SCREEN_Y) {

            BufferedImage image = null;

            switch(direction)
            {
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
                    if (spriteNum == 2)
                    {
                        image = right2;
                    }
                    break;
            }

            graphics2D.drawImage(image, screenX, screenY, sw.getDISPLAYED_TILE_SIZE(), sw.getDISPLAYED_TILE_SIZE(), null);
        }
    }

    public void update() {
        colliding = false;
        sw.getCollisionDetector().detectTile(this);
        if (colliding == false) {
            if (direction.equals("back")) {  //up speed units
                yCoord -= speed;  //top left is (0, 0)
            }
            if (direction.equals("front")) {  //down speed units
                yCoord += speed;
            }
            if (direction.equals("left")) {  //left speed units
                xCoord -= speed;
            }
            if (direction.equals("right")) {  //right speed units
                xCoord += speed;
            }
        }
    }
}
