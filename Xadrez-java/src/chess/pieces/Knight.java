package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

import chess.Movement.MoveStrategy;

public class Knight extends ChessPiece {
    private MoveStrategy moveStrategy;

    public Knight(Board board, Color color, MoveStrategy moveStrategy) {
        super(board, color);
        this.moveStrategy = moveStrategy;
    }

    @Override
    public String toString() {
        return "♘";
    }

    @Override
    public boolean[][] possibleMoves() {
        return moveStrategy.possibleMoves(this);
    }
}
