package ClientWindow;

import java.io.*;

public class FileManager {
    //VARIABLES
    private final SwingWindow sw;
    private File save = new File("Save_File.dat");
    private boolean alreadySaved = false;
    private boolean initiallyLoaded = false;
    private boolean currentlyLoaded = false;

    //CONSTRUCTOR
    public FileManager(SwingWindow sw) {
        this.sw = sw;
    }


    //GETTERS
    public boolean isAlreadySaved() {
        return alreadySaved;
    }
    public boolean isInitiallyLoaded() {
        return initiallyLoaded;
    }


    //SETTERS
    public void setAlreadySaved(boolean alreadySaved) {
        this.alreadySaved = alreadySaved;
    }
    public void setInitiallyLoaded(boolean initiallyLoaded) {
        this.initiallyLoaded = initiallyLoaded;
    }
    public void setCurrentlyLoaded(boolean currentlyLoaded) {
        this.currentlyLoaded = currentlyLoaded;
    }


    //OTHER METHODS
    public boolean save() {
        try {
            save = new File("Save_File.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(save));

            DataLibrary dl = new DataLibrary();
            dl.setMaxHp(sw.getPlayer().getMaxHp());
            dl.setHp(sw.getPlayer().getHp());
            dl.setExp(sw.getPlayer().getExp());
            dl.setLevel(sw.getPlayer().getLevel());
            dl.setXCoord(sw.getPlayer().getXCoord());
            dl.setYCoord(sw.getPlayer().getYCoord());
            dl.setEnemiesKilled(sw.getPlayer().getEnemiesKilled());
            dl.setDirection(sw.getPlayer().getDirection());
            dl.setMonsterAlive(new boolean[sw.monsters.length]);
            dl.setMonsterPos(new int[sw.monsters.length][2]);

            for (int i = 0; i < sw.monsters.length; i++)
            {
                if (sw.monsters[i] != null)
                {
                    dl.getMonsterAlive()[i] = true;
                    dl.getMonsterPos()[i][0] = sw.monsters[i].getXCoord();
                    dl.getMonsterPos()[i][1] = sw.monsters[i].getYCoord();
                }
                else {
                    dl.getMonsterAlive()[i] = false;
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
            sw.getPlayer().setMaxHp(dl.getMaxHp());
            sw.getPlayer().setHp(dl.getHp());
            sw.getPlayer().setExp(dl.getExp());
            sw.getPlayer().setLevel(dl.getLevel());
            sw.getPlayer().setXCoord(dl.getXCoord());
            sw.getPlayer().setYCoord(dl.getYCoord());
            sw.getPlayer().setEnemiesKilled(dl.getEnemiesKilled());
            sw.getPlayer().setDirection(dl.getDirection());

            sw.monsterAlive = dl.getMonsterAlive();
            sw.monsterPos = dl.getMonsterPos();

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
