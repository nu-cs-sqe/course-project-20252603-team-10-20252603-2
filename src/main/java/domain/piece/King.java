package domain.piece;

import domain.Board;
import domain.Location;

public class King extends Piece{
    public King(PieceColor color) {
        super(PieceType.KING, color);
    }

    @Override
    public Piece makeCopy() {
        return new King(this.getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {
        //  TODO: complete method
        return false;
    }

}