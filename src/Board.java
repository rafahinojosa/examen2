
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author http://zetcode.com/
 */
public class Board extends JPanel implements Runnable, Commons { 

    private Dimension dimDimension;
    private ArrayList arlAliens;
    private Player plyPlayer;
    private Shot shtShot;

    private int iAlienX = 150;
    private int iAlienY = 5;
    private int iDireccion = -1;
    private int iDeaths = 0;

    private boolean bIngame = true;
    private final String strExpl = "explosion.png";
    private final String strAlienpix = "alien.png";
    private String strMessage = "Game Over";

    private Thread animator;

    public Board() 
    {

        addKeyListener(new TAdapter());
        setFocusable(true);
        dimDimension = new Dimension(BOARD_WIDTH, BOARD_HEIGTH);
        setBackground(Color.black);

        gameInit();
        setDoubleBuffered(true);
    }

    public void addNotify() {
        super.addNotify();
        gameInit();
    }

    public void gameInit() {

        arlAliens = new ArrayList();

        ImageIcon iicImagen = new ImageIcon(this.getClass().getResource(strAlienpix));

        for (int i=0; i < 4; i++) {
            for (int j=0; j < 6; j++) {
                Alien alien = new Alien(iAlienX + 18*j, iAlienY + 18*i,
                                    ALIEN_HEIGHT, ALIEN_WIDTH);
                alien.setImage(iicImagen);
                arlAliens.add(alien);
            }
        }

        plyPlayer = new Player();
        shtShot = new Shot();

        if (animator == null || !bIngame) {
            animator = new Thread(this);
            animator.start();
        }
    }

    public void drawAliens(Graphics g) 
    {
        Iterator iteI = arlAliens.iterator();

        while (iteI.hasNext()) {
            Alien alien = (Alien) iteI.next();

            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    public void drawPlayer(Graphics g) {

        if (plyPlayer.isVisible()) {
            g.drawImage(plyPlayer.getImage(), plyPlayer.getX(),
                            plyPlayer.getY(), this);
        }

        if (plyPlayer.isDying()) {
            plyPlayer.die();
            bIngame = false;
        }
    }

    public void drawShot(Graphics g) {
        if (shtShot.isVisible())
            g.drawImage(shtShot.getImage(), shtShot.getX(),
                            shtShot.getY(), this);
    }

    public void drawBombing(Graphics g) {

        Iterator ite3 = arlAliens.iterator();

        while (ite3.hasNext()) {
            Alien a = (Alien) ite3.next();

            Alien.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this); 
            }
        }
    }

    public void paint(Graphics g)
    {
      super.paint(g);

      g.setColor(Color.black);
      g.fillRect(0, 0, dimDimension.width, dimDimension.height);
      g.setColor(Color.green);   

      if (bIngame) {

        g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
        drawAliens(g);
        drawPlayer(g);
        drawShot(g);
        drawBombing(g);
      }

      Toolkit.getDefaultToolkit().sync();
      g.dispose();
    }

    public void gameOver()
    {

        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, BOARD_WIDTH/2 - 30, BOARD_WIDTH-100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH/2 - 30, BOARD_WIDTH-100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(strMessage, (BOARD_WIDTH - metr.stringWidth(strMessage))/2, 
            BOARD_WIDTH/2);
    }
    
    public void animationCycle()  {

        if (iDeaths == NUMBER_OF_ALIENS_TO_DESTROY) {
            bIngame = false;
            strMessage = "Game won!";
        }

        // player

        plyPlayer.act();

        // shot
        if (shtShot.isVisible()) {
            Iterator iteI = arlAliens.iterator();

            while (iteI.hasNext()) {
                Alien alien = (Alien) iteI.next();

                if (alien.isVisible() && shtShot.isVisible()) {
                    if (alien.intersecta(shtShot)) {
                            ImageIcon ii = 
                                new ImageIcon(getClass().getResource(strExpl));
                            alien.setImage(ii);
                            alien.setDying(true);
                            iDeaths++;
                            shtShot.die();
                        }
                }
            }

            int y = shtShot.getY();
            y -= 4;
            if (y < 0)
                shtShot.die();
            else shtShot.setY(y);
        }

        // aliens

         Iterator ite1 = arlAliens.iterator();

         while (ite1.hasNext()) {
             Alien a1 = (Alien) ite1.next();
             int x = a1.getX();

             if (x  >= BOARD_WIDTH - BORDER_RIGHT && iDireccion != -1) {
                 iDireccion = -1;
                 Iterator i1 = arlAliens.iterator();
                 while (i1.hasNext()) {
                     Alien a2 = (Alien) i1.next();
                     a2.setY(a2.getY() + GO_DOWN);
                 }
             }

            if (x <= BORDER_LEFT && iDireccion != 1) {
                iDireccion = 1;

                Iterator i2 = arlAliens.iterator();
                while (i2.hasNext()) {
                    Alien a = (Alien)i2.next();
                    a.setY(a.getY() + GO_DOWN);
                }
            }
        }


        Iterator ite2 = arlAliens.iterator();

        while (ite2.hasNext()) {
            Alien alien = (Alien) ite2.next();
            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > GROUND - ALIEN_HEIGHT) {
                    bIngame = false;
                    strMessage = "Invasion!";
                }

                alien.act(iDireccion);
            }
        }

        // bombs

        Iterator ite3 = arlAliens.iterator();
        Random generator = new Random();

        while (ite3.hasNext()) {
            int shot = generator.nextInt(15);
            Alien a = (Alien) ite3.next();
            Alien.Bomb b = a.getBomb();
            if (shot == CHANCE && a.isVisible() && b.isDestroyed()) {

                b.setDestroyed(false);
                b.setX(a.getX());
                b.setY(a.getY());   
            }

            int bombX = b.getX();
            int bombY = b.getY();
            int playerX = plyPlayer.getX();
            int playerY = plyPlayer.getY();

            if (plyPlayer.isVisible() && !b.isDestroyed()) {
                if ( bombX >= (playerX) && 
                    bombX <= (playerX+PLAYER_WIDTH) &&
                    bombY >= (playerY) && 
                    bombY <= (playerY+PLAYER_HEIGHT) ) {
                        ImageIcon ii = 
                            new ImageIcon(this.getClass().getResource(strExpl));
                        plyPlayer.setImage(ii);
                        plyPlayer.setDying(true);
                        b.setDestroyed(true);;
                    }
            }

            if (!b.isDestroyed()) {
                b.setY(b.getY() + 1);   
                if (b.getY() >= GROUND - BOMB_HEIGHT) {
                    b.setDestroyed(true);
                }
            }
        }
    }

    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (bIngame) {
            repaint();
            animationCycle();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) 
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            beforeTime = System.currentTimeMillis();
        }
        gameOver();
    }

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent keyEvent) {
            plyPlayer.keyReleased(keyEvent);
        }

        public void keyPressed(KeyEvent keyEvent) {

          plyPlayer.keyPressed(keyEvent);

          int x = plyPlayer.getX();
          int y = plyPlayer.getY();

          if (bIngame)
          {
            if (keyEvent.getKeyCode() == KeyEvent.VK_ALT) {
                if (!shtShot.isVisible())
                    shtShot = new Shot(x, y);
            }
          }
        }
    }
}
