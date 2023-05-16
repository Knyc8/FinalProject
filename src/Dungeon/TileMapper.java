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
    int tileNum[][];

    public TileMapper(SwingWindow sw) {
        this.sw = sw;
        tiles = new Tile[9];  //The array length represents the different types of tiles
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
        for (int dr = 0; dr < sw.getDUNGEON_ROW(); dr++) {
            for (int dc = 0; dc < sw.getDUNGEON_COL(); dc++) {
                int dungeonX = (dc*tileSize);
                int dungeonY = (dr*tileSize);
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

        //testing:
//        for (int r = 0; r < sw.getSCREEN_HEIGHT(); r+=tileSize){
//            for (int c = 0; c < sw.getSCREEN_WIDTH(); c+=tileSize) {
//                if (r == 0 || c == 0 || r == sw.getSCREEN_HEIGHT() - tileSize || c == sw.getSCREEN_WIDTH() - tileSize)
//                {
//                    if (r == 0) {
//                        if (c == 0) {
//                            graphics2D.drawImage(tiles[1].getImg(), c, r, tileSize, tileSize, null);
//                        }
//                        else if (c == sw.getSCREEN_WIDTH() - tileSize) {
//                            graphics2D.drawImage(tiles[3].getImg(), c, r, tileSize, tileSize, null);
//                        }
//                        else {
//                            graphics2D.drawImage(tiles[2].getImg(), c, r, tileSize, tileSize, null);
//                        }
//                    }
//                    if (c == 0 && r != 0) {
//                        if (r == sw.getSCREEN_HEIGHT()- tileSize) {
//                            graphics2D.drawImage(tiles[7].getImg(), c, r, tileSize, tileSize, null);
//                        }
//                        else {
//                            graphics2D.drawImage(tiles[8].getImg(), c, r, tileSize, tileSize, null);
//                        }
//                    }
//                    if (c == sw.getSCREEN_WIDTH() - tileSize && r != 0) {
//                        {
//                            if (r == sw.getSCREEN_HEIGHT()- tileSize) {
//                                graphics2D.drawImage(tiles[5].getImg(), c, r, tileSize, tileSize, null);
//                            }
//                            else {
//                                graphics2D.drawImage(tiles[4].getImg(), c, r, tileSize, tileSize, null);
//                            }
//                        }
//                    }
//                    if (r == sw.getSCREEN_HEIGHT() - tileSize && (c != 0 && c!= sw.getSCREEN_WIDTH()- tileSize))
//                    {
//                        graphics2D.drawImage(tiles[6].getImg(), c, r, tileSize, tileSize, null);
//                    }
//                }
//                else {
//                    graphics2D.drawImage(tiles[0].getImg(), c, r, tileSize, tileSize, null);
//                }
//            }
//        }
    }
}
