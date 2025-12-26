//Noa Levi-214506396, Hila Ivgi- 326138377

package xo;

import java.util.Scanner;

public class UserPlayer extends Player {

    private Scanner scanner = new Scanner(System.in);

    public UserPlayer(Game game, PlayerType type) {
        super(game, type);
    }

    @Override
    public void run() {
        while (!game.isGameOver()) {
            synchronized (game) {
                if (game.getTurn() == type && !game.isGameOver()) {
                    System.out.println("Your turn (" + type + "). Enter row (0-4): ");
                    int row = scanner.nextInt();
                    System.out.println("Enter column (0-4): ");
                    int col = scanner.nextInt();

                    if (!game.makeMove(row, col, type)) {
                        System.out.println("Invalid move, try again.");
                    } else {
                        game.printBoard();
                    }
                }
            }
        }

        synchronized (game) {
            if (!game.winnerAnnounced) {
                if (game.getWinner() != null) {
                    System.out.println(game.getWinner() + " wins!");
                    game.winnerAnnounced = true;
                } else if (game.isBoardFull()) {
                    System.out.println("Board is full");
                    game.winnerAnnounced = true;
                }
            }
        }
    }
}