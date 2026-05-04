package domain.piece;

import domain.Board;
import domain.Location;

public class Pawn extends Piece {
    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color);
    }

    @Override
    public Piece makeCopy() {
        return new Pawn(getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {
        //  STRUCTURE:

        //  are location start / end the same?
        if (start.equals(end)) {
            return false;
        }

        //  is it too far?
        int direction = (getColor() == PieceColor.WHITE) ? 1 : -1;

        int dist_x = end.getX() - start.getX();
        int dist_y = end.getY() - start.getY();

        boolean oneForward = false;
        boolean twoForward = false;
        boolean oneForwardDiagonal = false;

        if (dist_x == direction && dist_y == 0) {
            oneForward = true;
        }

        if (dist_x == direction * 2 && dist_y == 0) {
            twoForward = true;
        }

        if (dist_x == direction && Math.abs(dist_y) == 1) {
            oneForwardDiagonal = true;
        }

        if (!(oneForward || oneForwardDiagonal || twoForward)) {
            return false;
        }

        return true;
    }
}