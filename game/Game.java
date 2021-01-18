package game;


import game.player.ConnectPlayer;

public class Game {
    private final boolean log;
    private final ConnectPlayer player1, player2;

    public Game(final boolean log, final ConnectPlayer player1, final ConnectPlayer player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(ConnectState board) {
        while (true) {
            final int result1 = move(board, player1, 1);
            if (result1 != -1) {
                return result1;
            }
            final int result2 = move(board, player2, 2);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int move(final ConnectState board, final ConnectPlayer player, final int no) {
        final Move move = player.move(board, board.getCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log("State of the game:\n" + board);
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return 3 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}