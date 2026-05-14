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
        return false;
    }
}