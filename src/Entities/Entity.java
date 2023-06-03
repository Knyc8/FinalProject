package Entities;

import ClientWindow.SwingWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    //setup
    private final SwingWindow sw;
    private int xCoord, yCoord;  //represents the coordinates of the world as the player stays in the center

    public BufferedImage back1, back2, back3, front1, front2, front3,  left1, left2, left3, right1, right2, right3;
    private String direction;
    public static int spriteCount = 0;
    public static int spriteNum = 1;
    private Rectangle hitbox;
    private int hitboxDefaultX;
    private int hitboxDefaultY;
    private boolean collidable;
    private boolean colliding;
    private boolean alive;
    private boolean dying;
    public Projectile projectile;
    Projectile projectile2;
    Projectile projectile3;
    public boolean immunity = false;
    public int iCount = 0;

    //stats
    private String name;  //future purposes
    private int speed;
    private int maxHp;
    private int hp;
    private int dmg;  //future purposes

    //Constructor
    public Entity (SwingWindow sw) {
        this.sw = sw;

        colliding = false;
        immunity = false;
    }


    //Getters
    public SwingWindow getSw() {
        return sw;
    }
    public int getXCoord() {
        return xCoord;
    }
    public int getYCoord() {
        return yCoord;
    }
    public String getDirection() {
        return direction;
    }
    public Rectangle getHitbox() {
        return hitbox;
    }
    public int getHitboxDefaultX() {
        return hitboxDefaultX;
    }
    public int getHitboxDefaultY() {
        return hitboxDefaultY;
    }
    public boolean isCollidable() {
        return collidable;
    }
    public boolean isColliding() {
        return colliding;
    }
    public boolean isAlive() {
        return alive;
    }
    public boolean isDying() {
        return dying;
    }
    public int getSpeed() {
        return speed;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public int getHp() {
        return hp;
    }


    //Setters
    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }
    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
    public void setHitboxDefaultX(int hitboxDefaultX) {
        this.hitboxDefaultX = hitboxDefaultX;
    }
    public void setHitboxDefaultY(int hitboxDefaultY) {
        this.hitboxDefaultY = hitboxDefaultY;
    }
    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }
    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public void setDying(boolean dying) {
        this.dying = dying;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
    public BufferedImage setImage(String pathname) {
        BufferedImage image = null;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource(pathname)));
        } catch (IOException ex) {
            ex.printStackTrace();;
        }
        return image;
    }
    public void setDefaultValues() {
        /*unused for inheritance*/
    }


    //Other methods
    public void draw(Graphics2D graphics2D) {
        int screenX = xCoord - sw.getPlayer().getXCoord() + sw.getPlayer().getSCREEN_X();
        int screenY = yCoord - sw.getPlayer().getYCoord() + sw.getPlayer().getSCREEN_Y();

        if (xCoord + sw.getDISPLAYED_TILE_SIZE() > sw.getPlayer().getXCoord() - sw.getPlayer().getSCREEN_X() &&
                xCoord - sw.getDISPLAYED_TILE_SIZE() < sw.getPlayer().getXCoord() + sw.getPlayer().getSCREEN_X() &&
                yCoord + sw.getDISPLAYED_TILE_SIZE() > sw.getPlayer().getYCoord() - sw.getPlayer().getSCREEN_Y() &&
                yCoord - sw.getDISPLAYED_TILE_SIZE() < sw.getPlayer().getYCoord() + sw.getPlayer().getSCREEN_Y()) {

            BufferedImage image = null;

            switch (direction) {
                case "north" -> {
                    if (spriteNum == 1) {
                        image = back1;
                    }
                    if (spriteNum == 2) {
                        image = back2;
                    }
                }
                case "south" -> {
                    if (spriteNum == 1) {
                        image = front1;
                    }
                    if (spriteNum == 2) {
                        image = front2;
                    }
                }
                case "west" -> {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                case "east" -> {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
            }

            if (immunity)
            {
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            }
            graphics2D.drawImage(image, screenX, screenY, sw.getDISPLAYED_TILE_SIZE(), sw.getDISPLAYED_TILE_SIZE(), null);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public void action() {/*unused for polymorphism*/}

    public void update() {
        this.action();

        colliding = false;
        sw.getCollisionDetector().detectTile(this);
        boolean detected = false;
        if (!(this instanceof Projectile))
        {
            detected = sw.getCollisionDetector().detectPlayer(this);
        }

        if (detected) {  //deals dmg to player
            if (!sw.getPlayer().immunity) {
                sw.getPlayer().setHp(sw.getPlayer().getHp()-1);
                sw.getPlayer().immunity = true;
            }
        }

        if (!colliding) {
            if (direction.equals("north")) {  //up speed units
                yCoord -= speed;  //top left is (0, 0)
            }
            if (direction.equals("south")) {  //down speed units
                yCoord += speed;
            }
            if (direction.equals("west")) {  //left speed units
                xCoord -= speed;
            }
            if (direction.equals("east")) {  //right speed units
                xCoord += speed;
            }
        }


        if (immunity) {
            iCount++;
            if (iCount > 60) {
                immunity = false;
                iCount = 0;
            }
        }
    }
}
