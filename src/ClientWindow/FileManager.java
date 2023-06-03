package ClientWindow;

import java.io.*;

public class FileManager {
    SwingWindow sw;
    File save = new File("Save_File.dat");
    public boolean alreadySaved = false;
    public boolean initiallyLoaded = false;
    public boolean currentlyLoaded = false;

    public FileManager(SwingWindow sw) {
        this.sw = sw;
    }


    public boolean save() {
        try {
            save = new File("Save_File.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(save));

            DataLibrary dl = new DataLibrary();
            dl.maxHp = sw.getPlayer().getMaxHp();
            dl.hp = sw.getPlayer().getHp();
            dl.exp = sw.getPlayer().getExp();
            dl.level = sw.getPlayer().getLevel();
            dl.xCoord = sw.getPlayer().getXCoord();
            dl.yCoord = sw.getPlayer().getYCoord();
            dl.enemiesKilled = sw.getPlayer().getEnemiesKilled();
            dl.direction = sw.getPlayer().getDirection();
            dl.monsterAlive = new boolean[sw.monsters.length];
            dl.monsterPos = new int[sw.monsters.length][2];

            for (int i = 0; i < sw.monsters.length; i++)
            {
                if (sw.monsters[i] != null)
                {
                    dl.monsterAlive[i] = true;
                    dl.monsterPos[i][0] = sw.monsters[i].getXCoord();
                    dl.monsterPos[i][1] = sw.monsters[i].getYCoord();
                }
                else {
                    dl.monsterAlive[i] = false;
                }
            }

            objectOutputStream.writeObject(dl);

            System.out.println("Save Successful");
            return true;
        } catch (Exception e) {
            System.out.println("Save Failed");
            return false;
        }
    }

    public boolean load() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("Save_File.dat")));
            currentlyLoaded = true;

            DataLibrary dl = (DataLibrary) objectInputStream.readObject();
            sw.getPlayer().setMaxHp(dl.maxHp);
            sw.getPlayer().setHp(dl.hp);
            sw.getPlayer().setExp(dl.exp);
            sw.getPlayer().setLevel(dl.level);
            sw.getPlayer().setXCoord(dl.xCoord);
            sw.getPlayer().setYCoord(dl.yCoord);
            sw.getPlayer().setEnemiesKilled(dl.enemiesKilled);
            sw.getPlayer().setDirection(dl.direction);

            sw.monsterAlive = dl.monsterAlive;
            sw.monsterPos = dl.monsterPos;

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public void deleteSaveFile()
    {
        if (save != null && currentlyLoaded) {
            System.out.println("File was deleted");
            save.delete();
        }
    }
}
