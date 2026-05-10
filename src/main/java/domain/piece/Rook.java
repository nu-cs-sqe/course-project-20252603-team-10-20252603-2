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
        return true;
    }
}