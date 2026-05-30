package domain.piece;

import domain.Board;
import domain.Location;

public class Knight extends Piece {
    public Knight(PieceColor color) {
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
            if (this.isSameColor(target)) {
                return false;
            }
        }

        boolean isL1 = (end.getX() == start.getX() + 2) && (end.getY() == start.getY() - 1);
        boolean isL2 = (end.getX() == start.getX() + 2) && (end.getY() == start.getY() + 1);
        boolean isL3 = (end.getX() == start.getX() + 1) && (end.getY() == start.getY() - 2);
        boolean isL4 = (end.getX() == start.getX() + 1) && (end.getY() == start.getY() + 2);
        boolean isL5 = (end.getX() == start.getX() - 2) && (end.getY() == start.getY() - 1);
        boolean isL6 = (end.getX() == start.getX() - 2) && (end.getY() == start.getY() + 1);
        boolean isL7 = (end.getX() == start.getX() - 1) && (end.getY() == start.getY() - 2);
        boolean isL8 = (end.getX() == start.getX() - 1) && (end.getY() == start.getY() + 2);

        if (isL1 || isL2 || isL3 || isL4 || isL5 || isL6 || isL7 || isL8) {
            return true;
        }

        return false;
    }
}
