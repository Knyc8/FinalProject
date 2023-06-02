package Entities;

import ClientWindow.KeyManager;
import ClientWindow.SwingWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    KeyManager km;
    public final int SCREEN_X;
    public final int SCREEN_Y;
    public int level;
    public int exp;
    public int enemiesKilled;

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
        hitboxDefaultX = 24;
        hitboxDefaultY = 60;

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
        speed = 10;
        direction = "south";
        collidable = true;
        immunity = false;
        enemiesKilled = 0;

        level = 5;
        exp = 0;
        maxHp = 3;
        hp = maxHp;
        projectile = new Projectile(sw);
        projectile2 = new Projectile(sw);
        projectile3 = new Projectile(sw);
    }

    public void getPlayerSprite() {
            back1 = setImage("/player_sprites/pigzard_b1.png");
            back2 = setImage("/player_sprites/pigzard_b2.png");
            back3 = setImage("/player_sprites/pigzard_b3.png");
            front1 = setImage("/player_sprites/pigzard_f1.png");
            front2 = setImage("/player_sprites/pigzard_f2.png");
            front3 = setImage("/player_sprites/pigzard_f3.png");
            left1 = setImage("/player_sprites/pigzard_l1.png");
            left2 = setImage("/player_sprites/pigzard_l2.png");
            left3 = setImage("/player_sprites/pigzard_l1.png");
            right1 = setImage("/player_sprites/pigzard_r1.png");
            right2 = setImage("/player_sprites/pigzard_r2.png");
            right3 = setImage("/player_sprites/pigzard_r1.png");
    }

    public void update() {
        if (km.isWPressed() || km.isSPressed() || km.isAPressed() || km.isDPressed()) {
            //Character orientation
            if (km.isWPressed())
            {
                direction = "north";
            }
            if (km.isSPressed())
            {
                direction = "south";
            }
            if (km.isAPressed())
            {
                direction = "west";
            }
            if (km.isDPressed())
            {
                direction = "east";
            }

            //check for tiles collisions
            colliding = false;
            sw.getCollisionDetector().detectTile(this);

            //check for enemy collisions
            int enemyIdx = sw.getCollisionDetector().detectEntity(sw.monsters, this);
            takeDamage(enemyIdx);

            if (colliding == false) {
                //dmgCount = 0;
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

            spriteCount++;
            if (spriteCount > 1500) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCount = 0;
            }
        }

        if (km.isShootPressed() == true) {
            speed = 10 / 2;

            if (level < 3) {
                if (projectile.alive == false) {
                    projectile.set(xCoord, yCoord, direction, true, this);
                    sw.projectiles.add(projectile);
                }
            }
            if (level >= 3 && level < 5) {
                if (projectile.alive == false && projectile2.alive == false) {
                    if (direction.equals("north") || direction.equals("south")) {
                        projectile.set(xCoord - 25, yCoord, direction, true, this);
                        projectile2.set(xCoord  +25, yCoord, direction, true, this);
                    }
                    if (direction.equals("east") || direction.equals("west")) {
                        projectile.set(xCoord, yCoord - 25, direction, true, this);
                        projectile2.set(xCoord, yCoord + 25, direction, true, this);
                    }
                    sw.projectiles.add(projectile);
                    sw.projectiles.add(projectile2);
                }
            }
            if (level >= 5) {
                if (projectile.alive == false && projectile2.alive == false && projectile3.alive == false) {
                    if (direction.equals("north") || direction.equals("south")) {
                        projectile.set(xCoord - 50, yCoord, direction, true, this);
                        projectile2.set(xCoord, yCoord, direction, true, this);
                        projectile3.set(xCoord + 50, yCoord, direction, true, this);
                    }
                    if (direction.equals("east") || direction.equals("west")) {
                        projectile.set(xCoord, yCoord - 50, direction, true, this);
                        projectile2.set(xCoord, yCoord, direction, true, this);
                        projectile3.set(xCoord, yCoord + 50, direction, true, this);
                    }
                    sw.projectiles.add(projectile);
                    sw.projectiles.add(projectile2);
                    sw.projectiles.add(projectile3);
                }
            }
        }
        else {
            speed = 10;
        }

        if (immunity) {  //Invincibility loop
            iCount++;
            if (sw.fileManager.alreadyLoaded == true)
            {
                if (iCount > 300)  //4 second invincibility
                {
                    immunity = false;
                    iCount = 0;
                    sw.fileManager.alreadyLoaded = false;
                }
            }
            else {
                if (iCount > 60)  //1 second invincibility
                {
                    immunity = false;
                    iCount = 0;
                }
            }
        }
    }

    /***
     * takes damage if the player is not invincible
     *
     * @param entityIdx
     */
    public void takeDamage(int entityIdx) {
        if (entityIdx != -1) {
            if (!immunity) {
                hp--;
            }
            immunity = true;
        }
    }

    public void damage(int entityIdx) {
        if (entityIdx != -1) {
            if (sw.monsters[entityIdx].immunity == false) {
                sw.monsters[entityIdx].hp--;
                sw.monsters[entityIdx].immunity = true;

                if (sw.monsters[entityIdx].hp <= 0)
                {
                    sw.monsters[entityIdx] = null;
                    exp++;
                    enemiesKilled++;
                    levelUp();
                }
            }
        }
    }

    public void levelUp()
    {
        if (exp == level*5)
        {
            level++;
            hp++;
            exp = 0;
            if (level % 2 == 0)
            {
                maxHp++;
                hp++;
            }
            if (hp > maxHp)
            {
                hp = maxHp;
            }
        }
    }

    public void draw(Graphics2D graphic2D) {
        BufferedImage image = null;

        switch(direction)
        {
            case "north":
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
            case "south":
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
            case "west":
                if (!km.isWPressed() && !km.isAPressed() && !km.isSPressed() && !km.isDPressed())
                {
                    image = left3;
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
            case "east":
                if (!km.isWPressed() && !km.isAPressed() && !km.isSPressed() && !km.isDPressed())
                {
                    image = right3;
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

        if (immunity)
        {
            graphic2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        graphic2D.drawImage(image, SCREEN_X, SCREEN_Y, sw.getDISPLAYED_TILE_SIZE(), sw.getDISPLAYED_TILE_SIZE(), null);  //draws sprite on the screen
        graphic2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
