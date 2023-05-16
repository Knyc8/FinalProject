package Characters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int xCoord, yCoord;  //represents the coordinates of the world as the player stays in the center
    public int speed;

    public BufferedImage back1, back2, back3, front1, front2, front3,  left1, left2, right1, right2;
    public String direction;
    public int spriteCount = 0;
    public int spriteNum = 1;
    public Rectangle hitbox;
    public boolean colliding = false;
}
