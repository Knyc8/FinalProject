package Dungeon;

import ClientWindow.SwingWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMapper {
    public SwingWindow sw;
    public Tile[] tiles;
    public int tileNum[][];

    public TileMapper(SwingWindow sw) {
        this.sw = sw;
        tiles = new Tile[50];  //The array length represents the different types of tiles
        tileNum = new int[sw.getDUNGEON_ROW()][sw.getDUNGEON_COL()];

        loadMapFile("/map_files/map1.txt");
        getTileImg();
    }

    /***
     * Loads the map file and transfers the values from the file into the tileNum Array
     *
     * @param mapFileName represents the name of the current map file
     */
    public void loadMapFile(String mapFileName)
    {
        try {
            InputStream inputStream = getClass().getResourceAsStream(mapFileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            for (int r = 0; r < sw.getDUNGEON_ROW(); r++)
            {
                String line = bufferedReader.readLine();
                String[] split = line.split(" ");
                for (int c = 0; c < sw.getDUNGEON_COL(); c++)
                {
                    tileNum[r][c] = Integer.parseInt(split[c]);
                }
            }
            bufferedReader.close();
        } catch (Exception ex)
        {

        }
    }
    /***
     * This method assigns a tile image to a tile object within the tiles array
     */
    public void getTileImg() {
        assignTileImg(0, "00", false);

        //floor 01-09
        assignTileImg(1, "01", false);
        assignTileImg(2, "02", false);
        assignTileImg(3, "03", false);
        assignTileImg(4, "04", false);
        assignTileImg(5, "05", false);
        assignTileImg(6, "06", false);
        assignTileImg(7, "07", false);
        assignTileImg(8, "08", false);
        assignTileImg(9, "09", false);

        //wall 10-17
        assignTileImg(10, "10", true);
        assignTileImg(11, "11", true);
        assignTileImg(12, "12", true);
        assignTileImg(13, "13", true);
        assignTileImg(14, "14", true);
        assignTileImg(15, "15", true);
        assignTileImg(16, "16", true);
        assignTileImg(17, "17", true);
    }

    public void assignTileImg(int index, String pathName, Boolean collision) {
        try {
            tiles[index] = new Tile();
            tiles[index].setImg(ImageIO.read(getClass().getResource("/dungeon_tiles/" + pathName + ".png")));
            tiles[index].setPathName(pathName);
            tiles[index].setCollision(collision);

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void drawTiles(Graphics2D graphics2D) {
        int tileSize = sw.getDISPLAYED_TILE_SIZE();
        for (int dr = 0; dr < sw.getDUNGEON_ROW(); dr++) {
            for (int dc = 0; dc < sw.getDUNGEON_COL(); dc++) {
                int dungeonX = (dc * tileSize);
                int dungeonY = (dr * tileSize);
                int xScreenLoc = dungeonX - sw.getPlayer().xCoord + sw.getPlayer().SCREEN_X;
                int yScreenLoc = dungeonY - sw.getPlayer().yCoord + sw.getPlayer().SCREEN_Y;

                //creates a boundary with which the tiles are drawn, thus minimizes the drawing of unseen tiles (especially for larger maps)
                if (dungeonX + sw.getDISPLAYED_TILE_SIZE() > sw.getPlayer().xCoord - sw.getPlayer().SCREEN_X &&
                        dungeonX - sw.getDISPLAYED_TILE_SIZE() < sw.getPlayer().xCoord + sw.getPlayer().SCREEN_X &&
                        dungeonY + sw.getDISPLAYED_TILE_SIZE() > sw.getPlayer().yCoord - sw.getPlayer().SCREEN_Y &&
                        dungeonY - sw.getDISPLAYED_TILE_SIZE() < sw.getPlayer().yCoord + sw.getPlayer().SCREEN_Y) {
                    graphics2D.drawImage(tiles[tileNum[dr][dc]].getImg(), xScreenLoc, yScreenLoc, tileSize, tileSize, null);
                }
            }
        }
    }
}
