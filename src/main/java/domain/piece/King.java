package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;

public class King extends Piece{
    public King(Color color) {
        super(PieceType.KING, color);
    }

    public boolean isInCheck(Location location, Board board) {
        // TODO: need ot implement
        return false;
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

    @Override
    public boolean hasValidMoves(Location location, Board board) {
        //  TODO: complete method
        return false;
    }

}