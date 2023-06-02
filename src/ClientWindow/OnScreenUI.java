package ClientWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class OnScreenUI {
    SwingWindow sw;
    Font dungeonFont;
    BufferedImage torch, DFIcon, wall, bacon, emptyBacon, sadCat, tDown, bCat, cBCat, arrow;
    int optionNum = 0;
    int messageCount = 0;
    String displayText = "";

    public OnScreenUI(SwingWindow sw)
    {
        this.sw = sw;

        try {
            InputStream inputStream = getClass().getResourceAsStream("/fonts/DungeonFont.TTF");
            dungeonFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (FontFormatException ffe) {
            ffe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            torch = ImageIO.read(getClass().getResource("/player_sprites/pigzard_torch.png"));
            DFIcon = ImageIO.read(getClass().getResource("/icons/Dungeon_Floor_Icon.png"));
            wall = ImageIO.read(getClass().getResource("/icons/Wall.png"));
            bacon = ImageIO.read(getClass().getResource("/icons/fullBacon.png"));
            emptyBacon = ImageIO.read(getClass().getResource("/icons/emptyBacon.png"));
            sadCat = ImageIO.read(getClass().getResource("/icons/sadCat.png"));
            tDown = ImageIO.read(getClass().getResource("/icons/thumbsdown.png"));
            bCat = ImageIO.read(getClass().getResource("/icons/bananaCat.png"));
            cBCat = ImageIO.read(getClass().getResource("/icons/cryingCatBanana.png"));
            arrow = ImageIO.read(getClass().getResource("/icons/arrow.png"));

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
        graphics2D.setFont(dungeonFont);
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
        //LOSE STATE
        else if (sw.gameState == sw.LOSE_STATE) {
            drawLoseScreen(graphics2D);
        }
        //LOAD MENU STATE
        else if (sw.gameState == sw.LOAD_MENU_STATE){
            drawLoadScreen(graphics2D);
        }
        //LOADING STATE
        else if (sw.gameState == sw.LOADING_STATE) {
            drawLoadingScreen(graphics2D);
        }
    }

    /***
     * Draws the title screen upon first execution
     *
     * @param graphics2D
     */
    private void drawTitleScreen(Graphics2D graphics2D) {
        //Background
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0,0, sw.SCREEN_WIDTH, sw.SCREEN_HEIGHT);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));  //25% opacity
        graphics2D.drawImage(wall, 0, 0, sw.SCREEN_WIDTH/2, sw.SCREEN_HEIGHT, null);
        graphics2D.drawImage(wall, sw.SCREEN_WIDTH/2, 0, sw.SCREEN_WIDTH/2, sw.SCREEN_HEIGHT, null);

        //Border
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));  //100% opacity
        float thickness = sw.DISPLAYED_TILE_SIZE/4f;
        Stroke oldStroke = graphics2D.getStroke();
        graphics2D.setStroke(new BasicStroke(thickness));
        graphics2D.setColor(Color.white);
        graphics2D.drawRect(0, 0, sw.getSCREEN_WIDTH(), sw.SCREEN_HEIGHT);
        graphics2D.setStroke(oldStroke);

        //Doors
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));  //50% opacity
        int door1X = sw.SCREEN_WIDTH/4 - (sw.getDISPLAYED_TILE_SIZE()*15)/4;
        int door1Y = (sw.getDISPLAYED_TILE_SIZE()*5)/2;
        graphics2D.drawImage(DFIcon, door1X, door1Y, sw.getDISPLAYED_TILE_SIZE()*5, sw.getDISPLAYED_TILE_SIZE()*5, null);  //1st door
        int door2X = sw.SCREEN_WIDTH/2 - (sw.getDISPLAYED_TILE_SIZE()*5)/2 - 4;
        int door2Y = (sw.getDISPLAYED_TILE_SIZE()*5)/2;
        graphics2D.drawImage(DFIcon, door2X, door2Y, sw.getDISPLAYED_TILE_SIZE()*5, sw.getDISPLAYED_TILE_SIZE()*5, null);  //2nd door
        int door3X = (sw.SCREEN_WIDTH*3)/4 - (sw.getDISPLAYED_TILE_SIZE()*5)/4;
        int door3Y = (sw.getDISPLAYED_TILE_SIZE()*5)/2;
        graphics2D.drawImage(DFIcon, door3X, door3Y, sw.getDISPLAYED_TILE_SIZE()*5, sw.getDISPLAYED_TILE_SIZE()*5, null);  //3rd door

        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //TITLE
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 150f));
        displayText = "Wilboar's Crypt";
        int length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        int x = sw.getSCREEN_WIDTH()/2 - length/2;
        int y = sw.getDISPLAYED_TILE_SIZE()*2;
        graphics2D.setColor(new Color(27, 27, 27));
        graphics2D.drawString(displayText, x+5, y+5);  //shadow
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString(displayText, x, y);

        //OPTIONS MENU
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 55f));
        displayText = "Start Game";
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        x -= sw.getSCREEN_WIDTH()/3;
        y = sw.getDISPLAYED_TILE_SIZE()*8 - sw.getDISPLAYED_TILE_SIZE()/4;
        graphics2D.setColor(new Color(255,255,255,127));
        graphics2D.drawString(displayText, x, y);
        if (optionNum == 0)
        {
            graphics2D.setColor(Color.white);
            graphics2D.drawString(displayText, x, y);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            graphics2D.drawImage(DFIcon, door1X, door1Y, sw.getDISPLAYED_TILE_SIZE()*5, sw.getDISPLAYED_TILE_SIZE()*5, null);  //1st door
            x = (door1X + sw.getDISPLAYED_TILE_SIZE()*5)/2 - (sw.getDISPLAYED_TILE_SIZE()*4)/3;
            y = (door1Y + sw.getDISPLAYED_TILE_SIZE()*5)/2;
            graphics2D.drawImage(torch, x, y, sw.getDISPLAYED_TILE_SIZE() * 3, sw.getDISPLAYED_TILE_SIZE() * 3, null);
        }

        displayText = "Load Game";
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        y = sw.getDISPLAYED_TILE_SIZE()*8 - sw.getDISPLAYED_TILE_SIZE()/4;
        graphics2D.setColor(new Color(255,255,255,127));
        graphics2D.drawString(displayText, x, y);
        if (optionNum == 1)
        {
            //graphics2D.drawString(">", x-sw.getDISPLAYED_TILE_SIZE()/2, y);
            graphics2D.setColor(Color.white);
            graphics2D.drawString(displayText, x, y);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            graphics2D.drawImage(DFIcon, door2X, door2Y, sw.getDISPLAYED_TILE_SIZE()*5, sw.getDISPLAYED_TILE_SIZE()*5, null);  //2nd door
            x = sw.getSCREEN_WIDTH()/2 - (sw.DISPLAYED_TILE_SIZE*3)/2;
            y = (door2Y + sw.getDISPLAYED_TILE_SIZE()*5)/2;
            graphics2D.drawImage(torch, x, y, sw.getDISPLAYED_TILE_SIZE() * 3, sw.getDISPLAYED_TILE_SIZE() * 3, null);
        }

        displayText = "Quit to Desktop";
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        x += sw.getSCREEN_WIDTH()/3;
        y = sw.getDISPLAYED_TILE_SIZE()*8 - sw.getDISPLAYED_TILE_SIZE()/4;
        graphics2D.setColor(new Color(255,255,255,127));
        graphics2D.drawString(displayText, x, y);
        if (optionNum == 2)
        {
            //graphics2D.drawString(">", x-sw.getDISPLAYED_TILE_SIZE()/2, y);
            graphics2D.setColor(Color.white);
            graphics2D.drawString(displayText, x, y);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            graphics2D.drawImage(DFIcon, door3X, door3Y, sw.getDISPLAYED_TILE_SIZE()*5, sw.getDISPLAYED_TILE_SIZE()*5, null);  //3rd door
            x = door3X + (door1X + sw.getDISPLAYED_TILE_SIZE()*3)/2 - (sw.getDISPLAYED_TILE_SIZE()*7)/12;
            y = (door3Y + sw.getDISPLAYED_TILE_SIZE()*5)/2;
            graphics2D.drawImage(torch, x, y, sw.getDISPLAYED_TILE_SIZE() * 3, sw.getDISPLAYED_TILE_SIZE() * 3, null);
        }
    }

    /***
     * Draws any onscreen UI during the play state (E.G. hearts, current floor, etc.)
     *
     * @param graphics2D
     */
    private void drawPlayUI(Graphics2D graphics2D) {
        //Display health
        int xheart  = sw.DISPLAYED_TILE_SIZE/2;
        for (int i = 0; i < sw.player.hp; i++) {
            graphics2D.drawImage(bacon, xheart, 15, (sw.getDISPLAYED_TILE_SIZE()*3)/4, (sw.getDISPLAYED_TILE_SIZE()*3)/4, null);
            xheart += sw.getDISPLAYED_TILE_SIZE()/2;
        }
        for (int i = 0; i < sw.player.maxHp - sw.player.hp; i++) {
            graphics2D.drawImage(emptyBacon, xheart, 15, (sw.getDISPLAYED_TILE_SIZE()*3)/4, (sw.getDISPLAYED_TILE_SIZE()*3)/4, null);
            xheart += sw.getDISPLAYED_TILE_SIZE()/2;
        }
        graphics2D.drawImage(DFIcon, sw.SCREEN_WIDTH-(sw.getDISPLAYED_TILE_SIZE() +15), 15, sw.getDISPLAYED_TILE_SIZE(), sw.getDISPLAYED_TILE_SIZE(), null);

        //Display Level
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 75f));
        displayText = Integer.toString(sw.getPlayer().level);
        int length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();
        graphics2D.drawString(displayText, sw.SCREEN_WIDTH - 64 - length/2, 100);

        //Background rectangle
        graphics2D.setColor(Color.gray);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        graphics2D.fillRect(sw.SCREEN_WIDTH - sw.DISPLAYED_TILE_SIZE*2 - 20, 100 + sw.getDISPLAYED_TILE_SIZE()*1/3, sw.getDISPLAYED_TILE_SIZE()*2 + 10, sw.getDISPLAYED_TILE_SIZE());
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        //Display Enemies Killed
        graphics2D.setColor(Color.white);
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 35f));
        displayText = Integer.toString(sw.getPlayer().enemiesKilled);
        displayText = "Enemies Killed:  " + displayText;
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();
        graphics2D.drawString(displayText, sw.SCREEN_WIDTH - length - 15, 100 + sw.getDISPLAYED_TILE_SIZE()*2/3);

        //Display XP
        displayText = Integer.toString((sw.getPlayer().level*8) - sw.getPlayer().exp);
        displayText = "Exp til level up:  " + displayText;
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();
        graphics2D.drawString(displayText, sw.SCREEN_WIDTH - length - 15, 100 + sw.getDISPLAYED_TILE_SIZE()*3/3);

        //XP Bar
        graphics2D.setColor(Color.gray);
        graphics2D.fillRect(sw.SCREEN_WIDTH - sw.DISPLAYED_TILE_SIZE*2 - 15, 100+ sw.getDISPLAYED_TILE_SIZE()+7, sw.getDISPLAYED_TILE_SIZE()*2, sw.getDISPLAYED_TILE_SIZE()/10 + 10);
        graphics2D.setColor(Color.darkGray);
        graphics2D.fillRect(sw.SCREEN_WIDTH - sw.DISPLAYED_TILE_SIZE*2 - 12, 100+ sw.getDISPLAYED_TILE_SIZE()+10, (sw.getDISPLAYED_TILE_SIZE()*2 - 6), sw.getDISPLAYED_TILE_SIZE()/10 + 4 );
        graphics2D.setColor(Color.green);
        double xpProgress = (double) sw.getPlayer().exp/(sw.getPlayer().level*8);
        int progressWidth = (int) ((sw.getDISPLAYED_TILE_SIZE()*2 - 6) * xpProgress);
        graphics2D.fillRect(sw.SCREEN_WIDTH - sw.DISPLAYED_TILE_SIZE*2 - 12, 100+ sw.getDISPLAYED_TILE_SIZE()+10, progressWidth, sw.getDISPLAYED_TILE_SIZE()/10 + 4 );
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
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 150f));
        displayText = "PAUSED";
        int length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        int x = sw.getSCREEN_WIDTH()/2 - length/2;
        int y = sw.getSCREEN_HEIGHT()/3;

        graphics2D.drawString(displayText, x, y);

        //OPTIONS MENU
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 45f));
        displayText = "Return to Title Screen";
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        y = sw.getSCREEN_HEIGHT()/2;
        graphics2D.setColor(new Color(255,255,255,127));
        graphics2D.drawString(displayText, x, y);
        if (optionNum == 0)
        {
            graphics2D.setColor(Color.white);
            graphics2D.drawString(displayText, x, y);
            graphics2D.fillOval(x-sw.getDISPLAYED_TILE_SIZE()/2 - (45/4)-1, y-(40), 45, 45);
            graphics2D.drawImage(sw.player.front3, x-sw.getDISPLAYED_TILE_SIZE()/2 - (45/4), y-(39), 45, 45, null);
        }

        if (sw.fileManager.alreadySaved == true) {
            displayText = "Progress Saved";
            messageCount++;
        }
        else {
            displayText = "Save Game";
        }

        if (messageCount >= 120) {
            displayText = "Save Game";
            messageCount = 0;
            sw.fileManager.alreadySaved = false;
        }
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        y = sw.getSCREEN_HEIGHT()/2 + sw.getDISPLAYED_TILE_SIZE();
        graphics2D.setColor(new Color(255,255,255,127));
        graphics2D.drawString(displayText, x, y);
        if (optionNum == 1)
        {
            graphics2D.setColor(Color.white);
            graphics2D.drawString(displayText, x, y);
            graphics2D.fillOval(x-sw.getDISPLAYED_TILE_SIZE()/2 - (45/4)-1, y-(40), 45, 45);
            graphics2D.drawImage(sw.player.front3, x-sw.getDISPLAYED_TILE_SIZE()/2 - (45/4), y-(39), 45, 45, null);
        }

        displayText = "Resume";
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        y = sw.getSCREEN_HEIGHT()/2 + sw.getDISPLAYED_TILE_SIZE()*2;
        graphics2D.setColor(new Color(255,255,255,127));
        graphics2D.drawString(displayText, x, y);
        if (optionNum == 2)
        {
            graphics2D.setColor(Color.white);
            graphics2D.drawString(displayText, x, y);
            graphics2D.fillOval(x-sw.getDISPLAYED_TILE_SIZE()/2 - (45/4)-1, y-(40), 45, 45);
            graphics2D.drawImage(sw.player.front3, x-sw.getDISPLAYED_TILE_SIZE()/2 - (45/4), y-(39), 45, 45, null);
        }
    }

    public void drawLoseScreen(Graphics2D graphics2D) {
        graphics2D.setColor(new Color(0,0,0));  //50% opacity
        graphics2D.fillRect(0,0, sw.SCREEN_WIDTH, sw.SCREEN_HEIGHT);
        graphics2D.setColor(Color.white);
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 150f));
        displayText = "You Lose!";
        int length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        int x = sw.getSCREEN_WIDTH()/2 - length/2;
        int y = sw.getSCREEN_HEIGHT()/2;
        graphics2D.drawString(displayText, x, y);

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 35f));
        displayText = "ESC to Return to Title Screen";
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        y = sw.getSCREEN_HEIGHT()*3/5;
        graphics2D.drawString(displayText, x, y);

        x = 0;
        y = sw.getSCREEN_HEIGHT()/2- sw.getDISPLAYED_TILE_SIZE()*2;
        graphics2D.drawImage(tDown, x, y, sw.getDISPLAYED_TILE_SIZE()*5,sw.getDISPLAYED_TILE_SIZE()*5, null );
        x = sw.getSCREEN_WIDTH()-sw.getDISPLAYED_TILE_SIZE()*11/2;
        y = sw.getSCREEN_HEIGHT()/2- sw.getDISPLAYED_TILE_SIZE()*3;
        graphics2D.drawImage(sadCat, x, y, sw.getDISPLAYED_TILE_SIZE()*6,sw.getDISPLAYED_TILE_SIZE()*6, null );

        x = sw.getSCREEN_WIDTH()/2 - sw.getDISPLAYED_TILE_SIZE();
        y = sw.getSCREEN_HEIGHT()- sw.getDISPLAYED_TILE_SIZE()*3;
        graphics2D.drawImage(arrow, x, y, sw.getDISPLAYED_TILE_SIZE()*2,sw.getDISPLAYED_TILE_SIZE()*2, null );
        x = sw.getSCREEN_WIDTH()/3 - sw.getDISPLAYED_TILE_SIZE();
        graphics2D.drawImage(bCat, x, y, sw.getDISPLAYED_TILE_SIZE()*4,sw.getDISPLAYED_TILE_SIZE()*2, null );
        x = sw.getSCREEN_WIDTH()*2/3 - sw.getDISPLAYED_TILE_SIZE()*2;
        graphics2D.drawImage(cBCat, x, y, sw.getDISPLAYED_TILE_SIZE()*2,sw.getDISPLAYED_TILE_SIZE()*2, null );
    }

    public void drawLoadScreen(Graphics2D graphics2D) {
        //Background
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0,0, sw.SCREEN_WIDTH, sw.SCREEN_HEIGHT);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));  //25% opacity
        graphics2D.drawImage(wall, 0, 0, sw.SCREEN_WIDTH/2, sw.SCREEN_HEIGHT, null);
        graphics2D.drawImage(wall, sw.SCREEN_WIDTH/2, 0, sw.SCREEN_WIDTH/2, sw.SCREEN_HEIGHT, null);

        //Load Box
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 50f));
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        graphics2D.setColor(Color.white);
        displayText = "Load Recent Save: ";
        int length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        int x = sw.getDISPLAYED_TILE_SIZE()*7/2-length/2;
        int y = sw.getDISPLAYED_TILE_SIZE()*2 - 5;
        graphics2D.drawString(displayText, x, y);

        x = sw.getDISPLAYED_TILE_SIZE()*2;
        y = sw.getDISPLAYED_TILE_SIZE()*2;
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        graphics2D.setColor(Color.darkGray);
        graphics2D.fillRect(x, y, sw.SCREEN_WIDTH-sw.DISPLAYED_TILE_SIZE*4, sw.getDISPLAYED_TILE_SIZE()*3);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        float thickness = sw.DISPLAYED_TILE_SIZE/10f;
        Stroke oldStroke = graphics2D.getStroke();
        graphics2D.setStroke(new BasicStroke(thickness));
        graphics2D.setColor(new Color(255,255,255,127));
        graphics2D.drawRect(x, y, sw.SCREEN_WIDTH-sw.DISPLAYED_TILE_SIZE*4, sw.getDISPLAYED_TILE_SIZE()*3);
        if (optionNum == 0)
        {
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            graphics2D.setColor(Color.white);
            graphics2D.drawRect(x, y, sw.SCREEN_WIDTH-sw.DISPLAYED_TILE_SIZE*4, sw.getDISPLAYED_TILE_SIZE()*3);
            graphics2D.setStroke(oldStroke);
        }
        graphics2D.setStroke(oldStroke);

        //Save File Data
        if (sw.fileManager.alreadyLoaded) {
            x = sw.getDISPLAYED_TILE_SIZE() * 2 - 7;
            y = sw.getDISPLAYED_TILE_SIZE() * 2 + 17;
            graphics2D.drawImage(torch, x, y, sw.getDISPLAYED_TILE_SIZE() * 5 / 2, sw.getDISPLAYED_TILE_SIZE() * 5 / 2, null);

            displayText = "Hitpoints: " + sw.getPlayer().hp + "/" + sw.getPlayer().maxHp;
            displayText += " - - - - - Current Level: " + sw.getPlayer().level;
            displayText += " - - - - - Exp: " + sw.getPlayer().exp + "/" + sw.getPlayer().level * 8;
            x = sw.getDISPLAYED_TILE_SIZE() * 5;
            y = sw.getDISPLAYED_TILE_SIZE() * 3;
            graphics2D.drawString(displayText, x, y);
            displayText = "Enemies Killed: " + sw.getPlayer().enemiesKilled + "/" + sw.monsters.length;
            x = sw.getDISPLAYED_TILE_SIZE() * 7;
            y = sw.getDISPLAYED_TILE_SIZE() * 11 / 3;
            graphics2D.drawString(displayText, x, y);
            displayText = "X Position: " + sw.getPlayer().xCoord + " - - - - - ";
            displayText += "Y Position: " + sw.getPlayer().yCoord + " - - - - - ";
            displayText += "Facing :" + sw.getPlayer().direction;
            x = sw.getDISPLAYED_TILE_SIZE() * 5 - 25;
            y = sw.getDISPLAYED_TILE_SIZE() * 13 / 3;
            graphics2D.drawString(displayText, x, y);
        }
        else {
            graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 80f));
            displayText = "No Files Saved";
            length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
            x = sw.getSCREEN_WIDTH()/2 - length/2;
            y = sw.getDISPLAYED_TILE_SIZE() * 4;
            graphics2D.drawString(displayText, x, y);
        }

        //Return to title screen
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 50f));
        displayText = "Return to Title Screen";
        length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        x = sw.getSCREEN_WIDTH()/2 - length/2;
        y = sw.getSCREEN_HEIGHT()/2 + sw.DISPLAYED_TILE_SIZE*3/2;
        graphics2D.setColor(new Color(255,255,255,127));
        graphics2D.drawString(displayText, x, y);
        if (optionNum == 1)
        {
            graphics2D.setColor(Color.white);
            graphics2D.drawString(displayText, x, y);
        }
    }

    public void drawLoadingScreen(Graphics2D graphics2D) {
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0,0, sw.SCREEN_WIDTH, sw.SCREEN_HEIGHT);

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 100f));
        graphics2D.setColor(Color.white);
        displayText = "LOADING...";
        int length = (int)graphics2D.getFontMetrics().getStringBounds(displayText, graphics2D).getWidth();  //centers the text
        int x = sw.getSCREEN_WIDTH()/2 - length/2;
        int y = sw.getSCREEN_HEIGHT()/2;
        graphics2D.drawString(displayText, x, y);
    }

}
