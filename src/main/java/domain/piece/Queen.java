package domain.piece;

import domain.Board;
import domain.Location;

public class Queen extends Piece {
    public Queen(PieceColor color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public Piece makeCopy() {
        return new Queen(getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {

        if (start.equals(end)) {
            return false;
        }

        int diffX = Math.abs(start.getX() - end.getX());
        int diffY = Math.abs(start.getY() - end.getY());

        int dirX = Integer.compare(end.getX(), start.getX());
        int dirY = Integer.compare(end.getY(), start.getY());

        boolean bishopMovement = diffX == diffY;
        boolean rookMovement = (start.getX() == end.getX()) || (start.getY() == end.getY());

        if (!bishopMovement && !rookMovement) {
            return false;
        }

        Location currSquare = new Location(start.getX() + dirX, start.getY() + dirY);

        while(!currSquare.equals(end)) {
            if (board.isPieceHere(currSquare)) {
                return false;
            }

            currSquare = new Location(currSquare.getX() + dirX, currSquare.getY() + dirY);
        }

        if (board.isPieceHere(end)) {
            Piece blocker = board.getPiece(end);
            return !this.isSameColor(blocker);
        }

        return true;

    }
}
