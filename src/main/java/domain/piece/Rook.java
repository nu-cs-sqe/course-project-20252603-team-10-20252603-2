package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;

public class Rook extends Piece {
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

        boolean isHorizontal = (start.getX() == end.getX());
        boolean isVertical = (start.getY() == end.getY());

        if (!isHorizontal && !isVertical) {
            return false;
        }

        int colDirection = 0;
        int rowDirection = 0;

        if (isHorizontal) {
            if (end.getY() > start.getY()) {
                colDirection = 1;
            } else {
                colDirection = -1;
            }
        } else {
            if (end.getX() > start.getX()) {
                rowDirection = 1;
            } else {
                rowDirection = -1;
            }
        }

        int currentCol = start.getY() + colDirection;
        int currentRow = start.getX() + rowDirection;

        while (currentCol != end.getY() || currentRow != end.getX()) {
            if (board.isPieceHere(new Location(currentRow, currentCol))) {
                return false;
            }
            currentCol += colDirection;
            currentRow += rowDirection;
        }

        if (board.isPieceHere(end)) {
            Piece target = board.getPiece(end);
            if (this.isSameColor(target)) return false;
        }

        return true;
    }

    @Override
    public boolean hasValidMoves(Location currentPosition, Board board) {
        int currX = currentPosition.getX();
        int currY = currentPosition.getY();

        int[][] directions = {
                {-1,  0},
                { 1,  0},
                { 0, -1},
                { 0,  1}
        };

        for (int[] dir : directions) {
            int dX = dir[0];
            int dY = dir[1];

            for (int step = 1; step < 8; step++) {
                int targetX = currX + (dX * step);
                int targetY = currY + (dY * step);

                if (targetX < 0 || targetX > 7 || targetY < 0 || targetY > 7) {
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
}