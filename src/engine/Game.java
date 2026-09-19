package engine;

import board.Board;
import board.Position;
import java.util.Scanner;
import player.Player;
import exceptions.InvalidMoveException;
import exceptions.InvalidPositionException;
import java.util.InputMismatchException;


public class Game {
    private Board board;
    private Player[] players;
    private int currentPlayerIndex;
    private Scanner scanner;

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
        while(true){
            try{
                System.out.println("Enter the row: ");
                int row = scanner.nextInt();

                System.out.println("Enter the column: ");
                int column = scanner.nextInt();

                return new Position(row -1, column -1);

            } catch(InputMismatchException e){
                System.out.println("Invalid input! Please enter a number from 1-3.");
                scanner.nextLine();
            }    
        }     
    }

    public void play(){
        while (true){
            board.print();
            Player current = getCurrentPlayer();
            System.out.println("Its " + current.getName() + " (" + current.getSymbol() + " turn)");
            Position pos = readPosition();

            try {
                board.placePiece(pos, current.getSymbol());
                if (board.checkWinner(current.getSymbol())){
                    board.print();
                    System.out.println("The end, " + current.getSymbol() + " is the winner");
                    return;
                };
                if (board.isFull()){
                    board.print();
                    System.out.println("The end! Tie, no winners");
                    return; 
                }

            } catch(InvalidPositionException | InvalidMoveException e){
                System.out.println(e.getMessage());
                continue;
            }
            switchTurn();
        }

    }


}
