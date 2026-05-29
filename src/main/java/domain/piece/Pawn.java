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

        int dist_x = end.getX() - start.getX();
        int dist_y = end.getY() - start.getY();

        boolean oneForward = (dist_x == direction && dist_y == 0);
        boolean twoForward = (dist_x == direction * 2 && dist_y == 0);
        boolean oneForwardDiagonal = (dist_x == direction && Math.abs(dist_y) == 1);

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
    public boolean hasValidMoves(Location currentPosition, Board board) {
        int direction = (this.getColor() == Color.BLACK) ? 1 : -1;
        int currX = currentPosition.getX();
        int currY = currentPosition.getY();

        Location[] possibleTargets = {
                new Location(currX + direction, currY),
                new Location(currX + 2 * direction, currY),
                new Location(currX + direction, currY - 1),
                new Location(currX + direction, currY + 1)
        };

        for (Location target : possibleTargets) {
            if (target.getX() >= 0 && target.getX() < 8 && target.getY() >= 0 && target.getY() < 8) {
                if (isValidMove(currentPosition, target, board)) {
                    return true;
                }
            }
        }

        return false;
    }
}