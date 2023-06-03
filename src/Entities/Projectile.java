package Entities;

import ClientWindow.SwingWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends Entity{
    Entity user;
    BufferedImage explosion1, explosion2;
    int spriteCount = 0;
    int spriteNum = 1;

    public Projectile(SwingWindow sw) {
        super(sw);

        setHitbox(new Rectangle(15, 21, 66, 60));
        setName("fireball");
        setSpeed(15);
        setMaxHp(45);
        setHp(getMaxHp());
        setDmg(1);
        setAlive(false);
        getImg();
    }

    public void getImg() {
        setBack1(setImage("/projectiles/pigball_up_1.png"));
        setBack2(setImage("/projectiles/pigball_up_2.png"));
        setFront1(setImage("/projectiles/pigball_down_1.png"));
        setFront2(setImage("/projectiles/pigball_down_2.png"));
        setLeft1(setImage("/projectiles/pigball_left_1.png"));
        setLeft2(setImage("/projectiles/pigball_left_2.png"));
        setRight1(setImage("/projectiles/pigball_right_1.png"));
        setRight2(setImage("/projectiles/pigball_right_2.png"));
        explosion1 = setImage("/projectiles/explosion.png");
        explosion2 = setImage("/projectiles/explosion2.png");
    }

    public void set(int xCoord, int yCoord, String dir, Boolean alive, Entity user){
        setXCoord(xCoord);
        setYCoord(yCoord);
        setDirection(dir);
        setAlive(alive);
        this.user = user;
        setHp(getMaxHp());
    }

    public void update() {

        super.update();
        int enemyIdx = getSw().getCollisionDetector().detectEntity(getSw().monsters, this);
        if (enemyIdx != -1) {
            getSw().getPlayer().damage(enemyIdx);
        }

        setHp(getHp()-1);

        if (getHp() <= 0) {
            setAlive(false);
        }

        this.spriteCount++;
        if (this.spriteCount > 2) {
            if (this.spriteNum == 1) {
                this.spriteNum = 2;
            } else if (this.spriteNum == 2) {
                this.spriteNum = 1;
            }
            this.spriteCount = 0;
        }
    }

    public void draw(Graphics2D graphics2D) {
        int screenX = getXCoord() - getSw().getPlayer().getXCoord() + getSw().getPlayer().getSCREEN_X();
        int screenY = getYCoord() - getSw().getPlayer().getYCoord() + getSw().getPlayer().getSCREEN_Y();// + sw.getDISPLAYED_TILE_SIZE()/5;
        int size = getSw().getDISPLAYED_TILE_SIZE();

        if (getXCoord() + getSw().getDISPLAYED_TILE_SIZE() > getSw().getPlayer().getXCoord() - getSw().getPlayer().getSCREEN_X() &&
                getXCoord() - getSw().getDISPLAYED_TILE_SIZE() < getSw().getPlayer().getXCoord() + getSw().getPlayer().getSCREEN_X() &&
                getYCoord() + getSw().getDISPLAYED_TILE_SIZE() > getSw().getPlayer().getYCoord() - getSw().getPlayer().getSCREEN_Y() &&
                getYCoord() - getSw().getDISPLAYED_TILE_SIZE() < getSw().getPlayer().getYCoord() + getSw().getPlayer().getSCREEN_Y()) {

            BufferedImage image = null;

            if (isColliding() || getHp() < 10)
            {
                if (this.spriteNum == 1) {
                    image = explosion1;
                }
                if (this.spriteNum == 2) {
                    image = explosion2;
                }
                setSpeed(0);
            }
            else {
                setSpeed(15);
                switch (getDirection()) {
                    case "north" -> {
                        if (this.spriteNum == 1) {
                            image = getBack1();
                        }
                        if (this.spriteNum == 2) {
                            image = getBack2();
                        }
                    }
                    case "south" -> {
                        if (this.spriteNum == 1) {
                            image = getFront1();
                        }
                        if (this.spriteNum == 2) {
                            image = getFront2();
                        }
                    }
                    case "west" -> {
                        if (this.spriteNum == 1) {
                            image = getLeft1();
                        }
                        if (this.spriteNum == 2) {
                            image = getLeft2();
                        }
                    }
                    case "east" -> {
                        if (this.spriteNum == 1) {
                            image = getRight1();
                        }
                        if (this.spriteNum == 2) {
                            image = getRight2();
                        }
                    }
                }
            }

            graphics2D.drawImage(image, screenX, screenY, size, size, null);
        }
    }
}
