import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;


    public Game(int size, Player player1, Player player2) {
        this.board = new Board(size);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            board.display();
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getPiece() + ")");
            System.out.println("Enter row and column: ");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (board.isValidMove(row, col)) {
                board.getCell(row, col).setPiece(currentPlayer.getPiece());

                if (board.checkWin(currentPlayer.getPiece())) {
                    board.display();
                    System.out.println(currentPlayer.getName() + " wins!");
                    break;
                }

                if (board.isFull()) {
                    board.display();
                    System.out.println("It's a draw!");
                    break;
                }

                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }
}
