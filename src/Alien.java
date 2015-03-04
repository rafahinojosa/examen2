
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriel Berlanga y Rafael Hinojosa
 */
public class Alien extends Sprite {

    private Bomb bomBomb; // variable de tipo bomba
    private final String strShot = "Alien.png"; // string para asiganr imagen

    public Alien(int x, int y) {
        this.iX = x;
        this.iY = y;
        
        // se crea objeto bomba
        bomBomb = new Bomb(x, y);
        ImageIcon iicImagen = new ImageIcon(this.getClass().getResource(strShot));
        setImage(iicImagen);

    }

    public void act(int direction) {
        this.iX += direction;
    }
    /**
     * getBomb
     * 
     * Metodo para obtener bomBomb
     * @return  bomBomb
     */
    public Bomb getBomb() {
        return bomBomb;
    }

    
    public class Bomb extends Sprite {

        private final String strBomb = "bomb.png"; // string para imagen bomb
        private boolean bolDestroyed; // Booleana de destruido

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
