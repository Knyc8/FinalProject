package ClientWindow;

import java.io.Serializable;

public class DataLibrary implements Serializable {
    //VARIABLES
    private int maxHp;
    private int hp;
    private int exp;
    private int level;
    private int xCoord;
    private int yCoord;
    private int enemiesKilled;
    private String direction;
    private boolean[] monsterAlive;
    private int[][] monsterPos;


    //GETTERS
    public int getMaxHp() {
        return maxHp;
    }
    public int getHp() {
        return hp;
    }
    public int getExp() {
        return exp;
    }
    public int getLevel() {
        return level;
    }
    public int getXCoord() {
        return xCoord;
    }
    public int getYCoord() {
        return yCoord;
    }
    public int getEnemiesKilled() {
        return enemiesKilled;
    }
    public String getDirection() {
        return direction;
    }
    public boolean[] getMonsterAlive() {
        return monsterAlive;
    }
    public int[][] getMonsterPos() {
        return monsterPos;
    }


    //SETTERS
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setXCoord(int xCoord) {
        this.xCoord = xCoord;
    }
    public void setYCoord(int yCoord) {
        this.yCoord = yCoord;
    }
    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public void setMonsterAlive(boolean[] monsterAlive) {
        this.monsterAlive = monsterAlive;
    }
    public void setMonsterPos(int[][] monsterPos) {
        this.monsterPos = monsterPos;
    }
}
