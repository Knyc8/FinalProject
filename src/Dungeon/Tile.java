package Dungeon;

import java.awt.image.BufferedImage;

/***
 * This class represents a single tile in the background.
 */
public class Tile {
    private BufferedImage img;
    private String pathName;
    public boolean collision = false;

    public void setCollision(Boolean c)
    {
        collision = c;
    }

    public void setImg(BufferedImage bi)
    {
        img = bi;
    }
    public void setPathName(String pn) {
        pathName = pn;
    }

    public BufferedImage getImg() {
        return img;
    }
    public String getPathName() {
        return pathName;
    }
}
