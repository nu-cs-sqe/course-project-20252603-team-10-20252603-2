package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;

public class King extends Piece{
    public King(Color color) {
        super(PieceType.KING, color);
    }

    public boolean isInCheck(Location kingLocation, Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Location location = new Location(i, j);
                if (!board.isPieceHere(location)) continue;
                Piece piece = board.getPiece(location);
                if (piece.isSameColor(this)) continue;
                if (piece.isValidMove(location, kingLocation, board)) return true;
            }
        }
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
    public boolean hasValidMoves() {
        //  TODO: complete method
        return false;
    }

}