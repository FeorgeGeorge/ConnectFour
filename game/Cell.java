package game;

public enum Cell {
    RED, YELLOW, E;

    Cell next() {
        switch (this){
            case RED: return YELLOW;
            case YELLOW: return RED;
            default: return RED;
        }
    }
}
