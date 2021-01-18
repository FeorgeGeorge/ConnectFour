package game.player;

import game.Cell;
import game.ConnectState;
import game.Move;

import java.util.Random;

public class RandomPlayer implements  ConnectPlayer{

    public Move move(ConnectState state, final Cell cell) {
        Random ran = new Random();
        int x = ran.nextInt(state.getWidth());
        int xmod = 0;
        while(true) {
            if (xmod == x && state.getHeight(xmod) < state.getHeight()) {
                return new Move(xmod, cell);
            }
            else{
                xmod = (xmod+1) % state.getWidth();
            }
        }
    }
}
