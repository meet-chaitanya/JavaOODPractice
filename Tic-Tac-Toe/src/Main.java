import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the game!");
        System.out.println("Enter the size of the board: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        System.out.println("Enter the two players names: ");
        String xPlayerName = scanner.next();
        String oPlayerName = scanner.next();

        Player xPlayer = new Player(xPlayerName, Piece.X);
        Player oPlayer = new Player(oPlayerName, Piece.O);

        Game game = new Game(size, xPlayer, oPlayer);
        game.start();
    }
}