package chess.pieces;

import chess.Movement.MoveStrategy;
import boardgame.Board;
import chess.Color;
import chess.ChessPiece;

public class King extends ChessPiece {
    private MoveStrategy moveStrategy;

    public King(Board board, Color color, MoveStrategy moveStrategy) {
        super(board, color);
        this.moveStrategy = moveStrategy;
    }

    @Override
    public String toString() {
        return "♔";
    }

    @Override
    public boolean[][] possibleMoves() {
        return moveStrategy.possibleMoves(this);
    }
}