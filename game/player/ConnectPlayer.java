package game.player;

import game.Cell;
import game.ConnectState;
import game.Move;
import game.State;

public interface ConnectPlayer {
    // player makes moves; that's it
    Move move(ConnectState position, Cell cell);
}
