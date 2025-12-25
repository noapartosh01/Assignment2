//Noa Levi-214506396, Hila Ivgi- 326138377

package xo;

public abstract class Player extends Thread {

    protected Game game;
    protected PlayerType type;

    public Player(Game game, PlayerType type) {
        this.game = game;
        this.type = type;
    }
}