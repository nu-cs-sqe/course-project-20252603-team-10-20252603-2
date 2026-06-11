package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;

public class King extends Piece {

    private static final int NUM_ROWS = 8;

    private static final int NUM_COLS = 8;

    public King(Color color) {
        super(PieceType.KING, color);
    }

    public boolean isInCheck(Location kingLocation, Board board) {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                Location location = new Location(i, j);
                if (isKingThreatened(location, kingLocation, board)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isKingThreatened(Location location, Location kingLocation, Board board) {
        if (!board.isPieceHere(location)) {
            return false;
        }
        Piece piece = board.getPiece(location);
        if (piece.isSameColor(this)) {
            return false;
        }
        if (piece.getType() == PieceType.KING) {
            return canKingAttack(location, kingLocation);
        }
        return piece.isValidMove(location, kingLocation, board);
    }

    private boolean canKingAttack(Location from, Location kingPos) {
        int rowDistance = Math.abs(kingPos.getX() - from.getX());
        int colDistance = Math.abs(kingPos.getY() - from.getY());
        return rowDistance <= 1 && colDistance <= 1 && !(rowDistance == 0 && colDistance == 0);
    }

    @Override
    public Piece makeCopy() {
        return new King(this.getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {
        if (!isValidMovementPattern(start, end)) {
            return false;
        }
        if (!isValidDestination(end, board)) {
            return false;
        }
        return !doesMoveKingIntoCheck(start, end, board);
    }

    private boolean isValidMovementPattern(Location start, Location end) {
        if (start.equals(end)) {
            return false;
        }

        int diffX = Math.abs(start.getX() - end.getX());
        int diffY = Math.abs(start.getY() - end.getY());

        return diffX <= 1 && diffY <= 1;
    }

    private boolean isValidDestination(Location end, Board board) {
        if (board.isPieceHere(end)) {
            Piece target = board.getPiece(end);
            return target.getColor() != this.getColor();
        }
        return true;
    }

    private boolean doesMoveKingIntoCheck(Location start, Location end, Board board) {
        Piece originalPiece = board.getPiece(end);

        board.setPiece(end, this);
        board.removePiece(start);

        boolean inCheck = this.isInCheck(end, board);

        board.setPiece(start, this);
        if (originalPiece != null) {
            board.setPiece(end, originalPiece);
        } else {
            board.removePiece(end);
        }

        return inCheck;
    }

    @Override
    public boolean hasValidMoves(Location currentPosition, Board board) {
        int currentRow = currentPosition.getX();
        int currentCol = currentPosition.getY();

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) {
                    continue;
                }
                int destinationRow = currentRow + rowOffset;
                int destinationCol = currentCol + colOffset;

                if (destinationRow >= 0 && destinationRow < NUM_ROWS
                        && destinationCol >= 0 && destinationCol < NUM_COLS) {
                    Location desiredDestination = new Location(destinationRow, destinationCol);
                    if (isValidMove(currentPosition, desiredDestination, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
