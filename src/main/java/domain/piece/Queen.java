package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;

public class Queen extends Piece {
    public Queen(Color color) {
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

        if (board.isPieceHere(end)) {
            Piece blocker = board.getPiece(end);
            if (blocker.getColor() == this.getColor()) return false;
        }

        boolean xMovement = (start.getY() == end.getY()) && (start.getX() != end.getX());
        boolean yMovement = (start.getY() != end.getY()) && (start.getX() == end.getX());

        int diffX = Math.abs(start.getX() - end.getX());
        int diffY = Math.abs(start.getY() - end.getY());

        int dirX = Integer.compare(end.getX(), start.getX());
        int dirY = Integer.compare(end.getY(), start.getY());

        boolean diagonalMovement = (diffX == diffY);

        if (diagonalMovement) {
            for (int i = 1; i < diffX; i++) {
                Location locationCheck = new Location(start.getX() + (i * dirX), start.getY() + (i * dirY));

                if (board.isPieceHere(locationCheck)) return false;

            }

            return true;
        }

        if (yMovement) {
            for (int i = 1; i < diffY; i++) {
                Location locationCheck = new Location(start.getX(), start.getY() + (i * dirY));

                if (board.isPieceHere(locationCheck)) return false;

            }

            return true;
        }

        if (xMovement) {
            for (int i = 1; i < diffX; i++) {
                Location locationCheck = new Location(start.getX() + (i * dirX), start.getY());

                if (board.isPieceHere(locationCheck)) return false;

            }

            return true;
        }

        return false;
    }

    @Override
    public boolean hasValidMoves() {
        //  TODO: complete method
        return false;
    }
}