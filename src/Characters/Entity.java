package Characters;

import ClientWindow.SwingWindow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    //setup
    SwingWindow sw;
    public Entity (SwingWindow sw) {
        this.sw = sw;
    }
    public int xCoord, yCoord;  //represents the coordinates of the world as the player stays in the center

    public BufferedImage back1, back2, back3, front1, front2, front3,  left1, left2, right1, right2;
    public String direction;
    public static int spriteCount = 0;
    public static int spriteNum = 1;
    public Rectangle hitbox;
    public boolean colliding = false;
    public boolean alive;
    Projectile projectile;

    //stats
    public String name;
    public int speed;
    public int maxHp;
    public int hp;
    public int dmg;
}
