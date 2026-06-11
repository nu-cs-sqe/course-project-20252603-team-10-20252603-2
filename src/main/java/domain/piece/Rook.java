package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;

public class Rook extends Piece {
    private static final int MIN_COORD = 0;
    private static final int MAX_COORD = 7;
    private static final int BOARD_SIZE = 8;

    public Rook(Color color) {
        super(PieceType.ROOK, color);
    }

    @Override
    public Piece makeCopy() {
        return new Rook(getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {
        if (start.equals(end)) return false;

        boolean isHorizontal = (start.getX() == end.getX());
        boolean isVertical = (start.getY() == end.getY());
        if (!isHorizontal && !isVertical) return false;

        int colDirection = isHorizontal ? ((end.getY() > start.getY()) ? 1 : -1) : 0;
        int rowDirection = !isHorizontal ? ((end.getX() > start.getX()) ? 1 : -1) : 0;

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

        boolean leavesKingInCheck = false;


            Piece originalPiece = board.getPiece(end);

            board.setPiece(end, this);
            board.removePiece(start);

            Location kingLocation = null;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    Location loc = new Location(i, j);
                    if (board.isPieceHere(loc)) {
                        Piece p = board.getPiece(loc);
                        if (p.getType() == PieceType.KING && p.getColor() == this.getColor()) {
                            kingLocation = loc;
                            break;
                        }
                    }
                }
                if (kingLocation != null) break;
            }

            if (kingLocation != null) {
                King alliedKing = (King) board.getPiece(kingLocation);
                leavesKingInCheck = alliedKing.isInCheck(kingLocation, board);
            }

            board.setPiece(start, this);
            if (originalPiece != null) {
                board.setPiece(end, originalPiece);
            } else {
                board.removePiece(end);
            }
        return !leavesKingInCheck;
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

            for (int step = 1; step < BOARD_SIZE; step++) {
                int targetX = currX + (dX * step);
                int targetY = currY + (dY * step);

                if (targetX < MIN_COORD || targetX > MAX_COORD || targetY < MIN_COORD || targetY > MAX_COORD) {
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