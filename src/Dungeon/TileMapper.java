package Dungeon;

import ClientWindow.SwingWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileMapper {
    public SwingWindow sw;
    public Tile[] tiles;

    public TileMapper(SwingWindow sw) {
        this.sw = sw;
        tiles = new Tile[9];  //The array length represents the different types of tiles

        getTileImg();
    }

    /***
     * This method assigns a tile image to a tile object within the tiles array
     */
    public void getTileImg() {
        try {
            tiles[0] = new Tile();
            tiles[0].setImg(ImageIO.read(getClass().getResource("/dungeon_tiles/floor.png")));
            tiles[1] = new Tile();
            tiles[1].setImg(ImageIO.read(getClass().getResource("/dungeon_tiles/tlWall.png")));
            tiles[2] = new Tile();
            tiles[2].setImg(ImageIO.read(getClass().getResource("/dungeon_tiles/tWall.png")));
            tiles[3] = new Tile();
            tiles[3].setImg(ImageIO.read(getClass().getResource("/dungeon_tiles/trWall.png")));
            tiles[4] = new Tile();
            tiles[4].setImg(ImageIO.read(getClass().getResource("/dungeon_tiles/rWall.png")));
            tiles[5] = new Tile();
            tiles[5].setImg(ImageIO.read(getClass().getResource("/dungeon_tiles/brWall.png")));
            tiles[6] = new Tile();
            tiles[6].setImg(ImageIO.read(getClass().getResource("/dungeon_tiles/bWall.png")));
            tiles[7] = new Tile();
            tiles[7].setImg(ImageIO.read(getClass().getResource("/dungeon_tiles/blWall.png")));
            tiles[8] = new Tile();
            tiles[8].setImg(ImageIO.read(getClass().getResource("/dungeon_tiles/lWall.png")));

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void drawTiles(Graphics2D graphics2D) {
        int tileSize = sw.getDISPLAYED_TILE_SIZE();

        for (int r = 0; r < sw.getSCREEN_HEIGHT(); r+=tileSize){
            for (int c = 0; c < sw.getSCREEN_WIDTH(); c+=tileSize) {
                if (r == 0 || c == 0 || r == sw.getSCREEN_HEIGHT() - tileSize || c == sw.getSCREEN_WIDTH() - tileSize)
                {
                    if (r == 0) {
                        if (c == 0) {
                            graphics2D.drawImage(tiles[1].getImg(), c, r, tileSize, tileSize, null);
                        }
                        else if (c == sw.getSCREEN_WIDTH() - tileSize) {
                            graphics2D.drawImage(tiles[3].getImg(), c, r, tileSize, tileSize, null);
                        }
                        else {
                            graphics2D.drawImage(tiles[2].getImg(), c, r, tileSize, tileSize, null);
                        }
                    }
                    if (c == 0 && r != 0) {
                        if (r == sw.getSCREEN_HEIGHT()- tileSize) {
                            graphics2D.drawImage(tiles[7].getImg(), c, r, tileSize, tileSize, null);
                        }
                        else {
                            graphics2D.drawImage(tiles[8].getImg(), c, r, tileSize, tileSize, null);
                        }
                    }
                    if (c == sw.getSCREEN_WIDTH() - tileSize && r != 0) {
                        {
                            if (r == sw.getSCREEN_HEIGHT()- tileSize) {
                                graphics2D.drawImage(tiles[5].getImg(), c, r, tileSize, tileSize, null);
                            }
                            else {
                                graphics2D.drawImage(tiles[4].getImg(), c, r, tileSize, tileSize, null);
                            }
                        }
                    }
                    if (r == sw.getSCREEN_HEIGHT() - tileSize && (c != 0 && c!= sw.getSCREEN_WIDTH()- tileSize))
                    {
                        graphics2D.drawImage(tiles[6].getImg(), c, r, tileSize, tileSize, null);
                    }
                }
                else {
                    graphics2D.drawImage(tiles[0].getImg(), c, r, tileSize, tileSize, null);
                }
            }
        }
    }
}
