package Entities;

import ClientWindow.KeyManager;
import ClientWindow.SwingWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    //VARIABLES
    private final KeyManager km;
    private final int SCREEN_X;
    private final int SCREEN_Y;
    private int level;
    private int exp;
    private int enemiesKilled;

    //CONSTRUCTOR
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

        setHitbox(new Rectangle(24, 60, 45, 30));
        setHitboxDefaultX(24);
        setHitboxDefaultY(60);

        setDefaultValues();
        getPlayerSprite();
    }


    //GETTERS
    public int getSCREEN_X() {
        return SCREEN_X;
    }
    public int getSCREEN_Y() {
        return SCREEN_Y;
    }
    public int getLevel() {
        return level;
    }
    public int getExp() {
        return exp;
    }
    public int getEnemiesKilled() {
        return enemiesKilled;
    }
    public void getPlayerSprite() {
        setBack1(setImage("/player_sprites/pigzard_b1.png"));
        setBack2(setImage("/player_sprites/pigzard_b2.png"));
        setBack3(setImage("/player_sprites/pigzard_b3.png"));
        setFront1(setImage("/player_sprites/pigzard_f1.png"));
        setFront2(setImage("/player_sprites/pigzard_f2.png"));
        setFront3(setImage("/player_sprites/pigzard_f3.png"));
        setLeft1(setImage("/player_sprites/pigzard_l1.png"));
        setLeft2(setImage("/player_sprites/pigzard_l2.png"));
        setLeft3(setImage("/player_sprites/pigzard_l1.png"));
        setRight1(setImage("/player_sprites/pigzard_r1.png"));
        setRight2(setImage("/player_sprites/pigzard_r2.png"));
        setRight3(setImage("/player_sprites/pigzard_r1.png"));
    }


    //SETTERS
    public void setLevel(int level) {
        this.level = level;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }
    /***
     * Sets the players intial location, speed, and direction
     */
    public void setDefaultValues() {
        setName("Player");
        setXCoord(getSw().getDISPLAYED_TILE_SIZE() * 7);
        setYCoord(getSw().getDISPLAYED_TILE_SIZE() * 4);
        setSpeed(10);
        setDirection("south");
        setImmune(false);
        enemiesKilled = 0;

        level = 1;
        exp = 0;
        setMaxHp(3);
        setHp(getMaxHp());
        setProjectile(new Projectile(getSw()));
        setProjectile2(new Projectile(getSw()));
        setProjectile3(new Projectile(getSw()));
    }


    //OTHER METHODS
    public void update() {
        if (km.isWPressed() || km.isSPressed() || km.isAPressed() || km.isDPressed()) {
            //Character orientation
            if (km.isWPressed())
            {
                setDirection("north");
            }
            if (km.isSPressed())
            {
                setDirection("south");
            }
            if (km.isAPressed())
            {
                setDirection("west");
            }
            if (km.isDPressed())
            {
                setDirection("east");
            }

            //check for tiles collisions
            setColliding(false);
            getSw().getCollisionDetector().detectTile(this);

            //check for enemy collisions
            int enemyIdx = getSw().getCollisionDetector().detectEntity(getSw().monsters, this);
            takeDamage(enemyIdx);

            if (!isColliding()) {
                //dmgCount = 0;
                if (getDirection().equals("north")) {  //up speed units
                    setYCoord(getYCoord() - getSpeed());  //top left is (0, 0)
                }
                if (getDirection().equals("south")) {  //down speed units
                    setYCoord(getYCoord() + getSpeed());
                }
                if (getDirection().equals("west")) {  //left speed units
                    setXCoord(getXCoord() - getSpeed());
                }
                if (getDirection().equals("east")) {  //right speed units
                    setXCoord(getXCoord() + getSpeed());
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

        if (km.isShootPressed()) {
            setSpeed(10/2);

            if (level < 3) {
                if (!getProjectile().isAlive()) {
                    getProjectile().set(getXCoord(), getYCoord(), getDirection(), true, this);
                    getSw().projectiles.add(getProjectile());
                }
            }
            if (level >= 3 && level < 5) {
                if (!getProjectile().isAlive() && !getProjectile2().isAlive()) {
                    if (getDirection().equals("north") || getDirection().equals("south")) {
                        getProjectile().set(getXCoord() - 25, getYCoord(), getDirection(), true, this);
                        getProjectile2().set(getXCoord()  +25, getYCoord(), getDirection(), true, this);
                    }
                    if (getDirection().equals("east") || getDirection().equals("west")) {
                        getProjectile().set(getXCoord(), getYCoord() - 25, getDirection(), true, this);
                        getProjectile2().set(getXCoord(), getYCoord() + 25, getDirection(), true, this);
                    }
                    getSw().projectiles.add(getProjectile());
                    getSw().projectiles.add(getProjectile2());
                }
            }
            if (level >= 5) {
                if (!getProjectile().isAlive() && !getProjectile2().isAlive() && !getProjectile3().isAlive()) {
                    if (getDirection().equals("north") || getDirection().equals("south")) {
                        getProjectile().set(getXCoord() - 50, getYCoord(), getDirection(), true, this);
                        getProjectile2().set(getXCoord(), getYCoord(), getDirection(), true, this);
                        getProjectile3().set(getXCoord() + 50, getYCoord(), getDirection(), true, this);
                    }
                    if (getDirection().equals("east") || getDirection().equals("west")) {
                        getProjectile().set(getXCoord(), getYCoord() - 50, getDirection(), true, this);
                        getProjectile2().set(getXCoord(), getYCoord(), getDirection(), true, this);
                        getProjectile3().set(getXCoord(), getYCoord() + 50, getDirection(), true, this);
                    }
                    getSw().projectiles.add(getProjectile());
                    getSw().projectiles.add(getProjectile2());
                    getSw().projectiles.add(getProjectile3());
                }
            }
        }
        else {
            setSpeed(10);
        }

        if (isImmune()) {  //Invincibility loop
            setICount(getICount()+1);
            if (getSw().getFileManager().isInitiallyLoaded())
            {
                if (getICount() > 300)  //4 second invincibility
                {
                    setImmune(false);
                    setICount(0);
                    getSw().getFileManager().setInitiallyLoaded(false);
                }
            }
            else {
                if (getICount() > 60)  //1 second invincibility
                {
                    setImmune(false);
                    setICount(0);
                }
            }
        }
    }

    /***
     * Makes the player take damage
     *
     * @param entityIdx represents the index current enemy in the enemy list
     */
    public void takeDamage(int entityIdx) {
        if (entityIdx != -1) {
            if (!isImmune()) {
                setHp(getHp()-1);
            }
            setImmune(true);
        }
    }

    public void damage(int entityIdx) {
        if (entityIdx != -1) {
            if (!getSw().monsters[entityIdx].isImmune()) {
                getSw().monsters[entityIdx].setHp(getSw().monsters[entityIdx].getHp()-1);
                getSw().monsters[entityIdx].setImmune(true);


                if (getSw().monsters[entityIdx].getHp() <= 0)
                {
                    getSw().monsters[entityIdx] = null;
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
            setHp(getHp()+1);
            exp = 0;
            if (level % 2 == 0)
            {
                setMaxHp(getMaxHp()+1);
                setHp(getHp()+1);
            }
            if (getHp() > getMaxHp())
            {
                setHp(getMaxHp());
            }
        }
    }

    public void draw(Graphics2D graphic2D) {
        BufferedImage image = null;

        switch(getDirection())
        {
            case "north":
                if (!km.isWPressed() && !km.isAPressed() && !km.isSPressed() && !km.isDPressed())
                {
                    image = getBack3();
                }
                else {
                    if (spriteNum == 1) {
                        image = getBack1();
                    }
                    if (spriteNum == 2) {
                        image = getBack2();
                    }
                }
                break;
            case "south":
                if (!km.isWPressed() && !km.isAPressed() && !km.isSPressed() && !km.isDPressed())
                {
                    image = getFront3();
                }
                else {
                    if (spriteNum == 1) {
                        image = getFront1();
                    }
                    if (spriteNum == 2) {
                        image = getFront2();
                    }
                }
                break;
            case "west":
                if (!km.isWPressed() && !km.isAPressed() && !km.isSPressed() && !km.isDPressed())
                {
                    image = getLeft3();
                }
                else {
                    if (spriteNum == 1) {
                        image = getLeft1();
                    }
                    if (spriteNum == 2) {
                        image = getLeft2();
                    }
                }
                break;
            case "east":
                if (!km.isWPressed() && !km.isAPressed() && !km.isSPressed() && !km.isDPressed())
                {
                    image = getRight3();
                }
                else {
                    if (spriteNum == 1) {
                        image = getRight1();
                    }
                    if (spriteNum == 2) {
                        image = getRight2();
                    }
                }
                break;
        }

        if (isImmune())
        {
            graphic2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        graphic2D.drawImage(image, SCREEN_X, SCREEN_Y, getSw().getDISPLAYED_TILE_SIZE(), getSw().getDISPLAYED_TILE_SIZE(), null);  //draws sprite on the screen
        graphic2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
