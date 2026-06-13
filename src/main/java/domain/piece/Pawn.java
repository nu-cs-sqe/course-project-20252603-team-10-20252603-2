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
        if (start.equals(end)) {
            return false;
        }

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
        board.removePiece(start);

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
        if (originalTarget != null) {
            board.setPiece(end, originalTarget);
        } else {
            board.removePiece(end);
        }

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

        int[][] potentialCoordinates = {
                {currX + direction, currY},
                {currX + 2 * direction, currY},
                {currX + direction, currY - 1},
                {currX + direction, currY + 1}
        };

        for (int[] coord : potentialCoordinates) {
            int targetX = coord[0];
            int targetY = coord[1];

            if (targetX >= 0 && targetX <= 7 && targetY >= 0 && targetY <= 7) {

                Location target = new Location(targetX, targetY);

                if (this.isValidMove(currentPosition, target, board)) {
                    return true;
                }
            }
        }

        return false;
    }
}
