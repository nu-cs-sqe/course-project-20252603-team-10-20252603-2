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
        //  TODO: complete method

        boolean isL1 = (end.getX() == start.getX()+2) && (end.getY() == start.getY()-1);
        boolean isL2 = (end.getX() == start.getX()+2) && (end.getY() == start.getY()+1);
        boolean isL3 = (end.getX() == start.getX()+1) && (end.getY() == start.getY()-2);
        boolean isL4 = (end.getX() == start.getX()+1) && (end.getY() == start.getY()+2);
        boolean isL5 = (end.getX() == start.getX()-2) && (end.getY() == start.getY()-1);
        boolean isL6 = (end.getX() == start.getX()-2) && (end.getY() == start.getY()+1);
        boolean isL7 = (end.getX() == start.getX()-1) && (end.getY() == start.getY()-2);
        boolean isL8 = (end.getX() == start.getX()-1) && (end.getY() == start.getY()+2);

        if (isL1) return true;
        if (isL2) return true;
        if (isL3) return true;
        if (isL4) return true;
        if (isL5) return true;
        if (isL6) return true;
        if (isL7) return true;
        if (isL8) return true;

        return false;
    }
}