package ClientWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OnScreenUI {
    SwingWindow sw;
    Font font;
    BufferedImage DFIcon;
    int optionNum = 0;

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

    /***
     * Displays the UI drawn in the different game states
     *
     * @param graphics2D
     */
    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(font);
        graphics2D.setColor(Color.white);

        //TITLE SCREEN STATE
        if (sw.gameState == sw.TITLE_SCREEN_STATE) {
            drawTitleScreen(graphics2D);
        }
        //PLAY STATE
        else if (sw.gameState == sw.PLAY_STATE) {
            drawPlayUI(graphics2D);
        }
        //PAUSED STATE
        else if (sw.gameState == sw.PAUSED_STATE) {
            drawPausedScreen(graphics2D);
        }
    }

    /***
     * Draws the title screen upon first execution
     *
     * @param graphics2D
     */
    private void drawTitleScreen(Graphics2D graphics2D) {
        //Background
        graphics2D.setColor(Color.darkGray);  //50% opacity
        graphics2D.fillRect(0,0, sw.SCREEN_WIDTH, sw.SCREEN_HEIGHT);
        int door1X = sw.SCREEN_WIDTH/4 - (sw.getDISPLAYED_TILE_SIZE()*15)/4;
        int door1Y = (sw.getDISPLAYED_TILE_SIZE()*5)/2;
        graphics2D.drawImage(DFIcon, door1X, door1Y, sw.getDISPLAYED_TILE_SIZE()*5, sw.getDISPLAYED_TILE_SIZE()*5, null);  //1st door
        int door2X = sw.SCREEN_WIDTH/2 - (sw.getDISPLAYED_TILE_SIZE()*5)/2 - 4;
        int door2Y = (sw.getDISPLAYED_TILE_SIZE()*5)/2;
        graphics2D.drawImage(DFIcon, door2X, door2Y, sw.getDISPLAYED_TILE_SIZE()*5, sw.getDISPLAYED_TILE_SIZE()*5, null);  //2nd door
        int door3X = (sw.SCREEN_WIDTH*3)/4 - (sw.getDISPLAYED_TILE_SIZE()*5)/4;
        int door3Y = (sw.getDISPLAYED_TILE_SIZE()*5)/2;
        graphics2D.drawImage(DFIcon, door3X, door3Y, sw.getDISPLAYED_TILE_SIZE()*5, sw.getDISPLAYED_TILE_SIZE()*5, null);  //3rd door

        //TITLE
        graphics2D.setFont(new Font("Arial", font.getStyle(), 100));
        String displayText = "2D Dungeon Game";
        int length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        int x = sw.getSCREEN_WIDTH()/2 - length/2;
        int y = (sw.getDISPLAYED_TILE_SIZE()*3)/2;
        graphics2D.setColor(new Color(27, 27, 27));
        graphics2D.drawString(displayText, x+5, y+5);  //shadow
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(displayText, x, y);

        //OPTIONS MENU
        graphics2D.setFont(new Font("Arial", font.getStyle(), 55));
        displayText = "Start Game";
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        x -= sw.getSCREEN_WIDTH()/3;
        y = sw.getDISPLAYED_TILE_SIZE()*8 - sw.getDISPLAYED_TILE_SIZE()/4;
        graphics2D.drawString(displayText, x, y);
        if (optionNum == 0)
        {
           // graphics2D.drawString(">", x-sw.getDISPLAYED_TILE_SIZE()/2, y);
            x = (door1X + sw.getDISPLAYED_TILE_SIZE()*5)/2 - (sw.getDISPLAYED_TILE_SIZE()*4)/3;
            y = (door1Y + sw.getDISPLAYED_TILE_SIZE()*5)/2;
            graphics2D.drawImage(sw.player.front3, x, y, sw.getDISPLAYED_TILE_SIZE()*3, sw.getDISPLAYED_TILE_SIZE()*3,null);
        }

        displayText = "Load Game";
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        y = sw.getDISPLAYED_TILE_SIZE()*8 - sw.getDISPLAYED_TILE_SIZE()/4;
        graphics2D.drawString(displayText, x, y);
        if (optionNum == 1)
        {
            //graphics2D.drawString(">", x-sw.getDISPLAYED_TILE_SIZE()/2, y);
            x = sw.getSCREEN_WIDTH()/2 - (sw.DISPLAYED_TILE_SIZE*3)/2;
            y = (door2Y + sw.getDISPLAYED_TILE_SIZE()*5)/2;
            graphics2D.drawImage(sw.player.front3, x, y, sw.getDISPLAYED_TILE_SIZE()*3, sw.getDISPLAYED_TILE_SIZE()*3,null);
        }

        displayText = "Quit to Desktop";
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        x += sw.getSCREEN_WIDTH()/3;
        y = sw.getDISPLAYED_TILE_SIZE()*8 - sw.getDISPLAYED_TILE_SIZE()/4;
        graphics2D.drawString(displayText, x, y);
        if (optionNum == 2)
        {
            //graphics2D.drawString(">", x-sw.getDISPLAYED_TILE_SIZE()/2, y);
            x = door3X + (door1X + sw.getDISPLAYED_TILE_SIZE()*3)/2 - (sw.getDISPLAYED_TILE_SIZE()*7)/12;
            y = (door3Y + sw.getDISPLAYED_TILE_SIZE()*5)/2;
            graphics2D.drawImage(sw.player.front3, x, y, sw.getDISPLAYED_TILE_SIZE()*3, sw.getDISPLAYED_TILE_SIZE()*3,null);
        }
    }

    /***
     * Draws any onscreen UI during the play state (E.G. hearts, current floor, etc.)
     *
     * @param graphics2D
     */
    private void drawPlayUI(Graphics2D graphics2D) {
        graphics2D.setFont(font);
        graphics2D.drawImage(DFIcon, sw.SCREEN_WIDTH-(sw.getDISPLAYED_TILE_SIZE() +15), 15, sw.getDISPLAYED_TILE_SIZE(), sw.getDISPLAYED_TILE_SIZE(), null);
        graphics2D.drawString("1", sw.SCREEN_WIDTH - 73, 80);
    }

    /***
     * Draws the pause screen when the esc is hit
     *
     * @param graphics2D
     */
    private void drawPausedScreen(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(0,0,0,127));  //50% opacity
        graphics2D.fillRect(0,0, sw.SCREEN_WIDTH, sw.SCREEN_HEIGHT);
        graphics2D.setColor(Color.white);
        graphics2D.setFont(font.deriveFont(font.BOLD, 100F));
        String displayText = "PAUSED";
        int length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        int x = sw.getSCREEN_WIDTH()/2 - length/2;
        int y = sw.getSCREEN_HEIGHT()/3;

        graphics2D.drawString(displayText, x, y);

        //OPTIONS MENU
        graphics2D.setFont(new Font("Arial", font.getStyle(), 45));
        displayText = "Return to Title Screen";
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        y = sw.getSCREEN_HEIGHT()/2;
        graphics2D.drawString(displayText, x, y);
        if (optionNum == 0)
        {
            graphics2D.setColor(new Color(255,255,255,127));
            graphics2D.fillOval(x-sw.getDISPLAYED_TILE_SIZE()/2 - (45/4)-1, y-(40), 45, 45);
            graphics2D.drawImage(sw.player.front3, x-sw.getDISPLAYED_TILE_SIZE()/2 - (45/4), y-(39), 45, 45, null);
            graphics2D.setColor(new Color(255,255,255));
        }

        displayText = "Save Game";
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        y = sw.getSCREEN_HEIGHT()/2 + sw.getDISPLAYED_TILE_SIZE();
        graphics2D.drawString(displayText, x, y);
        if (optionNum == 1)
        {
            graphics2D.setColor(new Color(255,255,255,127));
            graphics2D.fillOval(x-sw.getDISPLAYED_TILE_SIZE()/2 - (45/4)-1, y-(40), 45, 45);
            graphics2D.drawImage(sw.player.front3, x-sw.getDISPLAYED_TILE_SIZE()/2 - (45/4), y-(39), 45, 45, null);
            graphics2D.setColor(new Color(255,255,255));
        }

        displayText = "Resume";
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        y = sw.getSCREEN_HEIGHT()/2 + sw.getDISPLAYED_TILE_SIZE()*2;
        graphics2D.drawString(displayText, x, y);
        if (optionNum == 2)
        {
            graphics2D.setColor(new Color(255,255,255,127));
            graphics2D.fillOval(x-sw.getDISPLAYED_TILE_SIZE()/2 - (45/4)-1, y-(40), 45, 45);
            graphics2D.drawImage(sw.player.front3, x-sw.getDISPLAYED_TILE_SIZE()/2 - (45/4), y-(39), 45, 45, null);
            graphics2D.setColor(new Color(255,255,255));
        }
    }
}
