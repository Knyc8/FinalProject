package Dungeon;

import ClientWindow.SwingWindow;
import Entities.enemies.Enemy;

/***
 * places entities in a specific location in the dungeon
 */
public class DungeonPlacer {
    //VARIABLES
    private SwingWindow sw;
    private int[][] roomInfo;

    //CONSTRUCTOR
    public DungeonPlacer(SwingWindow sw)
    {
        this.sw = sw;
        roomInfo = new int[8][6];
        setUpRooms();
    }


    //SETTERS
    /***
     * Contains the x and y values of the
     */
    public void setUpRooms()
    {
        roomInfo[0] = new int[]{18, 30, 1, 7};  //room 1: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[1] = new int[]{4, 10, 12, 20};  //room 2: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[2] = new int[]{37, 47, 1, 7};  //room 3: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[3] = new int[]{4, 10, 26, 37};  //room 4: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[4] = new int[]{1, 13, 42, 48};  //room 5: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[5] = new int[]{39, 46, 15, 20};  //room 6: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[6] = new int[]{22, 26, 37, 40};  //room 7: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[7] = new int[]{29, 48, 29, 48};  //room 8: {startX, endX, startY, endY, startAmt, endAmt};
    }


    //OTHER METHODS
    public void placeMonsters() {
        for (int i = 0; i < 150; i++) {  //150 total enemies
            int startX = 0;
            int endX = 0;
            int startY = 0;
            int endY = 0;

            int room = i/15;
            if (room > 7)
            {
                room = 7;
            }

            startX = roomInfo[room][0];
            endX = roomInfo[room][1];
            startY = roomInfo[room][2];
            endY = roomInfo[room][3];

            sw.getMonsters()[i] = new Enemy(sw);
            int randX = (int) (Math.random() * (endX - startX + 1)) + startX;
            int randY = (int) (Math.random() * (endY - startY + 1)) + startY;
            sw.getMonsters()[i].setXCoord(sw.getDISPLAYED_TILE_SIZE() * randX);
            sw.getMonsters()[i].setYCoord(sw.getDISPLAYED_TILE_SIZE() * randY);
        }
    }

    public void loadMonsters() {
        for (int i = 0; i < sw.getMonsters().length; i++)
        {
            if (sw.getMonsterAlive()[i])
            {
                sw.getMonsters()[i] = new Enemy(sw);
                sw.getMonsters()[i].setXCoord(sw.getMonsterPos()[i][0]);
                sw.getMonsters()[i].setYCoord(sw.getMonsterPos()[i][1]);
            }
            else
            {
                sw.getMonsters()[i] = null;
            }
        }
    }
}
