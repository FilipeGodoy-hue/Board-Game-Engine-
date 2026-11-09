package rules;
import board.Board;
public class AlligmentWinCondition implements WinCondition {

    @Override
    public boolean isMet(Board board , char symbol){
        return board.checkWinner(symbol);
    }

}
