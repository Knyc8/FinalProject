package Characters.enemies;

import Characters.Entity;
import ClientWindow.SwingWindow;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BasicEnemy extends Entity {
    SwingWindow sw;
    public BasicEnemy(SwingWindow sw)
    {
        super(sw);
        name = "Green Slime";
        speed = 2;
        maxHp = 2;
        hp = maxHp;

        hitbox.x = 3;
        hitbox.y  =18;
        hitbox.width = 42;
        hitbox.height = 30;
    }

    public void getImge() {
        try {
            front1 = ImageIO.read(getClass().getResource("/enemy_sprites/slime_1.png"));
            front2 = ImageIO.read(getClass().getResource("/enemy_sprites/slime_2.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
