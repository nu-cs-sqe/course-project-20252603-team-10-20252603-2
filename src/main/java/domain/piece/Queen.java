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

//        Location blocker = new Location(3, 0);
//        if (board.isPieceHere(blocker) == true) return false;

        boolean xMovement = (start.getY() == end.getY()) && (start.getX() != end.getX());
        boolean yMovement = (start.getY() != end.getY()) && (start.getX() == end.getX());

        int diffX = Math.abs(start.getX() - end.getX());
        int diffY = Math.abs(start.getY() - end.getY());
        int dirX = (Math.abs(start.getX() - end.getX()) == (start.getX() - end.getX())) ? -1 : 1;
        int dirY = (Math.abs(start.getY() - end.getY()) == (start.getY() - end.getY())) ? -1 : 1;

        boolean diagonalMovement = (diffX == diffY);

        if (diagonalMovement) {
            for (int i = 1; i < diffX - 1; i++) {
                Location locationCheck = new Location(start.getX() + (i * dirY), start.getY() + (i * dirY));

                if (board.isPieceHere(locationCheck) == true) return false;

            }

            return true;
        };

        if (yMovement) {
            for (int i = 1; i < diffY - 1; i++) {
                Location locationCheck = new Location(start.getX(), start.getY() + (i * dirY));

                if (board.isPieceHere(locationCheck) == true) return false;

            }
            return true;
        };

        if (xMovement) {

            for (int i = 1; i < diffX - 1; i++) {
                Location locationCheck = new Location(start.getX() + (i * dirX), start.getY());

                if (board.isPieceHere(locationCheck) == true) return false;

            }
            return true;
        }



        //  TODO: complete method
        return false;
    }
}