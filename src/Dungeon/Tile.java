package Dungeon;

import java.awt.image.BufferedImage;

/***
 * This class represents a single tile in the background.
 */
public class Tile {
    private BufferedImage img;
    public boolean collision = false;

    public void setImg(BufferedImage bi)
    {
        img = bi;
    }

    public BufferedImage getImg() {
        return img;
    }
}
