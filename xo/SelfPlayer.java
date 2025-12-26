//Noa Levi-214506396, Hila Ivgi- 326138377

package xo;

import java.util.List;
import java.util.Random;

public class SelfPlayer extends Player {
    private Random rand = new Random();
    
    public SelfPlayer(Game game, PlayerType type) {
        super(game, type);
    }
    
    @Override
    public void run() {
        while (!game.isGameOver()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
            
            synchronized (game) {
                if (game.getTurn() == type && !game.isGameOver()) {
                    List<Cell> free = game.getFreeCells();
                    if (free.isEmpty()) {
                        break;
                    }
                    Cell chosen = free.get(rand.nextInt(free.size()));
                    game.makeMove(chosen.getRow(), chosen.getCol(), type);
                    game.printBoard();
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