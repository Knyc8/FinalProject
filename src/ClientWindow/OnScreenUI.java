package ClientWindow;

import Dungeon.Tile;

import javax.imageio.ImageIO;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OnScreenUI {
    SwingWindow sw;
    Font font;
    BufferedImage DFIcon;

    public OnScreenUI(SwingWindow sw)
    {
        this.sw = sw;
        font = new Font("Times New Roman", Font.BOLD, 40);

        try {
            DFIcon = ImageIO.read(getClass().getResource("/icons/Dungeon_Floor_Icon.png"));

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(font);
        graphics2D.setColor(Color.white);

        if (sw.gameState == sw.PLAY_STATE) {
            graphics2D.setFont(font);
            graphics2D.drawImage(DFIcon, sw.SCREEN_WIDTH-(sw.getDISPLAYED_TILE_SIZE() +15), 15, sw.getDISPLAYED_TILE_SIZE(), sw.getDISPLAYED_TILE_SIZE(), null);
            graphics2D.drawString("1", sw.SCREEN_WIDTH - 73, 80);
        }
        else if (sw.gameState == sw.PAUSED_STATE) {
            graphics2D.setColor(new Color(0,0,0,127));  //50% opacity
            graphics2D.fillRect(0,0, sw.SCREEN_WIDTH, sw.SCREEN_HEIGHT);
            graphics2D.setColor(Color.white);
            graphics2D.setFont(new Font(font.getFontName(), font.getStyle(), 100));
            String displayText = "PAUSED";
            int length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();
            int x = sw.getSCREEN_WIDTH()/2 - length/2;
            graphics2D.drawString(displayText, x, sw.getSCREEN_HEIGHT()/2);
        }
    }
}
