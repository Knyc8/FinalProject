package Entities;

import ClientWindow.SwingWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    //setup
    public SwingWindow sw;
    public int xCoord, yCoord;  //represents the coordinates of the world as the player stays in the center

    public BufferedImage back1, back2, back3, front1, front2, front3,  left1, left2, left3, right1, right2, right3;
    public String direction;
    public static int spriteCount = 0;
    public static int spriteNum = 1;
    public Rectangle hitbox;
    public int hitboxDefaultX;
    public int hitboxDefaultY;
    public boolean collidable;
    public boolean colliding = false;
    public boolean shooting = false;
    public boolean alive;
    public boolean dying;
    public Projectile projectile;
//    public int actionCount;
    public boolean iFrame = false;
    public int iCount = 0;

    //stats
    public String name;
    public int speed;
    public int maxHp;
    public int hp;
    public int dmg;

    public BufferedImage setImage(String pathname) {
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResource(pathname));
        } catch (IOException ex) {
            ex.printStackTrace();;
        }
        return image;
    }

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

            if (iFrame)
            {
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            }
            graphics2D.drawImage(image, screenX, screenY, sw.getDISPLAYED_TILE_SIZE(), sw.getDISPLAYED_TILE_SIZE(), null);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public void action() {}

    public void setDefaultValues() {
    }

    public void update() {
        this.action();

        colliding = false;
        sw.getCollisionDetector().detectTile(this);
        Boolean detected = false;
        if (!(this instanceof Projectile))
        {
            detected = sw.getCollisionDetector().detectPlayer(this);
        }

        if (detected) {  //deals dmg to player
            if (!sw.getPlayer().iFrame) {
                sw.getPlayer().hp--;
                sw.getPlayer().iFrame = true;
            }
        }


        if (!colliding) {
            if (direction.equals("back")) {  //up speed units
                yCoord -= (int)(speed * Math.random()*2 + 1);  //top left is (0, 0)
            }
            if (direction.equals("front")) {  //down speed units
                yCoord += (int)(speed * Math.random()*2 + 1);
            }
            if (direction.equals("left")) {  //left speed units
                xCoord -= (int)(speed * Math.random()*2 + 1);
            }
            if (direction.equals("right")) {  //right speed units
                xCoord += (int)(speed * Math.random()*2 + 1);
            }
        }

        this.spriteCount++;
        if (this.spriteCount > 60) {
            if (this.spriteNum == 1) {
                this.spriteNum = 2;
            } else if (this.spriteNum == 2) {
                this.spriteNum = 1;
            }
            this.spriteCount = 0;
        }

        if (iFrame) {
            iCount++;
            if (iCount > 60) {
                iFrame = false;
                iCount = 0;
            }
        }
    }
}
