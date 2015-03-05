
import java.awt.Image;
import java.awt.Rectangle;


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
        private Image imaImagen;
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

        public void setImage(Image imaImagen) {
            this.imaImagen = imaImagen;
        }

        public Image getImage() {
            return imaImagen;
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
            iAlto = alto;
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

