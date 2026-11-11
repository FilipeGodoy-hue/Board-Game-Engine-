package config;

import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameLoader {

    public void load(String filePath) throws IOException {
        String text = Files.readString(Path.of(filePath));
        JSONObject json = new JSONObject(text);
        String name = json.getString("game");
        JSONObject board = json.getJSONObject("board");
        int rows = board.getInt("rows");
        int columns = board.getInt("columns");

        System.out.println(name + ": " + rows + "x" + columns);
    }
}