package ClientWindow;

import javax.swing.*;
import java.awt.*;

public class SwingWindow extends JPanel implements Runnable{
    //Screen dimensions
    final int STANDARD_TILE_SIZE = 16;  //16 x 16 tiles/character sprites
    final int TILE_SCALE = 5;   //Makes 16x16 sprites bigger for modern computer resolutions
    final int DISPLAYED_TILE_SIZE = STANDARD_TILE_SIZE * TILE_SCALE;    //The actual size the sprite will be displayed as (80x80 pixels)
    final int SCREEN_TILE_COLUMNS = 16;   //Num of tiles horizontally
    final int SCREEN_TILE_ROWS = 10;    //Num of tiles vertically
    final int SCREEN_WIDTH = DISPLAYED_TILE_SIZE * SCREEN_TILE_COLUMNS;   //Horizontal resolution (80 * 16 = 1280 pixels)
    final int SCREEN_HEIGHT = DISPLAYED_TILE_SIZE * SCREEN_TILE_ROWS;    //Vertical resolution (80 * 10 = 800 pixels)

    Thread gameThread;  //allows the game to run indefinitely

    public SwingWindow() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));   //sets the window size to 1280x800 pixels
        setBackground(Color.white);
        setDoubleBuffered(true);    //rendering sprites and animations
    }

    public void startThread() {
        gameThread = new Thread(this);  //passes this class into the thread class
        gameThread.start();     //automatically calls the run method
    }
    @Override
    public void run() {

    }
}
