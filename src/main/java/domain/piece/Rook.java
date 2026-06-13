package domain.piece;

import constants.Color;
import domain.Board;
import domain.Location;

public class Rook extends Piece {
    private static final int MIN_COORD = 0;
    private static final int MAX_COORD = 7;
    private static final int BOARD_SIZE = 8;

    private static final int[][] DIRECTIONS = {
            {-1, 0},
            { 1, 0},
            { 0, -1},
            { 0, 1}
    };

    public Rook(Color color) {
        super(PieceType.ROOK, color);
    }

    @Override
    public Piece makeCopy() {
        return new Rook(getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {
        if (start.equals(end)) {
            return false;
        }
        if (!isStraightLine(start, end)) {
            return false;
        }
        if (!isPathClear(start, end, board)) {
            return false;
        }
        if (isOccupiedByAlly(end, board)) {
            return false;
        }
        return !wouldExposeKing(start, end, board);
    }

    private boolean isStraightLine(Location start, Location end) {
        return start.getX() == end.getX() || start.getY() == end.getY();
    }

    private boolean isPathClear(Location start, Location end, Board board) {
        int rowDirection = Integer.signum(end.getX() - start.getX());
        int colDirection = Integer.signum(end.getY() - start.getY());

        int currentRow = start.getX() + rowDirection;
        int currentCol = start.getY() + colDirection;

        while (currentRow != end.getX() || currentCol != end.getY()) {
            if (board.isPieceHere(new Location(currentRow, currentCol))) {
                return false;
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }
        return true;
    }

    private boolean isOccupiedByAlly(Location end, Board board) {
        if (!board.isPieceHere(end)) {
            return false;
        }
        return this.isSameColor(board.getPiece(end));
    }

    @Override
    public boolean hasValidMoves(Location currentPosition, Board board) {
        int currX = currentPosition.getX();
        int currY = currentPosition.getY();

        for (int[] dir : DIRECTIONS) {
            for (int step = 1; step < BOARD_SIZE; step++) {
                int targetX = currX + (dir[0] * step);
                int targetY = currY + (dir[1] * step);

                if (!isOnBoard(targetX, targetY)) {
                    break;
                }

                Location target = new Location(targetX, targetY);
                if (this.isValidMove(currentPosition, target, board)) {
                    return true;
                }
                if (board.isPieceHere(target)) {
                    break;
                }
            }
        }
        return false;
    }

    private boolean isOnBoard(int x, int y) {
        return x >= MIN_COORD && x <= MAX_COORD && y >= MIN_COORD && y <= MAX_COORD;
    }
}