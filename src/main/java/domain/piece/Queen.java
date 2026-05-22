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
        int diffX = Math.abs(start.getX() - end.getX());
        int diffY = Math.abs(start.getY() - end.getY());
        boolean diagonalMovement = (diffX == diffY);


        if (xMovement || yMovement || diagonalMovement) return true;

        //  TODO: complete method
        return false;
    }
}