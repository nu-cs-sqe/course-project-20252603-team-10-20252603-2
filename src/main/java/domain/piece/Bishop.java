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

        for (int rowDirection = -1; rowDirection <= 1; rowDirection += 2) {
            for (int colDirection = -1; colDirection <= 1; colDirection += 2) {
                int targetRow = currentRow + rowDirection;
                int targetCol = currentCol + colDirection;

                while (targetRow >= 0 && targetRow <  NUM_ROWS
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
