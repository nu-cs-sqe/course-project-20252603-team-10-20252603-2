package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;

public class Queen extends Piece {
    public Queen(Color color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public Piece makeCopy() {
        return new Queen(getColor());
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