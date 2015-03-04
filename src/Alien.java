
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author http://zetcode.com/
 */
public class Alien extends Sprite {

    private Bomb bomBomb;
    private final String strShot = "alien.png";

    public Alien(int x, int y) {
        this.x = x;
        this.y = y;

        bomBomb = new Bomb(x, y);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(strShot));
        setImage(ii.getImage());

    }

    public void act(int direction) {
        this.x += direction;
    }

    public Bomb getBomb() {
        return bomBomb;
    }

    public class Bomb extends Sprite {

        private final String strBomb = "bomb.png";
        private boolean destroyed;

        public Bomb(int x, int y) {
            setDestroyed(true);
            this.x = x;
            this.y = y;
            ImageIcon ii = new ImageIcon(this.getClass().getResource(strBomb));
            setImage(ii.getImage());
        }

        public void setDestroyed(boolean destroyed) {
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
            return destroyed;
        }
    }
}
