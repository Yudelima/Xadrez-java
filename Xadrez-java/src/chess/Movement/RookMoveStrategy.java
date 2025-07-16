package chess.Movement;

import boardgame.Position;
import chess.ChessPiece;
import chess.Color;
import boardgame.Board;

public class RookMoveStrategy implements MoveStrategy {
    @Override
    public boolean[][] possibleMoves(ChessPiece piece) {
        boolean[][] mat = new boolean[piece.getBoardPublic().getRows()][piece.getBoardPublic().getColumns()];
        Position position = piece.getPosition();
        Board board = piece.getBoardPublic();
        Color color = piece.getColor();
        Position p = new Position(0, 0);

        // above
        p.setValues(position.getRow() - 1, position.getColumn());
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }
        if (board.positionExists(p) && isThereOpponentPiece(piece, p, color)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        // left
        p.setValues(position.getRow(), position.getColumn() - 1);
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }
        if (board.positionExists(p) && isThereOpponentPiece(piece, p, color)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        // right
        p.setValues(position.getRow(), position.getColumn() + 1);
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if (board.positionExists(p) && isThereOpponentPiece(piece, p, color)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        // below
        p.setValues(position.getRow() + 1, position.getColumn());
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if (board.positionExists(p) && isThereOpponentPiece(piece, p, color)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }

    private boolean isThereOpponentPiece(ChessPiece piece, Position position, Color color) {
        ChessPiece p = (ChessPiece) piece.getBoardPublic().piece(position);
        return p != null && p.getColor() != color;
    }
}
