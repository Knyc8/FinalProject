package ClientWindow;

import java.io.Serializable;

public class DataLibrary implements Serializable {
    int maxHp;
    int hp;
    int exp;
    int level;
    int xCoord;
    int yCoord;
    int enemiesKilled;
    String direction;
    boolean[] monsterAlive;
    int[][] monsterPos;
}
