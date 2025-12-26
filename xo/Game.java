//Noa Levi-214506396, Hila Ivgi- 326138377

package xo;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    protected char[][] gameBoard;
    protected PlayerType currentTurn;
    protected boolean gameOver;
    protected PlayerType winner;
    protected boolean winnerAnnounced = false;
    
    public Game() {
        gameBoard = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                gameBoard[i][j] = ' ';
            }
        }
        currentTurn = PlayerType.X;
        gameOver = false;
        winner = null;
        winnerAnnounced = false; 
    }
    
    public synchronized void printBoard() {
        System.out.println("\n=== Game Board ===");
        System.out.println("  0 1 2 3 4");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 5; j++) {
                System.out.print(gameBoard[i][j]);
                if (j < 4) System.out.print("|");
            }
            System.out.println();
            if (i < 4) {
                System.out.println("  ---------");
            }
        }
        System.out.println("==================\n");
    }
    
    public synchronized PlayerType getTurn() {
        return currentTurn;
    }
    
    protected synchronized void switchTurn() {
        currentTurn = (currentTurn == PlayerType.X) ? PlayerType.O : PlayerType.X;
    }
    
    public synchronized List<Cell> getFreeCells() {
        List<Cell> freeCells = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (gameBoard[i][j] == ' ') {
                    freeCells.add(new Cell(i, j));
                }
            }
        }
        return freeCells;
    }
    
    public synchronized boolean makeMove(int row, int col, PlayerType player) {
        if (row < 0 || row >= 5 || col < 0 || col >= 5) {
            return false;
        }
        
        if (gameBoard[row][col] != ' ') {
            return false;
        }
        
        gameBoard[row][col] = (player == PlayerType.X) ? 'X' : 'O';
        
        if (checkWin(player)) {
            gameOver = true;
            winner = player;
        }
        
        switchTurn();
        
        return true;
    }
    
    protected synchronized boolean checkWin(PlayerType player) {
        char symbol = (player == PlayerType.X) ? 'X' : 'O';
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= 1; j++) {
                if (gameBoard[i][j] == symbol &&
                    gameBoard[i][j+1] == symbol &&
                    gameBoard[i][j+2] == symbol &&
                    gameBoard[i][j+3] == symbol) {
                    return true;
                }
            }
        }
        
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i <= 1; i++) {
                if (gameBoard[i][j] == symbol &&
                    gameBoard[i+1][j] == symbol &&
                    gameBoard[i+2][j] == symbol &&
                    gameBoard[i+3][j] == symbol) {
                    return true;
                }
            }
        }
        
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                if (gameBoard[i][j] == symbol &&
                    gameBoard[i+1][j+1] == symbol &&
                    gameBoard[i+2][j+2] == symbol &&
                    gameBoard[i+3][j+3] == symbol) {
                    return true;
                }
            }
        }
        
        for (int i = 0; i <= 1; i++) {
            for (int j = 3; j < 5; j++) {
                if (gameBoard[i][j] == symbol &&
                    gameBoard[i+1][j-1] == symbol &&
                    gameBoard[i+2][j-2] == symbol &&
                    gameBoard[i+3][j-3] == symbol) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public synchronized boolean isBoardFull() {
        return getFreeCells().isEmpty();
    }
    
    public synchronized boolean isGameOver() {
        return gameOver || isBoardFull();
    }
    
    public synchronized PlayerType getWinner() {
        return winner;
    }
    
    public synchronized char[][] getGameBoard() {
        return gameBoard;
    }
    
    public synchronized void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}