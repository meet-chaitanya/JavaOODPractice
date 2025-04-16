public class Cell {
    private final int row;
    private final int col;
    private Piece piece;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.piece = Piece.EMPTY;
    }

    public int getRow() {
        return row;
    }


    public int getCol() {
        return col;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        if (this.piece == Piece.EMPTY) {
            this.piece = piece;
        }
    }

    @Override
    public String toString() {
        return piece == Piece.EMPTY ? " " : piece.toString();
    }
}
