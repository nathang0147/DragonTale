package src.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import src.TitleMap.Background;
public class MenuState extends GameState{
    private Background bg;
    private Color titleColor;
    private Font titleFont;
    private int currentChoice = 0;
    private Font font;
    private String[] option = {
            "Start",
            "Option",
            "Quit"
    };

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background("",1);
            bg.setVector(-0.1,0);

            titleColor = new Color(120,123,222);
            titleFont = new Font("Century Gothic",
                    Font.PLAIN,
                    28);

            font = new Font("Jet Brain Mono",
                    Font.PLAIN,
                    12
            );
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void init() {
    }


    public void update() {
        bg.update();
    }


    public void draw(Graphics2D g) {
        bg.draw(g);

        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Dragon Tale",80,70);
        // menu
        g.setFont(font);
        for(int i = 0; i < option.length;i++){
                if(i == currentChoice){
                    g.setColor(Color.BLACK);
                }else {
                    g.setColor(Color.RED);
                }
                g.drawString(option[i],145,140 + i*15);
        }
    }

    private void select(){
        if(currentChoice == 0){
            //Start
        } else if (currentChoice == 1) {
            //Option

        } else if (currentChoice == 2) {
            System.exit(0);
        }
    }


    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER){
            select();
        }
        if (k == KeyEvent.VK_UP){
            currentChoice --;
            if(currentChoice == -1){
                currentChoice = option.length -1;
            }
        }if(k == KeyEvent.VK_DOWN){
            currentChoice ++;
            if(currentChoice == option.length){
                currentChoice = 0;
            }
        }
    }



    public void keyReleased(int k) {

    }
}
