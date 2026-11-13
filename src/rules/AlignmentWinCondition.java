package rules;
import board.Board;
import board.Position;
public class AlignmentWinCondition implements WinCondition {

    @Override
    public boolean isMet(Board board , char symbol) {

        for (int i = 0; i < board.getRows(); i++) {

            boolean linhaCompleta = true;

            for (int j = 0; j < board.getColumns(); j++) {
                Position pos = new Position(i, j);
                if (board.getPiece(pos) != symbol) {
                    linhaCompleta = false;
                    break;

                }
            }

            if (linhaCompleta) {
                return true;
            }
        }
        for (int j = 0; j < board.getColumns(); j++) {

            boolean colunaCompleta = true;

            for (int i = 0; i < board.getRows(); i++) {
                Position pos = new Position(i, j);
                if (board.getPiece(pos) != symbol) {
                    colunaCompleta = false;
                    break;
                }
            }
            if (colunaCompleta) {
                return true;
            }
        }
        return false; //AINDA FALTAM AS DIAGONAIS! PORTANTO AINDA TEM CÒDIGO PARA SER FEITO AQUI!

    }}
