package chess.pieces;

import boardgame.Board;
import chess.Movement.MoveStrategy;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
    private MoveStrategy moveStrategy;

    public Rook(Board board, Color color, MoveStrategy moveStrategy) {
        super(board, color);
        this.moveStrategy = moveStrategy;
    }

    @Override
    public String toString() {
        return "♖";
    }

    @Override
    public boolean[][] possibleMoves() {
        return moveStrategy.possibleMoves(this);
    }

}
