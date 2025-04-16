public class Board {
    private int size;
    private Cell[][] cells;

    public Board(int size) {
        this.size = size;
        cells = new Cell[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(cells[i][j]);
                if (j < size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < size - 1) {
                System.out.println("-".repeat(size * 2 - 1));
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public boolean isEmpty(int row, int col) {
        return cells[row][col].getPiece().equals(Piece.EMPTY);
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && isEmpty(row, col);
    }

    public boolean checkWin(Piece piece) {
        return checkRows(piece) || checkColumns(piece) || checkDiagonals(piece);
    }

    private boolean checkRows(Piece piece) {
        for (int row = 0; row < size; row++) {
            boolean win = true;
            for (int col = 0; col < size; col++) {
                if (!cells[row][col].getPiece().equals(piece)) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns(Piece piece) {
        for (int col = 0; col < size; col++) {
            boolean win = true;
            for (int row = 0; row < size; row++) {
                if (!cells[row][col].getPiece().equals(piece)) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(Piece piece) {
        boolean win = true;
        for (int row = 0; row < size; row++) {
            if (!cells[row][row].getPiece().equals(piece)) {
                win = false;
                break;
            }
        }
        if (win) {
            return true;
        }
        win = true;
        for (int row = 0; row < size; row++) {
            if (!cells[row][size - 1 - row].getPiece().equals(piece)) {
                win = false;
                break;
            }
        }
        return win;
    }

    public boolean isFull() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (cells[row][col].getPiece().equals(Piece.EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }
}
