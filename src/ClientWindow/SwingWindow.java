package ClientWindow;

import Characters.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingWindow extends JPanel implements Runnable {
    //Screen dimensions
    final int STANDARD_TILE_SIZE = 16;  //16 x 16 tiles/character sprites
    final int TILE_SCALE = 5;   //Makes 16x16 sprites bigger for modern computer resolutions
    final int DISPLAYED_TILE_SIZE = STANDARD_TILE_SIZE * TILE_SCALE;    //The actual size the sprite will be displayed as (80x80 pixels)
    final int SCREEN_TILE_COLUMNS = 16;   //Num of tiles horizontally
    final int SCREEN_TILE_ROWS = 10;    //Num of tiles vertically
    final int SCREEN_WIDTH = DISPLAYED_TILE_SIZE * SCREEN_TILE_COLUMNS;   //Horizontal resolution (80 * 16 = 1280 pixels)
    final int SCREEN_HEIGHT = DISPLAYED_TILE_SIZE * SCREEN_TILE_ROWS;    //Vertical resolution (80 * 10 = 800 pixels)

    //Game running
    int framesPerSecond = 60;  //screen refreshes 60 times every second
    Thread gameThread;  //allows the game to run indefinitely
    KeyManager keyManager = new KeyManager();  //allows the program to take key inputs
    Player player = new Player(this, keyManager);

    /***
     * Initializes the dimensions of the screen and client inputs
     */
    public SwingWindow() {
        //initiation
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));   //sets the window size to 1280x800 pixels
        setBackground(Color.white);
        setDoubleBuffered(true);    //rendering sprites and animations
        addKeyListener(keyManager);  //listens to user keystrokes
        setFocusable(true);  //makes the program focus for key inputs
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
        player.updateInfo();
    }

    /***
     * Default java method to draw onto a JPanel
     *
     * @param graphic represents an object to draw onto the jpanel
     */
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        Graphics2D graphic2D = (Graphics2D) graphic;  //Casts graphics as a Graphics2D object, so it can be drawn on a 2D plane
        player.drawPlayer((Graphics2D) graphic);
        graphic2D.dispose();  //Essentially removes the old window so that a new window can be drawn
    }
}

