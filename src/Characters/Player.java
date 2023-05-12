package Characters;

import ClientWindow.KeyManager;
import ClientWindow.SwingWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{
    SwingWindow sw;
    KeyManager km;

    /***
     * Initializes the SwingWindow and KeyManager
     *
     * @param sw represents the GUI
     * @param km represents the key manager
     */
    public Player (SwingWindow sw, KeyManager km) {
        this.sw = sw;
        this.km = km;

        setDefaultValues();
        getPlayerSprite();
    }

    /***
     * Sets the players intial location, speed, and direction
     */
    public void setDefaultValues() {
        xCoord = 100;
        yCoord = 100;
        speed = 7;
        direction = "front";
    }

    public void getPlayerSprite() {
        try{
            back1 = ImageIO.read(getClass().getResource("/player_sprites/back1.png"));
            back2 = ImageIO.read(getClass().getResource("/player_sprites/back2.png"));
            back1 = ImageIO.read(getClass().getResource("/player_sprites/back3.png"));
            front1 = ImageIO.read(getClass().getResource("/player_sprites/front1.png"));
            front2 = ImageIO.read(getClass().getResource("/player_sprites/front2.png"));
            front3 = ImageIO.read(getClass().getResource("/player_sprites/front3.png"));
            left1 = ImageIO.read(getClass().getResource("/player_sprites/left1.png"));
            left2 = ImageIO.read(getClass().getResource("/player_sprites/left2.png"));
            left3 = ImageIO.read(getClass().getResource("/player_sprites/left3.png"));
            right1 = ImageIO.read(getClass().getResource("/player_sprites/right1.png"));
            right2 = ImageIO.read(getClass().getResource("/player_sprites/right2.png"));
            right3 = ImageIO.read(getClass().getResource("/player_sprites/right3.png"));
        } catch (IOException ex) {
            ex.printStackTrace();;
        }
    }

    public void updateInfo() {
        //Character movement
        if (km.isWPressed())  //up speed units
        {
            direction = "back";
            yCoord -= speed;  //top left is (0, 0)
            if (yCoord < 0)
            {
                yCoord = 0;
            }
        }
        if (km.isSPressed())  //down speed units
        {
            direction = "front";
            yCoord += speed;
            if (yCoord > sw.getSCREEN_HEIGHT() - sw.getDISPLAYED_TILE_SIZE())
            {
                yCoord = sw.getSCREEN_HEIGHT() - sw.getDISPLAYED_TILE_SIZE();
            }
        }
        if (km.isAPressed())  //left speed units
        {
            direction = "left";
            xCoord -= speed;
            if (xCoord < 0)
            {
                xCoord = 0;
            }
        }
        if (km.isDPressed())  //right speed units
        {
            direction = "right";
            xCoord += speed;
            if (xCoord > sw.getSCREEN_WIDTH() - sw.getDISPLAYED_TILE_SIZE())
            {
                xCoord = sw.getSCREEN_WIDTH() - sw.getDISPLAYED_TILE_SIZE();
            }
        }

        if (!km.isWPressed() && !km.isAPressed() && !km.isSPressed() && !km.isDPressed()) {
            spriteCount = 3;
        }
        else {
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
    }

    public void drawPlayer(Graphics2D graphic2D) {
//        graphic2D.setColor(Color.black);
//        graphic2D.fillRect(xCoord, yCoord, sw.getDISPLAYED_TILE_SIZE(), sw.getDISPLAYED_TILE_SIZE());  //for testing character sprites
        BufferedImage image = null;

        switch(direction)
        {
            case "back":
                if (spriteNum == 1)
                {
                    image = back1;
                }
                if (spriteNum == 2)
                {
                    image = back2;
                }
                if (spriteNum == 3)
                {
                    image = back3;
                }
                break;
            case "front":
                if (spriteNum == 1)
                {
                    image = front1;
                }
                if (spriteNum == 2)
                {
                    image = front2;
                }
                if (spriteNum == 3)
                {
                    image = front3;
                }
                break;
            case "left":
                if (spriteNum == 1)
                {
                    image = left1;
                }
                if (spriteNum == 2)
                {
                    image = left2;
                }
                if (spriteNum == 3)
                {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1)
                {
                    image = right1;
                }
                if (spriteNum == 2)
                {
                    image = right2;
                }
                if (spriteNum == 3)
                {
                    image = right3;
                }
                break;
        }
        graphic2D.drawImage(image, xCoord, yCoord, sw.getDISPLAYED_TILE_SIZE(), sw.getDISPLAYED_TILE_SIZE(), null);  //draws sprite on the screen

    }
}
