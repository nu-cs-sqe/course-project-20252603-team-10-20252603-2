package domain.piece;

import domain.Board;
import domain.Location;

public class Pawn extends Piece {
    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color);
    }

    @Override
    public Piece makeCopy() {
        return new Pawn(getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {
        //  STRUCTURE:

        //  is location start == location end?
        if (start.equals(end)) {
            return false;
        }

        return true;
    }
}