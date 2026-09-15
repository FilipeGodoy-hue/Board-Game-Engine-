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

    public Game(Player player1, Player player2) {
        this.board = new Board(3, 3);
        this.players = new Player[2];
        this.players[0] = player1;
        this.players[1] = player2;
        this.currentPlayerIndex = 0;
        this.scanner = new Scanner(System.in);
    }

    private Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    private void switchTurn() {

        if (currentPlayerIndex == 0) {
            currentPlayerIndex = 1;
        } else {
            currentPlayerIndex = 0;
        }

    }

    private Position readPosition() {

        System.out.println("Enter the row: ");
        int row = scanner.nextInt();

        System.out.println("Enter the column: ");
        int column = scanner.nextInt();

        return new Position(row -1 , column -1);
    }

}
