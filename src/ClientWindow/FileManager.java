package ClientWindow;

import Entities.Entity;
import Entities.Player;

import java.io.*;

public class FileManager {
    SwingWindow sw;
    File save = null;
    boolean alreadySaved = false;
    boolean alreadyLoaded = false;

    public FileManager(SwingWindow sw) {
        this.sw = sw;
    }

    public boolean save() {
        try {
            save = new File("Save File.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(save));

            DataLibrary dl = new DataLibrary();
            dl.maxHp = sw.getPlayer().maxHp;
            dl.hp = sw.getPlayer().hp;
            dl.exp = sw.getPlayer().exp;
            dl.level = sw.getPlayer().level;
            dl.xCoord = sw.getPlayer().xCoord;
            dl.yCoord = sw.getPlayer().yCoord;
            dl.enemiesKilled = sw.getPlayer().enemiesKilled;
            dl.direction = sw.getPlayer().direction;
            dl.monsterAlive = new boolean[sw.monsters.length];
            dl.monsterPos = new int[sw.monsters.length][2];

            for (int i = 0; i < sw.monsters.length; i++)
            {
                if (sw.monsters[i] != null)
                {
                    dl.monsterAlive[i] = true;
                    dl.monsterPos[i][0] = sw.monsters[i].xCoord;
                    dl.monsterPos[i][1] = sw.monsters[i].yCoord;
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
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("Save File.dat")));

            DataLibrary dl = (DataLibrary) objectInputStream.readObject();
            sw.getPlayer().maxHp = dl.maxHp;
            sw.getPlayer().hp = dl.hp;
            sw.getPlayer().exp = dl.exp;
            sw.getPlayer().level = dl.level;
            sw.getPlayer().xCoord = dl.xCoord;
            sw.getPlayer().yCoord = dl.yCoord;
            sw.getPlayer().enemiesKilled = dl.enemiesKilled;
            sw.getPlayer().direction = dl.direction;

            sw.monsterAlive = dl.monsterAlive;
            sw.monsterPos = dl.monsterPos;

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public void deleteSaveFile()
    {
        if (save != null) {
            save.delete();
            save = null;
            System.out.println("File was deleted");
        }
    }
}
