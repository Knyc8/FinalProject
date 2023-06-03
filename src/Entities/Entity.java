package Entities;

import ClientWindow.SwingWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    //VARIABLES
    //setup
    private final SwingWindow sw;
    private int xCoord, yCoord;  //represents the coordinates of the world as the player stays in the center

    private BufferedImage back1, back2, back3, front1, front2, front3,  left1, left2, left3, right1, right2, right3;
    private String direction;
    public static int spriteCount = 0;
    public static int spriteNum = 1;
    private Rectangle hitbox;
    private int hitboxDefaultX;
    private int hitboxDefaultY;
    private boolean colliding;
    private boolean alive;
    private boolean dying;
    private Projectile projectile;
    private Projectile projectile2;
    private Projectile projectile3;
    private boolean immune;
    private int iCount;

    //stats
    private String name;  //future purposes
    private int speed;
    private int maxHp;
    private int hp;
    private int dmg;  //future purposes

    //CONSTRUCTOR
    public Entity (SwingWindow sw) {
        this.sw = sw;

        colliding = false;
        immune = false;
        iCount = 0;
    }


    //GETTERS
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
    public boolean isColliding() {
        return colliding;
    }
    public boolean isAlive() {
        return alive;
    }
    public boolean isDying() {
        return dying;
    }
    public boolean isImmune() {
        return immune;
    }
    public int getICount() {
        return iCount;
    }
    public Projectile getProjectile() {
        return projectile;
    }
    public Projectile getProjectile2() {
        return projectile2;
    }
    public Projectile getProjectile3() {
        return projectile3;
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


    //IMAGE GETTERS
    public BufferedImage getBack1() {
        return back1;
    }
    public BufferedImage getBack2() {
        return back2;
    }
    public BufferedImage getBack3() {
        return back3;
    }
    public BufferedImage getFront1() {
        return front1;
    }
    public BufferedImage getFront2() {
        return front2;
    }
    public BufferedImage getFront3() {
        return front3;
    }
    public BufferedImage getLeft1() {
        return left1;
    }
    public BufferedImage getLeft2() {
        return left2;
    }
    public BufferedImage getLeft3() {
        return left3;
    }
    public BufferedImage getRight1() {
        return right1;
    }
    public BufferedImage getRight2() {
        return right2;
    }
    public BufferedImage getRight3() {
        return right3;
    }


    //SETTERS
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
    public void setColliding(boolean colliding) {
        this.colliding = colliding;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public void setImmune(boolean immune) {
        this.immune = immune;
    }
    public void setICount(int iCount) {
        this.iCount = iCount;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    public void setProjectile2(Projectile projectile2) {
        this.projectile2 = projectile2;
    }

    public void setProjectile3(Projectile projectile3) {
        this.projectile3 = projectile3;
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


    //IMAGE SETTERS
    public void setBack1(BufferedImage back1) {
        this.back1 = back1;
    }
    public void setBack2(BufferedImage back2) {
        this.back2 = back2;
    }
    public void setBack3(BufferedImage back3) {
        this.back3 = back3;
    }
    public void setFront1(BufferedImage front1) {
        this.front1 = front1;
    }
    public void setFront2(BufferedImage front2) {
        this.front2 = front2;
    }
    public void setFront3(BufferedImage front3) {
        this.front3 = front3;
    }
    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }
    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }
    public void setLeft3(BufferedImage left3) {
        this.left3 = left3;
    }
    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }
    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }
    public void setRight3(BufferedImage right3) {
        this.right3 = right3;
    }


    //OTHER METHODS
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

            if (immune)
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
            if (!sw.getPlayer().isImmune()) {
                sw.getPlayer().setHp(sw.getPlayer().getHp()-1);
                sw.getPlayer().setImmune(true);
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


        if (immune) {
            iCount++;
            if (iCount > 60) {
                immune = false;
                iCount = 0;
            }
        }
    }
}
