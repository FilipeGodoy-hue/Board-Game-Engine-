package engine;

import board.Board;
import board.Position;
import java.util.Scanner;
import player.Player;
import exceptions.InvalidMoveException;
import exceptions.InvalidPositionException;


public class Game {
    Board board;
    Player[] players;
    int currentPlayerIndex;
    Scanner scanner = new Scanner(System.in);

    public Game(Player player1 , Player player2) {
        this.board = new Board(3 ,3);
        this.players = new Player[2];
        this.players[0] = player1;
        this.players[1] = player2;
        this.currentPlayerIndex = 0;
        this.scanner = new Scanner(System.in);
    }


}
