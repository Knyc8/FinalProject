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
        if (keyCode == KeyEvent.VK_W) {
            if (sw.gameState != sw.PAUSED_STATE) {  //prevents inputs when paused
                wPressed = true;
            }
        }
        if (keyCode == KeyEvent.VK_S) {
            if (sw.gameState != sw.PAUSED_STATE) {  //prevents inputs when paused
                sPressed = true;
            }
        }
        if (keyCode == KeyEvent.VK_A) {
            if (sw.gameState != sw.PAUSED_STATE) {  //prevents inputs when paused
                aPressed = true;
            }
        }
        if (keyCode == KeyEvent.VK_D) {
            if (sw.gameState != sw.PAUSED_STATE) {  //prevents inputs when paused
                dPressed = true;
            }
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            if (sw.gameState == sw.PLAY_STATE) {  //prevents inputs when paused
                sw.gameState = sw.PAUSED_STATE;
            }
            else if (sw.gameState == sw.PAUSED_STATE) {
                sw.gameState = sw.PLAY_STATE;
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
