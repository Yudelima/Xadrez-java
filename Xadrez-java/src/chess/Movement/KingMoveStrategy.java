package chess.Movement;

import boardgame.Position;
import chess.ChessPiece;

public class KingMoveStrategy implements MoveStrategy {
    @Override
    public boolean[][] possibleMoves(ChessPiece piece) {
        int rows = piece.getBoardPublic().getRows();
        int cols = piece.getBoardPublic().getColumns();
        boolean[][] mat = new boolean[rows][cols];
        int row = piece.getPosition().getRow();
        int col = piece.getPosition().getColumn();

        int[][] moves = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1},  {1, 0},  {1, 1}
        };
        for (int[] move : moves) {
            int r = row + move[0];
            int c = col + move[1];
            if (r >= 0 && r < rows && c >= 0 && c < cols) {
                Position pos = new Position(r, c);
                if (!piece.getBoardPublic().thereIsAPiece(pos) || piece.isThereOpponentPiece(pos)) {
                    mat[r][c] = true;
                }
            }
        }
        return mat;
    }
}
