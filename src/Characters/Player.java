package Characters;

import ClientWindow.KeyManager;
import ClientWindow.SwingWindow;

import javax.swing.text.Keymap;
import java.awt.*;

public class Player extends Entity{
    SwingWindow sw;
    KeyManager km;

    /***
     * Initializes the SwingWindow and KeyManager
     *
     * @param sw represents the GUI
     * @param km represents the key manager
     */
    public Player (SwingWindow sw, KeyManager km) {
        this.sw = sw;
        this.km = km;
    }

    /***
     * Sets the players intial location, speed, and direction
     */
    public void setDefaultValues() {
        xCoord = 100;
        yCoord = 100;
        speed = 5;
        direction = "front";
    }

    public void getPlayerSprite() {}

    public void updateInfo() {
        //Character movement
        if (km.isWPressed())  //up speed units
        {
            yCoord -= speed;  //top left is (0, 0)
        }
        if (km.isSPressed())  //down speed units
        {
            yCoord += speed;
        }
        if (km.isAPressed())  //left speed units
        {
            xCoord -= speed;
        }
        if (km.isDPressed())  //right speed units
        {
            xCoord += speed;
        }
    }

    public void drawPlayer(Graphics2D graphic2D) {
        graphic2D.setColor(Color.black);
        graphic2D.fillRect(xCoord, yCoord, DISPLAYED_TILE_SIZE, DISPLAYED_TILE_SIZE);  //for testing character sprites
    }
}
