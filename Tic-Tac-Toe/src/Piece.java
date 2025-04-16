public enum Piece {
    X("X"), O("O"), EMPTY(" ");

    private final String symbol;

    Piece(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
