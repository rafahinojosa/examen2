
import java.awt.event.KeyEvent;
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
public class Player extends Sprite implements Commons{

    private final int START_Y = 100; 
    private final int START_X = 270;
    
    private boolean bLeft; // si esta oprimida la tecla a la derecha
    private boolean bRight; // si esta oprimida la tecla a la izquierda

    private final String strPlayer = "player.png";
    private int iWidth;

    public Player() {

        ImageIcon iicImagen = new ImageIcon(this.getClass().getResource(strPlayer));

        iWidth = iicImagen.getImage().getWidth(null); 

        setImage(iicImagen);
        setX(START_X);
        setY(START_Y);
        setAncho(PLAYER_WIDTH);
        setAlto(PLAYER_HEIGHT);
    }

    public void act() {
        
        if (bLeft || bRight) {
            iX += iDx;
        }

        if (iX <= 2) 
          iX = 2;
        if (iX >= BOARD_WIDTH - 2*iWidth) 
          iX = BOARD_WIDTH - 2*iWidth;
    }

    public void keyPressed(KeyEvent keyEvent) {
        int iKey = keyEvent.getKeyCode();

        if (iKey == KeyEvent.VK_LEFT)
        {
            bLeft = true;
            iDx = -2;
        }

        if (iKey == KeyEvent.VK_RIGHT)
        {
            bRight = true;
            iDx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            bLeft = false;
            iDx = 0;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            bRight = false;
            iDx = 0;
        }
    }
}
