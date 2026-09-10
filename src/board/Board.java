package board;

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
        public void placePiece(Position pos , char symbol){
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
}
