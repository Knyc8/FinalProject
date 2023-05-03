package ClientWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    private boolean wPressed, sPressed, aPressed, dPressed;

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
            System.out.println("W");  //for testing
            wPressed = true;
        }
        if (keyCode == KeyEvent.VK_S) {
            System.out.println("S");  //for testing
            sPressed = true;
        }
        if (keyCode == KeyEvent.VK_A) {
            System.out.println("A");  //for testing
            aPressed = true;
        }
        if (keyCode == KeyEvent.VK_D) {
            System.out.println("D");  //for testing
            dPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();  //gets user keystroke
        if (keyCode == KeyEvent.VK_W) {
            System.out.println("W released");  //for testing
            wPressed = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            System.out.println("S released");  //for testing
            sPressed = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            System.out.println("A released");  //for testing
            aPressed = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            System.out.println("D released");  //for testing
            dPressed = false;
        }
    }
}
