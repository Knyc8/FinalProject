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
        assignTileImg(0, "/dungeon_tiles/00.png", false);

        //floor 01-09
        assignTileImg(1, "/dungeon_tiles/01.png", false);
        assignTileImg(2, "/dungeon_tiles/02.png", false);
        assignTileImg(3, "/dungeon_tiles/03.png", false);
        assignTileImg(4, "/dungeon_tiles/04.png", false);
        assignTileImg(5, "/dungeon_tiles/05.png", false);
        assignTileImg(6, "/dungeon_tiles/06.png", false);
        assignTileImg(7, "/dungeon_tiles/07.png", false);
        assignTileImg(8, "/dungeon_tiles/08.png", false);
        assignTileImg(9, "/dungeon_tiles/09.png", false);

        //wall 10-17
        assignTileImg(10, "/dungeon_tiles/10.png", true);
        assignTileImg(11, "/dungeon_tiles/11.png", true);
        assignTileImg(12, "/dungeon_tiles/12.png", true);
        assignTileImg(13, "/dungeon_tiles/13.png", true);
        assignTileImg(14, "/dungeon_tiles/14.png", true);
        assignTileImg(15, "/dungeon_tiles/15.png", true);
        assignTileImg(16, "/dungeon_tiles/16.png", true);
        assignTileImg(17, "/dungeon_tiles/17.png", true);
    }

    public void assignTileImg(int index, String pathName, Boolean collision) {
        try {
            tiles[index] = new Tile();
            tiles[index].setImg(ImageIO.read(getClass().getResource(pathName)));
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
