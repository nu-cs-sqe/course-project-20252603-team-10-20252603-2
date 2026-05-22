package domain.piece;

import domain.Board;
import domain.Location;

public class Queen extends Piece {
    public Queen(PieceColor color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public Piece makeCopy() {
        return new Queen(getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {

        boolean xMovement = (start.getY() == end.getY()) && (start.getX() != end.getX());
        boolean yMovement = (start.getY() != end.getY()) && (start.getX() == end.getX());

        if (xMovement || yMovement) return true;

        //  TODO: complete method
        return false;
    }
}