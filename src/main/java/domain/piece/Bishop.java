package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;


public class Bishop extends Piece {
    private static final int NUM_ROWS = 8;

    private static final int NUM_COLS = 8;

    public Bishop(Color color) {
        super(PieceType.BISHOP, color);
    }

    @Override
    public Piece makeCopy() {
        return new Bishop(getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {
        if (!isDiagonal(start, end)) {
            return false;
        }
        if (!isDestValid(end, board)) {
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

    private boolean isDiagonal(Location start, Location end) {
        if (start.equals(end)) {
            return false;
        }
        int rowChange = Math.abs(end.getX() - start.getX());
        int colChange = Math.abs(end.getY() - start.getY());

        return rowChange == colChange;
    }

    private boolean isDestValid(Location end, Board board) {
        if (board.isPieceHere(end)) {
            Piece target = board.getPiece(end);
            return !this.isSameColor(target);
        }
        return true;
    }

    private boolean isPathClear(Location start, Location end, Board board) {
        int rowDirection = (end.getX() > start.getX()) ? 1 : -1;
        int colDirection = (end.getY() > start.getY()) ? 1 : -1;

        int currentRow = start.getX() + rowDirection;
        int currentCol = start.getY() + colDirection;

        while (currentRow != end.getX()) {
            if (board.isPieceHere(new Location(currentRow, currentCol))) {
                return false;
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }
        return true;
    }

    private boolean doesMoveKingIntoCheck(Location start, Location end, Board board) {
        Piece originalTarget = board.getPiece(end);

        simulateMove(start, end, board);

        Location kingLocation = findAlliedKingLocation(board);
        King alliedKing = getAlliedKing(kingLocation, board);

        boolean exposesKing = alliedKing != null && alliedKing.isInCheck(kingLocation, board);

        restoreMove(start, end, originalTarget, board);

        return exposesKing;
    }

    private void simulateMove(Location start, Location end, Board board) {
        board.setPiece(end, this);
        board.removePiece(start);
    }

    private void restoreMove(Location start, Location end, Piece originalTarget, Board board) {
        board.setPiece(start, this);

        if (originalTarget != null) {
            board.setPiece(end, originalTarget);
        } else {
            board.removePiece(end);
        }
    }

    private Location findAlliedKingLocation(Board board) {
        for (int row = 0; row < NUM_ROWS; row++) {
            Location kingLocation = findAlliedKingInRow(row, board);

            if (kingLocation != null) {
                return kingLocation;
            }
        }

        return null;
    }

    private Location findAlliedKingInRow(int row, Board board) {
        for (int col = 0; col < NUM_COLS; col++) {
            Location location = new Location(row, col);

            if (isAlliedKing(location, board)) {
                return location;
            }
        }

        return null;
    }

    private boolean isAlliedKing(Location location, Board board) {
        if (!board.isPieceHere(location)) {
            return false;
        }

        Piece piece = board.getPiece(location);

        return piece.getColor() == getColor() && piece.getType() == PieceType.KING;
    }

    private King getAlliedKing(Location kingLocation, Board board) {
        if (kingLocation == null) {
            return null;
        }

        return (King) board.getPiece(kingLocation);
    }

    @Override
    public boolean hasValidMoves(Location currentPosition, Board board) {
        for (int rowDirection = -1; rowDirection <= 1; rowDirection += 2) {
            if (hasValidMoveInRowDirection(currentPosition, board, rowDirection)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasValidMoveInRowDirection(Location currentPosition, Board board,
                                               int rowDirection) {
        for (int colDirection = -1; colDirection <= 1; colDirection += 2) {
            if (hasValidMoveInDirection(currentPosition, board, rowDirection, colDirection)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasValidMoveInDirection(Location currentPosition, Board board,
                                            int rowDirection, int colDirection) {
        int targetRow = currentPosition.getX() + rowDirection;
        int targetCol = currentPosition.getY() + colDirection;

        while (isOnBoard(targetRow, targetCol)) {
            Location target = new Location(targetRow, targetCol);

            if (isValidMove(currentPosition, target, board)) {
                return true;
            }

            targetRow += rowDirection;
            targetCol += colDirection;
        }

        return false;
    }

    private boolean isOnBoard(int row, int col) {
        return row >= 0 && row < NUM_ROWS && col >= 0 && col < NUM_COLS;
    }
}
