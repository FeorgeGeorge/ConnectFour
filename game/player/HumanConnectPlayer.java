package game.player;

import game.Cell;
import game.ConnectState;
import game.Move;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanConnectPlayer implements ConnectPlayer {
    private final PrintStream out;
    private final Scanner in;

    public HumanConnectPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanConnectPlayer() {
        this(System.out, new Scanner(System.in));
    }

    public Move move(ConnectState state, final Cell cell) {
        while (true) {
            out.println("Сurrent Game State:");
            out.println(state);
            out.println(cell + "'s move");
            out.println("Enter the row where you would like to drop the piece:");
            String rowCommand = in.next();
            try {
                int putRow = Integer.parseInt(rowCommand);
                final Move move = new Move(putRow, cell);
                if (state.isValid(move)) {
                    return move;
                }
                final int row = move.getRow();
                out.println("Move [" + move + "] is invalid");
            } catch (NumberFormatException e) {
                out.println("Please enter a valid integer;");
            }
        }
    }
}
