package chess.Movement;

import chess.ChessPiece;

public interface MoveStrategy {
    boolean[][] possibleMoves(ChessPiece piece);
}
