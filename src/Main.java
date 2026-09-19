import board.Board;
import board.Position;
import engine.Game;
import player.Player;

public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("Jogador 1", 'X');
        Player p2 = new Player("Jogador 2", 'O');

        Game game = new Game(p1,p2);
        game.play();
    }

}

