package domain.piece;

import domain.Board;
import domain.Location;


public class Rook extends Piece {
    public Rook(PieceColor color) {
        super(PieceType.ROOK, color);
    }

    @Override
    public Piece makeCopy() {
        return new Rook(getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {

        if (start.equals(end)) {
            return false;
        }

        // Rook can only move horizontally or vertically any number of (empty) squares (within board)
        boolean isHorizontal = (start.getX() == end.getX());
        boolean isVertical = (start.getY() == end.getY());

        if (!isHorizontal && !isVertical) {
            return false;
        }

        // check if the path is clear by looping through each pos
        int colDirection = 0;
        int rowDirection = 0;

        if (isHorizontal) {
            if (end.getY() > start.getY()) {
                colDirection = 1;
            } else {
                colDirection = -1;
            }
        } else {
            if (end.getX() > start.getX()) {
                rowDirection = 1;
            } else {
                rowDirection = -1;
            }
        }

        int currentCol = start.getY() + colDirection;
        int currentRow = start.getX() + rowDirection;

        while (currentCol != end.getY() || currentRow != end.getX()) {
            if (board.isPieceHere(new Location(currentRow, currentCol))) {
                return false;
            }
            currentCol += colDirection;
            currentRow += rowDirection;
        }

        if (board.isPieceHere(end)) {
            Piece target = board.getPiece(end);
            if (this.isSameColor(target)) return false;
        }

        return true;
    }
}