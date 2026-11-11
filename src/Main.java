import config.GameLoader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new GameLoader().load("games/jogo_da_velha.json");
    }
}

