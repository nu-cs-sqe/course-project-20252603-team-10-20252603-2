package domain.piece;

import constants.Color;
import domain.Board;
import domain.Location;

public class Pawn extends Piece {
    private static final int WHITE_START_ROW = 6;
    private static final int BLACK_START_ROW = 1;
    private static final int LAST_ROW = 7;

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
        if (!isShapeValid(start, end)) {
            return false;
        }
        if (!isPathAndTargetValid(start, end, board)) {
            return false;
        }
        return !wouldExposeKing(start, end, board);
    }

    private boolean isShapeValid(Location start, Location end) {
        int direction = getDirection();
        int distX = end.getX() - start.getX();
        int distY = end.getY() - start.getY();

        boolean oneForward = (distX == direction && distY == 0);
        boolean twoForward = (distX == direction * 2 && distY == 0);
        boolean oneForwardDiagonal = (distX == direction && Math.abs(distY) == 1);

        return oneForward || oneForwardDiagonal || twoForward;
    }

    private boolean isPathAndTargetValid(Location start, Location end, Board board) {
        int direction = getDirection();
        int distX = end.getX() - start.getX();
        int distY = end.getY() - start.getY();

        boolean oneForward = (distX == direction && distY == 0);
        boolean twoForward = (distX == direction * 2 && distY == 0);
        boolean oneForwardDiagonal = (distX == direction && Math.abs(distY) == 1);

        if (oneForward) {
            return !board.isPieceHere(end);
        }
        if (twoForward) {
            return isTwoForwardValid(start, end, board, direction);
        }
        if (oneForwardDiagonal) {
            return isDiagonalCaptureValid(end, board);
        }
        return true;
    }

    private boolean isTwoForwardValid(Location start, Location end, Board board, int direction) {
        if (!isOnStartRow(start)) {
            return false;
        }
        Location mid = new Location(start.getX() + direction, start.getY());
        return !board.isPieceHere(mid) && !board.isPieceHere(end);
    }

    private boolean isDiagonalCaptureValid(Location end, Board board) {
        if (!board.isPieceHere(end)) {
            return false;
        }
        Piece target = board.getPiece(end);
        return !this.isSameColor(target);
    }

    private boolean isOnStartRow(Location start) {
        if (getColor() == Color.WHITE) {
            return start.getX() == WHITE_START_ROW;
        }
        return start.getX() == BLACK_START_ROW;
    }

    private int getDirection() {
        return (getColor() == Color.WHITE) ? -1 : 1;
    }

    @Override
    public boolean hasValidMoves(Location currentPosition, Board board) {
        int direction = getDirection();
        int currX = currentPosition.getX();
        int currY = currentPosition.getY();

        int[][] potentialCoordinates = {
                {currX + direction, currY},
                {currX + 2 * direction, currY},
                {currX + direction, currY - 1},
                {currX + direction, currY + 1}
        };

        for (int[] coord : potentialCoordinates) {
            if (isOnBoard(coord[0], coord[1])
                    && this.isValidMove(currentPosition, new Location(coord[0], coord[1]), board)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOnBoard(int x, int y) {
        return x >= 0 && x <= LAST_ROW && y >= 0 && y <= LAST_ROW;
    }
}