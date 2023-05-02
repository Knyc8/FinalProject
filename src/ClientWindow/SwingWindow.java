package ClientWindow;

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

    Thread gameThread;  //allows the game to run indefinitely
    KeyManager keyManager = new KeyManager();  //allows the program to take key inputs

    public SwingWindow() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));   //sets the window size to 1280x800 pixels
        setBackground(Color.white);
        setDoubleBuffered(true);    //rendering sprites and animations
        addKeyListener(keyManager);
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
     */

    @Override
    public void run() {
//        int count = 0;  //for testing
        while (gameThread != null) {
//            System.out.println("Loop Running: " + count); //for testing
//            count++;  //for testing
            update();  //updates the information about a character (positions) based on the fps

            repaint();  //redraws the sprite onto the screen however many times the fps is
        }
    }

    public void update() {}

    /***
     * Default java method to draw onto a JPanel
     *
     * @param graphic represents an object to draw onto the jpanel
     */
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        Graphics2D graphic2D = (Graphics2D) graphic;  //Casts graphics as a Graphics2D object, so it can be drawn on a 2D plane
        graphic2D.setColor(Color.black);
        graphic2D.fillRect(0, 0, DISPLAYED_TILE_SIZE, DISPLAYED_TILE_SIZE);  //for testing character sprites
        graphic2D.fillRect(SCREEN_WIDTH-DISPLAYED_TILE_SIZE, 0, DISPLAYED_TILE_SIZE, DISPLAYED_TILE_SIZE);  //for testing character sprites
        graphic2D.fillRect(0, SCREEN_HEIGHT-DISPLAYED_TILE_SIZE, DISPLAYED_TILE_SIZE, DISPLAYED_TILE_SIZE);  //for testing character sprites
        graphic2D.fillRect(SCREEN_WIDTH-DISPLAYED_TILE_SIZE, SCREEN_HEIGHT-DISPLAYED_TILE_SIZE, DISPLAYED_TILE_SIZE, DISPLAYED_TILE_SIZE);  //for testing character sprites
        graphic2D.dispose();  //application cleans up resources to save memory
    }
}

