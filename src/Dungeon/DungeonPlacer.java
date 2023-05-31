package Dungeon;

import ClientWindow.SwingWindow;
import Entities.enemies.MilesMM;

/***
 * places entities in a specific location in the dungeon
 */
public class DungeonPlacer {
    SwingWindow sw;
    public int[][] roomInfo;
    private int enemyCount = 0;
    public DungeonPlacer(SwingWindow sw)
    {
        this.sw = sw;
        roomInfo = new int[8][6];
        setUpRooms();
    }

    /***
     * Contains the x and y values of the
     */
    public void setUpRooms()
    {
        roomInfo[0] = new int[]{18, 30, 1, 7, 5, 10};  //room 1: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[1] = new int[]{4, 10, 12, 20, 5, 10};  //room 2: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[2] = new int[]{18, 30, 1, 7, 5, 10};  //room 3: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[3] = new int[]{18, 30, 1, 7, 5, 10};  //room 4: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[4] = new int[]{18, 30, 1, 7, 5, 10};  //room 5: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[5] = new int[]{18, 30, 1, 7, 5, 10};  //room 6: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[6] = new int[]{18, 30, 1, 7, 5, 10};  //room 7: {startX, endX, startY, endY, startAmt, endAmt};
        roomInfo[7] = new int[]{18, 30, 1, 7, 5, 10};  //room 8: {startX, endX, startY, endY, startAmt, endAmt};
    }

    public void placeMonsters() {
        for (int room = 0; room <= 1; room++) {  //8 total rooms
            int startX = 0;
            int endX = 0;
            int startY = 0;
            int endY = 0;
            int amount = 0;
            startX = roomInfo[room][0];
            endX = roomInfo[room][1];
            startY = roomInfo[room][2];
            endY = roomInfo[room][3];
            amount = (int) (Math.random()*(roomInfo[room][5] - roomInfo[room][4] + 1)) + roomInfo[room][4];

            for (int i = 0; i < 5; i++) {
                    sw.monsters[enemyCount + i] = new MilesMM(sw, room);
                    int randX = (int) (Math.random() * (endX - startX + 1)) + startX;
                    int randY = (int) (Math.random() * (endY - startY + 1)) + startY;
                    System.out.println(randX);
                    System.out.println(randY);
                    sw.monsters[i].xCoord = sw.getDISPLAYED_TILE_SIZE() * randX;
                    sw.monsters[i].yCoord = sw.getDISPLAYED_TILE_SIZE() * randY;
            }
        }
    }
}
