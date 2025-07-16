package chess.Builder;

import chess.*;

import chess.Movement.MoveStrategy;
import boardgame.Board;

public class PieceBuilder {
    private Board board;
    private Color color;
    private MoveStrategy strategy;
    private String type;

    public PieceBuilder setBoard(Board board) {
        this.board = board;
        return this;
    }
    public PieceBuilder setColor(Color color) {
        this.color = color;
        return this;
    }
    public PieceBuilder setStrategy(MoveStrategy strategy) {
        this.strategy = strategy;
        return this;
    }
    public PieceBuilder setType(String type) {
        this.type = type;
        return this;
    }
    public ChessPiece build() {
        switch (type) {
            case "R": return new chess.pieces.Rook(board, color, strategy);
            case "N": return new chess.pieces.Knight(board, color, strategy);
            case "B": return new chess.pieces.Bishop(board, color, strategy);
            case "Q": return new chess.pieces.Queen(board, color, strategy);
            case "K": return new chess.pieces.King(board, color, strategy);
            case "P": return new chess.pieces.Pawn(board, color, strategy);
            default: throw new IllegalArgumentException("Unknown piece type");
        }
    }
}
