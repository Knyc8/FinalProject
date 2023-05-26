package Characters;

import ClientWindow.SwingWindow;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Projectile extends Entity{
    Entity user;

    public Projectile(SwingWindow sw) {
        super(sw);

        name = "fireball";
        speed = 10;
        maxHp = 80;
        hp = maxHp;
        dmg = 1;
    }

    public void getImg() {
        try {
             back1 = ImageIO.read(getClass().getResource("/projectiles/fball_up.png"));
             front1 = ImageIO.read(getClass().getResource("/projectiles/fball_down.png"));
             left1 = ImageIO.read(getClass().getResource("/projectiles/fball_left.png"));
             right1 = ImageIO.read(getClass().getResource("/projectiles/fball_right.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void set(int xCoord, int yCoord, String dir, Boolean alive, Entity user){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        direction = dir;
        this.alive = alive;
        this.user = user;
        this.hp = this.maxHp;
    }

    public void update() {
        if (direction.equals("back")) {  //up speed units
            yCoord -= speed;  //top left is (0, 0)
        }
        if (direction.equals("front")) {  //down speed units
            yCoord += speed;
        }
        if (direction.equals("left")) {  //left speed units
            xCoord -= speed;
        }
        if (direction.equals("right")) {  //right speed units
            xCoord += speed;
        }

        hp--;
        if (hp <= 0) {
            alive = false;
        }
    }
}
