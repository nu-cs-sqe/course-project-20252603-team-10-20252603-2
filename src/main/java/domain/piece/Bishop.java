package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;

public class Bishop extends Piece {
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

    @Override
    public boolean hasValidMoves(Location location, Board board) {
        //  TODO: complete method
        return false;
    }
}
