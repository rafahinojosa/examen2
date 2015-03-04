
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
public class Shot extends Sprite {

    private String strShot = "shot.png";
    private final int H_SPACE = 6;
    private final int V_SPACE = 1;

    public Shot() {
    }

    public Shot(int x, int y) {

        ImageIcon iicImagen = new ImageIcon(this.getClass().getResource(strShot));
        setImage(iicImagen);
        setX(x + H_SPACE);
        setY(y - V_SPACE);
    }
}