package Dungeon;

import java.awt.image.BufferedImage;

/***
 * This class represents a single tile in the background.
 */
public class Tile {
    //VARIABLES
    private BufferedImage img;
    private boolean collision;

    //CONSTRUCTOR
    public Tile() {
        collision = false;
    }


    //GETTERS
    public boolean getCollision() {
        return collision;
    }
    public BufferedImage getImg() {
        return img;
    }


    //SETTERS
    public void setCollision(Boolean c)
    {
        collision = c;
    }

    public void setImg(BufferedImage bi)
    {
        img = bi;
    }

}
