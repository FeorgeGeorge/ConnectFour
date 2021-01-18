package game;

public interface State {
    // checks if a move is Valid (depends on the state of the game)
    boolean isValid(Move move);


    // attempts to make a move;
    Result makeMove(Move move);

    // returns a cell in position. presumes board is of form A in Z x Z;
    Cell getCell(int r, int c);

}
