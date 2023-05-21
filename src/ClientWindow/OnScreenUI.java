package ClientWindow;

import Dungeon.Tile;

import javax.imageio.ImageIO;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OnScreenUI {
    SwingWindow sw;
    final Font TNR_40;
    BufferedImage DFIcon;

    public OnScreenUI(SwingWindow sw)
    {
        this.sw = sw;
        TNR_40 = new Font("Times New Roman", Font.BOLD, 40);

        try {
            DFIcon = ImageIO.read(getClass().getResource("/icons/Dungeon_Floor_Icon.png"));

        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(DFIcon, sw.SCREEN_WIDTH-(sw.getDISPLAYED_TILE_SIZE() +15), 15, sw.getDISPLAYED_TILE_SIZE(), sw.getDISPLAYED_TILE_SIZE(), null);
        graphics2D.setFont(TNR_40);
        graphics2D.setColor(Color.white);
        graphics2D.drawString("1", sw.SCREEN_WIDTH-72, 80);
    }
}
