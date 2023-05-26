package Characters;

import ClientWindow.KeyManager;
import ClientWindow.SwingWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{
    KeyManager km;
    public final int SCREEN_X;
    public final int SCREEN_Y;
    int dmgCount = 0;

    /***
     * Initializes the SwingWindow and KeyManager
     *
     * @param sw represents the GUI
     * @param km represents the key manager
     */
    public Player (SwingWindow sw, KeyManager km) {
        super(sw);
        this.km = km;

        SCREEN_X = sw.getSCREEN_WIDTH()/2 - (sw.getDISPLAYED_TILE_SIZE()/2);
        SCREEN_Y = sw.getSCREEN_HEIGHT()/2 - (sw.getDISPLAYED_TILE_SIZE()/2);

        hitbox = new Rectangle(24, 60, 45, 30);

        setDefaultValues();
        getPlayerSprite();
    }

    /***
     * Sets the players intial location, speed, and direction
     */
    public void setDefaultValues() {
        name = "Player";
        xCoord = sw.getDISPLAYED_TILE_SIZE() * 7;
        yCoord = sw.getDISPLAYED_TILE_SIZE() * 4;
        speed = 25;
        direction = "front";

        maxHp = 3;
        hp = maxHp;
        projectile = new Projectile(sw);
    }

    public void getPlayerSprite() {
        try{
            back1 = ImageIO.read(getClass().getResource("/player_sprites/pigzard_b1.png"));
            back2 = ImageIO.read(getClass().getResource("/player_sprites/pigzard_b2.png"));
            back3 = ImageIO.read(getClass().getResource("/player_sprites/pigzard_b3.png"));
            front1 = ImageIO.read(getClass().getResource("/player_sprites/pigzard_f1.png"));
            front2 = ImageIO.read(getClass().getResource("/player_sprites/pigzard_f2.png"));
            front3 = ImageIO.read(getClass().getResource("/player_sprites/pigzard_f3.png"));
            left1 = ImageIO.read(getClass().getResource("/player_sprites/pigzard_l1.png"));
            left2 = ImageIO.read(getClass().getResource("/player_sprites/pigzard_l2.png"));
            right1 = ImageIO.read(getClass().getResource("/player_sprites/pigzard_r1.png"));
            right2 = ImageIO.read(getClass().getResource("/player_sprites/pigzard_r2.png"));
        } catch (IOException ex) {
            ex.printStackTrace();;
        }
    }

    public void updateInfo() {
        if (km.isWPressed() || km.isSPressed() || km.isAPressed() || km.isDPressed()) {
            //Character orientation
            if (km.isWPressed())
            {
                direction = "back";
            }
            if (km.isSPressed())
            {
                direction = "front";
            }
            if (km.isAPressed())
            {
                direction = "left";
            }
            if (km.isDPressed())
            {
                direction = "right";
            }

            //check for collisions
            colliding = false;
            sw.getCollisionDetector().detectTile(this);
            if (colliding)
            {
                if (dmgCount < 1) {
                    hp--;
                    if (hp < 0) {
                        hp = 0;
                    }
                }
                dmgCount++;
            }
            if (colliding == false) {
                dmgCount = 0;
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

            spriteCount++;
            if (spriteCount > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCount = 0;
            }
        }

        if (km.isShootPressed() == true)
        {
            projectile.set(xCoord, yCoord, direction, true,this);
        }
    }

    public void drawPlayer(Graphics2D graphic2D) {
        BufferedImage image = null;

        switch(direction)
        {
            case "back":
                if (!km.isWPressed() && !km.isAPressed() && !km.isSPressed() && !km.isDPressed())
                {
                    image = back3;
                }
                else {
                    if (spriteNum == 1) {
                        image = back1;
                    }
                    if (spriteNum == 2) {
                        image = back2;
                    }
                }
                break;
            case "front":
                if (!km.isWPressed() && !km.isAPressed() && !km.isSPressed() && !km.isDPressed())
                {
                    image = front3;
                }
                else {
                    if (spriteNum == 1) {
                        image = front1;
                    }
                    if (spriteNum == 2) {
                        image = front2;
                    }
                }
                break;
            case "left":
                if (!km.isWPressed() && !km.isAPressed() && !km.isSPressed() && !km.isDPressed())
                {
                    image = left1;
                }
                else {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                break;
            case "right":
                if (!km.isWPressed() && !km.isAPressed() && !km.isSPressed() && !km.isDPressed())
                {
                    image = right1;
                }
                else {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                break;
        }
        graphic2D.drawImage(image, SCREEN_X, SCREEN_Y, sw.getDISPLAYED_TILE_SIZE(), sw.getDISPLAYED_TILE_SIZE(), null);  //draws sprite on the screen

    }
}
