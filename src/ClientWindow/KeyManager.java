package ClientWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    SwingWindow sw;
    private boolean wPressed, sPressed, aPressed, dPressed;
    public KeyManager(SwingWindow sw)
    {
        this.sw = sw;
    }

    public boolean isWPressed() {
        return wPressed;
    }

    public boolean isSPressed() {
        return sPressed;
    }

    public boolean isAPressed() {
        return aPressed;
    }

    public boolean isDPressed() {
        return dPressed;
    }

    @Override
    public void keyTyped(KeyEvent e) {/*unused*/}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();  //gets user keystroke

        //TITLE SCREEN STATE
        if (sw.gameState == sw.TITLE_SCREEN_STATE)
        {
            if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
                sw.ui.optionNum--;
                if (sw.ui.optionNum < 0)
                {
                    sw.ui.optionNum = 2;
                }
            }
            if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
                sw.ui.optionNum++;
                if (sw.ui.optionNum > 2)
                {
                    sw.ui.optionNum = 0;
                }
            }
            if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_SPACE) {
                if (sw.ui.optionNum == 0)  //start game
                {
                    sw.gameState = sw.PLAY_STATE;
                    sw.player.setDefaultValues();
                }
                else if (sw.ui.optionNum == 1)  //load game
                {
                    System.out.println("Load games placeholder");  //for testing
                }
                else {
                    System.exit(0);  //exit game
                }
            }
        }

        //PLAY STATE
        if (sw.gameState != sw.PAUSED_STATE) {  //prevents inputs when paused or in title screen
            if (keyCode == KeyEvent.VK_W) {
                wPressed = true;
            }
            if (keyCode == KeyEvent.VK_S) {
                sPressed = true;
            }
            if (keyCode == KeyEvent.VK_A) {
                aPressed = true;
            }
            if (keyCode == KeyEvent.VK_D) {
                dPressed = true;
            }
        }

        //PAUSED STATE
        if (sw.gameState == sw.PAUSED_STATE) {
            if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
                sw.ui.optionNum--;
                if (sw.ui.optionNum < 0)
                {
                    sw.ui.optionNum = 2;
                }
            }
            if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
                sw.ui.optionNum++;
                if (sw.ui.optionNum > 2)
                {
                    sw.ui.optionNum = 0;
                }
            }
            if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_SPACE) {
                if (sw.ui.optionNum == 0)  //start game
                {
                    sw.gameState = sw.TITLE_SCREEN_STATE;
                }
                else if (sw.ui.optionNum == 1)  //load game
                {
                    /*Code for saving game*/
                }
                else {
                    sw.gameState = sw.PLAY_STATE;
                }
            }
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            if (sw.gameState == sw.PLAY_STATE) {
                sw.gameState = sw.PAUSED_STATE;
                sw.ui.optionNum = 0;
            }
            System.out.println("pressed!");
            if (sw.gameState == sw.LOSE_STATE) {
                System.out.println("changed!");
                sw.gameState = sw.TITLE_SCREEN_STATE;
                System.out.println(sw.gameState);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();  //gets user keystroke
        if (keyCode == KeyEvent.VK_W) {
            wPressed = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            sPressed = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            aPressed = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            dPressed = false;
        }
    }
}
