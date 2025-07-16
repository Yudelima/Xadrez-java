package chess.pieces;

import boardgame.Board;
import chess.Movement.MoveStrategy;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
    private MoveStrategy moveStrategy;

    public Pawn(Board board, Color color, MoveStrategy moveStrategy) {
        super(board, color);
        this.moveStrategy = moveStrategy;
    }

    @Override
    public boolean[][] possibleMoves() {
        return moveStrategy.possibleMoves(this);
    }

    @Override
    public String toString() {
        return "♙";
    }
}
