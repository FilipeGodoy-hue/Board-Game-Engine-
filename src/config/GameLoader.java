package config;

import exceptions.InvalidConfigurationException;
import player.Player;
import rules.AlligmentWinCondition;

import org.json.JSONArray;
import org.json.JSONObject;

import board.Board;
import engine.Game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameLoader {

    public Game load(String filePath) throws IOException, InvalidConfigurationException {
        String text = Files.readString(Path.of(filePath));
        JSONObject json = new JSONObject(text);
        String name = json.getString("game");
        JSONObject board = json.getJSONObject("board");
        int rows = board.getInt("rows");
        int columns = board.getInt("columns");

        if (rows <=0 || columns <=0 ) {
            throw new InvalidConfigurationException("Invalid board: rows and columns must be greater than 0.");
        }

        Board boardObj = new Board(rows, columns ); 

        JSONArray symbols = json.getJSONObject("players").getJSONArray("symbols");
        Player p1 = new Player("Jogador 1", symbols.getString(0).charAt(0));
        Player p2 = new Player("Jogador 2", symbols.getString(1).charAt(0));         
        return new Game(boardObj, p1, p2, new AlligmentWinCondition());
    }
}