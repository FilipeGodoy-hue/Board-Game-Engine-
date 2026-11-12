package config;

import exceptions.InvalidConfigurationException;
import player.Player;
import rules.AlligmentWinCondition;
import rules.WinCondition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import board.Board;
import engine.Game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameLoader {

    public Game load(String filePath) throws IOException, InvalidConfigurationException {
        String text = Files.readString(Path.of(filePath));
        try{
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
            char s1 = readSymbol(symbols, 0);
            char s2 = readSymbol(symbols, 1);

            if (s1==s2) {
                throw new InvalidConfigurationException("Invalid configuration file: Both of the players cannot have the same character");
            }

            Player p1 = new Player("Jogador 1", s1);
            Player p2 = new Player("Jogador 2", s2);
            JSONObject rules = json.getJSONObject("rules");
            String type = rules.getJSONObject("winCondition").getString("type");

            WinCondition winCondition;
            switch (type) {
            case "ALIGNMENT":
                winCondition = new AlligmentWinCondition();
                break;
            default:
                throw new InvalidConfigurationException("Unknown win condition type: " + type);
            }        
            return new Game(boardObj, p1, p2, winCondition);
        }catch(JSONException e){
            throw new InvalidConfigurationException("Invalid configuration file: " + e.getMessage());
        }
    }
        private char readSymbol(JSONArray symbols, int index) throws InvalidConfigurationException {
                String s = symbols.getString(index);
                if (s.length() != 1) {
                    throw new InvalidConfigurationException("Invalid configuration file: All symbols need to have 1 character");
                }
                return s.charAt(0);
        }
}