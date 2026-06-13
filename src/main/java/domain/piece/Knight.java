package domain.piece;

import constants.Color;
import domain.Board;
import domain.Location;

public class Knight extends Piece {
    private static final int MIN_COORD = 0;
    private static final int MAX_COORD = 7;

    private static final int[][] KNIGHT_OFFSETS = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {-1, 2},
            { 1, -2}, { 1, 2},
            { 2, -1}, { 2, 1}
    };

    public Knight(Color color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    public Piece makeCopy() {
        return new Knight(this.getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {
        if (start.equals(end)) {
            return false;
        }
        if (!isLShape(start, end)) {
            return false;
        }
        if (isOccupiedByAlly(end, board)) {
            return false;
        }
        return !wouldExposeKing(start, end, board);
    }

    private boolean isLShape(Location start, Location end) {
        int diffX = Math.abs(end.getX() - start.getX());
        int diffY = Math.abs(end.getY() - start.getY());
        return (diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1);
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

        for (int[] offset : KNIGHT_OFFSETS) {
            int targetX = currX + offset[0];
            int targetY = currY + offset[1];

            if (isOnBoard(targetX, targetY)
                    && this.isValidMove(currentPosition, new Location(targetX, targetY), board)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOnBoard(int x, int y) {
        return x >= MIN_COORD && x <= MAX_COORD && y >= MIN_COORD && y <= MAX_COORD;
    }
}
