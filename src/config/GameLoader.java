package config;

import exceptions.InvalidConfigurationException;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameLoader {

    public void load(String filePath) throws IOException, InvalidConfigurationException {
        String text = Files.readString(Path.of(filePath));
        JSONObject json = new JSONObject(text);
        String name = json.getString("game");
        JSONObject board = json.getJSONObject("board");
        int rows = board.getInt("rows");
        int columns = board.getInt("columns");

        if (rows <=0 || columns <=0 ) {
            throw new InvalidConfigurationException("Invalid board: rows and columns must be greater than 0.");
        }

        System.out.println(name + ": " + rows + "x" + columns);
    }
}