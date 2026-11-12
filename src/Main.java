import java.io.IOException;
import java.util.Scanner;

import config.GameLoader;
import engine.Game;
import exceptions.InvalidConfigurationException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Board Game Engine ===");
        System.out.println("1) Jogo da Velha");
        System.out.println("2) Picaria");
        System.out.print("Choose a Game: ");

        String choice = scanner.nextLine();

        String path;
        switch(choice){
            case "1":
                path = "games/jogo_da_velha.json";
                break;
            case "2":
                path = "games/picaria.json";
                break;
            default:
                System.out.println("Invalid Option, please select 1 or 2");
                return;
        }
        try {
            Game game = new GameLoader().load(path);
            game.play();
        } catch (IOException | InvalidConfigurationException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }
}

