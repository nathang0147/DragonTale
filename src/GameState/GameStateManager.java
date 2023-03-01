package src.GameState;

import java.util.ArrayList;

public class GameStateManager {
    private ArrayList<GameState>gameStates;
    private static int current;
    public static final int MENUSTATE = 0;
    public static final int LEVEL1STATE = 1;
    public GameStateManager(){
        gameStates = new ArrayList<GameState>();
        current = MENUSTATE;
        gameStates.add(new MenuState(this));
    }
    public void setState(int state){
        current = state;
        gameStates.get(current).init();
    }

    public void update(){
        gameStates.get(current).update();
    }

    public void draw(java.awt.Graphics2D g){
        gameStates.get(current).draw(g);
    }
    public void keyPressed(int k){
        gameStates.get(current).keyPressed(k);
    }

    public void keyReleased(int k){gameStates.get(current).keyReleased(k);
    }
}
