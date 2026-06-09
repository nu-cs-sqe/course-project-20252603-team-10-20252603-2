package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;

public class Knight extends Piece {
    final int BOARD_SIZE = 8;
    final int MIN_COORD = 0;
    final int MAX_COORD = 7;

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

        if (board.isPieceHere(end)) {
            Piece target = board.getPiece(end);
            if (this.isSameColor(target)) return false;
        }

        boolean isL1 = (end.getX() == start.getX() + 2) && (end.getY() == start.getY() - 1);
        boolean isL2 = (end.getX() == start.getX() + 2) && (end.getY() == start.getY() + 1);
        boolean isL3 = (end.getX() == start.getX() + 1) && (end.getY() == start.getY() - 2);
        boolean isL4 = (end.getX() == start.getX() + 1) && (end.getY() == start.getY() + 2);
        boolean isL5 = (end.getX() == start.getX() - 2) && (end.getY() == start.getY() - 1);
        boolean isL6 = (end.getX() == start.getX() - 2) && (end.getY() == start.getY() + 1);
        boolean isL7 = (end.getX() == start.getX() - 1) && (end.getY() == start.getY() - 2);
        boolean isL8 = (end.getX() == start.getX() - 1) && (end.getY() == start.getY() + 2);

        if (!(isL1 || isL2 || isL3 || isL4 || isL5 || isL6 || isL7 || isL8)) return false;

        Piece originalTarget = board.getPiece(end);

        board.setPiece(end, this);
        board.removePiece(start);

        Location kingLocation = null;
        King alliedKing = null;


        for (int i = MIN_COORD; i < BOARD_SIZE; i++) {
            for (int j = MIN_COORD; j < BOARD_SIZE; j++) {
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

        if (exposesKing) {
            return false;
        }

        return true;

    }

    @Override
    public boolean hasValidMoves(Location currentPosition, Board board) {
        int currX = currentPosition.getX();
        int currY = currentPosition.getY();

        int[][] potentialCoordinates = {
                {currX - 2, currY - 1}, {currX - 2, currY + 1},
                {currX - 1, currY - 2}, {currX - 1, currY + 2},
                {currX + 1, currY - 2}, {currX + 1, currY + 2},
                {currX + 2, currY - 1}, {currX + 2, currY + 1}
        };

        for (int[] coord : potentialCoordinates) {
            int targetX = coord[0];
            int targetY = coord[1];

            if (targetX >= MIN_COORD && targetX <= MAX_COORD && targetY >= MIN_COORD && targetY <= MAX_COORD) {
                Location target = new Location(targetX, targetY);

                if (this.isValidMove(currentPosition, target, board)) {
                    return true;
                }
            }
        }

        return false;
    }
}