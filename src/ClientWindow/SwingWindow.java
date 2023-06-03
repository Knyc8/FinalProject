package ClientWindow;

import Dungeon.DungeonPlacer;
import Entities.CollisionDetector;
import Entities.Entity;
import Entities.Player;
import Dungeon.TileMapper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SwingWindow extends JPanel implements Runnable {
    //VARIABLES
    //Screen dimensions
    private final int STANDARD_TILE_SIZE = 32;  //32 x 32 tiles/character sprites
    private final int TILE_SCALE = 3;   //Makes 32x32 sprites bigger for modern computer resolutions
    private final int DISPLAYED_TILE_SIZE = STANDARD_TILE_SIZE * TILE_SCALE;    //The actual size the sprite will be displayed as (96x96 pixels)
    private final int SCREEN_TILE_COLUMNS = 16;   //Num of tiles horizontally
    private final int SCREEN_TILE_ROWS = 10;    //Num of tiles vertically
    private final int SCREEN_WIDTH = DISPLAYED_TILE_SIZE * SCREEN_TILE_COLUMNS;   //Horizontal resolution (96 * 16 = 1536 pixels)
    private final int SCREEN_HEIGHT = DISPLAYED_TILE_SIZE * SCREEN_TILE_ROWS;    //Vertical resolution (96 * 10 = 960 pixels)

    //Dungeon setting
    private final int DUNGEON_COL = 50;
    private final int DUNGEON_ROW = 50;

    //Game Setup
    private final int FRAMES_PER_SECOND = 60;  //screen refreshes 60 times every second
    private Thread gameThread;  //allows the game to run indefinitely
    private OnScreenUI ui = new OnScreenUI(this);
    private final KeyManager keyManager = new KeyManager(this);  //allows the program to take key inputs
    private final TileMapper tileMapper = new TileMapper(this);
    private final CollisionDetector collisionDetector = new CollisionDetector(this);
    private final DungeonPlacer dungeonPlacer = new DungeonPlacer(this);
    private final FileManager fileManager = new FileManager(this);
    private int loadingCount = 0;

    //Entity settings
    private Player player = new Player(this, keyManager);
    private Entity[] monsters = new Entity[150];  //Can display up to 10 enemies at once
    private ArrayList<Entity> projectiles = new ArrayList<>();
    private boolean[] monsterAlive;
    private int[][] monsterPos;

    //Game Running State
    public int gameState;
    public final int TITLE_SCREEN_STATE = 0;
    public final int PLAY_STATE = 1;
    public final int PAUSED_STATE = 2;
    public final int LOSE_STATE = 3;
    public final int LOAD_MENU_STATE = 4;
    public final int LOADING_STATE = 5;


    //CONSTRUCTOR
    /***
     * Initializes the dimensions of the screen and client inputs
     */
    public SwingWindow() {
        //initiation
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));   //sets the window size to 1280x800 pixels
        setBackground(Color.black);
        setDoubleBuffered(true);    //rendering sprites and animations
        addKeyListener(keyManager);  //listens to user keystrokes
        setFocusable(true);  //makes the program focus for key inputs
    }


    //GETTERS
    public int getDISPLAYED_TILE_SIZE() {
        return DISPLAYED_TILE_SIZE;
    }
    public int getSCREEN_WIDTH() {
        return SCREEN_WIDTH;
    }
    public int getSCREEN_HEIGHT() {
        return SCREEN_HEIGHT;
    }
    public int getDUNGEON_COL() {
        return DUNGEON_COL;
    }
    public int getDUNGEON_ROW() {
        return DUNGEON_ROW;
    }
    public TileMapper getTileMapper() {
        return tileMapper;
    }
    public CollisionDetector getCollisionDetector() {
        return collisionDetector;
    }
    public Player getPlayer() {
        return player;
    }
    public OnScreenUI getUi() {
        return ui;
    }
    public FileManager getFileManager() {
        return fileManager;
    }
    public Entity[] getMonsters() {
        return monsters;
    }
    public ArrayList<Entity> getProjectiles() {
        return projectiles;
    }
    public boolean[] getMonsterAlive() {
        return monsterAlive;
    }
    public int[][] getMonsterPos() {
        return monsterPos;
    }


    //SETTERS
    /***
     * Sets up the title screen, etc. when the game is started
     */
    public void setUp() {
        gameState = TITLE_SCREEN_STATE;
    }
    public void setMonsterAlive(boolean[] monsterAlive) {
        this.monsterAlive = monsterAlive;
    }
    public void setMonsterPos(int[][] monsterPos) {
        this.monsterPos = monsterPos;
    }


    //OTHER METHODS
    /***
     * Calls and commences the game loop
     */
    public void startThread() {
        gameThread = new Thread(this);  //passes this class into the thread class
        gameThread.start();     //automatically calls the run method
    }

    /***
     * Creates a game loop that allows the program to run indefinitely
     * Constantly updates and repaints the screen
     */
    @Override
    public void run() {
        double refreshRate = (double)1000000000 / FRAMES_PER_SECOND;  //program runs in nanoseconds, and 1E9 nanoseconds equals 1 second (1 second/ 60 frames).
        double refreshInterval = System.nanoTime() + refreshRate;  //every refresh will occur 0.0167 seconds later than opposed to 1 nanosecond later.
        while (gameThread != null) {

            update();  //updates the information about a character (positions) based on the fps

            repaint();  //redraws the sprite onto the screen however many times the fps is

            try {
                double remainingRTime = refreshInterval - System.nanoTime();  //how much time left of one loop until the next refresh
                remainingRTime = remainingRTime / 1000000;  //sleep method only accepts ms rather than ns
                if (remainingRTime < 0) {
                    remainingRTime = 0;  //prevents negative time and tells the thread to stop sleeping
                }

                Thread.sleep((long) remainingRTime);  //delays/stops the loops by the remaining refresh time so the program doesn't update instantly
                refreshInterval += refreshRate;  //sets the next refresh time

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        if (gameState == TITLE_SCREEN_STATE) {
            player.setHp(player.getMaxHp());
        }
        if (gameState == PLAY_STATE) {
            //Character movement
            player.update();

            for (Entity monster : monsters) {
                if (monster != null) {
                    monster.update();
                }
            }

            for (int i = 0; i < projectiles.size(); i++) {
                if (projectiles.get(i) != null) {
                    if(projectiles.get(i).isAlive()) {
                        projectiles.get(i).update();
                    }
                    if (!projectiles.get(i).isAlive()) {
                        projectiles.remove(i);
                        i--;
                    }
                }
            }
        }

        if (player.getHp() == 0)
        {
            gameState = LOSE_STATE;
            fileManager.deleteSaveFile();
        }

        if (gameState == LOADING_STATE)
        {
            loadingCount++;
            if (loadingCount >= 30) {
                if (fileManager.isInitiallyLoaded()) {
                    gameState = PLAY_STATE;
                    dungeonPlacer.loadMonsters();
                    getPlayer().setImmune(true);
                } else {
                    dungeonPlacer.placeMonsters();
                    gameState = PLAY_STATE;
                    player.setDefaultValues();
                }
                loadingCount = 0;
            }
        }
    }

    /***
     * Default java method to draw onto a JPanel
     *
     * @param graphic represents an object to draw onto the jpanel
     */
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        Graphics2D graphic2D = (Graphics2D) graphic;  //Casts graphics as a Graphics2D object, so it can be drawn on a 2D plane

        //Title Screen
        if (gameState == TITLE_SCREEN_STATE) {
            ui.draw(graphic2D);
        }
        else {
            //Dungeon map
            tileMapper.drawTiles(graphic2D);  //draws background before player so it's behind the player

            //Enemies
            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i] != null) {
                    if (monsters[i].isDying())
                    {
                        monsters[i] = null;
                    }
                    else {
                        monsters[i].draw(graphic2D);
                    }
                }
            }

            //Projectiles
            for (Entity projectile : projectiles) {
                if (projectile != null) {
                    projectile.draw(graphic2D);
                }
            }

            //Player
            player.draw((Graphics2D) graphic);  //draws player on the screen

            //Player ui
            ui.draw(graphic2D);
        }
        graphic2D.dispose();  //Essentially removes the old window so that a new window can be drawn
    }
}

