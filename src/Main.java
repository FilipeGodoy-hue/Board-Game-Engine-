import java.io.IOException;

import board.Board;
import config.GameLoader;
import engine.Game;
import exceptions.InvalidConfigurationException;
import player.Player;
import rules.AlligmentWinCondition;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(3, 3);
        Player p1 = new Player("Jogador 1", 'X');
        Player p2 = new Player("Jogador 2", 'O');
        Game game = new Game(board, p1, p2, new AlligmentWinCondition());
        game.play();
        try {
            new GameLoader().load("games/jogo_da_velha.json");
        } catch (IOException | InvalidConfigurationException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }
}

