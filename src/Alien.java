
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
    private final String strShot = "Alien.png";

    public Alien(int x, int y) {
        this.iX = x;
        this.iY = y;

        bomBomb = new Bomb(x, y);
        ImageIcon iicImagen = new ImageIcon(this.getClass().getResource(strShot));
        setImage(iicImagen);

    }

    public void act(int direction) {
        this.iX += direction;
    }

    public Bomb getBomb() {
        return bomBomb;
    }

    public class Bomb extends Sprite {

        private final String strBomb = "bomb.png";
        private boolean bolDestroyed;

        public Bomb(int x, int y) {
            setDestroyed(true);
            this.iX = x;
            this.iY = y;
            ImageIcon iicImagen = new ImageIcon(this.getClass().getResource(strBomb));
            setImage(iicImagen);
        }

        public void setDestroyed(boolean destroyed) {
            this.bolDestroyed = destroyed;
        }

        public boolean isDestroyed() {
            return bolDestroyed;
        }
    }
}
