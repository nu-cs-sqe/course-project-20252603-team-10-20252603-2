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
        Piece originalTarget = board.getPiece(end);

        board.setPiece(end, this);
        board.setPiece(start, null);

        Location kingLocation = null;
        King alliedKing = null;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Location loc = new Location(i, j);
                if (board.isPieceHere(loc)) {
                    Piece p = board.getPiece(loc);
                    if (p.getColor() == this.getColor() && p.getType() == PieceType.KING) {
                        kingLocation = loc;
                        alliedKing = (King) p;
                        break;
                    }
                }
            }
            if (kingLocation != null)  {
                break;
            };
        }

        boolean exposesKing = false;
        if (alliedKing != null) {
            exposesKing = alliedKing.isInCheck(kingLocation, board);
        }

        board.setPiece(start, this);
        board.setPiece(end, originalTarget);

        if (exposesKing) {
            return false;
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