package game;

import java.util.Arrays;
import java.util.Map;

public class ConnectState implements State {
    private final int width = 7;
    private final int height = 6;
    private Cell[][] cells;
    private int[] columnHeight;
    Cell turn;

    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.RED, 'X',
            Cell.YELLOW, 'O',
            Cell.E, '.'
    );

    public ConnectState() {
        this.cells = new Cell[width][height];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        columnHeight = new int[width]; // initialised with zeros;
        turn = Cell.RED;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight(){
        return height;
    }
    @Override
    public Cell getCell(int r, int c) {
        return cells[c][r];
    }

    public Cell getCell() {
        return turn;
    }

    public int getHeight(int x) {
        return columnHeight[x];
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < width
                && columnHeight[move.getRow()] < height
                && turn == move.getValue();
    }

    @Override
    public Result makeMove(Move move){
        if (!isValid(move)) {
            return Result.LOSE; // no tolerancy for mistakes
        }
        int x = move.getRow();
        int y = getHeight(x);

        cells[x][y] = move.getValue();
        turn = turn.next();
        columnHeight[x] += 1;

        for (int dx = 0; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy ==0) {
                    continue;
                }
                int inRow = 0;
                for (int d = -4; d <= 4; d++) {
                    int newy = y + d*dy;
                    int newx = x + d*dx;
                    if (0 <= newy && newy < height && 0<= newx && newx < width) {
                        if  (cells[newx][newy] == move.getValue()) {
                            inRow += 1;
                        }
                        else {
                            inRow = 0;
                        }
                        if (inRow >= 4) {
                            System.out.println("Reached the end of the game;");
                            return Result.WIN;
                        }
                    }
                }
            }
        }

        // check if any places left to put for the next player;
        boolean anyLeft = false;
        for (int i = 0; i < width; i++) {
            if (columnHeight[i] < height-1) {
                anyLeft = true;
                break;
            }
        }

        if (!anyLeft) {
            return Result.DRAW;
        }

        return Result.UNKNOWN;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("0123456");
        for (int r = height -1; r >= 0; r--) {
            sb.append("\n");
            for (int c = 0; c < width; c++) {
                sb.append(SYMBOLS.get(cells[c][r]));
            }
        }
        return sb.toString();
    }
}
