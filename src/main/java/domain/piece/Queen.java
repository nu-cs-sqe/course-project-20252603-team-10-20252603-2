package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;

public class Queen extends Piece {

    private static final int NUM_ROWS = 8;

    private static final int NUM_COLS = 8;

    public Queen(Color color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public Piece makeCopy() {
        return new Queen(getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {
        if (!isValidShape(start, end)) {
            return false;
        }
        if (!isValidDestination(end, board)) {
            return false;
        }
        if (!isPathClear(start, end, board)) {
            return false;
        }
        if (doesMoveKingIntoCheck(start, end, board)) {
            return false;
        }
        return true;
    }

    private boolean isValidShape(Location start, Location end) {
        if (start.equals(end)) {
            return false;
        }
        boolean xMovement = (start.getY() == end.getY()) && (start.getX() != end.getX());
        boolean yMovement = (start.getY() != end.getY()) && (start.getX() == end.getX());

        int diffX = Math.abs(start.getX() - end.getX());
        int diffY = Math.abs(start.getY() - end.getY());

        boolean diagonalMovement = (diffX == diffY);

        return xMovement || yMovement || diagonalMovement;
    }

    private boolean isValidDestination(Location end, Board board) {
        if (board.isPieceHere(end)) {
            Piece blocker = board.getPiece(end);
            return blocker.getColor() != this.getColor();
        }
        return true;
    }

    private boolean isPathClear(Location start, Location end, Board board) {
        int diffX = Math.abs(start.getX() - end.getX());
        int diffY = Math.abs(start.getY() - end.getY());

        int dirX = Integer.compare(end.getX(), start.getX());
        int dirY = Integer.compare(end.getY(), start.getY());

        boolean diagonalMovement = (diffX == diffY);

        boolean xMovement = (start.getY() == end.getY()) && (start.getX() != end.getX());
        boolean yMovement = (start.getY() != end.getY()) && (start.getX() == end.getX());

        if (diagonalMovement) {
            for (int i = 1; i < diffX; i++) {
                Location locationCheck = new Location(start.getX() + (i * dirX), start.getY() + (i * dirY));

                if (board.isPieceHere(locationCheck)) {
                    return false;
                }

            }

            return true;
        }

        if (yMovement) {
            for (int i = 1; i < diffY; i++) {
                Location locationCheck = new Location(start.getX(), start.getY() + (i * dirY));

                if (board.isPieceHere(locationCheck)) {
                    return false;
                }

            }

            return true;
        }

        if (xMovement) {
            for (int i = 1; i < diffX; i++) {
                Location locationCheck = new Location(start.getX() + (i * dirX), start.getY());

                if (board.isPieceHere(locationCheck)) {
                    return false;
                }

            }

            return true;
        }

        return false;
    }

    private boolean doesMoveKingIntoCheck(Location start, Location end, Board board) {
        Piece originalTarget = board.getPiece(end);

        board.setPiece(end, this);
        board.removePiece(start);

        Location kingLocation = null;
        King alliedKing = null;

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
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
            if (kingLocation != null) {
                break;
            }
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

        return exposesKing;
    }

    @Override
    public boolean hasValidMoves(Location currentPosition, Board board) {
        int currentRow = currentPosition.getX();
        int currentCol = currentPosition.getY();

        for (int rowDirection = -1; rowDirection <= 1; rowDirection++) {
            for (int colDirection = -1; colDirection <= 1; colDirection++) {
                if (rowDirection == 0 && colDirection == 0) {
                    continue;
                }
                int targetRow = currentRow + rowDirection;
                int targetCol = currentCol + colDirection;
                while (targetRow >= 0 && targetRow < NUM_ROWS
                        && targetCol >= 0 && targetCol < NUM_COLS) {
                    Location target = new Location(targetRow, targetCol);
                    if (isValidMove(currentPosition, target, board)) {
                        return true;
                    }
                    targetRow += rowDirection;
                    targetCol += colDirection;
                }
            }
        }
        return false;
    }
}
