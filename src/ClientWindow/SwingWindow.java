package ClientWindow;

import Characters.CollisionDetector;
import Characters.Player;
import Dungeon.TileMapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingWindow extends JPanel implements Runnable {
    //Screen dimensions
    final int STANDARD_TILE_SIZE = 32;  //32 x 32 tiles/character sprites
    final int TILE_SCALE = 3;   //Makes 32x32 sprites bigger for modern computer resolutions
    final int DISPLAYED_TILE_SIZE = STANDARD_TILE_SIZE * TILE_SCALE;    //The actual size the sprite will be displayed as (96x96 pixels)
    final int SCREEN_TILE_COLUMNS = 16;   //Num of tiles horizontally
    final int SCREEN_TILE_ROWS = 10;    //Num of tiles vertically
    final int SCREEN_WIDTH = DISPLAYED_TILE_SIZE * SCREEN_TILE_COLUMNS;   //Horizontal resolution (96 * 16 = 1536 pixels)
    final int SCREEN_HEIGHT = DISPLAYED_TILE_SIZE * SCREEN_TILE_ROWS;    //Vertical resolution (96 * 10 = 960 pixels)

    //Dungeon setting
    final int DUNGEON_COL = 50;
    final int DUNGEON_ROW = 50;
    final int DUNGEON_WIDTH = DISPLAYED_TILE_SIZE * DUNGEON_COL;
    final int DUNGEON_HEIGHT = DISPLAYED_TILE_SIZE * DUNGEON_ROW;

    //Game Setup
    int framesPerSecond = 60;  //screen refreshes 60 times every second
    Thread gameThread;  //allows the game to run indefinitely
    OnScreenUI ui = new OnScreenUI(this);
    KeyManager keyManager = new KeyManager(this);  //allows the program to take key inputs
    TileMapper tileMapper = new TileMapper(this);
    CollisionDetector collisionDetector = new CollisionDetector(this);

    //Entity settings
    Player player = new Player(this, keyManager);

    //Game Running State
    public int gameState = 1;
    public final int MENU_STATE = 0;
    public final int PLAY_STATE = 1;
    public final int PAUSED_STATE = 2;

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

    public int getDISPLAYED_TILE_SIZE() {
        return DISPLAYED_TILE_SIZE;
    }

    public int getSCREEN_WIDTH() {
        return SCREEN_WIDTH;
    }

    public int getSCREEN_HEIGHT() {
        return SCREEN_HEIGHT;
    }

    public int getSCREEN_TILE_COLUMNS() {
        return SCREEN_TILE_COLUMNS;
    }

    public int getSCREEN_TILE_ROWS() {
        return SCREEN_TILE_ROWS;
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
        double refreshRate = 1000000000/framesPerSecond;  //program runs in nanoseconds, and 1E9 nanoseconds equals 1 second (1 second/ 60 frames).
        double refreshInterval = System.nanoTime() + refreshRate;  //every refresh will occur 0.0167 seconds later than opposed to 1 nanosecond later.
        while (gameThread != null) {
            update();  //updates the information about a character (positions) based on the fps

            repaint();  //redraws the sprite onto the screen however many times the fps is

            try {
                double remainingRTime = refreshInterval - System.nanoTime();  //how much time left of one loop until the next refresh
                remainingRTime = remainingRTime/1000000;  //sleep method only accepts ms rather than ns
                if (remainingRTime < 0)
                {
                    remainingRTime = 0;  //prevents negative time and tells the thread to stop sleeping
                }

                Thread.sleep((long)remainingRTime);  //delays/stops the loops by the remaining refresh time so the program doesn't update instantly
                refreshInterval += refreshRate;  //sets the next refresh time

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        if (gameState == PLAY_STATE) {
            //Character movement
            player.updateInfo();
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
        tileMapper.drawTiles(graphic2D);  //draws background before player so it's behind the player
        player.drawPlayer((Graphics2D) graphic);  //draws player on the screen
        ui.draw(graphic2D);
        graphic2D.dispose();  //Essentially removes the old window so that a new window can be drawn
    }
}

