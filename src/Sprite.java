
import java.awt.Image;
import java.awt.Rectangle;
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
public class Sprite {

        private boolean bolVisible;
        private ImageIcon iicImagen;
        protected int iAlto; // alto del sprite
        protected int iAncho; // ancho del sprite
        protected int iX;
        protected int iY;
        protected boolean bolDying;
        protected int iDx;

        public Sprite() {
            bolVisible = true;
        }

        public void die() {
            bolVisible = false;
        }

        public boolean isVisible() {
            return bolVisible;
        }

        protected void setVisible(boolean visible) {
            this.bolVisible = visible;
        }

        public void setImage(ImageIcon imaImagen) {
            this.iicImagen = imaImagen;
        }

        public Image getImage() {
            return iicImagen.getImage();
        }

        public void setX(int x) {
            this.iX = x;
        }

        public void setY(int y) {
            this.iY = y;
        }
        public int getY() {
            return iY;
        }

        public int getX() {
            return iX;
        }
        
        public void setAncho(int ancho) {
            iAncho = ancho;
        }
        
        public void setAlto(int alto) {
            iAncho = alto;
        }
        
        public int getAncho() {
            return iAncho;
        }
        
        public int getAlto() {
            return iAlto;
        }

        public void setDying(boolean dying) {
            this.bolDying = dying;
        }

        public boolean isDying() {
            return this.bolDying;
        }
        
                
        public boolean intersecta(Object objObjeto) {
        if (objObjeto instanceof Sprite) {
            Rectangle rctEste = new Rectangle(this.getX(), this.getY(), 
                                this.getAncho(), this.getAlto());
            Sprite sprTemp = (Sprite) objObjeto;
            Rectangle rctParam = new Rectangle(sprTemp.getX(),sprTemp.getY(),
                                sprTemp.getAncho(), sprTemp.getAlto());
            
            return rctEste.intersects(rctParam);
        }
        return false;
    }
}

