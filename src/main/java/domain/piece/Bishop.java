package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(PieceType.BISHOP, color);
    }

    @Override
    public Piece makeCopy() {
        return new Bishop(getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {
        //  TODO: complete method
        return false;
    }

    @Override
    public boolean hasValidMoves() {
        //  TODO: complete method
        return false;
    }
}