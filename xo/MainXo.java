//Noa Levi-214506396, Hila Ivgi- 326138377

package xo;

import java.util.Scanner;

public class MainXo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose game type: 1 - Self vs Self, 2 - User vs Self");
        int choice = scanner.nextInt();

        Game game;
        Player p1;
        Player p2;

        if (choice == 1) {
            game = new SelfGame();
            p1 = new SelfPlayer(game, PlayerType.X);
            p2 = new SelfPlayer(game, PlayerType.O);
        } else {
            game = new UserGame();
            p1 = new UserPlayer(game, PlayerType.X);
            p2 = new SelfPlayer(game, PlayerType.O);
        }

        p1.start();
        p2.start();
    }
}