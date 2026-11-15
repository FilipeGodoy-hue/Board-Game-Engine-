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
    public int getRows() { return rows; }
    public int getColumns() { return columns; }
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
        System.out.print("  ");
        for (int j = 0; j < columns; j++) {
            System.out.print((j + 1) + " ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
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

    public int countPieces(char symbol) {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Position pos = new Position(i, j);
                if (getPiece(pos) == symbol) {
                    count++;
                }
            }
        }
        return count;
    }
    public void movePiece(Position from, Position to, char symbol) throws InvalidPositionException, InvalidMoveException {
        if (!isValidPosition(from) || !isValidPosition(to)) {
            throw new InvalidPositionException("Invalid position: " + from + " or " + to);
        }
        if (getPiece(from) != symbol) {
            throw new InvalidMoveException("There is no piece of '" + symbol + "' at " + from);
        }
        if (!isEmpty(to)) {
            throw new InvalidMoveException("Position " + to + " is already taken.");
        }

        int rowDiff = Math.abs(from.getRow() - to.getRow());
        int colDiff = Math.abs(from.getColumn() - to.getColumn());
        boolean isAdjacent = rowDiff <= 1 && colDiff <= 1 && !(rowDiff == 0 && colDiff == 0);

        if (!isAdjacent) {
            throw new InvalidMoveException("Position " + to + " is not adjacent to " + from);
        }

        grid[from.getRow()][from.getColumn()] = EMPTY;
        grid[to.getRow()][to.getColumn()] = symbol;
    }

}
