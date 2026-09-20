package board;

import exceptions.InvalidPositionException;
import exceptions.InvalidMoveException;

public class Board {

        private static final char EMPTY = '-';

        private char[][] grid;
        private int rows;
        private int columns;

        public Board(int rows , int columns) {
            this.rows = rows;
            this.columns = columns;


            grid = new char[rows][columns];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {

                    grid[i][j] = EMPTY;
                }
            }

        }
        public boolean isValidPosition(Position pos){
        return pos.getRow() >= 0 && pos.getRow() < rows &&pos.getColumn() >= 0 && pos.getColumn() < columns;
        }
        public boolean isEmpty(Position pos){
        if (grid[pos.getRow()][pos.getColumn()] == EMPTY) {
            return true;
        }
        return false;
        }
        public void placePiece(Position pos , char symbol) throws InvalidMoveException, InvalidPositionException{
            if(!isValidPosition(pos)){
                throw new InvalidPositionException("Invalid position (" + (pos.getRow() + 1) + "," + (pos.getColumn() + 1) + ") Use row 1-3 and columns 1-3");
            }
            if(!isEmpty(pos)){
                throw new InvalidMoveException("Position (" + (pos.getRow() + 1) + "," + (pos.getColumn() + 1) + ") is already taken. Choose another one." );
            }
            grid[pos.getRow()][pos.getColumn()] = symbol;
        }
        public char getPiece(Position pos){
            return grid[pos.getRow()][pos.getColumn()];
        }
        public void print(){
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print(grid[i][j]);
                }
                System.out.println();
            }

        }
        public boolean checkWinner(char symbol){
            for (int i = 0; i < rows; i++){
                if(grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol){
                    return true;
                }

            }
            for (int j = 0; j < columns; j++){
                if(grid[0][j] == symbol && grid[1][j] == symbol && grid[2][j] == symbol){
                    return true;
                }
            }
            if(grid[2][0] == symbol && grid[1][1] == symbol && grid[0][2] == symbol){
                return true;
            }
            if(grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol){
                return true;
            }
            return false;
        }

        
            public boolean isFull(){
                for(int i =0; i<rows; i++){
                    for(int j =0; j<columns; j++){
                        if (grid[i][j] == EMPTY){
                            return false;
                        }
                    }
                }
                return true;
            }
}
