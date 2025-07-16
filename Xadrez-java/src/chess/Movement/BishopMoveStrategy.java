package chess.Movement;

import chess.ChessPiece;
import boardgame.Position;

public class BishopMoveStrategy implements MoveStrategy {
    @Override
    public boolean[][] possibleMoves(ChessPiece piece) {
        int rows = piece.getBoardPublic().getRows();
        int cols = piece.getBoardPublic().getColumns();
        boolean[][] mat = new boolean[rows][cols];
        int row = piece.getPosition().getRow();
        int col = piece.getPosition().getColumn();

        // Directions: nw, ne, se, sw
        int[][] directions = { {-1, -1}, {-1, 1}, {1, 1}, {1, -1} };
        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];
            while (r >= 0 && r < rows && c >= 0 && c < cols) {
                Position pos = new Position(r, c);
                if (piece.getBoardPublic().thereIsAPiece(pos)) {
                    if (piece.isThereOpponentPiece(pos)) {
                        mat[r][c] = true;
                    }
                    break;
                }
                mat[r][c] = true;
                r += dir[0];
                c += dir[1];
            }
        }
        return mat;
    }
}