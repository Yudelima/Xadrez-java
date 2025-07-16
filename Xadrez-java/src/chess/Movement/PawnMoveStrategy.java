package chess.Movement;

import boardgame.Position;
import chess.Color;
import chess.ChessPiece;

public class PawnMoveStrategy implements MoveStrategy {
    @Override
    public boolean[][] possibleMoves(ChessPiece piece) {
        int rows = piece.getBoardPublic().getRows();
        int cols = piece.getBoardPublic().getColumns();
        boolean[][] mat = new boolean[rows][cols];
        int row = piece.getPosition().getRow();
        int col = piece.getPosition().getColumn();
        Color color = piece.getColor();
        int dir = (color == Color.WHITE) ? -1 : 1;

        // Forward move
        int r = row + dir;
        if (r >= 0 && r < rows) {
            Position forward = new Position(r, col);
            if (!piece.getBoardPublic().thereIsAPiece(forward)) {
                mat[r][col] = true;
                // Double move from initial position
                if ((color == Color.WHITE && row == 6) || (color == Color.BLACK && row == 1)) {
                    int rr = row + 2 * dir;
                    Position doubleForward = new Position(rr, col);
                    if (!piece.getBoardPublic().thereIsAPiece(doubleForward)) {
                        mat[rr][col] = true;
                    }
                }
            }
        }
        // Captures
        for (int dc = -1; dc <= 1; dc += 2) {
            int cc = col + dc;
            if (cc >= 0 && cc < cols && r >= 0 && r < rows) {
                Position diag = new Position(r, cc);
                if (piece.isThereOpponentPiece(diag)) {
                    mat[r][cc] = true;
                }
            }
        }
        return mat;
    }
}
