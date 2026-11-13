package rules;

import board.Board;

public interface WinCondition {
    boolean isMet(Board board, char symbol);
}
