package domain.piece;

import domain.Board;
import domain.Location;

public class Bishop extends Piece {
    public Bishop(PieceColor color) {
        super(PieceType.BISHOP, color);
    }

    @Override
    public Piece makeCopy() {
        return new Bishop(getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {
        if (start.equals(end)) return false;

        int rowChange = Math.abs(end.getX() - start.getX());
        int colChange = Math.abs(end.getY() - start.getY());

        if (rowChange != colChange) return false;

        if (board.isPieceHere(end)) {
            Piece target = board.getPiece(end);
            if (this.isSameColor(target)) return false;
        }

        int rowDirection = 0;
        int colDirection = 0;

        if (end.getX() > start.getX()) {
            rowDirection = 1;
        } else {
            rowDirection = -1;
        }

        if (end.getY() > start.getY()) {
            colDirection = 1;
        } else {
            colDirection = -1;
        }

        int currentRow = start.getX() + rowDirection;
        int currentCol = start.getY() + colDirection;

        while (currentRow != end.getX()) {
            if (board.isPieceHere(new Location(currentRow, currentCol))) {
                return false;
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        return true;
    }
}
