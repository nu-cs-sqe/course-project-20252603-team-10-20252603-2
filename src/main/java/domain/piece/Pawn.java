package domain.piece;

import constants.Color;
import domain.Board;
import domain.Location;

public class Pawn extends Piece {
    public Pawn(Color color) {
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
        int direction = (getColor() == Color.WHITE) ? -1 : 1;

        int distX = end.getX() - start.getX();
        int distY = end.getY() - start.getY();

        boolean oneForward = (distX == direction && distY == 0);
        boolean twoForward = (distX == direction * 2 && distY == 0);
        boolean oneForwardDiagonal = (distX == direction && Math.abs(distY) == 1);

        boolean onStartRow =
                (getColor() == Color.WHITE && start.getX() == 6)
                        || (getColor() == Color.BLACK && start.getX() == 1);

        if (!(oneForward || oneForwardDiagonal || twoForward)) {
            return false;
        }

        //  is there a piece blocking?
        if (oneForward && board.isPieceHere(end)) {
            return false;
        }

        if (twoForward) {
            Location mid = new Location(start.getX() + direction, start.getY());
            if (!onStartRow) {
                return false;
            }
            if (board.isPieceHere(mid)) {
                return false;
            }
            if (board.isPieceHere(end)) {
                return false;
            }
        }
        if (oneForwardDiagonal) {
            if (!board.isPieceHere(end)) {
                return false;
            }
            Piece target = board.getPiece(end);
            if (this.isSameColor(target)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean hasValidMoves(Location location, Board board) {
        //  TODO: complete method
        return false;
    }
}