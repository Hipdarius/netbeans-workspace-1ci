package boardclub;

public class Board {
    private final Marker[][] cells;

    public Board(int size) {
        cells = new Marker[size][size];
    }

    public int size() {
        return cells.length;
    }

    public boolean isInside(int row, int col) {
        return row >= 0 && row < size() && col >= 0 && col < size();
    }

    public Marker get(int row, int col) {
        return cells[row][col];
    }

    public boolean place(Marker marker, int row, int col) {
        if (!isInside(row, col) || cells[row][col] != null) {
            return false;
        }
        cells[row][col] = marker;
        return true;
    }

    public boolean move(int fromRow, int fromCol, int toRow, int toCol) {
        // TODO E1
        return false;
    }
}
