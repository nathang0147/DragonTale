package src.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import src.GameState.GameStateManager;
public class GamePanel extends JPanel
    implements Runnable, KeyListener {

    //dimension
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 2;

    //Thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;

    //Image
    private Graphics2D g;
    private BufferedImage image;

    //game state manager
    private GameStateManager gsm;

    public GamePanel(){
        super();
        setPreferredSize(
                new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init(){
        image = new BufferedImage(
                WIDTH,HEIGHT,
                BufferedImage.TYPE_INT_RGB);

        g = (Graphics2D) image.getGraphics();
        running = true;

        gsm = new GameStateManager();
    }


    public void run() {
        init();
        long start;
        long elaspe;
        long wait;
        while (running){
            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elaspe = System.nanoTime() - start;

            wait = targetTime - elaspe/1000000;
            if (wait < 0) wait = 5;
            try {
                Thread.sleep(wait);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void update(){
        gsm.update();
    }
    private void draw(){
        gsm.draw(g);
    }
    private void drawToScreen(){
        Graphics g2 = getGraphics();
        g2.drawImage(image,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());
    }
    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e.getKeyCode());
    }

}
