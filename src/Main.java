import java.io.IOException;

import config.GameLoader;
import engine.Game;
import exceptions.InvalidConfigurationException;

public class Main {
    public static void main(String[] args) {
        try {
            Game game = new GameLoader().load("games/jogo_da_velha.json");
            game.play();
        } catch (IOException | InvalidConfigurationException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }
}

